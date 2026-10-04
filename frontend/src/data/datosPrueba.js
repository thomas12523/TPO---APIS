// Datos de prueba con la misma forma que los DTOs del back.
// Se borran cuando conectemos el front con el back.

export const categoriasPrueba = [
    {id: 1, nombre: 'Pesas', activo: true},
    {id: 2, nombre: 'Máquinas', activo: true},
    {id: 3, nombre: 'Indumentaria', activo: true},
    {id: 4, nombre: 'Suplementos', activo: true},
    {id: 5, nombre: 'Accesorios', activo: true}
]

export const productosPrueba = [
    {
        productoId: 1, categoriaId: 1, categoriaNombre: 'Pesas',
        nombreProducto: 'Mancuernas ajustables Pro', descripcion: 'Set de mancuernas de uretano de 5 a 40 kg con rack incluido.',
        precioUnitario: 449, precioConDescuento: 449, tieneDescuento: false, stock: 12, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDA8SwEMm7akuLYtFE1-v6NfDxJwP_Whnc_G6-UfuwWatgYia00gRSPNvR3W_UnQ7dl2Qq0FPDBNVKDH9EuseQc5_yZB1kKgwJjQOo1fxNaonX4TNRQvy18WH26kwMCwVF4ym7KAJoUOqxpLzSJfxDjqaFB1uk4aY4N04VPkt_JRrkidZPvUp-DVDzzm3Vx2R0vY_bykxLNnz8d-mw5OagyWmNz2mVjzlDlT0rbvd4HKP_VmeaiN4JTLg'
    },
    {
        productoId: 2, categoriaId: 2, categoriaNombre: 'Máquinas',
        nombreProducto: 'Jaula de potencia X1', descripcion: 'Rack multifuncional de acero calibre 11 con barra de dominadas.',
        precioUnitario: 1299, precioConDescuento: 1299, tieneDescuento: false, stock: 4, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBmo9ntFHlh6Gf13xpAEIdfXdodjIoezzrgRwDfSnvLqLQhk1dKSn_2kJDsn4StIybRuOFlUoQuZKlTfA_x03_rR8JKPBJGGIegK2Skq3KF9NQ-UZ5o7AcN2sUWxWvLWwYDgG00PCY99N21zQ5EZ_G5mgCqpnnhvYKDT1_8Xd_6vzpkgOMPjv77ABXg54xjHz8qN0J4xLjaHwe-PV5oywKciYG_OrYABk1tWAEt__r2PbIC1BCSdMXJ4w'
    },
    {
        productoId: 3, categoriaId: 1, categoriaNombre: 'Pesas',
        nombreProducto: 'Barra olímpica 20 kg', descripcion: 'Barra de competición de 28 mm con moleteado de precisión.',
        precioUnitario: 340, precioConDescuento: 289, tieneDescuento: true, stock: 20, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuB4bDY-3n5BTaXUlO2DuXQ8Zftybq4Rsu94BHqdlMJ44R6gvNFp2VBjATHe09ChKbk_iXD0HgvQySe8Ne4jEgNDKG0dNoYQ_ntQrjZkFMqOV76ZfOfVydIcDp5i-MtBsU1oift0P_grsf9RYwV14-EIDUEJxXfF4LS3pTXr2xRawHB07vEglfj--ao84JHxlV52v5OghRTCh2i9OrtA7_8LzOrYSFoGzRCfOo40Kw0nhfz1w45oH9KEuw'
    },
    {
        productoId: 4, categoriaId: 2, categoriaNombre: 'Máquinas',
        nombreProducto: 'Banco multiángulo', descripcion: 'Banco regulable de 7 posiciones, soporta hasta 450 kg.',
        precioUnitario: 349, precioConDescuento: 349, tieneDescuento: false, stock: 0, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDvGuPbT2_FZyi4_971iojGu5g9BAyf679UNTdMBW3ZqS0-XJ6XF6L0bSW0-hGW15WgZzPNkPmUOnSArxqBDhHxlFlUwC0vnbfBGP-4FPV9AkDo2YbZacBFGAJbjvFft-fA78MLNU5gsnnFZvbmBpthPnj1O7TYwC58xHDrbVxS85GOeIqtkhRcIJ4rTcTbKEBxKKq7jXQZtI1OByu51i9FXvaVhvnsoB7wvBkT6-woclXaIYRWn8EjWQ'
    },
    {
        productoId: 5, categoriaId: 1, categoriaNombre: 'Pesas',
        nombreProducto: 'Set de discos bumper 120 kg', descripcion: 'Discos de uretano calibrados con tolerancia de 10 gramos.',
        precioUnitario: 620, precioConDescuento: 620, tieneDescuento: false, stock: 7, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDeSn9nFCl4K8DG7hDd81P4jY7E4rB0EeXTPrnyJykics8Y_I0G0DwwKhErxX2DOo-S3ANyN8-RHRt4PH9f5iH9F92NEUo8aXEzfVOv7qurA5oGpVYaTeSgTeZxZwDpRL4IfF8brMmFH9UowqAvCgrKgPMb3CaV-i1ZCGoAb3y0Ni9oUTEcq7ANJoDvaCb96rLg27oxaSkHJXxt_Twr5-rGyMkocgu4IJjztHIPyxWifEExIcFh4VGK3g'
    },
    {
        productoId: 6, categoriaId: 5, categoriaNombre: 'Accesorios',
        nombreProducto: 'Módulo landmine', descripcion: 'Accesorio giratorio de 360° que se monta en cualquier rack.',
        precioUnitario: 119, precioConDescuento: 119, tieneDescuento: false, stock: 15, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuA8P_vh8aJHWSMuYWU_kzN_6tUgD_Y3g-07kLEmsM7DjXUqxR8ppki7iL3nyziVkfCOU4WwyLvpIc8nwQwgO-PRUQzXLHbrV9CQ2fUCh5524LFPfGB737VfRa2fv38Romu5NNnEgWCoaTFhUx1KD7qRkGmpro7-rd5ocpDySIQ-VJM8xWFzs80IITckd3fPdpx2t34ZcyUsDFGJNhYZB71r3kSIWJ8uBROJTqSLD5LkUw9wd4NDhwmg6A'
    },
    {
        productoId: 7, categoriaId: 1, categoriaNombre: 'Pesas',
        nombreProducto: 'Barra hexagonal', descripcion: 'Barra trap para peso muerto con doble agarre elevado.',
        precioUnitario: 395, precioConDescuento: 395, tieneDescuento: false, stock: 3, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuATzKvZfE11VTq3Cn49cs5SWne9Zv8vALiXoxgGRQ_mfA_trdwD39iz-3WL6ajWnUpxJHQQAFErCY82SnlNRKMi6HwjMs2VC8VUFiRP-5ttX2ew3Tdg4FK6Cl4qrpdgwdJUawsrw1DCKK_5fGs7rYHoGIoZD8_7_vkoHiqLiHrvurHX7B427OgdgnDuilaGo3TWIo6xgLTzyBlraP3Pc97PK51xXBrGBthyy093SRAbkthNhhHJHRUusQ'
    },
    {
        productoId: 8, categoriaId: 5, categoriaNombre: 'Accesorios',
        nombreProducto: 'Kettlebell de hierro 32 kg', descripcion: 'Pesa rusa de hierro fundido con base plana.',
        precioUnitario: 160, precioConDescuento: 135, tieneDescuento: true, stock: 9, activo: true,
        imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDqFuMXYs-mxmzoSAxSmssKqi2ZNepfugFc2Te59DVlpL5DbVfAa6zHxxO4moMN4yiqCgQzyFI0t7o2dNgKhCtwKGIHChy5F-PoFhgRNw6tgNuyMbsaqbJAAvqZrpejpa5vwuvR3NmJhPlqfoXRgUDSfF-gImoIhGSqYSmJHL5GTNy9rcF-Sq5RJ9-Lbyk8-l0QXZdLcklCnTCGMt0TgwmqblaetHUj2k3AmJn_dWujcadFydW8O-wKMA'
    }
]

