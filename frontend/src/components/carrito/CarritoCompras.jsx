import { useEffect, useState } from "react"
import CarritoVacio from "./vacio/CarritoVacio"
import ListaItems from "./lista/ListaItems"
import ResumenOrden from "./ResumenOrden"
import AvisoSesion from "../ui/AvisoSesion"
import MensajeError from "../ui/MensajeError"
import { URL, opciones, leerRespuesta } from "../../utils/api"
import { obtenerCarrito } from "../../utils/carrito"

const CarritoCompras = () => {

    const [carrito, setCarrito] = useState(null)
    const [error, setError] = useState('')
    const carritoId = localStorage.getItem('carritoId')

    useEffect(()=>{
        if(carritoId === null){
            return
        }
        obtenerCarrito()
        .then((data)=>setCarrito(data))
        .catch((error)=>{
            console.error('Error al cargar el carrito', error)
            setError('No pudimos cargar tu carrito. Probá cerrar sesión y volver a entrar.')
        })
    }, [carritoId])

    // Después de cada cambio volvemos a pedir el carrito, porque los totales con descuento los calcula el back
    const actualizarCarrito = () => {
        obtenerCarrito()
        .then((data)=>{
            setCarrito(data)
            setError('')
        })
        .catch((error)=>console.error('Error al actualizar el carrito', error))
    }

    const cambiarCantidad = (indice, cantidad) => {
        const item = carrito.items[indice]
        const datosAEnviar = {carritoId: item.carritoId, productoId: item.productoId, cantidad: cantidad, precioUnitario: item.precioUnitario}

        fetch(`${URL}/DetalleCarrito/${item.carritoId}/${item.productoId}`, opciones('PUT', datosAEnviar))
        .then(leerRespuesta)
        .then(()=>actualizarCarrito())
        .catch((error)=>{
            console.error('Error al cambiar la cantidad', error)
            setError(error.message)
        })
    }

    const eliminarItem = (indice) => {
        const item = carrito.items[indice]

        fetch(`${URL}/DetalleCarrito/${item.carritoId}/${item.productoId}`, opciones('DELETE'))
        .then(leerRespuesta)
        .then(()=>actualizarCarrito())
        .catch((error)=>{
            console.error('Error al eliminar el producto', error)
            setError(error.message)
        })
    }

    if(carritoId === null){
        return <AvisoSesion mensaje="Iniciá sesión para ver tu carrito." />
    }

    // Mientras la promesa está pendiente todavía no hay carrito para mostrar (o falló la carga)
    if(carrito === null){
        return <MensajeError mensaje={error} />
    }

    if(carrito.items.length === 0){
        return <CarritoVacio />
    }

    return(
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_22rem] gap-space-lg">
            <div className="flex flex-col gap-space-md">
                <MensajeError mensaje={error} />
                <ListaItems items={carrito.items} cambiarCantidad={cambiarCantidad} eliminarItem={eliminarItem} />
            </div>
            <ResumenOrden subtotal={carrito.subtotal} total={carrito.total} />
        </div>
    )
}

export default CarritoCompras
