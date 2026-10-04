import { useState } from "react"
import MensajeError from "../ui/MensajeError"

// Los nombres coinciden con ResenaRequest (puntuacion, comentario)
const resenaInicial = {puntuacion: '5', comentario: ''}

const ResenaForm = ({agregarResena}) => {

    const [resena, setResena] = useState(resenaInicial)
    const [error, setError] = useState('')

    const handleChange = (e) => {setResena({...resena, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(resena.comentario.trim() === ''){
            setError('El comentario no puede estar vacío')
            return
        }
        agregarResena({puntuacion: Number(resena.puntuacion), comentario: resena.comentario.trim()})
        setResena(resenaInicial)
        setError('')
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h3 className="font-headline font-extrabold text-headline-sm uppercase text-primary">Escribí tu reseña</h3>
            <div className="flex flex-col gap-space-xs">
                <label htmlFor="puntuacion" className="font-bold text-label-md uppercase text-on-surface-variant">Puntuación</label>
                <select id="puntuacion" name="puntuacion" value={resena.puntuacion} onChange={handleChange}
                    className="bg-surface-container-high text-on-surface text-body-lg px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none">
                    <option value="5">5 - Excelente</option>
                    <option value="4">4 - Muy bueno</option>
                    <option value="3">3 - Bueno</option>
                    <option value="2">2 - Regular</option>
                    <option value="1">1 - Malo</option>
                </select>
            </div>
            <div className="flex flex-col gap-space-xs">
                <label htmlFor="comentario" className="font-bold text-label-md uppercase text-on-surface-variant">Comentario</label>
                <textarea id="comentario" name="comentario" value={resena.comentario} onChange={handleChange} rows="3"
                    className="bg-surface-container-high text-on-surface text-body-lg px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none" />
            </div>
            <MensajeError mensaje={error} />
            <button type="submit" className="self-start bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-lg py-space-sm border border-white/10 hover:border-primary-container hover:text-primary-container">
                Publicar reseña
            </button>
        </form>
    )
}

export default ResenaForm
