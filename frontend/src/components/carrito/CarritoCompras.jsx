import { useState } from "react"
import CarritoVacio from "./vacio/CarritoVacio"
import ListaItems from "./lista/ListaItems"
import ResumenOrden from "./ResumenOrden"
import { carritoPrueba } from "../../data/datosPrueba"

const CarritoCompras = () => {

    // Al conectar: GET /Carrito?usuarioId={id}
    const [carrito, setCarrito] = useState(carritoPrueba)

    // Al conectar, el back devuelve el carrito con los totales ya calculados
    const actualizarItems = (nuevosItems) => {
        const subtotal = nuevosItems.reduce((acumulado, value) => acumulado + value.subtotal, 0)
        setCarrito({...carrito, items: nuevosItems, subtotal: subtotal, total: subtotal})
    }

    // Al conectar: PUT /DetalleCarrito/{carritoId}/{productoId}
    const cambiarCantidad = (indice, cantidad) => {
        const nuevosItems = carrito.items.map((value, index)=>(
            index === indice ? {...value, cantidad: cantidad, subtotal: cantidad * value.precioUnitario} : value
        ))
        actualizarItems(nuevosItems)
    }

    // Al conectar: DELETE /DetalleCarrito/{carritoId}/{productoId}
    const eliminarItem = (indice) => {
        const nuevosItems = [...carrito.items]
        nuevosItems.splice(indice, 1)
        actualizarItems(nuevosItems)
    }

    if(carrito.items.length === 0){
        return <CarritoVacio />
    }

    return(
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_22rem] gap-space-lg">
            <ListaItems items={carrito.items} cambiarCantidad={cambiarCantidad} eliminarItem={eliminarItem} />
            <ResumenOrden subtotal={carrito.subtotal} total={carrito.total} />
        </div>
    )
}

export default CarritoCompras
