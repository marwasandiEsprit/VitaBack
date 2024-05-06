package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.QuestionAnswer;
import tn.esprit.vitanova.services.IQuestionAnswerService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/questions")
public class QuestionAnswerController {
    IQuestionAnswerService iQuestionAnswerService;
    @PostMapping("/addquestion")
    public QuestionAnswer addQuoate(@RequestBody QuestionAnswer q) {
        return iQuestionAnswerService.addQuestions(q) ;
    }

    @GetMapping("/getallquestion")
    public List<QuestionAnswer> chercherTousQuestions(){
        return iQuestionAnswerService.getAllQuestions();
    }

    @PutMapping("/updatequestion/{questionsid}")
    public void updateQuestionAnswer(@PathVariable Long questionsid,@RequestBody QuestionAnswer q){
        iQuestionAnswerService.updateQuestionAnswer(questionsid,q);
    }
    @GetMapping("/getquestionbyid/{questionsid}")
    public QuestionAnswer getQuestionAnswerId(@PathVariable Long questionsid){
        return iQuestionAnswerService.getQuestionAnswerbyId(questionsid);
    }

    @DeleteMapping("/deletequestion/{questionsid}")
    public  void  supprimerQuestionAnswer(@PathVariable Long questionsid) {
        iQuestionAnswerService.supprimerQuestionAnswer(questionsid);
    }
}
