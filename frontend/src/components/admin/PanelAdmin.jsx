import Navbar from "../layout/Navbar"
import Footer from "../layout/Footer"
import AdminSidebar from "./AdminSidebar"
import TituloPagina from "../ui/TituloPagina"

// Arma la estructura que comparten todas las vistas del admin
const PanelAdmin = ({activa, contenido}) => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Panel de administración" titulo="Gestión de" resaltado={activa} />
                <div className="grid grid-cols-1 lg:grid-cols-[14rem_1fr] gap-space-lg">
                    <AdminSidebar activa={activa} />
                    <div className="flex flex-col gap-space-lg min-w-0">{contenido}</div>
                </div>
            </div>
        </main>
        <Footer />
        </>
    )
}

export default PanelAdmin
