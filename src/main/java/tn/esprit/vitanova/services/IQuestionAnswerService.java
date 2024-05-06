package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.QuestionAnswer;

import java.util.List;

public interface IQuestionAnswerService {
    QuestionAnswer addQuestions(QuestionAnswer p);
    List<QuestionAnswer> getAllQuestions();
}
