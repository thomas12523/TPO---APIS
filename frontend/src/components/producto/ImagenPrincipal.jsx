import { useState } from "react"
import { urlPrincipal } from "../../utils/imagenes"

// Si el producto todavía no tiene imágenes, el back responde 404 y mostramos un ícono en su lugar
const ImagenPrincipal = ({productoId, nombreProducto}) => {

    const [sinImagen, setSinImagen] = useState(false)

    if(sinImagen){
        return(
            <div className="w-full h-full flex items-center justify-center text-on-surface-variant">
                <span className="material-symbols-outlined text-5xl">image</span>
            </div>
        )
    }

    return <img src={urlPrincipal(productoId)} alt={nombreProducto} onError={()=>setSinImagen(true)} className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" />
}

export default ImagenPrincipal
