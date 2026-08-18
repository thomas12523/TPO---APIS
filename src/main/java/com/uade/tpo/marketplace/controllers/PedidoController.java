package com.uade.tpo.marketplace.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.service.PedidoService;

@RestController
@RequestMapping("Pedido")
public class PedidoController {

    @GetMapping
    public ArrayList<Pedido> getPedidos() {
        PedidoService pedidoService = new PedidoService();
        return pedidoService.getPedidos();
    }

    @GetMapping("{pedidoId}")
    public Pedido getPedidoById(@PathVariable int pedidoId) {
        PedidoService pedidoService = new PedidoService();
        return pedidoService.getPedidoById(pedidoId);
    }

    //CRUD
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido entity) {
        PedidoService pedidoService = new PedidoService();
        return pedidoService.crearPedido(entity);
    }

    @PutMapping("{pedidoId}")
    public Pedido actualizarPedido(@PathVariable int pedidoId, @RequestBody Pedido entity) {
        PedidoService pedidoService = new PedidoService();
        return pedidoService.actualizarPedido(pedidoId, entity);
    }

    @DeleteMapping("{pedidoId}")
    public boolean deletePedido(@PathVariable int pedidoId) {
        PedidoService pedidoService = new PedidoService();
        return pedidoService.deletePedido(pedidoId);
    }
    //SET ESTADO
    
}
