import { useEffect, useState } from "react"
import ListaResenas from "./lista/ListaResenas"
import ResenaForm from "./ResenaForm"
import TituloSeccion from "../ui/TituloSeccion"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const SeccionResenas = ({productoId}) => {

    const [resenas, setResenas] = useState([])

    useEffect(()=>{
        fetch(`${URL}/Resena?productoId=${productoId}`)
        .then(leerRespuesta)
        .then((data)=>setResenas(data.filter((value)=>value.activo)))
        .catch((error)=>console.error('Error al cargar las reseñas', error))
    }, [productoId])

    // Devolvemos la promesa para que el formulario sepa si salió bien o mal.
    // El back toma el usuario del token, así que solo mandamos el producto, la puntuación y el comentario
    const agregarResena = (nuevaResena) => {
        return fetch(`${URL}/Resena`, opciones('POST', {...nuevaResena, productoId: productoId}))
        .then(leerRespuesta)
        .then((data)=>{
            // Guardamos la reseña que devolvió el back, así se ve sin volver a pedir todas
            setResenas([...resenas, data])
        })
    }

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="Opiniones de compradores" titulo={`Reseñas (${resenas.length})`} />
            <div className="grid grid-cols-1 lg:grid-cols-[1fr_22rem] gap-space-lg">
                <ListaResenas resenas={resenas} />
                <ResenaForm agregarResena={agregarResena} />
            </div>
        </section>
    )
}

export default SeccionResenas
