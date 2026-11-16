import { useEffect, useState } from "react"
import FiltroEstados from "./FiltroEstados"
import ListaPedidos from "./lista/ListaPedidos"
import PedidosVacio from "./vacio/PedidosVacio"
import AvisoSesion from "../ui/AvisoSesion"
import MensajeError from "../ui/MensajeError"
import Modal from "../ui/Modal"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const HistorialPedidos = () => {

    const [pedidos, setPedidos] = useState([])
    const [filtro, setFiltro] = useState('TODOS')
    const [pedidoAbierto, setPedidoAbierto] = useState(null)
    const [pedidoACancelar, setPedidoACancelar] = useState(null)
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

    const confirmarCancelacion = () => {
        if(pedidoACancelar === null){
            return
        }
        fetch(`${URL}/Pedido/${pedidoACancelar}/cancelar`, opciones('POST'))
        .then(leerRespuesta)
        .then((data)=>{
            setPedidos(pedidos.map((value)=>(
                value.pedidoId === pedidoACancelar ? data : value
            )))
            setPedidoACancelar(null)
        })
        .catch((error)=>{
            console.error('Error al cancelar el pedido', error)
            setError(error.message)
            setPedidoACancelar(null)
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
            cancelarPedido={(id)=>setPedidoACancelar(id)}
            />
        )}

        {pedidoACancelar !== null && (
            <Modal cerrar={()=>setPedidoACancelar(null)}>
                <div className="bg-surface-container-low border border-white/10 p-space-xl max-w-md mx-auto text-center">
                    <span className="material-symbols-outlined text-error text-5xl mb-space-sm">warning</span>
                    <h3 className="font-headline font-bold text-headline-sm uppercase text-primary mb-space-md">
                        ¿Estas seguro que quieres cancelar el pedido?
                    </h3>
                    <div className="flex justify-center gap-space-md mt-space-lg">
                        <button
                            type="button"
                            onClick={()=>setPedidoACancelar(null)}
                            className="bg-surface-container-high text-primary font-bold text-label-md uppercase px-space-lg py-space-sm hover:bg-surface-container-highest transition-colors"
                        >
                            Volver
                        </button>
                        <button
                            type="button"
                            onClick={confirmarCancelacion}
                            className="bg-error text-surface-container-lowest font-bold text-label-md uppercase px-space-lg py-space-sm hover:opacity-90 transition-opacity"
                        >
                            Confirmar
                        </button>
                    </div>
                </div>
            </Modal>
        )}
        </>
    )
}

export default HistorialPedidos
