package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Ingredients")
public class Ingredients implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idIngredients")
    private Long idIngredients; // Clé primaire
    private String description;
    private String Products;
    private String images;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Recepies> recipes = new HashSet<>();
}
