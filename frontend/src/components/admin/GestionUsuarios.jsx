import { useState } from "react"
import UsuarioFila from "./UsuarioFila"
import EncabezadoTabla from "./EncabezadoTabla"
import { usuariosPrueba } from "../../data/datosPrueba"

const GestionUsuarios = () => {

    // Al conectar: GET /Usuario
    const [usuarios, setUsuarios] = useState(usuariosPrueba)

    // Al conectar: PATCH /Usuario/{id}/permisos
    const cambiarRol = (usuarioId, role) => {
        setUsuarios(usuarios.map((value)=>(
            value.usuarioId === usuarioId ? {...value, role: role} : value
        )))
    }

    // Al conectar: PATCH /Usuario/{id}/estado
    const cambiarEstado = (usuarioId) => {
        setUsuarios(usuarios.map((value)=>(
            value.usuarioId === usuarioId ? {...value, activo: !value.activo} : value
        )))
    }

    if(usuarios.length === 0){
        return <p className="bg-surface-container-low text-body-lg text-on-surface-variant p-space-xl">No hay usuarios registrados.</p>
    }

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

export default GestionUsuarios
