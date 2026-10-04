const BotonPrincipal = ({texto, icono, type = 'button', onClick}) => {
    return(
        <button type={type} onClick={onClick}
            className="w-full flex items-center justify-center gap-space-sm bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase tracking-wider px-space-lg py-space-md hover:bg-primary">
            {texto}
            {icono && <span className="material-symbols-outlined">{icono}</span>}
        </button>
    )
}

export default BotonPrincipal
