import { useEffect, useState } from "react"
import ProductoGrid from "../producto/ProductoGrid"
import TituloSeccion from "../ui/TituloSeccion"
import { URL, leerRespuesta } from "../../utils/api"

const ProductosRelacionados = ({productoId, categoriaId}) => {

    const [productos, setProductos] = useState([])

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
            <ProductoGrid productos={relacionados} />
        </section>
    )
}

export default ProductosRelacionados
