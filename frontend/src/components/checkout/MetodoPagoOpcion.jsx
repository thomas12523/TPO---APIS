const MetodoPagoOpcion = ({valor, titulo, descripcion, icono, seleccionado, handleChange}) => {
    return(
        <label className={`flex items-start gap-space-md p-space-lg cursor-pointer border-2
            ${seleccionado ? 'border-primary-container bg-primary-container/10' : 'border-transparent bg-surface-container hover:bg-surface-container-high'}`}>
            <input type="radio" name="metodoPago" value={valor} checked={seleccionado} onChange={handleChange} className="sr-only" />
            <span className={`material-symbols-outlined text-3xl ${seleccionado ? 'text-primary-container' : 'text-on-surface-variant'}`}>{icono}</span>
            <span>
                <span className="block font-headline font-extrabold text-headline-sm uppercase text-primary">{titulo}</span>
                <span className="block text-body-sm text-on-surface-variant">{descripcion}</span>
            </span>
        </label>
    )
}

export default MetodoPagoOpcion
