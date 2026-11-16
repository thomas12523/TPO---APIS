import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { useDispatch } from "react-redux"
import Precio from "../ui/Precio"
import SelectorCantidad from "../ui/SelectorCantidad"
import BotonPrincipal from "../ui/BotonPrincipal"
import MensajeError from "../ui/MensajeError"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito } from "../../utils/carrito"
import { fetchCarrito, activarAnimacion } from "../../store/slices/carritoSlice"

const InfoProducto = ({ productoId, nombreProducto, categoriaNombre, descripcion, precioUnitario, precioConDescuento, tieneDescuento, stock, carritoCambio }) => {
    const dispatch = useDispatch()
    const [cantidad, setCantidad] = useState(1)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    useEffect(() => {
        setCantidad(1)
        setError('')
    }, [productoId])

    useEffect(() => {
        const temporizador = setTimeout(() => setAviso(''), 2500)
        return () => clearTimeout(temporizador)
    }, [aviso])

    const handleClick = () => {
        if (localStorage.getItem('token') === null) {
            navigate('/login')
            return
        }
        setError('')
        agregarAlCarrito(productoId, cantidad, precioConDescuento)
            .then(() => {
                setAviso('Producto agregado al carrito')
                dispatch(activarAnimacion())
                dispatch(fetchCarrito())
                if (typeof carritoCambio === 'function') {
                    carritoCambio()
                }
            })
            .catch((err) => {
                console.error('Error al agregar al carrito', err)
                setError(err.message)
            })
    }

    return (
        <div className="flex flex-col gap-space-lg">
            <div>
                <span className="inline-block bg-primary-container text-surface-container-lowest font-bold text-label-md uppercase px-space-sm py-space-xs mb-space-md">{categoriaNombre}</span>
                <h1 className="font-headline font-extrabold text-headline-lg uppercase text-on-surface">{nombreProducto}</h1>
                <div className="mt-space-sm">
                    <Precio precioUnitario={precioUnitario} precioConDescuento={precioConDescuento} tieneDescuento={tieneDescuento} tamano="lg" />
                </div>
            </div>

            <p className="text-body-md text-on-surface-variant leading-relaxed">{descripcion}</p>

            <div className="flex items-center gap-space-md pt-space-md border-t border-white/5">
                <SelectorCantidad cantidad={cantidad} cambiarCantidad={setCantidad} maximo={stock} minimo={1} />
                <BotonPrincipal texto="Agregar al carrito" icono="shopping_bag" onClick={handleClick} disabled={stock === 0} />
            </div>

            <MensajeError mensaje={error} />
            <Aviso mensaje={aviso} />
        </div>
    )
}

export default InfoProducto
