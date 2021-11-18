exports.install = function(Vue, options) {
  Vue.prototype.jsp = function(api, data) {
    const baseUrl = "/bid/bidsso/";
    let url = `${baseUrl}${api}`;
    return this.$jsonp(url, data);
  };
};
