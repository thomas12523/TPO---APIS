import { useEffect, useState } from "react"
import ProductoForm from "./ProductoForm"
import TablaProductos from "./tablas/TablaProductos"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import Aviso from "../ui/Aviso"
import { URL, opciones, leerRespuesta } from "../../utils/api"
import { subirImagenes } from "../../utils/imagenes"

const GestionProductos = () => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')

    // El aviso se borra solo a los 2,5 segundos
    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

    useEffect(()=>{
        // soloActivos=false para que el admin vea también los productos desactivados
        fetch(`${URL}/Producto?size=100&soloActivos=false`)
        .then(leerRespuesta)
        .then((data)=>setProductos(data.content))
        .catch((error)=>console.error('Error al cargar los productos', error))

        fetch(`${URL}/Categories?size=100`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal.
    // Guardamos lo que devuelve el back, que ya trae la categoría y el precio con descuento.
    // Las imágenes se suben después, porque necesitan el id del producto
    const guardarProducto = (datos, archivos) => {
        if(editando === null){
            return fetch(`${URL}/Producto`, opciones('POST', datos))
            .then(leerRespuesta)
            .then((data)=>subirImagenes(data.productoId, archivos).then(()=>data))
            .then((data)=>{
                setProductos([...productos, data])
                setAviso(`Producto "${data.nombreProducto}" creado correctamente`)
            })
        }
        return fetch(`${URL}/Producto/${editando.productoId}`, opciones('PUT', datos))
        .then(leerRespuesta)
        .then((data)=>subirImagenes(data.productoId, archivos).then(()=>data))
        .then((data)=>{
            setProductos(productos.map((value)=>(value.productoId === data.productoId ? data : value)))
            setEditando(null)
            setAviso(`Cambios de "${data.nombreProducto}" guardados correctamente`)
        })
    }

    const editarProducto = (productoId) => {
        setEditando(productos.find((value)=>value.productoId === productoId))
    }

    const cambiarEstado = (productoId) => {
        const producto = productos.find((value)=>value.productoId === productoId)

        fetch(`${URL}/Producto/${productoId}/estado?activo=${!producto.activo}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>{
            setProductos(productos.map((value)=>(value.productoId === productoId ? data : value)))
            setError('')
            setAviso(`Producto "${data.nombreProducto}" ${data.activo ? 'activado' : 'desactivado'}`)
        })
        .catch((error)=>{
            console.error('Error al cambiar el estado del producto', error)
            setError(error.message)
        })
    }

    return(
        <>
        <ProductoForm inicial={null} categorias={categorias} guardarProducto={guardarProducto} />
        <MensajeError mensaje={error} />
        <Aviso mensaje={aviso} />
        <TablaProductos productos={productos} editarProducto={editarProducto} cambiarEstado={cambiarEstado} />
        {/* Al tocar la lapicera, el formulario de edición se abre encima de la tabla */}
        {editando !== null && (
            <Modal cerrar={()=>setEditando(null)}>
                <ProductoForm
                inicial={editando}
                categorias={categorias}
                guardarProducto={guardarProducto}
                cancelarEdicion={()=>setEditando(null)}
                />
            </Modal>
        )}
        </>
    )
}

export default GestionProductos
