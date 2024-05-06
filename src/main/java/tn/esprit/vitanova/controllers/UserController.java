package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.UserRepo;
import tn.esprit.vitanova.services.AuthService;
import tn.esprit.vitanova.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.deleteUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/ban/{id}")
    public ResponseEntity<String> banUser(@PathVariable Long id) {
        String response = userService.banUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count-banned")
    public ResponseEntity<Long> countBannedUsers() {
        long count = userService.countBannedUsers();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/getClients")
    public ResponseEntity<List<User>> getClients()
    {
        List<User> clients = userService.getClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PutMapping("/calculate-scores")
    public ResponseEntity<Void> calculateScores() {
        userService.calculateScores();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/most-purchased-products")
    public ResponseEntity<Map<Long, Integer>> findUserWithMostPurchasedProducts() {
        Map<Long, Integer> result = userService.findUserWithMostPurchasedProducts();
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/most-paid-products-amount")
    public ResponseEntity<Map<Long, Double>> findUserWithMostPaidProductsAmount() {
        Map<Long, Double> result = userService.findUserWithMostPaidProductsAmount();
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users-with-purchased-products")
    public ResponseEntity<List<Map<Long, Integer>>> findUsersWithPurchasedProducts() {
        List<Map<Long, Integer>> results = userService.findUsersWithPurchasedProducts();
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/users-with-paid-products")
    public ResponseEntity<List<Map<Long, Double>>> findUsersWithPaidProductsAmount() {
        List<Map<Long, Double>> results = userService.findUsersWithPaidProductsAmount();
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping("/cp/{idUser}")
    public void affectCluster(@PathVariable("idUser")long idUser)
    {
        userService.affectCluster(idUser);
    }

    @PostMapping("/affectclustersclients")
    public void affectClustersClients() {
        List<User> clients = userService.getClients();

        for (User user : clients) {
            affectCluster(user.getId());
        }
    }
    @GetMapping("/psy")
    public ResponseEntity<List<User>> getUsersWithPsychiatristSpecialty() {
        List<User> psychiatrists = userService.getUsersWithPsychiatristSpecialty();
        return ResponseEntity.ok(psychiatrists);
    }
    @GetMapping("/client")
    public ResponseEntity<List<User>> getUsersWithclientSpecialty() {
        List<User> psychiatrists = userService.getUsersWithclientSpecialty();
        return ResponseEntity.ok(psychiatrists);
    }

}

