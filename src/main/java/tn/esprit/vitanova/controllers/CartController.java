package tn.esprit.vitanova.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Cart;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.services.ICartService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    ICartService iCartService;

    @PostMapping("/addtocart/{ownerId}")
    public Cart ajouterCart(@PathVariable Long ownerId,@RequestBody Cart c) {
        return iCartService.ajouterCart(ownerId,c) ;
    }

    @PutMapping("/updatecart/{idProducts}")
    public void updateCart(@PathVariable Long idProducts,@RequestBody Cart c){
        iCartService.updateCart(idProducts,c);
    }
    @GetMapping("/getcartbyid")
    public Cart getCartbyId(Long idCart){
        return iCartService.getCartbyId(idCart);
    }
    @GetMapping("/getallcart")
    public List<Cart> getAllCart(){
        return iCartService.chercherTousCart();
    }
    @DeleteMapping("/deletecart/{idCart}")
    public  void  supprimerprodCart(@PathVariable Long idCart) {
        iCartService.supprimerprodCart(idCart);
    }

    @GetMapping("/getCartId/{idCart}")
    public Cart getCartId(@PathVariable Long idCart){
        return iCartService.getCartId(idCart);
    }

    @GetMapping("/getCartByOwnerId/{ownerId}")
    public List<Cart> getCartByOwnerId(@PathVariable Long ownerId) {
        return iCartService.getCartByOwnerId(ownerId);
    }

    @PostMapping("/pay/{ownerId}") // Make sure ownerId is mapped as a path variable
    public String pay(@PathVariable Long ownerId) throws StripeException {
        double totalPrice = iCartService.getTotalCombinedPriceByOwnerId(ownerId);
        // Set your secret key
        Stripe.apiKey = "sk_test_51P8W56E5EeInQmuIqOVJYjVN1KuUGct3EZPHQtNr8JDpJ5oXYbmEtFnzpg9oLeKEhQSgLz7OVLmcPQyxjRrhwQww00ZlsHfjLX";

        // Create a PaymentIntent
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount((long) (totalPrice * 100)) // amount should be in cents
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);

        // Return client secret to frontend
        return intent.getClientSecret();
    }
}
