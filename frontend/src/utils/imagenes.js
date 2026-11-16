import { URL, opciones, leerRespuesta } from "./api"

export const urlPrincipal = (productoId) => `${URL}/Imagen/principal/${productoId}`

export const urlImagen = (imagen) => `data:${imagen.tipoContenido};base64,${imagen.imagenBase64}`

export const obtenerImagenes = (productoId) => {
    return fetch(`${URL}/Imagen?productoId=${productoId}`)
    .then(leerRespuesta)
}

const subirImagen = (productoId, archivo) => {
    const datos = new FormData()
    datos.append('productoId', productoId)
    datos.append('image', archivo)

    return fetch(`${URL}/Imagen/upload`, {
        method: 'POST',
        headers: {'Authorization': `Bearer ${localStorage.getItem('token')}`},
        body: datos
    })
    .then(leerRespuesta)
}

export const subirImagenes = (productoId, archivos) => {
    return archivos.reduce(
        (promesa, archivo) => promesa.then(()=>subirImagen(productoId, archivo)),
        Promise.resolve()
    )
}

export const actualizarOrdenImagenes = (imagenIds) => {
    return fetch(`${URL}/Imagen/orden`, opciones('PUT', imagenIds))
    .then(leerRespuesta)
}

export const borrarImagen = (imagenId) => {
    return fetch(`${URL}/Imagen/${imagenId}`, opciones('PATCH'))
    .then(leerRespuesta)
}
