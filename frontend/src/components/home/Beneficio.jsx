const Beneficio = ({icono, titulo, descripcion}) => {
    return(
        <div className="flex items-center gap-space-md bg-surface-container-low p-space-md">
            <div className="w-12 h-12 shrink-0 flex items-center justify-center bg-surface-container-high text-primary-container">
                <span className="material-symbols-outlined">{icono}</span>
            </div>
            <div>
                <h3 className="font-headline font-extrabold text-headline-sm uppercase text-primary">{titulo}</h3>
                <p className="text-body-sm text-on-surface-variant">{descripcion}</p>
            </div>
        </div>
    )
}

export default Beneficio
