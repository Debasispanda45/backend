package com.tastytown.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "Operations related to categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @Operation(summary = "Create a new category", description = "This endpoint allows you to create a new category in the system.")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDto) {

        return new ResponseEntity<>(categoryService.saveCategory(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Fetched all categories successfully")
    @Operation(summary = "Get all categories", description = "This endpoint returns a list of all categories in the system.")
    // public ResponseEntity<List<Category>> getAllCategories() {
    // List<Category> categories = categoryService.getAllCategories();
    // // return ResponseEntity.ok(categories);
    // return new ResponseEntity<>(categories, HttpStatus.OK);
    // }
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{categoryId}")
    @ApiResponse(description = "Get category by ID")
    @Operation(summary = "Get all categories", description = "This endpoint returns a list of all categories in the system.")
    public Category getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

}
