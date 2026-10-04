import PedidoDetalle from "./PedidoDetalle"

const PedidoCard = ({pedidoId, numeroPedido, fechaCreacion, estado, total, metodoPago, abierto, alternarDetalle, cancelarPedido}) => {
    return(
        <article className="bg-surface-container-low border border-white/5">
            <div className="flex flex-wrap items-center gap-space-lg p-space-lg">
                <div className="w-14 h-14 flex items-center justify-center bg-surface-container-high text-primary-container">
                    <span className="material-symbols-outlined text-3xl">{estado === 'CANCELADO' ? 'block' : 'local_shipping'}</span>
                </div>
                <div className="flex-1 min-w-48">
                    <div className="flex items-center gap-space-sm">
                        <h3 className="font-headline font-black text-headline-md uppercase text-primary">Pedido #{numeroPedido}</h3>
                        <span className={`font-bold text-label-tag uppercase px-space-sm py-space-xs ${estado === 'CANCELADO' ? 'bg-surface-container-highest text-on-surface-variant' : 'bg-primary-container text-surface-container-lowest'}`}>{estado}</span>
                    </div>
                    <p className="text-body-sm text-on-surface-variant">{fechaCreacion} · {metodoPago.replace('_', ' ')}</p>
                </div>
                <p className="font-headline font-black text-headline-lg text-primary">${total.toFixed(2)}</p>
                <div className="flex gap-space-sm">
                    <button onClick={()=>alternarDetalle(pedidoId)} className="flex items-center gap-space-xs bg-surface-container-high text-primary font-bold text-label-md uppercase px-space-md py-space-sm hover:bg-surface-container-highest">
                        {abierto ? 'Ocultar detalle' : 'Ver detalle'}
                        <span className="material-symbols-outlined">{abierto ? 'expand_less' : 'expand_more'}</span>
                    </button>
                    {estado === 'PENDIENTE' && (
                        <button onClick={()=>cancelarPedido(pedidoId)} className="flex items-center gap-space-xs text-error font-bold text-label-md uppercase px-space-md py-space-sm hover:bg-error-container/30">
                            <span className="material-symbols-outlined">cancel</span>
                            Cancelar
                        </button>
                    )}
                </div>
            </div>
            {abierto && <PedidoDetalle pedidoId={pedidoId} />}
        </article>
    )
}

export default PedidoCard
