const ContadorResultados = ({mostrados, total}) => {
    return(
        <p className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant mb-space-md">
            Mostrando <span className="text-primary">{mostrados}</span> de <span className="text-primary">{total}</span> productos
        </p>
    )
}

export default ContadorResultados
