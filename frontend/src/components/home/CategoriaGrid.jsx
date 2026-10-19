import { useEffect, useState } from "react"
import CategoriaCard from "./CategoriaCard"
import TituloSeccion from "../ui/TituloSeccion"
import imagenesCategoria from "./imagenes/imagenesCategoria"
import { URL, leerRespuesta } from "../../utils/api"

const CategoriaGrid = () => {

    const [categorias, setCategorias] = useState([])

    useEffect(()=>{

        fetch(`${URL}/Categories?size=5`)
        .then(leerRespuesta)
        .then((data)=>{

            setCategorias(data.content)
        })
        .catch((error)=>{
            console.error('Error al cargar las categorías', error)
        })
    }, [])

    if(categorias.length === 0){
        return null
    }

    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
            <TituloSeccion etiqueta="Elegí tu disciplina" titulo="Explorá por categoría" />
            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-space-md">
                {
                    categorias.map((value, index)=>(
                        <CategoriaCard
                        key={value.id}
                        id={value.id}
                        nombre={value.nombre}
                        imagen={imagenesCategoria[index % imagenesCategoria.length]}
                        />
                    ))
                }
            </div>
        </section>
    )
}

export default CategoriaGrid
