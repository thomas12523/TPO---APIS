import DescuentoFila from "../DescuentoFila"
import EncabezadoTabla from "../EncabezadoTabla"

const TablaDescuentos = ({descuentos, nombreDe, editarDescuento, cambiarEstado}) => {
    return(
        <div className="bg-surface-container-low overflow-x-auto">
            <table className="w-full">
                <EncabezadoTabla columnas={['Producto', 'Descuento', 'Vigencia', 'Estado', 'Acciones']} />
                <tbody>
                    {
                        descuentos.map((value)=>(
                            <DescuentoFila
                            key={value.descuentoId}
                            descuentoId={value.descuentoId}
                            nombreProducto={nombreDe(value.productoId)}
                            porcentaje={value.porcentaje}
                            fechaInicio={value.fechaInicio}
                            fechaFin={value.fechaFin}
                            activo={value.activo}
                            editarDescuento={editarDescuento}
                            cambiarEstado={cambiarEstado}
                            />
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default TablaDescuentos
