import { useState } from "react"
import CategoriaForm from "./CategoriaForm"
import CategoriaFila from "./CategoriaFila"
import EncabezadoTabla from "./EncabezadoTabla"
import { categoriasPrueba } from "../../data/datosPrueba"

const GestionCategorias = () => {

    // Al conectar: GET /Categories
    const [categorias, setCategorias] = useState(categoriasPrueba)
    const [editando, setEditando] = useState(null)

    // Al conectar: POST /Categories o PUT /Categories/{id}
    const guardarCategoria = (datos) => {
        if(editando === null){
            setCategorias([...categorias, {...datos, id: categorias.length + 1, activo: true}])
            return
        }
        setCategorias(categorias.map((value)=>(
            value.id === editando.id ? {...value, ...datos} : value
        )))
        setEditando(null)
    }

    const editarCategoria = (id) => {
        setEditando(categorias.find((value)=>value.id === id))
    }

    // Al conectar: PATCH /Categories/{id} (baja lógica)
    const cambiarEstado = (id) => {
        setCategorias(categorias.map((value)=>(
            value.id === id ? {...value, activo: !value.activo} : value
        )))
    }

    return(
        <>
        <CategoriaForm
        key={editando === null ? 'nueva' : editando.id}
        inicial={editando}
        guardarCategoria={guardarCategoria}
        cancelarEdicion={()=>setEditando(null)}
        />
        <div className="bg-surface-container-low overflow-x-auto">
            <table className="w-full">
                <EncabezadoTabla columnas={['ID', 'Nombre', 'Estado', 'Acciones']} />
                <tbody>
                    {
                        categorias.map((value)=>(
                            <CategoriaFila
                            key={value.id}
                            id={value.id}
                            nombre={value.nombre}
                            activo={value.activo}
                            editarCategoria={editarCategoria}
                            cambiarEstado={cambiarEstado}
                            />
                        ))
                    }
                </tbody>
            </table>
        </div>
        </>
    )
}

export default GestionCategorias
