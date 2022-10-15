package All.repo;

import All.model.Cart;
import All.model.Product;
import All.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
    List<Cart> findAllByUser(User user);
    Cart findByProduct(Product product);
}
