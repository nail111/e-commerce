package All.service;

import All.dto.product.ProductDto;
import All.exception.category.CategoryException;
import All.exception.product.ProductException;
import All.model.Category;
import All.model.Product;
import All.repo.CategoryRepo;
import All.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ResponseEntity<?> createProduct(ProductDto productDto) {
        Category category = categoryRepo
                .findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(productDto.getCategoryName()))
                .findFirst()
                .orElseThrow(() -> new CategoryException("Category is not found"));

        Optional<Product> optionalProduct = productRepo
                .findAll()
                .stream()
                .filter(s -> s.getProductName().equals(productDto.getProductName()))
                .findFirst();

        if (optionalProduct.isPresent()) {
            throw new ProductException("Product is already exists");
        }

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductUrl(productDto.getProductUrl());
        product.setProductPrice(productDto.getProductPrice());
        product.setCategory(category);

        productRepo.save(product);
        return ResponseEntity.ok().body("Product created");
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productRepo.findAll();

        for (Product product : products) {
            productDtos.add(
                    ProductDto
                            .builder()
                            .productName(product.getProductName())
                            .productDescription(product.getProductDescription())
                            .productUrl(product.getProductUrl())
                            .productPrice(product.getProductPrice())
                            .categoryName(product.getCategory().getCategoryName())
                            .build()
            );
        }

        return productDtos;
    }

    public ProductDto getProduct(String productName) {

        Product product = productRepo.findAll()
                .stream()
                .filter(s -> s.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new ProductException("Product is not found"));

        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductUrl(product.getProductUrl());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setCategoryName(product.getCategory().getCategoryName());

        return productDto;
    }

    public ResponseEntity<?> updateProduct(String productName, ProductDto productDto) {
        Product product = productRepo
                .findAll()
                .stream()
                .filter(s -> s.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new ProductException("Product is not found"));

        Category category = categoryRepo
                .findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(productDto.getCategoryName()))
                .findFirst()
                .orElseThrow(() -> new CategoryException("Category is not found"));

        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductUrl(productDto.getProductUrl());
        product.setProductPrice(productDto.getProductPrice());
        product.setCategory(category);
        productRepo.save(product);
        return ResponseEntity.ok().body("Product updated");
    }

    public ResponseEntity<?> deleteProduct(String productName) {
        Product product = productRepo
                .findAll()
                .stream()
                .filter(s -> s.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new ProductException("Product is not found"));

        productRepo.delete(product);
        return ResponseEntity.ok().body("Product deleted");
    }
}
