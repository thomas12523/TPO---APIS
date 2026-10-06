import { useState } from "react"
import PerfilHeader from "./PerfilHeader"
import PerfilForm from "./PerfilForm"
import AccesosPerfil from "./accesos/AccesosPerfil"
import { usuarioPrueba } from "../../data/datosPrueba"

const PerfilUsuario = () => {

    // Al conectar: GET /Usuario/me
    const [usuario, setUsuario] = useState(usuarioPrueba)

    // Al conectar: PUT /Usuario/me y guardamos lo que devuelve el back
    const guardarCambios = (datos) => {
        setUsuario({...usuario, ...datos})
    }

    return(
        <>
        <PerfilHeader
        nombre={usuario.nombre}
        apellido={usuario.apellido}
        username={usuario.username}
        email={usuario.email}
        role={usuario.role}
        />
        <div className="grid grid-cols-1 lg:grid-cols-[1fr_20rem] gap-space-lg">
            <PerfilForm usuario={usuario} guardarCambios={guardarCambios} />
            <AccesosPerfil />
        </div>
        </>
    )
}

export default PerfilUsuario
