import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

import Layout from "@/layout";

export const constantRoutes = [
  {
    path: "/",
    component: Layout,
    children: [
      {
        path: "",
        name: "home",
        component: () => import("@/pages/home"),
        redirect: "",
        children: []
      },
      {
        path: "/p/:articleID",
        name: "details",
        component: () => import("@/pages/details"),
        redirect: "",
        children: []
      },
      {
        path: "/edit",
        name: "edit",
        component: () => import("@/pages/edit")
      }
    ]
  },
  {
    path: "/login",
    component: () => import("@/pages/login")
  },
  {
    path: "*",
    redirect: "/"
  }
];

const createRouter = () => {
  return new Router({
    mode: "history",
    routes: constantRoutes,
    base: process.env.BASE_URL,
    scrollBehavior() {
      return {
        x: 0,
        y: 0
      };
    }
  });
};

const router = createRouter();
export default router;
