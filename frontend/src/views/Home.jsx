import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import Hero from "../components/home/Hero"
import Beneficios from "../components/home/Beneficios"
import CategoriaGrid from "../components/home/CategoriaGrid"
import ProductosDestacados from "../components/home/ProductosDestacados"

const Home = () => {
    return(
        <>
        <Navbar />
        <main className="pt-20">
            <Hero />
            <Beneficios />
            <CategoriaGrid />
            <ProductosDestacados />
        </main>
        <Footer />
        </>
    )
}

export default Home
