const tamanosPrecio = {
    sm: { principal: "text-headline-sm", secundario: "text-label-sm" },
    md: { principal: "text-headline-md", secundario: "text-label-md" },
    lg: { principal: "text-headline-lg", secundario: "text-body-md" },
    xl: { principal: "text-headline-hero-mobile md:text-headline-hero", secundario: "text-body-lg" }
}

const Precio = ({precioUnitario, precioConDescuento, tieneDescuento, tamano = "md"}) => {
    const config = tamanosPrecio[tamano] || { principal: tamano, secundario: "text-label-md" }

    if(!tieneDescuento){
        return <p className={`font-headline font-black text-primary ${config.principal}`}>${precioUnitario.toFixed(2)}</p>
    }

    const porcentaje = Math.round((1 - precioConDescuento / precioUnitario) * 100)

    return(
        <div>
            <p className={`font-headline font-black text-primary-container ${config.principal}`}>${precioConDescuento.toFixed(2)}</p>
            <p className={`flex items-center gap-space-xs ${config.secundario}`}>
                <span className="text-on-surface-variant line-through">${precioUnitario.toFixed(2)}</span>
                <span className="font-bold text-primary-container">-{porcentaje}%</span>
            </p>
        </div>
    )
}

export default Precio
