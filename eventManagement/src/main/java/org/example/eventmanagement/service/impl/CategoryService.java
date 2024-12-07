package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Category;
import org.example.eventmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new NoSuchElementException("Category not found with ID: " + id);
        }
        return category;
    }

    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            throw new NoSuchElementException("Category not found with name: " + name);
        }
        return category;
    }

    public List<Category> searchCategoriesByName(String keyword) {
        return categoryRepository.findByNameContaining(keyword);
    }

    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(Category category) {
        Category existingCategory = getCategoryById(category.getId());
        categoryRepository.update(category);
        return category;
    }

    public void deleteCategoryById(long id) {
        Category existingCategory = getCategoryById(id);
        categoryRepository.deleteById(id);
    }

    public void deleteCategoryByName(String name) {
        Category existingCategory = getCategoryByName(name);
        categoryRepository.deleteByName(name);
    }
}
