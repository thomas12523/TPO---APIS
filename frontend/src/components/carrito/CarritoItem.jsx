import { Link } from "react-router-dom"
import SelectorCantidad from "../ui/SelectorCantidad"
import ImagenPrincipal from "../producto/ImagenPrincipal"

const CarritoItem = ({index, productoId, productoNombre, precioUnitario, cantidad, subtotal, cambiarCantidad, eliminarItem}) => {
    return(
        <article className="grid grid-cols-[4rem_1fr] md:grid-cols-[4rem_1fr_8rem_8rem_8rem] items-center gap-space-md bg-surface-container-low p-space-md">
            <Link to={`/producto/${productoId}`} className="w-16 h-16 overflow-hidden bg-surface-container-high flex items-center justify-center shrink-0 border border-white/5">
                <ImagenPrincipal productoId={productoId} nombreProducto={productoNombre} />
            </Link>
            <Link to={`/producto/${productoId}`} className="font-headline font-extrabold text-headline-sm uppercase text-primary hover:text-primary-container transition-colors">
                {productoNombre}
            </Link>
            <p className="font-bold text-body-lg text-on-surface-variant">${precioUnitario.toFixed(2)}</p>
            <SelectorCantidad cantidad={cantidad} cambiarCantidad={(nuevaCantidad)=>cambiarCantidad(index, nuevaCantidad)} maximo={99} />
            <div className="text-right">
                <p className="font-headline font-black text-headline-sm text-primary">${subtotal.toFixed(2)}</p>
                <button onClick={()=>eliminarItem(index)} className="inline-flex items-center gap-1 font-bold text-label-tag uppercase text-on-surface-variant hover:text-error">
                    <span className="material-symbols-outlined text-base">delete</span>
                    Eliminar
                </button>
            </div>
        </article>
    )
}

export default CarritoItem
