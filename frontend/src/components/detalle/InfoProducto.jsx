import { useState } from "react"
import Precio from "../ui/Precio"
import SelectorCantidad from "../ui/SelectorCantidad"
import BotonPrincipal from "../ui/BotonPrincipal"

const InfoProducto = ({nombreProducto, categoriaNombre, descripcion, precioUnitario, precioConDescuento, tieneDescuento, stock}) => {

    const [cantidad, setCantidad] = useState(1)

    return(
        <div className="flex flex-col gap-space-lg">
            <div>
                <span className="inline-block bg-primary-container text-surface-container-lowest font-bold text-label-md uppercase px-space-sm py-space-xs mb-space-md">{categoriaNombre}</span>
                <h1 className="font-headline font-black uppercase text-headline-hero-mobile md:text-headline-hero text-primary">{nombreProducto}</h1>
            </div>

            <div className="bg-surface-container-low p-space-lg">
                <Precio precioUnitario={precioUnitario} precioConDescuento={precioConDescuento} tieneDescuento={tieneDescuento} />
                <p className={`font-bold text-label-md uppercase mt-space-sm ${stock > 0 ? 'text-primary-container' : 'text-error'}`}>
                    {stock > 0 ? `${stock} unidades en stock` : 'Sin stock'}
                </p>
            </div>

            <p className="text-body-lg text-on-surface-variant">{descripcion}</p>

            {stock > 0 && (
                <div className="flex items-center gap-space-md">
                    <SelectorCantidad cantidad={cantidad} cambiarCantidad={setCantidad} maximo={stock} />
                    <BotonPrincipal texto="Agregar al carrito" icono="shopping_cart" />
                </div>
            )}
        </div>
    )
}

export default InfoProducto
