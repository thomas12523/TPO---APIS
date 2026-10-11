import { useEffect, useState } from "react"
import ProductoForm from "./ProductoForm"
import TablaProductos from "./tablas/TablaProductos"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionProductos = () => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')

    useEffect(()=>{
        fetch(`${URL}/Producto?size=100`)
        .then(leerRespuesta)
        .then((data)=>setProductos(data.content))
        .catch((error)=>console.error('Error al cargar los productos', error))

        fetch(`${URL}/Categories?size=100`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal.
    // Guardamos lo que devuelve el back, que ya trae la categoría y el precio con descuento
    const guardarProducto = (datos) => {
        if(editando === null){
            return fetch(`${URL}/Producto`, opciones('POST', datos))
            .then(leerRespuesta)
            .then((data)=>setProductos([...productos, data]))
        }
        return fetch(`${URL}/Producto/${editando.productoId}`, opciones('PUT', datos))
        .then(leerRespuesta)
        .then((data)=>{
            setProductos(productos.map((value)=>(value.productoId === data.productoId ? data : value)))
            setEditando(null)
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
        <TablaProductos productos={productos} editarProducto={editarProducto} cambiarEstado={cambiarEstado} />
        {/* Al tocar la lapicera, el formulario de edición se abre encima de la tabla */}
        {editando !== null && (
            <Modal cerrar={()=>setEditando(null)}>
                <ProductoForm
                key={editando.productoId}
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
