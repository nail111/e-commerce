package All.controller;

import All.dto.cart.CartDto;
import All.dto.cart.CartDtoForDeleteRequest;
import All.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDto cartDto, Authentication authentication) {
        return cartService.addProductToCart(cartDto, authentication.getName());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUserProducts(Authentication authentication) {
        return cartService.getAllUserProducts(authentication.getName());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFromCart(@Valid @RequestBody CartDtoForDeleteRequest cartDtoForDeleteRequest, Authentication authentication) {
        return cartService.deleteFromCart(cartDtoForDeleteRequest,authentication.getName());
    }
}
