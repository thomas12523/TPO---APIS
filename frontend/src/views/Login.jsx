import AccesoEncabezado from "../components/usuario/AccesoEncabezado"
import LoginForm from "../components/usuario/LoginForm"

const Login = () => {
    return(
        <main className="min-h-screen flex items-center justify-center px-margin py-space-xl bg-gradient-to-br from-background to-surface-container-lowest">
            <div className="w-full max-w-md bg-surface-container-low border border-white/5 p-space-xl">
                <AccesoEncabezado activa="login" />
                <LoginForm />
            </div>
        </main>
    )
}

export default Login
