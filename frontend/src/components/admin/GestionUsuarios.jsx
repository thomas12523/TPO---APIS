import { useState } from "react"
import TablaUsuarios from "./tablas/TablaUsuarios"
import UsuariosVacio from "./vacio/UsuariosVacio"
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
        return <UsuariosVacio />
    }

    return <TablaUsuarios usuarios={usuarios} cambiarRol={cambiarRol} cambiarEstado={cambiarEstado} />
}

export default GestionUsuarios
