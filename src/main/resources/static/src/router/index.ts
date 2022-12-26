/* James G Willmore - LJ Computing - (C) 2022 */
import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ContactsView from "../views/ContactsView.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/view/",
        name: "Home",
        component: HomeView,
    },
    {
        path: "/view/contacts",
        name: "Contacts",
        component: ContactsView,
    },
    {
        path: "/view/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL + "/#"),
    routes,
});

export default router;
