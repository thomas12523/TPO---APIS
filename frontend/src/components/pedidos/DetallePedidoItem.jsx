const DetallePedidoItem = ({productoNombre, cantidad, precioUnitario, subtotal}) => {
    return(
        <tr className="border-t border-white/5">
            <td className="py-space-sm font-bold text-primary uppercase">{productoNombre}</td>
            <td className="py-space-sm text-center">{cantidad}</td>
            <td className="py-space-sm text-right text-on-surface-variant">${precioUnitario.toFixed(2)}</td>
            <td className="py-space-sm text-right font-bold text-primary">${subtotal.toFixed(2)}</td>
        </tr>
    )
}

export default DetallePedidoItem
