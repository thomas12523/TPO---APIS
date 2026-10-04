import Beneficio from "./Beneficio"

const Beneficios = () => {
    return(
        <section className="max-w-7xl mx-auto px-margin md:px-margin-desktop py-space-lg grid grid-cols-1 md:grid-cols-3 gap-space-md">
            <Beneficio icono="local_shipping" titulo="Envío gratis" descripcion="En todos los pedidos de equipamiento" />
            <Beneficio icono="published_with_changes" titulo="Devoluciones" descripcion="Tenés 30 días para devolver tu compra" />
            <Beneficio icono="shield" titulo="Compra segura" descripcion="Tus datos viajan protegidos" />
        </section>
    )
}

export default Beneficios
