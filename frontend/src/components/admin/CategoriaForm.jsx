import { useState } from "react"
import Campo from "../ui/Campo"
import MensajeError from "../ui/MensajeError"

const CategoriaForm = ({inicial, guardarCategoria, cancelarEdicion}) => {

    // El nombre coincide con CategoryRequest
    const [categoria, setCategoria] = useState({nombre: inicial === null ? '' : inicial.nombre})
    const [error, setError] = useState('')

    const handleChange = (e) => {setCategoria({...categoria, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(categoria.nombre.trim() === ''){
            setError('El nombre no puede estar vacío')
            return
        }
        setError('')
        guardarCategoria({nombre: categoria.nombre.trim()})
        setCategoria({nombre: ''})
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">{inicial === null ? 'Nueva categoría' : 'Editar categoría'}</h2>
            <Campo label="Nombre" name="nombre" value={categoria.nombre} onChange={handleChange} />
            <MensajeError mensaje={error} />
            <div className="flex justify-end gap-space-sm">
                {inicial !== null && (
                    <button type="button" onClick={cancelarEdicion} className="bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-lg py-space-sm">Cancelar</button>
                )}
                <button type="submit" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                    {inicial === null ? 'Crear categoría' : 'Guardar cambios'}
                </button>
            </div>
        </form>
    )
}

export default CategoriaForm
