
export const tarjetaVacia = {numero: '', titular: '', vencimiento: '', cvv: '', dni: '', cuotas: '1'}

const soloDigitos = (texto) => texto.replace(/\D/g, '')

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

const largoNumero = (numero) => marcaTarjeta(numero) === 'AMEX' ? 15 : 16
const largoCvv = (numero) => marcaTarjeta(numero) === 'AMEX' ? 4 : 3

export const formatearCampo = (name, value, numero) => {
    if(name === 'numero'){

        return soloDigitos(value).slice(0, largoNumero(value)).replace(/(\d{4})(?=\d)/g, '$1 ')
    }
    if(name === 'vencimiento'){

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

const estaVencida = (mes, anio) => {
    const hoy = new Date()
    const anioActual = hoy.getFullYear() % 100
    const mesActual = hoy.getMonth() + 1
    return anio < anioActual || (anio === anioActual && mes < mesActual)
}

export const validarTarjeta = (tarjeta) => {
    const digitos = soloDigitos(tarjeta.numero)
    if(Object.values(tarjeta).some((value)=>value.trim() === '')){
        return 'Completá todos los datos de la tarjeta'
    }
    if(marcaTarjeta(digitos) === ''){
        return 'Solo aceptamos Visa, Mastercard o American Express'
    }
    if(digitos.length !== largoNumero(digitos)){
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
