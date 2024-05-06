package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.ProductType;
import tn.esprit.vitanova.entities.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    @Query("SELECT SUM(p.quantityP) FROM Products p WHERE p.typeProd = :typeProd")
    Long countByTypeProd(@Param("typeProd") ProductType typeProd);

    @Query("SELECT SUM(p.price) FROM Products p WHERE p.typeProd = :typeProd")
    Long sumPriceByTypeProd(@Param("typeProd") ProductType typeProd);
//    @Query("SELECT COALESCE(SUM(s.quantitySold), 0) FROM Sale s WHERE MONTH(s.saleDate) = MONTH(CURRENT_DATE()) AND YEAR(s.saleDate) = YEAR(CURRENT_DATE()) AND s.product.idProducts = :productId")
//    int findQuantitySoldThisMonth(@Param("productId") Long productId);
//
//    @Query("SELECT COALESCE(SUM(s.quantitySold), 0) FROM Sale s WHERE MONTH(s.saleDate) = MONTH(CURRENT_DATE()) - 1 AND YEAR(s.saleDate) = YEAR(CURRENT_DATE()) AND s.product.idProducts = :productId")
//    int findQuantitySoldLastMonth(@Param("productId") Long productId);


}
