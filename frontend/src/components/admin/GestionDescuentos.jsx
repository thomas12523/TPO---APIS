import { useEffect, useState } from "react"
import DescuentoForm from "./DescuentoForm"
import TablaDescuentos from "./tablas/TablaDescuentos"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import Aviso from "../ui/Aviso"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionDescuentos = () => {

    const [descuentos, setDescuentos] = useState([])
    const [productos, setProductos] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')

    // El aviso se borra solo a los 2,5 segundos
    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

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
            .then((data)=>{
                setDescuentos([...descuentos, data])
                setAviso(`Descuento del ${data.porcentaje}% para "${nombreDe(data.productoId)}" creado correctamente`)
            })
        }
        return fetch(`${URL}/Descuento/${editando.descuentoId}`, opciones('PUT', {...datos, activo: editando.activo}))
        .then(leerRespuesta)
        .then((data)=>{
            setDescuentos(descuentos.map((value)=>(value.descuentoId === data.descuentoId ? data : value)))
            setEditando(null)
            setAviso(`Cambios del descuento de "${nombreDe(data.productoId)}" guardados correctamente`)
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
            setAviso(`Descuento de "${nombreDe(data.productoId)}" ${data.activo ? 'activado' : 'desactivado'}`)
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
        <Aviso mensaje={aviso} />
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
