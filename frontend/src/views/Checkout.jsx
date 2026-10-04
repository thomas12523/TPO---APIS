import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import TituloPagina from "../components/ui/TituloPagina"
import PasosCheckout from "../components/checkout/PasosCheckout"
import FormCheckout from "../components/checkout/FormCheckout"

const Checkout = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Último paso" titulo="Finalizar" resaltado="compra" />
                <PasosCheckout pasoActual={2} />
                <FormCheckout />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default Checkout
