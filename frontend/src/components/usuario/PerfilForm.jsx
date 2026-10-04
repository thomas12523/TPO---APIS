import { useState } from "react"
import Campo from "../ui/Campo"
import MensajeError from "../ui/MensajeError"

const PerfilForm = ({usuario, guardarCambios}) => {

    // Los nombres coinciden con UsuarioRequest
    const datosIniciales = {nombre: usuario.nombre, apellido: usuario.apellido, dni: String(usuario.dni), username: usuario.username, email: usuario.email}

    const [datos, setDatos] = useState(datosIniciales)
    const [error, setError] = useState('')
    const [guardado, setGuardado] = useState(false)

    const handleChange = (e) => {
        setDatos({...datos, [e.target.name]: e.target.value})
        setGuardado(false)
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(datos).some((value)=>value.trim() === '')){
            setError('Completá todos los campos')
            return
        }
        if(isNaN(Number(datos.dni))){
            setError('El DNI tiene que ser un número')
            return
        }
        setError('')
        setGuardado(true)
        guardarCambios({...datos, dni: Number(datos.dni)})
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">Editar datos personales</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md">
                <Campo label="Nombre" name="nombre" value={datos.nombre} onChange={handleChange} />
                <Campo label="Apellido" name="apellido" value={datos.apellido} onChange={handleChange} />
                <Campo label="DNI" name="dni" value={datos.dni} onChange={handleChange} />
                <Campo label="Nombre de usuario" name="username" value={datos.username} onChange={handleChange} />
            </div>
            <Campo label="Correo electrónico" type="email" name="email" value={datos.email} onChange={handleChange} />
            <MensajeError mensaje={error} />
            {guardado && <p className="text-body-md text-primary-container">Cambios guardados.</p>}
            <div className="flex justify-end gap-space-sm">
                <button type="button" onClick={()=>{setDatos(datosIniciales); setError('')}} className="bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-surface-container-highest">
                    Cancelar
                </button>
                <button type="submit" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                    Guardar cambios
                </button>
            </div>
        </form>
    )
}

export default PerfilForm
