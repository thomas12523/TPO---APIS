const BotonIcono = ({icono, titulo, onClick}) => {
    return(
        <button type="button" onClick={onClick} title={titulo}
            className="w-9 h-9 inline-flex items-center justify-center bg-surface-container-high text-on-surface-variant hover:text-primary-container">
            <span className="material-symbols-outlined text-xl">{icono}</span>
        </button>
    )
}

export default BotonIcono
