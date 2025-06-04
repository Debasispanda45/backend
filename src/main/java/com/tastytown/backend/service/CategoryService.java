package com.tastytown.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.exception.CategoryNotFoundException;
import com.tastytown.backend.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveCategory(CategoryRequestDTO requestDTO) {
        var category = Category.builder()
                .categoryName(requestDTO.getCategoryName())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
    }

    public Category updateCategoryById(String categoryId, CategoryRequestDTO requestDTO) {
        var existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryName(requestDTO.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
    
    public void deleteCategoryById(String categoryId) {
        var category = getCategoryById(categoryId);
        categoryRepository.delete(category);
    }

}
