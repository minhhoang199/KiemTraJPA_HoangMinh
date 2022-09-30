package com.example.homewordexception.service;

import com.example.homewordexception.dto.CategoryDTO;
import com.example.homewordexception.entity.Category;
import com.example.homewordexception.exception.InvalidParamException;
import com.example.homewordexception.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void create(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        if (id <= 0){
            throw new InvalidParamException("Invalid id");
        }
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new EntityNotFoundException("Not found category with id " + id);
        }
        this.categoryRepository.delete(optionalCategory.get());
    }
}
