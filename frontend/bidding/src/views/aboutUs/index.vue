<template>
  <div class="about-us">
    <m-top />
    <m-logo />
    <section class="warpper">
      <div class="content type-area z-border-box z-flex">
        <m-side-bar />
        <m-content />
      </div>
    </section>
    <m-footer />
  </div>
</template>

<script>
import mTop from "../../components/Top";
import mLogo from "../../components/Logo";
import mFooter from "../../components/Footer";
import mSideBar from "./Modules/SideBar";
import mContent from "./Modules/Content";

export default {
  components: {
    mTop,
    mLogo,
    mFooter,
    mSideBar,
    mContent
  }
};
</script>

<style lang="less" scoped>
.about-us {
  .warpper {
    box-sizing: border-box;
    min-height: calc(100vh - 270px);
    background-color: #12222e;
    padding-top: 20px;
  }
  .content {
    min-height: 680px;
    height: 100%;
    background: rgba(13, 28, 42, 0.6);
    padding: 20px;
  }
}
</style>
