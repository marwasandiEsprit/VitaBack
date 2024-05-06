package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.ProductType;
import tn.esprit.vitanova.entities.Products;

import java.util.List;
import java.util.Map;

public interface IProductservice {
     Products ajouterProducts(Products p);
     void updateProducts(Long idProducts , Products p);
     Products getProductsbyId(Long idProducts);
     List<Products> chercherTousProducts();
     void supprimerproducts(Long idProducts);
      Products getproductId(Long idProducts);

     List<Products> getChartData(String metric);
     Map<ProductType, Long> countProductsByTypeProd();

     Map<ProductType, Long> calculateTotalPriceByTypeProd();

//     boolean shouldIncreaseQuantity(Long idProducts);
//     int calculateIncreasePercentage(int thisMonthQuantity, int lastMonthQuantity);

     void sellProduct(Long idProducts, int quantitySold, Long buyerId);
     void checkQuantity();
     void checkAndNotifyQuantity();
}
