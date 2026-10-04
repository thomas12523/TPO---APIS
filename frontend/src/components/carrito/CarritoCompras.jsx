import { useState } from "react"
import CarritoItem from "./CarritoItem"
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
        return(
            <div className="bg-surface-container-low p-space-xl text-center flex flex-col items-center gap-space-md">
                <span className="material-symbols-outlined text-5xl text-on-surface-variant">shopping_bag</span>
                <p className="text-body-lg text-on-surface-variant">Tu carrito está vacío.</p>
                <a href="/catalogo" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm">Ir al catálogo</a>
            </div>
        )
    }

    return(
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_22rem] gap-space-lg">
            <div className="flex flex-col gap-space-md">
                {
                    carrito.items.map((value, index)=>(
                        <CarritoItem
                        key={value.detalleCarritoId}
                        index={index}
                        productoNombre={value.productoNombre}
                        precioUnitario={value.precioUnitario}
                        cantidad={value.cantidad}
                        subtotal={value.subtotal}
                        cambiarCantidad={cambiarCantidad}
                        eliminarItem={eliminarItem}
                        />
                    ))
                }
            </div>
            <ResumenOrden subtotal={carrito.subtotal} total={carrito.total} />
        </div>
    )
}

export default CarritoCompras
