
export const iconoEstado = {
    PENDIENTE: 'schedule',
    ACEPTADO: 'task_alt',
    ENVIADO: 'local_shipping',
    ENTREGADO: 'inventory',
    CANCELADO: 'block'
}

export const siguientesEstados = {
    PENDIENTE: ['ACEPTADO', 'CANCELADO'],
    ACEPTADO: ['ENVIADO', 'CANCELADO'],
    ENVIADO: ['ENTREGADO'],
    ENTREGADO: [],
    CANCELADO: []
}
