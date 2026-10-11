import { Link } from "react-router-dom"

const IconoNav = ({ruta, icono, titulo}) => {
    return(
        <Link to={ruta} title={titulo} className="w-10 h-10 flex items-center justify-center text-on-surface-variant hover:text-on-surface hover:bg-surface-container">
            <span className="material-symbols-outlined">{icono}</span>
        </Link>
    )
}

export default IconoNav
