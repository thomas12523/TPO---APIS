package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El porcentaje de descuento debe estar entre 0 y 100")
public class DescuentoInvalidoException extends Exception {

}
