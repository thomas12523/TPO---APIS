import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import SelectorCantidad from "../ui/SelectorCantidad"
import MensajeError from "../ui/MensajeError"
import Aviso from "../ui/Aviso"
import { agregarAlCarrito, actualizarCantidad, sacarDelCarrito } from "../../utils/carrito"

// Si el producto no está en el carrito muestra el +, y si ya está muestra el selector de cantidad.
// carritoCambio viene de la vista: lo llamamos cuando entra o sale un producto del carrito
const BotonCarrito = ({productoId, precio, stock, cantidadEnCarrito, carritoCambio}) => {

    const [cantidad, setCantidad] = useState(cantidadEnCarrito)
    const [error, setError] = useState('')
    const [aviso, setAviso] = useState('')
    const navigate = useNavigate()

    // El carrito llega después de que se dibujan las cards, así que actualizamos la cantidad cuando llega
    useEffect(()=>{
        setCantidad(cantidadEnCarrito)
    }, [cantidadEnCarrito])

    // El aviso se borra solo a los 2,5 segundos
    useEffect(()=>{
        const temporizador = setTimeout(()=>setAviso(''), 2500)
        return ()=>clearTimeout(temporizador)
    }, [aviso])

    const agregar = () => {
        if(localStorage.getItem('token') === null){
            navigate('/login')
            return
        }
        setError('')
        agregarAlCarrito(productoId, 1, precio)
        .then((data)=>{
            setCantidad(data.cantidad)
            setAviso('Producto agregado al carrito')
            carritoCambio()
        })
        .catch((error)=>{
            console.error('Error al agregar al carrito', error)
            setError(error.message)
        })
    }

    const cambiarCantidad = (nueva) => {
        setError('')
        if(nueva === 0){
            sacarDelCarrito(productoId)
            .then(()=>{
                setCantidad(0)
                setAviso('Producto quitado del carrito')
                carritoCambio()
            })
            .catch((error)=>setError(error.message))
            return
        }
        actualizarCantidad(productoId, nueva, precio)
        .then(()=>setCantidad(nueva))
        .catch((error)=>setError(error.message))
    }

    return(
        <>
        {cantidad === 0 ? (
            <button onClick={agregar} disabled={stock === 0} title="Agregar al carrito" className="w-9 h-9 flex items-center justify-center bg-surface-container-high text-primary hover:bg-primary-container hover:text-surface-container-lowest disabled:opacity-30">
                <span className="material-symbols-outlined">add</span>
            </button>
        ) : (
            <SelectorCantidad cantidad={cantidad} cambiarCantidad={cambiarCantidad} maximo={stock} minimo={0} />
        )}
        {/* El error ocupa todo el ancho para que baje a otra línea, abajo del precio */}
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
