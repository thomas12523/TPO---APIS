import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import Precio from "../ui/Precio"
import SelectorCantidad from "../ui/SelectorCantidad"
import BotonPrincipal from "../ui/BotonPrincipal"
import MensajeError from "../ui/MensajeError"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito } from "../../utils/carrito"

// carritoCambio viene de la vista: lo llamamos para avisarle que el carrito cambió
const InfoProducto = ({productoId, nombreProducto, categoriaNombre, descripcion, precioUnitario, precioConDescuento, tieneDescuento, stock, carritoCambio}) => {

    const [cantidad, setCantidad] = useState(1)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    // Si entramos a otro producto (por ejemplo, uno relacionado), reiniciamos la cantidad y el error
    useEffect(()=>{
        setCantidad(1)
        setError('')
    }, [productoId])

    // El aviso se borra solo a los 2,5 segundos
    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

    const handleClick = () => {
        if(localStorage.getItem('token') === null){
            navigate('/login')
            return
        }
        setError('')
        agregarAlCarrito(productoId, cantidad, precioConDescuento)
        .then(()=>{
            setAviso('Producto agregado al carrito')
            carritoCambio()
        })
        .catch((error)=>{
            console.error('Error al agregar al carrito', error)
            setError(error.message)
        })
    }

    return(
        <div className="flex flex-col gap-space-lg">
            <div>
                <span className="inline-block bg-primary-container text-surface-container-lowest font-bold text-label-md uppercase px-space-sm py-space-xs mb-space-md">{categoriaNombre}</span>
                <h1 className="font-headline font-black uppercase text-headline-hero-mobile md:text-headline-hero text-primary">{nombreProducto}</h1>
            </div>

            <div className="bg-surface-container-low p-space-lg">
                <Precio precioUnitario={precioUnitario} precioConDescuento={precioConDescuento} tieneDescuento={tieneDescuento} />
                <p className={`font-bold text-label-md uppercase mt-space-sm ${stock > 0 ? 'text-primary-container' : 'text-error'}`}>
                    {stock > 0 ? `${stock} unidades en stock` : 'Sin stock'}
                </p>
            </div>

            <p className="text-body-lg text-on-surface-variant">{descripcion}</p>

            {stock > 0 && (
                <div className="flex items-center gap-space-md">
                    <SelectorCantidad cantidad={cantidad} cambiarCantidad={setCantidad} maximo={stock} />
                    <BotonPrincipal texto="Agregar al carrito" icono="shopping_cart" onClick={handleClick} />
                </div>
            )}
            <MensajeError mensaje={error} />
            <Aviso mensaje={aviso} />
        </div>
    )
}

export default InfoProducto
