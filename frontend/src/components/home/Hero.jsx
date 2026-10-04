import hero from "../../assets/hero.jpg"

const Hero = () => {
    return(
        <section className="relative h-[40rem] flex items-center overflow-hidden">
            <img src={hero} alt="Atleta levantando peso" className="absolute inset-0 w-full h-full object-cover opacity-50" />
            <div className="absolute inset-0 bg-gradient-to-r from-background via-background/60 to-transparent"></div>

            <div className="relative max-w-7xl w-full mx-auto px-margin md:px-margin-desktop">
                <p className="flex items-center gap-space-sm font-bold text-label-md uppercase tracking-widest text-primary-container mb-space-md">
                    <span className="w-6 h-0.5 bg-primary-container"></span>
                    Equipamiento profesional
                </p>
                <h1 className="font-headline font-black uppercase text-headline-hero-mobile md:text-headline-hero text-primary">
                    Forjado para el <br />
                    <span className="text-primary-container">rendimiento</span>
                </h1>
                <p className="text-body-lg text-on-surface-variant max-w-md mt-space-lg">
                    Equipá tu gimnasio con equipamiento de nivel profesional, hecho para aguantar tus entrenamientos más pesados.
                </p>
                <div className="flex flex-wrap gap-space-md mt-space-xl">
                    <a href="/catalogo" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-md">
                        Ver catálogo
                    </a>
                </div>
            </div>
        </section>
    )
}

export default Hero
