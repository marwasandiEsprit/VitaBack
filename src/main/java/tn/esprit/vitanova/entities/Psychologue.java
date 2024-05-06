package tn.esprit.vitanova.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Psychologue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long psychologueId;
    private String nom;
    private String prenom;
    private String email;
    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    private String gender;


}
