package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

public class RapportPsy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRapportPsy")
    private Long idRapportPsy; // Clé primaire

    private String description;
    private String summary ;
    @Temporal(TemporalType.DATE)
    private Date dateRappPs;



    @JsonIgnoreProperties({"consultationsAsPsychiatrist","consultationsAsClient","psychiatristReports","clientReport"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "psychiatrist_id")
    private User psychiatrist;
    @JsonIgnoreProperties({"consultationsAsPsychiatrist","consultationsAsClient","psychiatristReports","clientReport"})
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private User clients;
}
