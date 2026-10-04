const EncabezadoCatalogo = () => {
    return(
        <section className="bg-surface-container-lowest">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <p className="font-bold text-label-tag uppercase tracking-widest text-on-surface-variant mb-space-sm">
                    <a href="/" className="hover:text-primary">Inicio</a> / <span className="text-primary-container">Catálogo</span>
                </p>
                <h1 className="font-headline font-black uppercase text-headline-hero-mobile md:text-headline-hero text-primary">
                    Equipamiento <span className="text-primary-container">& accesorios</span>
                </h1>
                <p className="text-body-lg text-on-surface-variant max-w-2xl mt-space-sm">
                    Racks, barras, discos y todo el equipamiento de fuerza para armar tu gimnasio.
                </p>
            </div>
        </section>
    )
}

export default EncabezadoCatalogo
