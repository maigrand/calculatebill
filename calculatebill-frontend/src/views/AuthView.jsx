import React, {useState} from 'react'
import { useDispatch } from "react-redux";
import { login, logout } from "../store/reducers/user";
import AuthService from "../services/AuthService";

import '../styles/_authView.scss'

const AuthView = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const dispatch = useDispatch();

    const handleLogin = async (e) => {
        e.preventDefault();

        await AuthService.login({
            email, password
        }).then((data) => {
            localStorage.setItem('token', data.token);
            dispatch(login());
        }).catch((e) => {
            localStorage.removeItem('token');
            dispatch(logout());
            console.error(e.message);
        });
    };

    const handleChangeEmail = (e) => {
        setEmail(e.target.value);
    }

    const handleChangePassword = (e) => {
        setPassword(e.target.value);
    }

    return (
        <div className='auth-view__container'>
            <form className="auth-view__block" onSubmit={handleLogin}>
                <header>
                    <h2>
                        Sign in.
                    </h2>
                </header>

                <main>
                    <div className='auth-view__input-container'>
                        <label form='email'>Email</label>
                        <input
                            type="email"
                            id='email'
                            placeholder="Enter your email"
                            onChange={handleChangeEmail} />
                    </div>
                    <div className='auth-view__input-container'>
                        <label form='password'>Password</label>
                        <input
                            type="password"
                            id='password'
                            placeholder="Enter your password"
                            onChange={handleChangePassword} />
                    </div>
                </main>

                <footer>
                    <button type='submit'>Log in</button>
                </footer>
            </form>
        </div>
    )
}

export default AuthView
