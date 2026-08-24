package com.uade.tpo.marketplace.service.soporte;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.CheckoutRequest;
import com.uade.tpo.marketplace.exceptions.CarritoVacioException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;

public interface ICheckoutService {

    public Pedido checkout(int carritoId, CheckoutRequest checkoutRequest) throws CarritoVacioException, StockInsuficienteException;
}
