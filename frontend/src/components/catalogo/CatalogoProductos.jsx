import { useEffect, useState } from "react"
import FiltrosCatalogo from "./filtros/FiltrosCatalogo"
import Paginacion from "./paginacion/Paginacion"
import ContadorResultados from "./contador/ContadorResultados"
import ProductoGrid from "../producto/ProductoGrid"
import { URL, leerRespuesta } from "../../utils/api"

// Igual al tamaño de página por defecto del back (GET /Producto)
const TAMANIO_PAGINA = 20

// Los nombres coinciden con los parámetros de GET /Producto
const filtrosIniciales = {categoriaId: '', nombre: '', precioMin: '', precioMax: '', orden: ''}

// filtroInicial llega cuando venimos del buscador o de una categoría del inicio
const CatalogoProductos = ({filtroInicial}) => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])
    const [filtros, setFiltros] = useState({...filtrosIniciales, ...filtroInicial})
    const [pagina, setPagina] = useState(0)
    const [totalPaginas, setTotalPaginas] = useState(0)
    const [totalProductos, setTotalProductos] = useState(0)

    // Las categorías se traen una sola vez, cuando el componente se monta
    useEffect(()=>{
        fetch(`${URL}/Categories`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    // Si ya estamos en el catálogo y se busca algo desde el navbar, llega un filtro nuevo:
    // arrancamos los filtros de cero con ese filtro y volvemos a la primera página
    useEffect(()=>{
        setFiltros({...filtrosIniciales, ...filtroInicial})
        setPagina(0)
    }, [filtroInicial])

    // Cada vez que cambian los filtros o la página, le volvemos a pedir los productos al back
    useEffect(()=>{
        // Solo mandamos los filtros que el usuario completó
        const parametros = Object.keys(filtros)
            .filter((value)=>filtros[value].trim() !== '')
            .map((value)=>`&${value}=${encodeURIComponent(filtros[value].trim())}`)
            .join('')

        fetch(`${URL}/Producto?page=${pagina}&size=${TAMANIO_PAGINA}${parametros}`)
        .then(leerRespuesta)
        .then((data)=>{
            setProductos(data.content)
            setTotalPaginas(data.totalPages)
            setTotalProductos(data.totalElements)
        })
        .catch((error)=>console.error('Error al cargar los productos', error))
    }, [filtros, pagina])

    const handleChange = (e) => {
        // Los precios solo aceptan dígitos: si escribe otra cosa, no actualizamos el estado
        const esPrecio = e.target.name === 'precioMin' || e.target.name === 'precioMax'
        if(esPrecio && !/^\d*$/.test(e.target.value)){
            return
        }
        setFiltros({...filtros, [e.target.name]: e.target.value})
        setPagina(0)
    }

    const limpiarFiltros = () => {
        setFiltros(filtrosIniciales)
        setPagina(0)
    }

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl grid grid-cols-1 lg:grid-cols-[16rem_1fr] gap-space-lg">
            <FiltrosCatalogo
            categorias={categorias}
            filtros={filtros}
            handleChange={handleChange}
            limpiarFiltros={limpiarFiltros}
            />
            <div>
                <ContadorResultados mostrados={productos.length} total={totalProductos} />
                <ProductoGrid productos={productos} />
                <Paginacion pagina={pagina} totalPaginas={totalPaginas} cambiarPagina={setPagina} />
            </div>
        </section>
    )
}

export default CatalogoProductos
