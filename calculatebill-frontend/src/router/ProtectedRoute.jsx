import {Component, Suspense} from "react";
import { Route, Switch } from "react-router-dom";
import routes from './routes.default';
import Loader from "./Loader";

function ProtectedRoute() {
    return (
        <Switch>
            <Suspense fallback={<Loader />}>
                {routes.map(({ component: Component, path, exact }) => (
                    <Route
                        path={`/${path}`}
                        key={path}
                        exact={exact}
                    >
                        <Component />
                    </Route>
                ))}
            </Suspense>
        </Switch>
    );
}

export default ProtectedRoute;