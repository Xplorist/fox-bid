<template>
  <div class="login-box">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix card-title-text">
        <span>WELCOME</span>
      </div>
      <el-form
        class="form"
        label-position="top"
        label-width="80px"
        :model="form"
        @keydown.enter.native="handleLoginBtnClick()"
      >
        <el-form-item label="賬號">
          <el-input v-model.trim="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密碼">
          <el-input v-model.trim="form.password"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="handleLoginBtnClick()">登錄</el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    handleLoginBtnClick() {
      const flag = this.validateInfo();
      if (!flag) {
        return this.$message.warning("請檢查用戶名及密碼是否填寫完整");
      }
      // const url = "http://10.244.231.103:14000/bidsso/";
      const url = "/bid/bidsso/";

      this.$jsonp(url + "login", {
        username: this.form.username,
        password: this.$md5(this.form.password),
        callbackName: "jsonpFunc"
      }).then(res => {
        if (res.code === "1") {
          let t = res.t;

          sessionStorage.setItem("userId", t.userId);
          sessionStorage.setItem("token", t.token);
          this.$router.push("/");
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 檢查提交信息
    validateInfo() {
      for (let i in this.form) {
        if (!this.form[i]) {
          return false;
        }
      }
      return true;
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../style/variable";

.login-box {
  min-width: 400px;
  width: 50%;
  max-width: 600px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin: 5% 0 10%;
  .box-card {
    padding: 0 20px;
    min-width: 300px;
    .form {
      text-align: left;
    }
    .card-title-text {
      text-align: center;
    }
  }
  .box-card .card-title-text > span {
    font-size: 28px;
  }
}
</style>
