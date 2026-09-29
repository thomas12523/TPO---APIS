package com.uade.tpo.marketplace.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Resena;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.ResenaRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.ResenaResponse;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.exceptions.ResenaDuplicateException;
import com.uade.tpo.marketplace.exceptions.ResenaInvalidaException;
import com.uade.tpo.marketplace.service.resena.IResenaService;

@RestController
@RequestMapping("Resena")
public class ResenaController {

    @Autowired
    private IResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaResponse>> getResenas(@RequestParam(required = false) Integer productoId) {
        return ResponseEntity.ok(resenaService.getResenas(productoId).stream()
                .map(ResenaResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("{resenaId}")
    public ResponseEntity<ResenaResponse> getResenaById(@PathVariable int resenaId) {
        Optional<Resena> result = resenaService.getResenaById(resenaId);
        if (result.isPresent())
            return ResponseEntity.ok(new ResenaResponse(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearResena(@AuthenticationPrincipal Usuario usuarioActual, @RequestBody ResenaRequest resenaRequest)
            throws ResenaInvalidaException, ResenaDuplicateException, ProductoNotFoundException {
        Resena result = resenaService.crearResena(resenaRequest, usuarioActual);
        return ResponseEntity.ok(new ResenaResponse(result));
    }

    @PutMapping("{resenaId}")
    public ResponseEntity<ResenaResponse> actualizarResena(@AuthenticationPrincipal Usuario usuarioActual, @PathVariable int resenaId,
            @RequestBody ResenaRequest resenaRequest) throws ResenaInvalidaException {
        Optional<Resena> result = resenaService.actualizarResena(resenaId, resenaRequest, usuarioActual);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ResenaResponse(result.get()));
    }

    @PatchMapping("{resenaId}") // baja logica: desactiva la resena en vez de borrar la fila
    public ResponseEntity<Object> deleteResena(@AuthenticationPrincipal Usuario usuarioActual, @PathVariable int resenaId) {
        Optional<Resena> result = resenaService.deleteResena(resenaId, usuarioActual);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Resena desactivada correctamente", new ResenaResponse(result.get())));
    }
}
