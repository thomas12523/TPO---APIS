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

    public static DescuentoResponse from(Descuento descuento) {
        DescuentoResponse response = new DescuentoResponse();
        response.setDescuentoId(descuento.getDescuentoId());
        response.setProductoId(descuento.getProducto().getProductoId());
        response.setPorcentaje(descuento.getPorcentaje());
        response.setActivo(descuento.isActivo());
        response.setFechaInicio(descuento.getFechaInicio());
        response.setFechaFin(descuento.getFechaFin());
        return response;
    }
}
