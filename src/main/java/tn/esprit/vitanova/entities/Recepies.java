package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Recepies")
public class Recepies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRecepies")
    private Long idRecepies; // Clé primaire
    private double duration;
    // Durée de préparation en minutes (double pour inclure des demi-minutes si nécessaire)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_type_id")
    private DishType dishType; // Référence à l'entité DishType

    private Date dateAdded;
    @Column(length = 10000) // Définir une taille de colonne de 1000 caractères pour la description
    private String description;


    private String images;

    @Column(length = 1000) // Définir une taille de colonne de 1000 caractères pour la description
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredients> ingredients = new HashSet<>();
}
