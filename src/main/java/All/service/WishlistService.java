package All.service;

import All.dto.WishlistDto;
import All.dto.wishlist.WishlistDtoForDeleteRequest;
import All.dto.wishlist.WishlistDtoForGetRequest;
import All.exception.product.ProductException;
import All.exception.user.UserException;
import All.model.Product;
import All.model.Wishlist;
import All.repo.ProductRepo;
import All.repo.UserRepo;
import All.repo.WishlistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepo wishlistRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;


    public ResponseEntity<?> addToWishlist(WishlistDto wishlistDto, String name) {
        var user = userRepo
                .findByUsername(name)
                .orElseThrow(() -> new UserException("User is not found"));

        Optional<Product> optionalProduct = productRepo.findByProductName(wishlistDto.getProductName());
        Optional<Wishlist> optionalWishlist = wishlistRepo
                .findAll()
                .stream()
                .filter(s -> s.getProduct().getProductName().equals(wishlistDto.getProductName()))
                .findFirst();

        if (optionalProduct.isEmpty()) {
            throw new ProductException("Product is not found");
        }

        if (optionalWishlist.isPresent()) {
            throw new ProductException("Product is already on your wishlist");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(optionalProduct.get());
        wishlist.setUser(user);
        wishlistRepo.save(wishlist);

        return ResponseEntity.ok().body("Product has been successfully added to your wishlist");
    }

    public ResponseEntity<?> getWishlist(String username) {
        var userFromDb = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UserException("User is not found"));

        List<Wishlist> wishlists = wishlistRepo.findAllByUser(userFromDb);
        List<WishlistDtoForGetRequest> wishlistDtoForGetRequests = new ArrayList<>();

        for (Wishlist wishlist : wishlists) {
            wishlistDtoForGetRequests.add(
                    new WishlistDtoForGetRequest(
                            wishlist.getProduct().getProductName(),
                            wishlist.getProduct().getProductDescription(),
                            wishlist.getProduct().getProductPrice(),
                            wishlist.getProduct().getProductUrl(),
                            wishlist.getProduct().getCategory().getCategoryName()
                    )
            );
        }

        return ResponseEntity.ok().body(wishlistDtoForGetRequests);
    }

    public ResponseEntity<?> deleteWishlist(String username, WishlistDtoForDeleteRequest wishlistDtoForDeleteRequest) {
        var userFromDb = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UserException("User is not found"));

        var product = productRepo
                .findByProductName(wishlistDtoForDeleteRequest.getProductName())
                .orElseThrow(() -> new ProductException("Product is not found"));

        var wishlist = wishlistRepo.findByProduct(product);

        if (Objects.isNull(wishlist)) {
            throw new ProductException("Product is not found");
        }

        wishlistRepo.delete(wishlist);

        return ResponseEntity.ok().body("Product has been successfully deleted from your wishlist");
    }
}
