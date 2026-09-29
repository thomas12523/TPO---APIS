package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Resena;

import lombok.Data;

@Data
public class ResenaResponse {
    private int resenaId;
    private int productoId;
    private int usuarioId;
    private String username;
    private int puntuacion;
    private String comentario;
    private String fecha;
    private boolean activo;

    public ResenaResponse(Resena resena) {
        this.resenaId = resena.getResenaId();
        this.productoId = resena.getProducto().getProductoId();
        this.usuarioId = resena.getUsuario().getUsuarioId();
        this.username = resena.getUsuario().getUsername();
        this.puntuacion = resena.getPuntuacion();
        this.comentario = resena.getComentario();
        this.fecha = resena.getFecha();
        this.activo = resena.isActivo();
    }
}
