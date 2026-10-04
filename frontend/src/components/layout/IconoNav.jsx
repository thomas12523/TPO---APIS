const IconoNav = ({href, icono, titulo}) => {
    return(
        <a href={href} title={titulo} className="w-10 h-10 flex items-center justify-center text-on-surface-variant hover:text-on-surface hover:bg-surface-container">
            <span className="material-symbols-outlined">{icono}</span>
        </a>
    )
}

export default IconoNav
