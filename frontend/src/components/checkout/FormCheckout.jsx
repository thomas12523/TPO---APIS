import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import SeleccionMetodoPago from "./metodos/SeleccionMetodoPago"
import ResumenCheckout from "./ResumenCheckout"
import MensajeError from "../ui/MensajeError"
import AvisoSesion from "../ui/AvisoSesion"
import CarritoVacio from "../carrito/vacio/CarritoVacio"
import { URL, opciones, leerRespuesta } from "../../utils/api"
import { obtenerCarrito } from "../../utils/carrito"

const FormCheckout = () => {

    const [carrito, setCarrito] = useState(null)

    const [checkout, setCheckout] = useState({metodoPago: ''})
    const [error, setError] = useState('')
    const navigate = useNavigate()
    const carritoId = localStorage.getItem('carritoId')

    useEffect(()=>{
        if(carritoId === null){
            return
        }
        obtenerCarrito()
        .then((data)=>setCarrito(data))
        .catch((error)=>{
            console.error('Error al cargar el carrito', error)
            setError('No pudimos cargar tu carrito. Probá cerrar sesión y volver a entrar.')
        })
    }, [carritoId])

    const handleChange = (e) => {setCheckout({...checkout, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(checkout.metodoPago === ''){
            setError('Elegí un método de pago')
            return
        }
        setError('')

        fetch(`${URL}/Carrito/${carritoId}/checkout`, opciones('POST', checkout))
        .then(leerRespuesta)
        .then((data)=>{

            navigate('/pedido-confirmado', {state: data})
        })
        .catch((error)=>{
            console.error('Error al confirmar la compra', error)
            setError(error.message)
        })
    }

    if(carritoId === null){
        return <AvisoSesion mensaje="Iniciá sesión para finalizar tu compra." />
    }

    if(carrito === null){
        return <MensajeError mensaje={error} />
    }

    if(carrito.items.length === 0){
        return <CarritoVacio />
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
