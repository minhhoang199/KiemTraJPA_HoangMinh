package com.example.homewordexception.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {
    @NotBlank
    @Length(min = 5)
    private String categoryName;
}
