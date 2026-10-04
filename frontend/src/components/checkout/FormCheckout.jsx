import { useState } from "react"
import MetodoPagoOpcion from "./MetodoPagoOpcion"
import ResumenCheckout from "./ResumenCheckout"
import MensajeError from "../ui/MensajeError"
import { carritoPrueba } from "../../data/datosPrueba"

const metodosPago = [
    {valor: 'TARJETA_CREDITO', titulo: 'Tarjeta de crédito', descripcion: 'Hasta 6 cuotas sin interés', icono: 'credit_card'},
    {valor: 'TARJETA_DEBITO', titulo: 'Tarjeta de débito', descripcion: 'Se debita en el momento', icono: 'payments'},
    {valor: 'TRANSFERENCIA', titulo: 'Transferencia', descripcion: 'Te enviamos los datos por mail', icono: 'account_balance'}
]

const FormCheckout = () => {

    // Al conectar: GET /Carrito?usuarioId={id}
    const [carrito, setCarrito] = useState(carritoPrueba)
    // El nombre coincide con CheckoutRequest (metodoPago)
    const [checkout, setCheckout] = useState({metodoPago: ''})
    const [error, setError] = useState('')

    const handleChange = (e) => {setCheckout({...checkout, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(checkout.metodoPago === ''){
            setError('Elegí un método de pago')
            return
        }
        setError('')
        // Al conectar: POST /Carrito/{carritoId}/checkout con {metodoPago} y vamos a la confirmación
    }

    return(
        <form onSubmit={handleSubmit} className="grid grid-cols-1 lg:grid-cols-[1fr_24rem] gap-space-lg">
            <section className="bg-surface-container-low p-space-lg flex flex-col gap-space-md h-fit">
                <h2 className="flex items-center gap-space-sm font-headline font-extrabold text-headline-md uppercase text-primary">
                    <span className="w-2 h-2 bg-primary-container"></span>
                    Medio de pago
                </h2>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-space-sm">
                    {
                        metodosPago.map((value)=>(
                            <MetodoPagoOpcion
                            key={value.valor}
                            valor={value.valor}
                            titulo={value.titulo}
                            descripcion={value.descripcion}
                            icono={value.icono}
                            seleccionado={checkout.metodoPago === value.valor}
                            handleChange={handleChange}
                            />
                        ))
                    }
                </div>
                <MensajeError mensaje={error} />
            </section>
            <ResumenCheckout items={carrito.items} subtotal={carrito.subtotal} total={carrito.total} />
        </form>
    )
}

export default FormCheckout
