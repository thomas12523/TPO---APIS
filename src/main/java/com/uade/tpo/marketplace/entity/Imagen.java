package com.uade.tpo.marketplace.entity;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Imagen {

    private int imagenId;
    private int productoId;
    private String imagenUrl;


}
