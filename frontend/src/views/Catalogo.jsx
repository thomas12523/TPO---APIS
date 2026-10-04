import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import EncabezadoCatalogo from "../components/catalogo/EncabezadoCatalogo"
import CatalogoProductos from "../components/catalogo/CatalogoProductos"

const Catalogo = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20">
            <EncabezadoCatalogo />
            <CatalogoProductos />
        </main>
        <Footer />
        </>
    )
}

export default Catalogo
