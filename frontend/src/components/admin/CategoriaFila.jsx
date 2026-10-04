import EstadoBadge from "../ui/EstadoBadge"
import BotonIcono from "../ui/BotonIcono"

const CategoriaFila = ({id, nombre, activo, editarCategoria, cambiarEstado}) => {
    return(
        <tr className="border-t border-white/5 text-body-md">
            <td className="px-space-md py-space-sm text-on-surface-variant">#{id}</td>
            <td className="px-space-md py-space-sm font-bold text-primary uppercase">{nombre}</td>
            <td className="px-space-md py-space-sm"><EstadoBadge activo={activo} /></td>
            <td className="px-space-md py-space-sm flex gap-space-xs">
                <BotonIcono icono="edit" titulo="Editar" onClick={()=>editarCategoria(id)} />
                <BotonIcono icono={activo ? 'visibility_off' : 'visibility'} titulo={activo ? 'Desactivar' : 'Activar'} onClick={()=>cambiarEstado(id)} />
            </td>
        </tr>
    )
}

export default CategoriaFila
