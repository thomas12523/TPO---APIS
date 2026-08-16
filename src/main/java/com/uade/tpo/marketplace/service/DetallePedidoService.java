package com.uade.tpo.marketplace.service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DetallePedidoService {
    
    @GetMapping
    public String getDetallePedidos() {
        return new String(); // cambiar luego
    }

    @GetMapping("{detallePedidoId}")
    public String getDetallePedidoById(@PathVariable int detallePedidoId) {
        return new String();
    }

    @PostMapping
    public String crearDetallePedido(@RequestBody String entity) { // REQUESTBODY porque mando un cuerpo de solicitud
        //TODO: process POST request
        return entity;
    }

    @PutMapping("{detallePedidoId}")
    public String actualizarDetallePedido(@PathVariable int detallePedidoId, @RequestBody String entity){
        
        return new String();
    }
    
    @DeleteMapping("{detallePedidoId}")
    public String deleteDetallePedido(@PathVariable int detallePedidoId) {
        //TODO: process DELETE request
        return new String();
    }

}
