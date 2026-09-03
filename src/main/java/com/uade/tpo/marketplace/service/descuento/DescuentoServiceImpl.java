package com.uade.tpo.marketplace.service.descuento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Descuento;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.DescuentoRequest;
import com.uade.tpo.marketplace.exceptions.DescuentoInvalidoException;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.repository.IDescuentoRepository;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DescuentoServiceImpl implements IDescuentoService {

    @Autowired
    private IDescuentoRepository descuentoRepository;

    @Autowired
    private IProductoService productoService;

    @Transactional
    public List<Descuento> getDescuentos(Integer productoId) {
        if (productoId != null)
            return descuentoRepository.findByProductoId(productoId);

        return descuentoRepository.findAll();
    }

    @Transactional
    public Optional<Descuento> getDescuentoById(int descuentoId) {
        return descuentoRepository.findById(descuentoId);
    }

    public Descuento crearDescuento(DescuentoRequest descuentoRequest) throws DescuentoInvalidoException {
        validarPorcentaje(descuentoRequest.getPorcentaje());

        Producto producto = productoService.getProductoById(descuentoRequest.getProductoId())
                .orElseThrow(ProductoNotFoundException::new);

        Descuento descuento = new Descuento();
        descuento.setProducto(producto);
        descuento.setPorcentaje(descuentoRequest.getPorcentaje());
        descuento.setActivo(descuentoRequest.isActivo());
        descuento.setFechaInicio(descuentoRequest.getFechaInicio());
        descuento.setFechaFin(descuentoRequest.getFechaFin());
        return descuentoRepository.save(descuento);
    }

    public Descuento actualizarDescuento(int descuentoId, DescuentoRequest descuentoRequest) throws DescuentoInvalidoException {
        Optional<Descuento> existente = descuentoRepository.findById(descuentoId);
        if (existente.isEmpty())
            return null;

        validarPorcentaje(descuentoRequest.getPorcentaje());

        Descuento descuento = existente.get();
        descuento.setPorcentaje(descuentoRequest.getPorcentaje());
        descuento.setActivo(descuentoRequest.isActivo());
        descuento.setFechaInicio(descuentoRequest.getFechaInicio());
        descuento.setFechaFin(descuentoRequest.getFechaFin());
        return descuentoRepository.save(descuento);
    }

    public Optional<Descuento> deleteDescuento(int descuentoId) {
        Optional<Descuento> existente = descuentoRepository.findById(descuentoId);
        if (existente.isEmpty())
            return Optional.empty();

        Descuento descuento = existente.get();
        descuento.setActivo(false);
        return Optional.of(descuentoRepository.save(descuento));
    }

    @Transactional
    public double getPrecioConDescuento(Producto producto) {
        Optional<Descuento> vigente = descuentoRepository.findByProductoId(producto.getProductoId()).stream()
                .filter(this::esVigente)
                .findFirst();

        if (vigente.isEmpty())
            return producto.getPrecioUnitario();

        double porcentaje = vigente.get().getPorcentaje();
        return producto.getPrecioUnitario() - (producto.getPrecioUnitario() * porcentaje / 100);
    }

    private boolean esVigente(Descuento descuento) {
        if (!descuento.isActivo())
            return false;

        LocalDate hoy = LocalDate.now();
        if (descuento.getFechaInicio() != null && !descuento.getFechaInicio().isBlank()
                && hoy.isBefore(LocalDate.parse(descuento.getFechaInicio())))
            return false;

        if (descuento.getFechaFin() != null && !descuento.getFechaFin().isBlank()
                && hoy.isAfter(LocalDate.parse(descuento.getFechaFin())))
            return false;

        return true;
    }

    private void validarPorcentaje(double porcentaje) throws DescuentoInvalidoException {
        if (porcentaje < 0 || porcentaje > 100)
            throw new DescuentoInvalidoException();
    }
}
