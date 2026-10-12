import { useEffect, useState } from "react"
import DescuentoForm from "./DescuentoForm"
import TablaDescuentos from "./tablas/TablaDescuentos"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionDescuentos = () => {

    const [descuentos, setDescuentos] = useState([])
    const [productos, setProductos] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')

    useEffect(()=>{
        fetch(`${URL}/Descuento`)
        .then(leerRespuesta)
        .then((data)=>setDescuentos(data))
        .catch((error)=>console.error('Error al cargar los descuentos', error))

        fetch(`${URL}/Producto?size=100`)
        .then(leerRespuesta)
        .then((data)=>setProductos(data.content))
        .catch((error)=>console.error('Error al cargar los productos', error))
    }, [])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal
    const guardarDescuento = (datos) => {
        if(editando === null){
            return fetch(`${URL}/Descuento`, opciones('POST', {...datos, activo: true}))
            .then(leerRespuesta)
            .then((data)=>setDescuentos([...descuentos, data]))
        }
        return fetch(`${URL}/Descuento/${editando.descuentoId}`, opciones('PUT', {...datos, activo: editando.activo}))
        .then(leerRespuesta)
        .then((data)=>{
            setDescuentos(descuentos.map((value)=>(value.descuentoId === data.descuentoId ? data : value)))
            setEditando(null)
        })
    }

    const editarDescuento = (descuentoId) => {
        setEditando(descuentos.find((value)=>value.descuentoId === descuentoId))
    }

    // Usamos el PUT con activo invertido, porque el PATCH del back solo sirve para desactivar
    const cambiarEstado = (descuentoId) => {
        const descuento = descuentos.find((value)=>value.descuentoId === descuentoId)

        fetch(`${URL}/Descuento/${descuentoId}`, opciones('PUT', {...descuento, activo: !descuento.activo}))
        .then(leerRespuesta)
        .then((data)=>{
            setDescuentos(descuentos.map((value)=>(value.descuentoId === descuentoId ? data : value)))
            setError('')
        })
        .catch((error)=>{
            console.error('Error al cambiar el estado del descuento', error)
            setError(error.message)
        })
    }

    // El DescuentoResponse trae el productoId; buscamos el nombre para mostrarlo
    const nombreDe = (productoId) => {
        const producto = productos.find((value)=>value.productoId === productoId)
        return producto === undefined ? `Producto #${productoId}` : producto.nombreProducto
    }

    return(
        <>
        <DescuentoForm inicial={null} productos={productos} guardarDescuento={guardarDescuento} />
        <MensajeError mensaje={error} />
        <TablaDescuentos descuentos={descuentos} nombreDe={nombreDe} editarDescuento={editarDescuento} cambiarEstado={cambiarEstado} />
        {/* Al tocar la lapicera, el formulario de edición se abre encima de la tabla */}
        {editando !== null && (
            <Modal cerrar={()=>setEditando(null)}>
                <DescuentoForm
                inicial={editando}
                productos={productos}
                guardarDescuento={guardarDescuento}
                cancelarEdicion={()=>setEditando(null)}
                />
            </Modal>
        )}
        </>
    )
}

export default GestionDescuentos
