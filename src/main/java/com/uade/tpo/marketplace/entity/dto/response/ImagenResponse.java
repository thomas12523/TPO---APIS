package com.uade.tpo.marketplace.entity.dto.response;

import java.util.Base64;

import com.uade.tpo.marketplace.entity.Imagen;

import lombok.Data;

@Data
public class ImagenResponse {
    private int imagenId;
    private int productoId;
    private String imagenUrl;
    private String tipoContenido;
    private String imagenBase64;

    public static ImagenResponse from(Imagen imagen) {
        ImagenResponse response = new ImagenResponse();
        response.setImagenId(imagen.getImagenId());
        response.setProductoId(imagen.getProducto().getProductoId());
        response.setImagenUrl(imagen.getImagenUrl());
        response.setTipoContenido(imagen.getTipoContenido());
        if (imagen.getDatos() != null)
            response.setImagenBase64(Base64.getEncoder().encodeToString(imagen.getDatos()));
        return response;
    }
}
