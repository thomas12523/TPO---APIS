import { Link } from "react-router-dom"
import { useSelector } from "react-redux"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import TituloPagina from "../components/ui/TituloPagina"
import HistorialPedidos from "../components/pedidos/HistorialPedidos"

const MisPedidos = () => {
    const { role } = useSelector((state) => state.auth)

    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <TituloPagina etiqueta="Tu historial" titulo="Mis pedidos" resaltado="& compras" />
                {role === 'ADMIN' ? (
                    <div className="bg-surface-container-low p-space-xl flex flex-col items-center justify-center gap-space-md text-center max-w-lg mx-auto border border-white/5">
                        <span className="material-symbols-outlined text-primary-container text-6xl">admin_panel_settings</span>
                        <h2 className="font-headline font-extrabold text-headline-sm uppercase text-primary">Acceso restringido</h2>
                        <p className="text-body-lg text-on-surface-variant">Los productos y pedidos son exclusivos para usuarios.</p>
                        <Link to="/admin/pedidos" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary mt-space-sm">
                            Gestionar pedidos de clientes
                        </Link>
                    </div>
                ) : (
                    <HistorialPedidos />
                )}
            </div>
        </main>
        <Footer />
        </>
    )
}

export default MisPedidos
