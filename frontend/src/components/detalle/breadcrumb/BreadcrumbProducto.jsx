import { Link } from "react-router-dom"
const BreadcrumbProducto = ({nombreProducto}) => {
    return(
        <p className="font-bold text-label-tag uppercase tracking-widest text-on-surface-variant mb-space-lg">
            <Link to="/" className="hover:text-primary">Inicio</Link> / <Link to="/catalogo" className="hover:text-primary">Catálogo</Link> / <span className="text-primary">{nombreProducto}</span>
        </p>
    )
}

export default BreadcrumbProducto
