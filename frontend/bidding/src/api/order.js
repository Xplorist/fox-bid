import $axios from "./index";

// 【發單方】
// 保存訂單
export function saveBill(data) {
  const url = "/bidding/bill/saveBill";
  return $axios.postWithToken(url, data);
}

// 發佈訂單
export function submitBill(data) {
  const url = "/bidding/bill/submitBill";
  return $axios.postWithToken(url, data);
}

// 查詢訂單
export function query_bill_list_send_user(bill_status, pageIndex, pageSize) {
  const data = {
    bill_status: bill_status,
    pageIndex: pageIndex,
    pageSize: pageSize
  };
  const url = "/bidding/bill/query_bill_list_send_user";
  return $axios.postWithToken(url, data);
}

// 查詢訂單狀態對應數量
export function query_status_bill_num_send_user() {
  const data = {};
  const url = "/bidding/bill/query_status_bill_num_send_user";
  return $axios.postWithToken(url, data);
}

// 查詢保存的訂單信息
export function query_bill_by_pkid(pkid) {
  const data = {
    pkid: pkid
  };
  const url = "/bidding/bill/query_bill_by_pkid";
  return $axios.post(url, data);
}

// 【接單方】
// 查詢訂單信息
export function query_bill_list_recv_user(bill_status, pageIndex, pageSize) {
  const data = {
    bill_status: bill_status,
    pageIndex: pageIndex,
    pageSize: pageSize
  };
  const url = "/bidding/bill/query_bill_list_recv_user";
  return $axios.postWithToken(url, data);
}

// 查詢訂單狀態對應數量
export function query_status_num_recv_user() {
  const data = {};
  const url = "/bidding/bill/query_status_num_recv_user";
  return $axios.postWithToken(url, data);
}

// 開始競價
export function save_give_price(data) {
  const url = "/bidding/bill/save_give_price";
  return $axios.postWithToken(url, data);
}

// 【主頁】
// 查詢訂單信息（未登錄狀態）
export function query_bill_list_not_login(data) {
  const url = "/bidding/bill/query_bill_list_not_login";
  return $axios.post(url, data);
}

// 查詢訂單信息（接單方）
export function query_bill_list_recv_main(data) {
  const url = "/bidding/bill/query_bill_list_recv_main";
  return $axios.postWithToken(url, data);
}

// 【公共】
// 更改訂單狀態
export function update_bill_status(data) {
  const url = "/bidding/bill/update_bill_status";
  return $axios.postWithToken(url, data);
}

// 發單方做出評價
export function save_send_eval(data) {
  // data = {
  //   "bill_pkid": "",
  //   "summary_score": "",
  //   "out_rate_score": "",
  //   "out_qual_score": "",
  //   "svc_atitu_score": "",
  //   "summary_text": "",
  //   "f_anomus": ""
  // }
  const url = "/bidding/bill/save_send_eval";
  return $axios.postWithToken(url, data);
}

// 接單方做出評價
export function save_recv_eval(data) {
  // data = {
  //   "bill_pkid": "",
  //   "summary_score": "",
  //   "pay_rate_score": "",
  //   "svc_atitu_score": "",
  //   "summary_text": "",
  //   "f_anomus": ""
  // }
  const url = "/bidding/bill/save_recv_eval";
  return $axios.postWithToken(url, data);
}

// 根據訂單id查詢報價信息
export function query_give_price_by_bill_pkid(pkid) {
  const data = {
    bill_pkid: pkid
  };
  const url = "/bidding/bill/query_give_price_by_bill_pkid";
  return $axios.postWithToken(url, data);
}

// -------------------------------------------------------------

// 查詢[發單方]收到的評價
export function query_send_get_eval_list(pageIndex, pageSize) {
  const data = {
    pageIndex,
    pageSize
  };
  const url = "/bidding/bill/query_send_get_eval_list";
  return $axios.postWithToken(url, data);
}

// 查詢[發單方]做出的評價
export function query_send_make_eval_list(pageIndex, pageSize) {
  const data = {
    pageIndex,
    pageSize
  };
  const url = "/bidding/bill/query_send_make_eval_list";
  return $axios.postWithToken(url, data);
}

// 查詢[發單方]評價分數
export function query_send_get_eval_avg() {
  const data = {};
  const url = "/bidding/bill/query_send_get_eval_avg";
  return $axios.postWithToken(url, data);
}

// -------------------------------------------------------------

// 查詢[接單方]收到的評價
export function query_recv_get_eval_list(pageIndex, pageSize) {
  const data = {
    pageIndex,
    pageSize
  };
  const url = "/bidding/bill/query_recv_get_eval_list";
  return $axios.postWithToken(url, data);
}

// 查詢[接單方]做出的評價
export function query_recv_make_eval_list(pageIndex, pageSize) {
  const data = {
    pageIndex,
    pageSize
  };
  const url = "/bidding/bill/query_recv_make_eval_list";
  return $axios.postWithToken(url, data);
}

// 查詢[接單方]評價分數
export function query_recv_get_eval_avg() {
  const data = {};
  const url = "/bidding/bill/query_recv_get_eval_avg";
  return $axios.postWithToken(url, data);
}

// 根據訂單ID查詢排序後報價LIST
export function query_give_price_list(data) {
  const url = "/bidding/bill/query_give_price_list";
  return $axios.postWithToken(url, data);
}

// 選定中標
export function select_win_bid(pkid) {
  const data = {
    pkid
  };
  const url = "/bidding/bill/select_win_bid";
  return $axios.postWithToken(url, data);
}

// 棄標
export function abandon_bid(pkid) {
  const data = {
    bill_pkid: pkid
  };
  const url = "/bidding/bill/abandon_bid";
  return $axios.postWithToken(url, data);
}

// 統計訂單信息
export function queryBillStatistics(data) {
  const url = "/bidding/bill/queryBillStatistics";
  return $axios.postWithToken(url, data);
}
