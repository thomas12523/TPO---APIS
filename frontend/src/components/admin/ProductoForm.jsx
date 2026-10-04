import { useState } from "react"
import Campo from "../ui/Campo"
import CampoSelect from "../ui/CampoSelect"
import MensajeError from "../ui/MensajeError"

// Los nombres coinciden con ProductoRequest
const productoVacio = {categoriaId: '', nombreProducto: '', descripcion: '', precioUnitario: '', stock: '', imagenUrl: ''}

const ProductoForm = ({inicial, categorias, guardarProducto, cancelarEdicion}) => {

    const [producto, setProducto] = useState(inicial === null ? productoVacio : {
        categoriaId: inicial.categoriaId,
        nombreProducto: inicial.nombreProducto,
        descripcion: inicial.descripcion,
        precioUnitario: inicial.precioUnitario,
        stock: inicial.stock,
        imagenUrl: inicial.imagenUrl
    })
    const [error, setError] = useState('')

    const handleChange = (e) => {setProducto({...producto, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(producto).some((value)=>String(value).trim() === '')){
            setError('Completá todos los campos')
            return
        }
        if(Number(producto.precioUnitario) <= 0 || Number(producto.stock) < 0){
            setError('El precio tiene que ser mayor a 0 y el stock no puede ser negativo')
            return
        }
        setError('')
        guardarProducto({...producto, categoriaId: Number(producto.categoriaId), precioUnitario: Number(producto.precioUnitario), stock: Number(producto.stock)})
        setProducto(productoVacio)
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">{inicial === null ? 'Nuevo producto' : 'Editar producto'}</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md">
                <Campo label="Nombre" name="nombreProducto" value={producto.nombreProducto} onChange={handleChange} />
                <CampoSelect label="Categoría" name="categoriaId" value={producto.categoriaId} onChange={handleChange}
                    opciones={categorias.map((value)=>({valor: value.id, texto: value.nombre}))} />
                <Campo label="Precio unitario" type="number" name="precioUnitario" value={producto.precioUnitario} onChange={handleChange} />
                <Campo label="Stock" type="number" name="stock" value={producto.stock} onChange={handleChange} />
            </div>
            <Campo label="URL de la imagen" name="imagenUrl" value={producto.imagenUrl} onChange={handleChange} placeholder="https://..." />
            <Campo label="Descripción" name="descripcion" value={producto.descripcion} onChange={handleChange} />
            <MensajeError mensaje={error} />
            <div className="flex justify-end gap-space-sm">
                {inicial !== null && (
                    <button type="button" onClick={cancelarEdicion} className="bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-lg py-space-sm">Cancelar</button>
                )}
                <button type="submit" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                    {inicial === null ? 'Crear producto' : 'Guardar cambios'}
                </button>
            </div>
        </form>
    )
}

export default ProductoForm
