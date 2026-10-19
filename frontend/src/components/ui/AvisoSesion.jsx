import { Link } from "react-router-dom"

const AvisoSesion = ({mensaje}) => {
    return(
        <div className="bg-surface-container-low p-space-xl flex flex-col items-center text-center gap-space-md">
            <span className="material-symbols-outlined text-primary-container text-5xl">lock</span>
            <p className="text-body-lg text-on-surface-variant">{mensaje}</p>
            <Link to="/login" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                Iniciar sesión
            </Link>
        </div>
    )
}

export default AvisoSesion
