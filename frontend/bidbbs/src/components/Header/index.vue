<template>
  <el-col class="bb-header">
    <el-col class="header-content-wrapper container">
      <el-row :gutter="10">
        <el-col :span="8" class="flex-start">
          <router-link to="/" class="logo"> <span>BID</span>-BBS</router-link>
        </el-col>
        <el-col :span="10" class="">
          <ul class="nav-list flex-start">
            <li>
              <router-link to="/">
                首頁
              </router-link>
            </li>
            <li>
              <router-link to="/">
                最新
              </router-link>
            </li>
            <li>
              <router-link to="/">
                熱門
              </router-link>
            </li>
          </ul>
        </el-col>
        <el-col :span="4">
          <router-link class="write" to="/edit" target="_blank">
            <el-button type="primary" icon="el-icon-edit" round>
              發帖
            </el-button>
          </router-link>
        </el-col>
        <el-col :span="2">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <div class="avatar">
                user
              </div>
            </span>
            <el-dropdown-menu slot="dropdown">
              <!-- <el-dropdown-item command="info">個人信息</el-dropdown-item> -->
              <el-dropdown-item command="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
    </el-col>
  </el-col>
</template>

<script>
// import "element-ui/lib/theme-chalk/display.css";
export default {
  methods: {
    errorHandler() {
      return true;
    },
    handleCommand(command) {
      if (command === "logout") {
        let flag = confirm("确定登出？");
        if (!flag) {
          return;
        }
      const url = "/bid/bidsso/";

        this.$jsonp(url + "logout", {
          callbackName: "jsonpFunc"
        }).then(res => {
          if (res.code === "1") {
            sessionStorage.clear();
            this.$router.push("/login");
          }
        });
      }
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../style/variable.less";

.bb-header {
  box-sizing: border-box;
  border-top: 3px solid @mainColor;
  height: @headerHeight;
  box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.1), 0 1px rgba(0, 0, 0, 0.1);
  background: #fafafa;
  .header-content-wrapper {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
  .logo {
    font-size: 28px;
    font-weight: 800;
    transition-duration: 0.3s;
    > span {
      color: @mainColor;
    }
    &:hover {
      color: @mainColor;
    }
  }
  .container {
    height: 64px;
    line-height: 64px;
  }
  .nav-list {
    > li {
      margin-right: 2rem;
      cursor: pointer;
      &:hover {
        color: @mainColor;
      }
      > a {
        display: inline-block;
        height: 100%;
        transition-duration: 0.3s;
      }
    }
  }
  .avatar {
    width: 44px;
    height: 44px;
    line-height: 44px;
    border-radius: 50%;
    background-color: @mainColorLight;
    cursor: pointer;
  }
}
</style>
