import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import md5 from "js-md5";
import htmlToPdf from "./assets/js/htmlToPdf";
import vueJsonp from "vue-jsonp";
import echarts from "echarts";
import login from "./common/login";

Vue.config.productionTip = false;

Vue.prototype.$md5 = md5;
Vue.prototype.$echarts = echarts;

Vue.use(ElementUI);
Vue.use(htmlToPdf);
Vue.use(vueJsonp);
Vue.use(login);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
