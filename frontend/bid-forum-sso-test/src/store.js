import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: sessionStorage.getItem('userId'),
    token: sessionStorage.getItem('token'),
  },
  mutations: {
    // 更新登录信息
    updateLoginInfo (state, payload) {
      state.userId = payload.userId;
      state.token = payload.token;
    },
  },
  actions: {

  }
})
