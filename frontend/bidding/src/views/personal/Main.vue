<template>
  <div class="main">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area">
        <SilderBar :choose-item="silderBarItem"></SilderBar>
        <div class="mainPart">
          <!-- 我的收入 -->
          <!-- <div class="income">
            <div class="title">我的收入</div>
            <div class="details">
              <div class="single">
                <div class="singleTit">累計收益</div>
                <div class="singleCon">5,264,800</div>
              </div>
              <div class="single">
                <div class="singleTit">製作中資產(元)</div>
                <div class="singleCon">193,500</div>
              </div>
              <div class="single">
                <div class="singleTit">參與項目</div>
                <div class="singleCon">
                  185
                  <span>個</span>
                </div>
              </div>
              <div class="single">
                <div class="singleTit">中標項目</div>
                <div class="singleCon">
                  27
                  <span>個</span>
                </div>
              </div>
              <div class="single">
                <el-progress
                  type="circle"
                  :percentage="18"
                  :stroke-width="progress.strokeWidth"
                  :width="progress.width"
                  :color="progress.color"
                ></el-progress>
              </div>
            </div>
          </div>-->
          <!-- 分割線 -->
          <!-- <div class="splitLine"></div> -->
          <!-- 接單管理 -->
          <div class="manage">
            <div class="title">
              <div class="tit_icon"></div>
              <div>接單管理</div>
            </div>
            <!-- 篩選 -->
            <el-radio-group v-model="classify">
              <el-radio
                v-for="(item) in classifyList"
                :key="item.id"
                :label="item.ename"
                @click="classify = item.ename"
              >
                {{item.name}}
                <span v-show="item.num">({{item.num}})</span>
              </el-radio>
            </el-radio-group>
            <div class="list">
              <!-- listStar -->
              <div
                class="list_single"
                v-for="(item, index) in orderList"
                :class="{winBidding: item.winBidding === 's'}"
                :key="index"
              >
                <div class="list_sin_left">
                  <div class="sin_left_scope">{{item.scope}}</div>
                  <div class="sin_left_info">
                    <div class="left_info_item">
                      <div
                        class="formNum"
                        @click="$router.push('/particulars?pkid='+item.pkid)"
                      >{{item.formNum}}</div>
                      <div class="state">
                        <div v-for="(itemS, index) in item.state" :key="index">{{itemS}}</div>
                      </div>
                    </div>
                    <div class="left_info_item">
                      <div class="price">
                        <div class="kind">{{item.price[0]}}</div>
                        <div class="num">{{item.price[1]}}</div>
                      </div>
                      <div class="unitNum">{{item.unitNum}}個單位已參與</div>
                    </div>
                  </div>
                </div>
                <div class="list_sin_right">
                  <div class="sin_right_left">
                    <div class="current">
                      <div v-if="item.winBidding === 'f'" class="failed">競標失敗</div>
                      <div
                        v-else
                        class="remainTime"
                      >{{item.status.class=="finished" ? '':'交貨日期：'+item.deliver_date.split(' ')[0]}}</div>
                    </div>
                  </div>
                  <div class="sin_right_right" :class="item.status.class">
                    <div class="status">{{item.status.text}}</div>
                    <div class="btn">
                      <router-link :to="item.href +'?pkid='+ item.pkid">{{item.status.btnText}}</router-link>
                    </div>
                    <!-- <div class="showDoc">查看标书</div> -->
                  </div>
                </div>
              </div>
              <!-- listEnd -->
            </div>

            <!-- 分頁 -->
            <div class="paging">
              <Paging
                :total="total"
                :current-page="currentPage"
                :page-size="pageSize"
                @getCurrentPage="getListDate"
              ></Paging>
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
import Paging from "@/components/paging/Paging";
import SilderBar from "@/components/personal/SilderBar";

import {
  query_status_num_recv_user,
  query_bill_list_recv_user
} from "@/api/order";

import { mapState } from "vuex";

