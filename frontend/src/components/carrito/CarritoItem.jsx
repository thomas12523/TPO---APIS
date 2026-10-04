import SelectorCantidad from "../ui/SelectorCantidad"

const CarritoItem = ({index, productoNombre, precioUnitario, cantidad, subtotal, cambiarCantidad, eliminarItem}) => {
    return(
        <article className="grid grid-cols-[4rem_1fr] md:grid-cols-[4rem_1fr_8rem_8rem_8rem] items-center gap-space-md bg-surface-container-low p-space-md">
            <div className="w-16 h-16 flex items-center justify-center bg-surface-container-high text-primary-container">
                <span className="material-symbols-outlined text-3xl">fitness_center</span>
            </div>
            <h3 className="font-headline font-extrabold text-headline-sm uppercase text-primary">{productoNombre}</h3>
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
