// Ocupa el lugar del formulario cuando el usuario no puede dejar una reseña
const AvisoResena = ({icono, mensaje}) => {
    return(
        <div className="bg-surface-container-low p-space-xl flex flex-col items-center text-center gap-space-md h-fit">
            <span className="material-symbols-outlined text-primary-container text-5xl">{icono}</span>
            <p className="text-body-lg text-on-surface-variant">{mensaje}</p>
        </div>
    )
}

export default AvisoResena
