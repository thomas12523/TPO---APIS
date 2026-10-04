const PerfilHeader = ({nombre, apellido, username, email, role}) => {
    return(
        <section className="flex flex-wrap items-center gap-space-lg bg-surface-container-low p-space-lg mb-space-lg">
            <div className="w-24 h-24 flex items-center justify-center bg-surface-container-high text-primary-container">
                <span className="material-symbols-outlined text-6xl">person</span>
            </div>
            <div>
                <span className="inline-block bg-primary-container text-surface-container-lowest font-bold text-label-tag uppercase px-space-sm py-space-xs mb-space-sm">
                    {role === 'ADMIN' ? 'Administrador' : 'Comprador'}
                </span>
                <h1 className="font-headline font-black text-headline-lg uppercase text-primary">{nombre} {apellido}</h1>
                <p className="font-bold text-label-md text-on-surface-variant">
                    <span className="text-primary-container">@{username}</span> · {email}
                </p>
            </div>
        </section>
    )
}

export default PerfilHeader
