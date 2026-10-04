import { useState } from "react"
import Campo from "../ui/Campo"
import CampoSelect from "../ui/CampoSelect"
import MensajeError from "../ui/MensajeError"

// Los nombres coinciden con DescuentoRequest (activo se maneja desde la tabla)
const descuentoVacio = {productoId: '', porcentaje: '', fechaInicio: '', fechaFin: ''}

const DescuentoForm = ({inicial, productos, guardarDescuento, cancelarEdicion}) => {

    const [descuento, setDescuento] = useState(inicial === null ? descuentoVacio : {
        productoId: inicial.productoId,
        porcentaje: inicial.porcentaje,
        fechaInicio: inicial.fechaInicio,
        fechaFin: inicial.fechaFin
    })
    const [error, setError] = useState('')

    const handleChange = (e) => {setDescuento({...descuento, [e.target.name]: e.target.value})}

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(descuento).some((value)=>String(value).trim() === '')){
            setError('Completá todos los campos')
            return
        }
        if(Number(descuento.porcentaje) <= 0 || Number(descuento.porcentaje) > 100){
            setError('El porcentaje tiene que estar entre 1 y 100')
            return
        }
        if(descuento.fechaFin < descuento.fechaInicio){
            setError('La fecha de fin no puede ser anterior a la de inicio')
            return
        }
        setError('')
        guardarDescuento({...descuento, productoId: Number(descuento.productoId), porcentaje: Number(descuento.porcentaje)})
        setDescuento(descuentoVacio)
    }

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">{inicial === null ? 'Nuevo descuento' : 'Editar descuento'}</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md">
                <CampoSelect label="Producto" name="productoId" value={descuento.productoId} onChange={handleChange}
                    opciones={productos.map((value)=>({valor: value.productoId, texto: value.nombreProducto}))} />
                <Campo label="Porcentaje" type="number" name="porcentaje" value={descuento.porcentaje} onChange={handleChange} />
                <Campo label="Fecha de inicio" type="date" name="fechaInicio" value={descuento.fechaInicio} onChange={handleChange} />
                <Campo label="Fecha de fin" type="date" name="fechaFin" value={descuento.fechaFin} onChange={handleChange} />
            </div>
            <MensajeError mensaje={error} />
            <div className="flex justify-end gap-space-sm">
                {inicial !== null && (
                    <button type="button" onClick={cancelarEdicion} className="bg-surface-container-high text-primary font-bold text-label-lg uppercase px-space-lg py-space-sm">Cancelar</button>
                )}
                <button type="submit" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-sm hover:bg-primary">
                    {inicial === null ? 'Crear descuento' : 'Guardar cambios'}
                </button>
            </div>
        </form>
    )
}

export default DescuentoForm
