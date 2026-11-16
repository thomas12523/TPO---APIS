import { useEffect, useState } from "react"
import ProductoCard from "./ProductoCard"
import { obtenerCarrito } from "../../utils/carrito"

const ProductoGrid = ({productos, carritoCambio}) => {

    const [itemsCarrito, setItemsCarrito] = useState([])

    useEffect(()=>{
        if(localStorage.getItem('token') === null || localStorage.getItem('carritoId') === null){
            return
        }
        obtenerCarrito()
        .then((data)=>setItemsCarrito(data.items))
        .catch((error)=>console.error('Error al cargar el carrito', error))
    }, [productos])

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
                    activo={value.activo}
                    cantidadEnCarrito={cantidadEnCarrito(value.productoId)}
                    carritoCambio={carritoCambio}
                    />
                ))
            }
        </div>
    )
}

export default ProductoGrid
