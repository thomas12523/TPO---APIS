import { Link } from "react-router-dom"

// contador es opcional: si es mayor a 0 se muestra el numerito arriba del ícono
const IconoNav = ({ruta, icono, titulo, contador}) => {
    return(
        <Link to={ruta} title={titulo} className="relative w-10 h-10 flex items-center justify-center text-on-surface-variant hover:text-on-surface hover:bg-surface-container">
            <span className="material-symbols-outlined">{icono}</span>
            {contador > 0 && (
                // Con la key, cada vez que cambia el número React lo vuelve a montar y se repite la animación
                <span key={contador} className="absolute -top-1 -right-1 min-w-5 h-5 px-1 flex items-center justify-center rounded-full bg-primary-container text-surface-container-lowest font-bold text-label-tag animate-pop">{contador}</span>
            )}
        </Link>
    )
}

export default IconoNav
