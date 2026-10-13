// URL base del back (en la clase era localhost:8080, el nuestro corre en el 4002)
export const URL = 'http://localhost:4002'

// Opciones del fetch para los endpoints que piden estar logueado:
// el método HTTP, los headers con el token y el body en formato JSON
export const opciones = (method, datos) => {
    const token = localStorage.getItem('token')
    return {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(datos)
    }
}

// Claves que se guardan en localStorage al iniciar sesión
const CLAVES_SESION = ['token', 'usuarioId', 'role', 'carritoId']

// fetch solo rechaza la promesa si no hay conexión: si el back responde un 400 o un 401
// igual entra al .then. Por eso, si la respuesta no es ok, tiramos el error para que
// lo agarre el .catch con el mensaje que manda el back.
// Si la respuesta es 401 (sesión vencida / token inválido), limpiamos el localStorage
// y redirigimos al home para que la página se muestre sin usuario logueado.
export const leerRespuesta = (response) => {
    if (response.status === 401) {
        CLAVES_SESION.forEach((clave) => localStorage.removeItem(clave))
        window.location.href = '/'
        // Rechazamos igual para cortar la cadena de .then
        return Promise.reject(new Error('Sesión expirada. Por favor ingresá de nuevo.'))
    }
    if(!response.ok){
        return response.json().then((data)=>{
            throw new Error(data.message)
        })
    }
    return response.json()
}
