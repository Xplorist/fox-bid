module.exports = {
    // 部署应用包时的基本 URL
    publicPath: process.env.NODE_ENV === 'production'? '/send/': '/send',
    devServer: {
      proxy: {
            // '/api': {
            //     target: 'http://10.244.186.86:8080/api',
            //     changeOrigin: true,
            //     ws: true,
            //     pathRewrite: {
            //       '^/api': ''
            //     }
            // },
            '/bid/api': {
                target: 'http://10.244.231.103:14000',
                changeOrigin: true,
                    ws: true,
                pathRewrite: {
                 '^/bid/api': ''
                }
            }
        }
    }
};