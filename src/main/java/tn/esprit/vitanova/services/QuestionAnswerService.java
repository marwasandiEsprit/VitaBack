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
}
