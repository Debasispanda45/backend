package com.tastytown.backend.service;

import java.util.List;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;

public interface ICategoryService {
    /**
     * <h3>It creates a Category Object by Category name</h3>
     */
    Category saveCategory(CategoryRequestDTO requestDTO);
    List<Category> findAllCategories();
    Category getCategoryById(String categoryId);
    Category updateCategoryById(String categoryId, CategoryRequestDTO requestDTO);
    void deleteCategoryById(String categoryId);
}
