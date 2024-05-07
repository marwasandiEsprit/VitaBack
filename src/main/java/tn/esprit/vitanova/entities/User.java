package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String nom;

    private String prenom;

    private String gender;
    private String desctiption;
    private String achievments;
    private String fears;
    private int age;
    private Boolean banned;
    private Boolean activated;

    private String activationToken;
    private Double Rating;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime activationTokenCreationDate;
    private String pwdToken;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime pwdTokenCreationDate;

    private double score;
    private double annualIncome;

    private String cluster;
    private boolean mfaEnabled;
    private String secret;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "community_id_community")
    @JsonIgnore
    private Community community;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserAchievementHistory> userAchievementHistories = new LinkedHashSet<>();
    @ManyToMany
    @JoinTable(name = "User_achievements",
            joinColumns = @JoinColumn(name = "user_idUser"),
            inverseJoinColumns = @JoinColumn(name = "achievements_id"))
    private Set<Achievement> achievements = new LinkedHashSet<>();
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "psychiatrist", cascade = CascadeType.ALL)
    private List<Consultation> consultationsAsPsychiatrist;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Consultation> consultationsAsClient;
    @OneToMany(mappedBy = "psychiatrist", cascade = CascadeType.ALL)
    private List<RapportPsy> psychiatristReports;

    @OneToMany(mappedBy = "nutristionist", cascade = CascadeType.ALL)
    private List<RapportNutr> nutrs;
    // Association with client report (one-to-one)
    @OneToOne(mappedBy = "clients")
    private RapportPsy clientReport;

    // Association with client report (one-to-one)





    ////relation product
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Products> products;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "buyer")
    private Set<Sale> sales;
    ////relation journal
    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    private Jornals jornals;
    //// notification
//    @JsonIgnore
//    @ToString.Exclude
//    @ManyToMany(mappedBy = "users",
//            cascade = CascadeType.ALL)
//    private Set<Notifications> notifications;

    ///premuimv
    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    private PremuimV premuimV;
}



