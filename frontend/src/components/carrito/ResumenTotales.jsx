const ResumenTotales = ({subtotal, total}) => {
    return(
        <div className="flex flex-col gap-space-sm">
            <div className="flex justify-between text-body-lg">
                <span className="text-on-surface-variant">Subtotal</span>
                <span className="font-bold text-primary">${subtotal.toFixed(2)}</span>
            </div>
            <div className="flex justify-between text-body-lg">
                <span className="text-on-surface-variant">Envío</span>
                <span className="font-bold text-primary-container">Gratis</span>
            </div>
            {subtotal !== total && (
                <div className="flex justify-between text-body-lg">
                    <span className="text-on-surface-variant">Descuento</span>
                    <span className="font-bold text-error">-${(subtotal - total).toFixed(2)}</span>
                </div>
            )}
            <div className="flex items-end justify-between border-t border-white/10 pt-space-md mt-space-sm">
                <span className="font-bold text-label-md uppercase text-on-surface-variant">Total</span>
                <span className="font-headline font-black text-headline-lg text-primary-container">${total.toFixed(2)}</span>
            </div>
        </div>
    )
}

export default ResumenTotales
