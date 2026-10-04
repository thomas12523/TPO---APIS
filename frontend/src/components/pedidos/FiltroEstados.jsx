// PENDIENTE lo pone el checkout y CANCELADO el endpoint de cancelar
const estados = ['TODOS', 'PENDIENTE', 'CANCELADO']

const FiltroEstados = ({pedidos, filtro, cambiarFiltro}) => {
    return(
        <div className="flex flex-wrap gap-space-sm mb-space-lg">
            {
                estados.map((value)=>(
                    <button key={value} onClick={()=>cambiarFiltro(value)}
                        className={`flex items-center gap-space-sm px-space-lg py-space-sm font-bold text-label-lg uppercase
                        ${filtro === value ? 'bg-primary-container text-surface-container-lowest' : 'bg-surface-container-low text-on-surface-variant hover:text-primary'}`}>
                        {value}
                        <span className="text-label-tag">({value === 'TODOS' ? pedidos.length : pedidos.filter((pedido)=>pedido.estado === value).length})</span>
                    </button>
                ))
            }
        </div>
    )
}

export default FiltroEstados
