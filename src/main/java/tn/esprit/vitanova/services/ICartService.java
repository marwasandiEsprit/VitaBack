package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Cart;
import tn.esprit.vitanova.entities.Products;

import java.util.List;

public interface ICartService {
    Cart ajouterCart(Long ownerId,Cart c);
    void updateCart(Long idCart , Cart c);
    Cart getCartbyId(Long idCart);
    List<Cart> chercherTousCart();
    void supprimerprodCart(Long idCart);
    Cart getCartId(Long idCart);
    List<Cart> getCartByOwnerId(Long ownerId);
    double getTotalCombinedPriceByOwnerId(Long ownerId);
}
