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
    private boolean activo;

    public ImagenResponse(Imagen imagen) {
        this.imagenId = imagen.getImagenId();
        this.productoId = imagen.getProducto().getProductoId();
        this.imagenUrl = imagen.getImagenUrl();
        this.tipoContenido = imagen.getTipoContenido();
        if (imagen.getDatos() != null)
            this.imagenBase64 = Base64.getEncoder().encodeToString(imagen.getDatos());
        this.activo = imagen.isActivo();
    }
}
