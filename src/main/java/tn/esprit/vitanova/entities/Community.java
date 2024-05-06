package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Community")
public class Community implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommunity")
    private Long idCommunity; // Clé primaire

    private String name;
    private String description;

    @OneToMany(mappedBy = "community")
    private Set<Posts> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "community")
    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

}
