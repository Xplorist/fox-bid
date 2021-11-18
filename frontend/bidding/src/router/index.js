import Vue from "vue";
import Router from "vue-router";
// import Home from "@/views/Home.vue";
import Home from "@/views/home/Main.vue";
import store from "@/store";

Vue.use(Router);

const creatRouter = () => {
  return new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
      {
        path: "/",
        name: "home",
        component: Home
      },
      {
        path: "/login",
        name: "login",
        component: () => import("@/views/login/Main")
      },
      {
        path: "/personal",
        component: () =>
          import(/* webpackChunkName: "about" */ "../views/personal/Container"),
        meta: {
          title: "permission",
          roles: ["recv"]
        },
        children: [
          {
            path: "",
            name: "per-main",
            component: () => import("@/views/personal/Main")
          },
          {
            path: "delivery",
            name: "per-delivery",
            component: () => import("@/views/personal/Delivery")
          },
          {
            path: "evaluation",
            name: "per-evaluation",
            component: () => import("@/views/personal/Evaluation")
          },
          {
            path: "information",
            name: "per-information",
            component: () => import("@/views/personal/Information")
          },
          {
            path: "postEvaluation",
            name: "per-postEvaluation",
            component: () => import("@/views/personal/PostEva")
          },
          {
            path: "gather",
            name: "per-gather",
            component: () => import("@/views/personal/Gather")
          },
          {
            path: "setting",
            name: "per-setting",
            component: () => import("@/views/personal/Setting")
          }
        ]
      },
      // 詳情頁
      {
        path: "/particulars",
        name: "particulars",
        component: () => import("@/views/particulars/Main")
      },
      // 標書
      {
        path: "/biddingDoc",
        name: "biddingDoc",
        component: () => import("@/views/particulars/BiddingDoc")
      },
      {
        path: "/merchant",
        // name: 'merchant',
        component: () => import("@/views/merchant/Container"),
        children: [
          {
            path: "",
            name: "entryFir",
            component: () => import("@/views/merchant/EntryFir")
          },
          {
            path: "entrySec",
            name: "entrySec",
            component: () => import("@/views/merchant/EntrySec")
          },
          {
            path: "information",
            name: "information",
            component: () => import("@/views/merchant/Information")
          }
        ]
      },
      // 合同
      {
        path: "/contract",
        name: "contract",
        component: () => import("@/views/contract/Main")
      },
      // 404
      {
        path: "/404",
        name: "404",
        component: () => import("@/views/404/404")
      },
      {
        path: "/demand",
        redirect: "/demand/order",
        name: "demand",
        meta: {
          title: "permission",
          roles: ["send"]
        },
        component: () => import("@/views/demand/Container"),
        children: [
          {
            path: "order",
            name: "de-order",
            component: () => import("@/views/demand/Main")
          },
          {
            path: "information",
            name: "de-information",
            component: () => import("@/views/demand/Information")
          },
          {
            path: "evaluation",
            name: "de-evaluation",
            component: () => import("@/views/demand/Evaluation")
          },
          {
            path: "postEva",
            name: "de-postEva",
            component: () => import("@/views/demand/PostEva")
          },
          {
            path: "publish",
            name: "de-publish",
            component: () => import("@/views/demand/Publish")
          },
          {
            path: "chooseDetails",
            name: "de-chooseDetails",
            component: () => import("@/views/demand/ChooseDetails")
          },
          {
            path: "setting",
            name: "de-setting",
            component: () => import("@/views/demand/Setting")
          }
        ]
      },
      // 管理員
      {
        path: "/admin",
        name: "admin",
        redirect: "/admin/audit",
        meta: {
          title: "permission",
          roles: ["system"]
        },
        component: () => import("@/views/admin/Container"),
        children: [
          {
            path: "audit",
            name: "sys-audit",
            component: () => import("@/views/admin/Main")
          },
          {
            path: "manage",
            name: "sys-manage",
            component: () => import("@/views/admin/manage")
          },
          {
            path: "postMessage",
            name: "sys-postMessage",
            component: () => import("@/views/admin/postMessage")
          },
          {
            path: "orderSummary",
            name: "sys-orderSummary",
            component: () => import("@/views/admin/orderSummary")
          },
          {
            path: "administration",
            name: "sys-administration",
            component: () => import("@/views/admin/administration")
          },
          {
            path: "administration/rankingList",
            name: "sys-administration-rankingList",
            component: () => import("@/views/admin/administration/rankingList/RankingList")
          }
        ]
      },
      // 关于我们
      {
        path: "/aboutUs",
        name: "aboutUs",
        component: () => import("@/views/aboutUs")
      }
    ]
  });
};

const router = creatRouter();

// 導航守衛
router.beforeEach((to, from, next) => {
  let token = sessionStorage.getItem("token");
  const nextRoute = ["/personal", "/demand", "/admin"];
  var path = "/" + to.path.split("/")[1];
  const type = store.state.userInfo ? store.state.userInfo.send_recv_type : ""; // recv send system

  // 404 頁面
  if (to.name === null) {
    console.log("這是錯誤的頁面");
    next({
      path: "/404"
    });
  } else if (nextRoute.indexOf(path) >= 0) {
    // 未登錄不能進入
    if (!token || token == null) {
      next({
        path: "/login"
      });
    } else {
      // 權限
      const flag = to.matched[0].meta.roles.includes(type);
      if (flag) {
        next();
      } else {
        console.log("這是不該來的頁面");
        next({
          path: "/404"
        });
      }
    }
  } else {
    next();
  }
});

// 跳转后回到顶部
router.afterEach(() => {
  // to,from,next
  // window.scrollTo(0, 110)
});

export default router;
