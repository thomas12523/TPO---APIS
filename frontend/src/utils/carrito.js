import { URL, opciones, leerRespuesta } from "./api"

export const obtenerCarrito = () => {
    return fetch(`${URL}/Carrito/${localStorage.getItem('carritoId')}`, opciones('GET'))
    .then(leerRespuesta)
}

export const agregarAlCarrito = (productoId, cantidad, precioUnitario) => {
    const datosAEnviar = {
        carritoId: Number(localStorage.getItem('carritoId')),
        productoId: productoId,
        cantidad: cantidad,
        precioUnitario: precioUnitario
    }

    return fetch(`${URL}/DetalleCarrito`, opciones('POST', datosAEnviar))
    .then(leerRespuesta)
    .catch((error)=>{

        if(error.message.includes('duplicado')){
            throw new Error('Ese producto ya está en tu carrito')
        }
        throw error
    })
}
