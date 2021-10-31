import React from "react";
import {Link} from "react-router-dom";

import {logout} from "../store/actions/userActions";
import {connect} from "react-redux";

import '../styles/_userView.scss';

const HomeView = ({username, logout}) => {
  return (
      <div className="home-view__container">
        <div className="home-view__wrapper">
          <header>
            <strong>
              Calculate Bill
            </strong>
            <p>{username}</p>
          </header>

          <main>
            <Link to="/create-bill">Create bill</Link>
            <Link to="/my-bills">My bills</Link>
            <Link to="/settings">Settings</Link>
          </main>

          <footer>
            <button onClick={logout}>Log out</button>
          </footer>
        </div>
      </div>
  )
}

const mapStateToProps = state => ({
  username: state.user.username,
  error: state.error,
});

export default connect(mapStateToProps, {logout})(HomeView);
