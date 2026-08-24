package com.uade.tpo.marketplace.service.descuento;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Descuento;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.DescuentoRequest;
import com.uade.tpo.marketplace.exceptions.DescuentoInvalidoException;

public interface IDescuentoService {

    public List<Descuento> getDescuentos(Integer productoId);

    public Optional<Descuento> getDescuentoById(int descuentoId);

    public Descuento crearDescuento(DescuentoRequest descuentoRequest) throws DescuentoInvalidoException;

    public Descuento actualizarDescuento(int descuentoId, DescuentoRequest descuentoRequest) throws DescuentoInvalidoException;

    public boolean deleteDescuento(int descuentoId);

    public double getPrecioConDescuento(Producto producto);
}
