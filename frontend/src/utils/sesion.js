import { URL, opciones, leerRespuesta } from "./api"
import store from "../store"
import { setSesion, cerrarSesionRedux } from "../store/slices/authSlice"
import { setCarritoId, limpiarCarrito, fetchCarrito } from "../store/slices/carritoSlice"

// El login y el registro solo devuelven el token. Lo guardamos en el localStorage y en Redux,
// y con el token, le pedimos al back quién es el usuario y cuál es su carrito.
export const iniciarSesion = (token) => {
    localStorage.setItem('token', token)
    return fetch(`${URL}/Usuario/me`, opciones('GET'))
        .then(leerRespuesta)
        .then((usuario) => {
            localStorage.setItem('usuarioId', usuario.usuarioId)
            localStorage.setItem('role', usuario.role)
            store.dispatch(setSesion({
                token: token,
                usuarioId: usuario.usuarioId,
                role: usuario.role
            }))
            return fetch(`${URL}/Carrito?usuarioId=${usuario.usuarioId}`, opciones('GET'))
        })
        .then(leerRespuesta)
        .then((carritos) => {
            if (carritos.length > 0) {
                const id = carritos[0].carritoId
                localStorage.setItem('carritoId', id)
                store.dispatch(setCarritoId(id))
                store.dispatch(fetchCarrito())
            }
        })
}

export const cerrarSesion = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('usuarioId')
    localStorage.removeItem('role')
    localStorage.removeItem('carritoId')
    store.dispatch(cerrarSesionRedux())
    store.dispatch(limpiarCarrito())
}
