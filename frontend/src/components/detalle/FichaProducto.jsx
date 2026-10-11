import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import GaleriaImagenes from "./GaleriaImagenes"
import InfoProducto from "./InfoProducto"
import ProductosRelacionados from "./ProductosRelacionados"
import BreadcrumbProducto from "./breadcrumb/BreadcrumbProducto"
import SeccionResenas from "../resenas/SeccionResenas"
import { URL, leerRespuesta } from "../../utils/api"

const FichaProducto = () => {

    // El id sale de la URL: /producto/:productoId
    const { productoId } = useParams()
    const [producto, setProducto] = useState(null)
    const [imagenes, setImagenes] = useState([])
    const [error, setError] = useState('')

    // Se vuelve a ejecutar si cambia el id (por ejemplo, al entrar a un producto relacionado)
    useEffect(()=>{
        fetch(`${URL}/Producto/${productoId}`)
        .then(leerRespuesta)
        .then((data)=>{
            setProducto(data)
            setError('')
        })
        .catch((error)=>{
            console.error('Error al cargar el producto', error)
            setError('No encontramos este producto')
        })

        fetch(`${URL}/Imagen?productoId=${productoId}`)
        .then(leerRespuesta)
        .then((data)=>setImagenes(data.filter((value)=>value.activo)))
        .catch((error)=>console.error('Error al cargar las imágenes', error))
    }, [productoId])

    if(error !== ''){
        return <p className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl text-body-lg text-on-surface-variant">{error}</p>
    }

    // Mientras la promesa está pendiente todavía no hay producto para mostrar
    if(producto === null){
        return null
    }

    // Las imágenes pueden venir como URL o como archivo subido (en base64)
    const urlsExtra = imagenes.map((value)=>(
        value.imagenUrl !== null ? value.imagenUrl : `data:${value.tipoContenido};base64,${value.imagenBase64}`
    ))
    // La imagen principal del producto va primero y después las extra
    const urls = [producto.imagenUrl, ...urlsExtra]

    return(
        <>
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <BreadcrumbProducto nombreProducto={producto.nombreProducto} />
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-space-xl">
                <GaleriaImagenes key={producto.productoId} imagenes={urls} nombreProducto={producto.nombreProducto} />
                <InfoProducto
                key={producto.productoId}
                productoId={producto.productoId}
                nombreProducto={producto.nombreProducto}
                categoriaNombre={producto.categoriaNombre}
                descripcion={producto.descripcion}
                precioUnitario={producto.precioUnitario}
                precioConDescuento={producto.precioConDescuento}
                tieneDescuento={producto.tieneDescuento}
                stock={producto.stock}
                />
            </div>
        </section>
        <SeccionResenas productoId={producto.productoId} />
        <ProductosRelacionados productoId={producto.productoId} categoriaId={producto.categoriaId} />
        </>
    )
}

export default FichaProducto
