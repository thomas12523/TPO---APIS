import { useEffect, useState } from "react"
import MensajeError from "../../ui/MensajeError"
import { obtenerImagenes, borrarImagen, urlImagen } from "../../../utils/imagenes"

// Las imágenes que el producto ya tiene subidas (solo al editar). Borrar es inmediato
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

    return(
        <div className="flex flex-col gap-space-xs">
            <span className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">Imágenes actuales</span>
            {imagenes.length === 0 && <p className="text-body-md text-on-surface-variant">Este producto todavía no tiene imágenes.</p>}
            <div className="grid grid-cols-4 gap-space-sm">
                {
                    imagenes.map((value, index)=>(
                        <div key={value.imagenId} className="relative aspect-square bg-surface-container">
                            <img src={urlImagen(value)} alt={`Imagen ${index + 1}`} className="w-full h-full object-cover" />
                            {index === 0 && (
                                <span className="absolute bottom-0 left-0 right-0 bg-primary-container text-surface-container-lowest font-bold text-label-tag uppercase text-center">Principal</span>
                            )}
                            <button type="button" onClick={()=>borrar(value.imagenId)} title="Borrar imagen"
                                className="absolute top-1 right-1 w-6 h-6 flex items-center justify-center bg-surface-container-lowest/80 text-on-surface hover:text-error">
                                <span className="material-symbols-outlined text-base">close</span>
                            </button>
                        </div>
                    ))
                }
            </div>
            <MensajeError mensaje={error} />
        </div>
    )
}

export default ImagenesActuales
