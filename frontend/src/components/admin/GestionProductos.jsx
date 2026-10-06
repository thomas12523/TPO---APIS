import { useState } from "react"
import ProductoForm from "./ProductoForm"
import TablaProductos from "./tablas/TablaProductos"
import { categoriasPrueba, productosPrueba } from "../../data/datosPrueba"

const GestionProductos = () => {

    // Al conectar: GET /Producto y GET /Categories
    const [productos, setProductos] = useState(productosPrueba)
    const [categorias, setCategorias] = useState(categoriasPrueba)
    const [editando, setEditando] = useState(null)

    // Al conectar: POST /Producto o PUT /Producto/{id}, y guardamos lo que devuelve el back
    const guardarProducto = (datos) => {
        const categoriaNombre = categorias.find((value)=>value.id === datos.categoriaId).nombre
        if(editando === null){
            setProductos([...productos, {...datos, categoriaNombre, productoId: productos.length + 1, precioConDescuento: datos.precioUnitario, tieneDescuento: false, activo: true}])
            return
        }
        setProductos(productos.map((value)=>(
            value.productoId === editando.productoId ? {...value, ...datos, categoriaNombre} : value
        )))
        setEditando(null)
    }

    const editarProducto = (productoId) => {
        setEditando(productos.find((value)=>value.productoId === productoId))
    }

    // Al conectar: PATCH /Producto/{id}/estado
    const cambiarEstado = (productoId) => {
        setProductos(productos.map((value)=>(
            value.productoId === productoId ? {...value, activo: !value.activo} : value
        )))
    }

    return(
        <>
        <ProductoForm
        key={editando === null ? 'nuevo' : editando.productoId}
        inicial={editando}
        categorias={categorias}
        guardarProducto={guardarProducto}
        cancelarEdicion={()=>setEditando(null)}
        />
        <TablaProductos productos={productos} editarProducto={editarProducto} cambiarEstado={cambiarEstado} />
        </>
    )
}

export default GestionProductos
