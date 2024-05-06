package tn.esprit.vitanova.controllers;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.Dto.requests.*;
import tn.esprit.vitanova.Dto.responses.JwtResponse;
import tn.esprit.vitanova.Dto.responses.MessageResponse;
import tn.esprit.vitanova.Dto.responses.SignupResponse;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.security.jwt.JwtUtils;
import tn.esprit.vitanova.security.services.TwoFactorAuthenticationService;
import tn.esprit.vitanova.security.services.UserDetailsImpl;
import tn.esprit.vitanova.services.IAuthService;
import tn.esprit.vitanova.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  AuthenticationManager authenticationManager;
  IAuthService authService;
  UserService userService;
  PasswordEncoder encoder;
  JwtUtils jwtUtils;
  TwoFactorAuthenticationService tfaService;
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    User user = userService.getUser(userDetails.getId());
    if (!user.getActivated() || user.getBanned()) {
      throw new BadCredentialsException("Your account is not activated or banned!");
    } else {
      if (user.isMfaEnabled())
      {
        return ResponseEntity.ok(new JwtResponse("", userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), null, true));
      }
      else {
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles, false));
      } }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (authService.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (authService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
            signUpRequest.getPassword());

    String  role = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (role == null) {
      Role defaultRole = authService.findByName(ERole.ROLE_CLIENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(defaultRole);
    } else {
      switch (role) {
        case "ROLE_COACH" -> {
          Role coachRole = authService.findByName(ERole.ROLE_COACH)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(coachRole);
        }
        case "ROLE_NUTRITOINISTE" -> {
          Role nutRole = authService.findByName(ERole.ROLE_NUTRITOINISTE)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(nutRole);
        }
        case "ROLE_PSYCOLOGIST" -> {
          Role psyRole = authService.findByName(ERole.ROLE_PSYCOLOGIST)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(psyRole);
        }
        case "ROLE_ADMIN" -> {
          Role adminRole = authService.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
        }
        default -> {
          Role defaultRole = authService.findByName(ERole.ROLE_CLIENT)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(defaultRole);
        }
      }
    }
    user.setRoles(roles);

    user.setMfaEnabled(signUpRequest.isMfaEnabled());

    if (signUpRequest.isMfaEnabled()) {
      user.setSecret(tfaService.generateNewSecret());
    }
    authService.saveUser(user);
    return  ResponseEntity.ok(new SignupResponse(user.isMfaEnabled(),tfaService.generateQrCodeImageUri(user.getSecret())));

  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest) {

    User user = authService.findByUsername(verificationRequest.getUsername())
            .orElseThrow(() -> new EntityNotFoundException(
                    String.format("No user found with %S", verificationRequest.getUsername()))
            );
    if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {
      throw new BadCredentialsException("Code is not correct!");
    }
    String jwt = jwtUtils.generateJwtToken(UserDetailsImpl.build(user));
    List<String> roles = user.getRoles().stream()
            .map(Role::getName)
            .map(ERole::name)
            .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, user.getId(),
            user.getUsername(), user.getEmail(), roles, user.isMfaEnabled()));
  }
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

  @PostMapping("/activate")
  public ResponseEntity<?> activateUser(@RequestBody String token)
  {
    return ResponseEntity.ok(new MessageResponse(authService.activateUser(token)));
  }
  @PostMapping("/forgetpassword")
  public ResponseEntity<?> forgetPassword(@RequestBody ForgetPassword fp) {
    return ResponseEntity.ok(new MessageResponse(authService.forgetPassword(fp.getEmail())));
  }

  @PutMapping("/resetpassword")
  public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest rpr) {
    return ResponseEntity.ok(new MessageResponse(authService.resetPassword(rpr.getToken(), rpr.getPassword())));
  }

}
