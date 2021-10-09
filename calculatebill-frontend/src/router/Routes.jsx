import React from 'react'
import {useSelector} from "react-redux";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Redirect,
} from "react-router-dom"

import AuthView from "../views/AuthView"
import HomeView from "../views/HomeView"

function AuthRoutes() {
    return (
        <Route exact path='/auth'>
            <AuthView />
        </Route>
    )
}

function UserRoutes() {
    return (
        <Route exact path='/home'>
            <HomeView />
        </Route>
    )
}

export default function Routes() {
    const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

    console.log(isAuthenticated)

    return (
        <Router>
            <Switch>
                <Route path='/'>
                    { !isAuthenticated ? <Redirect to='/auth' /> : <Redirect to='/home' /> }
                </Route>
                { !isAuthenticated ? <AuthRoutes /> : <UserRoutes /> }
            </Switch>
        </Router>
    )
}
