import { Link, useLocation } from "react-router-dom"
import EncabezadoConfirmacion from "./encabezado/EncabezadoConfirmacion"
import DatosPedido from "./datos/DatosPedido"
import AccionesConfirmacion from "./acciones/AccionesConfirmacion"

const ConfirmacionPedido = () => {

    // El checkout nos manda el PedidoResponse que devolvió el back en el state de la ruta
    const location = useLocation()
    const pedido = location.state

    // Si se entra a esta ruta directo (sin pasar por el checkout) no hay pedido para mostrar
    if(pedido === null){
        return(
            <section className="bg-surface-container-low p-space-xl flex flex-col items-center text-center gap-space-md max-w-2xl mx-auto">
                <p className="text-body-lg text-on-surface-variant">No hay un pedido recién confirmado para mostrar.</p>
                <Link to="/mis-pedidos" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">Ver mis pedidos</Link>
            </section>
        )
    }

    return(
        <section className="bg-surface-container-low p-space-xl flex flex-col items-center text-center gap-space-lg max-w-2xl mx-auto">
            <EncabezadoConfirmacion />
            <DatosPedido
            numeroPedido={pedido.numeroPedido}
            estado={pedido.estado}
            metodoPago={pedido.metodoPago}
            total={pedido.total}
            />
            <AccionesConfirmacion />
        </section>
    )
}

export default ConfirmacionPedido
