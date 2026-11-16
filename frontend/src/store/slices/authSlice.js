import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    token: localStorage.getItem('token') || null,
    usuarioId: localStorage.getItem('usuarioId') || null,
    role: localStorage.getItem('role') || null,
    isAuthenticated: !!localStorage.getItem('token')
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setSesion: (state, action) => {
            const { token, usuarioId, role } = action.payload
            state.token = token ?? state.token
            state.usuarioId = usuarioId ?? state.usuarioId
            state.role = role ?? state.role
            state.isAuthenticated = !!state.token

            if (token !== undefined) {
                if (token) localStorage.setItem('token', token)
                else localStorage.removeItem('token')
            }
            if (usuarioId !== undefined) {
                if (usuarioId) localStorage.setItem('usuarioId', String(usuarioId))
                else localStorage.removeItem('usuarioId')
            }
            if (role !== undefined) {
                if (role) localStorage.setItem('role', role)
                else localStorage.removeItem('role')
            }
        },
        cerrarSesionRedux: (state) => {
            state.token = null
            state.usuarioId = null
            state.role = null
            state.isAuthenticated = false

            localStorage.removeItem('token')
            localStorage.removeItem('usuarioId')
            localStorage.removeItem('role')
            localStorage.removeItem('carritoId')
        }
    }
})

export const { setSesion, cerrarSesionRedux } = authSlice.actions
export default authSlice.reducer
