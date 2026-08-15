// SOLO LENGUAJE DE PROGRAMACION, CASI NADA DE ANOTACION. NADA DE LIBRERIAS
package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.marketplace.repository.CategoriesRepository;

import entity.Category;

public class CategoriesService {

    public ArrayList<Category> getCategories() {
        CategoriesRepository categoriesRepository = new CategoriesRepository();
        return categoriesRepository.getCategories();
    }

    public String getCategoriesById(@PathVariable int categoryId) { //PATH VARIABLE es porque va a cambiar y que lo vaya cambiando
        return new String();
    }
    
    public String createCategory(@RequestBody String entity) { // REQUESTBODY porque mando un cuerpo de solicitud
        //TODO: process POST request
        
        return entity;
    }
}
