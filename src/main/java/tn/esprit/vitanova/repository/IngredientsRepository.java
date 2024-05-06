package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Ingredients;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
    @Query("SELECT DISTINCT i FROM Ingredients i LEFT JOIN FETCH i.recipes")
    List<Ingredients> findAllWithRecipes();
}
