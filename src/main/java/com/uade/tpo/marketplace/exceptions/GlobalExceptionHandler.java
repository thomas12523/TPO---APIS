package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // NotFound Exceptions (404)
    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<String> handleCategoriaNotFound(CategoriaNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Categoria no encontrada.");
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Usuario no encontrado.");
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<String> handleProductoNotFound(ProductoNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Producto no encontrado.");
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<String> handlePedidoNotFound(PedidoNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Pedido no encontrado.");
    }

    @ExceptionHandler(CarritoNotFoundException.class)
    public ResponseEntity<String> handleCarritoNotFound(CarritoNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Carrito no encontrado.");
    }

    // Duplicate & Business Logic Exceptions (400 Bad Request)
    @ExceptionHandler(CategoryDuplicateException.class)
    public ResponseEntity<String> handleCategoryDuplicate(CategoryDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "La categoria que se intenta agregar esta duplicada.");
    }

    @ExceptionHandler(UsuarioDuplicateException.class)
    public ResponseEntity<String> handleUsuarioDuplicate(UsuarioDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El usuario que se intenta agregar esta duplicado.");
    }

    @ExceptionHandler(ProductoDuplicateException.class)
    public ResponseEntity<String> handleProductoDuplicate(ProductoDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El producto que se intenta agregar esta duplicado.");
    }

    @ExceptionHandler(ImagenDuplicateException.class)
    public ResponseEntity<String> handleImagenDuplicate(ImagenDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "La imagen que se intenta agregar esta duplicada.");
    }

    @ExceptionHandler(DetalleCarritoDuplicateException.class)
    public ResponseEntity<String> handleDetalleCarritoDuplicate(DetalleCarritoDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El detalle de carrito que se intenta agregar esta duplicado.");
    }

    @ExceptionHandler(DetallePedidoDuplicateException.class)
    public ResponseEntity<String> handleDetallePedidoDuplicate(DetallePedidoDuplicateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El detalle de pedido que se intenta agregar esta duplicado.");
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> handleStockInsuficiente(StockInsuficienteException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "La cantidad solicitada supera el stock disponible del producto.");
    }

    @ExceptionHandler(StockInvalidoException.class)
    public ResponseEntity<String> handleStockInvalido(StockInvalidoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo.");
    }

    @ExceptionHandler(DescuentoInvalidoException.class)
    public ResponseEntity<String> handleDescuentoInvalido(DescuentoInvalidoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El porcentaje de descuento debe estar entre 0 y 100.");
    }

    @ExceptionHandler(CarritoVacioException.class)
    public ResponseEntity<String> handleCarritoVacio(CarritoVacioException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "El carrito no tiene productos para confirmar el pedido.");
    }

    private ResponseEntity<String> buildResponse(HttpStatus status, String message) {
        String jsonResponse = String.format(
                "{\"error\": \"%s\", \"status\": %d, \"message\": \"%s\"}",
                status.name(),
                status.value(),
                message);
        return ResponseEntity.status(status).body(jsonResponse);
    }

}
