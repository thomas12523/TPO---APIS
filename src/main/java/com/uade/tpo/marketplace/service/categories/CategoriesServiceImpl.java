package com.uade.tpo.marketplace.service.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.ICategoriesRepository;

@Service
@Transactional
public class CategoriesServiceImpl implements ICategoriesService {

    @Autowired
    private ICategoriesRepository categoriesRepository;

    @Transactional(readOnly = true)
    public Page<Category> getCategories(PageRequest pageRequest) {
        return categoriesRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(int categoryId) {
        return categoriesRepository.findById(categoryId);
    }

    public Category createCategory(String nombre) throws CategoryDuplicateException {
        List<Category> categories = categoriesRepository.findByNombre(nombre);
        if (categories.isEmpty()) {
            Category category = new Category();
            category.setNombre(nombre);
            return categoriesRepository.save(category);
        }

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
