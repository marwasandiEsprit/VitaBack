package tn.esprit.vitanova.services;



import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;
import tn.esprit.vitanova.entities.User;

import java.util.Optional;

public interface IAuthService {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<Role> findByName(ERole name);

    User saveUser(User user);

    String activateUser(String token);

    String forgetPassword(String email);

    String resetPassword(String token, String password);

    void sendEmail(String toEmail, String subject, String body);
}
