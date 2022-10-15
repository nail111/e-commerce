package All.service;

import All.dto.cart.CartDto;
import All.dto.cart.CartDtoForDeleteRequest;
import All.exception.cart.CartException;
import All.exception.product.ProductException;
import All.exception.user.UserException;
import All.model.Cart;
import All.repo.CartRepo;
import All.repo.ProductRepo;
import All.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;


    public ResponseEntity<?> addProductToCart(CartDto cartDto, String name) {
        var user = userRepo
                .findByUsername(name)
                .orElseThrow(() -> new UserException("User is not found"));

        var product = productRepo
                .findByProductName(cartDto.getProductName())
                .orElseThrow(() -> new ProductException("Product is not found"));

        List<Cart> cartList = cartRepo.findAllByUser(user);

        for (Cart cart : cartList) {
            if (cart.getProduct().getProductName().equals(cartDto.getProductName())) {
                throw new ProductException("Product is already in your cart");
            }
        }

        if (cartDto.getCount() < 1) {
            throw new CartException("Min count is 1");
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setCount(cartDto.getCount());

        cartRepo.save(cart);

        return ResponseEntity.ok().body("Product has been successfully added to your Cart");
    }

    public ResponseEntity<?> getAllUserProducts(String name) {
        var user = userRepo
                .findByUsername(name)
                .orElseThrow(() -> new UserException("User is not found"));

        List<Cart> cartList = cartRepo.findAllByUser(user);
        List<CartDto> cartDtos = new ArrayList<>();

        double totalPrice = 0;

        for (Cart cart : cartList) {
            totalPrice += cart.getCount() * cart.getProduct().getProductPrice();
        }

        for (Cart cart : cartList) {
            cartDtos.add(new CartDto(cart.getProduct().getProductName(), cart.getCount()));
        }

        Map<List<CartDto>, Double> map = new HashMap<>();
        map.put(List.copyOf(cartDtos), totalPrice);

        return ResponseEntity.ok().body(map);
    }

    public ResponseEntity<?> deleteFromCart(CartDtoForDeleteRequest cartDtoForDeleteRequest, String name) {
        var user = userRepo
                .findByUsername(name)
                .orElseThrow(() -> new UserException("User is not found"));

        var product = productRepo
                .findByProductName(cartDtoForDeleteRequest.getProductName())
                .orElseThrow(() -> new ProductException("Product is not found"));

        List<Cart> cartList = cartRepo.findAllByUser(user);

        for (Cart cart : cartList) {
            if (cart.getProduct().getProductName().equals(cartDtoForDeleteRequest.getProductName())) {
                cartRepo.delete(cart);
                break;
            }
        }

        List<CartDto> cartDtos = new ArrayList<>();
        cartList = cartRepo.findAllByUser(user);

        double totalPrice = 0;

        for (Cart cart : cartList) {
            totalPrice += cart.getCount() * cart.getProduct().getProductPrice();
        }

        for (Cart cart : cartList) {
            cartDtos.add(new CartDto(cart.getProduct().getProductName(), cart.getCount()));
        }

        Map<List<CartDto>, Double> map = new HashMap<>();
        map.put(List.copyOf(cartDtos), totalPrice);

        return ResponseEntity.ok().body(map);
    }
}
