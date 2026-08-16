package com.uade.tpo.marketplace.repository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DetallePedidoRepository {
    @GetMapping
    public String getDetallePedidos() {
        return null; // cambiar luego
    }

    @GetMapping("{detallePedidoId}")
    public String getDetallePedidoById(@PathVariable int detallePedidoId) {
        return null;
    }

    @PostMapping
    public String crearDetallePedido(@RequestBody String entity) { // REQUESTBODY porque mando un cuerpo de solicitud
        //TODO: process POST request
        return null;
    }

    @PutMapping("{detallePedidoId}")
    public String actualizarDetallePedido(@PathVariable int detallePedidoId, @RequestBody String entity){
        
        return null;
    }
    
    @DeleteMapping("{detallePedidoId}")
    public String deleteDetallePedido(@PathVariable int detallePedidoId) {
        //TODO: process DELETE request
        return null;
    }

}
