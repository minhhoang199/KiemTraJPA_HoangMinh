package com.example.jpaTest.repository;

import com.example.jpaTest.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
