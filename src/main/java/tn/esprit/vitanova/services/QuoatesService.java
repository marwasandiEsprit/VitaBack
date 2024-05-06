package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Quotes;
import tn.esprit.vitanova.repository.QuotesRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuoatesService implements IQuoatesService{
    QuotesRepo quotesRepo;

    @Override
    public Quotes addQuoate(Quotes q) {
        return quotesRepo.save(q);
    }

    @Override
    public void updateQuoate(Long idQuotes, Quotes q) {
        q.setIdQuotes(idQuotes);
        quotesRepo.save(q);
    }

    @Override
    public Quotes getQuotebyId(Long idQuotes) {
        if (idQuotes == null) {
            // Handle the case where ID is null, e.g., throw an exception or return null
            throw new IllegalArgumentException("ID cannot be null");
        }
        return quotesRepo.findById(idQuotes).orElse(null);

    }

    @Override
    public List<Quotes> chercherTousQuotes() {
        return quotesRepo.findAll();
    }

    @Override
    public void supprimerQuote(Long idQuotes) {
        quotesRepo.deleteById(idQuotes);
    }
}
