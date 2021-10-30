import React, {useState} from "react";
import {Link} from "react-router-dom";

import AuthService from "../services/AuthService";

import EmailInput from "../components/ui/EmailInput.jsx";
import PasswordInput from "../components/ui/PasswordInput.jsx";

import "../styles/_authView.scss";

const RegisterView = () => {
    const [state, setState] = useState({
       email: '',
       password: '',
       confirmPassword: '',
    });

    const [isConfirmFieldDirty, setConfirmFieldDirty] = useState(false);

    const handleChangeEmail = (value) => {
        setState(prevState => {
            return { ...prevState, email: value };
        })
    };

    const handleChangePassword = (value) => {
        setState(prevState => {
            return { ...prevState, password: value };
        })
    }

    const handleConfirmPassword = (value) => {
        setState(prevState => {
            return { ...prevState, confirmPassword: value };
        })
    }

    const handleRegister = async (e) => {
      e.preventDefault();

      try {
          await AuthService.register({
              email: state.email, password: state.password
          });
      } catch (e) {
          console.error(e.message);
      }
    };

    return (
        <div className='form__container'>
            <form className="form__wrapper" onSubmit={handleRegister}>
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
                    <EmailInput
                        placeholder="Enter your email"
                        label="Email"
                        onchange={(value) => handleChangeEmail(value)}
                    />
                    <PasswordInput
                        placeholder="Enter your password"
                        label="Password"
                        onchange={(value) => handleChangePassword(value)}
                    />
                    <PasswordInput
                        placeholder="Confirm your password"
                        label="Confirm password"
                        onchange={(value) => handleConfirmPassword(value)}
                    />
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