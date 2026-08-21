package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.entity.dto.EnvioRequest;
import com.uade.tpo.marketplace.exceptions.EnvioDuplicateException;
import com.uade.tpo.marketplace.service.envio.IEnvioService;

@RestController
@RequestMapping("Envio")
public class EnvioController {

    @Autowired
    private IEnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> getEnvios() {
        return ResponseEntity.ok(envioService.getEnvios());
    }

    @GetMapping("{envioId}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable int envioId) {
        Optional<Envio> result = envioService.getEnvioById(envioId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearEnvio(@RequestBody EnvioRequest envioRequest) throws EnvioDuplicateException {
        Envio result = envioService.crearEnvio(envioRequest);
        return ResponseEntity.created(URI.create("/Envio/" + result.getEnvioId())).body(result);
    }

    @PutMapping("{envioId}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable int envioId, @RequestBody EnvioRequest envioRequest) {
        Envio result = envioService.actualizarEnvio(envioId, envioRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{envioId}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable int envioId) {
        boolean deleted = envioService.deleteEnvio(envioId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
