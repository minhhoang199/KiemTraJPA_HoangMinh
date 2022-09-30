package com.example.homewordexception.service;

import com.example.homewordexception.dto.CategoryDTO;
import com.example.homewordexception.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    void create(CategoryDTO categoryDTO);
    void delete(Integer id);
}
