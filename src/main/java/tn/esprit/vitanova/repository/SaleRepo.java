package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.vitanova.entities.Sale;

public interface SaleRepo extends JpaRepository<Sale, Long> {
    @Query("SELECT COALESCE(SUM(s.quantitySold), 0) FROM Sale s WHERE s.product.idProducts = :productId")
    int getTotalAmountSoldByProduct(@Param("productId") Long productId);
}
