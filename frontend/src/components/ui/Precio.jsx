const Precio = ({precioUnitario, precioConDescuento, tieneDescuento}) => {

    if(!tieneDescuento){
        return <p className="font-headline font-black text-headline-md text-primary">${precioUnitario.toFixed(2)}</p>
    }

    return(
        <div>
            <p className="font-headline font-black text-headline-md text-primary-container">${precioConDescuento.toFixed(2)}</p>
            <p className="text-label-md text-on-surface-variant line-through">${precioUnitario.toFixed(2)}</p>
        </div>
    )
}

export default Precio
