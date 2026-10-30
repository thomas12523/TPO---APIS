import { useEffect, useState } from "react"
import PedidoAdminCard from "./PedidoAdminCard"
import FiltroEstados from "../pedidos/FiltroEstados"
import CampoSelect from "../ui/CampoSelect"
import MensajeError from "../ui/MensajeError"
import Aviso from "../ui/Aviso"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const GestionPedidos = () => {

    const [usuarios, setUsuarios] = useState([])
    // '' = todos los clientes
    const [clienteId, setClienteId] = useState('')
    const [pedidos, setPedidos] = useState([])
    const [filtro, setFiltro] = useState('TODOS')
    const [pedidoAbierto, setPedidoAbierto] = useState(null)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')

    // El aviso se borra solo a los 2,5 segundos
    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

    useEffect(()=>{
        fetch(`${URL}/Usuario?size=100`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>setUsuarios(data.content))
        .catch((error)=>console.error('Error al cargar los usuarios', error))
    }, [])

    // Cada vez que elige otro cliente, pedimos sus pedidos (o los de todos)
    useEffect(()=>{
        const parametroCliente = clienteId === '' ? '' : `&usuarioId=${clienteId}`
        fetch(`${URL}/Pedido?size=100${parametroCliente}`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>{
            // Mostramos primero los más nuevos
            setPedidos([...data.content].reverse())
            setPedidoAbierto(null)
        })
        .catch((error)=>{
            console.error('Error al cargar los pedidos', error)
            setError('No pudimos cargar los pedidos.')
        })
    }, [clienteId])

    const alternarDetalle = (pedidoId) => {
        setPedidoAbierto(pedidoAbierto === pedidoId ? null : pedidoId)
    }

    // El back valida que el cambio esté permitido; si cancela, también devuelve el stock
    const cambiarEstado = (pedidoId, estado) => {
        fetch(`${URL}/Pedido/${pedidoId}/estado?estado=${estado}`, opciones('PATCH'))
        .then(leerRespuesta)
        .then((data)=>{
            setPedidos(pedidos.map((value)=>(value.pedidoId === pedidoId ? data : value)))
            setError('')
            setAviso(`Pedido #${data.numeroPedido} pasó a ${data.estado}`)
        })
        .catch((error)=>{
            console.error('Error al cambiar el estado del pedido', error)
            setError(error.message)
        })
    }

    const nombreCliente = (usuarioId) => {
        const usuario = usuarios.find((value)=>value.usuarioId === usuarioId)
        return usuario === undefined ? `Usuario #${usuarioId}` : `${usuario.nombre} ${usuario.apellido}`
    }

    const pedidosFiltrados = filtro === 'TODOS' ? pedidos : pedidos.filter((value)=>value.estado === filtro)

    return(
        <>
        <div className="bg-surface-container-low p-space-lg">
            <CampoSelect label="Cliente" name="clienteId" value={clienteId} onChange={(e)=>setClienteId(e.target.value)} textoVacio="Todos los clientes"
                opciones={usuarios.map((value)=>({valor: value.usuarioId, texto: `${value.nombre} ${value.apellido} (${value.username})`}))} />
        </div>
        <MensajeError mensaje={error} />
        <Aviso mensaje={aviso} />
        <FiltroEstados pedidos={pedidos} filtro={filtro} cambiarFiltro={setFiltro} />
        {pedidosFiltrados.length === 0 && <p className="text-body-lg text-on-surface-variant">No hay pedidos para mostrar.</p>}
        <div className="flex flex-col gap-space-md">
            {
                pedidosFiltrados.map((value)=>(
                    <PedidoAdminCard
                    key={value.pedidoId}
                    pedidoId={value.pedidoId}
                    numeroPedido={value.numeroPedido}
                    cliente={nombreCliente(value.usuarioId)}
                    fechaCreacion={value.fechaCreacion}
                    estado={value.estado}
                    total={value.total}
                    metodoPago={value.metodoPago}
                    abierto={pedidoAbierto === value.pedidoId}
                    alternarDetalle={alternarDetalle}
                    cambiarEstado={cambiarEstado}
                    />
                ))
            }
        </div>
        </>
    )
}

export default GestionPedidos
