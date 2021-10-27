import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: 'auth',
    initialState: {
        data: {},
        isAuthenticated: false,
    },

    reducers: {
        login: (state) => {
            state.isAuthenticated = true;
        },

        setUser: (state, action) => {
            state.data = action.payload;
        },

        logout: (state) => {
            state.isAuthenticated = false;
        },
    }
})

export const { login, logout, setUser } = userSlice.actions;
export default userSlice.reducer;
