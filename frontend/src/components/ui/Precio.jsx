const Precio = ({precioUnitario, precioConDescuento, tieneDescuento}) => {

    if(!tieneDescuento){
        return <p className="font-headline font-black text-headline-md text-primary">${precioUnitario.toFixed(2)}</p>
    }

    const porcentaje = Math.round((1 - precioConDescuento / precioUnitario) * 100)

    return(
        <div>
            <p className="font-headline font-black text-headline-md text-primary-container">${precioConDescuento.toFixed(2)}</p>
            <p className="flex items-center gap-space-xs text-label-md">
                <span className="text-on-surface-variant line-through">${precioUnitario.toFixed(2)}</span>
                <span className="font-bold text-primary-container">-{porcentaje}%</span>
            </p>
        </div>
    )
}

export default Precio
