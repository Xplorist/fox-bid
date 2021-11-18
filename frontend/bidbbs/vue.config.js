const targetUrl = "http://10.244.231.103:14000";
// const targetUrl = "http://10.244.186.86:8091";

module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/bidbbs/" : "/",

  devServer: {
    proxy: {
      "/bid/api": {
        target: targetUrl,
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/bid/api": ""
        }
      },
      "/bid/bidsso/": {
        target: targetUrl + "/bidsso",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/bid/bidsso/": ""
        }
      }
    }
  }
};
