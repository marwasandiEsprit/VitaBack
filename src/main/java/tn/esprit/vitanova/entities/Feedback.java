package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
    public class Feedback {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "therapist_id", nullable = false)
        private User therapist;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column(nullable = false)
        private int rating; // Rating from 0 to 5

    }
