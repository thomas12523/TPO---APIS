// Ventana flotante: se muestra encima de la página, sin importar dónde esté el scroll.
// Clic afuera (en el fondo oscuro) la cierra; clic adentro no
const Modal = ({cerrar, children}) => {
    return(
        <div onClick={cerrar} className="fixed inset-0 z-[60] bg-black/70 flex items-center justify-center p-space-md">
            <div onClick={(e)=>e.stopPropagation()} className="w-full max-w-2xl max-h-[90vh] overflow-y-auto">
                {children}
            </div>
        </div>
    )
}

export default Modal
