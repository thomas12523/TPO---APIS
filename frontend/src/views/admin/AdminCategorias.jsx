import PanelAdmin from "../../components/admin/PanelAdmin"
import GestionCategorias from "../../components/admin/GestionCategorias"

const AdminCategorias = () => {
    return(
        <PanelAdmin activa="Categorías" contenido={<GestionCategorias />} />
    )
}

export default AdminCategorias
