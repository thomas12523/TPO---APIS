
export const URL = 'http://localhost:4002'

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

export const leerRespuesta = (response) => {
    if (response.status === 401) {
        localStorage.removeItem('token')
        localStorage.removeItem('usuarioId')
        localStorage.removeItem('role')
        localStorage.removeItem('carritoId')

        return Promise.reject(new Error('Sesión expirada. Por favor ingresá de nuevo.'))
    }
    if(!response.ok){
        return response.json().then((data)=>{
            throw new Error(data.message)
        })
    }
    return response.json()
}
