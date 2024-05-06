package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Long> {
}
