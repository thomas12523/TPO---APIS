const MensajeError = ({mensaje}) => {

    if(mensaje === ''){
        return null
    }

    return(
        <p className="flex items-center gap-space-xs bg-error-container/40 text-error text-body-md px-space-md py-space-sm">
            <span className="material-symbols-outlined text-lg">error</span>
            {mensaje}
        </p>
    )
}

export default MensajeError
