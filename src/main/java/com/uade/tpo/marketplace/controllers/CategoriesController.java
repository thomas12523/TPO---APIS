package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController // capa de trafico, con esto delimitamos para eso
@RequestMapping("Categories") //ENDPOINT mappea request con lo siguiente del localhost/Categories
public class CategoriesController {

    @Autowired // para que spring sepa que tiene que inyectar la dependencia
    private ICategoriesService categoriesService;


    @GetMapping //localhost/8080/Categories
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(categoriesService.getCategories(PageRequest.of(page, size)).map(CategoryResponse::from));
    }

    @GetMapping("{categoryId}") //localhost/8080/Categories/id
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable int categoryId) { //PATH VARIABLE es porque va a cambiar y que lo vaya cambiando
        Optional<Category> result = categoriesService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(CategoryResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryDuplicateException {
        Category result = categoriesService.createCategory(categoryRequest.getNombre());
        return ResponseEntity.ok(CategoryResponse.from(result));
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> actualizarCategory(@PathVariable int categoryId, @RequestBody CategoryRequest categoryRequest) {
        Category result = categoriesService.actualizarCategory(categoryId, categoryRequest.getNombre());
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(CategoryResponse.from(result));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int categoryId) {
        Optional<Category> result = categoriesService.deleteCategory(categoryId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Categoria desactivada correctamente", CategoryResponse.from(result.get())));
    }

}
