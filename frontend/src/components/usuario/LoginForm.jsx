import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { URL, leerRespuesta } from "../../utils/api"
import { iniciarSesion } from "../../utils/sesion"
import Campo from "../ui/Campo"
import CampoContrasenia from "../ui/CampoContrasenia"
import MensajeError from "../ui/MensajeError"
import BotonPrincipal from "../ui/BotonPrincipal"

const credencialesIniciales = {username: '', contrasenia: ''}

const LoginForm = () => {

    const [credenciales, setCredenciales] = useState(credencialesIniciales)
    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleChange = (e) => {setCredenciales({...credenciales, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(credenciales.username.trim() === '' || credenciales.contrasenia.trim() === ''){
            setError('Completá usuario y contraseña')
            return
        }
        setError('')

        const options = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(credenciales)
        }

        fetch(`${URL}/Auth/authenticate`, options)
        .then(leerRespuesta)
        .then((data)=>iniciarSesion(data.access_token))
        .then(()=>{
            navigate('/')
        })
        .catch((error)=>{
            console.error('Error al iniciar sesión', error)
            setError('Usuario o contraseña incorrectos')
        })
    }

    return(
        <form onSubmit={handleSubmit} className="flex flex-col gap-space-md">
            <Campo label="Nombre de usuario" name="username" value={credenciales.username} onChange={handleChange} placeholder="Ingresá tu usuario" />
            <CampoContrasenia label="Contraseña" name="contrasenia" value={credenciales.contrasenia} onChange={handleChange} placeholder="********" />
            <MensajeError mensaje={error} />
            <BotonPrincipal texto="Ingresar" icono="login" type="submit" />
        </form>
    )
}

export default LoginForm
