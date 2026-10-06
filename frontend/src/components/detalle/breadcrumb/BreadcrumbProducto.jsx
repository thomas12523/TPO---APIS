const BreadcrumbProducto = ({nombreProducto}) => {
    return(
        <p className="font-bold text-label-tag uppercase tracking-widest text-on-surface-variant mb-space-lg">
            <a href="/" className="hover:text-primary">Inicio</a> / <a href="/catalogo" className="hover:text-primary">Catálogo</a> / <span className="text-primary">{nombreProducto}</span>
        </p>
    )
}

export default BreadcrumbProducto
