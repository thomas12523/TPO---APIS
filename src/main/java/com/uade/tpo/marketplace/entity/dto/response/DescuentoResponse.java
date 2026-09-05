package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Descuento;

import lombok.Data;

@Data
public class DescuentoResponse {
    private int descuentoId;
    private int productoId;
    private double porcentaje;
    private boolean activo;
    private String fechaInicio;
    private String fechaFin;

    public DescuentoResponse(Descuento descuento) {
        this.descuentoId = descuento.getDescuentoId();
        this.productoId = descuento.getProducto().getProductoId();
        this.porcentaje = descuento.getPorcentaje();
        this.activo = descuento.isActivo();
        this.fechaInicio = descuento.getFechaInicio();
        this.fechaFin = descuento.getFechaFin();
    }
}
