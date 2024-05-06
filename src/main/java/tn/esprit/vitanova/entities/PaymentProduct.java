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
@Table( name = "PaymentProduct")
public class PaymentProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPaymentProduct")
    private Long idPaymentProduct; // Clé primaire
    private Long totalPrice;
    private String object;

    @OneToOne(mappedBy = "paymentProduct")
    private Products  products;
}
