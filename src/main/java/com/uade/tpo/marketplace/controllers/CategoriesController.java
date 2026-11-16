package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.entity.dto.request.CategoryRequest;
import com.uade.tpo.marketplace.entity.dto.response.CategoryResponse;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.service.categories.ICategoriesService;

@RestController
@RequestMapping("Categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(required = false, defaultValue = "true") boolean soloActivos,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<Category> resultado = soloActivos
                ? categoriesService.getCategories(PageRequest.of(page, size))
                : categoriesService.getTodasCategories(PageRequest.of(page, size));
        return ResponseEntity.ok(resultado.map(CategoryResponse::new));
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable int categoryId) {
        Optional<Category> result = categoriesService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(new CategoryResponse(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryDuplicateException {
        Category result = categoriesService.createCategory(categoryRequest.getNombre());
        return ResponseEntity.ok(new CategoryResponse(result));
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> actualizarCategory(@PathVariable int categoryId, @RequestBody CategoryRequest categoryRequest) {
        Category result = categoriesService.actualizarCategory(categoryId, categoryRequest.getNombre());
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new CategoryResponse(result));
    }

    @PatchMapping("{categoryId}/estado")
    public ResponseEntity<CategoryResponse> actualizarEstado(@PathVariable int categoryId, @RequestParam boolean activo) {
        Optional<Category> result = categoriesService.actualizarEstado(categoryId, activo);
        if (result.isPresent())
            return ResponseEntity.ok(new CategoryResponse(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int categoryId) {
        Optional<Category> result = categoriesService.deleteCategory(categoryId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Categoria desactivada correctamente", new CategoryResponse(result.get())));
    }

}
