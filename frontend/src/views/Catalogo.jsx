import { useState } from "react"
import { useLocation } from "react-router-dom"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import EncabezadoCatalogo from "../components/catalogo/EncabezadoCatalogo"
import CatalogoProductos from "../components/catalogo/CatalogoProductos"

const Catalogo = () => {

    // El buscador y las categorías del inicio mandan el filtro en el state de la ruta
    const location = useLocation()
    // Sube en uno cada vez que se agrega algo al carrito, así el navbar actualiza el contador
    const [cambiosCarrito, setCambiosCarrito] = useState(0)
    const carritoCambio = () => setCambiosCarrito((valor)=>valor + 1)

    return(
        <>
        <Navbar cambiosCarrito={cambiosCarrito} />
        <main className="pt-20">
            <EncabezadoCatalogo />
            <CatalogoProductos filtroInicial={location.state} carritoCambio={carritoCambio} />
        </main>
        <Footer />
        </>
    )
}

export default Catalogo
