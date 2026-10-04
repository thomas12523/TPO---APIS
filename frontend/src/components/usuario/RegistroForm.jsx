import { useState } from "react"
import Campo from "../ui/Campo"
import CampoContrasenia from "../ui/CampoContrasenia"
import MensajeError from "../ui/MensajeError"
import BotonPrincipal from "../ui/BotonPrincipal"

// Los nombres coinciden con UsuarioRequest
const usuarioInicial = {nombre: '', apellido: '', dni: '', username: '', email: '', contrasenia: ''}

const RegistroForm = () => {

    const [usuario, setUsuario] = useState(usuarioInicial)
    // La confirmación solo se valida en el front, no viaja al back
    const [confirmacion, setConfirmacion] = useState('')
    const [error, setError] = useState('')

    const handleChange = (e) => {setUsuario({...usuario, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(usuario).some((value)=>value.trim() === '')){
            setError('Completá todos los campos')
            return
        }
        if(isNaN(Number(usuario.dni))){
            setError('El DNI tiene que ser un número')
            return
        }
        if(usuario.contrasenia.length < 6){
            setError('La contraseña tiene que tener al menos 6 caracteres')
            return
        }
        if(usuario.contrasenia !== confirmacion){
            setError('Las contraseñas no coinciden')
            return
        }
        setError('')
        // Al conectar: POST /Auth/register con {...usuario, dni: Number(usuario.dni)}
        setUsuario(usuarioInicial)
        setConfirmacion('')
    }

    return(
        <form onSubmit={handleSubmit} className="flex flex-col gap-space-md">
            <div className="grid grid-cols-2 gap-space-md">
                <Campo label="Nombre" name="nombre" value={usuario.nombre} onChange={handleChange} />
                <Campo label="Apellido" name="apellido" value={usuario.apellido} onChange={handleChange} />
                <Campo label="DNI" name="dni" value={usuario.dni} onChange={handleChange} />
                <Campo label="Usuario" name="username" value={usuario.username} onChange={handleChange} />
            </div>
            <Campo label="Correo electrónico" type="email" name="email" value={usuario.email} onChange={handleChange} />
            <div className="grid grid-cols-2 gap-space-md">
                <CampoContrasenia label="Contraseña" name="contrasenia" value={usuario.contrasenia} onChange={handleChange} />
                <CampoContrasenia label="Confirmar" name="confirmacion" value={confirmacion} onChange={(e)=>setConfirmacion(e.target.value)} />
            </div>
            <MensajeError mensaje={error} />
            <BotonPrincipal texto="Crear cuenta" icono="person_add" type="submit" />
        </form>
    )
}

export default RegistroForm
