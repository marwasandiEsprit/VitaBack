package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table( name = "QA")
public class QuestionAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionsid;
    private String question;
    private String answer;
}
