package tn.esprit.vitanova.services;



import tn.esprit.vitanova.entities.Quotes;

import java.util.List;

public interface IQuoatesService {
    Quotes addQuoate(Quotes q);
    void updateQuoate(Long idQuotes , Quotes q);
    Quotes getQuotebyId(Long idQuotes);
    List<Quotes> chercherTousQuotes();
    void supprimerQuote(Long idQuotes);
}
