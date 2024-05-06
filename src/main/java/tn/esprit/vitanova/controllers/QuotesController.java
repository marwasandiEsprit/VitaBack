package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Quotes;
import tn.esprit.vitanova.services.IQuoatesService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quoates")
public class QuotesController {
    IQuoatesService iQuoatesService;
    @PostMapping("/addquote")
    public Quotes addQuoate( @RequestBody Quotes q) {
        return iQuoatesService.addQuoate(q) ;
    }

    @PutMapping("/updatequote/{idQuotes}")
    public void updateQuoate(@PathVariable Long idQuotes,@RequestBody Quotes q){
        iQuoatesService.updateQuoate(idQuotes,q);
    }
    @GetMapping("/getquotebyid/{idQuotes}")
    public Quotes getquoteId(Long idQuotes){
        return iQuoatesService.getQuotebyId(idQuotes);
    }
    @GetMapping("/getallquotes")
    public List<Quotes> chercherTousQuotes(){
        return iQuoatesService.chercherTousQuotes();
    }
    @DeleteMapping("/deletequote/{idQuotes}")
    public  void  supprimerQuote(@PathVariable Long idQuotes) {
        iQuoatesService.supprimerQuote(idQuotes);
    }
}
