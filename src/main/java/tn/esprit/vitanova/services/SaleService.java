package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Sale;
import tn.esprit.vitanova.repository.SaleRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SaleService implements ISaleService{
    SaleRepo saleRepo;

    @Override
    public List<Sale> chercherTousSales() {
        return saleRepo.findAll();
    }
}
