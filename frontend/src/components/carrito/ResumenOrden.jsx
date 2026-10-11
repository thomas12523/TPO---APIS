import { Link } from "react-router-dom"
import ResumenTotales from "./ResumenTotales"

const ResumenOrden = ({subtotal, total}) => {
    return(
        <aside className="bg-surface-container-low p-space-lg flex flex-col gap-space-lg h-fit">
            <h2 className="font-headline font-extrabold text-headline-sm uppercase text-primary border-b border-white/10 pb-space-md">Resumen de la orden</h2>
            <ResumenTotales subtotal={subtotal} total={total} />
            <Link to="/checkout" className="flex items-center justify-center gap-space-sm bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase tracking-wider py-space-md hover:bg-primary">
                Finalizar compra
                <span className="material-symbols-outlined">arrow_forward</span>
            </Link>
            <Link to="/catalogo" className="flex items-center justify-center gap-space-sm bg-surface-container-high text-primary font-bold text-label-lg uppercase py-space-sm hover:bg-surface-container-highest">
                <span className="material-symbols-outlined">arrow_back</span>
                Seguir comprando
            </Link>
        </aside>
    )
}

export default ResumenOrden
