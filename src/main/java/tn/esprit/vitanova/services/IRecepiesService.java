package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Recepies;
import tn.esprit.vitanova.entities.RecipeDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRecepiesService {
    Recepies addRecipe(RecipeDto recipe);
    Recepies getRecipeById(Long id);
    List<Recepies> getAllRecipes();
    void updateRecipe(Recepies recipe);
    void deleteRecipe(Long id);
    List<Recepies> getAllRecipesWithIngredients(Long ingredientId);
    double calculateAveragePreparationTime();
    List<Object[]> findMostPopularIngredients();
    long getRecipeCountsForInterval(Date startDate, Date endDate);
    Map<String, Long> getMonthlyOrWeeklyRecipeCounts(Date start, Date end, String type);
    List<Object[]> getRecepiesStatsByDishType();
    List<Object[]> getRecepiesStatsByDuration();
}
