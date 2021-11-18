import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import element from "element-ui";
import vueJsonp from "vue-jsonp";
import md5 from "md5";
import "element-ui/lib/theme-chalk/index.css";
import "./style/index.less";

Vue.config.productionTip = false;
Vue.prototype.$md5 = md5;

Vue.use(element);
Vue.use(vueJsonp);

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
