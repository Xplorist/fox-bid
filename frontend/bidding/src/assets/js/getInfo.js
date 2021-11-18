  import state from '@/store/state'

  // 獲取頭像地址 通過url直接拿到頭像
  export function getPorImg(path, name, originName) {
    const param1 = encodeURIComponent(path || state.userInfo.user_pic_file.file_save_path)
    const param2 = encodeURIComponent(name || state.userInfo.user_pic_file.file_save_name)
    const param3 = encodeURIComponent(originName || state.userInfo.user_pic_file.file_origin_name)
    const baseUrl = '/bid/api/bidding/pic_show/load'
    const url = baseUrl + '?file_save_path=' + param1 + '&file_save_name=' + param2 + '&file_origin_name=' + param3
    return url
  }

  // 獲取圖檔附件 通過對此url發送請求獲取資源
  export function getAccess(path, name, originName) {
    const param1 = encodeURIComponent(path)
    const param2 = encodeURIComponent(name)
    const param3 = encodeURIComponent(originName)
    const baseUrl = '/bidding/pic_show/download'
    const url = baseUrl + '?file_save_path=' + param1 + '&file_save_name=' + param2 + '&file_origin_name=' + param3
    return url
  }