import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import PerfilUsuario from "../components/usuario/PerfilUsuario"

const Perfil = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen">
            <div className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-xl">
                <PerfilUsuario />
            </div>
        </main>
        <Footer />
        </>
    )
}

export default Perfil
