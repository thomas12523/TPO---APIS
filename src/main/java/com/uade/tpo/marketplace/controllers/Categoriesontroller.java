package com.uade.tpo.marketplace.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.service.CategoriesService;

import entity.Category;


@RestController // capa de trafico, con esto delimitamos para eso
@RequestMapping("Categories") //ENDPOINT mappea request con lo siguiente del localhost/Categories
public class Categoriesontroller {

    @GetMapping //localhost/8080/Categories
    public ArrayList<Category> getCategories() {        
        CategoriesService categoriesService = new CategoriesService();
        return categoriesService.getCategories();
    }

    @GetMapping("{categoriId}") //localhost/8080/Categories/id
    public String getCategoriesById(@PathVariable int categoryId) { //PATH VARIABLE es porque va a cambiar y que lo vaya cambiando
        CategoriesService categoriesService = new CategoriesService();
        return categoriesService.getCategoriesById(categoryId);
    }
    
    @PostMapping
    public String createCategory(@RequestBody String entity) { // REQUESTBODY porque mando un cuerpo de solicitud
        //TODO: process POST request
        CategoriesService categoriesService = new CategoriesService();
        return categoriesService.createCategory(entity);
    }
    
    
}
