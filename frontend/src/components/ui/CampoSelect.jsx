const CampoSelect = ({label, name, value, onChange, opciones}) => {
    return(
        <div className="flex flex-col gap-space-xs">
            <label htmlFor={name} className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">{label}</label>
            <select id={name} name={name} value={value} onChange={onChange}
                className="bg-surface-container-high text-on-surface text-body-lg px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none">
                <option value="">Elegí una opción</option>
                {
                    opciones.map((value)=>(
                        <option key={value.valor} value={value.valor}>{value.texto}</option>
                    ))
                }
            </select>
        </div>
    )
}

export default CampoSelect
