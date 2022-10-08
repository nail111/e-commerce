package All.service;

import All.dto.CategoryDto;
import All.exception.category.CategoryException;
import All.model.Category;
import All.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public ResponseEntity<?> createCategory(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(categoryDto.getCategoryName()))
                .findFirst();

        if (optionalCategory.isPresent()) {
            throw new CategoryException("Category is already exists");
        }

        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryUrl(categoryDto.getCategoryUrl());

        categoryRepo.save(category);
        return ResponseEntity.ok().body("Category created");
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new CategoryException("There is no category");
        }
        return categories;
    }

    public Category getCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepo.findAll().stream()
                .filter(s -> s.getCategoryName().equals(categoryName))
                .findFirst();

        if (optionalCategory.isEmpty()) {
            throw new CategoryException("Category is not found");
        }

        return optionalCategory.get();
    }

    public ResponseEntity<?> updateCategory(CategoryDto categoryDto, String categoryName) {
        Optional<Category> optionalCategory = categoryRepo
                .findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(categoryName))
                .findFirst();

        if (optionalCategory.isEmpty()) {
            throw new CategoryException("Category is not found");
        }

        Optional<Category> categoryFromDb = categoryRepo
                .findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(categoryDto.getCategoryName()))
                .findFirst();

        if (categoryFromDb.isPresent()) {
            throw new CategoryException("Category is already exists");
        }

        Category category = optionalCategory.get();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryUrl(categoryDto.getCategoryUrl());

        categoryRepo.save(category);
        return ResponseEntity.ok().body("Category updated");
    }

    public ResponseEntity<?> deleteCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepo
                .findAll()
                .stream()
                .filter(s -> s.getCategoryName().equals(categoryName))
                .findFirst();

        if (optionalCategory.isEmpty()) {
            throw new CategoryException("Category is not found");
        }

        categoryRepo.delete(optionalCategory.get());
        return ResponseEntity.ok().body("Category " + categoryName + " deleted");
    }
}
