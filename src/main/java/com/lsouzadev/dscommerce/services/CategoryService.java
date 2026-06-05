package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.CategoryDto;
import com.lsouzadev.dscommerce.entities.Category;
import com.lsouzadev.dscommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(CategoryDto::new).toList();
    }


}
