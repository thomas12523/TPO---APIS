// Estados de un pedido: PENDIENTE lo pone el checkout y el resto lo avanza el admin
// (el cliente solo puede cancelar mientras está PENDIENTE)

export const iconoEstado = {
    PENDIENTE: 'schedule',
    ACEPTADO: 'task_alt',
    ENVIADO: 'local_shipping',
    ENTREGADO: 'inventory',
    CANCELADO: 'block'
}

// A qué estados puede pasar cada uno (las mismas reglas que valida el back)
export const siguientesEstados = {
    PENDIENTE: ['ACEPTADO', 'CANCELADO'],
    ACEPTADO: ['ENVIADO', 'CANCELADO'],
    ENVIADO: ['ENTREGADO'],
    ENTREGADO: [],
    CANCELADO: []
}
