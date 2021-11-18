import Vue from 'vue'
import Router from 'vue-router'
import selection from './pages/selection.vue'
import task from './pages/task.vue'
import requirement from './pages/requirement.vue'
import profile from './pages/profile.vue'
import reviewManagement from './pages/reviewManagement.vue'
import evaluate from './pages/evaluate.vue'



Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'task',
      component: task
    },
    {
      path: '/selection',
      name: 'selection',
      component: selection
    },
    {
      path: '/requirement',
      name: 'requirement',
      component: requirement
    },
    {
      path: '/profile',
      name: 'profile',
      component: profile
    },
    {
      path: '/reviewManagement',
      name: 'reviewManagement',
      component: reviewManagement
    },
    {
      path: '/evaluate',
      name: 'evaluate',
      component: evaluate
    },
    
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    // }
  ]
})
