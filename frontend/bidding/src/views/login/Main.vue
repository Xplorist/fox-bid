<template>
  <div class="main">
    <section class="main-cont">
      <div class="type-area">
        <!-- 頂部 -->
        <div class="header">
          <div class="hea_left">
            <div class="text1">
              <span>模治檢具</span>
              <span>招投標平台</span>
            </div>
            <div class="text2">通行證</div>
          </div>
          <div class="hea_right">
            <router-link to="/">返回首頁</router-link>
          </div>
        </div>
        <!-- 主要內容 -->
        <div class="prin">
          <!-- 背景框 -->
          <div class="pri-bgBox">
            <!-- 登錄框 -->
            <div class="pri-loginBox">
              <div class="login-logo"></div>
              <!-- 登錄 -->
              <div v-if="nowStatus == 'login'" class="login_content">
                <div class="title">登錄</div>
                <el-form
                  ref="formLogin"
                  status-icon
                  :model="formLogin"
                  :rules="rulesLogin"
                  label-width="50px"
                  label-position="left"
                  @keyup.enter.native="_login()"
                >
                  <el-form-item label="用戶" class="name" prop="name">
                    <el-input
                      v-model="formLogin.name"
                      placeholder="請輸入您的用戶名"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="密碼" class="psd" prop="psd">
                    <el-input
                      type="password"
                      v-model="formLogin.psd"
                      show-password
                      placeholder="請輸入您的密碼"
                    ></el-input>
                  </el-form-item>
                </el-form>
                <div class="opreation">
                  <div class="autoLogin">
                    <!-- <el-checkbox v-model="autoLogin">下次自動登錄</el-checkbox> -->
                  </div>
                  <div class="forget_register">
                    <router-link to @click.native="changeStatue('forgot')"
                      >忘記密碼</router-link
                    >
                    <router-link to="/merchant">立即註冊</router-link>
                  </div>
                </div>
                <div class="nextBtn" @click="_login()">立即登錄</div>
              </div>
              <!-- 找回密碼 -->
              <div v-else-if="nowStatus == 'forgot'" class="forgot_content">
                <div class="title">找回密碼</div>
                <el-form
                  ref="formForgot"
                  status-icon
                  :model="formForgot"
                  :rules="rulesForgot"
                  @keyup.enter.native="_confirm()"
                >
                  <el-form-item class="account" prop="account">
                    <el-input
                      v-model="formForgot.account"
                      placeholder="請輸入您的賬號"
                    ></el-input>
                    <el-button :disabled="getCodeFlag" @click="getCode()">{{
                      getCodeFlag ? "已發送(" + countdown + ")" : "發送驗證碼"
                    }}</el-button>
                    <span v-show="getCodeFlag" class="tip"
                      >已發往{{ formForgot.email }}</span
                    >
                  </el-form-item>
                  <el-form-item class="verifCode" prop="verifCode">
                    <el-input
                      v-model="formForgot.verifCode"
                      placeholder="請輸入驗證碼"
                    ></el-input>
                    <span v-show="verifCodeFlag" class="tip">驗證碼錯誤！</span>
                  </el-form-item>
                </el-form>
                <div class="opreation">
                  <div></div>
                  <div class="forget_register">
                    <router-link to @click.native="changeStatue('login')"
                      >返回</router-link
                    >
                  </div>
                </div>
                <div class="nextBtn" @click="_confirm()">確認</div>
              </div>
              <!-- 修改密碼 -->
              <div v-else class="alter_content">
                <div class="title">修改密碼</div>
                <el-form
                  ref="formAlter"
                  status-icon
                  :model="formAlter"
                  :rules="rulesAlter"
                  @keyup.enter.native="_submit()"
                >
                  <el-form-item class="psd" prop="psd">
                    <el-input
                      type="password"
                      show-password
                      v-model="formAlter.psd"
                      placeholder="請輸入新密碼"
                    ></el-input>
                  </el-form-item>
                  <el-form-item class="checkPsd" prop="checkPsd">
                    <el-input
                      type="password"
                      show-password
                      v-model="formAlter.checkPsd"
                      placeholder="請再次輸入密碼"
                    ></el-input>
                  </el-form-item>
                </el-form>
                <div class="opreation">
                  <div></div>
                  <div class="forget_register">
                    <router-link to @click.native="changeStatue('forgot')"
                      >返回</router-link
                    >
                  </div>
                </div>
                <div class="nextBtn" @click="_submit()">確認</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- 使用底部組件 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import { mapState, mapActions } from "vuex";
import { Message } from "element-ui";

// 引入 api
import {
  login_user_info,
  generate_code,
  check_code,
  update_password
} from "@/api/user";
import { getPorImg } from "@/assets/js/getInfo";

