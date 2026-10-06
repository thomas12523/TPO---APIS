import { useState } from "react"
import DescuentoForm from "./DescuentoForm"
import TablaDescuentos from "./tablas/TablaDescuentos"
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
        <TablaDescuentos descuentos={descuentos} nombreDe={nombreDe} editarDescuento={editarDescuento} cambiarEstado={cambiarEstado} />
        </>
    )
}

export default GestionDescuentos
