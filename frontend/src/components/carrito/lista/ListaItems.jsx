import CarritoItem from "../CarritoItem"

const ListaItems = ({items, cambiarCantidad, eliminarItem}) => {
    return(
        <div className="flex flex-col gap-space-md">
            {
                items.map((value, index)=>(
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
    )
}

export default ListaItems
