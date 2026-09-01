package com.uade.tpo.marketplace.service.categories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;


public interface ICategoriesService {

    public Page<Category> getCategories(PageRequest pageRequest);
    public Optional<Category> getCategoryById(int categoryId);
    public Category createCategory(String nombre) throws CategoryDuplicateException;
    public Category actualizarCategory(int categoryId, String nombre);
    public Optional<Category> deleteCategory(int categoryId);

}
