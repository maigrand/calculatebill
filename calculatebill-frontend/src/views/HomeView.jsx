import React from "react";
import { useSelector } from "react-redux";

import '../styles/_userView.scss';

const HomeView = () => {
    const user = useSelector((state) => state.user.data);

    return (
        <div className="home-view__container">
            <div className="home-view__wrapper">
                { user.username ? <h1>{ user.username }</h1> : '' }
            </div>
        </div>
    )
}

export default HomeView;
