<template>
  <div class="home">
    <Top></Top>
    <Logo></Logo>
    <section class="home-content">
      <!-- 左上角tag -->
      <div class="con-tag type-area">
        <OverView></OverView>
      </div>
      <div class="con-list type-area">
        <RankingList></RankingList>
      </div>
      <!-- 主體內容：模治檢具任務需求 -->
      <div class="con-body type-area">
        <p class="statement">
          声明：本平臺訂單是公開的，對於仿形治具、新增功能性治具等產品客戶有特殊保密性要求的模治檢具的相關訂單，建議小範圍內開標或者線下交易。
        </p>

        <!-- 標題 -->
        <div class="panel-title">
          <!-- 左線條 -->
          <div class="left-line">
            <div class="theLine"></div>
            <div class="theBlock"></div>
          </div>
          <!-- 標題 -->
          <div class="center-text">模治檢具訂單</div>
          <!-- 右線條 -->
          <div class="right-line">
            <div class="theBlock"></div>
            <div class="theLine"></div>
          </div>
        </div>
        <!-- 篩選器 filter -->
        <div class="panel-filter">
          <div class="fil-purpose">
            <div>類型:</div>
            <select v-model="filterCondition.purpose">
              <option value="all">全部</option>
              <option
                v-for="item in typeList"
                :key="item.pkid"
                :label="item.name"
                :value="item.name"
              ></option>
            </select>
          </div>
          <div class="fil-scope">
            <div>開標範圍:</div>
            <select v-model="filterCondition.scope">
              <option
                v-for="item in rangeList"
                :key="item.pkid"
                :label="item.name"
                :value="item.name"
              ></option>
            </select>
          </div>
          <div class="fil-state">
            <div>狀態:</div>
            <select v-model="filterCondition.state">
              <option value="all">全部</option>
              <option value="ing">競標中</option>
              <option value="end">競標完成</option>
            </select>
          </div>
          <div class="fil-site">
            <div>交貨地點:</div>
            <select v-model="filterCondition.site">
              <option value="all">全部</option>
              <option
                v-for="item in addressList"
                :key="item.pkid"
                :label="item.name"
                :value="item.name"
              ></option>
            </select>
          </div>
          <div class="fil-time">
            <div>交貨時間:</div>
            <select v-model="filterCondition.time">
              <option value="all">默認</option>
              <option value="0_5">0-5天</option>
              <option value="5_10">5-10天</option>
              <option value="10more">10天以上</option>
            </select>
          </div>
        </div>
        <!-- 列表 -->
        <div class="panel-list">
          <div
            class="list-single"
            v-for="(item, index) in listData"
            :key="index"
          >
            <div class="single-left">
              <!-- 類型 -->
              <div class="scope">
                <div>{{ item.purpose }}</div>
              </div>
              <div class="detail">
                <div class="info-top">
                  <div class="formNum">需求單號{{ item.formNum }}</div>
                  <div class="state">
                    <div class="bidding">{{ item.bill_status_text }}</div>
                    <div class>{{ item.deliver_address }}</div>
                    <div class>{{ item.bid_range }}</div>
                  </div>
                </div>
                <div class="info-bottom">
                  <div class="price">
                    <div class="kind">
                      {{ item.money_type == "RMB" ? "￥" : "$" }}
                    </div>
                    <div class="num">{{ item.price }}</div>
                  </div>
                  <div class="demand">
                    <div>需求量:</div>
                    <div class="num">{{ item.demand }}</div>
                  </div>
                  <div class="unitNum">
                    {{ item.unitNum ? item.unitNum : 0 }}個單位已參與
                  </div>
                </div>
              </div>
            </div>
            <div class="single-right">
              <div class="sin-r-left">
                <div class="dept-name">
                  <div class="icon"></div>
                  <div>{{ item.dept_name }}</div>
                </div>
                <div class="dev-time">
                  <div>交貨時間:</div>
                  <div class="num">{{ item.deliver_date.split(" ")[0] }}</div>
                </div>
              </div>
              <div class="btn-info-box">
                <router-link
                  target="_blank"
                  :to="'/particulars?pkid=' + item.pkid"
                  :style="{
                    backgroundColor:
                      item.bill_status > 1 ? '#567082' : '#2e6e9e'
                  }"
                  >{{
                    item.bill_status > 1 ? "查看" : "查看/參與"
                  }}</router-link
                >
              </div>
            </div>
          </div>
          <p v-if="listData.length == 0" class="z-text-center no-data">
            暫無 標單
          </p>
        </div>
        <!-- 分页 -->
        <div class="paging" style="backgroundColor:#D3DFE7">
          <Paging
            :total="total"
            :current-page="currentPage"
            :page-size="pageSize"
            @getCurrentPage="getListDate"
          ></Paging>
        </div>
      </div>
    </section>
    <Footer></Footer>
    <section>
      <Fixbox :backFlag="backFlag" @backTop="backTop()"></Fixbox>
    </section>
  </div>
</template>

