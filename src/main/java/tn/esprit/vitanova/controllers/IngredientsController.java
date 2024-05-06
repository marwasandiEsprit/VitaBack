package tn.esprit.vitanova.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.vitanova.entities.Ingredients;
import tn.esprit.vitanova.repository.ProductRepo;
import tn.esprit.vitanova.services.CloudinaryService;
import tn.esprit.vitanova.services.IngredientsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequiredArgsConstructor
public class IngredientsController {

    private final IngredientsService ingredientsService;
    private final ProductRepo productsRepository;
    private final CloudinaryService cloudinaryService;

    // Endpoint pour ajouter un ingrédient
    @PostMapping
    public ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredient) {
        ;
        Ingredients createdIngredient = ingredientsService.addIngredient(ingredient);
        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);

    }

    @GetMapping("/withRecipes")
    public ResponseEntity<List<Ingredients>> getAllIngredientsWithRecipes() {
        List<Ingredients> ingredientsWithRecipes = ingredientsService.getAllIngredientsWithRecipes();
        return new ResponseEntity<>(ingredientsWithRecipes, HttpStatus.OK);
    }

    // Endpoint pour obtenir un ingrédient par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Ingredients> getIngredientById(@PathVariable Long id) {
        Ingredients ingredient = ingredientsService.getIngredientById(id);
        if (ingredient != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour obtenir tous les ingrédients
    @GetMapping
    public ResponseEntity<List<Ingredients>> getAllIngredients() {
        List<Ingredients> ingredients = ingredientsService.getAllIngredients();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un ingrédient
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateIngredient(@PathVariable Long id, @RequestBody Ingredients ingredient) {
        Ingredients existingIngredient = ingredientsService.getIngredientById(id);
        if (existingIngredient != null) {
            ingredient.setIdIngredients(id); // Assurez-vous que l'ID de l'ingrédient est correct
            ingredientsService.updateIngredients(ingredient);
            Map<String, String> response = new HashMap<>();

            response.put("message", "Ingredient mise a jour  avec succès.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();

            response.put("message", "ERROR  NOT UPDATED.!!!");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un ingrédient
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteIngredient(@PathVariable Long id) {
        ingredientsService.deleteIngredients(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Ingredient supprimée avec succès.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String,String> uploadImage(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String,String> result = new HashMap<>();
        result.put("url", cloudinaryService.uploadFile(file));
        return result;
    }
}
