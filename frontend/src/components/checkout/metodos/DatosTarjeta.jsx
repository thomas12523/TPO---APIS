import Campo from "../../ui/Campo"
import CampoSelect from "../../ui/CampoSelect"
import { marcaTarjeta } from "../../../utils/tarjeta"

const cuotasPosibles = [1, 3, 6]

// Los datos los guarda FormCheckout: acá solo se muestran y se avisa cada cambio
const DatosTarjeta = ({tarjeta, handleChange, conCuotas, total}) => {

    const marca = marcaTarjeta(tarjeta.numero)

    return(
        <div className="flex flex-col gap-space-md">
            {/* Vista previa de la tarjeta, se completa mientras el usuario escribe */}
            <div className="max-w-sm aspect-[1.6] p-space-lg flex flex-col justify-between bg-linear-to-br from-surface-container-highest to-surface-container-low border border-white/10">
                <div className="flex items-center justify-between">
                    <span className="material-symbols-outlined text-primary-container text-3xl">memory</span>
                    <span className="font-headline font-black italic text-headline-sm text-primary">{marca}</span>
                </div>
                <p className="font-headline font-bold text-headline-sm tracking-widest text-primary">{tarjeta.numero === '' ? '•••• •••• •••• ••••' : tarjeta.numero}</p>
                <div className="flex justify-between text-label-md uppercase text-on-surface-variant">
                    <span className="truncate">{tarjeta.titular === '' ? 'NOMBRE Y APELLIDO' : tarjeta.titular}</span>
                    <span>{tarjeta.vencimiento === '' ? 'MM/AA' : tarjeta.vencimiento}</span>
                </div>
            </div>

            <Campo label="Número de tarjeta" name="numero" value={tarjeta.numero} onChange={handleChange} placeholder="1234 5678 9012 3456" />
            <Campo label="Nombre y apellido (como figura en la tarjeta)" name="titular" value={tarjeta.titular} onChange={handleChange} />
            <div className="grid grid-cols-1 sm:grid-cols-3 gap-space-md">
                <Campo label="Vencimiento" name="vencimiento" value={tarjeta.vencimiento} onChange={handleChange} placeholder="MM/AA" />
                <Campo label="Código de seguridad" type="password" name="cvv" value={tarjeta.cvv} onChange={handleChange} placeholder={marca === 'AMEX' ? '4 dígitos' : '3 dígitos'} />
                <Campo label="DNI del titular" name="dni" value={tarjeta.dni} onChange={handleChange} />
            </div>
            {conCuotas && (
                <CampoSelect label="Cuotas" name="cuotas" value={tarjeta.cuotas} onChange={handleChange}
                    opciones={cuotasPosibles.map((value)=>({valor: String(value), texto: `${value} ${value === 1 ? 'cuota' : 'cuotas'} de $${(total / value).toFixed(2)} sin interés`}))} />
            )}
        </div>
    )
}

export default DatosTarjeta
