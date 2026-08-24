package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Imagen;

import lombok.Data;

@Data
public class ImagenResponse {
    private int imagenId;
    private int productoId;
    private String imagenUrl;

    public static ImagenResponse from(Imagen imagen) {
        ImagenResponse response = new ImagenResponse();
        response.setImagenId(imagen.getImagenId());
        response.setProductoId(imagen.getProducto().getProductoId());
        response.setImagenUrl(imagen.getImagenUrl());
        return response;
    }
}
