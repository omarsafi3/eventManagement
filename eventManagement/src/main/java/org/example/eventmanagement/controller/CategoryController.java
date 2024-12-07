package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Category;
import org.example.eventmanagement.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get category by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        try {
            Category category = categoryService.getCategoryByName(name);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search categories by keyword in the name
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategoriesByName(@RequestParam String keyword) {
        List<Category> categories = categoryService.searchCategoriesByName(keyword);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        category.setId(id);
        try {
            Category updatedCategory = categoryService.updateCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable long id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete category by Name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteCategoryByName(@PathVariable String name) {
        try {
            categoryService.deleteCategoryByName(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}