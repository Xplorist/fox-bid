module.exports = {
  // 部署应用包时的基本 URL
  publicPath: process.env.NODE_ENV === "production" ? "/bid" : "/",

  devServer: {
    proxy: {
      "/bid/api": {
        // target: 'http://10.244.186.201:14000',
        target: "http://10.244.231.103:14000",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/bid/api": ""
        }
      },
      "/bid/bidsso": {
        target: "http://10.244.231.103:14000/bidsso",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/bid/bidsso": ""
        }
      }
    }
  }
};
