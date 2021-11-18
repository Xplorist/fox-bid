<template>
  <div id="biddingDoc">
    <Top></Top>
    <Logo></Logo>
    <section class="bidContent" v-if="userInfo">
      <div class="type-area">
        <div id="pdfDom">
          <!-- 標題 -->
          <div class="panel-title">
            <!-- 左線條 -->
            <div class="left-line">
              <div class="theLine"></div>
              <div class="theBlock"></div>
            </div>
            <!-- 標題 -->
            <div class="center-text">模治檢具標書</div>
            <!-- 右線條 -->
            <div class="right-line">
              <div class="theBlock"></div>
              <div class="theLine"></div>
            </div>
          </div>
          <!-- 內容 -->
          <div class="bid-main" v-if="orderInfo.send_user">
            <div class="pan_info_Top">
              <!-- 基本信息 -->
              <div class="baseInfo">
                <div class="ba_title">
                  <span>基本信息</span>
                </div>
                <table class="ba_content">
                  <tr>
                    <td>接單方</td>
                    <td>：</td>
                    <td style="width: 160px">{{ recvInfo.dept_name }}</td>
                    <td></td>
                    <td>發單方</td>
                    <td>：</td>
                    <td style="width: 160px">
                      {{ orderInfo.send_user.dept_name }}
                    </td>
                  </tr>
                  <tr>
                    <td>聯絡人</td>
                    <td>：</td>
                    <td>{{ recvInfo.busis_mngr }}</td>
                    <td></td>
                    <td>聯絡人</td>
                    <td>：</td>
                    <td>{{ orderInfo.send_user.busis_mngr }}</td>
                  </tr>
                  <tr>
                    <td>聯繫電話</td>
                    <td>：</td>
                    <td>{{ recvInfo.tel }}</td>
                    <td></td>
                    <td>聯繫電話</td>
                    <td>：</td>
                    <td>{{ orderInfo.send_user.tel }}</td>
                  </tr>
                  <tr>
                    <td>費用代碼</td>
                    <td>：</td>
                    <td>{{ recvInfo.cost_code }}</td>
                    <td></td>
                    <td>費用代碼</td>
                    <td>：</td>
                    <td>{{ orderInfo.send_user.cost_code }}</td>
                  </tr>
                  <tr>
                    <td>交易法人</td>
                    <td>：</td>
                    <td>{{ recvInfo.legal_person }}</td>
                    <td></td>
                    <td>交易法人</td>
                    <td>：</td>
                    <td>{{ orderInfo.send_user.legal_person }}</td>
                  </tr>
                </table>
              </div>
              <!-- 訂單基本信息 -->
              <div class="orderInfo">
                <div class="or_title">
                  <span>訂單信息</span>
                </div>
                <table class="or_content">
                  <tr>
                    <td>订单编号</td>
                    <td>：</td>
                    <td colspan="5">{{ orderInfo.bill_no }}</td>
                    <!-- <td colspan="4"></td> -->
                  </tr>
                  <tr>
                    <td>交易類型</td>
                    <td>：</td>
                    <td>{{ orderInfo.pd_type }}</td>
                    <td></td>
                    <td>交易法人</td>
                    <td>：</td>
                    <td>{{ orderInfo.send_user.legal_person }}</td>
                  </tr>
                  <tr>
                    <td>需求套數</td>
                    <td>：</td>
                    <td>{{ orderInfo.amount }}</td>
                    <td></td>
                    <td>交易地點</td>
                    <td>：</td>
                    <td>{{ orderInfo.deliver_address }}</td>
                  </tr>
                  <tr>
                    <td>交貨時間</td>
                    <td>：</td>
                    <td>{{ orderInfo.deliver_date.split(" ")[0] }}</td>
                    <td></td>
                    <td>交易幣別</td>
                    <td>：</td>
                    <td>{{ orderInfo.money_type }}</td>
                  </tr>
                </table>
              </div>
            </div>
            <!-- 報價信息 -->
            <div class="quoteInfo">
              <div class="qu_title">
                <span>報價信息</span>
              </div>
              <table class="tbFir">
                <tr>
                  <td>總報價</td>
                  <td>：</td>
                  <td>{{ total_price * orderInfo.amount }}</td>
                </tr>
                <tr>
                  <td>清單（單套）總價</td>
                  <td>：</td>
                  <td>{{ total_price }}</td>
                </tr>
                <tr>
                  <td>交貨時間</td>
                  <td>：</td>
                  <td>{{ deliver_date.split(" ")[0] }}</td>
                </tr>
              </table>

              <div class="qu_content">
                <table class="tbSec" id="tbSec">
                  <tr>
                    <th>序號</th>
                    <th>零件名（成套名）</th>
                    <th>數量</th>
                    <th>單價</th>
                    <th>金額</th>
                  </tr>
                  <tr
                    v-for="(item, index) in slav_list"
                    :key="item.part_doc_file_pkid"
                  >
                    <td>{{ index + 1 }}</td>
                    <td>{{ item.part_name.split(".")[0] }}</td>
                    <td>{{ item.part_amunt }}</td>
                    <td>{{ item.part_unit_price }}</td>
                    <td>{{ item.part_price_sum }}</td>
                  </tr>
                </table>
              </div>
              <span class="sign" id="sign">權限主管確認核准：</span>
            </div>
          </div>
          <!-- 第二塊區域 -->
          <div class="bid-mainSec" id="bid-mainSec">
            <!-- 注釋 -->
            <div class="annotation" id="annotation">
              <div class="an_title">
                <span>注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;釋</span>
              </div>
              <div class="an_content">
                <p>
                  <span>1.</span
                  >可單個零件報價，若是成套模治檢具零件數量巨大，可採用成套模治檢具報價。
                </p>
                <p>
                  <span>2.</span
                  >模治檢具加工涉及到輔助材料/組裝費/運費等不能歸屬的，可在零件名中填其它，報出金額即可。
                </p>
                <p>
                  <span>3.</span
                  >報價需接單方權限主管核准后視為加工協議達成，接單方依系統訂單標書按要求加工、按期按質交貨等。
                </p>
                <p><span>4.</span>報價單報價均為未稅價。</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <div style="background-color:#12222E">
      <div style="background-color:#12222E" class="type-area">
        <el-button
          style="margin-left:20px;"
          type="primary"
          @click="$getPdf('pdfDom', htmlTitle)"
          >生成PDF</el-button
        >
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Top from "@/components/Top.vue";
import Logo from "@/components/Logo.vue";
import Footer from "@/components/Footer.vue";

