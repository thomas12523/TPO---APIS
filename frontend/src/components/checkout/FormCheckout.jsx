import { useState } from "react"
import SeleccionMetodoPago from "./metodos/SeleccionMetodoPago"
import ResumenCheckout from "./ResumenCheckout"
import MensajeError from "../ui/MensajeError"
import { carritoPrueba } from "../../data/datosPrueba"

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
                <SeleccionMetodoPago metodoPago={checkout.metodoPago} handleChange={handleChange} />
                <MensajeError mensaje={error} />
            </section>
            <ResumenCheckout items={carrito.items} subtotal={carrito.subtotal} total={carrito.total} />
        </form>
    )
}

export default FormCheckout