export default {
  data: function() {
    return {
      // 環形進度條
      progress: {
        strokeWidth: 12,
        width: 100,
        color: "#0096FF"
      },
      // 選中的訂單狀態
      classify: "all",
      classifyList: [
        // {
        //   interfaceName: "num_status_-1",
        //   id: "-1",
        //   ename: "-1",
        //   name: "已流標",
        //   num: "0"
        // },
        {
          interfaceName: "num_status_all",
          id: "0",
          ename: "all",
          name: "全部",
          num: "0"
        },
        {
          interfaceName: "num_status_1",
          id: "1",
          ename: "1",
          name: "競價中",
          num: "0"
        },
        {
          interfaceName: "num_status_2",
          id: "2",
          ename: "2",
          name: "待發貨",
          num: "0"
        },
        {
          interfaceName: "num_status_3",
          id: "3",
          ename: "3",
          name: "待收貨",
          num: "0"
        },
        {
          interfaceName: "num_status_4",
          id: "4",
          ename: "4",
          name: "待付款",
          num: "0"
        },
        {
          interfaceName: "num_status_5",
          id: "5",
          ename: "5",
          name: "待收款",
          num: "0"
        },
        {
          interfaceName: "num_status_6",
          id: "6",
          ename: "6",
          name: "已完成",
          num: "0"
        },
        {
          interfaceName: "num_status_n2",
          id: "-2",
          ename: "-2",
          name: "已棄標",
          num: "0"
        }
      ],
      // 單頁顯示的數據
      orderList: [],
      // 數據總數目
      total: null,
      // 單頁數據數目
      pageSize: 6,
      // 當前頁數
      currentPage: null,
      // 侧边栏选中条目
      silderBarItem: "order",
      initPage: 1
    };
  },
  methods: {
    // 更改href
    setHref() {
      for (let i in this.orderList) {
        switch (this.orderList[i].status.class) {
          case "abandon":
          case "quote":
          case "payment":
          case "takeDel":
          case "finished":
          case "failed":
            this.orderList[i].href = "particulars";
            break;
          case "deliver":
            this.orderList[i].href = "personal/delivery";
            break;
          case "gather":
            this.orderList[i].href = "personal/gather";
            break;
          case "evaluation":
            this.orderList[i].href = "personal/postEvaluation";
            break;
        }
      }
    },

    // 獲取訂單狀態數量
    getListClassifyNum() {
      query_status_num_recv_user().then(res => {
        if (res.code === "1") {
          for (let i in this.classifyList) {
            // 對應賦值
            switch (this.classifyList[i].interfaceName) {
              case "num_status_n2":
                this.classifyList[i].num = res.t.num_status_n2;
                break;
              case "num_status_all":
                this.classifyList[i].num = res.t.num_status_all;
                break;
              case "num_status_1":
                this.classifyList[i].num = res.t.num_status_1;
                break;
              case "num_status_2":
                this.classifyList[i].num = res.t.num_status_2;
                break;
              case "num_status_3":
                this.classifyList[i].num = res.t.num_status_3;
                break;
              case "num_status_4":
                this.classifyList[i].num = res.t.num_status_4;
                break;
              case "num_status_5":
                this.classifyList[i].num = res.t.num_status_5;
                break;
              case "num_status_6":
                this.classifyList[i].num = res.t.num_status_6;
                break;
              case "num_no_recv_eval":
                this.classifyList[i].num = res.t.num_no_recv_eval;
                break;
              default:
                break;
            }
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 向後端發起請求獲取數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("personalMainCurrentPage", this.currentPage);
      query_bill_list_recv_user(
        this.classify,
        this.currentPage,
        this.pageSize
      ).then(res => {
        if (res.code === "1") {
          // 清空原有數據 獲取總數據長度
          this.orderList = [];
          this.total = res.t.row_total;

          const listData = res.t.bill_list;
          this.changeListData(listData);
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 修改展示列表數據
    changeListData(listData) {
      for (let i in listData) {
        // 右側按鈕所需屬性
        var status = {
          class: "",
          text: "",
          btnText: ""
        };
        // 判斷是否中標 f:fail s:success w:wait
        var winBidding = "f";
        if (listData[i].recv_user_pkid) {
          winBidding =
            listData[i].recv_user_pkid == this.userInfo.pkid ? "s" : "f";
        } else {
          winBidding = "w";
        }
        if (winBidding === "f") {
          (status.class = "failed"),
            (status.text = ""),
            (status.btnText = "詳情");
        } else {
          switch (listData[i].bill_status) {
             case "-2":
              status.class = "abandon";
              status.text = "已棄標";
              status.btnText = "詳情";
              break;
            case "1":
              status.class = "quote";
              status.text = "競價中";
              status.btnText = "詳情";
              break;
            case "2":
              status.class = "deliver";
              status.text = "待發貨";
              status.btnText = "發貨";
              break;
            case "3":
              status.class = "takeDel";
              status.text = "待收貨";
              status.btnText = "詳情";
              break;
            case "4":
              status.class = "payment";
              status.text = "待付款";
              status.btnText = "詳情";
              break;
            case "5":
              status.class = "gather";
              status.text = "待收款";
              status.btnText = "收款";
              break;
            case "6":
              if (listData[i].f_recv_eval === "N") {
                status.class = "evaluation";
                status.text = "待評價";
                status.btnText = "評價";
                break;
              }
              status.class = "finished";
              status.text = "已完成";
              status.btnText = "詳情";
              break;
            default:
              break;
          }
        }
        // 單條數據
        const single = {
          pkid: listData[i].pkid,
          scope: listData[i].pd_type,
          formNum: "需求單號" + listData[i].bill_no,
          state: [listData[i].deliver_address, listData[i].bid_range],
          price: [
            listData[i].money_type == "RMB" ? "￥" : "$",
            listData[i].total_price
          ],
          unitNum: listData[i].recv_user_num,
          deliver_date: listData[i].deliver_date,
          status: status,
          winBidding: winBidding,
          // href: ""
          href: ""
        };
        this.orderList.push(single);
      }
      this.setHref();
    }
  },
  created() {
    this.getListClassifyNum();
    // 获取分页数据
    let page = Number(sessionStorage.getItem("personalMainCurrentPage"));
    let classify = sessionStorage.getItem("personalMainCurrentClassify");

    this.currentPage = page || 1;

    if (classify && classify !== "all") {
      this.initPage = this.currentPage;
      this.classify = classify;
    } else {
      this.getListDate(this.currentPage);
    }
  },
  computed: {
    ...mapState({
      userInfo: state => state.userInfo
    })
  },
  filters: {},
  components: {
    Top,
    Logo,
    Footer,
    SilderBar,
    Paging
  },
  watch: {
    classify: function() {
      sessionStorage.setItem("personalMainCurrentClassify", this.classify);
      if (this.initPage == 1) {
        this.getListDate();
      } else {
        this.getListDate(this.initPage);
        this.initPage = 1;
      }
    }
  }
};
</script>

<style lang="less" scoped>
#content {
  // background: [background-color] [background-image] [background-repeat] [background-attachment] [background-position] / [ background-size] [background-origin] [background-clip];
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
  box-sizing: border-box;
  // padding: 30px;
  // 我的收入
}
.income {
  margin: 30px;
  // 標題
  .title {
    color: #12222e;
    font-size: 18px;
  }
  // 詳細
  .details {
    margin-top: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .singleTit {
    font-size: 14px;
  }
  .singleCon {
    margin-top: 12px;
    font-size: 30px;
    color: #0096ff;
    > span {
      font-size: 16px;
    }
  }
  // 環形表
  /deep/ .el-progress-circle svg path:nth-child(1) {
    stroke: #aac0cf;
  }
  /deep/ .el-progress__text {
    color: #12222e;
    font-size: 30px !important;
    &:before {
      display: block;
      content: "中標率";
      font-size: 14px;
      margin-bottom: 6px;
    }
  }
}
// 分割線
.splitLine {
  width: 100%;
  height: 4px;
  background-color: #273846;
}
// 接單管理
.manage {
  margin: 30px;
  // 總標題
  .title {
    color: #12222e;
    font-size: 16px;
    display: flex;
    align-items: center;
  }
  .tit_icon {
    width: 25px;
    height: 25px;
    background: url(../../assets/imgs/personal/manageIcon.png) no-repeat;
    margin-right: 8px;
  }
}
// 篩選欄
.el-radio-group {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
}
.el-radio {
  width: 100%;
  height: 40px;
  margin: 0;
  padding: 0;
  background-color: #c0c8cf;
  text-align: center;
  display: flex;
  align-items: center;
  margin-right: 1px;
  position: relative;
  &:last-child {
    margin-right: 0;
    &::after {
      display: none;
    }
  }
  &::after {
    content: "";
    display: block;
    width: 1px;
    height: 40px;
    background-color: #eff9ff;
    position: absolute;
    top: 0;
    right: -1px;
    border-top: 8px solid #c0c8cf;
    border-bottom: 8px solid #c0c8cf;
    box-sizing: border-box;
  }
  &.is-checked {
    background-color: #2e6e9e;
  }
  /deep/ .el-radio__input {
    display: none;
    &.is-checked + .el-radio__label {
      color: #fff;
    }
    & + .el-radio__label {
      display: inline-block;
      width: 100%;
      padding: 0;
    }
  }
}

// 列表框
.list_single {
  padding: 15px 0px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #c0c8cf;
  transition-duration: 0.3s;
  position: relative;
  &:hover {
    background-color: #e1e9ef;
  }
  &.winBidding:after {
    display: block;
    content: "";
    width: 86px;
    height: 66px;
    background: url(../../assets/imgs/personal/winBidding.png) no-repeat;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  // &.failBidding {
  // }
}
// 列表左側
.list_sin_left {
  display: flex;
}
// 訂單所屬類別
.sin_left_scope {
  width: 70px;
  height: 70px;
  background: url(../../assets/imgs/personal/listScopeBG.png) no-repeat;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 5px;
  user-select: none;
}
// 基本信息
.sin_left_info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.left_info_item {
  display: flex;
  align-items: center;
}
// 訂單編號
.formNum {
  color: #212f3a;
  font-size: 16px;
  width: 230px;
  cursor: pointer;
  &:hover {
    color: #0093ff;
  }
}
// 狀態
.state {
  display: flex;
  div {
    width: 70px;
    height: 26px;
    line-height: 26px;
    text-align: center;
    margin-right: 10px;
    color: #fff;
    font-size: 14px;
    background-color: #9ab0c3;
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
// 參與單位數
.unitNum {
  color: #202e39;
  font-size: 16px;
}
// 列表右側
.list_sin_right {
  display: flex;
  align-items: center;
}
.sin_right_left {
  font-size: 16px;
  .remainTime,
  .failed {
    width: 280px;
    text-align: center;
  }
  .remainTime {
    color: #0096ff;
  }
  .failed {
    color: #626f7f;
  }
}
.sin_right_right {
  display: flex;
  flex-direction: column;
  align-items: center;
  user-select: none;
  // &.failed {
  // }
  &.deliver {
    > .status {
      color: #e51313;
    }
    > .btn {
      background-color: #e51313;
    }
  }
  &.takeDel {
    > .status {
      color: #12222e;
    }
  }
  &.payment {
    > .status {
      color: #12222e;
    }
  }
  &.gather {
    > .status {
      color: #e6a23c;
    }
    > .btn {
      background-color: #e6a23c;
    }
  }
  &.evaluation {
    > .status {
      color: #0096ff;
    }
    > .btn {
      background-color: #0096ff;
    }
  }
  &.finished {
    > .status {
      color: #626f7f;
    }
  }
  > .status {
    color: #212f3a;
    font-size: 16px;
  }
  > .btn {
    margin: 12px 0;
    color: #fff;
    background-color: #2e6e9e;
    width: 80px;
    height: 30px;
    font-size: 14px;
    border-radius: 2px;
    text-align: center;
    line-height: 30px;
    cursor: pointer;
    a {
      display: inline-block;
      width: 100%;
      height: 100%;
    }
  }
  > .showDoc {
    color: #0096ff;
    cursor: pointer;
  }
}
</style>