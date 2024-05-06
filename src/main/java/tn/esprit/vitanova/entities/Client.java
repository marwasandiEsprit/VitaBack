package tn.esprit.vitanova.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Client implements Serializable
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long clientId;
    private String nom;
    private String prenom;
    private String email;
    private String phonenumber;


}
