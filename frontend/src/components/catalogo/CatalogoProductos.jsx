import { useEffect, useState } from "react"
import FiltrosCatalogo from "./filtros/FiltrosCatalogo"
import Paginacion from "./paginacion/Paginacion"
import ContadorResultados from "./contador/ContadorResultados"
import ProductoGrid from "../producto/ProductoGrid"
import { URL, leerRespuesta } from "../../utils/api"

const TAMANIO_PAGINA = 20

const filtrosIniciales = {categoriaId: '', nombre: '', precioMin: '', precioMax: '', orden: ''}

const CatalogoProductos = ({filtroInicial}) => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])
    const [filtros, setFiltros] = useState({...filtrosIniciales, ...filtroInicial})
    const [pagina, setPagina] = useState(0)
    const [totalPaginas, setTotalPaginas] = useState(0)
    const [totalProductos, setTotalProductos] = useState(0)

    useEffect(()=>{
        fetch(`${URL}/Categories`)
        .then(leerRespuesta)
        .then((data)=>setCategorias(data.content))
        .catch((error)=>console.error('Error al cargar las categorías', error))
    }, [])

    useEffect(()=>{
        setFiltros({...filtrosIniciales, ...filtroInicial})
        setPagina(0)
    }, [filtroInicial])

    useEffect(()=>{

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
