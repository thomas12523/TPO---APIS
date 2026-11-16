import { useEffect, useState } from "react"
import TablaUsuarios from "./tablas/TablaUsuarios"
import UsuariosVacio from "./vacio/UsuariosVacio"
import MensajeError from "../ui/MensajeError"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionUsuarios = () => {

    const [usuarios, setUsuarios] = useState([])
    const [error, setError] = useState('')

    useEffect(()=>{
        fetch(`${URL}/Usuario?size=100`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>setUsuarios(data.content))
        .catch((error)=>{
            console.error('Error al cargar los usuarios', error)
            setError('No pudimos cargar los usuarios.')
        })
    }, [])

    const reemplazarUsuario = (usuarioActualizado) => {
        setUsuarios(usuarios.map((value)=>(
            value.usuarioId === usuarioActualizado.usuarioId ? usuarioActualizado : value
        )))
        setError('')
    }

    const cambiarRol = (usuarioId, role) => {
        fetch(`${URL}/Usuario/${usuarioId}/permisos?role=${role}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>reemplazarUsuario(data))
        .catch((error)=>{
            console.error('Error al cambiar el rol', error)
            setError(error.message)
        })
    }

    const cambiarEstado = (usuarioId) => {
        const usuario = usuarios.find((value)=>value.usuarioId === usuarioId)

        fetch(`${URL}/Usuario/${usuarioId}/estado?activo=${!usuario.activo}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>reemplazarUsuario(data))
        .catch((error)=>{
            console.error('Error al cambiar el estado del usuario', error)
            setError(error.message)
        })
    }

    if(usuarios.length === 0){
        return(
            <>
            <MensajeError mensaje={error} />
            <UsuariosVacio />
            </>
        )
    }

    return(
        <>
        <MensajeError mensaje={error} />
        <TablaUsuarios usuarios={usuarios} cambiarRol={cambiarRol} cambiarEstado={cambiarEstado} />
        </>
    )
}

export default GestionUsuarios
