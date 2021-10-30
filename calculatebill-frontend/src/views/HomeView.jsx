import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useHistory } from "react-router-dom";

import {logout} from "../store/reducers/user";

import '../styles/_userView.scss';

const HomeView = () => {
    const dispatch = useDispatch();
    const history = useHistory();
    const user = useSelector((state) => state.user.data);

    const handleLogout = () => {
        localStorage.removeItem('token');
        dispatch(logout());

        history.push('/login');
    }

    return (
        <div className="home-view__container">
            <div className="home-view__wrapper">
                <header>
                    <strong>
                        Calculate Bill
                    </strong>
                    {user && (<p>{ user.username }</p>)}
                </header>

                <main>
                    <Link to="/create-bill">Create bill</Link>
                    <Link to="/my-bills">My bills</Link>
                    <Link to="/settings">Settings</Link>
                </main>

                <footer>
                    <button onClick={handleLogout}>Log out</button>
                </footer>
            </div>
        </div>
    )
}

export default HomeView;
