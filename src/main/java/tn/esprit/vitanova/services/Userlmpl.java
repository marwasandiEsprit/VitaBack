package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.PremuimVRepo;
import tn.esprit.vitanova.repository.ProductsRepo;
import tn.esprit.vitanova.repository.RoleRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.*;

@Service
@AllArgsConstructor
public class Userlmpl implements UserService {
    private UserRepo userRepo;

    private RoleRepo roleRepo;

    private PremuimVRepo premuimVRepo;
    private ProductsRepo productsRepo;
    private JavaMailSender emailSender;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User updateUser(Long userId, User userDTO) {
        User existingUser = getUser(userId);

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setCreationDate(userDTO.getCreationDate());
        existingUser.setNom(userDTO.getNom());
        existingUser.setPrenom(userDTO.getPrenom());
        existingUser.setGender(userDTO.getGender());
        existingUser.setDesctiption(userDTO.getDesctiption());
        existingUser.setAchievments(userDTO.getAchievments());
        existingUser.setFears(userDTO.getFears());
        existingUser.setAge(userDTO.getAge());
        existingUser.setAnnualIncome(userDTO.getAnnualIncome());
        existingUser.setActivated(userDTO.getActivated());
        existingUser.setBanned(userDTO.getBanned());
        existingUser.setRoles(userDTO.getRoles());

        return userRepo.save(existingUser);
    }


    @Override
    public String deleteUser(Long id)
    {
        userRepo.deleteById(id);
        return "User with id " + id + " has been successfully deleted !";
    }

    @Override
    public String banUser(Long id) {
        User user = getUser(id);
        if (user.getBanned()) {
            user.setBanned(false);
            userRepo.save(user);
            return "User with id " + id + " has been unbanned!";
        } else {
            user.setBanned(true);
            userRepo.save(user);
            return "User with id " + id + " has been banned!";
        }
    }

    @Override
    public long countBannedUsers() {
        return userRepo.countByBannedTrue();
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("baliaynos@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }

    @Override
    public List<User> getClients()
    {
        return userRepo.findAllByRolesName(ERole.ROLE_CLIENT);
    }
    @Override
    public void calculateScores() {
        List<User> users = getClients();

        for (User user : users) {
            int purchasedProducts = 0;
            double paidProductsAmount = 0.0;

            for (Sale sale : user.getSales()) {
                purchasedProducts = purchasedProducts + sale.getQuantitySold();
            }

            for (Sale sale : user.getSales()) {
                paidProductsAmount = paidProductsAmount + (sale.getProduct().getPrice()) * (sale.getQuantitySold());
            }

            double score = (purchasedProducts * 0.2) + (paidProductsAmount * 0.3);
            System.out.println(score);
            user.setScore(score);
            userRepo.save(user);
        }
    }


    @Override
    public Map<Long, Integer> findUserWithMostPurchasedProducts() {
        User userWithMostPurchases = null;
        int maxPurchases = 0;

        List<User> users = getClients();

        for (User user : users) {
            int purchaseCount = 0;
            for (Sale sale : user.getSales()) {
                purchaseCount = purchaseCount + sale.getQuantitySold();
            }

            if (purchaseCount > maxPurchases) {
                maxPurchases = purchaseCount;
                userWithMostPurchases = user;
            }
        }

        Map<Long, Integer> result = new HashMap<>();
        result.put(userWithMostPurchases.getId(), maxPurchases);

        return result;
    }


    @Override
    public Map<Long, Double> findUserWithMostPaidProductsAmount() {
        User userWithMostPaidAmount = null;
        double maxPaidAmount = 0.0;

        List<User> users = getClients();

        for (User user : users) {
            double currentPaidAmount = 0.0;
            for (Sale sale : user.getSales()) {
                currentPaidAmount = currentPaidAmount + (sale.getProduct().getPrice()) * (sale.getQuantitySold());
            }

            if (currentPaidAmount > maxPaidAmount) {
                maxPaidAmount = currentPaidAmount;
                userWithMostPaidAmount = user;
            }
        }

        Map<Long, Double> result = new HashMap<>();
        result.put(userWithMostPaidAmount.getId(), maxPaidAmount);

        return result;
    }


    @Override
    public List<Map<Long, Integer>> findUsersWithPurchasedProducts() {
        List<Map<Long, Integer>> results = new ArrayList<>();

        List<User> users = getClients();

        for (User user : users) {
            int purchaseCount = 0;

            for (Sale sale : user.getSales()) {
                purchaseCount = purchaseCount + sale.getQuantitySold();
            }

            if (purchaseCount > 0) {
                Map<Long, Integer> result = new HashMap<>();
                result.put(user.getId(), purchaseCount);
                results.add(result);
            }
        }

        return results;
    }


    @Override
    public List<Map<Long, Double>> findUsersWithPaidProductsAmount() {
        List<User> users = getClients();
        List<Map<Long, Double>> results = new ArrayList<>();

        for (User user : users) {
            double currentPaidAmount = 0.0;

            for (Sale sale : user.getSales()) {
                currentPaidAmount = currentPaidAmount + (sale.getProduct().getPrice()) * (sale.getQuantitySold());
            }

            if (currentPaidAmount > 0) {
                Map<Long, Double> result = new HashMap<>();
                result.put(user.getId(), currentPaidAmount);
                results.add(result);
            }
        }

        return results;
    }
    @Override
    public String getCluster(Integer age, Integer annualIncome, Integer spendingScore, Integer male, Integer female) {
        int[][] data = {{age, annualIncome, spendingScore, male, female}};

        WebClient client = WebClient.create();
        Mono<String> response = client.post()
                .uri("http://127.0.0.1:5000/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .bodyToMono(String.class);

        return (response.block());
    }
    @Override
    public void affectCluster(long idUser) {
        User user = this.getUser(idUser);

        int age = user.getAge();
        int annualIncome = (int) user.getAnnualIncome();
        int spendingScore = (int) user.getScore();
        int male = 0;
        int female = 0;
        if (Objects.equals(user.getGender(), "Male")) {
            male = 1;
        } else {
            female = 1;
        }
        String cluster = this.getCluster(age, annualIncome, spendingScore, male, female);
        user.setCluster(cluster);
        System.out.println(cluster);
        sendEmail(user.getEmail(), "You have been added to a new cluster", "You have been added to cluster " + cluster);
        userRepo.save(user);
    }
    @Override
    public List<User> getUsersWithPsychiatristSpecialty() {
        return userRepo.findByRolesName(ERole.ROLE_PSYCOLOGIST);
    }
    @Override
    public List<User> getUsersWithclientSpecialty() {
        return userRepo.findByRolesName(ERole.ROLE_CLIENT);
    }

    @Override
    public List<User> getUsersWithNutritionnisteSpecialty() {
        return userRepo.findAllByRolesName(ERole.ROLE_NUTRITOINISTE);

    }

    @Override
    public List<User> findByRolesName(ERole roleName) {
        return userRepo.findByRolesName(roleName) ;
    }


}