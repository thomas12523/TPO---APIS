import PedidoDetalle from "../pedidos/PedidoDetalle"
import { iconoEstado, siguientesEstados } from "../pedidos/estados"

// Como la card de Mis pedidos, pero en vez de cancelar el admin elige el próximo estado
const PedidoAdminCard = ({pedidoId, numeroPedido, cliente, fechaCreacion, estado, total, metodoPago, abierto, alternarDetalle, cambiarEstado}) => {

    const siguientes = siguientesEstados[estado] === undefined ? [] : siguientesEstados[estado]

    return(
        <article className="bg-surface-container-low border border-white/5">
            <div className="flex flex-wrap items-center gap-space-lg p-space-lg">
                <div className="w-14 h-14 flex items-center justify-center bg-surface-container-high text-primary-container">
                    <span className="material-symbols-outlined text-3xl">{iconoEstado[estado]}</span>
                </div>
                <div className="flex-1 min-w-48">
                    <div className="flex items-center gap-space-sm">
                        <h3 className="font-headline font-black text-headline-md uppercase text-primary">Pedido #{numeroPedido}</h3>
                        <span className={`font-bold text-label-tag uppercase px-space-sm py-space-xs ${estado === 'CANCELADO' ? 'bg-surface-container-highest text-on-surface-variant' : 'bg-primary-container text-surface-container-lowest'}`}>{estado}</span>
                    </div>
                    <p className="text-body-sm text-on-surface-variant">{cliente} · {fechaCreacion} · {metodoPago.replace('_', ' ')}</p>
                </div>
                <p className="font-headline font-black text-headline-lg text-primary">${total.toFixed(2)}</p>
                <div className="flex flex-wrap gap-space-sm">
                    {siguientes.length === 0 ? (
                        <span className="flex items-center px-space-md py-space-sm font-bold text-label-md uppercase text-on-surface-variant">Estado final</span>
                    ) : (
                        // Siempre muestra "Cambiar a…": al elegir una opción se manda al back y la lista se actualiza
                        <select value="" onChange={(e)=>cambiarEstado(pedidoId, e.target.value)} title="Cambiar estado"
                            className="bg-surface-container-high text-primary font-bold text-label-md uppercase px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none">
                            <option value="" disabled>Cambiar a…</option>
                            {
                                siguientes.map((value)=>(
                                    <option key={value} value={value}>{value}</option>
                                ))
                            }
                        </select>
                    )}
                    <button onClick={()=>alternarDetalle(pedidoId)} className="flex items-center gap-space-xs bg-surface-container-high text-primary font-bold text-label-md uppercase px-space-md py-space-sm hover:bg-surface-container-highest">
                        {abierto ? 'Ocultar detalle' : 'Ver detalle'}
                        <span className="material-symbols-outlined">{abierto ? 'expand_less' : 'expand_more'}</span>
                    </button>
                </div>
            </div>
            {abierto && <PedidoDetalle pedidoId={pedidoId} />}
        </article>
    )
}

export default PedidoAdminCard
