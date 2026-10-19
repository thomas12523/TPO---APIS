import hero from "../../assets/hero.jpg"

const FondoHome = ({children}) => {
    return(
        <div className="relative overflow-hidden">
            <img src={hero} alt="" className="absolute inset-0 w-full h-full object-cover opacity-35" />
            {}
            <div className="absolute inset-0 bg-gradient-to-b from-background via-background/30 to-background"></div>
            <div className="relative">
                {children}
            </div>
        </div>
    )
}

export default FondoHome
