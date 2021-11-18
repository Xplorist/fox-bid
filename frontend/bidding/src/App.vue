<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  created() {
    this.loginValidate();
  },
  methods: {
    ...mapActions({
      get_token: "get_token"
    }),
    loginValidate() {
      const data = {
        callbackName: "jsonpFunc"
      };
      this.jsp("loginValidate", data)
        .then(res => {
          if (res.code === "1") {
            this.get_token(res.t.token);
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
@import "./assets/css/common/normalize.css";
@import "./assets/css/common/common.css";

/* 修改首要字體 */
@font-face {
  font-family: "myFont";
  src: url("./assets/font/STZHONGS.TTF");
}

#app {
  font-family: "myFont", "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100%;
}
</style>
