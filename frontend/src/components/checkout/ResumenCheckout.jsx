import ResumenTotales from "../carrito/ResumenTotales"
import BotonPrincipal from "../ui/BotonPrincipal"

const ResumenCheckout = ({items, subtotal, total}) => {
    return(
        <aside className="bg-surface-container-low p-space-lg flex flex-col gap-space-lg h-fit">
            <div className="flex items-center justify-between border-b border-white/10 pb-space-md">
                <h2 className="font-headline font-extrabold text-headline-sm uppercase text-primary">Resumen del pedido</h2>
                <a href="/carrito" className="font-bold text-label-tag uppercase text-on-surface-variant underline hover:text-primary">Editar</a>
            </div>
            <ul className="flex flex-col gap-space-sm">
                {
                    items.map((value)=>(
                        <li key={value.detalleCarritoId} className="flex justify-between gap-space-md text-body-md">
                            <span className="text-on-surface">{value.cantidad} x {value.productoNombre}</span>
                            <span className="font-bold text-primary">${value.subtotal.toFixed(2)}</span>
                        </li>
                    ))
                }
            </ul>
            <ResumenTotales subtotal={subtotal} total={total} />
            <BotonPrincipal texto="Confirmar y pagar" icono="arrow_forward" type="submit" />
        </aside>
    )
}

export default ResumenCheckout
