import { useState } from "react"
import PerfilHeader from "./PerfilHeader"
import PerfilForm from "./PerfilForm"
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
            <aside className="flex flex-col gap-space-sm h-fit">
                <a href="/mis-pedidos" className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                    <span className="material-symbols-outlined text-primary-container text-3xl">local_shipping</span>
                    <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Mis pedidos</span>
                </a>
                <a href="/login" className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                    <span className="material-symbols-outlined text-error text-3xl">logout</span>
                    <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Cerrar sesión</span>
                </a>
            </aside>
        </div>
        </>
    )
}

export default PerfilUsuario
