import { useState } from "react"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import FichaProducto from "../components/detalle/FichaProducto"

const DetalleProducto = () => {

    // Sube en uno cada vez que se agrega algo al carrito, así el navbar actualiza el contador
    const [cambiosCarrito, setCambiosCarrito] = useState(0)
    const carritoCambio = () => setCambiosCarrito((valor)=>valor + 1)

    return(
        <>
        <Navbar cambiosCarrito={cambiosCarrito} />
        <main className="pt-20">
            <FichaProducto carritoCambio={carritoCambio} />
        </main>
        <Footer />
        </>
    )
}

export default DetalleProducto
