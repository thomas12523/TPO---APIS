package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.CategoriesRepository;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    
    @Autowired
    private CategoriesRepository categoriesRepository;


    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoriesRepository.findById(categoryId);
    }
/*
    public Category createCategory(int newCategoryId, String nombre) throws CategoryDuplicateException {
        List<Category> categories = categoriesRepository.findAll();
        if (categories.stream().anyMatch(category -> category.getId()==newCategoryId && category.getNombre().equals(nombre)))
            throw new CategoryDuplicateException();

        return categoriesRepository.createCategory(newCategoryId,nombre);
    }
 */
    
}
    
    
    
