<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "app",
  components: {},
  created() {
    this.loginValidate();
  },
  methods: {
    loginValidate() {
      // const url = "http://10.244.231.103:14000/bidsso/";
      const url = "/bid/bidsso/";

      // const url = "http://10.244.186.86:10000/";
      this.$jsonp(url + "loginValidate", {
        callbackName: "jsonpFunc"
      })
        .then(res => {
          if (res.code === "1") {
            let t = res.t;
            sessionStorage.setItem("userId", t.userId);
            sessionStorage.setItem("token", t.token);
          } else if (res.code === "0") {
            this.$router.push("/login");
          } else if (res.code === "-1") {
            this.$message.error(res.msg);
          }
        })
        .catch(err => {
          this.$message.error(err);
        });
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
