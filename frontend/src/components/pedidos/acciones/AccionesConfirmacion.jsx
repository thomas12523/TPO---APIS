const AccionesConfirmacion = () => {
    return(
        <div className="w-full grid grid-cols-1 sm:grid-cols-2 gap-space-sm">
            <a href="/mis-pedidos" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase py-space-md hover:bg-primary">Ver mis pedidos</a>
            <a href="/catalogo" className="bg-surface-container-high text-primary font-bold text-label-lg uppercase py-space-md hover:bg-surface-container-highest">Seguir comprando</a>
        </div>
    )
}

export default AccionesConfirmacion
