package com.uade.tpo.marketplace.service.categories;

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

    public Category createCategory(String nombre) throws CategoryDuplicateException {
        List<Category> categories = categoriesRepository.findByNombre(nombre);
        if (categories.isEmpty())
            return categoriesRepository.save(new Category(nombre));

        throw new CategoryDuplicateException();
    }

    public Category actualizarCategory(int categoryId, String nombre) {
        Optional<Category> existente = categoriesRepository.findById(categoryId);
        if (existente.isEmpty())
            return null;

        Category category = existente.get();
        category.setNombre(nombre);
        return categoriesRepository.save(category);
    }

    public boolean deleteCategory(int categoryId) {
        if (categoriesRepository.findById(categoryId).isEmpty())
            return false;

        categoriesRepository.deleteById(categoryId);
        return true;
    }

}
    
    
    
