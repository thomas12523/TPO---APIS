package com.uade.tpo.marketplace.entity.dto.response;

import java.util.Date;

import com.uade.tpo.marketplace.entity.Resenia;

import lombok.Data;

@Data
public class ReseniaResponse {
    private int reseniaId;
    private int usuarioId;
    private String usuarioNombre;
    private int productoId;
    private String productoNombre;
    private int calificacion;
    private String comentario;
    private Date fechaCreacion;

    public static ReseniaResponse from(Resenia resenia) {
        ReseniaResponse response = new ReseniaResponse();
        response.setReseniaId(resenia.getReseniaId());
        response.setUsuarioId(resenia.getUsuario().getUsuarioId());
        response.setUsuarioNombre(resenia.getUsuario().getNombre());
        response.setProductoId(resenia.getProducto().getProductoId());
        response.setProductoNombre(resenia.getProducto().getNombreProducto());
        response.setCalificacion(resenia.getCalificacion());
        response.setComentario(resenia.getComentario());
        response.setFechaCreacion(resenia.getFechaCreacion());
        return response;
    }
}
