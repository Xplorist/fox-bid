<template>
  <div class="entryFir">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area">
        <div class="general">
          <!-- 頂部 -->
          <div class="gen_title">
            <span>模治檢具订单信息服務平台【厂商入駐】</span>
            <!-- <span>数据截至到2019-08-31 24:00:00</span> -->
          </div>
          <!-- 交易總額 -->
          <!-- <div class="gen_amount">
            <span>平台交易總額</span>
            <div class="gen_am_number">
              <span>￥</span>
              <span>95,371,863</span>
              <span>元</span>
            </div>
            <div class="gen_am_info">
              <span>需求總量: 13,537個</span>
              <span>加工廠商: 159個</span>
              <span>需求廠商: 159個</span>
            </div>
          </div> -->
        </div>
        <div class="slogan">加入我们,让您遇见更好的伙伴</div>
        <!-- 選擇 -->
        <div class="choose">
          <div class="cho_title">
            <span>立即入駐</span>
            <span>根據自身角色選擇註冊類型</span>
          </div>
          <div class="cho_category">
            <div
              class="cho_cate_recipients"
              :class="{ active: category.checked }"
              @click="changCategory(true)"
            >
              <span>我是接單方</span>
              <span>承接模治具加工、設計</span>
              <span
                :style="
                  category.checked
                    ? category.recipientsActive
                    : category.recipients
                "
              ></span>
            </div>
            <div
              class="cho_cate_demanders"
              :class="{ active: !category.checked }"
              @click="changCategory(false)"
            >
              <span>我是需求方</span>
              <span>我有任務，需要發佈</span>
              <span
                :style="
                  category.checked
                    ? category.demanders
                    : category.demandersActive
                "
              ></span>
            </div>
          </div>
          <div class="cho_nextBtn">
            <router-link :to="{ path: 'merchant/entrySec', query: {} }">
              <svg-btn
                inColor="3FB0FF"
                outColor="#0096FF"
                fillColor="#0096FF"
              />
              <span>下一步</span>
            </router-link>
          </div>
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
import SvgBtn from "../../components/operate/SvgBtn";

export default {
  data: function() {
    return {
      category: {
        // 選中項
        checked: true, // true: recipients false: demanders
        // 圖片地址
        recipients: {
          backgroundImage:
            "url(" + require("../../assets/imgs/merchant/recipients.png") + ")"
        },
        recipientsActive: {
          backgroundImage:
            "url(" +
            require("../../assets/imgs/merchant/recipientsActive.png") +
            ")"
        },
        demanders: {
          backgroundImage:
            "url(" + require("../../assets/imgs/merchant/demanders.png") + ")"
        },
        demandersActive: {
          backgroundImage:
            "url(" +
            require("../../assets/imgs/merchant/demandersActive.png") +
            ")"
        }
      }
    };
  },
  methods: {
    changCategory(val) {
      this.category.checked = val;
      val = val ? "recv" : "send";
      sessionStorage.setItem("merchangtCategory", val);
      return;
    }
  },
  created() {
    sessionStorage.setItem("merchangtCategory", "recv");
  },
  components: {
    Top,
    Logo,
    Footer,
    SvgBtn
  }
};
</script>

<style lang="less" scoped>
// 主體內容
#content {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  padding-top: 24px;
  // 邊界效果
  > .main {
    background: rgba(13, 28, 42, 0.2);
    box-sizing: border-box;
    padding: 20px;
    position: relative;
  }
  // 基本信息
  .general {
    height: 100px;
    padding: 20px 30px;
    box-sizing: border-box;
    background-color: #0096ff;
  }
  // 頂部信息
  .gen_title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #fff;
    span:nth-child(2) {
      color: #9dccff;
      font-size: 12px;
    }
  }
  // 總額
  .gen_amount {
    margin-top: 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #fff;
    > span {
      color: #d9e9f3;
      font-size: 14px;
    }
  }
  // 具體數值
  .gen_am_number {
    margin-top: 8px;
    span:nth-child(1),
    span:nth-child(3) {
      font-size: 14px;
    }
    span:nth-child(2) {
      font-size: 48px;
    }
  }
  // 其餘總額相關信息
  .gen_am_info {
    margin-top: 36px;
    width: 100%;
    display: flex;
    justify-content: space-around;
    color: #d9e9f3;
    font-size: 20px;
  }
  // 標語
  .slogan {
    width: 422px;
    height: 45px;
    background-repeat: no-repeat;
    background-image: url("../../assets/imgs/merchant/sloganBG.png");
    position: absolute;
    top: 98px;
    left: 50%;
    transform: translateX(-50%);
    color: #d3dfe7;
    font-size: 20px;
    text-align: center;
    line-height: 45px;
  }
  // 選擇框
  .choose {
    padding-top: 60px;
    background-color: #d3dfe7;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  // 頂部信息
  .cho_title {
    display: flex;
    flex-direction: column;
    align-items: center;
    span:nth-child(1) {
      color: #12222e;
      font-size: 24px;
    }
    span:nth-child(2) {
      color: #0096ff;
      margin-top: 12px;
    }
  }
  // 類別
  .cho_category {
    margin-top: 40px;
    width: 90%;
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  // 具體的類別框
  .cho_cate_recipients,
  .cho_cate_demanders {
    box-sizing: border-box;
    border: 1px solid;
    border-color: #c6d4de;
    width: 400px;
    height: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #c6d4de;
    cursor: pointer;
    span:nth-child(1) {
      margin-top: 30px;
      font-size: 20px;
      color: #12222e;
    }
    span:nth-child(2) {
      color: #50616f;
      margin-top: 10px;
      font-size: 14px;
    }
    span:nth-child(3) {
      margin-top: 20px;
      display: block;
      width: 75px;
      height: 75px;
      background-repeat: no-repeat;
    }
    &.active {
      background-color: #d3dfe7;
      border-color: #0096ff;
      span:nth-child(1) {
        color: #0096ff;
        margin-top: 30px;
        font-size: 20px;
      }
      span:nth-child(2) {
        color: #0096ff;
        margin-top: 10px;
        font-size: 14px;
      }
      span:nth-child(3) {
        margin-top: 20px;
        display: block;
        width: 75px;
        height: 75px;
        background-repeat: no-repeat;
      }
    }
  }
  // 按鈕
  .cho_nextBtn {
    margin: 60px 0;
    position: relative;
    cursor: pointer;
    span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-size: 20px;
      color: #fff;
    }
  }
}
</style>
