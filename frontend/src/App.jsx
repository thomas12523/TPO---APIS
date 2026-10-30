import { Routes, Route } from "react-router-dom"
import Home from "./views/Home"
import Catalogo from "./views/Catalogo"
import DetalleProducto from "./views/DetalleProducto"
import Carrito from "./views/Carrito"
import Checkout from "./views/Checkout"
import PedidoConfirmado from "./views/PedidoConfirmado"
import MisPedidos from "./views/MisPedidos"
import Perfil from "./views/Perfil"
import Login from "./views/Login"
import Registro from "./views/Registro"
import NoEncontrado from "./views/NoEncontrado"
import AdminProductos from "./views/admin/AdminProductos"
import AdminCategorias from "./views/admin/AdminCategorias"
import AdminDescuentos from "./views/admin/AdminDescuentos"
import AdminUsuarios from "./views/admin/AdminUsuarios"
import AdminPedidos from "./views/admin/AdminPedidos"

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/catalogo" element={<Catalogo />} />
        <Route path="/producto/:productoId" element={<DetalleProducto />} />
        <Route path="/carrito" element={<Carrito />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/pedido-confirmado" element={<PedidoConfirmado />} />
        <Route path="/mis-pedidos" element={<MisPedidos />} />
        <Route path="/perfil" element={<Perfil />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/admin/productos" element={<AdminProductos />} />
        <Route path="/admin/categorias" element={<AdminCategorias />} />
        <Route path="/admin/descuentos" element={<AdminDescuentos />} />
        <Route path="/admin/usuarios" element={<AdminUsuarios />} />
        <Route path="/admin/pedidos" element={<AdminPedidos />} />
        <Route path="*" element={<NoEncontrado />} />
      </Routes>
    </>
  )
}

export default App
