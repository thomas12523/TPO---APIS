import { useEffect, useState } from "react"
import FiltroEstados from "./FiltroEstados"
import ListaPedidos from "./lista/ListaPedidos"
import PedidosVacio from "./vacio/PedidosVacio"
import AvisoSesion from "../ui/AvisoSesion"
import MensajeError from "../ui/MensajeError"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const HistorialPedidos = () => {

    const [pedidos, setPedidos] = useState([])
    const [filtro, setFiltro] = useState('TODOS')
    const [pedidoAbierto, setPedidoAbierto] = useState(null)
    const [error, setError] = useState('')
    const usuarioId = localStorage.getItem('usuarioId')

    useEffect(()=>{
        if(usuarioId === null){
            return
        }
        fetch(`${URL}/Pedido?usuarioId=${usuarioId}&size=50`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>{

            setPedidos([...data.content].reverse())
        })
        .catch((error)=>{
            console.error('Error al cargar los pedidos', error)
            setError('No pudimos cargar tus pedidos. Probá cerrar sesión y volver a entrar.')
        })
    }, [usuarioId])

    const alternarDetalle = (pedidoId) => {
        setPedidoAbierto(pedidoAbierto === pedidoId ? null : pedidoId)
    }

    const cancelarPedido = (pedidoId) => {
        fetch(`${URL}/Pedido/${pedidoId}/cancelar`, opciones('POST'))
        .then(leerRespuesta)
        .then((data)=>{

            setPedidos(pedidos.map((value)=>(
                value.pedidoId === pedidoId ? data : value
            )))
        })
        .catch((error)=>{
            console.error('Error al cancelar el pedido', error)
            setError(error.message)
        })
    }

    if(usuarioId === null){
        return <AvisoSesion mensaje="Iniciá sesión para ver tus pedidos." />
    }

    const pedidosFiltrados = filtro === 'TODOS' ? pedidos : pedidos.filter((value)=>value.estado === filtro)

    return(
        <>
        <MensajeError mensaje={error} />
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
