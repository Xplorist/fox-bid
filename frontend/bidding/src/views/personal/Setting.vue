<template>
  <div class="information">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area">
        <SilderBar :choose-item="silderBarItem"></SilderBar>
        <div class="mainPart">
          <!-- title -->
          <div class="main_title">設置</div>
          <button @click="quit">退出登錄</button>
        </div>
      </div>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
import Top from "../../components/Top";
import Logo from "../../components/Logo";
import Footer from "../../components/Footer";
import SilderBar from "../../components/personal/SilderBar";

import { mapState } from "vuex";

export default {
  data: function() {
    return {
      // 侧边栏选中条目
      silderBarItem: "setting"
    };
  },
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
    }
  },
  components: {
    Top,
    Logo,
    Footer,
    SilderBar
  },
  computed: {
    ...mapState({
      userInfo: state => state.userInfo,
      porImgUrl: state => state.porImgUrl
    })
  }
};
</script>

<style lang="less" scoped>
// 主體內容
#content {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  padding-top: 24px;
  > .main {
    background: rgba(13, 28, 42, 0.2);
    box-sizing: border-box;
    padding: 20px;
    display: flex;
    justify-content: space-between;
  }
}
// 主體部分
.mainPart {
  width: 910px;
  background-color: #d3dfe7;
  box-sizing: border-box;
  padding: 30px;
}
.main_title {
  color: #12222e;
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #afc2cf;
}

/deep/ .el-form-item {
  display: flex;
  align-items: center;

  // 賬號屬性欄修改
  &.properties {
    span {
      color: #0092ff;
    }
  }
}
</style>
