import { useEffect, useState } from "react"
import ProductoCard from "./ProductoCard"
import { obtenerCarrito } from "../../utils/carrito"

const ProductoGrid = ({productos, carritoCambio}) => {

    // Los items del carrito, para que cada card sepa si su producto ya está agregado
    const [itemsCarrito, setItemsCarrito] = useState([])

    // Lo pedimos de nuevo si cambian los productos (por ejemplo, al pasar a otro producto relacionado)
    useEffect(()=>{
        if(localStorage.getItem('token') === null || localStorage.getItem('carritoId') === null){
            return
        }
        obtenerCarrito()
        .then((data)=>setItemsCarrito(data.items))
        .catch((error)=>console.error('Error al cargar el carrito', error))
    }, [productos])

    // Cuántas unidades de ese producto hay en el carrito (0 si no está)
    const cantidadEnCarrito = (productoId) => {
        const item = itemsCarrito.find((value)=>value.productoId === productoId)
        return item === undefined ? 0 : item.cantidad
    }

    if(productos.length === 0){
        return <p className="text-body-lg text-on-surface-variant py-space-xl">No hay productos para mostrar.</p>
    }

    return(
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-space-md">
            {
                productos.map((value)=>(
                    <ProductoCard
                    key={value.productoId}
                    productoId={value.productoId}
                    nombreProducto={value.nombreProducto}
                    categoriaNombre={value.categoriaNombre}
                    precioUnitario={value.precioUnitario}
                    precioConDescuento={value.precioConDescuento}
                    tieneDescuento={value.tieneDescuento}
                    stock={value.stock}
                    cantidadEnCarrito={cantidadEnCarrito(value.productoId)}
                    carritoCambio={carritoCambio}
                    />
                ))
            }
        </div>
    )
}

export default ProductoGrid
