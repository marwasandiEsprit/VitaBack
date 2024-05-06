package tn.esprit.vitanova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.vitanova.entities.DishType;
import tn.esprit.vitanova.services.IDishType;

import java.util.List;

@RestController
@RequestMapping(value="/dish-types", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class DishTypeController {

    @Autowired
    private IDishType dishTypeService;

    @GetMapping
    public ResponseEntity<List<DishType>> getAllDishTypes() {
        List<DishType> dishTypes = dishTypeService.getAllDishTypes();
         return new ResponseEntity<>(dishTypes, HttpStatus.OK);

    }


}
