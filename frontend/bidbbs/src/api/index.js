import axios from "axios";
import { Loading, Message } from "element-ui";

const $axios = axios.create({
  timeout: 3000
});

let loading = null;

// 請求攔截器
$axios.interceptors.request.use(
  config => {
    loading = Loading.service({
      text: "資源拉取中..."
    });
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 響應攔截器
$axios.interceptors.response.use(
  response => {
    if (loading) {
      loading.close();
    }
    const code = response.status;
    if ((code >= 200 && code < 300) || code === 304) {
      return Promise.resolve(response.data);
    } else {
      return Promise.reject(response);
    }
  },
  error => {
    if (loading) {
      loading.close();
    }
    if (error.response) {
      switch (error.response.status) {
        case 404:
          Message.error("網絡請求不存在！");
          break;
        case 500:
          Message.error("服務器出錯啦，請稍後再試！");
          break;
        default:
          Message.error(error.response.data.message);
      }
    } else {
      if (error.message.includes("timeout")) {
        Message.error("請求超時！");
      } else {
        Message.error("請求失敗！");
      }
    }
  }
);

export default {
  get(url, params) {
    return $axios({
      method: "get",
      url,
      params,
      headers: {
        token: sessionStorage.getItem("token")
      }
    });
  },
  getWithoutToken(url, params) {
    return $axios({
      method: "get",
      url,
      params
    });
  },

  post(url, data) {
    return $axios({
      method: "post",
      url,
      data,
      headers: {
        token: sessionStorage.getItem("token")
      }
    });
  },
  postWithoutToken(url, data) {
    return $axios({
      method: "post",
      url,
      data
    });
  }
};
