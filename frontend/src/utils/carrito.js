import { URL, opciones, leerRespuesta } from "./api"

// GET /Carrito/{carritoId}: trae el carrito con sus items y los totales
// (el subtotal sin descuentos y el total con descuentos los calcula el back)
export const obtenerCarrito = () => {
    return fetch(`${URL}/Carrito/${localStorage.getItem('carritoId')}`, opciones('GET'))
    .then(leerRespuesta)
}

// PUT /DetalleCarrito/{carritoId}/{productoId}: cambia la cantidad de un producto que ya está en el carrito
export const actualizarCantidad = (productoId, cantidad, precioUnitario) => {
    const carritoId = Number(localStorage.getItem('carritoId'))
    const datosAEnviar = {carritoId: carritoId, productoId: productoId, cantidad: cantidad, precioUnitario: precioUnitario}

    return fetch(`${URL}/DetalleCarrito/${carritoId}/${productoId}`, opciones('PUT', datosAEnviar))
    .then(leerRespuesta)
}

// DELETE /DetalleCarrito/{carritoId}/{productoId}: saca un producto del carrito
export const sacarDelCarrito = (productoId) => {
    return fetch(`${URL}/DetalleCarrito/${localStorage.getItem('carritoId')}/${productoId}`, opciones('DELETE'))
    .then(leerRespuesta)
}

// Le suma la cantidad nueva a un producto que ya estaba en el carrito
const sumarCantidad = (datosAEnviar) => {
    return obtenerCarrito()
    .then((data)=>{
        const item = data.items.find((value)=>value.productoId === datosAEnviar.productoId)
        return actualizarCantidad(datosAEnviar.productoId, item.cantidad + datosAEnviar.cantidad, datosAEnviar.precioUnitario)
    })
}

// POST /DetalleCarrito: agrega un producto al carrito del usuario logueado.
// Los nombres coinciden con DetalleCarritoRequest
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
        // Si el producto ya estaba, el back avisa que el detalle está duplicado: en ese caso sumamos la cantidad
        if(error.message.includes('duplicado')){
            return sumarCantidad(datosAEnviar)
        }
        throw error
    })
}
