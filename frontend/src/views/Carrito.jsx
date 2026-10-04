import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import TituloPagina from "../components/ui/TituloPagina"
import CarritoCompras from "../components/carrito/CarritoCompras"

const Carrito = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Tu selección" titulo="Carrito de" resaltado="compras" />
                <CarritoCompras />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default Carrito
