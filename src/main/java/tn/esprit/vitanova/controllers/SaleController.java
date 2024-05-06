package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.vitanova.entities.Sale;
import tn.esprit.vitanova.services.ISaleService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sale")
public class SaleController {
ISaleService iSaleService;
    @GetMapping("/getsales")
    public List<Sale> getAllProds(){
        return iSaleService.chercherTousSales();
    }
}
