const Estrellas = ({puntuacion}) => {

    const numeros = [1, 2, 3, 4, 5]

    return(
        <span className="flex text-primary-container">
            {
                numeros.map((value)=>(
                    <span key={value} className="material-symbols-outlined text-lg" style={{fontVariationSettings: `'FILL' ${value <= puntuacion ? 1 : 0}`}}>star</span>
                ))
            }
        </span>
    )
}

export default Estrellas
