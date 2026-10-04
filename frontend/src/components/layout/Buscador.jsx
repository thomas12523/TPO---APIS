import { useState } from "react"

const Buscador = () => {

    const [nombre, setNombre] = useState('')

    const handleChange = (e) => {setNombre(e.target.value)}

    return(
        <form onSubmit={(e)=>{e.preventDefault()}} className="hidden xl:flex flex-1 max-w-md mx-space-md">
            <div className="relative w-full">
                <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant">search</span>
                <input
                    type="text"
                    name="nombre"
                    value={nombre}
                    onChange={handleChange}
                    placeholder="BUSCAR EQUIPO, DISCOS, BARRAS..."
                    className="w-full bg-surface-container-high text-on-surface placeholder:text-on-surface-variant/60 font-bold text-label-md pl-10 pr-space-md py-2.5 focus:outline-none focus:bg-surface-container-highest"
                />
            </div>
        </form>
    )
}

export default Buscador
