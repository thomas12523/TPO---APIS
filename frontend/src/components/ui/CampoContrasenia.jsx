import { useState } from "react"

const CampoContrasenia = ({label, name, value, onChange}) => {

    const [visible, setVisible] = useState(false)

    return(
        <div className="flex flex-col gap-space-xs">
            <label htmlFor={name} className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">{label}</label>
            <div className="relative">
                <input
                    id={name}
                    type={visible ? 'text' : 'password'}
                    name={name}
                    value={value}
                    onChange={onChange}
                    className="w-full bg-surface-container-high text-on-surface text-body-lg px-space-md py-space-sm pr-12 border border-white/10 focus:border-primary-container focus:outline-none"
                />
                <button type="button" onClick={()=>setVisible(!visible)} title="Mostrar u ocultar"
                    className="absolute right-space-sm top-1/2 -translate-y-1/2 text-on-surface-variant hover:text-primary">
                    <span className="material-symbols-outlined">{visible ? 'visibility_off' : 'visibility'}</span>
                </button>
            </div>
        </div>
    )
}

export default CampoContrasenia
