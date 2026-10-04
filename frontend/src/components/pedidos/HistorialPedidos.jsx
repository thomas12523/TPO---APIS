import { useState } from "react"
import FiltroEstados from "./FiltroEstados"
import PedidoCard from "./PedidoCard"
import { pedidosPrueba } from "../../data/datosPrueba"

const HistorialPedidos = () => {

    // Al conectar: GET /Pedido?usuarioId={id}
    const [pedidos, setPedidos] = useState(pedidosPrueba)
    const [filtro, setFiltro] = useState('TODOS')
    const [pedidoAbierto, setPedidoAbierto] = useState(null)

    const alternarDetalle = (pedidoId) => {
        setPedidoAbierto(pedidoAbierto === pedidoId ? null : pedidoId)
    }

    // Al conectar: POST /Pedido/{pedidoId}/cancelar
    const cancelarPedido = (pedidoId) => {
        setPedidos(pedidos.map((value)=>(
            value.pedidoId === pedidoId ? {...value, estado: 'CANCELADO'} : value
        )))
    }

    const pedidosFiltrados = filtro === 'TODOS' ? pedidos : pedidos.filter((value)=>value.estado === filtro)

    return(
        <>
        <FiltroEstados pedidos={pedidos} filtro={filtro} cambiarFiltro={setFiltro} />
        {pedidosFiltrados.length === 0 ? (
            <p className="bg-surface-container-low text-body-lg text-on-surface-variant p-space-xl text-center">No hay pedidos para mostrar.</p>
        ) : (
            <div className="flex flex-col gap-space-md">
                {
                    pedidosFiltrados.map((value)=>(
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
        )}
        </>
    )
}

export default HistorialPedidos
