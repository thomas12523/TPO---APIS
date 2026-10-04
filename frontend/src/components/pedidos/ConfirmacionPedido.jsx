import { useState } from "react"
import { pedidosPrueba } from "../../data/datosPrueba"

const ConfirmacionPedido = () => {

    // Al conectar: es el PedidoResponse que devuelve el checkout
    const [pedido, setPedido] = useState(pedidosPrueba[0])

    return(
        <section className="bg-surface-container-low p-space-xl flex flex-col items-center text-center gap-space-lg max-w-2xl mx-auto">
            <div className="w-20 h-20 flex items-center justify-center bg-primary-container text-surface-container-lowest">
                <span className="material-symbols-outlined text-5xl">check</span>
            </div>
            <div>
                <h2 className="font-headline font-black text-headline-lg uppercase text-primary">¡Gracias por tu compra!</h2>
                <p className="text-body-lg text-on-surface-variant mt-space-sm">Registramos tu pedido. Te avisamos por mail cuando cambie de estado.</p>
            </div>

            <dl className="w-full grid grid-cols-2 gap-space-sm text-left">
                <div className="bg-surface-container p-space-md">
                    <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Número de pedido</dt>
                    <dd className="font-headline font-extrabold text-headline-sm text-primary">{pedido.numeroPedido}</dd>
                </div>
                <div className="bg-surface-container p-space-md">
                    <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Estado</dt>
                    <dd className="font-headline font-extrabold text-headline-sm text-primary-container">{pedido.estado}</dd>
                </div>
                <div className="bg-surface-container p-space-md">
                    <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Método de pago</dt>
                    <dd className="font-headline font-extrabold text-headline-sm text-primary">{pedido.metodoPago.replace('_', ' ')}</dd>
                </div>
                <div className="bg-surface-container p-space-md">
                    <dt className="font-bold text-label-tag uppercase text-on-surface-variant">Total</dt>
                    <dd className="font-headline font-extrabold text-headline-sm text-primary">${pedido.total.toFixed(2)}</dd>
                </div>
            </dl>

            <div className="w-full grid grid-cols-1 sm:grid-cols-2 gap-space-sm">
                <a href="/mis-pedidos" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase py-space-md hover:bg-primary">Ver mis pedidos</a>
                <a href="/catalogo" className="bg-surface-container-high text-primary font-bold text-label-lg uppercase py-space-md hover:bg-surface-container-highest">Seguir comprando</a>
            </div>
        </section>
    )
}

export default ConfirmacionPedido
