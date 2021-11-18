<template>
  <el-container>
    <el-main>
      <el-header>
        <BbHeader></BbHeader>
      </el-header>
      <router-view></router-view>
      <el-footer>
        <BbFooter></BbFooter>
      </el-footer>
    </el-main>
  </el-container>
</template>

<script>
import BbHeader from "../components/Header";
import BbFooter from "../components/Footer";
export default {
  components: {
    BbHeader,
    BbFooter
  }
};
</script>

<style lang="less" scoped>
@import "../style/variable";

.el-container {
  height: 100%;
}
.el-header,
.el-main,
.el-footer {
  padding: 0;
}
.el-main {
  background-color: @mainBackgroundColor;
  overflow: visible;
  > div {
    width: 100%;
    min-height: calc(100vh - @headerHeight - @footHeight);
    box-sizing: border-box;
    padding: 20px 0;
  }
}
.el-header {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 100;
  width: 100%;
  height: @headerHeight !important;
}
.el-footer {
  height: @footHeight !important;
  background-color: #fff;
}
</style>