// ImagenResponse: imágenes extra de un producto (GET /Imagen?productoId=)
export const imagenesPrueba = [
    {imagenId: 1, productoId: 2, imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAzvZ8CnXHYwsMHGEZGc8AwRHAEbQSrwK80ljYnSc-cVKBLVbKXzwe-9ZrTzyCM3S1tmDRW749E4FI_wKbx07VCqstm1IyqQ78am9oecGf4YLwwX1Fqv_kMLfN2NXxsaEaaY_pPtccmrl_FdwPmXwbpiXUbF6UK1rFhUdzOlfm4y4DQmFwi7YvyavpQ_a_TsTvBHJgJoizN0rd6yfXCSCFEuQ7jR6Ddl2DXAOIcevFVeHa5dlSPSDOQCg', activo: true},
    {imagenId: 2, productoId: 2, imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBP7tH8PYp-oPBy_R6BECS5HsRAW41NjenDGvyMWMdPWxXploHlZhvOaKbCO7H0f-oc4_HXE2NjDNwJ3rtwwh03IkMaoSm5xb6MHaARLalqSaTiMvAS-w9AUuW5m2F2_UucGG0gw3M1XJzL28pWAHY62TXMk4sTqp8ghIOxd_AYkQzwSnQ6CmSxxXEWSgNsNBinzUIzF8BUsfTvHFworR9IVbOt7dxHK6EeGBzzc6r8N-lUoxxNT5RcLA', activo: true},
    {imagenId: 3, productoId: 2, imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAN8g67SsTrDZ8k5I8DGhckLh3328Q6u8hz-a87QX0BcegadCKW4YTuWl2k-izVUzl-7vUZonmHjSnUgyYwyiAD9zXpdpHubA-Qqa1BXtHix83gp_rSfXlf9SI3gxcK3IDrWc2pZHBGTt4xTDDC_9_J0UziyPOwFFsMTISPyjokw0JjcgZmuTMQnm4ENycInu48VKoFBrYG5buLrWOpv6zDfA4IHMtLYqvYzt0U7ZoV2H-XdV8y0amQIw', activo: true},
    {imagenId: 4, productoId: 2, imagenUrl: 'https://lh3.googleusercontent.com/aida-public/AB6AXuC1EwMj3tHbAaJYXuUnKI6m2S4DUI35rrS3bfhgGA1uYufhypk_x7aO3SYPPljn3hWKjYLybYhNIleg9E-_b8c_Grrrj9KKdJKOgso0btGqUy8gQlVn_8w6iuNYxFUi_dS6e97AddukAt089INLB79JwQK9tYe6d0osedLVaaP_cr_lapMIVWa5GVsGFIniSw6t2CAbNTs8WaUstL4yPy9-2K074CSnHVHgraXZjl6-j3iXoUh8qIwWVA', activo: true}
]

// ResenaResponse (GET /Resena?productoId=)
export const resenasPrueba = [
    {resenaId: 1, productoId: 2, usuarioId: 2, username: 'marcus_r', puntuacion: 5, comentario: 'Muy sólida, no se mueve nada aunque no esté amurada.', fecha: '2026-09-28', activo: true},
    {resenaId: 2, productoId: 2, usuarioId: 3, username: 'sarah_v', puntuacion: 4, comentario: 'Excelente calidad. El armado lleva un rato, pero vale la pena.', fecha: '2026-09-15', activo: true},
    {resenaId: 3, productoId: 2, usuarioId: 4, username: 'devon_k', puntuacion: 5, comentario: 'La mejor compra para el garage, las marcas láser ayudan mucho.', fecha: '2026-08-30', activo: true}
]

// CarritoResponse con sus DetalleCarritoResponse (GET /Carrito?usuarioId=)
export const carritoPrueba = {
    carritoId: 1, usuarioId: 1, fechaCarrito: '2026-10-04', activo: true,
    items: [
        {detalleCarritoId: 1, carritoId: 1, productoId: 3, productoNombre: 'Barra olímpica 20 kg', cantidad: 1, precioUnitario: 289, subtotal: 289},
        {detalleCarritoId: 2, carritoId: 1, productoId: 1, productoNombre: 'Mancuernas ajustables Pro', cantidad: 2, precioUnitario: 449, subtotal: 898},
        {detalleCarritoId: 3, carritoId: 1, productoId: 6, productoNombre: 'Módulo landmine', cantidad: 1, precioUnitario: 119, subtotal: 119}
    ],
    subtotal: 1306, total: 1306
}

// UsuarioResponse (GET /Usuario/me)
export const usuarioPrueba = {
    usuarioId: 1, dni: 38941202, username: 'marcus_vance', email: 'marcus.vance@mail.com',
    nombre: 'Marcus', apellido: 'Vance', role: 'USER', activo: true
}

// PedidoResponse (GET /Pedido?usuarioId=)
export const pedidosPrueba = [
    {pedidoId: 3, numeroPedido: 'PED-0003', usuarioId: 1, fechaCreacion: '2026-10-02', estado: 'PENDIENTE', subtotal: 1588, total: 1588, metodoPago: 'TARJETA_CREDITO', activo: true},
    {pedidoId: 2, numeroPedido: 'PED-0002', usuarioId: 1, fechaCreacion: '2026-09-15', estado: 'PENDIENTE', subtotal: 349, total: 349, metodoPago: 'TRANSFERENCIA', activo: true},
    {pedidoId: 1, numeroPedido: 'PED-0001', usuarioId: 1, fechaCreacion: '2026-08-02', estado: 'CANCELADO', subtotal: 620, total: 620, metodoPago: 'TARJETA_DEBITO', activo: true}
]

// DetallePedidoResponse (GET /DetallePedido?pedidoId=)
export const detallesPedidoPrueba = [
    {detallePedidoId: 1, pedidoId: 3, productoId: 2, productoNombre: 'Jaula de potencia X1', cantidad: 1, precioUnitario: 1299, observaciones: '', subtotal: 1299, activo: true},
    {detallePedidoId: 2, pedidoId: 3, productoId: 3, productoNombre: 'Barra olímpica 20 kg', cantidad: 1, precioUnitario: 289, observaciones: '', subtotal: 289, activo: true},
    {detallePedidoId: 3, pedidoId: 2, productoId: 4, productoNombre: 'Banco multiángulo', cantidad: 1, precioUnitario: 349, observaciones: '', subtotal: 349, activo: true},
    {detallePedidoId: 4, pedidoId: 1, productoId: 5, productoNombre: 'Set de discos bumper 120 kg', cantidad: 1, precioUnitario: 620, observaciones: '', subtotal: 620, activo: true}
]

// DescuentoResponse (GET /Descuento)
export const descuentosPrueba = [
    {descuentoId: 1, productoId: 3, porcentaje: 15, activo: true, fechaInicio: '2026-10-01', fechaFin: '2026-10-31'},
    {descuentoId: 2, productoId: 8, porcentaje: 15.6, activo: true, fechaInicio: '2026-09-15', fechaFin: '2026-12-31'}
]

// UsuarioResponse (GET /Usuario, solo admin)
export const usuariosPrueba = [
    usuarioPrueba,
    {usuarioId: 2, dni: 40123456, username: 'marcus_r', email: 'marcus.r@mail.com', nombre: 'Marcus', apellido: 'Ríos', role: 'USER', activo: true},
    {usuarioId: 3, dni: 35987654, username: 'sarah_v', email: 'sarah@mail.com', nombre: 'Sarah', apellido: 'Vidal', role: 'ADMIN', activo: true},
    {usuarioId: 4, dni: 42555111, username: 'devon_k', email: 'devon@mail.com', nombre: 'Devon', apellido: 'Klein', role: 'USER', activo: false}
]
