import { useLocation } from "react-router-dom"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import EncabezadoCatalogo from "../components/catalogo/EncabezadoCatalogo"
import CatalogoProductos from "../components/catalogo/CatalogoProductos"

const Catalogo = () => {

    // El buscador y las categorías del inicio mandan el filtro en el state de la ruta
    const location = useLocation()

    return(
        <>
        <Navbar />
        <main className="pt-20">
            <EncabezadoCatalogo />
            <CatalogoProductos filtroInicial={location.state} />
        </main>
        <Footer />
        </>
    )
}

export default Catalogo
