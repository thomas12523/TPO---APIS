import ProductoCard from "./ProductoCard"

const ProductoGrid = ({productos}) => {

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
                    imagenUrl={value.imagenUrl}
                    />
                ))
            }
        </div>
    )
}

export default ProductoGrid
