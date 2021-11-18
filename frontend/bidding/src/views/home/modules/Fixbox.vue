<template>
  <div class="fix-box">
    <!-- 頭像 -->
    <!-- <img
      v-if="porImgUrl"
      style="width: 100px; height: 100px; object-fit:cover;"
      :src="porImgUrl"
      alt
    /> -->
    <!-- 功能 -->
    <!-- <div class="func" v-if="porImgUrl">
      <div class="single collect">
        <div class="func-icon"></div>
        <div class="func-text">收藏</div>
      </div>
      <div class="single history">
        <div class="func-icon"></div>
        <div class="func-text">足跡</div>
      </div>
      <div class="single news">
        <div class="func-icon"></div>
        <div class="func-text">消息</div>
        <div class="func-num"></div>
      </div>
      <div class="single order">
        <div class="func-icon"></div>
        <div class="func-text">我的訂單</div>
      </div>
    </div> -->
    <!-- 返回頂端 -->
    <div class="backtop" v-show="backFlag" @click="backTop()">
      <div class="backtop-icon"></div>
      <div class="backtop-text">返回頂端</div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  methods: {
    backTop() {
      this.$emit("backTop");
    }
  },
  props: ["backFlag"],
  computed: {
    ...mapState({
      porImgUrl: state => state.porImgUrl
    })
  }
};
</script>

<style lang="less" scoped>
// 右側固定
.fix-box {
  position: fixed;
  top: 400px;
  right: 0%;
  height: 360px;
  width: 100px;
  text-align: center;
  .func {
    height: 210px;
    font-size: 14px;
    color: #d3dfe7;
    background-image: url("../../../assets/imgs/index/fixBoxBG.png");
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  // 公共樣式
  .single {
    height: 100%;
    width: 92px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    &:hover {
      color: #0096ff;
    }
    .func-icon {
      width: 16px;
      height: 16px;
      background-size: contain;
      background-repeat: no-repeat;
      margin-right: 10px;
    }
  }
  // 收藏
  .collect .func-icon {
    background-image: url("../../../assets/imgs/index/collect.png");
  }

  // 消息
  .news .func-icon {
    background-image: url("../../../assets/imgs/index/news.png");
  }

  // 足迹
  .history .func-icon {
    background-image: url("../../../assets/imgs/index/history.png");
  }

  // 訂單
  .order .func-icon {
    background-image: url("../../../assets/imgs/index/order.png");
  }
  .order :hover {
    color: black;
  }
  // 返回頂部
  .backtop {
    width: 100%;
    font-size: 14px;
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    cursor: pointer;
    .backtop-icon {
      background-repeat: no-repeat;
      background-image: url("../../../assets/imgs/index/backTop.png");
      width: 26px;
      height: 9px;
      margin: 0 auto 6px;
    }
    .backtop-text {
      color: #fff;
    }
  }
}
</style>
