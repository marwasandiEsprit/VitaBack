package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table( name = "Posts")
@EntityListeners(AuditingEntityListener.class)
public class Posts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPosts")
    private Long idPosts; // Clé primaire

    @CreatedDate
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    private String Post;
    private String ImageP;
    private String Description;
    private Long idOwner;

    @ManyToOne
    @JoinColumn(name = "community_id_community")
    @JsonIgnore
    private Community community;

}
