import $axios from "./index";

const baseURI = "/bid/api/bid-forum/";

/**
 * func: 查詢全部帖子
 * params: pageIndex, pageSize
 *
 * */
export function listPostsByPage(params) {
  const url = baseURI + "listPostsByPage";
  return $axios.getWithoutToken(url, params);
}

/**
 * func: 查詢對應id帖子
 * params: postId
 *
 * */
export function getPostById(params) {
  const url = baseURI + "getPostById";
  return $axios.getWithoutToken(url, params);
}

/**
 * func: 發佈帖子
 * params: title, content
 *
 * */
export function sendPost(data) {
  const url = baseURI + "sendPost";
  return $axios.post(url, data);
}

/**
 * func: 回復帖子
 * params: replyType, bbsPostId, parentId, content
 *
 * */
export function sendReply(data) {
  const url = baseURI + "sendReply";
  return $axios.post(url, data);
}
