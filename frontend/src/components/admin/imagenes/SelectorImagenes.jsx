import { useState } from "react"
import MensajeError from "../../ui/MensajeError"

// Igual al máximo que acepta el back (spring.servlet.multipart.max-file-size)
const TAMANIO_MAXIMO = 5 * 1024 * 1024

// Los archivos elegidos los guarda el formulario: acá solo se agregan o se quitan
const SelectorImagenes = ({archivos, cambiarArchivos}) => {

    const [error, setError] = useState('')

    // Se pueden elegir varias de una vez y volver a elegir más: se suman a las que ya estaban
    const handleChange = (e) => {
        const elegidos = [...e.target.files]
        const validos = elegidos.filter((value)=>value.size <= TAMANIO_MAXIMO)
        setError(validos.length === elegidos.length ? '' : 'Algunas imágenes pesan más de 5 MB y no se agregaron')
        cambiarArchivos([...archivos, ...validos])
    }

    const quitar = (indice) => {
        cambiarArchivos(archivos.filter((value, index)=>index !== indice))
    }

    return(
        <div className="flex flex-col gap-space-xs">
            <span className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">Imágenes nuevas</span>
            {/* La key cambia con cada selección: el input se vuelve a montar vacío y se puede elegir el mismo archivo otra vez */}
            <label className="self-start flex items-center gap-space-xs cursor-pointer bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-md py-space-sm border border-white/10 hover:border-primary-container">
                <span className="material-symbols-outlined text-base">add_photo_alternate</span>
                Elegir imágenes
                <input key={archivos.length} type="file" accept="image/*" multiple onChange={handleChange} className="hidden" />
            </label>
            {
                archivos.map((value, index)=>(
                    <div key={index} className="flex items-center justify-between gap-space-sm bg-surface-container px-space-md py-space-xs">
                        <span className="text-body-md text-on-surface truncate">{value.name}</span>
                        <button type="button" onClick={()=>quitar(index)} title="Quitar" className="text-on-surface-variant hover:text-error">
                            <span className="material-symbols-outlined text-lg">close</span>
                        </button>
                    </div>
                ))
            }
            <MensajeError mensaje={error} />
        </div>
    )
}

export default SelectorImagenes
