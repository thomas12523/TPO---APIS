import { useState } from "react"
import Navbar from "../components/layout/Navbar"
import Footer from "../components/layout/Footer"
import Hero from "../components/home/Hero"
import Beneficios from "../components/home/Beneficios"
import CategoriaGrid from "../components/home/CategoriaGrid"
import ProductosDestacados from "../components/home/ProductosDestacados"
import FondoHome from "../components/home/FondoHome"

const Home = () => {

    // Sube en uno cada vez que se agrega algo al carrito, así el navbar actualiza el contador
    const [cambiosCarrito, setCambiosCarrito] = useState(0)
    const carritoCambio = () => setCambiosCarrito((valor)=>valor + 1)

    return(
        <>
        <Navbar cambiosCarrito={cambiosCarrito} />
        <main className="pt-20">
            <Hero />
            <FondoHome>
                <Beneficios />
                <CategoriaGrid />
                <ProductosDestacados carritoCambio={carritoCambio} />
            </FondoHome>
        </main>
        <Footer />
        </>
    )
}

export default Home
