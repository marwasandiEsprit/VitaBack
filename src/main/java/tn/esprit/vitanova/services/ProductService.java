package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ProductType;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.entities.Sale;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.ProductRepo;
import tn.esprit.vitanova.repository.SaleRepo;
import tn.esprit.vitanova.repository.UserRepo;
import tn.esprit.vitanova.security.services.UserDetailsImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductservice{
    ProductRepo productRepo;
    UserRepo userRepo;
    public static final int THRESHOLD_QUANTITY = 10;
    private static final double  THRESHOLD_PERCENTAGE =  0.7; // 20% increase threshold
    private SaleRepo saleRepo;

    @Override
    public Products ajouterProducts(Products p) {
        return productRepo.save(p);
    }

    @Override
    public void updateProducts(Long idProducts, Products p) {
        p.setIdProducts(idProducts);
        productRepo.save(p);

    }

    @Override
    public Products getProductsbyId(Long idProducts) {
        return productRepo.findById(idProducts).get();
    }

    @Override
    public List<Products> chercherTousProducts() {
        return productRepo.findAll();
    }

    @Override
    public void supprimerproducts(Long idProducts) {
    productRepo.deleteById(idProducts);
    }

    @Override
    public Products getproductId(Long idProducts) {
        return productRepo.findById(idProducts).orElse(null);
    }

    @Override
    public List<Products> getChartData(String metric) {
        return null;
    }

    @Override
    public Map<ProductType, Long> countProductsByTypeProd() {
        Map<ProductType, Long> countMap = new HashMap<>();
        for (ProductType type : ProductType.values()) {
            countMap.put(type, productRepo.countByTypeProd(type));
        }
        return countMap;
    }

    @Override
    public Map<ProductType, Long> calculateTotalPriceByTypeProd() {
        Map<ProductType, Long> totalPriceMap = new HashMap<>();
        for (ProductType type : ProductType.values()) {
            Long totalPrice = productRepo.sumPriceByTypeProd(type);
            totalPriceMap.put(type, totalPrice != null ? totalPrice : 0);
        }
        return totalPriceMap;
    }

    @Override
    public void sellProduct(Long idProducts, int quantitySold, Long buyerId) {
        Products product = productRepo.findById(idProducts).orElse(null);
        User buyer = userRepo.findById(buyerId).orElse(null);
        if (product != null && product.getQuantityP() >= quantitySold) {
            // Create Sale entity
            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setBuyer(buyer);
            sale.setQuantitySold(quantitySold);
            sale.setSaleDate(new Date()); // Set current time as sale date
            // Decrease quantity sold from total quantity
            product.setQuantityP(product.getQuantityP() - quantitySold);
            productRepo.save(product);

            // Save sale entity
            saleRepo.save(sale);
        }  else {
            throw new IllegalArgumentException("Invalid product ID or insufficient quantity.");
        }
    }
    @Scheduled(fixedRate = 3000)
    @Override
    public void checkQuantity() {
        List<Products> products = productRepo.findAll();
        for (Products product : products) {
            if (product.getQuantityP() < THRESHOLD_QUANTITY) {
                product.setQuantityLow(true);
                // You can also trigger notifications here
            }else {
                product.setQuantityLow(false);

            }
        }
        productRepo.saveAll(products);
    }

    @Override
    @Scheduled(fixedRate = 3000)
    public void checkAndNotifyQuantity() {
        List<Products> products = productRepo.findAll();
        for (Products product : products) {
            // Calculate the total amount sold for this product
            int totalAmountSold = saleRepo.getTotalAmountSoldByProduct(product.getIdProducts());

            // Calculate 70% of the quantityP
            double seventyPercentOfQuantity = product.getQuantityP() * THRESHOLD_PERCENTAGE;

            // Check if the total amount sold exceeds 70% of the quantityP
            boolean quantityExceeded = totalAmountSold > seventyPercentOfQuantity;

            // Set the notification attribute accordingly
            product.setQuantityExceeded(quantityExceeded);
            if (!quantityExceeded) {
                product.setQuantityExceeded(false);
            }
            // If quantity exceeded, you can also trigger notifications here
        }
        // Save the products with updated notification attribute
        productRepo.saveAll(products);
    }


}
