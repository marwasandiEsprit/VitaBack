package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Recepies;

import java.util.Date;
import java.util.List;

@Repository
public interface RecepiesRepository  extends JpaRepository<Recepies,Long> {

    @Query("SELECT DISTINCT r FROM Recepies r JOIN r.ingredients i WHERE i.idIngredients = :ingredientId")
    List<Recepies> findAllRecipesByIngredientId(Long ingredientId);
    @Query("SELECT i.Products, COUNT(r) FROM Recepies r JOIN r.ingredients i GROUP BY i.Products ORDER BY COUNT(r) DESC")
    List<Object[]> findMostPopularIngredients();
    @Query("SELECT COUNT(r) FROM Recepies r WHERE r.dateAdded BETWEEN :startDate AND :endDate")
    long countRecipesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT dt.name, COUNT(r) FROM Recepies r JOIN r.dishType dt GROUP BY dt.name")
    List<Object[]> countRecepiesByDishType();

    // Statistiques pour le graphique en secteurs de la durée de préparation
    @Query("SELECT r.duration, COUNT(r) FROM Recepies r GROUP BY r.duration ORDER BY r.duration")
    List<Object[]> countRecepiesByDuration();
}
