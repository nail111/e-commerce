package All.repo;

import All.model.Product;
import All.model.User;
import All.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, UUID> {
    Wishlist findByProduct(Product product);

    List<Wishlist> findAllByUser(User user);
}
