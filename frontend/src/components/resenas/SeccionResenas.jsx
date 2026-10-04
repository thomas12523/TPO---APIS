import { useState } from "react"
import ResenaCard from "./ResenaCard"
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
                {resenas.length === 0 ? (
                    <p className="text-body-lg text-on-surface-variant">Todavía no hay reseñas. ¡Sé el primero!</p>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md h-fit">
                        {
                            resenas.map((value)=>(
                                <ResenaCard
                                key={value.resenaId}
                                username={value.username}
                                puntuacion={value.puntuacion}
                                comentario={value.comentario}
                                fecha={value.fecha}
                                />
                            ))
                        }
                    </div>
                )}
                <ResenaForm agregarResena={agregarResena} />
            </div>
        </section>
    )
}

export default SeccionResenas
