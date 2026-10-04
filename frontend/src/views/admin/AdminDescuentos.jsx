import PanelAdmin from "../../components/admin/PanelAdmin"
import GestionDescuentos from "../../components/admin/GestionDescuentos"

const AdminDescuentos = () => {
    return(
        <PanelAdmin activa="Descuentos" contenido={<GestionDescuentos />} />
    )
}

export default AdminDescuentos
