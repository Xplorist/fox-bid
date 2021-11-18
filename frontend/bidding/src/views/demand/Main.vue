<template>
  <div id="main">
    <!-- 我的任务 -->
    <div class="assignment" style="display: none">
      <div class="title">我的任務</div>
      <div class="details">
        <div class="single">
          <span class="singleTit">待签核</span>
          <span class="singleCon">【0】</span>
        </div>
        <div class="single">
          <span class="singleTit">待选标</span>
          <span class="singleCon" style="color: #E51313">【1】</span>
        </div>
        <div class="single">
          <span class="singleTit">待收货</span>
          <span class="singleCon" style="color: #E51313">【2】</span>
        </div>
        <div class="single">
          <span class="singleTit">新动态</span>
          <span class="singleCon" style="color: #E51313">【17】</span>
        </div>
      </div>
    </div>
    <!-- 我的项目 -->
    <div class="project">
      <div class="title">我的項目</div>
      <div class="details">
        <!-- 篩選欄 -->
        <el-radio-group v-model="classify">
          <el-radio
            v-for="item in classifyList"
            :key="item.id"
            :label="item.ename"
            @click="classify = item.ename"
          >
            {{ item.name }}
            <span v-show="item.num">({{ item.num }})</span>
          </el-radio>
        </el-radio-group>
        <!-- 訂單列表 -->
        <div>
          <div
            class="orderList"
            v-for="(item, index) in orderList"
            :key="item.pkid"
          >
            <div class="list_left">
              <div class="left_info1">
                <div>
                  <span class="price"
                    >{{ item.money_type }}{{ item.total_price }}</span
                  >
                  <router-link :to="'/particulars?pkid=' + item.pkid">
                    <span class="orderNumber"
                      >【{{ item.pd_type }}】需求單號：{{ item.bill_no }}</span
                    >
                  </router-link>
                </div>
                <div v-if="item.publish_date" class="startTime">
                  發佈時間：{{ item.publish_date }}
                </div>
              </div>
              <div class="left_info2">
                <div class="unitNumber">
                  {{ item.unitNumber || 0 }}個單位已參與
                </div>
                <!-- <div>
                  <span class="browseNumber">瀏覽：{{item.browseNumber || 0}}</span>
                  <span class="collectNumber">收藏：{{item.collectNumber || 0}}</span>
                </div>-->
              </div>
            </div>
            <div class="list_right">
              <div>
                <div v-if="item.winUnit" class="winUnit">
                  中標單位：{{ item.winUnit || "" }}
                </div>
                <div v-else class="restTime">
                  竞标结束时间：{{ item.restTime }}
                </div>
              </div>
              <div class="right_info">
                <div class="status" :class="item.statusClass">
                  {{ item.status }}
                </div>
                <div
                  class="operation"
                  @click="
                    (item.dropDownFlag = !item.dropDownFlag),
                      getDropItem(item.statusClass, index)
                  "
                >
                  操作
                  <img src="../../assets/imgs/demand/arrow_main.png" alt />
                  <!-- 下拉框 -->
                  <div
                    class="dropDown"
                    v-show="item.dropDownFlag && item.operationList.length"
                  >
                    <span
                      v-for="operation in item.operationList"
                      :key="operation.id"
                      @click="handleOperation(operation.to, item.pkid)"
                      >{{ operation.text }}</span
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
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
</template>

<script>
import Paging from "@/components/paging/Paging";
import {
  query_bill_list_send_user,
  query_status_bill_num_send_user,
  update_bill_status
} from "@/api/order";

