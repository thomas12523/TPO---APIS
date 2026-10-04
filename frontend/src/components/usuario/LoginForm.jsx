import { useState } from "react"
import Campo from "../ui/Campo"
import CampoContrasenia from "../ui/CampoContrasenia"
import MensajeError from "../ui/MensajeError"
import BotonPrincipal from "../ui/BotonPrincipal"

// Los nombres coinciden con AuthenticationRequest (username, contrasenia)
const credencialesIniciales = {username: '', contrasenia: ''}

const LoginForm = () => {

    const [credenciales, setCredenciales] = useState(credencialesIniciales)
    const [error, setError] = useState('')

    const handleChange = (e) => {setCredenciales({...credenciales, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(credenciales.username.trim() === '' || credenciales.contrasenia.trim() === ''){
            setError('Completá usuario y contraseña')
            return
        }
        setError('')
        // Al conectar: POST /Auth/authenticate, guardamos el accessToken y vamos al inicio
    }

    return(
        <form onSubmit={handleSubmit} className="flex flex-col gap-space-md">
            <Campo label="Nombre de usuario" name="username" value={credenciales.username} onChange={handleChange} placeholder="tu_usuario" />
            <CampoContrasenia label="Contraseña" name="contrasenia" value={credenciales.contrasenia} onChange={handleChange} />
            <MensajeError mensaje={error} />
            <BotonPrincipal texto="Ingresar" icono="login" type="submit" />
        </form>
    )
}

export default LoginForm
