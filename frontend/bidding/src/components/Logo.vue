<template>
  <section class="logo">
    <div class="type-area logo-content">
      <div class="left z-flex z-align-items-center">
        <router-link to="/"></router-link>
        <div class="announce">
          <div
            ref="announceText"
            class="per-wrapper"
            :style="{ top: announceTop + 'px' }"
            v-if="announceList.length > 1"
          >
            <div>
              <p v-for="item in announceList" :key="item.list_order">
                {{ item.list_order }}.{{ item.list_content }}
              </p>
            </div>
            <div>
              <p v-for="item in announceList" :key="item.list_order">
                {{ item.list_order }}.{{ item.list_content }}
              </p>
            </div>
          </div>
          <div class="per-wrapper" v-else-if="announceList.length == 1">
            <div>
              <p>
                {{ announceList[0].list_order }}.{{
                  announceList[0].list_content
                }}
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- <a href="../../index.html"></a> -->
      <div class="search-box">
        <!-- <input type="text" class="search-text" placeholder="請輸入信息" />
        <input type="submit" class="search-btn" value="搜索" /> -->
      </div>
    </div>
  </section>
</template>

<script>
import { listBulletin } from "@/api/admin";

export default {
  name: "logo",
  data() {
    return {
      // 距离顶部距离
      announceTop: 0,
      // 接受定時器
      timer: "",
      // 公告
      announceList: [],
    };
  },
  created() {
    this.getAnnounce();
  },
  destroyed() {
    // clearInterval(this.timer);
  },
  methods: {
    // 當前頁面是否活躍 chrome
    isVisible() {
      let _this = this;
      document.addEventListener("visibilitychange", function() {
        if (!document.hidden) {
          _this.getPositon();
        } else {
          clearInterval(_this.timer);
        }
      });
    },

    // 获取定位
    getPositon() {
      if (!this.$refs.announceText) {
        return;
      }
      const announceH = this.$refs.announceText.clientHeight;
      let topH = this.announceTop;
      let _this = this;
      this.timer = setInterval(function() {
        if (Math.abs(topH) < announceH) {
          // 如果有公告信息
          let currH = topH;
          let moveTimer = setInterval(function() {
            if (topH <= currH - 20) {
              clearInterval(moveTimer);
            } else {
              // 自減1
              topH -= 1;
              if (Math.abs(topH) >= announceH) {
                // 如果超出了自己本身的高度
                clearInterval(moveTimer);
                topH = 0;
              }
              _this.announceTop = topH;
            }
          }, 60);
        } else {
          // 如果沒有公告信息，直接清除
          clearInterval(_this.timer);
        }
      }, 4000);
    },

    // 獲取公告
    getAnnounce() {
      const data = {};
      listBulletin(data).then(res => {
        if (res.code === "1") {
          this.announceList = res.t;
          this.$nextTick(() => {
            this.getPositon();
            this.isVisible();
          });
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
// logo和搜索框
.logo {
  height: 80px;
  background-color: #d7efff; //rgba(215,239,255,1)
  display: flex;
  align-items: center;
}

.logo-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  .left {
    position: relative;
    width: 70%;
    > a {
      display: block;
      width: 127px;
      height: 49px;
      background: url(../assets/imgs/index/LOGO-gif5.gif) no-repeat;
      background-size: contain;
    }
  }
}
.announce {
  width: 70%;
  position: relative;
  height: 40px;
  overflow: hidden;
  margin-left: 6rem;
  .per-wrapper {
    position: absolute;
    top: 0;
    width: 100%;
    > div {
      &:nth-child(2) {
        position: absolute;
        width: 100%;
      }
      p {
        margin: 0 auto;
        line-height: 20px;
        color: #12222e;
        font-size: 16px;
        width: 100%;
      }
    }
  }
}
.search-box {
  height: 36px;
  font-size: 0;
  display: flex;
  align-items: center;
}
.search-text {
  font-size: 16px;
  width: 250px;
  height: 34px;
  border: 1px solid rgba(145, 173, 192, 1);
  padding: 0;
  // 首行縮進
  text-indent: 10px;
  // 藍色高亮
  outline: none;
  font-size: 14px;
}
.search-btn {
  width: 70px;
  height: 36px;
  background-color: #0096ff;
  font-size: 16px;
  color: #c9e1f1;
  border: 1px solid #0096ff;
  border-radius: 0px 2px 2px 0px;
  cursor: pointer;
}
</style>
