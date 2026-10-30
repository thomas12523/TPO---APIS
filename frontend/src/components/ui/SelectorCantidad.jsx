// minimo es opcional: en las cards vale 0, porque bajar de 1 saca el producto del carrito
const SelectorCantidad = ({cantidad, cambiarCantidad, maximo, minimo = 1}) => {
    return(
        <div className="flex items-center bg-surface-container-high">
            <button type="button" onClick={()=>cambiarCantidad(cantidad - 1)} disabled={cantidad <= minimo}
                className="w-10 h-10 flex items-center justify-center text-primary hover:bg-surface-container-highest disabled:opacity-30">
                <span className="material-symbols-outlined">remove</span>
            </button>
            <span className="w-10 text-center font-headline font-extrabold text-headline-sm text-primary-container">{cantidad}</span>
            <button type="button" onClick={()=>cambiarCantidad(cantidad + 1)} disabled={cantidad >= maximo}
                className="w-10 h-10 flex items-center justify-center text-primary hover:bg-surface-container-highest disabled:opacity-30">
                <span className="material-symbols-outlined">add</span>
            </button>
        </div>
    )
}

export default SelectorCantidad
