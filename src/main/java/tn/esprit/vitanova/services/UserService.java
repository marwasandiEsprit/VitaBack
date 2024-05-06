package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User updateUser(Long userId, User userDTO);

    String deleteUser(Long id);

    String banUser(Long id);

    long countBannedUsers();

    List<User> getClients();

    void calculateScores();

    Map<Long, Integer> findUserWithMostPurchasedProducts();

    Map<Long, Double> findUserWithMostPaidProductsAmount();


    List<Map<Long, Integer>> findUsersWithPurchasedProducts();

    List<Map<Long, Double>> findUsersWithPaidProductsAmount();

    String getCluster(Integer age, Integer annualIncome, Integer spendingScore, Integer male, Integer female);

    void affectCluster(long idUser);
    public List<User> getUsersWithPsychiatristSpecialty();
    public List<User> getUsersWithclientSpecialty();
}
