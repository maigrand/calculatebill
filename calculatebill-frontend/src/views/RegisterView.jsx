import React, {useState} from "react";
import {Link} from "react-router-dom";

import AuthService from "../services/AuthService";

import '../styles/_authView.scss';

const RegisterView = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [isConfirmFieldDirty, setConfirmFieldDirty] = useState(false);

    const handleChangeEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleChangePassword = (e) => {
        setPassword(e.target.value);
    }

    const handleConfirmPassword = (e) => {
        if (!isConfirmFieldDirty) setConfirmFieldDirty(true);
        setConfirmPassword(e.target.value);
    }

    const handleRegister = async (e) => {
      e.preventDefault();

      try {
          await AuthService.register({
              email, password
          });
      } catch (e) {
          console.error(e.message);
      }
    };

    return (
        <div className='auth-view__container'>
            <form className="auth-view__block" onSubmit={handleRegister}>
                <header>
                    <div className="auth-view__block-desc">
                        <h1>
                            Calculate your bill with friends faster!
                        </h1>
                        <p>We create this app for friends, just calculate and chill :)</p>
                    </div>
                    <h2>
                        Sign up.
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
                    <div className='auth-view__input-container'>
                        <input
                            type="password"
                            id='confirm-password'
                            placeholder="Enter your password"
                            onChange={handleConfirmPassword} />
                        <label form='confirm-password'>Confirm password</label>
                    </div>
                </main>

                <footer>
                    <button type='submit'>Sign up</button>
                    <div className='auth-view__footer-link'>
                        <span>Already have account?</span>
                        <Link to='/login'>Sign in!</Link>
                    </div>
                </footer>
            </form>
        </div>
    );
}

export default RegisterView;