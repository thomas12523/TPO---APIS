import { useState } from "react"
import DescuentoForm from "./DescuentoForm"
import DescuentoFila from "./DescuentoFila"
import EncabezadoTabla from "./EncabezadoTabla"
import { descuentosPrueba, productosPrueba } from "../../data/datosPrueba"

const GestionDescuentos = () => {

    // Al conectar: GET /Descuento y GET /Producto
    const [descuentos, setDescuentos] = useState(descuentosPrueba)
    const [productos, setProductos] = useState(productosPrueba)
    const [editando, setEditando] = useState(null)

    // Al conectar: POST /Descuento o PUT /Descuento/{id}
    const guardarDescuento = (datos) => {
        if(editando === null){
            setDescuentos([...descuentos, {...datos, descuentoId: descuentos.length + 1, activo: true}])
            return
        }
        setDescuentos(descuentos.map((value)=>(
            value.descuentoId === editando.descuentoId ? {...value, ...datos} : value
        )))
        setEditando(null)
    }

    const editarDescuento = (descuentoId) => {
        setEditando(descuentos.find((value)=>value.descuentoId === descuentoId))
    }

    // Al conectar: PATCH /Descuento/{id}
    const cambiarEstado = (descuentoId) => {
        setDescuentos(descuentos.map((value)=>(
            value.descuentoId === descuentoId ? {...value, activo: !value.activo} : value
        )))
    }

    // El DescuentoResponse trae el productoId; buscamos el nombre para mostrarlo
    const nombreDe = (productoId) => {
        const producto = productos.find((value)=>value.productoId === productoId)
        return producto === undefined ? `Producto #${productoId}` : producto.nombreProducto
    }

    return(
        <>
        <DescuentoForm
        key={editando === null ? 'nuevo' : editando.descuentoId}
        inicial={editando}
        productos={productos}
        guardarDescuento={guardarDescuento}
        cancelarEdicion={()=>setEditando(null)}
        />
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
        </>
    )
}

export default GestionDescuentos
