import AccesoEncabezado from "../components/usuario/AccesoEncabezado"
import RegistroForm from "../components/usuario/RegistroForm"

const Registro = () => {
    return(
        <main className="min-h-screen flex items-center justify-center px-margin py-space-xl bg-gradient-to-br from-background to-surface-container-lowest">
            <div className="w-full max-w-lg bg-surface-container-low border border-white/5 p-space-xl">
                <AccesoEncabezado activa="registro" />
                <RegistroForm />
            </div>
        </main>
    )
}

export default Registro
