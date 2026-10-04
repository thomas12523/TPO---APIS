import EstadoBadge from "../ui/EstadoBadge"
import BotonIcono from "../ui/BotonIcono"

const DescuentoFila = ({descuentoId, nombreProducto, porcentaje, fechaInicio, fechaFin, activo, editarDescuento, cambiarEstado}) => {
    return(
        <tr className="border-t border-white/5 text-body-md">
            <td className="px-space-md py-space-sm font-bold text-primary uppercase">{nombreProducto}</td>
            <td className="px-space-md py-space-sm font-bold text-primary-container">{porcentaje}%</td>
            <td className="px-space-md py-space-sm text-on-surface-variant">{fechaInicio} al {fechaFin}</td>
            <td className="px-space-md py-space-sm"><EstadoBadge activo={activo} /></td>
            <td className="px-space-md py-space-sm flex gap-space-xs">
                <BotonIcono icono="edit" titulo="Editar" onClick={()=>editarDescuento(descuentoId)} />
                <BotonIcono icono={activo ? 'visibility_off' : 'visibility'} titulo={activo ? 'Desactivar' : 'Activar'} onClick={()=>cambiarEstado(descuentoId)} />
            </td>
        </tr>
    )
}

export default DescuentoFila
