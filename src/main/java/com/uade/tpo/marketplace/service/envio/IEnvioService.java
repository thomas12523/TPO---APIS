package com.uade.tpo.marketplace.service.envio;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.entity.dto.EnvioRequest;
import com.uade.tpo.marketplace.exceptions.EnvioDuplicateException;

public interface IEnvioService {

    public List<Envio> getEnvios(Integer pedidoId);

    public Optional<Envio> getEnvioById(int envioId);

    public Envio crearEnvio(EnvioRequest envioRequest) throws EnvioDuplicateException;

    public Envio actualizarEnvio(int envioId, EnvioRequest envioRequest);

    public boolean deleteEnvio(int envioId);

    public Envio guardarEnvio(Envio envio);

    public void deleteByPedidoId(int pedidoId);
}
