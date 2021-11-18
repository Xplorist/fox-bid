<template>
  <div class="container" v-if="userInfo" @click="handleClick">
    <el-container>
      <el-header height="60px">
        <div class="head-left">
          <router-link to="/"></router-link>
        </div>
        <div class="head-right">
          <!-- 搜索欄 -->
          <div class="search-box">
            <!-- <input type="text" class="search-text" placeholder="請輸入信息" />
            <input type="submit" class="search-btn" value="搜索" /> -->
          </div>
          <!-- 退出 -->
          <div
            @click="quit"
            style="margin-right:20px;cursor: pointer;user-select:none;"
          >
            退出登錄
          </div>
        </div>
      </el-header>
      <el-container>
        <!-- 側邊欄 -->
        <el-aside width="280px">
          <div class="side-portrait">
            <!-- 頭像 -->
            <img
              :src="porImgUrl"
              alt="頭像加載失敗"
              onerror="this.style.display='none'"
              style="width:120px;height:120px;text-align:center;line-height:120px;borderRadius: 60px;"
            />
            <!-- <div  class="porImg"></div> -->
          </div>
          <!-- 部門 -->
          <div class="side-department">
            <span class="dep-icon"></span>
            <span>{{ userInfo.username }}</span>
          </div>
          <!-- 導航欄 -->
          <div class="side-navigate">
            <router-link
              v-for="(item, index) in list"
              to
              :key="index"
              :class="{ active: chooseItem == item.ename }"
              @click.native="navigatTo(item.ename)"
            >
              <div>
                {{ item.name }}
                <span v-show="item.sub">({{ item.sub }})</span>
              </div>
            </router-link>
          </div>
        </el-aside>
        <!-- 主體內容區域 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      // 侧边栏列表
      list: [
        { ename: "/admin/audit", name: "賬號審核", sub: "" },
        { ename: "/admin/manage", name: "賬號管理", sub: "" },
        { ename: "/admin/postMessage", name: "消息發佈", sub: "" },
        { ename: "/admin/orderSummary", name: "訂單匯總", sub: "" },
        { ename: "/admin/administration", name: "經營管理", sub: "" }
      ],
      // 默认选择的项
      chooseItem: "/admin/audit"
    };
  },
  created() {
    this.getCurrentItem();
  },
  watch: {
    $route() {
      this.getCurrentItem();
    }
  },
  methods: {
    // 根據路由判定選中的側邊欄
    getCurrentItem() {
      let item = this.$router.history.current.name.split("-")[1];
      this.chooseItem = "/admin/" + item;
    },
    // 處理點擊事件 關閉此頁面上為關閉的下拉框
    handleClick() {
      if (this.mSelectList.length == 0) return;
      let arr = JSON.parse(JSON.stringify(this.mSelectList));
      for (let item of arr) {
        if (item.focus) {
          item.focus = false;
        }
      }
      this.$store.dispatch("get_mSelectList", arr);
    },
    // 点击修改
    navigatTo(val) {
      if (this.chooseItem == val) return;
      this.$router.push(val);
    },
    // 退出
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
      userInfo: state => state.userInfo,
      porImgUrl: state => state.porImgUrl,
      mSelectList: state => state.mSelectList
    })
  }
};
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
  overflow: hidden;
}
// 頭部
.el-header {
  display: flex;
  padding: 0;
  > .head-left {
    width: 280px;
    height: 100%;
    background-color: rgba(0, 146, 255, 1);
    display: flex;
    align-items: center;
    justify-content: center;
    a {
      display: block;
      width: 142px;
      height: 50px;
      background-image: url("../../assets/imgs/admin/logo.png");
      background-repeat: no-repeat;
      background-position: center;
      background-size: contain;
    }
  }
  > .head-right {
    flex: 1;
    height: 100%;
    background-color: rgba(215, 239, 255, 1);
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  // 搜索框
  .search-box {
    margin-left: 20px;
    height: 36px;
    font-size: 0;
    display: flex;
    align-items: center;
    overflow: hidden;
    border: 1px solid #91adc0;
    border-radius: 18px;
  }
  .search-text {
    background-color: #d7efff;
    border: none;
    font-size: 14px;
    width: 280px;
    height: 34px;
    padding: 0;
    // 首行縮進
    text-indent: 10px;
    outline: none;
    font-size: 14px;
    color: #91adc0;
  }
  .search-btn {
    width: 70px;
    height: 36px;
    background-color: #91adc0;
    font-size: 16px;
    color: #fff;
    border: 1px solid #91adc0;
    border-radius: 0px 2px 2px 0px;
    cursor: pointer;
  }
}
// 側邊欄
.el-aside {
  width: 240px;
  min-height: 600px;
  background-color: rgb(18, 34, 46);
  display: flex;
  flex-direction: column;
  align-items: center;
  user-select: none;
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
  img {
    object-fit: cover;
  }
}
// 所屬部門
.side-department {
  margin-top: 18px;
  font-size: 18px;
  color: rgba(156, 175, 189, 1);
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
  > a {
    display: block;
    width: 100%;
    height: 60px;
    border-bottom: 1px solid rgba(11, 24, 33, 1);
    transition-duration: 0.2s;
    &.active,
    &:hover {
      background-color: #0f1e29;
      > div {
        color: #0092ff;
      }
    }
  }
  div {
    width: 40%;
    height: 60px;
    margin: 0 auto;
    line-height: 60px;
    font-size: 16px;
    color: rgba(156, 175, 189, 1);
    transition-duration: 0.2s;
  }
}
// 主體
.el-main {
  padding: 0;
  background-color: #d3dee7;
}
</style>
