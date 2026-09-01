package com.uade.tpo.marketplace.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Descuento;
import com.uade.tpo.marketplace.entity.dto.request.DescuentoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.DescuentoResponse;
import com.uade.tpo.marketplace.exceptions.DescuentoInvalidoException;
import com.uade.tpo.marketplace.service.descuento.IDescuentoService;

@RestController
@RequestMapping("Descuento")
public class DescuentoController {

    @Autowired
    private IDescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<DescuentoResponse>> getDescuentos(@RequestParam(required = false) Integer productoId) {
        return ResponseEntity.ok(descuentoService.getDescuentos(productoId).stream()
                .map(DescuentoResponse::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("{descuentoId}")
    public ResponseEntity<DescuentoResponse> getDescuentoById(@PathVariable int descuentoId) {
        Optional<Descuento> result = descuentoService.getDescuentoById(descuentoId);
        if (result.isPresent())
            return ResponseEntity.ok(DescuentoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearDescuento(@RequestBody DescuentoRequest descuentoRequest) throws DescuentoInvalidoException {
        Descuento result = descuentoService.crearDescuento(descuentoRequest);
        return ResponseEntity.ok(DescuentoResponse.from(result));
    }

    @PutMapping("{descuentoId}")
    public ResponseEntity<DescuentoResponse> actualizarDescuento(@PathVariable int descuentoId, @RequestBody DescuentoRequest descuentoRequest) throws DescuentoInvalidoException {
        Descuento result = descuentoService.actualizarDescuento(descuentoId, descuentoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(DescuentoResponse.from(result));
    }

    @DeleteMapping("{descuentoId}")
    public ResponseEntity<Object> deleteDescuento(@PathVariable int descuentoId) {
        Optional<Descuento> result = descuentoService.deleteDescuento(descuentoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Descuento desactivado correctamente", DescuentoResponse.from(result.get())));
    }
}
