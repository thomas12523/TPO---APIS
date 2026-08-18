package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.CategoriesRepository;

public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    public CategoriesService() {
        categoriesRepository = new CategoriesRepository();
    }

    public ArrayList<Category> getCategories() {
        return categoriesRepository.getCategories();
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoriesRepository.getCategoryById(categoryId);
    }

    public Category createCategory(int newCategoryId, String nombre) throws CategoryDuplicateException {
        if (categoriesRepository.getCategoryById(newCategoryId).isPresent())
            throw new CategoryDuplicateException();
        Category newCategory = Category.builder()
                .nombre(nombre)
                .id(newCategoryId).build();
        return categoriesRepository.createCategory(newCategory);
    }

    public Category actualizarCategory(int categoryId, Category category) {
        return categoriesRepository.actualizarCategory(categoryId, category);
    }

    public boolean deleteCategory(int categoryId) {
        return categoriesRepository.deleteCategory(categoryId);
    }
}
