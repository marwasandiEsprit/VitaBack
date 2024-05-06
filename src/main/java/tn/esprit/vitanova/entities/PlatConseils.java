package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatConseils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Taille taille ;
    private int  maxQuantite ;
    private int  minQauntite ;
    private String conseil ;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plat_id", nullable = false)
    private Plat plat;
}
