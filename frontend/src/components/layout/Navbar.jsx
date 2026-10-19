import { Link } from "react-router-dom"
import Buscador from "./Buscador"
import IconoNav from "./IconoNav"

const Navbar = () => {

    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')

    return(
        <header className="fixed top-0 left-0 right-0 z-50 bg-surface-container-lowest/90 backdrop-blur-xl shadow-lg">
            <div className="h-20 max-w-7xl mx-auto px-margin md:px-margin-desktop flex items-center justify-between gap-space-md">
                <Link to="/" className="flex items-center gap-space-xs shrink-0">
                    <span className="w-2.5 h-6 bg-primary-container inline-block"></span>
                    <span className="font-headline font-extrabold text-headline-md uppercase text-primary">GYMSTORE</span>
                    <span className="text-primary-container font-light">/</span>
                    <span className="text-on-surface-variant font-bold text-label-md tracking-widest">MARKETPLACE</span>
                </Link>

                <Buscador />

                <nav className="hidden lg:flex items-center gap-space-xs">
                    <Link to="/" className="font-bold text-label-lg uppercase px-space-sm py-2 text-on-surface-variant hover:text-on-surface">Inicio</Link>
                    <Link to="/catalogo" className="font-bold text-label-lg uppercase px-space-sm py-2 text-on-surface-variant hover:text-on-surface">Catálogo</Link>
                </nav>

                {token === null ? (
                    <Link to="/login" className="flex items-center gap-space-xs bg-primary-container text-surface-container-lowest font-bold text-label-md uppercase px-space-md py-space-sm hover:bg-primary">
                        <span className="material-symbols-outlined text-base">login</span>
                        Ingresar
                    </Link>
                ) : (
                    <div className="flex items-center gap-space-sm">
                        {role === 'ADMIN' && (
                            <Link to="/admin/productos" className="hidden sm:flex items-center gap-1.5 px-space-sm py-1.5 bg-surface-container hover:bg-surface-container-high text-primary-container font-bold text-label-tag uppercase tracking-wider">
                                <span className="material-symbols-outlined text-base">admin_panel_settings</span>
                                Panel admin
                            </Link>
                        )}
                        <IconoNav ruta="/mis-pedidos" icono="local_shipping" titulo="Mis pedidos" />
                        <IconoNav ruta="/carrito" icono="shopping_bag" titulo="Carrito" />
                        <Link to="/perfil" title="Mi perfil" className="ml-space-xs w-8 h-8 rounded-full bg-primary flex items-center justify-center">
                            <span className="material-symbols-outlined text-on-primary text-lg">person</span>
                        </Link>
                    </div>
                )}
            </div>
        </header>
    )
}

export default Navbar
