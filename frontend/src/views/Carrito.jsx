import { useState } from "react"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import TituloPagina from "../components/ui/TituloPagina"
import CarritoCompras from "../components/carrito/CarritoCompras"

const Carrito = () => {

    // Sube en uno cada vez que cambia el carrito, así el navbar actualiza el contador
    const [cambiosCarrito, setCambiosCarrito] = useState(0)
    const carritoCambio = () => setCambiosCarrito((valor)=>valor + 1)

    return(
        <>
        <Navbar cambiosCarrito={cambiosCarrito} />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Tu selección" titulo="Carrito de" resaltado="compras" />
                <CarritoCompras carritoCambio={carritoCambio} />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default Carrito
