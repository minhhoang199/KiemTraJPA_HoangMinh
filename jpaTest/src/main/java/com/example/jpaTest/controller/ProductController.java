package com.example.jpaTest.controller;

import com.example.jpaTest.model.Product;
import com.example.jpaTest.model.ProductVM;
import com.example.jpaTest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        var ret = this.productService.getAll();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(
            @PathVariable("productId") Long productId
    ) {
        var ret = this.productService.getById(productId);
        return ResponseEntity.ok(ret);
    }

    @PostMapping()
    public ResponseEntity<String> createNew(
            @RequestBody ProductVM productVM
    ) {
        this.productService.createProduct(productVM);
        return ResponseEntity.ok("Create product succeed");
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<String> editProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductVM productVM
    ) {
        this.productService.editProduct(productVM, productId);
        return ResponseEntity.ok("Edit product succeed");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> createNew(
            @PathVariable("productId") Long productId
    ) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.ok("Delete product succeed");
    }
}
