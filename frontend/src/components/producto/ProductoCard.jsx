import { Link } from "react-router-dom"
import Precio from "../ui/Precio"
import BotonCarrito from "./BotonCarrito"
import ImagenPrincipal from "./ImagenPrincipal"

// cantidadEnCarrito y carritoCambio se los pasamos al botón del carrito
const ProductoCard = ({productoId, nombreProducto, categoriaNombre, precioUnitario, precioConDescuento, tieneDescuento, stock, cantidadEnCarrito, carritoCambio}) => {
    return(
        <article className="group flex flex-col bg-surface-container-low border border-white/5 hover:border-primary-container transition-colors">
            <Link to={`/producto/${productoId}`} className="relative aspect-square overflow-hidden bg-surface-container">
                <ImagenPrincipal productoId={productoId} nombreProducto={nombreProducto} />
                {tieneDescuento && (
                    <span className="absolute top-space-sm left-space-sm bg-on-tertiary-container text-primary font-bold text-label-tag uppercase px-space-sm py-space-xs">Oferta -{Math.round((1 - precioConDescuento / precioUnitario) * 100)}%</span>
                )}
                {stock === 0 && (
                    <span className="absolute top-space-sm right-space-sm bg-surface-container-lowest/80 text-on-surface-variant font-bold text-label-tag uppercase px-space-sm py-space-xs">Sin stock</span>
                )}
            </Link>

            <div className="flex flex-col flex-1 gap-space-xs p-space-md">
                <p className="font-bold text-label-tag uppercase tracking-widest text-on-surface-variant">{categoriaNombre}</p>
                <Link to={`/producto/${productoId}`} className="font-headline font-extrabold text-headline-sm uppercase text-primary line-clamp-2 min-h-10">{nombreProducto}</Link>
                <div className="flex flex-wrap items-center justify-between gap-space-xs mt-auto pt-space-sm">
                    <Precio precioUnitario={precioUnitario} precioConDescuento={precioConDescuento} tieneDescuento={tieneDescuento} />
                    <BotonCarrito productoId={productoId} precio={precioConDescuento} stock={stock} cantidadEnCarrito={cantidadEnCarrito} carritoCambio={carritoCambio} />
                </div>
            </div>
        </article>
    )
}

export default ProductoCard
