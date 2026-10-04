import Buscador from "./Buscador"
import IconoNav from "./IconoNav"

const Navbar = () => {
    return(
        <header className="fixed top-0 left-0 right-0 z-50 bg-surface-container-lowest/90 backdrop-blur-xl shadow-lg">
            <div className="h-20 max-w-7xl mx-auto px-margin md:px-margin-desktop flex items-center justify-between gap-space-md">
                <a href="/" className="flex items-center gap-space-xs shrink-0">
                    <span className="w-2.5 h-6 bg-primary-container inline-block"></span>
                    <span className="font-headline font-extrabold text-headline-md uppercase text-primary">GYMSTORE</span>
                    <span className="text-primary-container font-light">/</span>
                    <span className="text-on-surface-variant font-bold text-label-md tracking-widest">MARKETPLACE</span>
                </a>

                <Buscador />

                <nav className="hidden lg:flex items-center gap-space-xs">
                    <a href="/" className="font-bold text-label-lg uppercase px-space-sm py-2 text-on-surface-variant hover:text-on-surface">Inicio</a>
                    <a href="/catalogo" className="font-bold text-label-lg uppercase px-space-sm py-2 text-on-surface-variant hover:text-on-surface">Catálogo</a>
                </nav>

                <div className="flex items-center gap-space-sm">
                    <a href="/admin/productos" className="hidden sm:flex items-center gap-1.5 px-space-sm py-1.5 bg-surface-container hover:bg-surface-container-high text-primary-container font-bold text-label-tag uppercase tracking-wider">
                        <span className="material-symbols-outlined text-base">admin_panel_settings</span>
                        Panel admin
                    </a>
                    <IconoNav href="/mis-pedidos" icono="local_shipping" titulo="Mis pedidos" />
                    <IconoNav href="/carrito" icono="shopping_bag" titulo="Carrito" />
                    <a href="/perfil" title="Mi perfil" className="ml-space-xs w-8 h-8 rounded-full bg-primary flex items-center justify-center">
                        <span className="material-symbols-outlined text-on-primary text-lg">person</span>
                    </a>
                </div>
            </div>
        </header>
    )
}

export default Navbar
