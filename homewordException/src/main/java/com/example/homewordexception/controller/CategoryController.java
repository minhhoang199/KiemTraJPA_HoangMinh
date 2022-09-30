package com.example.homewordexception.controller;

import com.example.homewordexception.dto.CategoryDTO;
import com.example.homewordexception.entity.Category;
import com.example.homewordexception.exception.InvalidParamException;
import com.example.homewordexception.service.CategoryService;
import com.example.homewordexception.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
//    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryService = categoryServiceImp;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll(){
        throw new InvalidParamException("Invalid categories");
//        List<Category> list = this.categoryService.getAll();
//        return ResponseEntity.ok(list);
    }

    @PostMapping("")
    public ResponseEntity<String> create(
            @RequestBody @Valid CategoryDTO categoryDTO){
        this.categoryService.create(categoryDTO);
        return ResponseEntity.ok("Create success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> create(
            @PathVariable Integer id){
        this.categoryService.delete(id);
        return ResponseEntity.ok("Delete success");
    }
}
