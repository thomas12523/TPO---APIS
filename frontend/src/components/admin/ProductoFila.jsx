import { Link } from "react-router-dom"
import EstadoBadge from "../ui/EstadoBadge"
import BotonIcono from "../ui/BotonIcono"
import ImagenPrincipal from "../producto/ImagenPrincipal"

const ProductoFila = ({productoId, nombreProducto, categoriaNombre, precioUnitario, precioConDescuento, tieneDescuento, porcentajeDescuento, stock, activo, editarProducto, cambiarEstado}) => {
    const textoDescuento = tieneDescuento
        ? `${porcentajeDescuento !== undefined && porcentajeDescuento !== null ? porcentajeDescuento : Math.round((1 - precioConDescuento / precioUnitario) * 100)}%`
        : '-'

    return(
        <tr className="border-t border-white/5 text-body-md">
            <td className="px-space-md py-space-sm font-bold text-primary uppercase">
                <div className="flex items-center gap-space-sm">
                    <div className="w-12 h-12 overflow-hidden bg-surface-container-high flex items-center justify-center shrink-0 border border-white/5">
                        <ImagenPrincipal productoId={productoId} nombreProducto={nombreProducto} />
                    </div>
                    <span>{nombreProducto}</span>
                </div>
            </td>
            <td className="px-space-md py-space-sm text-on-surface-variant">{categoriaNombre}</td>
            <td className="px-space-md py-space-sm text-primary">${precioUnitario.toFixed(2)}</td>
            <td className="px-space-md py-space-sm">
                {tieneDescuento ? (
                    <span className="inline-block bg-on-tertiary-container text-primary font-bold text-label-tag px-2 py-0.5 uppercase tracking-wider">
                        {textoDescuento}
                    </span>
                ) : (
                    <span className="text-on-surface-variant font-medium">-</span>
                )}
            </td>
            <td className={`px-space-md py-space-sm font-bold ${stock === 0 ? 'text-error' : 'text-primary'}`}>{stock}</td>
            <td className="px-space-md py-space-sm"><EstadoBadge activo={activo} /></td>
            <td className="px-space-md py-space-sm">
                <div className="flex gap-space-xs">
                    <Link
                        to={`/producto/${productoId}`}
                        title="Ver producto"
                        className="w-9 h-9 inline-flex items-center justify-center bg-surface-container-high text-on-surface-variant hover:text-primary-container"
                    >
                        <span className="material-symbols-outlined text-xl">open_in_new</span>
                    </Link>
                    <BotonIcono icono="edit" titulo="Editar" onClick={()=>editarProducto(productoId)} />
                    <BotonIcono icono={activo ? 'visibility_off' : 'visibility'} titulo={activo ? 'Desactivar' : 'Activar'} onClick={()=>cambiarEstado(productoId)} />
                </div>
            </td>
        </tr>
    )
}

export default ProductoFila
