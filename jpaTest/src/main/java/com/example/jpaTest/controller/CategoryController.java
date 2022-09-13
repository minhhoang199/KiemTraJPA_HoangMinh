package com.example.jpaTest.controller;

import com.example.jpaTest.model.Category;
import com.example.jpaTest.model.Product;
import com.example.jpaTest.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllProducts() {
        var ret = this.categoryService.getAll();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Category> getById(
            @PathVariable("productId") Long productId
    ) {
        var ret = this.categoryService.getById(productId);
        return ResponseEntity.ok(ret);
    }

    @PostMapping()
    public ResponseEntity<String> createNew(
            @RequestBody Category newCategory
    ) {
        this.categoryService.createCategory(newCategory);
        return ResponseEntity.ok("Create product succeed");
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> editProduct(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody Category newCategory
    ) {
        this.categoryService.editCategory(newCategory, categoryId);
        return ResponseEntity.ok("Edit category succeed");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> createNew(
            @PathVariable("categoryId") Long categoryId
    ) {
        this.categoryService.deleteProduct(categoryId);
        return ResponseEntity.ok("Delete category succeed");
    }
}
