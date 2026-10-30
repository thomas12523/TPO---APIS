import MetodoPagoOpcion from "../MetodoPagoOpcion"

const metodosPago = [
    {valor: 'TARJETA_CREDITO', titulo: 'Tarjeta de crédito', descripcion: 'Hasta 6 cuotas sin interés', icono: 'credit_card'},
    {valor: 'TARJETA_DEBITO', titulo: 'Tarjeta de débito', descripcion: 'Se debita en el momento', icono: 'payments'}
]

const SeleccionMetodoPago = ({metodoPago, handleChange}) => {
    return(
        <div className="grid grid-cols-1 md:grid-cols-2 gap-space-sm">
            {
                metodosPago.map((value)=>(
                    <MetodoPagoOpcion
                    key={value.valor}
                    valor={value.valor}
                    titulo={value.titulo}
                    descripcion={value.descripcion}
                    icono={value.icono}
                    seleccionado={metodoPago === value.valor}
                    handleChange={handleChange}
                    />
                ))
            }
        </div>
    )
}

export default SeleccionMetodoPago
