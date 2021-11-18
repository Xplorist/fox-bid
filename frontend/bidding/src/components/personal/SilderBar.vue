<template>
  <div class="sideBar" v-if="userInfo">
    <div class="side-portrait">
      <!-- 頭像 -->
      <img
        v-if="porImg"
        :src="porImg"
        alt="頭像加載失敗"
        onerror="this.style.display='none'"
        style="width:120px;height:120px;text-align:center;line-height:120px;borderRadius: 50%;"
      />
      <div v-else class="porImg"></div>
    </div>
    <!-- 部門 -->
    <div class="side-department">
      <span class="dep-icon"></span>
      <span>{{ userInfo.dept_name }}</span>
    </div>
    <!-- 導航欄 -->
    <div class="side-navigate">
      <router-link :to='item.path' v-for="(item, index) in list" :key="index" :class="{active: chooseItem == item.ename }">
        <div>
          {{item.name}}
          <span v-show="item.sub">({{item.sub}})</span>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      porImg: "",
      list: [
        { ename: "order", name: "訂單管理", path: "/personal", sub: "" },
        {
          ename: "information",
          name: "賬戶信息",
          path: "/personal/information",
          sub: ""
        },
        {
          ename: "evaluation",
          name: "評價管理",
          path: "/personal/evaluation",
          sub: ""
        },
        // { ename: "news", name: "消息", path: "", sub: "2" },
        // { ename: "", name: "收藏", path: "", sub: "" },
        { ename: "setting", name: "設置", path: "/personal/setting", sub: "" }
      ]
    };
  },
  props: ["chooseItem"],
  mounted() {
    this.porImg = this.porImgUrl
  },
  methods: {},
  watch:{
    porImgUrl:function(){
      this.porImg = this.porImgUrl
    }
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
// 左側導航
.sideBar {
  width: 240px;
  // 設置最小高度
  min-height: 800px;
  background-color: #d3dfe7;
  display: flex;
  flex-direction: column;
  align-items: center;
}
// 頭像
.side-portrait {
  margin-top: 55px;
  width: 130px;
  height: 130px;
  border-radius: 50%;
  background-color: #eef4f7;
  display: flex;
  justify-content: center;
  align-items: center;
  user-select: none;
  .porImg {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background-image: url(../../assets/imgs/personal/portrait.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }
  img{
    object-fit: cover;
  }
}
// 所屬部門
.side-department {
  margin-top: 18px;
  font-size: 18px;
  color: #212f3a;
  display: flex;
  align-items: center;
  > .dep-icon {
    display: inline-block;
    width: 14px;
    height: 23px;
    background: url(../../assets/imgs/personal/depIcon.png) no-repeat;
    margin-right: 6px;
  }
}
// 導航條目
.side-navigate {
  margin-top: 26px;
  width: 100%;
  user-select: none;
  > a {
    display: block;
    width: 100%;
    height: 60px;
    border-top: 1px solid #c0c8cf;
    transition-duration: 0.2s;
    &.active,
    &:hover {
      background-color: #2e6e9e;
      > div {
        color: #fff;
      }
    }
  }
  div {
    width: 60%;
    height: 60px;
    margin: 0 auto;
    line-height: 60px;
    font-size: 16px;
    color: #12222e;
    transition-duration: 0.2s;
    > span {
      color: #e51313;
    }
  }
}
</style>