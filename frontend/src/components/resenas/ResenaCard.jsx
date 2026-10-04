import Estrellas from "../ui/Estrellas"

const ResenaCard = ({username, puntuacion, comentario, fecha}) => {
    return(
        <article className="bg-surface-container-low border border-white/5 p-space-lg flex flex-col gap-space-sm">
            <div className="flex items-center justify-between">
                <Estrellas puntuacion={puntuacion} />
                <span className="font-bold text-label-tag uppercase text-on-surface-variant">{fecha}</span>
            </div>
            <p className="text-body-md text-on-surface">{comentario}</p>
            <p className="flex items-center gap-space-xs font-bold text-label-tag uppercase tracking-wider text-primary-container mt-auto">
                <span className="material-symbols-outlined text-base">verified_user</span>
                {username}
            </p>
        </article>
    )
}

export default ResenaCard
