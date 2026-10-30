import hero from "../../assets/hero.jpg"

// Fondo con la foto del hero, bien oscurecida, para la parte de abajo de la home.
// Lo que va adentro (children) se muestra encima de la foto
const FondoHome = ({children}) => {
    return(
        <div className="relative overflow-hidden">
            <img src={hero} alt="" className="absolute inset-0 w-full h-full object-cover opacity-35" />
            {/* Degradé arriba y abajo para que la foto se funda con el video y con el footer */}
            <div className="absolute inset-0 bg-gradient-to-b from-background via-background/30 to-background"></div>
            <div className="relative">
                {children}
            </div>
        </div>
    )
}

export default FondoHome
