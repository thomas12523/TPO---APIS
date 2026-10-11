import { Link } from "react-router-dom"
const AccionesConfirmacion = () => {
    return(
        <div className="w-full grid grid-cols-1 sm:grid-cols-2 gap-space-sm">
            <Link to="/mis-pedidos" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase py-space-md hover:bg-primary">Ver mis pedidos</Link>
            <Link to="/catalogo" className="bg-surface-container-high text-primary font-bold text-label-lg uppercase py-space-md hover:bg-surface-container-highest">Seguir comprando</Link>
        </div>
    )
}

export default AccionesConfirmacion
