import { lazy } from "react";

const routes = [
    {
        path: 'home',
        component: lazy(() => import('../views/HomeView')),
        exact: true,
    },
    {
        path: 'bill',
        component: lazy(() => import('../views/BillViews/BillView')),
        exact: true,
    }
];

export default routes;