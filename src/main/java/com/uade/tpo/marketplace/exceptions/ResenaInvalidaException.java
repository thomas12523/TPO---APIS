package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La puntuacion de la resena debe estar entre 1 y 5")
public class ResenaInvalidaException extends Exception {

}
