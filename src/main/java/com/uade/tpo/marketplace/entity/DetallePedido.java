package com.uade.tpo.marketplace.entity;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class DetallePedido {

    private int detallePedidoId;
    private int pedidoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;
    private double subtotal;


    
}
