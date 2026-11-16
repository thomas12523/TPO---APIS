import { useState, useEffect } from "react"
import Campo from "../ui/Campo"
import CampoSelect from "../ui/CampoSelect"
import MensajeError from "../ui/MensajeError"
import SelectorImagenes from "./imagenes/SelectorImagenes"
import ImagenesActuales from "./imagenes/ImagenesActuales"
import { URL, leerRespuesta } from "../../utils/api"

const productoVacio = {categoriaId: '', nombreProducto: '', descripcion: '', precioUnitario: '', stock: ''}

const hoyStr = new Date().toISOString().split('T')[0]
const tresMesesDespuesStr = new Date(Date.now() + 90 * 24 * 60 * 60 * 1000).toISOString().split('T')[0]

const descuentoVacio = {
    descuentoId: null,
    aplicar: false,
    porcentaje: '',
    fechaInicio: hoyStr,
    fechaFin: tresMesesDespuesStr,
    activo: true
}

const ProductoForm = ({inicial, categorias, guardarProducto, cancelarEdicion}) => {

    const [producto, setProducto] = useState(inicial === null ? productoVacio : {
        categoriaId: inicial.categoriaId,
        nombreProducto: inicial.nombreProducto,
        descripcion: inicial.descripcion,
        precioUnitario: inicial.precioUnitario,
        stock: inicial.stock
    })

    const [descuento, setDescuento] = useState(descuentoVacio)

    const [archivos, setArchivos] = useState([])
    const [error, setError] = useState('')

    useEffect(() => {
        if (inicial && inicial.productoId) {
            fetch(`${URL}/Descuento?productoId=${inicial.productoId}`)
                .then(leerRespuesta)
                .then((data) => {
                    if (data && data.length > 0) {
                        const desc = data[0]
                        setDescuento({
                            descuentoId: desc.descuentoId,
                            aplicar: desc.activo,
                            porcentaje: desc.porcentaje,
                            fechaInicio: desc.fechaInicio || hoyStr,
                            fechaFin: desc.fechaFin || tresMesesDespuesStr,
                            activo: desc.activo
                        })
                    } else {
                        setDescuento(descuentoVacio)
                    }
                })
                .catch((err) => console.error('Error al cargar descuento del producto', err))
        } else {
            setDescuento(descuentoVacio)
        }
    }, [inicial])

    const handleChange = (e) => {setProducto({...producto, [e.target.name]: e.target.value})}

    const handleDescuentoChange = (e) => {
        const { name, value, type, checked } = e.target
        setDescuento({
            ...descuento,
            [name]: type === 'checkbox' ? checked : value
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        if(Object.values(producto).some((value)=>String(value).trim() === '')){
            setError('Completá todos los campos del producto')
            return
        }
        if(Number(producto.precioUnitario) <= 0 || Number(producto.stock) < 0){
            setError('El precio tiene que ser mayor a 0 y el stock no puede ser negativo')
            return
        }
        if(inicial === null && archivos.length === 0){
            setError('Elegí al menos una imagen')
            return
        }

        if (descuento.aplicar) {
            if (!descuento.porcentaje || Number(descuento.porcentaje) <= 0 || Number(descuento.porcentaje) > 100) {
                setError('El porcentaje de descuento tiene que estar entre 1 y 100')
                return
            }
            if (!descuento.fechaInicio || !descuento.fechaFin) {
                setError('Completá las fechas de inicio y fin del descuento')
                return
            }
            if (descuento.fechaFin < descuento.fechaInicio) {
                setError('La fecha de fin del descuento no puede ser anterior a la de inicio')
                return
            }
        }

        setError('')
        guardarProducto(
            {
                ...producto,
                categoriaId: Number(producto.categoriaId),
                precioUnitario: Number(producto.precioUnitario),
                stock: Number(producto.stock)
            },
            archivos,
            descuento
        )
        .then(()=>{
            setProducto(productoVacio)
            setDescuento(descuentoVacio)
            setArchivos([])
        })
        .catch((error)=>{
            console.error('Error al guardar el producto', error)
            setError(error.message)
        })
    }

    const precioBase = Number(producto.precioUnitario) || 0
    const porc = Number(descuento.porcentaje) || 0
    const precioConDesc = porc > 0 && porc <= 100 ? precioBase - (precioBase * porc / 100) : null

    return(
        <form onSubmit={handleSubmit} className="bg-surface-container-low p-space-lg flex flex-col gap-space-md">
            <h2 className="font-headline font-extrabold text-headline-md uppercase text-primary">
                {inicial === null ? 'Nuevo producto' : 'Editar producto'}
            </h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md">
                <Campo label="Nombre" name="nombreProducto" value={producto.nombreProducto} onChange={handleChange} />
                <CampoSelect label="Categoría" name="categoriaId" value={producto.categoriaId} onChange={handleChange}
                    opciones={categorias.map((value)=>({valor: value.id, texto: value.nombre}))} />
                <Campo label="Precio unitario" type="number" name="precioUnitario" value={producto.precioUnitario} onChange={handleChange} />
                <Campo label="Stock" type="number" name="stock" value={producto.stock} onChange={handleChange} />
            </div>
            <Campo label="Descripción" name="descripcion" value={producto.descripcion} onChange={handleChange} />

            <div className="p-space-md bg-surface-container/60 border border-white/5 flex flex-col gap-space-sm">
                <label className="flex items-center gap-space-sm cursor-pointer select-none">
                    <input
                        type="checkbox"
                        name="aplicar"
                        checked={descuento.aplicar}
                        onChange={handleDescuentoChange}
                        className="w-4 h-4 accent-primary-container cursor-pointer"
                    />
                    <span className="font-headline font-bold text-label-lg uppercase text-primary">
                        Aplicar descuento a este producto
                    </span>
                </label>

                {descuento.aplicar && (
                    <div className="flex flex-col gap-space-sm pt-space-xs">
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-space-md">
                            <Campo
                                label="Porcentaje de descuento (%)"
                                type="number"
                                name="porcentaje"
                                value={descuento.porcentaje}
                                onChange={handleDescuentoChange}
                            />
                            <Campo
                                label="Fecha de inicio"
                                type="date"
                                name="fechaInicio"
                                value={descuento.fechaInicio}
                                onChange={handleDescuentoChange}
                            />
                            <Campo
                                label="Fecha de fin"
                                type="date"
                                name="fechaFin"
                                value={descuento.fechaFin}
                                onChange={handleDescuentoChange}
                            />
                        </div>

                        {precioConDesc !== null && precioBase > 0 && (
                            <p className="text-body-sm text-primary-container font-semibold">
                                Precio final con oferta: ${precioConDesc.toLocaleString('es-AR')} (Ahorro: ${(precioBase - precioConDesc).toLocaleString('es-AR')})
                            </p>
                        )}
                    </div>
                )}
            </div>

            {inicial !== null && <ImagenesActuales productoId={inicial.productoId} />}
            <SelectorImagenes archivos={archivos} cambiarArchivos={setArchivos} />
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
