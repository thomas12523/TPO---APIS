import { useState } from "react"
import CampoContrasenia from "../ui/CampoContrasenia"
import MensajeError from "../ui/MensajeError"

// Los nombres coinciden con ContraseniaRequest (la confirmación solo se usa acá)
const contraseniasIniciales = {contraseniaActual: '', contraseniaNueva: '', confirmacion: ''}

const ContraseniaForm = ({cambiarContrasenia}) => {

    const [contrasenias, setContrasenias] = useState(contraseniasIniciales)
    const [error, setError] = useState('')
    const [guardado, setGuardado] = useState(false)

    const handleChange = (e) => {
        setContrasenias({...contrasenias, [e.target.name]: e.target.value})
        setGuardado(false)
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(contrasenias).some((value)=>value === '')){
            setError('Completá todos los campos')
            return
        }
        if(contrasenias.contraseniaNueva.length < 6){
            setError('La contraseña nueva tiene que tener al menos 6 caracteres')
            return
        }
        if(contrasenias.contraseniaNueva !== contrasenias.confirmacion){
            setError('Las contraseñas nuevas no coinciden')
            return
        }
        if(contrasenias.contraseniaNueva === contrasenias.contraseniaActual){
            setError('La contraseña nueva tiene que ser distinta a la actual')
            return
        }
        setError('')
        cambiarContrasenia({contraseniaActual: contrasenias.contraseniaActual, contraseniaNueva: contrasenias.contraseniaNueva})
        .then(()=>{
            setContrasenias(contraseniasIniciales)
            setGuardado(true)
        })
        .catch((error)=>{
            console.error('Error al cambiar la contraseña', error)
            setError('No se pudo cambiar la contraseña. Revisá que la contraseña actual sea correcta.')
        })
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">Cambiar contraseña</h2>
            <CampoContrasenia label="Contraseña actual" name="contraseniaActual" value={contrasenias.contraseniaActual} onChange={handleChange} />
            <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md">
                <CampoContrasenia label="Contraseña nueva" name="contraseniaNueva" value={contrasenias.contraseniaNueva} onChange={handleChange} />
                <CampoContrasenia label="Repetí la contraseña nueva" name="confirmacion" value={contrasenias.confirmacion} onChange={handleChange} />
            </div>
            <MensajeError mensaje={error} />
            {guardado && <p className="text-body-md text-primary-container">Contraseña actualizada.</p>}
            <div className="flex justify-end">
                <button type="submit" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                    Cambiar contraseña
                </button>
            </div>
        </form>
    )
}

export default ContraseniaForm
