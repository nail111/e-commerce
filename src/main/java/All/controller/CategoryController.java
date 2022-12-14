package All.controller;

import All.dto.CategoryDto;
import All.model.Category;
import All.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping("/list")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}")
    public Category getCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.getCategory(categoryName);
    }

    @PutMapping("/update/{categoryName}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryName") String categoryName) {
        return categoryService.updateCategory(categoryDto, categoryName);
    }

    @DeleteMapping("/delete/{categoryName}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.deleteCategory(categoryName);
    }
}
