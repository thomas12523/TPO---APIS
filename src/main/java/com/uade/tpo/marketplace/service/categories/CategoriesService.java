package com.uade.tpo.marketplace.service.categories;

import java.util.List;
import java.util.Optional;


import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;


public interface CategoriesService {
    
    public List<Category> getCategories();
    public Optional<Category> getCategoryById(int categoryId);
    public Category createCategory(String nombre) throws CategoryDuplicateException;
    
}
