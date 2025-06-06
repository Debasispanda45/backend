package com.tastytown.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.ICategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "Operations related to categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @Operation(summary = "Create a new category", description = "This endpoint allows you to create a new category in the system.")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDto) {

        return new ResponseEntity<>(categoryService.saveCategory(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Fetched all categories successfully")
    @Operation(summary = "Get all categories", description = "This endpoint returns a list of all categories in the system.")
    public ResponseEntity<List<Category>> getAllCategories() {
        // List<Category> categories = categoryService.findAllCategories();
        // return ResponseEntity.ok(categories);
        // Alternatively, you can directly return the response entity with the list of
        // categories
        // return new ResponseEntity<>(categories, HttpStatus.OK);

        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{categoryId}")
    @ApiResponse(responseCode = "200", description = "Category fetched successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @Operation(summary = "Get category by ID", description = "This endpoint returns a category by its ID.")
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/{categoryId}")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @Operation(summary = "Update a category", description = "This endpoint allows you to update an existing category by its ID.")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId,
            @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryId, requestDTO));
    }

    @DeleteMapping("/{categoryId}")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    // @ApiResponse(responseCode = "404", description = "Category not found")
    @Operation(summary = "Delete category by ID", description = "This endpoint deletes a category by its ID.")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable String categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

}
