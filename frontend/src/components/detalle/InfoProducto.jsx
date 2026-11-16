import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { useDispatch, useSelector } from "react-redux"
import Precio from "../ui/Precio"
import SelectorCantidad from "../ui/SelectorCantidad"
import BotonPrincipal from "../ui/BotonPrincipal"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito } from "../../utils/carrito"
import { fetchCarrito, activarAnimacion } from "../../store/slices/carritoSlice"

const InfoProducto = ({ productoId, nombreProducto, categoriaNombre, descripcion, precioUnitario, precioConDescuento, tieneDescuento, stock, carritoCambio }) => {
    const dispatch = useDispatch()
    const { role } = useSelector((state) => state.auth)
    const [cantidad, setCantidad] = useState(1)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    useEffect(() => {
        setCantidad(1)
        setError('')
    }, [productoId])

    useEffect(() => {
        if (aviso) {
            const temporizador = setTimeout(() => setAviso(''), 2500)
            return () => clearTimeout(temporizador)
        }
    }, [aviso])

    useEffect(() => {
        if (error) {
            const temporizador = setTimeout(() => setError(''), 2500)
            return () => clearTimeout(temporizador)
        }
    }, [error])

    const handleClick = () => {
        if (localStorage.getItem('token') === null) {
            navigate('/login')
            return
        }
        if (role === 'ADMIN' || localStorage.getItem('role') === 'ADMIN') {
            setError('Los productos son exclusivos para usuarios')
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

            <div className="flex items-center gap-space-xs">
                {stock > 0 ? (
                    <div className="flex flex-wrap items-center gap-space-xs text-body-md">
                        <span className="w-2.5 h-2.5 rounded-full bg-primary-container inline-block"></span>
                        <span className="text-on-surface-variant font-medium">
                            Stock disponible: <strong className="text-primary font-bold">{stock} {stock === 1 ? 'unidad' : 'unidades'}</strong>
                        </span>
                        {stock <= 5 && (
                            <span className="ml-space-xs text-label-tag font-bold uppercase text-on-tertiary-container bg-tertiary-container/20 px-space-xs py-0.5 border border-on-tertiary-container/30">
                                ¡Últimas unidades!
                            </span>
                        )}
                    </div>
                ) : (
                    <div className="flex items-center gap-space-xs text-body-md text-error">
                        <span className="w-2.5 h-2.5 rounded-full bg-error inline-block"></span>
                        <span className="font-bold uppercase tracking-wider">Agotado / Sin stock</span>
                    </div>
                )}
            </div>

            <p className="text-body-md text-on-surface-variant leading-relaxed">{descripcion}</p>

            <div className="flex items-center gap-space-md pt-space-md border-t border-white/5">
                <SelectorCantidad cantidad={cantidad} cambiarCantidad={setCantidad} maximo={stock} minimo={1} />
                <BotonPrincipal texto="Agregar al carrito" icono="shopping_bag" onClick={handleClick} disabled={stock === 0} />
            </div>

            <Aviso mensaje={aviso} />
            <Aviso mensaje={error} tipo="error" />
        </div>
    )
}

export default InfoProducto
