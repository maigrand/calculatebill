import { lazy, Suspense, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
    BrowserRouter as Router,
    Switch,
} from "react-router-dom";

import AuthService from "../services/AuthService";

import PrivateRoute from "./PrivateRoute";
import PublicRoute from "./PublicRoute";
import ProtectedRoute from "./ProtectedRoute";
import Loader from "./Loader";
import { login, setUser } from "../store/reducers/user";

const AuthView = lazy(() => import('../views/AuthView'));
const RegisterView = lazy(() => import('../views/RegisterView'));

const HomeView = lazy(() => import('../views/HomeView'));

const BillView = lazy(() => import('../views/BillViews/BillView'));
const MyBillsView = lazy(() => import('../views/BillViews/MyBillsView'));
const CreateBillView = lazy(() => import('../views/BillViews/CreateBillView'));

export default function Routes() {
    const dispatch = useDispatch();
    const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

    const setAuth = async (token) => {
        const user = await AuthService.getUser(token);
        dispatch(login());
        dispatch(setUser(user));
    };

    useEffect(() => {
        (localStorage.getItem('token') && !isAuthenticated) && setAuth(localStorage.getItem('token'));
    });

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
                    <PrivateRoute path='/bill' isAuthenticated={isAuthenticated}>
                        <BillView />
                    </PrivateRoute>
                    <PrivateRoute path='/create-bill' isAuthenticated={isAuthenticated}>
                        <CreateBillView />
                    </PrivateRoute>
                    <PrivateRoute path='/my-bills' isAuthenticated={isAuthenticated}>
                        <MyBillsView />
                    </PrivateRoute>
                    <PrivateRoute path='/' isAuthenticated={isAuthenticated}>
                        <ProtectedRoute />
                    </PrivateRoute>
                </Switch>
            </Suspense>
        </Router>
    )
}
