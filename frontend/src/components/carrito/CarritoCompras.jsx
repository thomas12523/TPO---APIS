import { useEffect, useState } from "react"
import { useSelector, useDispatch } from "react-redux"
import CarritoVacio from "./vacio/CarritoVacio"
import ListaItems from "./lista/ListaItems"
import ResumenOrden from "./ResumenOrden"
import AvisoSesion from "../ui/AvisoSesion"
import MensajeError from "../ui/MensajeError"
import { URL, opciones, leerRespuesta } from "../../utils/api"
import { obtenerCarrito } from "../../utils/carrito"
import { setCarrito } from "../../store/slices/carritoSlice"

const CarritoCompras = ({ carritoCambio }) => {
    const dispatch = useDispatch()
    const carrito = useSelector((state) => state.carrito.datos)
    const carritoId = useSelector((state) => state.carrito.carritoId) || localStorage.getItem('carritoId')
    const [error, setError] = useState('')

    useEffect(() => {
        if (!carritoId) {
            return
        }
        obtenerCarrito()
            .then((data) => dispatch(setCarrito(data)))
            .catch((err) => {
                console.error('Error al cargar el carrito', err)
                setError('No pudimos cargar tu carrito. Probá cerrar sesión y volver a entrar.')
            })
    }, [carritoId, dispatch])

    const actualizarCarrito = () => {
        obtenerCarrito()
            .then((data) => {
                dispatch(setCarrito(data))
                setError('')
                if (typeof carritoCambio === 'function') {
                    carritoCambio()
                }
            })
            .catch((err) => console.error('Error al actualizar el carrito', err))
    }

    const cambiarCantidad = (indice, cantidad) => {
        const item = carrito.items[indice]
        const datosAEnviar = { carritoId: item.carritoId, productoId: item.productoId, cantidad: cantidad, precioUnitario: item.precioUnitario }

        fetch(`${URL}/DetalleCarrito/${item.carritoId}/${item.productoId}`, opciones('PUT', datosAEnviar))
            .then(leerRespuesta)
            .then(() => actualizarCarrito())
            .catch((err) => {
                console.error('Error al cambiar la cantidad', err)
                setError(err.message)
            })
    }

    const eliminarItem = (indice) => {
        const item = carrito.items[indice]

        fetch(`${URL}/DetalleCarrito/${item.carritoId}/${item.productoId}`, opciones('DELETE'))
            .then(leerRespuesta)
            .then(() => actualizarCarrito())
            .catch((err) => {
                console.error('Error al eliminar el producto', err)
                setError(err.message)
            })
    }

    if (!carritoId) {
        return <AvisoSesion mensaje="Iniciá sesión para ver tu carrito." />
    }

    if (!carrito) {
        return <MensajeError mensaje={error} />
    }

    if (carrito.items.length === 0) {
        return <CarritoVacio />
    }

    return (
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
