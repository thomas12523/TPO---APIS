import { useState } from "react"
import EncabezadoConfirmacion from "./encabezado/EncabezadoConfirmacion"
import DatosPedido from "./datos/DatosPedido"
import AccionesConfirmacion from "./acciones/AccionesConfirmacion"
import { pedidosPrueba } from "../../data/datosPrueba"

const ConfirmacionPedido = () => {

    // Al conectar: es el PedidoResponse que devuelve el checkout
    const [pedido, setPedido] = useState(pedidosPrueba[0])

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
