package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Cart;
import tn.esprit.vitanova.repository.CartRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CartService implements ICartService{
    CartRepo cartRepo;
    UserRepo userRepo;

   

    @Override
    public Cart ajouterCart(Long ownerId, Cart c) {
        c.setOwnerId(ownerId);
        return cartRepo.save(c);
    }

    @Override
    public void updateCart(Long idCart, Cart c) {
        c.setIdCart(idCart);
        cartRepo.save(c);
    }

    @Override
    public Cart getCartbyId(Long idCart) {
        return cartRepo.findById(idCart).get();
    }

    @Override
    public List<Cart> chercherTousCart() {
        return cartRepo.findAll();
    }

    @Override
    public void supprimerprodCart(Long idCart) {
        cartRepo.deleteById(idCart);
    }

    @Override
    public Cart getCartId(Long idCart) {
        return cartRepo.findById(idCart).orElse(null);
    }

    @Override
    public List<Cart> getCartByOwnerId(Long ownerId) {
        return cartRepo.findByOwnerId(ownerId);
    }

    @Override
    public double getTotalCombinedPriceByOwnerId(Long ownerId) {
        List<Cart> carts = cartRepo.findByOwnerId(ownerId);
        double total = carts.stream().mapToDouble(Cart::getTotal).sum();
        return total;
    }
}
