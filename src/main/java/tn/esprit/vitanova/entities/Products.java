package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table( name = "Products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProducts")
    private Long idProducts; // Clé primaire
    private String prodName;
    @Enumerated(EnumType.STRING)
    private ProductType typeProd;
    private Long price;
    private int quantityP;
    private String descriptionP;
    private String imageUrl;
    private boolean increaseNeeded;
    private boolean quantityLow = false;
    private Boolean quantityExceeded;
//    @Temporal(TemporalType.DATE)
    private String expiration;
    private int quantitySold; // Quantity sold for this product
    private Boolean purchased;
    @ManyToOne
    User user;
    @OneToOne
    private PaymentProduct paymentProduct;
    @Temporal(TemporalType.TIMESTAMP)

    @ManyToOne
    Cart cart;
}
