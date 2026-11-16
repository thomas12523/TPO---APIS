import { useEffect, useState } from "react"
import MensajeError from "../../ui/MensajeError"
import { obtenerImagenes, borrarImagen, urlImagen, actualizarOrdenImagenes } from "../../../utils/imagenes"

const ImagenesActuales = ({productoId}) => {

    const [imagenes, setImagenes] = useState([])
    const [error, setError] = useState('')

    useEffect(()=>{
        obtenerImagenes(productoId)
        .then((data)=>setImagenes(data))
        .catch((error)=>console.error('Error al cargar las imágenes', error))
    }, [productoId])

    const borrar = (imagenId) => {
        borrarImagen(imagenId)
        .then(()=>{
            setImagenes(imagenes.filter((value)=>value.imagenId !== imagenId))
            setError('')
        })
        .catch((error)=>{
            console.error('Error al borrar la imagen', error)
            setError(error.message)
        })
    }

    const mover = (desde, hacia) => {
        if (hacia < 0 || hacia >= imagenes.length) return
        const reordenadas = [...imagenes]
        const [movida] = reordenadas.splice(desde, 1)
        reordenadas.splice(hacia, 0, movida)
        setImagenes(reordenadas)
        setError('')

        actualizarOrdenImagenes(reordenadas.map((img) => img.imagenId))
            .catch((err) => {
                console.error("Error al actualizar el orden de imágenes", err)
                setError("Error al guardar el nuevo orden de imágenes")
            })
    }

    return(
        <div className="flex flex-col gap-space-xs">
            <div className="flex items-center justify-between">
                <span className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">Imágenes actuales</span>
            </div>
            {imagenes.length === 0 && <p className="text-body-md text-on-surface-variant">Este producto todavía no tiene imágenes.</p>}
            <div className="grid grid-cols-2 sm:grid-cols-4 gap-space-sm">
                {
                    imagenes.map((value, index)=>(
                        <div key={value.imagenId} className={`relative aspect-square bg-surface-container overflow-hidden border-2 ${index === 0 ? 'border-primary-container' : 'border-white/5'}`}>
                            <img src={urlImagen(value)} alt={`Imagen ${index + 1}`} className="w-full h-full object-cover" />

                            {index === 0 && (
                                <span className="absolute top-1 left-1 bg-primary-container text-surface-container-lowest font-bold text-label-tag uppercase px-2 py-0.5 tracking-wider shadow z-10">
                                    Principal
                                </span>
                            )}

                            <button type="button" onClick={()=>borrar(value.imagenId)} title="Borrar imagen"
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
                                    disabled={index === imagenes.length - 1}
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
            <MensajeError mensaje={error} />
        </div>
    )
}

export default ImagenesActuales
