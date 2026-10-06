import ProductoFila from "../ProductoFila"
import EncabezadoTabla from "../EncabezadoTabla"

const TablaProductos = ({productos, editarProducto, cambiarEstado}) => {
    return(
        <div className="bg-surface-container-low overflow-x-auto">
            <table className="w-full">
                <EncabezadoTabla columnas={['Producto', 'Categoría', 'Precio', 'Stock', 'Estado', 'Acciones']} />
                <tbody>
                    {
                        productos.map((value)=>(
                            <ProductoFila
                            key={value.productoId}
                            productoId={value.productoId}
                            nombreProducto={value.nombreProducto}
                            categoriaNombre={value.categoriaNombre}
                            precioUnitario={value.precioUnitario}
                            stock={value.stock}
                            activo={value.activo}
                            editarProducto={editarProducto}
                            cambiarEstado={cambiarEstado}
                            />
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default TablaProductos
