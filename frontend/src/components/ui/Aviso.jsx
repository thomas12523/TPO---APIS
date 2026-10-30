const Aviso = ({mensaje}) => {

    if(mensaje === ''){
        return null
    }

    return(
        <p className="fixed bottom-space-lg right-space-lg z-50 flex items-center gap-space-xs bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-md py-space-sm shadow-lg animate-entrar">
            <span className="material-symbols-outlined text-lg">check_circle</span>
            {mensaje}
        </p>
    )
}

export default Aviso
