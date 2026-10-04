const EstadoBadge = ({activo}) => {
    return(
        <span className={`font-bold text-label-tag uppercase px-space-sm py-space-xs ${activo ? 'bg-primary-container text-surface-container-lowest' : 'bg-surface-container-highest text-on-surface-variant'}`}>
            {activo ? 'Activo' : 'Inactivo'}
        </span>
    )
}

export default EstadoBadge
