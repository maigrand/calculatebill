import { lazy } from "react";

const routes = [
    {
        path: 'home',
        component: lazy(() => import('../views/HomeView.jsx')),
        exact: true,
    },
    {
        path: 'bill',
        component: lazy(() => import('../views/BillViews/BillView.jsx')),
        exact: true,
    }
];

export default routes;