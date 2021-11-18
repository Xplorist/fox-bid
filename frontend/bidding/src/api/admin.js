import $axios from './index'

// 根據訂單id查詢報價信息
export function query_user_au_list_pagi(data) {
  const url = '/bidding/admin/query_user_au_list_pagi'
  return $axios.postWithToken(url, data)
}

// 審核
export function user_au(data) {
  const url = '/bidding/admin/user_au'
  return $axios.postWithToken(url, data)
}

// 保存公告
export function saveBulletin(data) {
  const url = '/bidding/admin/saveBulletin'
  return $axios.postWithToken(url, data)
}

// 查詢公告
export function listBulletin(data) {
  const url = '/bidding/admin/listBulletin'
  return $axios.post(url, data)
}