import Home from "./views/Home"

// Hasta que veamos ruteo, se cambia a mano la vista que se muestra.
// Vistas disponibles en ./views: Home, Catalogo, DetalleProducto, Carrito, Checkout,
// PedidoConfirmado, MisPedidos, Perfil, Login, Registro, NoEncontrado
// y en ./views/admin: AdminProductos, AdminCategorias, AdminDescuentos, AdminUsuarios
const App = () => {
  return (
    <>
      <Home />
    </>
  )
}

export default App
