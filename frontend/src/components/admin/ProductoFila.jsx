import EstadoBadge from "../ui/EstadoBadge"
import BotonIcono from "../ui/BotonIcono"

const ProductoFila = ({productoId, nombreProducto, categoriaNombre, precioUnitario, stock, activo, editarProducto, cambiarEstado}) => {
    return(
        <tr className="border-t border-white/5 text-body-md">
            <td className="px-space-md py-space-sm font-bold text-primary uppercase">{nombreProducto}</td>
            <td className="px-space-md py-space-sm text-on-surface-variant">{categoriaNombre}</td>
            <td className="px-space-md py-space-sm text-primary">${precioUnitario.toFixed(2)}</td>
            <td className={`px-space-md py-space-sm font-bold ${stock === 0 ? 'text-error' : 'text-primary'}`}>{stock}</td>
            <td className="px-space-md py-space-sm"><EstadoBadge activo={activo} /></td>
            <td className="px-space-md py-space-sm flex gap-space-xs">
                <BotonIcono icono="edit" titulo="Editar" onClick={()=>editarProducto(productoId)} />
                <BotonIcono icono={activo ? 'visibility_off' : 'visibility'} titulo={activo ? 'Desactivar' : 'Activar'} onClick={()=>cambiarEstado(productoId)} />
            </td>
        </tr>
    )
}

export default ProductoFila
