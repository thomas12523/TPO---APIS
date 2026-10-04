import PanelAdmin from "../../components/admin/PanelAdmin"
import GestionUsuarios from "../../components/admin/GestionUsuarios"

const AdminUsuarios = () => {
    return(
        <PanelAdmin activa="Usuarios" contenido={<GestionUsuarios />} />
    )
}

export default AdminUsuarios
