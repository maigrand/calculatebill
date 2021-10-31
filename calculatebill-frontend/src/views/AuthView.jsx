import React, {useState} from 'react'
import {Link} from "react-router-dom";

import EmailInput from "../components/ui/EmailInput.jsx";
import PasswordInput from "../components/ui/PasswordInput.jsx";

import {login} from "../store/actions/userActions";
import {clearErrors} from "../store/actions/errorActions";
import {connect} from "react-redux";

import '../styles/_authView.scss';

const AuthView = ({login}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleChangeEmail = (value) => setEmail(value);
  const handleChangePassword = (value) => setPassword(value);

  const handleLogin = async (e) => {
    e.preventDefault();

    await login({email, password});
  };

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
                onchange={(value) => handleChangeEmail(value)}
            />
            <PasswordInput
                placeholder="Enter your password"
                onchange={(value) => handleChangePassword(value)}
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

const mapStateToProps = state => ({
  isAuthenticated: state.user.isAuthenticated,
  error: state.error,
});

export default connect(mapStateToProps, {login, clearErrors})(AuthView);
