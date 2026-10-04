const Paginacion = ({pagina, totalPaginas, cambiarPagina}) => {

    if(totalPaginas <= 1){
        return null
    }

    const numeros = Array.from({length: totalPaginas}, (value, index) => index)

    return(
        <nav className="flex items-center justify-end gap-space-xs bg-surface-container-low p-space-md mt-space-lg">
            <button onClick={()=>cambiarPagina(pagina - 1)} disabled={pagina === 0}
                className="w-10 h-10 flex items-center justify-center text-primary hover:bg-surface-container-high disabled:opacity-30">
                <span className="material-symbols-outlined">chevron_left</span>
            </button>
            {
                numeros.map((value)=>(
                    <button key={value} onClick={()=>cambiarPagina(value)}
                        className={`w-10 h-10 font-headline font-extrabold text-headline-sm ${value === pagina ? 'bg-primary-container text-surface-container-lowest' : 'text-primary hover:bg-surface-container-high'}`}>
                        {value + 1}
                    </button>
                ))
            }
            <button onClick={()=>cambiarPagina(pagina + 1)} disabled={pagina === totalPaginas - 1}
                className="w-10 h-10 flex items-center justify-center text-primary hover:bg-surface-container-high disabled:opacity-30">
                <span className="material-symbols-outlined">chevron_right</span>
            </button>
        </nav>
    )
}

export default Paginacion
