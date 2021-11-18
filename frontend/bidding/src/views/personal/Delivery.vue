<template>
  <div class="delivery">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area" v-if="orderInfo">
        <SilderBar :choose-item="silderBarItem"></SilderBar>
        <div class="mainPart">
          <!-- 頂部信息 -->
          <div class="main_headline">
            <div class="main_head_back">
              <div class="head_back_backBtn" @click="$router.back(-1)">
                <span></span>
                返回
              </div>
              <div class="head_back_status">交貨</div>
            </div>
            <div class="main_head_title">
              <div class="head_tit_text">【模具】需求單號{{orderInfo.bill_no}}</div>
              <div class="head_tit_time">交貨時間: {{orderInfo.deliver_date.split(" ")[0]}}</div>
            </div>
          </div>
          <!-- 基本信息 -->
          <div class="main_baseInfo">
            <div class="main_base_title">基本信息</div>
            <div class="main_base_content">
              <!-- table1 -->
              <table>
                <tr>
                  <td>產品處</td>
                  <td>:</td>
                  <td>{{orderInfo.send_user.pd_office}}</td>
                </tr>
                <tr>
                  <td>需求單位</td>
                  <td>:</td>
                  <td>{{orderInfo.send_user.dept_name}}</td>
                </tr>
                <tr>
                  <td>交易法人名稱</td>
                  <td>:</td>
                  <td>{{orderInfo.send_user.legal_person}}</td>
                </tr>
                <tr>
                  <td>交貨地點</td>
                  <td>:</td>
                  <td>{{orderInfo.deliver_address}}</td>
                </tr>
                <tr>
                  <td>費用代碼</td>
                  <td>:</td>
                  <td>{{orderInfo.send_user.cost_code}}</td>
                </tr>
              </table>
              <!-- table2 -->
              <table>
                <tr>
                  <td>接受總價</td>
                  <td>:</td>
                  <td>{{orderInfo.total_price}}</td>
                </tr>
                <tr>
                  <td>幣別</td>
                  <td>:</td>
                  <td>{{orderInfo.money_type}}</td>
                </tr>
                <tr>
                  <td>競標範圍</td>
                  <td>:</td>
                  <td>{{orderInfo.bid_range}}</td>
                </tr>
                <tr>
                  <td>競價開始時間</td>
                  <td>:</td>
                  <td>{{orderInfo.bid_start_date}}</td>
                </tr>
                <tr>
                  <td>競價結束時間</td>
                  <td>:</td>
                  <td>{{orderInfo.bid_end_date}}</td>
                </tr>
              </table>
            </div>
          </div>
          <!-- 時間 -->
          <!-- <div class="main_timeRemaining">
            <p>交貨剩餘時間:</p>
            <p>
              09
              <span>天</span>
              15
              <span>小時</span>
              34
              <span>分</span>
              21
              <span>秒</span>
            </p>
            <p>制作已用时间：20天17小时25分39秒</p>
            <div>申请延迟交货</div>
          </div>-->
          <!-- 確認交貨按鈕 -->
          <div class="main_confirm">
            <div class="main_con_upload">
              <span></span>
              <p>上傳憑證</p>
            </div>
            <div class="main_con_conBtn" @click="confirm">
              <svg width="265px" height="65px" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <polygon
                  points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
                  style="fill:none;stroke:e51313;stroke-width:1"
                />
                <rect x="2" y="2" width="261" height="61" style="fill:e51313;" />
                <polyline
                  points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
                  style="fill:none;stroke:#ea4242;stroke-width:2;"
                />
              </svg>
              <span>確認交貨</span>
            </div>
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

import { query_bill_by_pkid, update_bill_status } from "@/api/order";

export default {
  data: function() {
    return {
      // 选中的侧边栏条目
      silderBarItem: "order",
      // 訂單信息
      orderInfo: ""
    };
  },
  methods: {
    // 從後台查詢訂單數據
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
    confirm() {
      this.updataStatus();
    },

    // 提交狀態
    updataStatus() {
      const data = {
        pkid: this.orderInfo.pkid,
        bill_status: 3
      };
      update_bill_status(data).then(res => {
        if (res.code === "1") {
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
// 主體內容
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
  // 右側主體
  .mainPart {
    width: 910px;
    background-color: #d3dfe7;
    padding: 30px;
    box-sizing: border-box;
  }
  // <!-- 頂部信息 -->
  .main_headline {
  }
  // 返回區域
  .main_head_back {
    display: flex;
    align-items: center;
  }
  // 返回按鈕
  .head_back_backBtn {
    color: #626f7f;
    font-size: 14px;
    display: flex;
    align-items: center;
    cursor: pointer;
    // 返回圖標
    span {
      display: block;
      width: 17px;
      height: 12px;
      background: url(../../assets/imgs/personal/back.png) no-repeat;
      margin-right: 6px;
    }
  }
  // 當前狀態
  .head_back_status {
    color: #0096ff;
    font-size: 18px;
    margin-left: 28px;
  }
  // 主內容標題
  .main_head_title {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .head_tit_text {
    color: #212f3a;
    font-size: 18px;
  }
  .head_tit_time {
    color: #0096ff;
    font-size: 16px;
    margin-top: 15px;
  }
  // <!-- 基本信息 -->
  .main_baseInfo {
  }
  .main_base_title {
    color: #485463;
    font-size: 16px;
    border-bottom: 1px solid #c0c8cf;
    margin-top: 20px;
    padding: 10px 0;
  }
  .main_base_content {
    display: flex;
    justify-content: space-between;
    margin-top: 15px;
    width: 80%;
    td {
      height: 24px;
      color: #212f3a;
      font-size: 16px;
      &:nth-child(1) {
        color: #626f7f;
        // 文本居中
        // text-align:center;
        text-align: justify;
        // text-justify:distribute-all-lines;
        text-align-last: justify;
      }
      &:nth-child(2) {
        width: 20px;
        color: #626f7f;
      }
      &:nth-child(3) {
      }
    }
  }
  // <!-- 時間 -->
  .main_timeRemaining {
    margin-top: 100px;
    background-color: #384752;
    text-align: center;
    font-size: 16px;
    padding: 4px 0;
    position: relative;
    > p:nth-child(1) {
      color: #748089;
    }
    > p:nth-child(2) {
      color: #d3dfe7;
      font-size: 30px;
      margin: 0 0 30px 0;
      > span {
        font-size: 16px;
      }
    }
    > p:nth-child(3) {
      color: #748089;
    }
    > div {
      width: 100px;
      height: 26px;
      position: absolute;
      top: 70px;
      right: 30px;
      background-color: #203443;
      color: #5d6e79;
      font-size: 14px;
      line-height: 26px;
      text-align: center;
    }
  }

  // <!-- 確認交貨按鈕 -->
  .main_confirm {
    text-align: center;
    margin: 80px 0;
  }
  .main_con_upload {
    display: flex;
    justify-content: center;
    align-items: center;
    color: #0096ff;
    font-size: 14px;
    > span {
      display: block;
      width: 16px;
      height: 16px;
      background: url(../../assets/imgs/personal/upload.png) no-repeat;
      margin-right: 10px;
    }
  }
  .main_con_conBtn {
    color: #fff;
    font-size: 18px;
    position: relative;
    > svg {
      cursor: pointer;
    }
    > span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      cursor: pointer;
    }
  }
}
</style>