import ResenaCard from "../ResenaCard"

const ListaResenas = ({resenas}) => {
    if(resenas.length === 0){
        return <p className="text-body-lg text-on-surface-variant">Todavía no hay reseñas. ¡Sé el primero!</p>
    }

    return(
        <div className="grid grid-cols-1 md:grid-cols-2 gap-space-md h-fit">
            {
                resenas.map((value)=>(
                    <ResenaCard
                    key={value.resenaId}
                    username={value.username}
                    puntuacion={value.puntuacion}
                    comentario={value.comentario}
                    fecha={value.fecha}
                    />
                ))
            }
        </div>
    )
}

export default ListaResenas
