package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El detalle de pedido que se intenta agregar esta duplicado")
public class DetallePedidoDuplicateException extends Exception {

}
