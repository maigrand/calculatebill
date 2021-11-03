import {lazy, Suspense, useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {
  BrowserRouter as Router,
  Switch,
} from "react-router-dom";

import PrivateRoute from "./PrivateRoute.jsx";
import PublicRoute from "./PublicRoute.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";
import Loader from "./Loader.jsx";
import {loadUser} from "../store/actions/userActions";

const AuthView = lazy(() => import('../views/AuthView.jsx'));
const RegisterView = lazy(() => import('../views/RegisterView.jsx'));

const HomeView = lazy(() => import('../views/HomeView.jsx'));

const BillView = lazy(() => import('../views/BillViews/BillView.jsx'));
const MyBillsView = lazy(() => import('../views/BillViews/MyBillsView.jsx'));
const CreateBillView = lazy(() => import('../views/BillViews/CreateBillView.jsx'));

export default function Routes() {
  const dispatch = useDispatch();
  const token = useSelector((state) => state.user.token);
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);

  const setAuth = async () => {
    dispatch(loadUser());
  };

  useEffect(() => {
    token && setAuth();
  }, [token]);

  return (
      <Router>
        <Suspense fallback={<Loader/>}>
          <Switch>
            <PublicRoute path='/login' isAuthenticated={isAuthenticated}>
              <AuthView/>
            </PublicRoute>
            <PublicRoute path='/register' isAuthenticated={isAuthenticated}>
              <RegisterView/>
            </PublicRoute>
            <PrivateRoute path='/home' isAuthenticated={isAuthenticated}>
              <HomeView/>
            </PrivateRoute>
            <PrivateRoute path='/bill' isAuthenticated={isAuthenticated}>
              <BillView/>
            </PrivateRoute>
            <PrivateRoute path='/create-bill' isAuthenticated={isAuthenticated}>
              <CreateBillView/>
            </PrivateRoute>
            <PrivateRoute path='/my-bills' isAuthenticated={isAuthenticated}>
              <MyBillsView/>
            </PrivateRoute>
            <PrivateRoute path='/' isAuthenticated={isAuthenticated}>
              <ProtectedRoute/>
            </PrivateRoute>
          </Switch>
        </Suspense>
      </Router>
  )
}
