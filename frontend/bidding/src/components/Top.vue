<template>
  <section id="top">
    <div class="type-area top-nav">
      <div class="backHome">
        <router-link v-show="backHome || false" to="">
          返回首頁
        </router-link>
      </div>
      <ul>
        <li v-if="userInfo">
          <router-link to="">歡迎您：{{ userInfo.username }}</router-link>
        </li>
        <li v-show="this.$router.history.current.name !== 'home'">
          <router-link to="/">主頁</router-link>
        </li>
        <li>
          <router-link
            v-if="userInfo"
            :to="
              userInfo
                ? userInfo.send_recv_type == 'send'
                  ? '/demand'
                  : userInfo.send_recv_type == 'recv'
                  ? '/personal'
                  : '/admin'
                : '/login'
            "
            >個人中心</router-link
          >
          <router-link v-else to="/login">登錄</router-link>
        </li>

        <li>
          <router-link to="/merchant">註冊</router-link>
        </li>
        <li>
          <!-- <a href="http://10.244.231.103:8086/bidbbs" target="_blank">論壇</a> -->
          <!-- <span @click="handleBbsClick">
            論壇
          </span> -->
          <a :href="bbsURL + '/bidbbs/'" target="_blank">論壇</a>
        </li>
        <li v-if="userInfo" @click="quit">
          <router-link to="">退出登錄</router-link>
        </li>
        <li>
          <router-link to="" @click.native="dialogTableVisible = true"
            >关于我们</router-link
          >
        </li>
      </ul>
    </div>
    <div>
      <el-dialog title="" :visible.sync="dialogTableVisible">
        <div class="sideContent z-border-box">
          <div class="z-text-center">關於我們</div>
          <pre>
      我们致力于发展成为一个专业的产品开发技术信息服务平台，为各单位提供更公平、快捷、开放的加工资源交易方案。通过平台订单信息公开，产品生产单位可以快速地找到优势加工资源降低产品开发门槛，短缩量产准备时间；加工服务单位可结合自身当前的经营状况，积极承接或转发订单，从而实现营收平衡，发展持续！
      希望各单位通过平台使用，生产力和竞争力可以得到提升，进而为公司永续经营提供更多活力！                            
    </pre
          >
          <hr />
          <table>
            <tr>
              <td>地址</td>
              <td>：</td>
              <td>富士康科技集团（成都）D23三楼西侧</td>
            </tr>
            <tr>
              <td>電話</td>
              <td>：</td>
              <td>5073-66645</td>
            </tr>
            <tr>
              <td>E-mall</td>
              <td>：</td>
              <td>hzlh-mac-gongweilong@mail.foxconn.com</td>
            </tr>
          </table>
        </div>
      </el-dialog>
    </div>
  </section>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "top",
  data() {
    return {
      dialogTableVisible: false,
      bbsURL: window.location.origin
    };
  },
  created() {
    console.log(this.$router.history.current.name);
  },
  // computed: {
  //   bbsBaseUrl() {
  //     return window.location.origin;
  //   }
  // },
  methods: {
    quit() {
      const data = {
        callbackName: "jsonpFunc"
      };
      this.jsp("logout", data).then(res => {
        if (res.code === "1") {
          this.$router.push("/login");
          this.$store.commit("LOG_OUT");
        }
      });
    },
    handleBbsClick() {
      // console.log(window.location.origin + "/bidbbs")
      window.open(window.location.origin + "/bidbbs");
    }
  },
  props: {
    backHome: Boolean
  },
  computed: {
    ...mapState({
      username: state => state.username,
      userInfo: state => state.userInfo,
      token: state => state.token
    })
  }
};
</script>

<style lang="less" scoped>
// 頂部
#top {
  height: 30px;
  width: 100%;
  background: #aac0cf; // rgba(170,192,207,1)
  color: #333;
  user-select: none;
  .top-nav {
    height: 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .backHome {
    a {
      color: #333;
    }
  }
  ul {
    li {
      height: 30px;
      line-height: 30px;
      float: left;
      margin-left: 30px;
      font-size: 16px;
      color: #333333;
    }
    a {
      text-decoration: none;
      color: #333;
    }
  }
}
// 关于弹窗
/deep/.el-dialog {
  .el-dialog__header,
  .el-dialog__body {
    background-color: #d3dfe7;
  }
  .el-dialog__close {
    padding: 1px;
    border-radius: 50%;
    box-sizing: border-box;
    color: #d3dfe7;
    background-color: #485463;
  }
  .el-dialog__headerbtn:focus .el-dialog__close,
  .el-dialog__headerbtn:hover .el-dialog__close {
    color: #d3dfe7;
  }
}
.sideContent {
  flex: 1;
  background-color: #d3dfe7;
  padding: 0 45px 45px 45px;
  color: #333333;
  > div {
    font-size: 24px;
    color: #0092ff;
  }
  > pre {
    white-space: pre-wrap;
    word-wrap: break-word;
    margin-top: 45px;
    font-size: 14px;
    color: #333333;
    line-height: 30px;
  }
  > hr {
    border-color: #d3dfe7;
  }
  > table {
    font-size: 14px;
    margin-top: 30px;
    tr {
      height: 30px;
      td {
        &:nth-child(1) {
          text-align-last: justify;
        }
      }
    }
  }
}
</style>
