import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue'),
    },
    {
      path: '/forum',
      name: 'forum',
      component: () => import('./views/Forum.vue'),
    },
    {
      path: '*',
      redirect: '/',// 重定向规则放在路由表的最底部，当前面所有的路由都没有匹配到时，会进入此重定向规则，重定向到首页
    },
  ]
})

router.beforeEach((to, from, next) => {
  let userId = sessionStorage.getItem('userId');
  let token = sessionStorage.getItem('token');
  if (to.path === '/' || to.name === 'login') {
    next();
  } else {
    if (userId === null || userId === '' || token === null || token === '') {
      next('/');
    } else {
      next();
    }
  }
});

export default router