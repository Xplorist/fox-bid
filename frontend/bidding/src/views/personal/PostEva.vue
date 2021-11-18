<template>
  <div class="postEva" v-if="orderInfo">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area">
        <SilderBar :choose-item="silderBarItem"></SilderBar>
        <div class="mainPart">
          <!-- 頂部 -->
          <div class="back">
            <div class="back_backBtn">
              <router-link to="/personal">
                <span></span>
                返回
              </router-link>
            </div>
            <div class="back_status">評價</div>
          </div>
          <div class="title">
            <div class="tit_text">【模具】需求單號{{orderInfo.bill_no}}</div>
            <div class="tit_time">交貨時間: {{orderInfo.deliver_date.split(" ")[0]}}</div>
          </div>
          <!-- evaluationBox -->
          <div class="evaluationBox">
            <!-- 綜合評分 -->
            <div class="rate-comprehensive rate">
              <div class="single">
                <span>綜合評價:</span>
                <el-rate
                  v-model="rate.comprehensive"
                  :texts="rateData.texts"
                  :colors="rateData.color"
                  text-color="#212F3A"
                  show-text
                  class="stars"
                ></el-rate>
              </div>
            </div>
            <!-- 評語框 -->
            <div class="commentsBox">
              <el-input type="textarea" placeholder="请输入您對商家的評價" v-model="comments"></el-input>
              <!-- 匿名 -->
              <!-- <div class="anonymity">
                <el-checkbox
                  label="匿名評價"
                  name="type"
                  @change="anonymityFlag = !anonymityFlag"
                  :checked="anonymityFlag"
                ></el-checkbox>
                <div>匿名評價不會再個人主頁中展示昵称</div>
              </div> -->
            </div>
            <!-- 付款時效 -->
            <div class="rate-speed rate">
              <div class="single">
                <span>付款時效:</span>
                <el-rate
                  v-model="rate.speed"
                  :texts="rateData.texts"
                  :colors="rateData.color"
                  text-color="#212F3A"
                  show-text
                  class="stars"
                ></el-rate>
              </div>
            </div>
            <!-- 服務態度 -->
            <div class="rate-attitude rate">
              <div class="single">
                <span>服務態度:</span>
                <el-rate
                  v-model="rate.attitude"
                  :texts="rateData.texts"
                  :colors="rateData.color"
                  text-color="#212F3A"
                  show-text
                  class="stars"
                ></el-rate>
              </div>
            </div>
          </div>
          <!-- 提交 -->
          <div class="confirm" @click="comfirm">
            <svg width="265px" height="65px" version="1.1" xmlns="http://www.w3.org/2000/svg">
              <polygon
                points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
                style="fill:none;stroke:#0096FF;stroke-width:1"
              />
              <rect x="2" y="2" width="261" height="61" style="fill:#0096FF;" />
              <polyline
                points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
                style="fill:none;stroke:#3FB0FF;stroke-width:2;"
              />
            </svg>
            <!-- old -->
            <span>提交</span>
          </div>
        </div>
      </div>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
import Top from "@/components/Top";
import Logo from "@/components/Logo";
import Footer from "@/components/Footer";
import SilderBar from "@/components/personal/SilderBar";

import { query_bill_by_pkid, save_recv_eval } from "@/api/order";

export default {
  data: function() {
    return {
      // 侧边栏选中条目
      silderBarItem: "order",
      // 评分信息
      rateData: {
        texts: ["(非常差)", "(差)", "(一般)", "(好)", "(非常好)"],
        color: ["#0096FF", "#0096FF", "#0096FF"]
      },
      // 評分
      rate: {
        comprehensive: null,
        speed: null,
        attitude: null
      },
      // 是否匿名
      anonymityFlag: false,
      // 評價
      comments: "",
      // 訂單信息
      orderInfo: null
    };
  },
  methods: {
    // 獲取信息
    getOrderInfo(pkid) {
      query_bill_by_pkid(pkid).then(res => {
        if (res.code === "1") {
          this.orderInfo = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 按鈕
    comfirm() {
      if (!this.rate.comprehensive || !this.rate.speed || !this.rate.attitude) {
        return this.$message.warning("評分未完成");
      }
      if (!this.comments) return this.$message.warning("評價未完成");
      this.postEva();
    },

    // 發起評價
    postEva() {
      const data = {
        bill_pkid: this.orderInfo.pkid,
        summary_score: this.rate.comprehensive,
        pay_rate_score: this.rate.speed,
        svc_atitu_score: this.rate.attitude,
        summary_text: this.comments,
        f_anomus: this.anonymityFlag ? "Y" : "N"
      };
      save_recv_eval(data).then(res => {
        if (res.code === "1") {
          this.$message.success("評價成功");
          this.$router.push("/personal");
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  },
  created() {
    this.getOrderInfo(this.$router.history.current.query.pkid);
  },
  components: {
    Top,
    Logo,
    Footer,
    SilderBar
  }
};
</script>

<style lang="less" scoped>
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
  padding: 30px;
  box-sizing: border-box;
}
// 返回按鈕
.back {
  display: flex;
  align-items: center;
}
.back_backBtn {
  > a {
    color: #626f7f;
    font-size: 14px;
    display: flex;
    align-items: center;
  }
  span {
    display: block;
    width: 17px;
    height: 12px;
    background: url(../../assets/imgs/personal/back.png) no-repeat;
    margin-right: 6px;
  }
}
.back_status {
  color: #0096ff;
  font-size: 18px;
  margin-left: 28px;
}
// 主內容標題
.title {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.tit_text {
  color: #212f3a;
  font-size: 18px;
}
.tit_time {
  color: #0096ff;
  font-size: 16px;
  margin-top: 15px;
}
// 評價框
.evaluationBox {
  margin-top: 30px;
  border-top: 1px solid #afc2cf;
  .rate {
    margin-top: 16px;
  }
  .single {
    display: flex;
    align-items: center;
  }
  .stars {
    margin: 5px;
    width: 180px;
  }
  /deep/ .el-icon-star-off:before {
    color: #0096ff;
  }
  // 評語
  .commentsBox {
    margin: 15px 0 50px 0;
  }
  .anonymity {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    > div {
      color: #626f7f;
    }
  }
  // 修改element默認樣式
  /deep/ .el-textarea__inner {
    padding: 20px;
    border-color: #afc2cf;
    border-radius: 0;
    background-color: #d3dfe7;
    height: 200px;
    &:focus {
      border-color: #0096ff;
    }
  }
  /deep/ .el-checkbox {
    display: flex;
    align-items: center;
  }
  /deep/ .el-checkbox__inner {
    background-color: #d3dfe7;
    border: 1px dashed #afc2cf;
    &:after {
      border-color: #0092ff;
    }
  }
  /deep/ .el-checkbox__label {
    font-size: 16px;
    color: #12222e;
  }
}
// 按鈕
.confirm {
  text-align: center;
  margin: 100px 0;
  position: relative;
  > svg {
    cursor: pointer;
  }
  > span {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: #fff;
    font-size: 18px;
  }
}
</style>