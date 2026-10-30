import PanelAdmin from "../../components/admin/PanelAdmin"
import GestionPedidos from "../../components/admin/GestionPedidos"

const AdminPedidos = () => {
    return(
        <PanelAdmin activa="Pedidos" contenido={<GestionPedidos />} />
    )
}

export default AdminPedidos
