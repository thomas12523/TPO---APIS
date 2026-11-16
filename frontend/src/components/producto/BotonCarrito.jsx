import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { useDispatch, useSelector } from "react-redux"
import SelectorCantidad from "../ui/SelectorCantidad"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito, actualizarCantidad, sacarDelCarrito } from "../../utils/carrito"
import { fetchCarrito, activarAnimacion } from "../../store/slices/carritoSlice"

const BotonCarrito = ({ productoId, precio, stock, cantidadEnCarrito, carritoCambio }) => {
    const dispatch = useDispatch()
    const { role } = useSelector((state) => state.auth)
    const [cantidad, setCantidad] = useState(cantidadEnCarrito)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    useEffect(() => {
        setCantidad(cantidadEnCarrito)
    }, [cantidadEnCarrito])

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
        if (role === 'ADMIN' || localStorage.getItem('role') === 'ADMIN') {
            setError('Los productos son exclusivos para usuarios')
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
        if (role === 'ADMIN' || localStorage.getItem('role') === 'ADMIN') {
            setError('Los productos son exclusivos para usuarios')
            return
        }
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
            <Aviso mensaje={aviso} />
            <Aviso mensaje={error} tipo="error" />
        </>
    )
}

export default BotonCarrito
