package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Cart;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    List<Cart> findByOwnerId(Long ownerId);
}
