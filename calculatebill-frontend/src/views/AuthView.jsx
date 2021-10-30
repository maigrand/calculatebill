import React, {useState} from 'react'
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { login, logout, setUser } from "../store/reducers/user";

import EmailInput from "../components/ui/EmailInput";

import AuthService from "../services/AuthService";

import PasswordInput from "../components/ui/PasswordInput";

import '../styles/_authView.scss';

const AuthView = () => {
    const [state, setState] = useState({
        email: '', password: '',
    })

    const dispatch = useDispatch();

    const handleLogin = async (e) => {
        e.preventDefault();

        await AuthService.login({
            email: state.email,
            password: state.password
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

    const handleChangeEmail = (value) => {
        setState(prevState => {
           return { ...prevState, email: value };
        });
    };

    const handleChangePassword = (value) => {
        setState(prevState => {
            return { ...prevState, password: value };
        })
    }

    return (
        <div className='form__container'>
            <form className="form__wrapper" onSubmit={handleLogin}>
                <header>
                    <div className="auth-view__block-desc">
                        <strong>
                            Calculate your bill with friends faster!
                        </strong>
                        <p>We create this app for friends, just calculate and chill :)</p>
                    </div>
                    <h2>
                        Sign in.
                    </h2>
                </header>

                <main>
                    <EmailInput
                        placeholder="Enter your email"
                        label="Email"
                        onchange={(value) => handleChangeEmail(value)}
                    />
                    <PasswordInput
                        placeholder="Enter your password"
                        label="Password"
                        onchange={(value) => handleChangePassword(value) }
                    />
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
