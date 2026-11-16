import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { useDispatch } from "react-redux"
import SelectorCantidad from "../ui/SelectorCantidad"
import MensajeError from "../ui/MensajeError"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito, actualizarCantidad, sacarDelCarrito } from "../../utils/carrito"
import { fetchCarrito, activarAnimacion } from "../../store/slices/carritoSlice"

const BotonCarrito = ({ productoId, precio, stock, cantidadEnCarrito, carritoCambio }) => {
    const dispatch = useDispatch()
    const [cantidad, setCantidad] = useState(cantidadEnCarrito)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    useEffect(() => {
        setCantidad(cantidadEnCarrito)
    }, [cantidadEnCarrito])

    useEffect(() => {
        const temporizador = setTimeout(() => setAviso(''), 2500)
        return () => clearTimeout(temporizador)
    }, [aviso])

    const notificarCambio = () => {
        dispatch(fetchCarrito())
        if (typeof carritoCambio === 'function') {
            carritoCambio()
        }
    }

    const agregar = () => {
        if (localStorage.getItem('token') === null) {
            navigate('/login')
            return
        }
        setError('')
        agregarAlCarrito(productoId, 1, precio)
            .then((data) => {
                setCantidad(data.cantidad)
                setAviso('Producto agregado al carrito')
                dispatch(activarAnimacion())
                notificarCambio()
            })
            .catch((err) => {
                console.error('Error al agregar al carrito', err)
                setError(err.message)
            })
    }

    const cambiarCantidad = (nueva) => {
        setError('')
        if (nueva === 0) {
            sacarDelCarrito(productoId)
                .then(() => {
                    setCantidad(0)
                    setAviso('Producto quitado del carrito')
                    notificarCambio()
                })
                .catch((err) => setError(err.message))
            return
        }
        if (nueva > cantidad) {
            dispatch(activarAnimacion())
        }
        actualizarCantidad(productoId, nueva, precio)
            .then(() => {
                setCantidad(nueva)
                notificarCambio()
            })
            .catch((err) => setError(err.message))
    }

    return (
        <>
            {cantidad === 0 ? (
                <button onClick={agregar} disabled={stock === 0} title="Agregar al carrito" className="w-9 h-9 flex items-center justify-center bg-surface-container-high text-primary hover:bg-primary-container hover:text-surface-container-lowest disabled:opacity-30">
                    <span className="material-symbols-outlined">add</span>
                </button>
            ) : (
                <SelectorCantidad cantidad={cantidad} cambiarCantidad={cambiarCantidad} maximo={stock} minimo={0} />
            )}
            {error !== '' && (
                <div className="w-full">
                    <MensajeError mensaje={error} />
                </div>
            )}
            <Aviso mensaje={aviso} />
        </>
    )
}

export default BotonCarrito
