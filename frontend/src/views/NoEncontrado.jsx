import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"

const NoEncontrado = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20 min-h-screen flex items-center justify-center px-margin">
            <div className="flex flex-col items-center text-center gap-space-md">
                <p className="font-headline font-black text-[10rem] leading-none text-primary-container">404</p>
                <h1 className="font-headline font-black text-headline-lg uppercase text-primary">Página no encontrada</h1>
                <p className="text-body-lg text-on-surface-variant max-w-md">La página que buscás no existe o cambió de lugar.</p>
                <a href="/" className="bg-primary-container text-surface-container-lowest font-bold text-label-lg uppercase px-space-lg py-space-md mt-space-md hover:bg-primary">
                    Volver al inicio
                </a>
            </div>
        </main>
        <Footer />
        </>
    )
}

export default NoEncontrado
