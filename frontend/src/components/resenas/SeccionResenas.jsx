import { useState } from "react"
import ListaResenas from "./lista/ListaResenas"
import ResenaForm from "./ResenaForm"
import TituloSeccion from "../ui/TituloSeccion"
import { resenasPrueba } from "../../data/datosPrueba"

const SeccionResenas = ({productoId}) => {

    // Al conectar: GET /Resena?productoId={productoId}
    const [resenas, setResenas] = useState(resenasPrueba)

    // Al conectar: POST /Resena y el back devuelve la reseña creada
    const agregarResena = (nuevaResena) => {
        setResenas([...resenas, {
            ...nuevaResena,
            resenaId: resenas.length + 1,
            productoId: productoId,
            username: 'yo',
            fecha: 'Hoy',
            activo: true
        }])
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