export default {
  data() {
    return {
      // 登錄表單
      formLogin: {
        name: "",
        psd: ""
      },
      rulesLogin: {
        name: [
          { required: true, message: "請輸入賬號名", trigger: "blur" },
          { max: 16, message: "長度不超過16個字符", trigger: "blur" }
        ],
        psd: [{ required: true, message: "請輸入密碼" }]
      },
      // 忘記密碼表單
      formForgot: {
        account: "",
        verifCode: ""
      },
      rulesForgot: {
        account: [{ required: true, message: "請先輸入賬號", trigger: "blur" }],
        email: "",
        verifCode: [
          { required: true, message: "還未輸入驗證碼", trigger: "blur" }
        ]
      },
      // 修改密碼表單
      formAlter: {
        psd: "",
        checkPsd: ""
      },
      rulesAlter: {
        psd: [
          { required: true, message: "不能為空" },
          { min: 4, max: 16, message: "密碼長度為4-16" }
        ],
        checkPsd: [{ required: true, message: "不能為空" }]
      },
      // 自動登錄
      autoLogin: false,
      // 當前狀態
      nowStatus: "login",
      // 是否獲取到驗證碼
      getCodeFlag: false,
      // 倒計時
      countdown: 60,
      // 驗證碼消息
      verifCodeFlag: false,
      // 接收了驗證碼的郵箱
      account: "",
      // 接收到的 驗證code
      verifCode: ""
    };
  },
  methods: {
    ...mapActions({
      get_username: "get_username",
      get_token: "get_token",
      get_userInfo: "get_userInfo",
      get_porImgUrl: "get_porImgUrl"
    }),

    // 確認修改密碼
    _submit() {
      this.$refs["formAlter"].validate(valid => {
        if (valid) {
          if (this.formAlter.psd !== this.formAlter.checkPsd) {
            return this.$message.error("密碼不一致，請檢查！");
          }
          const data = {
            username: this.account,
            password: this.$md5(this.formAlter.psd),
            code: this.verifCode
          };
          update_password(data).then(res => {
            if (res.code === "1") {
              this.$message.success("修改成功，請登錄！");
              this.changeStatue("login");
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          return;
        }
      });
    },

    // 發送驗證碼
    getCode() {
      // 驗證是否輸入賬號
      this.$refs["formForgot"].validateField("account", error => {
        if (!error) {
          const data = { username: this.formForgot.account };
          generate_code(data).then(res => {
            if (res.code === "1") {
              // 避免修改賬號
              this.account = this.formForgot.account;
              // 獲取郵箱號
              this.formForgot.email = res.t;
              // 修改值 設置定時器
              this.getCodeFlag = true;
              var _this = this;
              let timer = setInterval(function() {
                if (_this.countdown <= 0) {
                  _this.getCodeFlag = false;
                  _this.countdown = 60;
                  clearInterval(timer);
                } else {
                  _this.countdown--;
                }
              }, 1000);
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          return false;
        }
      });
    },

    // 修改狀態框
    changeStatue(val) {
      if (this.$refs["formLogin"]) {
        this.$refs["formLogin"].clearValidate();
        if (this.formLogin.name) {
          this.formForgot.account = this.formLogin.name;
        }
      } else if (this.$refs["formForgot"]) {
        this.$refs["formForgot"].resetFields();
      } else {
        this.$refs["formAlter"].resetFields();
      }
      this.nowStatus = val;
    },

    // 確認驗證碼
    _confirm() {
      if (!this.getCodeFlag) return this.$message.warning("請先獲取驗證碼！");
      this.$refs["formForgot"].validateField("verifCode", error => {
        if (!error) {
          const data = {
            username: this.account,
            code: this.formForgot.verifCode
          };
          check_code(data).then(res => {
            if (res.code === "1") {
              this.verifCode = data.code;
              this.$message.info("驗證成功，請設置您的新密碼！");
              this.changeStatue("alter");
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          return;
        }
      });
    },

    _login() {
      this.$refs["formLogin"].validate(valid => {
        if (valid) {
          const data = {
            username: this.formLogin.name,
            password: this.$md5(this.formLogin.psd),
            callbackName: "jsonpFunc"
          };

          this.jsp("login", data).then(res => {
            if (res.code === "1") {
              this.get_username(this.formLogin.name);
              this.get_token(res.t.token);
              this.getInfo();
              Message.success(res.msg);
            } else {
              Message.error(res.msg);
            }
          });
        } else {
          return false;
        }
      });
    },

    // 獲取信息並寫入vuex
    getInfo() {
      var username = this.username;
      login_user_info(username)
        .then(res => {
          if (res.code === "1") {
            // 如果返回成功，講數據通過 actions 傳給 state
            this.get_userInfo(res.t);
            // 查詢頭像信息
            if (res.t.send_recv_type === "send") {
              this.$router.push("/demand");
            } else if (res.t.send_recv_type === "system") {
              this.$router.push("/admin");
            } else {
              this.$router.push("/");
            }
            this.get_porImgUrl(getPorImg());
          } else {
            Message.error(res.msg);
          }
        })
        .catch(err => {
          this.$message.error(err);
        });
    }
  },
  computed: {
    ...mapState({
      username: state => state.username,
      userInfo: state => state.userInfo
    })
  },
  components: {
    Footer
  }
};
</script>

<style lang="less" scoped>
.main {
  min-height: 100vh;
  background-color: #12222e;
  position: relative;
}
.main-cont {
  // 多張背景圖
  background: url("../../assets/imgs/login/bg-land.png") no-repeat center bottom,
    url("../../assets/imgs/login/bg-light.png") no-repeat center top;
  background-color: #12222e;
  padding-bottom: 157px;
}
// d頂部樣式
.header {
  color: #c1d0db;
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.hea_left {
  display: flex;
  align-items: center;
  .text1 {
    display: flex;
    flex-direction: column;
    align-items: center;
    > span {
      font-size: 14px;
    }
    > span:nth-child(2) {
      font-size: 12px;
    }
  }
  .text2 {
    border-left: 2px solid #fff;
    margin-left: 10px;
    padding-left: 10px;
    font-size: 26px;
  }
}
.hea_right {
  font-size: 14px;
  a {
    color: #c1d0db;
  }
}
.pri-bgBox {
  margin: 0 auto;
  width: 1028px;
  height: 630px;
  background: url("../../assets/imgs/login/bg-mould.png") no-repeat center;
  display: flex;
  justify-content: center;
  align-items: center;
}
// 登錄框
.pri-loginBox {
  margin-top: 40px;
  background-color: #233848;
  width: 346px;
  height: 438px;
  position: relative;
  // 加border並且漸變
  border: 1px solid;
  border-image: linear-gradient(
      0deg,
      rgba(52, 95, 127, 1),
      rgba(25, 44, 59, 1),
      rgba(83, 179, 227, 1)
    )
    10 10;
  transition-delay: 0.3s;
  transition-duration: 0.3s;
  transition-timing-function: ease-in;
  &:hover {
    width: 450px;
    height: 490px;
  }
  .login-logo {
    width: 292px;
    height: 65px;
    position: absolute;
    top: -32px;
    left: 50%;
    transform: translateX(-50%);
    background-image: url(../../assets/imgs/login/logo.png);
    background-repeat: no-repeat;
    background-position: center;
  }
}
// 登錄框內容
.login_content,
.forgot_content,
.alter_content {
  display: flex;
  flex-direction: column;
  align-items: center;
  .title {
    color: #b8d4ea;
    font-size: 20px;
    margin-top: 50px;
  }
  .nextBtn {
    margin-top: 10%;
    width: 80%;
    height: 50px;
    background: url("../../assets/imgs/login/btn.png") no-repeat center /
      contain;
    text-align: center;
    line-height: 50px;
    color: #12222e;
    font-size: 20px;
    cursor: pointer;
  }
}
.opreation {
  margin-top: 10%;
  width: 80%;
  display: flex;
  justify-content: space-between;
  .forget_register {
    > a {
      color: #0092ff;
      margin-left: 10px;
    }
  }
}

.forgot_content {
  .account,
  .verifCode {
    position: relative;
    /deep/.el-input__inner {
      /* WebKit browsers */
      &::-webkit-input-placeholder {
        color: #b8d5eb;
      }
      /* Mozilla Firefox 4 to 18 */
      &:-moz-placeholder {
        color: #b8d5eb;
      }
      /* Mozilla Firefox 19+ */
      &::-moz-placeholder {
        color: #b8d5eb;
      }
      /* Internet Explorer 10+ */
      &:-ms-input-placeholder {
        color: #b8d5eb;
      }
    }
  }
  /deep/.el-button {
    background-color: #101e28;
    border: 0;
    width: 90px;
    height: 26px;
    border-radius: 0;
    padding: 0;
    position: absolute;
    right: 0;
    bottom: 10px;
    span {
      color: #b8d5eb;
      font-size: 14px;
    }
  }
  .tip {
    display: inline-block;
    width: 100%;
    position: absolute;
    bottom: -34px;
    left: 0;
    color: #ff5a00;
    font-size: 12px;
    text-indent: 2em;
  }
}

.el-form {
  width: 80%;
  margin-top: 20%;
  .el-form-item {
    border-bottom: 1px solid #3a5970;
  }
  /deep/ .el-form-item__label {
    width: 52px !important;
    color: #b8d5eb;
  }
  /deep/ .el-input {
    width: 95%;
  }
  /deep/ .el-input__inner {
    border-color: #233848;
    background-color: #233848;
    color: #0092ff;
    border: none;
    /* WebKit browsers */
    &::-webkit-input-placeholder {
      color: #3a5970;
    }
    /* Mozilla Firefox 4 to 18 */
    &:-moz-placeholder {
      color: #3a5970;
    }
    /* Mozilla Firefox 19+ */
    &::-moz-placeholder {
      color: #3a5970;
    }
    /* Internet Explorer 10+ */
    &:-ms-input-placeholder {
      color: #3a5970;
    }
  }
}
// 自動登錄框
.el-checkbox {
  color: #b8d4ea;
}
/deep/ .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #b8d4ea;
}
/deep/ .el-checkbox__inner {
  background-color: #233848;
  border: 1px solid #3a5970;
}

/* 修改自動填充樣式 用陰影遮擋內容背景色 */
/deep/ input:-webkit-autofill,
/deep/ textarea:-webkit-autofill,
/deep/ select:-webkit-autofill {
  -webkit-box-shadow: 0 0 0 1000px #233848 inset !important;
  border: none !important;
  border-radius: 0;
}</style
>>
