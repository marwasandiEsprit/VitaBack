package tn.esprit.vitanova.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@Table(name = "Sales")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Products product;
    private int quantitySold;
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate; // Date of the sale
    @ManyToOne
    private User buyer;
}
