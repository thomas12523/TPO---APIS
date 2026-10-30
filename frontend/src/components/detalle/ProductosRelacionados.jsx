import { useEffect, useState } from "react"
import ProductoGrid from "../producto/ProductoGrid"
import TituloSeccion from "../ui/TituloSeccion"
import { URL, leerRespuesta } from "../../utils/api"

const ProductosRelacionados = ({productoId, categoriaId, carritoCambio}) => {

    const [productos, setProductos] = useState([])

    // Traemos 5 de la misma categoría porque uno puede ser el producto que estamos viendo
    useEffect(()=>{
        fetch(`${URL}/Producto?categoriaId=${categoriaId}&size=5`)
        .then(leerRespuesta)
        .then((data)=>setProductos(data.content))
        .catch((error)=>console.error('Error al cargar los productos relacionados', error))
    }, [categoriaId])

    const relacionados = productos
        .filter((value)=>value.productoId !== productoId)
        .slice(0, 4)

    if(relacionados.length === 0){
        return null
    }

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="De la misma categoría" titulo="También te puede interesar" />
            <ProductoGrid productos={relacionados} carritoCambio={carritoCambio} />
        </section>
    )
}

export default ProductosRelacionados
