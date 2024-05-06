package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
//pour insérer le champ createdDate automatiquement lors de la creation du commentaire
@EntityListeners(AuditingEntityListener.class)
public class Comments implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComments")
    private Long idComments; // Clé primaire

    @CreatedDate
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @NotNull
    private String comment;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    @JsonIgnore
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