export default {
  data() {
    return {
      // 篩選欄默認選中
      classify: "all",
      // 篩選欄列表
      classifyList: [
        { id: "0", ename: "all", name: "全部", num: "" },
        { id: "1", ename: "0", name: "待發佈", num: "" },
        { id: "2", ename: "1", name: "待報價", num: "" },
        { id: "1_5", ename: "1.5", name: "待選標", num: "" },
        { id: "3", ename: "2", name: "待發貨", num: "" },
        { id: "4", ename: "3", name: "待收貨", num: "" },
        { id: "5", ename: "4", name: "待付款", num: "" },
        { id: "6", ename: "5", name: "待收款", num: "" },
        { id: "7", ename: "6", name: "已完成", num: "" },
        { id: "-1", ename: "-1", name: "已流標", num: "" },
        { id: "-2", ename: "-2", name: "已棄標", num: "" }
      ],
      // 下拉框
      dropDownFlag: false,
      // 當前頁的訂單列表
      orderList: [],
      // 操作選項列表
      operationList: [
        {
          id: "-2",
          status: "abandon",
          // text: "操作",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "-1",
          status: "drain",
          // text: "操作",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "1",
          status: "publish",
          // text: "操作",
          child: [
            { id: "1", text: "修改", to: "change" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "2",
          status: "quote",
          // text: "操作",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "3",
          status: "deliver",
          // text: "操作",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "4",
          status: "takeDeliver",
          // text: "操作",
          child: [
            { id: "1", text: "確認收貨", to: "take" },
            { id: "2", text: "投標明細", to: "show" },
            { id: "3", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "5",
          status: "payment",
          // text: "操作",
          child: [
            { id: "1", text: "付款", to: "pay" },
            { id: "2", text: "投標明細", to: "show" },
            { id: "3", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "6",
          status: "gather",
          // text: "待收款",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "7",
          status: "finished",
          // text: "已完成",
          child: [
            { id: "1", text: "投標明細", to: "show" },
            { id: "2", text: "取消", to: "cancel" }
          ]
        },
        {
          id: "8",
          status: "remainEvaluate",
          // text: "已完成",
          child: [
            { id: "1", text: "評價", to: "postEva" },
            { id: "2", text: "查看", to: "show" },
            { id: "3", text: "取消", to: "cancel" }
          ]
        }
      ],
      currentPage: 1,
      pageSize: 10,
      total: 1,
      // 初始页
      initPage: 1
    };
  },
  methods: {
    // 操作處理
    handleOperation(to, pkid) {
      let data = {
        pkid: pkid,
        bill_status: ""
      };
      switch (to) {
        // 修改
        case "change":
          this.$router.push("/demand/publish?pkid=" + pkid);
          break;
        // 評價
        case "postEva":
          this.$router.push("/demand/postEva?pkid=" + pkid);
          break;
        // 收貨
        case "take":
          data.bill_status = 4;
          this.updataStatus(data);
          break;
        // 付款
        case "pay":
          data.bill_status = 5;
          this.updataStatus(data);
          break;
        // 查看
        case "show":
          this.$router.push("/demand/chooseDetails?pkid=" + pkid);
          break;
        // 取消
        case "cancel":
          // this.$router.push()
          break;
      }
    },
    // 變更狀態
    updataStatus(data) {
      update_bill_status(data).then(res => {
        if (res.code === "1") {
          this.getListDate(this.currentPage);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 獲取下拉框條目
    getDropItem(val, index) {
      for (let i in this.operationList) {
        if (this.operationList[i].status == val) {
          this.orderList[index].operationList = this.operationList[i].child;
        }
      }
    },
    // 向後端發起請求獲取數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("demandMainCurrentPage", this.currentPage);
      query_bill_list_send_user(
        this.classify,
        this.currentPage,
        this.pageSize
      ).then(res => {
        if (res.code === "1") {
          // 清空原有數據
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
        // 修改 status
        let status = "";
        let statusClass = "";
        switch (listData[i].bill_status) {
          case "-2":
            status = "已棄標";
            statusClass = "abandon";
            break;
          case "-1":
            status = "已流標";
            statusClass = "drain";
            break;
          case "0":
            status = "待發佈";
            statusClass = "publish";
            break;
          case "1":
            status = "待報價";
            statusClass = "quote";
            break;
          case "2":
            status = "待發貨";
            statusClass = "deliver";
            break;
          case "3":
            status = "待收貨";
            statusClass = "takeDeliver";
            break;
          case "4":
            status = "待付款";
            statusClass = "payment";
            break;
          case "5":
            status = "待收款";
            statusClass = "gather";
            break;
          case "6":
            if (listData[i].f_send_eval === "N") {
              status = "待評價";
              statusClass = "remainEvaluate";
              break;
            }
            status = "已完成";
            statusClass = "finished";
            break;
          default:
            break;
        }
        var obj = {
          pkid: listData[i].pkid,
          total_price: listData[i].total_price,
          pd_type: listData[i].pd_type,
          bill_no: listData[i].bill_no,
          publish_date: listData[i].publish_date
            ? listData[i].publish_date.split("T")[0]
            : "",
          unitNumber: listData[i].recv_user_num,
          browseNumber: "295",
          collectNumber: "67",
          winUnit: listData[i].recv_user ? listData[i].recv_user.dept_name : "",
          restTime: listData[i].bid_end_date
            ? listData[i].bid_end_date.split("T")[0]
            : "",
          status: status,
          statusClass: statusClass,
          money_type: listData[i].money_type == "RMB" ? "￥" : "$",
          dropDownFlag: false
        };
        this.orderList.push(obj);
      }
    },

    // 獲取訂單狀態數量
    getListClassifyNum() {
      query_status_bill_num_send_user().then(res => {
        if (res.code === "1") {
          const result = res.t;
          for (let i in this.classifyList) {
            switch (this.classifyList[i].ename) {
              case "-2":
                this.classifyList[i].num = result.num_status_n2;
                break;
              case "-1":
                this.classifyList[i].num = result.num_status_n1;
                break;
              case "all":
                this.classifyList[i].num = result.num_status_all;
                break;
              case "0":
                this.classifyList[i].num = result.num_status_0;
                break;
              case "1":
                this.classifyList[i].num = result.num_status_1;
                break;
              case "1.5":
                this.classifyList[i].num = result.num_status_1_5;
                break;
              case "2":
                this.classifyList[i].num = result.num_status_2;
                break;
              case "3":
                this.classifyList[i].num = result.num_status_3;
                break;
              case "4":
                this.classifyList[i].num = result.num_status_4;
                break;
              case "5":
                this.classifyList[i].num = result.num_status_5;
                break;
              case "6":
                this.classifyList[i].num = result.num_status_6;
                break;
              case "no_send_eval":
                this.classifyList[i].num = result.num_no_send_eval;
                break;
              default:
                break;
            }
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  },
  created() {
    // 獲取篩選表數據
    this.getListClassifyNum();
    // 獲取分頁數據
    let page = Number(sessionStorage.getItem("demandMainCurrentPage"));
    let classify = sessionStorage.getItem("demandMainCurrentClassify");

    this.currentPage = page || 1;

    if (classify && classify !== "all") {
      this.initPage = this.currentPage;
      this.classify = classify;
    } else {
      this.getListDate(this.currentPage);
    }
  },
  components: {
    Paging
  },
  computed: {},
  watch: {
    classify: function() {
      sessionStorage.setItem("demandMainCurrentClassify", this.classify);
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
// 我的任务 & 我的项目
.assignment,
.project {
  background-color: #eff9ff;
  box-sizing: border-box;
  padding: 20px;
  color: #12222e;
  > .title {
    font-size: 18px;
  }
}
.assignment {
  height: 100px;
  // 詳細
  > .details {
    margin-top: 25px;
    display: flex;
    align-items: center;
    font-size: 14px;
  }
  .singleCon {
    margin-right: 60px;
  }
  margin-bottom: 10px;
}
.project {
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
    background-color: #d3dfe7;
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
      border-top: 8px solid #d3dfe7;
      border-bottom: 8px solid #d3dfe7;
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
  // 訂單列表
  .orderList {
    height: 110px;
    display: flex;
    justify-content: space-between;
    font-size: 16px;
    padding: 30px 0 30px 0;
    box-sizing: border-box;
    border-bottom: 1px solid #d3dfe7;
    transition-duration: 0.3s;
    &:hover {
      background-color: #e1e9ef;
    }
  }
  // 左側
  .list_left {
    display: flex;
    justify-content: space-between;
  }
  .left_info1,
  .left_info2 {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .left_info1 {
    min-width: 380px;
  }
  .left_info2 {
    text-align: center;
    > div {
      width: 160px;
    }
  }
  .price {
    font-size: 18px;
    color: #0092ff;
  }
  .orderNumber {
    margin-left: 10px;
    color: #0f1e29;
    &:hover {
      color: #0092ff;
    }
  }
  .startTime {
    font-size: 14px;
    color: #626f7f;
  }
  .unitNumber {
    font-size: 14px;
    color: #0092ff;
  }
  .browseNumber,
  .collectNumber {
    font-size: 14px;
    color: #626f7f;
    margin-right: 14px;
  }
  // 右側
  .list_right {
    width: 420px;
    display: flex;
    justify-content: space-between;
  }
  .winUnit,
  .restTime {
    min-width: 250px;
    padding: 0 5px;
    line-height: 30px;
    text-align: center;
  }
  .winUnit {
    border: 1px solid #e51313;
    color: #e51313;
  }
  .restTime {
    color: #0096ff;
  }
  .status {
    margin-bottom: 10px;
    text-align: center;
    color: #0f1e29;
    &.publish {
      color: #0096ff;
    }
    &.quote {
      color: #67c23a;
    }
    &.deliver,
    &.takeDeliver {
      color: #e6a23c;
    }
    &.payment,
    &.gather {
      color: #e51313;
    }
    &.remainEvaluate,
    &.finished {
      color: #626f7f;
    }
  }
  .operation {
    position: relative;
    width: 80px;
    height: 30px;
    background-color: #2e6e9e;
    color: #ffffff;
    // 避免連續點擊選中文字
    font-size: 14px;
    text-align: center;
    line-height: 30px;
    cursor: pointer;
    user-select: none;
    img {
      margin-left: 5px;
    }
  }
  // 下拉框
  .dropDown {
    width: 80px;
    box-sizing: border-box;
    position: absolute;
    border: 1px solid #2e6e9e;
    background-color: #eff9ff;
    overflow: hidden;
    z-index: 1;
    span {
      display: inline-block;
      height: 30px;
      width: 100%;
      color: #626f7f;
      &:hover {
        color: #fff;
        background-color: #2e6e9e;
      }
    }
  }
}
</style>
