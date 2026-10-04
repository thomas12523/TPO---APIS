import { useState } from "react"
import ProductoGrid from "../producto/ProductoGrid"
import TituloSeccion from "../ui/TituloSeccion"
import { productosPrueba } from "../../data/datosPrueba"

const ProductosDestacados = () => {

    const [productos, setProductos] = useState(productosPrueba.slice(0, 4))

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="Lo más elegido" titulo="Productos destacados" />
            <ProductoGrid productos={productos} />
        </section>
    )
}

export default ProductosDestacados
