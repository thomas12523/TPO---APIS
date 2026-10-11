import { useEffect, useState } from "react"
import CategoriaForm from "./CategoriaForm"
import TablaCategorias from "./tablas/TablaCategorias"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionCategorias = () => {

    const [categorias, setCategorias] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')

    useEffect(()=>{
        fetch(`${URL}/Categories?size=100`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal
    const guardarCategoria = (datos) => {
        if(editando === null){
            return fetch(`${URL}/Categories`, opciones('POST', datos))
            .then(leerRespuesta)
            .then((data)=>setCategorias([...categorias, data]))
        }
        return fetch(`${URL}/Categories/${editando.id}`, opciones('PUT', datos))
        .then(leerRespuesta)
        .then((data)=>{
            setCategorias(categorias.map((value)=>(value.id === data.id ? data : value)))
            setEditando(null)
        })
    }

    const editarCategoria = (id) => {
        setEditando(categorias.find((value)=>value.id === id))
    }

    // Baja lógica: el back solo permite desactivar (no tiene cómo reactivar una categoría)
    const desactivarCategoria = (id) => {
        fetch(`${URL}/Categories/${id}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>{
            setCategorias(categorias.map((value)=>(value.id === id ? data.data : value)))
            setError('')
        })
        .catch((error)=>{
            console.error('Error al desactivar la categoría', error)
            setError(error.message)
        })
    }

    return(
        <>
        <CategoriaForm inicial={null} guardarCategoria={guardarCategoria} />
        <MensajeError mensaje={error} />
        <TablaCategorias categorias={categorias} editarCategoria={editarCategoria} cambiarEstado={desactivarCategoria} />
        {/* Al tocar la lapicera, el formulario de edición se abre encima de la tabla */}
        {editando !== null && (
            <Modal cerrar={()=>setEditando(null)}>
                <CategoriaForm
                key={editando.id}
                inicial={editando}
                guardarCategoria={guardarCategoria}
                cancelarEdicion={()=>setEditando(null)}
                />
            </Modal>
        )}
        </>
    )
}

export default GestionCategorias
