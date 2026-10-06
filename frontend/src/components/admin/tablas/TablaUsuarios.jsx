import UsuarioFila from "../UsuarioFila"
import EncabezadoTabla from "../EncabezadoTabla"

const TablaUsuarios = ({usuarios, cambiarRol, cambiarEstado}) => {
    return(
        <div className="bg-surface-container-low overflow-x-auto">
            <table className="w-full">
                <EncabezadoTabla columnas={['Usuario', 'Email', 'Rol', 'Estado', 'Acciones']} />
                <tbody>
                    {
                        usuarios.map((value)=>(
                            <UsuarioFila
                            key={value.usuarioId}
                            usuarioId={value.usuarioId}
                            nombre={value.nombre}
                            apellido={value.apellido}
                            username={value.username}
                            email={value.email}
                            role={value.role}
                            activo={value.activo}
                            cambiarRol={cambiarRol}
                            cambiarEstado={cambiarEstado}
                            />
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default TablaUsuarios
