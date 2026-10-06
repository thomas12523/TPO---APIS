const FiltrosCatalogo = ({categorias, filtros, handleChange, limpiarFiltros}) => {
    return(
        <form onSubmit={(e)=>{e.preventDefault()}} className="bg-surface-container-low p-space-lg flex flex-col gap-space-lg h-fit">
            <div className="flex items-center justify-between bg-surface-container-lowest px-space-md py-space-sm">
                <span className="flex items-center gap-space-xs font-headline font-extrabold text-headline-sm uppercase text-primary">
                    <span className="material-symbols-outlined text-primary-container">filter_alt</span>
                    Filtros
                </span>
                <button type="button" onClick={limpiarFiltros} className="font-bold text-label-tag uppercase text-on-surface-variant hover:text-primary">Limpiar</button>
            </div>

            <div className="flex flex-col gap-space-sm">
                <label className="font-bold text-label-md uppercase text-primary">Buscar</label>
                <input type="text" name="nombre" value={filtros.nombre} onChange={handleChange} placeholder="Nombre del producto"
                    className="bg-surface-container text-on-surface text-body-md px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none" />
            </div>

            <fieldset className="flex flex-col gap-space-sm">
                <legend className="font-bold text-label-md uppercase text-primary mb-space-sm">Categoría</legend>
                <label className="flex items-center gap-space-sm text-body-md text-on-surface cursor-pointer">
                    <input type="radio" name="categoriaId" value="" checked={filtros.categoriaId === ''} onChange={handleChange} className="accent-primary-container" />
                    Todas
                </label>
                {
                    categorias.map((value)=>(
                        <label key={value.id} className="flex items-center gap-space-sm text-body-md text-on-surface cursor-pointer">
                            <input type="radio" name="categoriaId" value={value.id} checked={filtros.categoriaId === String(value.id)} onChange={handleChange} className="accent-primary-container" />
                            {value.nombre}
                        </label>
                    ))
                }
            </fieldset>

            <div className="flex flex-col gap-space-sm">
                <label className="font-bold text-label-md uppercase text-primary">Precio</label>
                <div className="grid grid-cols-2 gap-space-sm">
                    <input type="number" name="precioMin" value={filtros.precioMin} onChange={handleChange} placeholder="Mínimo" min="0"
                        className="bg-surface-container text-on-surface text-body-md px-space-sm py-space-sm border border-white/10 focus:border-primary-container focus:outline-none" />
                    <input type="number" name="precioMax" value={filtros.precioMax} onChange={handleChange} placeholder="Máximo" min="0"
                        className="bg-surface-container text-on-surface text-body-md px-space-sm py-space-sm border border-white/10 focus:border-primary-container focus:outline-none" />
                </div>
            </div>
        </form>
    )
}

export default FiltrosCatalogo
