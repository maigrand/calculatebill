import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: 'auth',
    initialState: {
        isAuthenticated: false,
    },

    reducers: {
        login: (state) => {
            state.isAuthenticated = true;
        },

        logout: (state) => {
            state.isAuthenticated = false;
        },
    }
})

export const { login, logout } = userSlice.actions;
export default userSlice.reducer;
