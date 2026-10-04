const TituloPagina = ({etiqueta, titulo, resaltado}) => {
    return(
        <div className="mb-space-xl">
            <p className="flex items-center gap-space-sm font-bold text-label-tag uppercase tracking-widest text-primary-container mb-space-sm">
                <span className="w-2 h-2 bg-primary-container"></span>
                {etiqueta}
            </p>
            <h1 className="font-headline font-black uppercase text-headline-hero-mobile md:text-headline-hero text-primary">
                {titulo} <span className="text-primary-container">{resaltado}</span>
            </h1>
        </div>
    )
}

export default TituloPagina