<script>
import Top from "@/components/Top.vue";
import Logo from "@/components/Logo.vue";
import Footer from "@/components/Footer.vue";
import Paging from "@/components/paging/Paging.vue";
import Fixbox from "./modules/Fixbox";
import RankingList from "./modules/RankingList";
import OverView from "./modules/OverView";

import { mapState } from "vuex";
import {
  query_bill_list_not_login,
  query_bill_list_recv_main
} from "@/api/order";
import {
  query_deliver_address_list,
  query_bid_range_list,
  query_pd_type_list
} from "@/api/formInfo";

let statusContrast = [
  { num: "-2", val: "已棄標" },
  { num: "-1", val: "已流標" },
  { num: "0", val: "待發佈" },
  { num: "1", val: "待報價" },
  { num: "2", val: "待發貨" },
  { num: "3", val: "待收貨" },
  { num: "4", val: "待付款" },
  { num: "5", val: "待收款" },
  { num: "6", val: "已完成" }
];
export default {
  name: "home",
  data() {
    return {
      // 每一頁展示的數據
      listData: [],
      // 總數據長度
      total: null,
      // 當前頁數
      currentPage: 1,
      // 每一頁數據長度
      pageSize: 8,
      // 篩選條件
      filterCondition: {
        purpose: "all",
        scope: "集團",
        state: "ing",
        site: "all",
        time: "all"
      },
      // 類型
      typeList: [],
      // 目標範圍
      rangeList: [],
      // 交貨地點
      addressList: [],
      // 返回顯示
      backFlag: false,
      // 狀態對照
      statusContrast
      // // 距离顶部距离
      // announceTop: 0,
    };
  },
  components: {
    Top,
    Logo,
    Footer,
    Paging,
    Fixbox,
    RankingList,
    OverView
  },
  methods: {
    // 處理滾動
    handleScroll() {
      if (window.scrollY > 400) {
        this.backFlag = true;
      } else {
        this.backFlag = false;
      }
    },
    
    // 返回頂端
    backTop() {
      if (document.documentElement.scrollTop > 0) {
        if (document.documentElement.scrollTop > 30) {
          var interval = setInterval(function() {
            document.documentElement.scrollTop -= 10;
            if (document.documentElement.scrollTop < 30) {
              document.documentElement.scrollTop = 0;
              clearInterval(interval);
            }
          }, 5);
        } else {
          document.documentElement.scrollTop = 0;
        }
      }
    },

    // 獲取筛选信息
    query_classify() {
      query_pd_type_list().then(res => {
        if (res.code === "1") {
          this.typeList = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });

      query_bid_range_list().then(res => {
        if (res.code === "1") {
          this.rangeList = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });

      query_deliver_address_list().then(res => {
        if (res.code === "1") {
          this.addressList = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 從後台查詢數據信息
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("homeCurrentPage", this.currentPage);
      var data = {
        pd_type: this.filterCondition.purpose,
        bid_range: this.filterCondition.scope,
        bill_status: this.filterCondition.state,
        deliver_address: this.filterCondition.site,
        deliver_date: this.filterCondition.time,
        pageIndex: this.currentPage,
        pageSize: this.pageSize
      };
      if (this.userInfo && this.userInfo.send_recv_type === "recv") {
        // 接單方
        query_bill_list_recv_main(data).then(res => {
          if (res.code === "1") {
            this.total = res.t.row_total;
            var result = res.t.bill_list;
            this.changeListData(result);
          } else {
            this.$message.error(res.msg);
          }
        });
      } else {
        // 未登錄
        query_bill_list_not_login(data).then(res => {
          if (res.code === "1") {
            this.total = res.t.row_total;
            var result = res.t.bill_list;
            this.changeListData(result);
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    // 修改展示數據信息
    changeListData(data) {
      // 先清空原始數據
      this.listData = [];
      for (let i in data) {
        var bill_status_text = "";
        for (let item of statusContrast) {
          if (item.num === data[i].bill_status) {
            bill_status_text = item.val;
          }
        }

        var obj = {
          // 唯一id
          pkid: data[i].pkid,
          // 訂單需求類別
          purpose: data[i].pd_type,
          // 訂單號
          formNum: data[i].bill_no,
          // 訂單狀態
          bill_status: data[i].bill_status,
          // 訂單狀態文字
          bill_status_text: bill_status_text,
          // 交貨地址
          deliver_address: data[i].deliver_address,
          // 目標範圍
          bid_range: data[i].bid_range,
          // 價值
          price: data[i].total_price,
          // 幣別
          money_type: data[i].money_type,
          // 需求數量
          demand: data[i].amount,
          // 參與單位數量
          unitNum: data[i].recv_user_num,
          // 發佈單位名稱
          dept_name: data[i].send_user ? data[i].send_user.dept_name : "",
          // 交貨時間
          deliver_date: data[i].deliver_date.split("T")[0]
        };
        this.listData.push(obj);
      }
    }
  },
  created() {
    let page = Number(sessionStorage.getItem("homeCurrentPage"));
    page = page ? page : 1;
    this.getListDate(page);

    this.query_classify();
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll, true);
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  computed: {
    ...mapState({
      porImgUrl: state => state.porImgUrl,
      userInfo: state => state.userInfo
    })
  },
  watch: {
    filterCondition: {
      handler: function() {
        this.getListDate();
      },
      deep: true
    }
  }
};
</script>

<style lang="less" scoped>
.home {
  min-height: 100vh;
  .home-content {
    background: #12222e url("../../assets/imgs/index/contentBG1.png") no-repeat
      top center / contain;
    padding-top: 10px;
    box-sizing: border-box;
    min-height: calc(100vh - 270px);
  }
  // 頂部小標籤信息
  .con-tag {
    display: flex;
    justify-content: flex-start;
    color: #d7efff;
    font-size: 14px;
  }
  .con-body {
    background: rgba(13, 28, 42, 0.4);
    margin-top: 60px;
    border: 1px solid rgba(0, 0, 0, 0);
    box-sizing: border-box;
    padding: 20px;
    position: relative;
  }
  // 聲明
  .statement {
    position: absolute;
    top: -50px;
    left: 0;
    color: #223543;
    width: 100%;
    text-align: center;
    color: rgba(215, 239, 255, 0.8);
    height: 24px;
    line-height: 24px;
    background-color: rgba(0, 0, 0, 0.1);
    font-size: 14px;
  }
  // 標題
  .panel-title {
    display: flex;
    justify-content: space-between;
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
  // 篩選器
  .panel-filter {
    display: flex;
    color: #d7efff;
    font-size: 14px;
    margin-top: 30px;
    user-select: none;
    > div {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-right: 50px;
    }
    > div select {
      height: 24px;
      margin-left: 6px;
      border: 0;
      background-color: rgb(13, 28, 42);
      color: #d7efff;
      &:focus {
        outline: 1px solid #d7efff;
      }
    }
  }
  // 列表
  .panel-list {
    margin-top: 16px;
    background: #d3dfe7;
    min-height: 100px;
  }
  .panel-list .list-single {
    padding: 25px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #c0c8cf;
    transition-duration: 0.3s;
    &:hover {
      background-color: #e1e9ef;
    }
  }
  // list 左側
  .panel-list .single-left {
    display: flex;
    justify-content: space-between;
    width: 60%;
    // 分類的背景效果
    .scope {
      background: url("../../assets/imgs/index/listScopeBG.png") no-repeat;
      width: 70px;
      height: 70px;
      display: flex;
      justify-content: center;
      align-items: center;
      margin-right: 5px;
      user-select: none;
    }
    // 具體信息
    .detail {
      flex: 1;
      .info-top {
        display: flex;
        align-items: center;
      }
      .info-bottom {
        display: flex;
        align-items: flex-end;
        margin-top: 0.5rem;
      }
    }
    // 編號
    .formNum {
      color: #212f3a;
      font-size: 16px;
      margin-right: 10px;
    }
    // 狀態
    .state {
      display: flex;
      user-select: none;
      div {
        min-width: 50px;
        padding: 0 10px;
        height: 26px;
        line-height: 26px;
        text-align: center;
        margin-right: 8px;
        color: #fff;
        font-size: 14px;
        background-color: #9ab0c3;
      }
      div.bidding {
        background-color: #2e6e9e;
      }
    }
    // 價格
    .price {
      color: #0093ff;
      font-size: 30px;
      display: flex;
      align-items: center;
      margin-right: 10px;
      .kind {
        align-self: flex-end;
        font-size: 18px;
      }
    }
    // 需求量
    .demand {
      display: flex;
      color: #212f3a;
      font-size: 14px;
      margin-right: 35px;
      .num {
        color: #0093ff;
        font-size: 16px;
        margin-left: 8px;
      }
    }
    // 參與單位量
    .unitNum {
      color: #47515b;
      font-size: 16px;
    }
  }
  // list 右側
  .panel-list .single-right {
    width: 35%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .dept-name {
      display: flex;
      align-items: center;
      min-width: 150px;
      color: #0096ff;
      font-size: 14px;
      .icon {
        width: 16px;
        height: 15px;
        background: url("../../assets/imgs/index/plane.png") no-repeat;
      }
    }
    .dev-time {
      display: flex;
      color: #212f3a;
      font-size: 16px;
      margin-top: 18px;
      .num {
        margin-left: 5px;
        color: #2e6e9e;
        font-size: 14px;
      }
    }
    .btn-info-box a {
      display: inline-block;
      padding: 8px 0;
      width: 80px;
      border-radius: 2px;
      font-size: 14px;
      text-align: center;
      color: #fff;
      background-color: #2e6e9e;
      user-select: none;
      cursor: pointer;
    }
  }
  // 無數據
  .panel-list .no-data {
    padding: 2rem 0;
    box-sizing: border-box;
    color: #969696;
  }
}
</style>
