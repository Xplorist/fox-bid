const mutations = {
  // 修改 token 值
  SET_TOKEN(state, token) {
    sessionStorage.setItem("token", JSON.stringify(token));
    state.token = token;
  },
  // 修改 username 值
  SET_USERNAME(state, username) {
    state.username = username;
  },
  // 修改 userInfo
  SET_USERINFO(state, userInfo) {
    sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
    state.userInfo = userInfo;
  },
  // 修改 porImgUrl
  SET_PORIMGURL(state, porImgUrl) {
    sessionStorage.setItem("porImgUrl", JSON.stringify(porImgUrl));
    state.porImgUrl = porImgUrl;
  },
  // 登出清空 state 相關數據
  LOG_OUT(state) {
    sessionStorage.clear();
    state.username = null;
    state.token = null;
    state.userInfo = null;
    state.porImgUrl = null;
    state.mSelectList = [];
  },
  // 修改下拉框列表值
  SET_MSELECTLIST(state, mSelectList) {
    state.mSelectList = mSelectList;
  },
  // 修改管理員經營管理頁用戶數據
  SET_ADMINISTRATION_SENDDATA(state, administrationSendData) {
    state.administrationSendData = administrationSendData;
  },
  SET_ADMINISTRATION_RECVDATA(state, administrationRecvData) {
    state.administrationRecvData = administrationRecvData;
  }
};
export default mutations;
