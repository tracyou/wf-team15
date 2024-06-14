import {createRouter, createWebHashHistory} from 'vue-router'
import Welcome from "@/components/Welcome";
import Overview32 from "@/cabins/Overview32";
import Overview31 from "@/cabins/Overview31";
import Detail32 from "@/cabins/Detail32";
import Overview33 from "@/cabins/Overview33";
import Detail34 from "@/cabins/Detail34";
import Overview34 from "@/cabins/Overview34";
import UnknownRoute from "@/components/UnknownRoute";
import Overview37 from "@/cabins/Overview37";
import Detail37 from "@/cabins/Detail37";
import signIn from "@/components/SignIn.vue";
import RequestError from "@/components/RequestError";

const routes = [
    {path: '/:pathMatch', component: UnknownRoute},
    { path: '/home', name: 'Home', component: Welcome},
    { path: '/', redirect: '/home'},
    { path: '/cabins/overview31', name: 'Overview31', component: Overview31},
    { path: '/sign-in', name: 'sign-in', query: "signOff", component: signIn},
    { path: '/sign-out', name: 'sign-out', query: "signOff",
        redirect: to => {
            return {path: "/home", query: {signOff: to.query.signOff}}
        },
        component: signIn
    },
    {
        path: '/cabins/overview32', name: 'Overview32', component: Overview32,
        children: [{path: ':id', component: Detail32}]
    },
    {
        path: '/cabins/overview33', name: 'Overview33', component: Overview33,
        children: [{path: ':id', component: Detail32}]
    },
    {
        path: '/cabins/overview34', name: 'Overview34', component: Overview34,
        children: [{path: ':id', component: Detail34}]
    },
    {
        path: '/cabins/overview37', name: 'Overview37', component: Overview37,
        children: [{path: ':id', component: Detail37}]
    },
    {
        path: '/error', name: 'ERROR', component: RequestError, props: true
    }

]
const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
