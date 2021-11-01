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
    },
    {
        path: 'create-bill',
        component: lazy(() => import('../views/BillViews/CreateBillView.jsx')),
        exact: true,
    },
    {
        path: '/my-bills',
        component: lazy(() => import('../views/BillViews/MyBillsView.jsx')),
        exact: true,
    }
];

export default routes;