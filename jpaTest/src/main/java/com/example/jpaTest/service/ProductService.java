package com.example.jpaTest.service;

import com.example.jpaTest.model.Category;
import com.example.jpaTest.model.Product;
import com.example.jpaTest.model.ProductVM;
import com.example.jpaTest.repository.CategoryRepository;
import com.example.jpaTest.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        List<Product> productList = this.productRepository.findAll();
        return productList;
    }

    private void validateNumber(Long number) {
        if (number == null || number <= 0) {
            throw new RuntimeException("Invalid number: " + number);
        }
    }

    public Product getById(Long id) {
        validateNumber(id);
        var optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Not found Product");
        }
        Product product = optionalProduct.get();
        return product;
    }

    public void createProduct(ProductVM productVM) {
        if (productVM != null) {
            String productName = productVM.getProductName();
            if (productName == null || productName.trim().length() == 0) {
                throw new RuntimeException("Invalid product name");
            }

            Long quantity = productVM.getQuantity();
            validateNumber(quantity);

            Long categoryId = productVM.getCategoryId();
            validateNumber(categoryId);
            var optionalCategory = this.categoryRepository.findById(categoryId);
            if (optionalCategory.isEmpty()) {
                throw new RuntimeException("Not found Category");
            }
            Category currentCategory = optionalCategory.get();

            Product newProduct = new Product();
            newProduct.setProductName(productName);
            newProduct.setQuantity(quantity);
            newProduct.setCategory(currentCategory);

            this.productRepository.save(newProduct);

            currentCategory.getProducts().add(newProduct);
            this.categoryRepository.save(currentCategory);
        }
    }

    public void editProduct(ProductVM productVM, Long id) {
        validateNumber(id);
        var optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Not found Product");
        }
        Product currentProduct = optionalProduct.get();
        Category currentCategory = currentProduct.getCategory();

        if (productVM != null) {
            String productName = productVM.getProductName();
            if (productName != null) {
                if (productName.trim().length() == 0) {
                    throw new RuntimeException("Invalid product name");
                } else {
                    currentProduct.setProductName(productName);
                }
            }

            Long quantity = productVM.getQuantity();
            if (quantity != null) {
                if (quantity <= 0) {
                    throw new RuntimeException("Invalid quantity");
                } else {
                    currentProduct.setQuantity(quantity);
                }
            }

            Long categoryId = productVM.getCategoryId();
            validateNumber(categoryId);
            var optionalCategory = this.categoryRepository.findById(categoryId);
            if (optionalCategory.isEmpty()) {
                throw new RuntimeException("Not found Category");
            }
            Category newCategory = optionalCategory.get();

            currentProduct.setCategory(newCategory);

            this.productRepository.save(currentProduct);

            newCategory.getProducts().add(currentProduct);
            currentCategory.getProducts().remove(currentProduct);
            this.categoryRepository.save(newCategory);
        }
    }

    public void deleteProduct(Long id){
        validateNumber(id);
        var optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Not found Product");
        }
        Product currentProduct = optionalProduct.get();
        this.productRepository.delete(currentProduct);
    }
}
