import { useState } from "react"
import FiltrosCatalogo from "./FiltrosCatalogo"
import Paginacion from "./Paginacion"
import ProductoGrid from "../producto/ProductoGrid"
import { categoriasPrueba, productosPrueba } from "../../data/datosPrueba"

const TAMANIO_PAGINA = 6

// Los nombres coinciden con los parámetros de GET /Producto
const filtrosIniciales = {categoriaId: '', nombre: '', precioMin: '', precioMax: ''}

const CatalogoProductos = () => {

    const [productos, setProductos] = useState(productosPrueba)
    const [categorias, setCategorias] = useState(categoriasPrueba)
    const [filtros, setFiltros] = useState(filtrosIniciales)
    const [pagina, setPagina] = useState(0)

    const handleChange = (e) => {
        setFiltros({...filtros, [e.target.name]: e.target.value})
        setPagina(0)
    }

    const limpiarFiltros = () => {
        setFiltros(filtrosIniciales)
        setPagina(0)
    }

    // Por ahora filtramos en el front; al conectar, estos filtros viajan como parámetros al back
    const productosFiltrados = productos.filter((value)=>(
        (filtros.categoriaId === '' || value.categoriaId === Number(filtros.categoriaId)) &&
        value.nombreProducto.toLowerCase().includes(filtros.nombre.trim().toLowerCase()) &&
        (filtros.precioMin === '' || value.precioConDescuento >= Number(filtros.precioMin)) &&
        (filtros.precioMax === '' || value.precioConDescuento <= Number(filtros.precioMax))
    ))

    const totalPaginas = Math.ceil(productosFiltrados.length / TAMANIO_PAGINA)
    const productosPagina = productosFiltrados.slice(pagina * TAMANIO_PAGINA, (pagina + 1) * TAMANIO_PAGINA)

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl grid grid-cols-1 lg:grid-cols-[16rem_1fr] gap-space-lg">
            <FiltrosCatalogo
            categorias={categorias}
            filtros={filtros}
            handleChange={handleChange}
            limpiarFiltros={limpiarFiltros}
            />
            <div>
                <p className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant mb-space-md">
                    Mostrando <span className="text-primary">{productosPagina.length}</span> de <span className="text-primary">{productosFiltrados.length}</span> productos
                </p>
                <ProductoGrid productos={productosPagina} />
                <Paginacion pagina={pagina} totalPaginas={totalPaginas} cambiarPagina={setPagina} />
            </div>
        </section>
    )
}

export default CatalogoProductos
