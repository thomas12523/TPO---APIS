package com.uade.tpo.marketplace.service.envio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.EnvioRequest;
import com.uade.tpo.marketplace.exceptions.EnvioDuplicateException;
import com.uade.tpo.marketplace.repository.IEnvioRepository;
import com.uade.tpo.marketplace.repository.IPedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnvioServiceImpl implements IEnvioService {

    @Autowired
    private IEnvioRepository envioRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    public List<Envio> getEnvios() {
        return envioRepository.findAll();
    }

    public Optional<Envio> getEnvioById(int envioId) {
        return envioRepository.findById(envioId);
    }

    public Envio crearEnvio(EnvioRequest envioRequest) throws EnvioDuplicateException {
        if (envioRepository.findByPedido_PedidoId(envioRequest.getPedidoId()).isPresent())
            throw new EnvioDuplicateException();

        Pedido pedido = pedidoRepository.findById(envioRequest.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        Envio envio = new Envio();
        envio.setPedido(pedido);
        envio.setDireccion(envioRequest.getDireccion());
        envio.setMetodoEnvio(envioRequest.getMetodoEnvio());
        envio.setCostoEnvio(envioRequest.getCostoEnvio());
        return envioRepository.save(envio);
    }

    public Envio actualizarEnvio(int envioId, EnvioRequest envioRequest) {
        Optional<Envio> existente = envioRepository.findById(envioId);
        if (existente.isEmpty())
            return null;

        Envio envio = existente.get();
        envio.setDireccion(envioRequest.getDireccion());
        envio.setMetodoEnvio(envioRequest.getMetodoEnvio());
        envio.setCostoEnvio(envioRequest.getCostoEnvio());
        return envioRepository.save(envio);
    }

    public boolean deleteEnvio(int envioId) {
        if (envioRepository.findById(envioId).isEmpty())
            return false;

        envioRepository.deleteById(envioId);
        return true;
    }
}
