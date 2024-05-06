package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.DishType;

public interface DishTypeRepository extends JpaRepository<DishType, Long> {
}
