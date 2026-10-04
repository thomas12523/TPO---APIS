import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import FichaProducto from "../components/detalle/FichaProducto"

const DetalleProducto = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20">
            <FichaProducto />
        </main>
        <Footer />
        </>
    )
}

export default DetalleProducto
