package com.example.jpaTest.service;

import com.example.jpaTest.model.Category;
import com.example.jpaTest.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        List<Category> categoryList = this.categoryRepository.findAll();
        return categoryList;
    }

    private void validateNumber(Long number) {
        if (number == null || number <= 0) {
            throw new RuntimeException("Invalid number: " + number);
        }
    }

    public Category getById(Long id) {
        validateNumber(id);
        var optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Not found Category");
        }
        Category category = optionalCategory.get();
        return category;
    }

    public void createCategory(Category newCategory) {
        if (newCategory != null) {
            String categoryName = newCategory.getCategoryName();
            if (categoryName == null || categoryName.trim().length() == 0) {
                throw new RuntimeException("Invalid category name");
            }

            this.categoryRepository.save(newCategory);
        }
    }

    public void editCategory(Category newCategory, Long id) {
        validateNumber(id);
        var optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Not found Category");
        }
        Category currentCategory = optionalCategory.get();

        if (newCategory != null) {
            String categoryName = newCategory.getCategoryName();
            if (categoryName != null) {
                if (categoryName.trim().length() == 0) {
                    throw new RuntimeException("Invalid category name");
                } else {
                    currentCategory.setCategoryName(categoryName);
                }
            }

            this.categoryRepository.save(newCategory);
        }
    }

    public void deleteProduct(Long id){
        validateNumber(id);
        var optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Not found Category");
        }
        Category currentCategory = optionalCategory.get();
        this.categoryRepository.delete(currentCategory);
    }
}
