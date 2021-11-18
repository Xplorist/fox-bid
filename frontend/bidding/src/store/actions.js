const actions = {
  // 獲取 token
  get_token({ commit }, data) {
    commit("SET_TOKEN", data);
  },
  // 獲取用戶名
  get_username({ commit }, data) {
    commit("SET_USERNAME", data);
  },
  // 獲取用戶信息
  get_userInfo({ commit }, data) {
    commit("SET_USERINFO", data);
  },
  // 獲取頭像地址
  get_porImgUrl({ commit }, data) {
    commit("SET_PORIMGURL", data);
  },
  // 獲取下拉框列表數據
  get_mSelectList({ commit }, data) {
    commit("SET_MSELECTLIST", data);
  },
  // 獲取管理員經營管理頁用戶數據
  get_administrationSendData({ commit }, data) {
    commit("SET_ADMINISTRATION_SENDDATA", data);
  },
  get_administrationRecvData({ commit }, data) {
    commit("SET_ADMINISTRATION_RECVDATA", data);
  }
};
export default actions;
