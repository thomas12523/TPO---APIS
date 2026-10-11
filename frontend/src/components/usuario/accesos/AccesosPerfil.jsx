import { Link, useNavigate } from "react-router-dom"
import { cerrarSesion } from "../../../utils/sesion"

const AccesosPerfil = () => {

    const navigate = useNavigate()

    const handleClick = () => {
        cerrarSesion()
        navigate('/login')
    }

    return(
        <aside className="flex flex-col gap-space-sm h-fit">
            <Link to="/mis-pedidos" className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                <span className="material-symbols-outlined text-primary-container text-3xl">local_shipping</span>
                <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Mis pedidos</span>
            </Link>
            <button onClick={handleClick} className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                <span className="material-symbols-outlined text-error text-3xl">logout</span>
                <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Cerrar sesión</span>
            </button>
        </aside>
    )
}

export default AccesosPerfil
