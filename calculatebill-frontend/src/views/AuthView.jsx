import React, {useState} from 'react'
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { login, logout, setUser } from "../store/reducers/user";
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

        const token = localStorage.getItem('token');

        await AuthService.getUser(token)
            .then((data) => {
                dispatch(setUser(data));
            })
            .catch((e) => {
                localStorage.removeItem('token');
                dispatch(logout());
                console.error(e.message);
            })
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
                    <div className="auth-view__block-desc">
                        <h1>
                            Calculate your bill with friends faster!
                        </h1>
                        <p>We create this app for friends, just calculate and chill :)</p>
                    </div>
                    <h2>
                        Sign in.
                    </h2>
                </header>

                <main>
                    <div className='auth-view__input-container'>
                        <input
                            type="email"
                            id='email'
                            placeholder="Enter your email"
                            onChange={handleChangeEmail} />
                        <label form='email'>Email</label>
                    </div>
                    <div className='auth-view__input-container'>
                        <input
                            type="password"
                            id='password'
                            placeholder="Enter your password"
                            onChange={handleChangePassword} />
                        <label form='password'>Password</label>
                    </div>
                </main>

                <footer>
                    <button type='submit'>Log in</button>
                    <div className='auth-view__footer-link'>
                        <span>Don't have account?</span>
                        <Link to='/register'>Sign up!</Link>
                    </div>
                </footer>
            </form>
        </div>
    )
}

export default AuthView;
