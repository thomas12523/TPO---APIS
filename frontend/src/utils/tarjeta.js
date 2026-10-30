// Simulación de pago con tarjeta: nada se manda al back, solo validamos que los datos tengan sentido

export const tarjetaVacia = {numero: '', titular: '', vencimiento: '', cvv: '', dni: '', cuotas: '1'}

const soloDigitos = (texto) => texto.replace(/\D/g, '')

// La marca sale de los primeros dígitos (así lo hacen las tarjetas reales)
export const marcaTarjeta = (numero) => {
    const digitos = soloDigitos(numero)
    if(/^4/.test(digitos)){
        return 'VISA'
    }
    if(/^(5[1-5]|2[2-7])/.test(digitos)){
        return 'MASTERCARD'
    }
    if(/^3[47]/.test(digitos)){
        return 'AMEX'
    }
    return ''
}

// American Express tiene 15 dígitos y CVV de 4; las demás, 16 y 3
const largoNumero = (numero) => marcaTarjeta(numero) === 'AMEX' ? 15 : 16
const largoCvv = (numero) => marcaTarjeta(numero) === 'AMEX' ? 4 : 3

// Cada campo se va formateando mientras el usuario escribe (solo deja escribir lo que corresponde)
export const formatearCampo = (name, value, numero) => {
    if(name === 'numero'){
        // Bloques de 4: 4111 1111 1111 1111
        return soloDigitos(value).slice(0, largoNumero(value)).replace(/(\d{4})(?=\d)/g, '$1 ')
    }
    if(name === 'vencimiento'){
        // MM/AA: la barra se pone sola
        const digitos = soloDigitos(value).slice(0, 4)
        return digitos.length > 2 ? `${digitos.slice(0, 2)}/${digitos.slice(2)}` : digitos
    }
    if(name === 'cvv'){
        return soloDigitos(value).slice(0, largoCvv(numero))
    }
    if(name === 'dni'){
        return soloDigitos(value).slice(0, 8)
    }
    if(name === 'titular'){
        return value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]/g, '').toUpperCase()
    }
    return value
}

// Algoritmo de Luhn: el dígito verificador que tienen todas las tarjetas de verdad
const pasaLuhn = (digitos) => {
    const suma = [...digitos].reverse()
        .map((value, index)=>{
            const numero = Number(value)
            if(index % 2 === 0){
                return numero
            }
            return numero * 2 > 9 ? numero * 2 - 9 : numero * 2
        })
        .reduce((total, value)=>total + value, 0)
    return suma % 10 === 0
}

// Vencida si el mes ya pasó (vence el último día del mes que figura)
const estaVencida = (mes, anio) => {
    const hoy = new Date()
    const anioActual = hoy.getFullYear() % 100
    const mesActual = hoy.getMonth() + 1
    return anio < anioActual || (anio === anioActual && mes < mesActual)
}

// Devuelve el primer error que encuentra, o '' si la tarjeta está bien
export const validarTarjeta = (tarjeta) => {
    const digitos = soloDigitos(tarjeta.numero)
    if(Object.values(tarjeta).some((value)=>value.trim() === '')){
        return 'Completá todos los datos de la tarjeta'
    }
    if(marcaTarjeta(digitos) === ''){
        return 'Solo aceptamos Visa, Mastercard o American Express'
    }
    if(digitos.length !== largoNumero(digitos) || !pasaLuhn(digitos)){
        return 'El número de tarjeta no es válido'
    }
    if(tarjeta.titular.trim().split(/\s+/).length < 2){
        return 'Escribí el nombre y apellido como figuran en la tarjeta'
    }
    const [mes, anio] = tarjeta.vencimiento.split('/').map(Number)
    if(tarjeta.vencimiento.length !== 5 || mes < 1 || mes > 12){
        return 'El vencimiento tiene que ser MM/AA'
    }
    if(estaVencida(mes, anio)){
        return 'La tarjeta está vencida'
    }
    if(tarjeta.cvv.length !== largoCvv(digitos)){
        return `El código de seguridad tiene ${largoCvv(digitos)} dígitos`
    }
    if(tarjeta.dni.length < 7){
        return 'El DNI del titular tiene que tener 7 u 8 dígitos'
    }
    return ''
}
