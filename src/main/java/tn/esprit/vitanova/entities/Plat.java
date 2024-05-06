package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlat;
    private String Name ;
    private float  Quantity;
    private float  nbCalories ;
    @OneToMany(mappedBy = "plat", cascade = CascadeType.ALL)
    public List<PlatConseils> platConseils;
}
