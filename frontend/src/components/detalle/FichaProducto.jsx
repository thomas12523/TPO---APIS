import { useState } from "react"
import GaleriaImagenes from "./GaleriaImagenes"
import InfoProducto from "./InfoProducto"
import ProductosRelacionados from "./ProductosRelacionados"
import BreadcrumbProducto from "./breadcrumb/BreadcrumbProducto"
import SeccionResenas from "../resenas/SeccionResenas"
import { imagenesPrueba, productosPrueba } from "../../data/datosPrueba"

const FichaProducto = () => {

    // Al conectar: GET /Producto/{id} y GET /Imagen?productoId={id}
    const [producto, setProducto] = useState(productosPrueba[1])
    const [imagenes, setImagenes] = useState(imagenesPrueba)

    // La imagen principal del producto va primero y después las extra
    const urls = [producto.imagenUrl, ...imagenes.map((value)=>value.imagenUrl)]

    return(
        <>
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <BreadcrumbProducto nombreProducto={producto.nombreProducto} />
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-space-xl">
                <GaleriaImagenes imagenes={urls} nombreProducto={producto.nombreProducto} />
                <InfoProducto
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
