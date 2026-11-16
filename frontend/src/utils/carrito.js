import { URL, opciones, leerRespuesta } from "./api"

export const obtenerCarrito = () => {
    return fetch(`${URL}/Carrito/${localStorage.getItem('carritoId')}`, opciones('GET'))
    .then(leerRespuesta)
}

export const actualizarCantidad = (productoId, cantidad, precioUnitario) => {
    const carritoId = Number(localStorage.getItem('carritoId'))
    const datosAEnviar = {carritoId: carritoId, productoId: productoId, cantidad: cantidad, precioUnitario: precioUnitario}

    return fetch(`${URL}/DetalleCarrito/${carritoId}/${productoId}`, opciones('PUT', datosAEnviar))
    .then(leerRespuesta)
}

export const sacarDelCarrito = (productoId) => {
    return fetch(`${URL}/DetalleCarrito/${localStorage.getItem('carritoId')}/${productoId}`, opciones('DELETE'))
    .then(leerRespuesta)
}

const sumarCantidad = (datosAEnviar) => {
    return obtenerCarrito()
    .then((data)=>{
        const item = data.items.find((value)=>value.productoId === datosAEnviar.productoId)
        return actualizarCantidad(datosAEnviar.productoId, item.cantidad + datosAEnviar.cantidad, datosAEnviar.precioUnitario)
    })
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
            return sumarCantidad(datosAEnviar)
        }
        throw error
    })
}
