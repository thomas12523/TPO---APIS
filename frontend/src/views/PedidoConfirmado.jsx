import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import PasosCheckout from "../components/checkout/PasosCheckout"
import ConfirmacionPedido from "../components/pedidos/ConfirmacionPedido"

const PedidoConfirmado = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <PasosCheckout pasoActual={3} />
                <ConfirmacionPedido />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default PedidoConfirmado
