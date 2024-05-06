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
@Table( name = "PaymentV")
public class PaymentV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPaymentV")
    private Long idPaymentV; // Clé primaire
    private Long totalPrice;
    private String object;

    @OneToOne(mappedBy = "paymentV")
    private PremuimV  premuimV;

}
