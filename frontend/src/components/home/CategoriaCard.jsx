const CategoriaCard = ({nombre, imagen}) => {
    return(
        <a href="/catalogo" className="group relative h-72 overflow-hidden bg-surface-container-low">
            <img src={imagen} alt={nombre} className="absolute inset-0 w-full h-full object-cover opacity-70 group-hover:scale-105 transition-transform duration-500" />
            <div className="absolute inset-0 bg-gradient-to-t from-surface-container-lowest via-transparent to-transparent"></div>
            <div className="absolute bottom-0 left-0 p-space-md">
                <h3 className="font-headline font-black text-headline-md uppercase text-primary">{nombre}</h3>
                <span className="flex items-center font-bold text-label-tag uppercase tracking-widest text-primary-container">
                    Ver productos
                    <span className="material-symbols-outlined text-sm">chevron_right</span>
                </span>
            </div>
        </a>
    )
}

export default CategoriaCard
