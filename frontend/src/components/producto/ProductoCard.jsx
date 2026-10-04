import Precio from "../ui/Precio"

const ProductoCard = ({productoId, nombreProducto, categoriaNombre, precioUnitario, precioConDescuento, tieneDescuento, stock, imagenUrl}) => {
    return(
        <article className="group flex flex-col bg-surface-container-low border border-white/5 hover:border-primary-container transition-colors">
            <a href={`/producto/${productoId}`} className="relative aspect-square overflow-hidden bg-surface-container">
                <img src={imagenUrl} alt={nombreProducto} className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" />
                {tieneDescuento && (
                    <span className="absolute top-space-sm left-space-sm bg-on-tertiary-container text-primary font-bold text-label-tag uppercase px-space-sm py-space-xs">Oferta</span>
                )}
                {stock === 0 && (
                    <span className="absolute top-space-sm right-space-sm bg-surface-container-lowest/80 text-on-surface-variant font-bold text-label-tag uppercase px-space-sm py-space-xs">Sin stock</span>
                )}
            </a>

            <div className="flex flex-col flex-1 gap-space-xs p-space-md">
                <p className="font-bold text-label-tag uppercase tracking-widest text-on-surface-variant">{categoriaNombre}</p>
                <a href={`/producto/${productoId}`} className="font-headline font-extrabold text-headline-sm uppercase text-primary line-clamp-2 min-h-10">{nombreProducto}</a>
                <div className="flex items-center justify-between mt-auto pt-space-sm">
                    <Precio precioUnitario={precioUnitario} precioConDescuento={precioConDescuento} tieneDescuento={tieneDescuento} />
                    <button disabled={stock === 0} title="Agregar al carrito" className="w-9 h-9 flex items-center justify-center bg-surface-container-high text-primary hover:bg-primary-container hover:text-surface-container-lowest disabled:opacity-30">
                        <span className="material-symbols-outlined">add</span>
                    </button>
                </div>
            </div>
        </article>
    )
}

export default ProductoCard
