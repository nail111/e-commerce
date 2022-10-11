package All.repo;

import All.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    Optional<Product> findByProductName(String productName);
}
