import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import TituloPagina from "../components/ui/TituloPagina"
import HistorialPedidos from "../components/pedidos/HistorialPedidos"

const MisPedidos = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Tu historial" titulo="Mis pedidos" resaltado="& compras" />
                <HistorialPedidos />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default MisPedidos
