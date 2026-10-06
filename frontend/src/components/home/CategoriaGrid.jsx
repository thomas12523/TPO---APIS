import { useState } from "react"
import CategoriaCard from "./CategoriaCard"
import TituloSeccion from "../ui/TituloSeccion"
import imagenesCategoria from "./imagenes/imagenesCategoria"
import { categoriasPrueba } from "../../data/datosPrueba"

const CategoriaGrid = () => {

    const [categorias, setCategorias] = useState(categoriasPrueba)

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
