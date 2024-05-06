package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCart")
    private Long idCart; // Clé primaire
    private Long ownerId;
    private Long idProducts;
    private String products;
    private Long quantity;
    private Long total;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="cart")
    private Set<Products>prods;
}