import { mapState } from "vuex";
import { query_bill_by_pkid, query_give_price_by_bill_pkid } from "@/api/order";

export default {
  data() {
    return {
      orderInfo: {},
      slav_list: [],
      pkid: "",
      recvInfo: {},
      deliver_date: "",
      loaded: "false",
      // 打印標題
      htmlTitle: "模治檢具標書"
    };
  },
  methods: {
    getInfo(loaded) {
      if (loaded == "false") {
        let data = JSON.parse(sessionStorage.getItem("biddinfdDoc"));
        this.orderInfo = data.orderInfo;
        this.slav_list = data.slav_list;
        let time = data.deliver_date;
        let timeS = new Date(time);

        //格式化日期
        Date.prototype.Format = function(fmt) {
          var o = {
            "y+": this.getFullYear(),
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S+": this.getMilliseconds() //毫秒
          };
          for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
              if (k == "y+") {
                fmt = fmt.replace(
                  RegExp.$1,
                  ("" + o[k]).substr(4 - RegExp.$1.length)
                );
              } else if (k == "S+") {
                var lens = RegExp.$1.length;
                lens = lens == 1 ? 3 : lens;
                fmt = fmt.replace(
                  RegExp.$1,
                  ("00" + o[k]).substr(("" + o[k]).length - 1, lens)
                );
              } else {
                fmt = fmt.replace(
                  RegExp.$1,
                  RegExp.$1.length == 1
                    ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length)
                );
              }
            }
          }
          return fmt;
        };

        this.deliver_date = timeS.Format("yyyy-MM-dd");
        this.getRecvInfo();
        return;
      }

      query_bill_by_pkid(this.pkid).then(res => {
        if (res.code === "1") {
          this.orderInfo = res.t;
          this.getRecvInfo();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 獲取接單方信息
    getRecvInfo() {
      if (this.userInfo.send_recv_type === "recv") {
        this.recvInfo = this.userInfo;
        this.getSlavList();
      } else {
        const recvPkid = JSON.parse(sessionStorage.getItem("recvPkid"));
        for (let item of this.orderInfo.give_recv_user_list) {
          if (recvPkid == item.pkid) {
            this.recvInfo = item;
          }
        }
        this.deliver_date = JSON.parse(sessionStorage.getItem("deliverDate"));
        this.slav_list = JSON.parse(sessionStorage.getItem("slavList"));
      }
    },

    // 獲取清單信息
    getSlavList() {
      if (this.loaded == "false") return;
      query_give_price_by_bill_pkid(this.orderInfo.pkid).then(res => {
        if (res.code === "1") {
          this.deliver_date = res.t.deliver_date;
          for (let item of res.t.slav_list) {
            const obj = {
              id: item.pkid,
              part_name: item.part_name,
              part_amunt: item.part_amunt,
              part_unit_price: item.part_unit_price,
              part_price_sum: item.part_price_sum
            };
            this.slav_list.push(obj);
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 添加空元素佔位置
    addElement(item, addHeight, ele) {
      let parentElement,
        targetElement = item,
        newElement;

      if (ele == "anno") {
        parentElement = document.querySelector("#bid-mainSec");
        newElement = document.createElement("div");
        newElement.className = "momentSinEle";
      } else if (ele == "sign") {
        parentElement = document.querySelector(".quoteInfo");
        newElement = document.createElement("div");
        newElement.className = "momentSinEle";
      } else {
        parentElement = document.querySelector("#tbSec");
        newElement = document.createElement("tr");
        newElement.className = "momentEle";
      }
      newElement.style.height = addHeight + "px";
      parentElement.insertBefore(newElement, targetElement);
    },
    // 刪除空元素
    delElement() {
      let list = document.querySelectorAll(".momentEle");
      let parentElement = document.querySelector("#tbSec");
      for (let item of list) {
        parentElement.removeChild(item);
      }

      let listS = document.querySelectorAll(".momentSinEle");
      for (let itemS of listS) {
        itemS.parentNode.removeChild(itemS);
      }
    }
  },
  created() {
    const loaded = this.$router.history.current.query.loaded;
    this.loaded = loaded;
    this.pkid = this.$router.history.current.query.pkid;
    this.getInfo(loaded);
  },
  computed: {
    ...mapState({
      userInfo: state => state.userInfo
    }),
    total_price() {
      var price = 0;
      for (let item of this.slav_list) {
        price += item.part_price_sum;
      }
      return price;
    }
  },
  components: {
    Top,
    Logo,
    Footer
  }
};
</script>

<style lang="less" scoped>
#biddingDoc {
}
.bidContent {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  background-size: 120%;
  position: relative;
  padding-top: 40px;
  .type-area {
    > div {
      box-shadow: border-box;
      padding: 20px;
      background-color: rgba(13, 28, 42, 0.2);
    }
  }
}
// 標題
.panel-title {
  display: flex;
  justify-content: space-between;
  padding-bottom: 20px;
  user-select: none;
  // 左右線條
  .left-line,
  .right-line {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .theLine {
    width: 460px;
    height: 1px;
    background: #96aabb; //rgba(150,170,187,1);
  }
  .theBlock {
    width: 10px;
    height: 10px;
    border: 1px solid #96aabb; //rgba(150,170,187,1);
    transform: rotate(45deg);
  }
  // 中間文本
  .center-text {
    font-size: 24px;
    color: #fff;
  }
}
// 主體內容
.bid-main {
  box-sizing: border-box;
  padding: 32px 50px;
  background: #d3dfe7;
}
.pan_info_Top {
  display: flex;
  justify-content: space-between;
}
// info公共樣式
.ba_title,
.or_title,
.qu_title,
.an_title {
  user-select: none;
  position: relative;
  width: 100px;
  height: 35px;
  background: url(../../assets/imgs/particulars/infoTitleBG.png) no-repeat;
  span {
    color: #fff;
    font-size: 16px;
    position: absolute;
    left: 14%;
    top: 32%;
  }
}
// 【基本信息】
.baseInfo,
.orderInfo {
  table {
    margin-top: 18px;
    td {
      height: 24px;
      &:nth-child(1) {
        font-size: 16px;
        color: #626f7f;
        // 文本居中
        // text-align:center;
        text-align: justify;
        // text-justify:distribute-all-lines;
        text-align-last: justify;
      }
      &:nth-child(2),
      &:nth-child(6) {
        color: #626f7f;
      }
      &:nth-child(4) {
        width: 6px;
      }
      &:nth-child(5) {
        font-size: 16px;
        color: #626f7f;
        // 文本居中
        text-align: justify;
        text-align-last: justify;
      }
    }
  }
}
.orderInfo {
  margin-right: 40px;
}
// 【報價信息】
.quoteInfo {
  margin-top: 40px;
  .qu_content {
    margin-top: 30px;
    box-sizing: border-box;
    padding: 20px;
    border: 1px solid #c0c8cf;
  }
  .tbFir {
    margin-top: 18px;
    td {
      height: 24px;
      &:nth-child(1) {
        font-size: 16px;
        color: #626f7f;
        // 文本居中
        text-align: justify;
        text-align-last: justify;
      }
      &:nth-child(2) {
        color: #626f7f;
      }
    }
  }
  .tbSec {
    width: 100%;
    text-align: center;
    border-collapse: collapse;
    tr {
      word-break: break-all;
      page-break-before: always;
      page-break-after: always;
      page-break-inside: avoid;
      &:nth-child(odd) {
        background-color: #c1d2de;
      }
      &:nth-child(even) {
        background-color: #d3dfe7;
      }
      &:nth-child(1) {
        background-color: #a6bed0;
      }
    }
    th {
      height: 45px;
      font-weight: 400;
    }
    td {
      height: 45px;
    }
  }
  .sign {
    user-select: none;
    display: inline-block;
    height: 120px;
    line-height: 120px;
  }
}
// 第二塊區域
.bid-mainSec {
  margin-top: 20px;
  box-sizing: border-box;
  padding: 32px 50px 32px 50px;
  background: #d3dfe7;
}
.annotation {
  .an_content {
    margin-top: 20px;
  }
}
</style>
