import $axios from "./index";

// 實時看板
export function realtimeDataBoard(params) {
  const url = "/bid-statistics/realtimeDataBoard";
  // const params = { factory: "深圳" };
  return $axios.get(url, params);
}

// 發佈類型
export function publishType() {
  const url = "/bid-statistics/publishType";
  return $axios.get(url);
}

// 客戶營銷
export function customerMarketing() {
  const url = "/bid-statistics/customerMarketing";
  return $axios.get(url);
}

// 訂單分析
export function billAnalysis() {
  const url = "/bid-statistics/billAnalysis";
  return $axios.get(url);
}

// 廠部分析
export function factoryAnalysis() {
  const url = "/bid-statistics/factoryAnalysis";
  return $axios.get(url);
}

// 發單用戶排行
export function sendUserRank(params) {
  const url = "/bid-statistics/sendUserRank";
  // const params = { timeLimit: "thisYear" };
  return $axios.get(url, params);
}

// 接單用戶排行
export function receiveUserRank(params) {
  const url = "/bid-statistics/receiveUserRank";
  // const params = { timeLimit: "thisYear" };
  return $axios.get(url, params);
}
