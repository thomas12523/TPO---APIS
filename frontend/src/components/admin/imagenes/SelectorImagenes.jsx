import { useState, useEffect } from "react"
import MensajeError from "../../ui/MensajeError"

const TAMANIO_MAXIMO = 5 * 1024 * 1024

const SelectorImagenes = ({archivos, cambiarArchivos}) => {

    const [error, setError] = useState('')
    const [vistasPrevias, setVistasPrevias] = useState([])

    useEffect(() => {
        const urls = archivos.map((archivo) => URL.createObjectURL(archivo))
        setVistasPrevias(urls)

        return () => {
            urls.forEach((url) => URL.revokeObjectURL(url))
        }
    }, [archivos])

    const handleChange = (e) => {
        const elegidos = [...e.target.files]
        const validos = elegidos.filter((value)=>value.size <= TAMANIO_MAXIMO)
        setError(validos.length === elegidos.length ? '' : 'Algunas imágenes pesan más de 5 MB y no se agregaron')
        cambiarArchivos([...archivos, ...validos])
    }

    const quitar = (indice) => {
        cambiarArchivos(archivos.filter((value, index)=>index !== indice))
    }

    const mover = (desde, hacia) => {
        if (hacia < 0 || hacia >= archivos.length) return
        const reordenados = [...archivos]
        const [movido] = reordenados.splice(desde, 1)
        reordenados.splice(hacia, 0, movido)
        cambiarArchivos(reordenados)
    }

    return(
        <div className="flex flex-col gap-space-xs">
            <div className="flex items-center justify-between">
                <span className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">Agregar Imágenes</span>
            </div>

            <label className="self-start flex items-center gap-space-xs cursor-pointer bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-md py-space-sm border border-white/10 hover:border-primary-container">
                <span className="material-symbols-outlined text-base">add_photo_alternate</span>
                Elegir imágenes
                <input key={archivos.length} type="file" accept="image/*" multiple onChange={handleChange} className="hidden" />
            </label>

            {archivos.length > 0 && (
                <div className="grid grid-cols-2 sm:grid-cols-4 gap-space-sm mt-space-xs">
                    {
                        archivos.map((archivo, index)=>(
                            <div key={index} className={`relative aspect-square bg-surface-container overflow-hidden border-2 ${index === 0 ? 'border-primary-container' : 'border-white/5'}`}>
                                {vistasPrevias[index] ? (
                                    <img src={vistasPrevias[index]} alt={archivo.name} className="w-full h-full object-cover" />
                                ) : (
                                    <div className="w-full h-full flex items-center justify-center p-2 text-center text-body-xs text-on-surface-variant">
                                        {archivo.name}
                                    </div>
                                )}

                                {index === 0 && (
                                    <span className="absolute top-1 left-1 bg-primary-container text-surface-container-lowest font-bold text-label-tag uppercase px-2 py-0.5 tracking-wider shadow z-10">
                                        Principal
                                    </span>
                                )}

                                <button type="button" onClick={()=>quitar(index)} title="Quitar"
                                    className="absolute top-1 right-1 w-6 h-6 flex items-center justify-center bg-surface-container-lowest/80 text-on-surface hover:text-error z-10">
                                    <span className="material-symbols-outlined text-base">close</span>
                                </button>

                                <div className="absolute bottom-1 left-1 right-1 flex items-center justify-between gap-1 z-10">
                                    <button
                                        type="button"
                                        onClick={() => mover(index, index - 1)}
                                        disabled={index === 0}
                                        title="Mover a la izquierda"
                                        className="w-7 h-7 flex items-center justify-center bg-surface-container-lowest/90 text-on-surface hover:bg-primary-container hover:text-surface-container-lowest disabled:opacity-20 disabled:pointer-events-none"
                                    >
                                        <span className="material-symbols-outlined text-base">arrow_back</span>
                                    </button>

                                    {index !== 0 && (
                                        <button
                                            type="button"
                                            onClick={() => mover(index, 0)}
                                            title="Hacer principal (mover a la izquierda)"
                                            className="px-1.5 h-7 flex items-center justify-center bg-surface-container-lowest/90 text-primary font-bold text-label-tag uppercase hover:bg-primary-container hover:text-surface-container-lowest text-[10px]"
                                        >
                                            Hacer principal
                                        </button>
                                    )}

                                    <button
                                        type="button"
                                        onClick={() => mover(index, index + 1)}
                                        disabled={index === archivos.length - 1}
                                        title="Mover a la derecha"
                                        className="w-7 h-7 flex items-center justify-center bg-surface-container-lowest/90 text-on-surface hover:bg-primary-container hover:text-surface-container-lowest disabled:opacity-20 disabled:pointer-events-none"
                                    >
                                        <span className="material-symbols-outlined text-base">arrow_forward</span>
                                    </button>
                                </div>
                            </div>
                        ))
                    }
                </div>
            )}

            <MensajeError mensaje={error} />
        </div>
    )
}

export default SelectorImagenes
