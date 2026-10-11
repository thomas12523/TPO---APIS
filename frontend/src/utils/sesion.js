import { URL, opciones, leerRespuesta } from "./api"

// El login y el registro solo devuelven el token. Lo guardamos en el localStorage y,
// con el token, le pedimos al back quién es el usuario y cuál es su carrito,
// así el resto de los componentes los tienen a mano sin pasar props por todos lados
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
