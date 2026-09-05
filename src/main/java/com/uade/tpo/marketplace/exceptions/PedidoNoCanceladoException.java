package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El pedido debe estar cancelado antes de eliminarlo")
public class PedidoNoCanceladoException extends Exception {

}
