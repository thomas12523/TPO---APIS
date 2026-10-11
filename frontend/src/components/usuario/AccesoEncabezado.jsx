import { Link } from "react-router-dom"
const AccesoEncabezado = ({activa}) => {
    return(
        <div className="flex flex-col items-center gap-space-md mb-space-lg">
            <Link to="/" className="flex items-center gap-space-xs">
                <span className="material-symbols-outlined text-primary-container text-4xl">fitness_center</span>
                <span className="font-headline font-black text-headline-lg uppercase text-primary">GymStore</span>
            </Link>
            <p className="text-body-md text-on-surface-variant text-center">Equipamiento pesado para atletas de alto rendimiento</p>
            <div className="w-full grid grid-cols-2 bg-surface-container p-space-xs">
                <Link to="/login" className={`text-center font-bold text-label-md uppercase py-space-sm ${activa === 'login' ? 'bg-primary-container text-surface-container-lowest' : 'text-on-surface-variant hover:text-primary'}`}>
                    Iniciar sesión
                </Link>
                <Link to="/registro" className={`text-center font-bold text-label-md uppercase py-space-sm ${activa === 'registro' ? 'bg-primary-container text-surface-container-lowest' : 'text-on-surface-variant hover:text-primary'}`}>
                    Crear cuenta
                </Link>
            </div>
        </div>
    )
}

export default AccesoEncabezado
