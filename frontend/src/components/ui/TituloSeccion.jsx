const TituloSeccion = ({etiqueta, titulo}) => {
    return(
        <div className="mb-space-lg">
            <p className="font-bold text-label-tag uppercase tracking-widest text-primary-container">{etiqueta}</p>
            <h2 className="font-headline font-black text-headline-lg-mobile md:text-headline-lg uppercase text-primary">{titulo}</h2>
        </div>
    )
}

export default TituloSeccion
