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
      <div className='flex__container'>
        <form className="flex__wrapper column" onSubmit={handleLogin}>
          <header>
            <h2>
              Sign in to Calculate Bill.
            </h2>
          </header>

          <main>
            <EmailInput
                placeholder="Enter your email"
                onchange={(value) => handleChangeEmail(value)}
                label="Email address"
            />
            <PasswordInput
                placeholder="Enter your password"
                onchange={(value) => handleChangePassword(value)}
                label="Password"
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
