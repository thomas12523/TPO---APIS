package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Category;

import lombok.Data;

@Data
public class CategoryResponse {
    private int id;
    private String nombre;
    private boolean activo;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.nombre = category.getNombre();
        this.activo = category.isActivo();
    }
}
