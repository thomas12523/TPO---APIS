import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import PerfilHeader from "./PerfilHeader"
import PerfilForm from "./PerfilForm"
import AccesosPerfil from "./accesos/AccesosPerfil"
import AvisoSesion from "../ui/AvisoSesion"
import MensajeError from "../ui/MensajeError"
import { URL, opciones, leerRespuesta } from "../../utils/api"
import { cerrarSesion } from "../../utils/sesion"

const PerfilUsuario = () => {

    const [usuario, setUsuario] = useState(null)
    const [error, setError] = useState('')
    const navigate = useNavigate()
    const token = localStorage.getItem('token')

    useEffect(()=>{
        if(token === null){
            return
        }
        fetch(`${URL}/Usuario/me`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>setUsuario(data))
        .catch((error)=>{
            console.error('Error al cargar el perfil', error)
            setError('No pudimos cargar tu perfil. Tu sesión puede haber vencido: cerrá sesión y volvé a entrar.')
        })
    }, [token])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal
    const guardarCambios = (datos) => {
        return fetch(`${URL}/Usuario/me`, opciones('PUT', datos))
        .then(leerRespuesta)
        .then((data)=>{
            // El token tiene adentro el username: si cambió, hay que volver a iniciar sesión
            if(data.username !== usuario.username){
                cerrarSesion()
                navigate('/login')
                return
            }
            setUsuario(data)
        })
    }

    if(token === null){
        return <AvisoSesion mensaje="Iniciá sesión para ver tu perfil." />
    }

    // Si falló la carga mostramos el error, pero dejamos cerrar sesión
    if(error !== ''){
        return(
            <div className="grid grid-cols-1 lg:grid-cols-[1fr_20rem] gap-space-lg">
                <MensajeError mensaje={error} />
                <AccesosPerfil />
            </div>
        )
    }

    // Mientras la promesa está pendiente todavía no hay usuario para mostrar
    if(usuario === null){
        return null
    }

    return(
        <>
        <PerfilHeader
        nombre={usuario.nombre}
        apellido={usuario.apellido}
        username={usuario.username}
        email={usuario.email}
        role={usuario.role}
        />
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_20rem] gap-space-lg">
            <PerfilForm usuario={usuario} guardarCambios={guardarCambios} />
            <AccesosPerfil />
        </div>
        </>
    )
}

export default PerfilUsuario
