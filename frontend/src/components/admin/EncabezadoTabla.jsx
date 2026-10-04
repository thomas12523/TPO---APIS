const EncabezadoTabla = ({columnas}) => {
    return(
        <thead>
            <tr className="bg-surface-container-lowest font-bold text-label-tag uppercase tracking-wider text-on-surface-variant">
                {
                    columnas.map((value)=>(
                        <th key={value} className="text-left px-space-md py-space-sm">{value}</th>
                    ))
                }
            </tr>
        </thead>
    )
}

export default EncabezadoTabla
