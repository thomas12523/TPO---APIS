const DatosPedido = ({numeroPedido, estado, metodoPago, total}) => {
    return(
        <dl className="w-full grid grid-cols-2 gap-space-sm text-left">
            <div className="bg-surface-container p-space-md">
                <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Número de pedido</dt>
                <dd className="font-headline font-extrabold text-headline-sm text-primary">{numeroPedido}</dd>
            </div>
            <div className="bg-surface-container p-space-md">
                <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Estado</dt>
                <dd className="font-headline font-extrabold text-headline-sm text-primary-container">{estado}</dd>
            </div>
            <div className="bg-surface-container p-space-md">
                <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Método de pago</dt>
                <dd className="font-headline font-extrabold text-headline-sm text-primary">{metodoPago.replace('_', ' ')}</dd>
            </div>
            <div className="bg-surface-container p-space-md">
                <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Total</dt>
                <dd className="font-headline font-extrabold text-headline-sm text-primary">${total.toFixed(2)}</dd>
            </div>
        </dl>
    )
}

export default DatosPedido
