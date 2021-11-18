import $axios from './index'

// 上傳文檔
export function file_upload(data){
  const url = '/bidding/ftp_file/upload'
  const headers = 'multipart/form-data'
  return $axios.post(url, data, headers)
}

// 刪除FTP文檔
export function file_delete(data){
  const url = '/bidding/ftp_file/delete'
  return $axios.post(url, data)
}

// 刪除數據庫文檔
export function file_delete_Bypkid(data){
  const url = '/bidding/bill/delete_part_doc_file_by_pkid'
  return $axios.post(url, data)
}

// 下載 文件
export function downLoad(url){
  return $axios.getWithToken(url)
}