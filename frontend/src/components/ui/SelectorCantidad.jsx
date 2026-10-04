const SelectorCantidad = ({cantidad, cambiarCantidad, maximo}) => {
    return(
        <div className="flex items-center bg-surface-container-high">
            <button type="button" onClick={()=>cambiarCantidad(cantidad - 1)} disabled={cantidad <= 1}
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
