package tn.esprit.vitanova.services;


import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.RoleRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder encoder;

    private JavaMailSender emailSender;

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepo.findByName(name);
    }

    @Override
    public User saveUser(User user) {
        user.setCreationDate(new Date(System.currentTimeMillis()));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivated(false);
        user.setBanned(false);
        String token = generateToken();
        user.setActivationToken(token);
        user.setActivationTokenCreationDate(LocalDateTime.now());

        String email = user.getEmail();
        String subject = "Verification Code";
        String body = "Your verification code is: " + token;

        sendEmail(email, subject, body);
        return userRepo.save(user);}

    @Override
    public String activateUser(String token)
    {
        Optional<User> userOptional = userRepo.findByActivationToken(token);

        if (userOptional.isEmpty()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getActivationTokenCreationDate();

        if (isTokenExpired(tokenCreationDate, 1440)) {
            return "Token expired.";

        }

        User user = userOptional.get();

        user.setActivationToken(null);
        user.setActivationTokenCreationDate(null);
        user.setActivated(true);

        userRepo.save(user);

        return "User successfully activated.";
    }
    @Override
    public String forgetPassword(String email) {

        Optional<User> userOptional = userRepo.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();

        if (!user.getActivated())
        {
            return "The account is not activated yet";

        }
        String token = generateToken();
        user.setPwdToken(token);
        user.setPwdTokenCreationDate(LocalDateTime.now());

        String subject = "Verification Code";
        String body = "Your verification code is: " + token;

        sendEmail(email, subject, body);
        userRepo.save(user);
        return "Mail Sent successfully...";
    }

    @Override
    public String resetPassword(String token, String password) {

        Optional<User> userOptional = userRepo.findByPwdToken(token);

        if (userOptional.isEmpty()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getPwdTokenCreationDate();

        if (isTokenExpired(tokenCreationDate, 30)) {
            return "Token expired.";

        }

        User user = userOptional.get();
        user.setPassword(encoder.encode(password));
        user.setPwdToken(null);
        user.setPwdTokenCreationDate(null);

        userRepo.save(user);

        return "Your password successfully updated.";
    }



    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private boolean isTokenExpired(LocalDateTime tokenCreationDate, long expireAfterMnts) {
        return Duration.between(tokenCreationDate, LocalDateTime.now()).toMinutes() >= expireAfterMnts;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("besttrip.esprit1@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }

}
