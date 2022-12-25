const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/users", {
      target: "http://54.180.117.22:8080",
      changeOrigin: true,
    })
  );
};
