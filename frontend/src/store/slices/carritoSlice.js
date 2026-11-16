import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import { obtenerCarrito } from '../../utils/carrito'

export const fetchCarrito = createAsyncThunk(
    'carrito/fetchCarrito',
    async (_, { rejectWithValue }) => {
        try {
            const carritoId = localStorage.getItem('carritoId')
            if (!carritoId) return null
            const data = await obtenerCarrito()
            return data
        } catch (error) {
            return rejectWithValue(error.message)
        }
    }
)

const initialState = {
    datos: null,
    items: [],
    cantidadTotal: 0,
    carritoId: localStorage.getItem('carritoId') || null,
    cargando: false,
    error: null,
    animarBadge: false
}

export const carritoSlice = createSlice({
    name: 'carrito',
    initialState,
    reducers: {
        setCarrito: (state, action) => {
            state.datos = action.payload
            state.items = action.payload?.items || []
            state.cantidadTotal = state.items.length
            state.error = null
        },
        vaciarCarrito: (state) => {
            state.datos = { items: [], subtotal: 0, total: 0 }
            state.items = []
            state.cantidadTotal = 0
            state.animarBadge = false
            state.error = null
        },
        setCarritoId: (state, action) => {
            state.carritoId = action.payload
            if (action.payload) {
                localStorage.setItem('carritoId', String(action.payload))
            } else {
                localStorage.removeItem('carritoId')
            }
        },
        limpiarCarrito: (state) => {
            state.datos = null
            state.items = []
            state.cantidadTotal = 0
            state.carritoId = null
            state.error = null
            state.animarBadge = false
            localStorage.removeItem('carritoId')
        },
        activarAnimacion: (state) => {
            state.animarBadge = true
        },
        desactivarAnimacion: (state) => {
            state.animarBadge = false
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchCarrito.pending, (state) => {
                state.cargando = true
                state.error = null
            })
            .addCase(fetchCarrito.fulfilled, (state, action) => {
                state.cargando = false
                if (action.payload) {
                    state.datos = action.payload
                    state.items = action.payload.items || []
                    state.cantidadTotal = state.items.length
                }
            })
            .addCase(fetchCarrito.rejected, (state, action) => {
                state.cargando = false
                state.error = action.payload || 'Error al obtener el carrito'
            })
    }
})

export const { setCarrito, vaciarCarrito, setCarritoId, limpiarCarrito, activarAnimacion, desactivarAnimacion } = carritoSlice.actions
export default carritoSlice.reducer
