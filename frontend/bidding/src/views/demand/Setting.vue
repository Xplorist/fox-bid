<template>
  <div id="setting">
    <!-- 主體內容 -->
    <!-- title -->
    <div class="main_title">設置</div>
    <button @click="quit()">退出登錄</button>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data: function() {
    return {};
  },
  methods: {
    // 退出登錄，清除信息
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
  computed: {
    ...mapState({
      userInfo: state => state.userInfo
    })
  },
  created() {}
};
</script>

<style lang="less" scoped>
#setting {
  background-color: #eff9ff;
  box-sizing: border-box;
  color: #12222e;
  padding: 30px 100px;
  height: 100%;
}

/* 主體部分 */
.main_title {
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #d3dfe7;
}
</style>
