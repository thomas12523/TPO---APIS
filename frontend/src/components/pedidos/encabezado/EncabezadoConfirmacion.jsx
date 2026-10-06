const EncabezadoConfirmacion = () => {
    return(
        <>
        <div className="w-20 h-20 flex items-center justify-center bg-primary-container text-surface-container-lowest">
            <span className="material-symbols-outlined text-5xl">check</span>
        </div>
        <div>
            <h2 className="font-headline font-black text-headline-lg uppercase text-primary">¡Gracias por tu compra!</h2>
            <p className="text-body-lg text-on-surface-variant mt-space-sm">Registramos tu pedido. Te avisamos por mail cuando cambie de estado.</p>
        </div>
        </>
    )
}

export default EncabezadoConfirmacion
