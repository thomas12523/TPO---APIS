import { useState } from "react"
import DetallePedidoItem from "./DetallePedidoItem"
import { detallesPedidoPrueba } from "../../data/datosPrueba"

const PedidoDetalle = ({pedidoId}) => {

    // Al conectar: GET /DetallePedido?pedidoId={pedidoId}
    const [detalles, setDetalles] = useState(detallesPedidoPrueba.filter((value)=>value.pedidoId === pedidoId))

    if(detalles.length === 0){
        return <p className="text-body-md text-on-surface-variant p-space-md">Este pedido no tiene productos.</p>
    }

    return(
        <div className="bg-surface-container p-space-md overflow-x-auto">
            <table className="w-full text-body-md">
                <thead>
                    <tr className="font-bold text-label-tag uppercase text-on-surface-variant">
                        <th className="text-left pb-space-sm">Producto</th>
                        <th className="text-center pb-space-sm">Cantidad</th>
                        <th className="text-right pb-space-sm">Precio unitario</th>
                        <th className="text-right pb-space-sm">Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        detalles.map((value)=>(
                            <DetallePedidoItem
                            key={value.detallePedidoId}
                            productoNombre={value.productoNombre}
                            cantidad={value.cantidad}
                            precioUnitario={value.precioUnitario}
                            subtotal={value.subtotal}
                            />
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default PedidoDetalle
