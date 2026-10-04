import { useState } from "react"
import ProductoGrid from "../producto/ProductoGrid"
import TituloSeccion from "../ui/TituloSeccion"
import { productosPrueba } from "../../data/datosPrueba"

const ProductosRelacionados = ({productoId, categoriaId}) => {

    // Al conectar: GET /Producto?categoriaId={categoriaId}
    const [productos, setProductos] = useState(productosPrueba)

    const relacionados = productos
        .filter((value)=>value.categoriaId === categoriaId && value.productoId !== productoId)
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
