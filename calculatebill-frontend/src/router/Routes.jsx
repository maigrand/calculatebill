import { lazy, Suspense } from 'react'
import {useSelector} from "react-redux";
import {
    BrowserRouter as Router,
    Switch,
} from "react-router-dom";

import PrivateRoute from "./PrivateRoute";
import PublicRoute from "./PublicRoute";
import ProtectedRoute from "./ProtectedRoute";
import Loader from "./Loader";

const AuthView = lazy(() => import('../views/AuthView'));
const RegisterView = lazy(() => import('../views/RegisterView'));

const HomeView = lazy(() => import('../views/HomeView'));
const PartyView = lazy(() => import('../views/PartyView'));

export default function Routes() {
    const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

    return (
        <Router>
            <Suspense fallback={<Loader />}>
                <Switch>
                    <PublicRoute path='/login' isAuthenticated={isAuthenticated}>
                        <AuthView />
                    </PublicRoute>
                    <PublicRoute path='/register' isAuthenticated={isAuthenticated}>
                        <RegisterView />
                    </PublicRoute>
                    <PrivateRoute path='/home' isAuthenticated={isAuthenticated}>
                        <HomeView />
                    </PrivateRoute>
                    <PrivateRoute path='/party'>
                        <PartyView />
                    </PrivateRoute>
                    <PrivateRoute path='/' isAuthenticated={isAuthenticated}>
                        <ProtectedRoute />
                    </PrivateRoute>
                </Switch>
            </Suspense>
        </Router>
    )
}
