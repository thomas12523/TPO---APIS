import { useEffect, useState } from "react"
import CategoriaForm from "./CategoriaForm"
import TablaCategorias from "./tablas/TablaCategorias"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import Aviso from "../ui/Aviso"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionCategorias = () => {

    const [categorias, setCategorias] = useState([])
    const [editando, setEditando] = useState(null)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')

    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

    useEffect(()=>{

        fetch(`${URL}/Categories?size=100&soloActivos=false`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    const guardarCategoria = (datos) => {
        if(editando === null){
            return fetch(`${URL}/Categories`, opciones('POST', datos))
            .then(leerRespuesta)
            .then((data)=>{
                setCategorias([...categorias, data])
                setAviso(`Categoría "${data.nombre}" creada correctamente`)
            })
        }
        return fetch(`${URL}/Categories/${editando.id}`, opciones('PUT', datos))
        .then(leerRespuesta)
        .then((data)=>{
            setCategorias(categorias.map((value)=>(value.id === data.id ? data : value)))
            setEditando(null)
            setAviso(`Cambios de "${data.nombre}" guardados correctamente`)
        })
    }

    const editarCategoria = (id) => {
        setEditando(categorias.find((value)=>value.id === id))
    }

    const cambiarEstado = (id) => {
        const categoria = categorias.find((value)=>value.id === id)

        fetch(`${URL}/Categories/${id}/estado?activo=${!categoria.activo}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>{
            setCategorias(categorias.map((value)=>(value.id === id ? data : value)))
            setError('')
            setAviso(`Categoría "${data.nombre}" ${data.activo ? 'activada' : 'desactivada'}`)
        })
        .catch((error)=>{
            console.error('Error al cambiar el estado de la categoría', error)
            setError(error.message)
        })
    }

    return(
        <>
        <CategoriaForm inicial={null} guardarCategoria={guardarCategoria} />
        <MensajeError mensaje={error} />
        <Aviso mensaje={aviso} />
        <TablaCategorias categorias={categorias} editarCategoria={editarCategoria} cambiarEstado={cambiarEstado} />

        {editando !== null && (
            <Modal cerrar={()=>setEditando(null)}>
                <CategoriaForm
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
