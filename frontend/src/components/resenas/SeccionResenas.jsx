import { useEffect, useState } from "react"
import ListaResenas from "./lista/ListaResenas"
import ResenaForm from "./ResenaForm"
import AvisoResena from "./AvisoResena"
import TituloSeccion from "../ui/TituloSeccion"
import AvisoSesion from "../ui/AvisoSesion"
import { URL, opciones, leerRespuesta } from "../../utils/api"

const SeccionResenas = ({productoId}) => {

    const [resenas, setResenas] = useState([])

    const [compro, setCompro] = useState(false)
    const token = localStorage.getItem('token')

    useEffect(()=>{
        fetch(`${URL}/Resena?productoId=${productoId}`)
        .then(leerRespuesta)
        .then((data)=>setResenas(data.filter((value)=>value.activo)))
        .catch((error)=>console.error('Error al cargar las reseñas', error))

        setCompro(false)
        if(token === null){
            return
        }
        fetch(`${URL}/Resena/compra/${productoId}`, opciones('GET'))
        .then(leerRespuesta)
        .then((data)=>setCompro(data))
        .catch((error)=>console.error('Error al revisar la compra', error))
    }, [productoId])

    const agregarResena = (nuevaResena) => {
        return fetch(`${URL}/Resena`, opciones('POST', {...nuevaResena, productoId: productoId}))
        .then(leerRespuesta)
        .then((data)=>{

            setResenas([...resenas, data])
        })
    }

    const yaReseno = resenas.some((value)=>value.usuarioId === Number(localStorage.getItem('usuarioId')))

    const lateral = () => {
        if(token === null){
            return <AvisoSesion mensaje="Iniciá sesión para escribir una reseña." />
        }
        if(!compro){
            return <AvisoResena icono="shopping_bag" mensaje="Solo pueden opinar quienes compraron este producto." />
        }
        if(yaReseno){
            return <AvisoResena icono="check_circle" mensaje="Ya dejaste tu reseña de este producto. ¡Gracias!" />
        }
        return <ResenaForm agregarResena={agregarResena} />
    }

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="Opiniones de compradores" titulo={`Reseñas (${resenas.length})`} />
            <div className="grid grid-cols-1 lg:grid-cols-[1fr_22rem] gap-space-lg">
                <ListaResenas resenas={resenas} />
                {lateral()}
            </div>
        </section>
    )
}

export default SeccionResenas
