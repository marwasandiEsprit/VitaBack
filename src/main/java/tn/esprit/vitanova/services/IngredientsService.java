package tn.esprit.vitanova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Ingredients;
import tn.esprit.vitanova.repository.IngredientsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService implements IngredientsInterface {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Override
    public Ingredients addIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public Ingredients getIngredientById(Long id) {
        return ingredientsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @Override
    public void updateIngredients(Ingredients ingredient) {
        ingredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredients(Long id) {
        Optional<Ingredients> optionalIngredient = ingredientsRepository.findById(id);
        if (optionalIngredient.isPresent()) {
            Ingredients ingredient = optionalIngredient.get();
            // Remove associations from the join table
            ingredient.getRecipes().forEach(recipe -> recipe.getIngredients().remove(ingredient));
            // Clear the associations from the ingredient entity
            ingredient.getRecipes().clear();
            // Delete the ingredient entity
            ingredientsRepository.delete(ingredient);
        } else {
            // Handle the case where the ingredient is not found
            // For example, you can throw an exception or log an error message
        }
    }


    public List<Ingredients> getAllIngredientsWithRecipes() {
        return ingredientsRepository.findAllWithRecipes(); // Assurez-vous que cette méthode existe dans votre repository
    }
}
