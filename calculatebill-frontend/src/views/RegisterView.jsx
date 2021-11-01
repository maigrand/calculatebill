import React, {useState} from "react";
import {Link} from "react-router-dom";

import EmailInput from "../components/ui/EmailInput.jsx";
import PasswordInput from "../components/ui/PasswordInput.jsx";

import {register} from "../store/actions/userActions";
import {clearErrors} from "../store/actions/errorActions";
import {connect} from "react-redux";

import "../styles/_authView.scss";

const RegisterView = ({register}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleChangeEmail = (value) => setEmail(value);
  const handleChangePassword = (value) => setPassword(value);
  const handleConfirmPassword = (value) => setConfirmPassword(value);

  const handleRegister = async (e) => {
    e.preventDefault();
    register({email, password});
  };

  return (
      <div className="flex__container">
        <form className="flex__wrapper column" onSubmit={handleRegister}>
          <header>
            <h2>
              Sign up to Calculate Bill.
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

const mapStateToProps = state => ({
  isAuthenticated: state.user.isAuthenticated,
  error: state.error,
});

export default connect(mapStateToProps, { register, clearErrors })(RegisterView);