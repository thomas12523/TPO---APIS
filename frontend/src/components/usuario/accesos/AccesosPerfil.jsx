const AccesosPerfil = () => {
    return(
        <aside className="flex flex-col gap-space-sm h-fit">
            <a href="/mis-pedidos" className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                <span className="material-symbols-outlined text-primary-container text-3xl">local_shipping</span>
                <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Mis pedidos</span>
            </a>
            <a href="/login" className="flex items-center gap-space-md bg-surface-container-low p-space-lg hover:bg-surface-container">
                <span className="material-symbols-outlined text-error text-3xl">logout</span>
                <span className="font-headline font-extrabold text-headline-sm uppercase text-primary">Cerrar sesión</span>
            </a>
        </aside>
    )
}

export default AccesosPerfil
