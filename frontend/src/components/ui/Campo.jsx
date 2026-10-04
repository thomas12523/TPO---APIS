const Campo = ({label, type = 'text', name, value, onChange, placeholder}) => {
    return(
        <div className="flex flex-col gap-space-xs">
            <label htmlFor={name} className="font-bold text-label-md uppercase tracking-wider text-on-surface-variant">{label}</label>
            <input
                id={name}
                type={type}
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                className="bg-surface-container-high text-on-surface text-body-lg px-space-md py-space-sm border border-white/10 focus:border-primary-container focus:outline-none"
            />
        </div>
    )
}

export default Campo
