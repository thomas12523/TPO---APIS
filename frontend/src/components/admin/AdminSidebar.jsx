const secciones = [
    {ruta: '/admin/productos', texto: 'Productos', icono: 'inventory_2'},
    {ruta: '/admin/categorias', texto: 'Categorías', icono: 'category'},
    {ruta: '/admin/descuentos', texto: 'Descuentos', icono: 'sell'},
    {ruta: '/admin/usuarios', texto: 'Usuarios', icono: 'group'}
]

const AdminSidebar = ({activa}) => {
    return(
        <aside className="bg-surface-container-low p-space-md h-fit flex flex-col gap-space-xs">
            <p className="flex items-center gap-space-xs font-bold text-label-tag uppercase tracking-widest text-primary-container px-space-sm py-space-sm">
                <span className="material-symbols-outlined text-base">admin_panel_settings</span>
                Panel admin
            </p>
            {
                secciones.map((value)=>(
                    <a key={value.ruta} href={value.ruta}
                        className={`flex items-center gap-space-sm px-space-sm py-space-sm font-bold text-label-lg uppercase
                        ${activa === value.texto ? 'bg-primary-container text-surface-container-lowest' : 'text-on-surface-variant hover:bg-surface-container hover:text-primary'}`}>
                        <span className="material-symbols-outlined">{value.icono}</span>
                        {value.texto}
                    </a>
                ))
            }
        </aside>
    )
}

export default AdminSidebar
