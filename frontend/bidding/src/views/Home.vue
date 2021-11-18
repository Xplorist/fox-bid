<template>
  <div class="home">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 左上角tag -->
      <div class="con-tag type-area">
        <!-- <ul>
          <li>交易總額: ￥95,371,863元</li>
          <li>需求總量: 3,537個</li>
          <li>競標單位: 26個</li>
        </ul>-->
      </div>
      <!-- 主體內容：模治檢具任務需求 -->
      <div class="con-req type-area">
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
              <option value="end">已結束</option>
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
          <div class="list-single" v-for="(item, index) in listData" :key="index">
            <div class="single-left">
              <!-- 類型 -->
              <div class="sin_l_pur">
                <div>{{item.purpose}}</div>
              </div>
              <div class="sin_l_info">
                <div class="info-top">
                  <div class="formNum">需求單號{{item.formNum}}</div>
                  <div class="state">
                    <div class="bidding">{{item.bill_status_text}}</div>
                    <div class>{{item.deliver_address}}</div>
                    <div class>{{item.bid_range}}</div>
                  </div>
                </div>
                <div class="info-bottom">
                  <div class="price">
                    <div class="kind">{{item.money_type == "RMB" ? "￥" : "$"}}</div>
                    <div class="num">{{item.price}}</div>
                  </div>
                  <div class="demand">
                    <div>需求量:</div>
                    <div class="num">{{item.demand}}</div>
                  </div>
                  <div class="unitNum">{{item.unitNum ? item.unitNum : 0}}個單位已參與</div>
                </div>
              </div>
            </div>
            <div class="single-right">
              <div class="sin_r_left">
                <div class="sin_r_l_dep">
                  <div class="icon"></div>
                  <div class="text">{{item.dept_name}}</div>
                </div>
                <div class="sin_r_l_time">
                  <div>交貨時間:</div>
                  <div class="num">{{item.deliver_date.split(' ')[0]}}</div>
                </div>
              </div>
              <div class="sin_r_right">
                <div>
                  <router-link :to="'/particulars?pkid='+item.pkid">查看/參與</router-link>
                </div>
              </div>
            </div>
          </div>
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
    <section id="fixBox">
      <!-- 頭像 -->
      <!-- <el-image style="width: 100px; height: 100px" src="../assets/imgs/index/portrait.png" fit="cover"></el-image> -->
      <img
        v-if="porImgUrl"
        style="width: 100px; height: 100px; object-fit:cover;"
        :src="porImgUrl"
        alt
      />
      <!-- 功能 -->
      <div class="liFunc" v-if="porImgUrl">
        <div class="singleFunc collect">
          <div class="funcIcon"></div>
          <div class="funcText">收藏</div>
        </div>
        <div class="singleFunc history">
          <div class="funcIcon"></div>
          <div class="funcText">足跡</div>
        </div>
        <div class="singleFunc news">
          <div class="funcIcon"></div>
          <div class="funcText">消息</div>
          <div class="funcNum"></div>
        </div>
        <div class="singleFunc order">
          <div class="funcIcon"></div>
          <div class="funcText">我的訂單</div>
        </div>
      </div>
      <!-- 返回頂端 -->
      <div class="backTop" v-show="backFlag" @click="backTop()">
        <div class="backTop-icon"></div>
        <div class="backTop-text">返回頂端</div>
      </div>
    </section>
  </div>
</template>

<script>
import Top from "@/components/Top.vue";
import Logo from "@/components/Logo.vue";
import Footer from "@/components/Footer.vue";
import Paging from "@/components/paging/Paging.vue";

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
      backFlag: false
    };
  },
  components: {
    Top,
    Logo,
    Footer,
    Paging
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
    // // 修改條件後重新獲取數據
    // changeData() {
    //   let arr = [];
    //   for (let i in this.originData) {
    //     arr.push(this.originData[i]);
    //   }
    //   // 類型 篩選
    //   for (let i = 0; i < arr.length; i++) {
    //     if (this.filterCondition.purpose == "all") {
    //       continue;
    //     } else if (arr[i].purpose != this.filterCondition.purpose) {
    //       arr.splice(i, 1);
    //       i--;
    //     }
    //   }
    //   // 時間 篩選
    //   for (let i = 0; i < arr.length; i++) {
    //     switch (this.filterCondition.time) {
    //       case "all":
    //         break;
    //       case "0-5":
    //         if (arr[i].reTime > 5) {
    //           arr.splice(i, 1);
    //           i--;
    //         }
    //         break;
    //       case "5-10":
    //         if (arr[i].reTime <= 5 || arr[i].reTime > 10) {
    //           arr.splice(i, 1);
    //           i--;
    //         }
    //         break;
    //       case "more":
    //         if (arr[i].reTime <= 10) {
    //           arr.splice(i, 1);
    //           i--;
    //         }
    //         break;
    //       default:
    //         break;
    //     }
    //   }
    //   this.allListData = arr;
    //   this.getPage();
    // },
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
        switch (data[i].bill_status) {
          case "0":
            bill_status_text = "待發佈";
            break;
          case "1":
            bill_status_text = "待報價";
            break;
          case "2":
            bill_status_text = "待發貨";
            break;
          case "3":
            bill_status_text = "待收貨";
            break;
          case "4":
            bill_status_text = "待付款";
            break;
          case "5":
            bill_status_text = "待收款";
            break;
          case "6":
            bill_status_text = "已完成";
            break;
          default:
            break;
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
@import "../assets/css/views/home.less";
</style>