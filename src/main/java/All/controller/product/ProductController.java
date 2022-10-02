package All.controller.product;

import All.dto.product.ProductDto;
import All.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping("/list")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productName}")
    public ProductDto getProduct(@PathVariable("productName") String productName) {
       return productService.getProduct(productName);
    }

    @PutMapping("/update/{productName}")
    public ResponseEntity<?> updateProduct(@PathVariable("productName") String productName, @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(productName,productDto);
    }

    @DeleteMapping("/delete/{productName}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productName") String productName) {
        return productService.deleteProduct(productName);
    }
}
