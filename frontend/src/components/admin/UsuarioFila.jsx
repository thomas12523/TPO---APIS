import EstadoBadge from "../ui/EstadoBadge"
import BotonIcono from "../ui/BotonIcono"

const UsuarioFila = ({usuarioId, nombre, apellido, username, email, role, activo, cambiarRol, cambiarEstado}) => {
    return(
        <tr className="border-t border-white/5 text-body-md">
            <td className="px-space-md py-space-sm">
                <p className="font-bold text-primary uppercase">{nombre} {apellido}</p>
                <p className="text-body-sm text-primary-container">@{username}</p>
            </td>
            <td className="px-space-md py-space-sm text-on-surface-variant">{email}</td>
            <td className="px-space-md py-space-sm">
                <select value={role} onChange={(e)=>cambiarRol(usuarioId, e.target.value)}
                    className="bg-surface-container-high text-on-surface text-body-md px-space-sm py-space-xs border border-white/10 focus:border-primary-container focus:outline-none">
                    <option value="USER">Comprador</option>
                    <option value="ADMIN">Administrador</option>
                </select>
            </td>
            <td className="px-space-md py-space-sm"><EstadoBadge activo={activo} /></td>
            <td className="px-space-md py-space-sm">
                <BotonIcono icono={activo ? 'block' : 'check_circle'} titulo={activo ? 'Desactivar cuenta' : 'Activar cuenta'} onClick={()=>cambiarEstado(usuarioId)} />
            </td>
        </tr>
    )
}

export default UsuarioFila
