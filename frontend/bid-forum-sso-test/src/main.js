import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import md5 from 'md5'

Vue.config.productionTip = false
Vue.prototype.axios = axios
Vue.prototype.md5 = md5

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
