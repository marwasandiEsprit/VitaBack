package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RapportNutr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRapportNutr")
    private Long idRapportNutr; // Clé primaire

    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateRappNutr;

    @JsonIgnoreProperties({"consultationsAsPsychiatrist", "consultationsAsClient", "psychiatristReports", "clientReport", "nutrs"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nutritionist_id")
    private User nutristionist ;

    @JsonIgnoreProperties({"consultationsAsPsychiatrist", "consultationsAsClient", "psychiatristReports", "clientReport", "nutrs"})
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private User clients;
}
