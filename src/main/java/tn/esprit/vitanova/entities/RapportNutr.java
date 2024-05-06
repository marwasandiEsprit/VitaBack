package tn.esprit.vitanova.entities;

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
@Table( name = "RapportNutr")
public class RapportNutr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRapportNutr")
    private Long idRapportNutr; // Clé primaire
    private Long OwnerId;
    private Long idPatient;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateRappNutr;
}
