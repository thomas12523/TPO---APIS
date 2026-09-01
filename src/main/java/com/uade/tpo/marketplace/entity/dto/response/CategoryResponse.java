package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Category;

import lombok.Data;

@Data
public class CategoryResponse {
    private int id;
    private String nombre;
    private boolean activo;

    public static CategoryResponse from(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setNombre(category.getNombre());
        response.setActivo(category.isActivo());
        return response;
    }
}
