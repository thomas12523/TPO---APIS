import { URL, opciones, leerRespuesta } from "./api"

export const iniciarSesion = (token) => {
    localStorage.setItem('token', token)
    return fetch(`${URL}/Usuario/me`, opciones('GET'))
    .then(leerRespuesta)
    .then((usuario)=>{
        localStorage.setItem('usuarioId', usuario.usuarioId)
        localStorage.setItem('role', usuario.role)
        return fetch(`${URL}/Carrito?usuarioId=${usuario.usuarioId}`, opciones('GET'))
    })
    .then(leerRespuesta)
    .then((carritos)=>{
        if(carritos.length > 0){
            localStorage.setItem('carritoId', carritos[0].carritoId)
        }
    })
}

export const cerrarSesion = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('usuarioId')
    localStorage.removeItem('role')
    localStorage.removeItem('carritoId')
}
