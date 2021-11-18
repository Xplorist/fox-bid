<template>
  <div class="bb-footer">
    <div class="main">
      <a href="">關於我們</a>
      <a href="">聯繫我們</a>
      <a href="">成都智網平台</a>
      <a href="">幫助中心</a>
    </div>
    <div class="icp">©1988-2020 富士康科技集團有限公司</div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
@import "../../style/variable";

.bb-footer {
  height: @footHeight;
  box-sizing: border-box;
  padding: 30px 0 0 0;
  font-size: 12px;
  .main {
    color: #969696;
    > a {
      padding: 0 4px;
    }
  }
  .icp {
    margin-top: 8px;
    color: #c8c8c8;
  }
}
</style>
