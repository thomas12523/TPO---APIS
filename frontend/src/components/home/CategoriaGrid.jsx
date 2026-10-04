import { useState } from "react"
import CategoriaCard from "./CategoriaCard"
import TituloSeccion from "../ui/TituloSeccion"
import categoria1 from "../../assets/categoria-1.jpg"
import categoria2 from "../../assets/categoria-2.jpg"
import categoria3 from "../../assets/categoria-3.jpg"
import categoria4 from "../../assets/categoria-4.jpg"
import categoria5 from "../../assets/categoria-5.jpg"
import { categoriasPrueba } from "../../data/datosPrueba"

// El back no guarda imagen de categoría: usamos imágenes fijas de assets
const imagenes = [categoria1, categoria2, categoria3, categoria4, categoria5]

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
                        imagen={imagenes[index % imagenes.length]}
                        />
                    ))
                }
            </div>
        </section>
    )
}

export default CategoriaGrid
