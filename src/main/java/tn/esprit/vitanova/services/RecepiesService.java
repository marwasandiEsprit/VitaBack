package tn.esprit.vitanova.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.IngredientDto;
import tn.esprit.vitanova.entities.Ingredients;
import tn.esprit.vitanova.entities.Recepies;
import tn.esprit.vitanova.entities.RecipeDto;
import tn.esprit.vitanova.repository.IngredientsRepository;
import tn.esprit.vitanova.repository.RecepiesRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecepiesService  implements IRecepiesService  {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RecepiesRepository  recipesRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    public RecepiesService(RecepiesRepository recipesRepository, IngredientsRepository ingredientsRepository) {
        this.recipesRepository = recipesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    @Transactional
    public Recepies addRecipe(RecipeDto recipeDto) {
        Recepies recipe = new Recepies();
        recipe.setDescription(recipeDto.getDescription());
        recipe.setName(recipeDto.getName());
        recipe.setDateAdded(recipeDto.getDateAdded());
        recipe.setDuration(recipeDto.getDuration());
        recipe.setDishType(recipeDto.getDishType());


        recipe.setImages(recipeDto.getImages());

        Set<Ingredients> ingredients = new HashSet<>();
        for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
            Ingredients ingredient;
            if (ingredientDto.getIdIngredients() != null) {
                ingredient = ingredientsRepository.findById(ingredientDto.getIdIngredients())
                        .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
            } else {
                ingredient = new Ingredients();
                ingredient.setDescription(ingredientDto.getDescription());
                ingredient.setProducts(ingredientDto.getProducts());
                ingredient.setImages(ingredientDto.getImages());
                ingredient = ingredientsRepository.save(ingredient);
            }
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);

        return recipesRepository.save(recipe);
    }


    // Method to retrieve all recipes with their ingredients for a given ingredient ID
    public List<Recepies> getAllRecipesWithIngredients(Long ingredientId) {
        return recipesRepository.findAllRecipesByIngredientId(ingredientId);
    }

    @Override
    public double calculateAveragePreparationTime() {
        return 0;
    }

    @Override
    public  Recepies getRecipeById(Long id) {
        return recipesRepository.findById(id).orElse(null);
    }

    @Override
    public List< Recepies> getAllRecipes() {
        return recipesRepository.findAll();
    }

    @Override
    public void updateRecipe( Recepies recipe) {
        recipesRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        Optional<Recepies> optionalRecipe = recipesRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recepies recipe = optionalRecipe.get();
            // Supprimer les associations avec les ingrédients
            recipe.getIngredients().clear(); // ou recipe.getIngredients().removeAll(recipe.getIngredients());
            // Supprimer la recette
            recipesRepository.delete(recipe);
        } else {
            // Gérer le cas où la recette n'est pas trouvée
            // Par exemple, vous pouvez lever une exception ou enregistrer un message d'erreur
        }
    }
    public List<Object[]> getRecepiesStatsByDishType() {
        return recipesRepository.countRecepiesByDishType();
    }

    public List<Object[]> getRecepiesStatsByDuration() {
        return recipesRepository.countRecepiesByDuration();
    }
    public List<Object[]> findMostPopularIngredients() {
        return recipesRepository.findMostPopularIngredients();
    }

    public long getRecipeCountsForInterval(Date startDate, Date endDate) {
        return recipesRepository.countRecipesByDateRange(startDate, endDate);
    }
    public Map<String, Long> getMonthlyOrWeeklyRecipeCounts(Date start, Date end, String type) {
        Map<String, Long> counts = new LinkedHashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);

        while (cal.getTime().before(end) || cal.getTime().equals(end)) {
            Date startDate = cal.getTime();
            cal.add(type.equals("monthly") ? Calendar.MONTH : Calendar.WEEK_OF_YEAR, 1);
            cal.add(Calendar.DATE, -1);
            Date endDate = cal.getTime();
            long count = getRecipeCountsForInterval(startDate, endDate);
            String label = new SimpleDateFormat("MMM dd").format(startDate) + " - " + new SimpleDateFormat("MMM dd").format(endDate);
            counts.put(label, count);
            cal.add(Calendar.DATE, 1);
        }

        return counts;
    }
}
