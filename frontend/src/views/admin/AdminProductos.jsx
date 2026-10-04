import PanelAdmin from "../../components/admin/PanelAdmin"
import GestionProductos from "../../components/admin/GestionProductos"

const AdminProductos = () => {
    return(
        <PanelAdmin activa="Productos" contenido={<GestionProductos />} />
    )
}

export default AdminProductos
