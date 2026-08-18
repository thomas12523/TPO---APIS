package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Category;

public class CategoriesRepository {
    private ArrayList<Category> categories;

    public CategoriesRepository() {
        categories = new ArrayList<Category>(
                Arrays.asList(Category.builder().nombre("caballos").id(1).build(),
                        Category.builder().nombre("peluches").id(2).build(),
                        Category.builder().nombre("cafe").id(3).build()));
    }

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return this.categories.stream().filter(c -> c.getId() == categoryId).findAny();
    }

    public Category createCategory(Category category) {
        this.categories.add(category);
        return category;
    }

    public Category actualizarCategory(int categoryId, Category categoryActualizada) {
        Optional<Category> category = getCategoryById(categoryId);
        if (category.isEmpty()) {
            return null;
        }
        this.categories.remove(category.get());
        this.categories.add(categoryActualizada);
        return categoryActualizada;
    }

    public boolean deleteCategory(int categoryId) {
        Optional<Category> category = getCategoryById(categoryId);
        if (category.isEmpty()) {
            return false;
        }
        return this.categories.remove(category.get());
    }
}
