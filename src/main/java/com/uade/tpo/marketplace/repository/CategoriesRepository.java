package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import entity.Category;

public class CategoriesRepository {
    public ArrayList<Category> categories = new ArrayList<>(
        Arrays.asList(Category.builder().nombre("caballos").id(1).build(),
        Category.builder().nombre("peluches").id(2).build(),
        Category.builder().nombre("cafe").id(3).build())
    );

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    public String getCategoriesById(@PathVariable int categoryId) { //PATH VARIABLE es porque va a cambiar y que lo vaya cambiando
        return null;
    }
    
    public String createCategory(@RequestBody String entity) { // REQUESTBODY porque mando un cuerpo de solicitud
        //TODO: process POST request
        
        return null;
    }

}
