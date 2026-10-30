import { URL, opciones, leerRespuesta } from "./api"

// GET /Imagen/principal/{productoId}: el back devuelve el archivo, así que va directo en el src del <img>
export const urlPrincipal = (productoId) => `${URL}/Imagen/principal/${productoId}`

// Las imágenes del listado vienen en base64: armamos la dirección que entiende el <img>
export const urlImagen = (imagen) => `data:${imagen.tipoContenido};base64,${imagen.imagenBase64}`

// GET /Imagen?productoId=: las imágenes activas del producto, la primera es la principal
export const obtenerImagenes = (productoId) => {
    return fetch(`${URL}/Imagen?productoId=${productoId}`)
    .then(leerRespuesta)
}

// POST /Imagen/upload: el archivo viaja como multipart, por eso no usamos opciones()
// (no hay que poner el Content-Type a mano, fetch lo arma solo con el FormData)
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

// Las subimos de a una y en orden, así la primera que eligió el admin queda como principal
export const subirImagenes = (productoId, archivos) => {
    return archivos.reduce(
        (promesa, archivo) => promesa.then(()=>subirImagen(productoId, archivo)),
        Promise.resolve()
    )
}

// PATCH /Imagen/{imagenId}: baja lógica de la imagen
export const borrarImagen = (imagenId) => {
    return fetch(`${URL}/Imagen/${imagenId}`, opciones('PATCH'))
    .then(leerRespuesta)
}
