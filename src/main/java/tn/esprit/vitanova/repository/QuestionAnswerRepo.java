package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.QuestionAnswer;

@Repository
public interface QuestionAnswerRepo extends JpaRepository<QuestionAnswer,Long> {
}
