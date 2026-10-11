import { useEffect, useState } from "react"
import ProductoGrid from "../producto/ProductoGrid"
import TituloSeccion from "../ui/TituloSeccion"
import { URL, leerRespuesta } from "../../utils/api"

const ProductosDestacados = () => {

    const [productos, setProductos] = useState([])

    useEffect(()=>{
        fetch(`${URL}/Producto?size=4`)
        .then(leerRespuesta)
        .then((data)=>{
            setProductos(data.content)
        })
        .catch((error)=>{
            console.error('Error al cargar los productos destacados', error)
        })
    }, [])

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="Lo más elegido" titulo="Productos destacados" />
            <ProductoGrid productos={productos} />
        </section>
    )
}

export default ProductosDestacados
