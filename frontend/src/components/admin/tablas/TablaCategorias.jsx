import CategoriaFila from "../CategoriaFila"
import EncabezadoTabla from "../EncabezadoTabla"

const TablaCategorias = ({categorias, editarCategoria, cambiarEstado}) => {
    return(
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
    )
}

export default TablaCategorias
