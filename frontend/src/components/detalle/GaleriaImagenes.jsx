import { useState } from "react"

const GaleriaImagenes = ({imagenes, nombreProducto}) => {

    const [seleccionada, setSeleccionada] = useState(0)

    if(imagenes.length === 0){
        return <div className="aspect-[4/3] bg-surface-container-low"></div>
    }

    return(
        <div className="flex flex-col gap-space-md">
            <div className="aspect-[4/3] overflow-hidden bg-surface-container-low">
                <img src={imagenes[seleccionada]} alt={nombreProducto} className="w-full h-full object-cover" />
            </div>
            <div className="grid grid-cols-5 gap-space-sm">
                {
                    imagenes.map((value, index)=>(
                        <button key={index} onClick={()=>setSeleccionada(index)}
                            className={`aspect-square overflow-hidden border-2 ${index === seleccionada ? 'border-primary-container' : 'border-transparent opacity-60 hover:opacity-100'}`}>
                            <img src={value} alt={`${nombreProducto} ${index + 1}`} className="w-full h-full object-cover" />
                        </button>
                    ))
                }
            </div>
        </div>
    )
}

export default GaleriaImagenes
