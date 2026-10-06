import { useState } from "react"
import FiltroEstados from "./FiltroEstados"
import ListaPedidos from "./lista/ListaPedidos"
import PedidosVacio from "./vacio/PedidosVacio"
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
            <PedidosVacio />
        ) : (
            <ListaPedidos
            pedidos={pedidosFiltrados}
            pedidoAbierto={pedidoAbierto}
            alternarDetalle={alternarDetalle}
            cancelarPedido={cancelarPedido}
            />
        )}
        </>
    )
}

export default HistorialPedidos
