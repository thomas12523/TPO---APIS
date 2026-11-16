import { Link } from "react-router-dom"
import ImagenPrincipal from "../producto/ImagenPrincipal"

const DetallePedidoItem = ({productoId, productoNombre, cantidad, precioUnitario, subtotal}) => {
    return(
        <tr className="border-t border-white/5">
            <td className="py-space-sm">
                <div className="flex items-center gap-space-sm">
                    <Link to={`/producto/${productoId}`} className="w-12 h-12 overflow-hidden bg-surface-container-high flex items-center justify-center shrink-0 border border-white/5">
                        <ImagenPrincipal productoId={productoId} nombreProducto={productoNombre} />
                    </Link>
                    <Link to={`/producto/${productoId}`} className="font-bold text-primary uppercase hover:text-primary-container transition-colors">
                        {productoNombre}
                    </Link>
                </div>
            </td>
            <td className="py-space-sm text-center">{cantidad}</td>
            <td className="py-space-sm text-right text-on-surface-variant">${precioUnitario.toFixed(2)}</td>
            <td className="py-space-sm text-right font-bold text-primary">${subtotal.toFixed(2)}</td>
        </tr>
    )
}

export default DetallePedidoItem
