package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.entity.dto.CategoryRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.service.CategoriesService;

@RestController // capa de trafico, con esto delimitamos para eso
@RequestMapping("Categories") //ENDPOINT mappea request con lo siguiente del localhost/Categories
public class CategoriesController {
    private CategoriesService categoriesService;

    public CategoriesController() {
        categoriesService = new CategoriesService();
    }

    @GetMapping //localhost/8080/Categories
    public ResponseEntity<ArrayList<Category>> getCategories() {
        return ResponseEntity.ok(categoriesService.getCategories());
    }

    @GetMapping("{categoryId}") //localhost/8080/Categories/id
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) { //PATH VARIABLE es porque va a cambiar y que lo vaya cambiando
        Optional<Category> result = categoriesService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryDuplicateException {
        Category result = categoriesService.createCategory(categoryRequest.getId(), categoryRequest.getNombre());
        return ResponseEntity.created(URI.create("/Categories/" + result.getId())).body(result);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<Category> actualizarCategory(@PathVariable int categoryId, @RequestBody Category entity) {
        Category result = categoriesService.actualizarCategory(categoryId, entity);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) {
        boolean deleted = categoriesService.deleteCategory(categoryId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
