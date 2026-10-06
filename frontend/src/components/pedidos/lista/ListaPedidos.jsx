import PedidoCard from "../PedidoCard"

const ListaPedidos = ({pedidos, pedidoAbierto, alternarDetalle, cancelarPedido}) => {
    return(
        <div className="flex flex-col gap-space-md">
            {
                pedidos.map((value)=>(
                    <PedidoCard
                    key={value.pedidoId}
                    pedidoId={value.pedidoId}
                    numeroPedido={value.numeroPedido}
                    fechaCreacion={value.fechaCreacion}
                    estado={value.estado}
                    total={value.total}
                    metodoPago={value.metodoPago}
                    abierto={pedidoAbierto === value.pedidoId}
                    alternarDetalle={alternarDetalle}
                    cancelarPedido={cancelarPedido}
                    />
                ))
            }
        </div>
    )
}

export default ListaPedidos
