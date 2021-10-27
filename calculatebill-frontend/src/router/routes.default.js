import { lazy } from "react";

const routes = [
    {
        path: 'home',
        component: lazy(() => import('../views/HomeView')),
        exact: true,
    },
    {
        path: 'party',
        component: lazy(() => import('../views/PartyView')),
        exact: true,
    }
];

export default routes;