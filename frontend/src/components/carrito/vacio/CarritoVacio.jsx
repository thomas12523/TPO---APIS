import { Link } from "react-router-dom"
const CarritoVacio = () => {
    return(
        <div className="bg-surface-container-low p-space-xl text-center flex flex-col items-center gap-space-md">
            <span className="material-symbols-outlined text-5xl text-on-surface-variant">shopping_bag</span>
            <p className="text-body-lg text-on-surface-variant">Tu carrito está vacío.</p>
            <Link to="/catalogo" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">Ir al catálogo</Link>
        </div>
    )
}

export default CarritoVacio
