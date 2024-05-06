package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.QuestionAnswer;
import tn.esprit.vitanova.repository.QuestionAnswerRepo;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class QuestionAnswerService implements IQuestionAnswerService{
    QuestionAnswerRepo answerRepo;

    @Override
    public QuestionAnswer addQuestions(QuestionAnswer p) {
        return answerRepo.save(p);
    }

    @Override
    public List<QuestionAnswer> getAllQuestions() {
        return answerRepo.findAll();
    }


    @Override
    public void updateQuestionAnswer(Long questionsid, QuestionAnswer q) {
        q.setQuestionsid(questionsid);
        answerRepo.save(q);
    }

    @Override
    public QuestionAnswer getQuestionAnswerbyId(Long questionsid) {
        if (questionsid == null) {
            // Handle the case where ID is null, e.g., throw an exception or return null
            throw new IllegalArgumentException("ID cannot be null");
        }
        return answerRepo.findById(questionsid).orElse(null);
    }

    @Override
    public void supprimerQuestionAnswer(Long questionsid) {
        answerRepo.deleteById(questionsid);
    }
}
