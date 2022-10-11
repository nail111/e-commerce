package All.controller;

import All.dto.WishlistDto;
import All.dto.wishlist.WishlistDtoForDeleteRequest;
import All.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<?> addToWishlist(@Valid @RequestBody WishlistDto wishlistDto, Authentication user) {
        return wishlistService.addToWishlist(wishlistDto,user.getName());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWishlist(Authentication user) {
        return wishlistService.getWishlist(user.getName());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWishlist(Authentication user, @Valid @RequestBody WishlistDtoForDeleteRequest wishlistDtoForDeleteRequest) {
        return wishlistService.deleteWishlist(user.getName(),wishlistDtoForDeleteRequest);
    }
}
