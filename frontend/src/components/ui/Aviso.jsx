const Aviso = ({mensaje, tipo = "exito"}) => {

    if(!mensaje || mensaje === ''){
        return null
    }

    const esError = tipo === "error"

    return(
        <p className={`fixed bottom-space-lg right-space-lg z-50 flex items-center gap-space-xs font-bold text-label-lg uppercase px-space-md py-space-sm shadow-xl animate-entrar ${
            esError
                ? "bg-error-container text-white border border-error/30"
                : "bg-primary-container text-surface-container-lowest"
        }`}>
            <span className="material-symbols-outlined text-lg">
                {esError ? "error" : "check_circle"}
            </span>
            {mensaje}
        </p>
    )
}

export default Aviso
