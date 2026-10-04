const pasos = ['Carrito', 'Pago', 'Confirmación']

const PasosCheckout = ({pasoActual}) => {
    return(
        <ol className="flex flex-wrap items-center gap-space-sm mb-space-xl">
            {
                pasos.map((value, index)=>(
                    <li key={value} className={`flex items-center gap-space-sm px-space-md py-space-sm font-bold text-label-md uppercase tracking-wider
                        ${index + 1 === pasoActual ? 'bg-primary-container text-surface-container-lowest' : 'bg-surface-container-low text-on-surface-variant'}
                        ${index + 1 < pasoActual ? 'line-through' : ''}`}>
                        <span>{index + 1}</span>
                        {value}
                    </li>
                ))
            }
        </ol>
    )
}

export default PasosCheckout
