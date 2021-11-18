<template>
  <div class="choose-details">
    <div class="top">
      <div class="back-btn">
        <router-link to="/demand">
          返回
        </router-link>
      </div>
      <!-- 排序 -->
      <div class="sort" style="position:relative">
        <!-- 展示按鈕 -->
        <div class="sort-btn" @click="sortFlag = !sortFlag">
          <span>排序：</span>
          <span>{{ sortBy | sortNameFormat }}</span>
          <img src="../../assets/imgs/demand/arrow.png" alt />
        </div>
        <div class="classify-list" v-show="sortFlag">
          <span
            v-for="item in classifyList"
            :key="item.id"
            :class="{ active: sortBy == item.val }"
            @click="selectClassfiy(item.val)"
            >{{ item.name }}</span
          >
        </div>
        <button
          v-if="toEndTimeFlag && billInfo.bill_status === '1'"
          class="fail-btn"
          @click="faliBid"
        >
          棄標
        </button>
      </div>
    </div>
    <!-- 標題 -->
    <div class="title" v-if="billInfo">
      <div class="tit-text">
        【模具】需求單號{{ billInfo.bill_no }}&nbsp;需求量:{{ billInfo.amount }}
        <router-link :to="'/particulars?pkid=' + pkid" class="title_btn">
          詳情
        </router-link>
      </div>
      <div class="tit-info">
        我的報價: {{ billInfo.total_price }}&nbsp;{{ billInfo.money_type }}
      </div>
      <div class="tit-info">發佈時間: {{ billInfo.publish_date }}</div>
      <div class="tit-unit">
        當前項目投標單位數
        <span>
          {{
            billInfo.give_recv_user_list
              ? billInfo.give_recv_user_list.length
              : 0
          }}
        </span>
      </div>
    </div>
    <!-- 表格信息 -->
    <table
      style="width:100%; text-align:center; border-collapse:collapse;"
      v-if="give_price_list"
    >
      <tr>
        <th
          v-for="(item, index) in tableTitleContrast"
          :key="index + item"
          :style="{ minWidth: item.minWidth }"
        >
          {{ item.name }}
        </th>
      </tr>
      <tr v-for="(singleList, index) in give_price_list" :key="singleList.pkid">
        <td>{{ index + 1 + (currentPage - 1) * pageSize }}</td>
        <td>
          <router-link
            :to="'/merchant/information?pkid=' + singleList.recv_user.pkid"
            style="color:#0096ff"
          >
            {{ singleList.recv_user.dept_name }}
          </router-link>
        </td>
        <td>{{ singleList.total_price * billInfo.amount }}</td>
        <td>
          <div class="detail-list">
            <span>{{ singleList.total_price }}</span>
            <div class="show-list" @click="getSlavList(singleList)">
              標書
            </div>
          </div>
        </td>
        <td>{{ singleList.deliver_date.split(" ")[0] }}</td>
        <td>{{ singleList.descp }}</td>
        <!-- 狀態為待選標 且 價差小於50% -->
        <td
          v-if="billInfo.bill_status === '1' && singleList.f_valid === 'Y'"
          :class="toEndTimeFlag ? 'choose-win' : 'choose-win-disabled'"
          @click="
            chooseToWin(
              singleList.pkid,
              singleList.recv_user.dept_name,
              singleList.f_valid
            )
          "
        >
          选为中标
        </td>
        <td
          v-else-if="billInfo.bill_status === '1' && singleList.f_valid === 'N'"
        >
          報價過高
        </td>
        <td v-else>
          <img
            v-if="singleList.f_win_bid === 'Y'"
            src="../../assets/imgs/demand/winImg.png"
            alt
          />
        </td>
      </tr>
      <tr v-if="give_price_list.length == 0">
        <td colspan="7">暫無數據</td>
      </tr>
    </table>

    <!-- 分頁 -->
    <Paging
      :total="total"
      :current-page="currentPage"
      :page-size="pageSize"
      @getCurrentPage="getListDate"
    ></Paging>
  </div>
</template>

<script>
import Paging from "@/components/paging/Paging";
import {
  query_bill_by_pkid,
  query_give_price_list,
  select_win_bid,
  abandon_bid
} from "@/api/order";

const tableTitleContrast = [
  { minWidth: "40px", name: "排名" },
  { minWidth: "160px", name: "單位" },
  { minWidth: "60px", name: "總報價" },
  { minWidth: "180px", name: "廠商報價" },
  { minWidth: "100px", name: "交期" },
  { minWidth: "140px", name: "備註" },
  { minWidth: "140px", name: "操作" }
];
export default {
  data() {
    return {
      // 是否能選標
      toEndTimeFlag: false,
      sortItem: "",
      // 總數據量
      total: 1,
      // 當前頁數
      currentPage: 1,
      // 每一頁長度
      pageSize: 8,
      pkid: "",
      // 數據
      give_price_list: [],
      // 訂單信息
      billInfo: {},
      // 零件清單信息
      slav_list: [],
      // 排序
      classifyList: [
        { id: 1, name: "綜合排序", val: "complex" },
        { id: 2, name: "價格優先", val: "price" },
        { id: 3, name: "交期優先", val: "date" }
      ],
      // 默認 | 選中 的排序方式
      sortBy: "complex",
      // 是否展開
      sortFlag: false,
      // 
      recvPkid: "",
      // 表格頭信息對照
      tableTitleContrast
    };
  },
  methods: {
    // 棄標
    faliBid() {
      this.$confirm("此操作將“廢棄”此訂單，是否繼續？", "警告", {
        confirmButtonText: "確認",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          const pkid = this.billInfo.pkid;
          abandon_bid(pkid).then(res => {
            if (res.code === "1") {
              this.$router.push("/demand");
              this.$message({
                type: "success",
                message: "已棄標"
              });
            } else {
              this.$message.error(res.msg);
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    },
    // 查看報價清單
    checkList() {
      sessionStorage.setItem("biddinfdDoc", JSON.stringify(this.billInfo));
      sessionStorage.setItem("recvPkid", JSON.stringify(this.recvPkid));
      // const url = "/biddingDoc?loaded=true&pkid=" + this.pkid;
      let routeData = this.$router.resolve({
        path: "/biddingDoc",
        query: { loaded: true, pkid: this.pkid }
      });
      window.open(routeData.href, "_blank");
    },

    // 選中的項
    selectClassfiy(val) {
      this.sortBy = val;
      this.sortFlag = false;
    },

    // 獲取零件清單信息
    getSlavList(singleList) {
      const pkid = singleList.pkid;
      this.recvPkid = singleList.recv_user_pkid;
      sessionStorage.setItem(
        "deliverDate",
        JSON.stringify(singleList.deliver_date)
      );
      for (let item of this.give_price_list) {
        if (item.pkid == pkid) {
          sessionStorage.setItem("slavList", JSON.stringify(item.slav_list));
          this.checkList();
        }
      }
    },

    // 判斷是否能選擇中標
    getTimeStatus() {
      let endDate = new Date(this.billInfo.bid_end_date);
      let nowDate = new Date();
      this.toEndTimeFlag = nowDate > endDate ? true : false;
    },

    // 選為中標
    chooseToWin(pkid, deptName) {
      if (!this.toEndTimeFlag) {
        return this.$alert("競標結束時間未到，不能選標！", "警告", {
          confirmButtonText: "确定",
          callback: action => {
            return action;
          }
        });
      }

      this.$confirm('请确认是否将 "' + deptName + '" 选为中标单位?', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      })
        .then(() => {
          select_win_bid(pkid).then(res => {
            if (res.code === "1") {
              this.getBillInfo();
              this.getListDate(
                Number(sessionStorage.getItem("demandDetailsCurrentPage"))
              );
              this.$message({
                type: "success",
                message: "成功!"
              });
            } else {
              this.$message.error(res.msg);
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    },

    // 向後端發起請求獲取數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("demandDetailsCurrentPage", this.currentPage);
      const data = {
        bill_pkid: this.pkid,
        pageIndex: this.currentPage,
        pageSize: this.pageSize,
        sort_way: this.sortBy
      };
      query_give_price_list(data).then(res => {
        if (res.code === "1") {
          // 清空原有數據
          this.give_price_list = [];
          this.total = res.t.row_total;
          const listData = res.t.give_price_list;
          this.changeListData(listData);
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 修改展示列表數據
    changeListData(listData) {
      for (let item of listData) {
        this.give_price_list.push(item);
      }
    },

    // 查詢訂單信息
    getBillInfo() {
      query_bill_by_pkid(this.pkid).then(res => {
        if (res.code === "1") {
          this.billInfo = res.t;
          this.getTimeStatus();
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  },
  components: {
    Paging
  },
  created() {
    this.pkid = this.$router.history.current.query.pkid;

    // 獲取分頁數據
    let page = Number(sessionStorage.getItem("demandDetailsCurrentPage"));
    page = page ? page : 1;
    this.getListDate(page);

    // 查詢訂單信息
    this.getBillInfo();
  },
  filters: {
    // 過濾排序名稱的顯示
    sortNameFormat(val) {
      if (!val) return "";
      switch (val) {
        case "complex":
          return "綜合排序";
        case "price":
          return "價格優先";
        case "date":
          return "時間優先";
      }
    }
  },
  watch: {
    sortBy: function() {
      this.getListDate();
    }
  },
  computed: {}
};
</script>

<style lang="less" scoped>
.choose-details {
  background-color: #eff9ff;
  box-sizing: border-box;
  color: #12222e;
  padding: 30px;
}
.top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
// 返回按鈕
.back-btn {
  display: flex;
  > a {
    color: #626f7f;
    font-size: 14px;
    display: flex;
    align-items: center;
    &::before {
      content: "";
      display: block;
      width: 17px;
      height: 12px;
      background: url(../../assets/imgs/personal/back.png) no-repeat;
      margin-right: 6px;
    }
  }
}
// 排序
.sort {
  color: #626f7f;
  font-size: 14px;
  position: relative;
}
.fail-btn {
  width: 70px;
  height: 26px;
  position: absolute;
  right: 0;
  bottom: -140px;
  background-color: #d3dfe7;
  border: 0;
  border-radius: 13px;
  cursor: pointer;
  &:focus {
    outline: none;
  }
}
.sort-btn {
  width: 160px;
  height: 30px;
  border: 1px solid #c0c8cf;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  cursor: pointer;
  span {
    display: inline-block;
  }
  span:nth-child(2) {
    text-align: left;
    width: 60px;
  }
}
.classify-list {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  background-color: #d7efff;
  width: 130px;
  z-index: 1;
  span {
    display: block;
    height: 30px;
    line-height: 30px;
    text-align: center;
    cursor: pointer;
    &.active,
    &:hover {
      color: #0096ff;
    }
  }
}

// 主內容標題
.title {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 14px;
}
.tit-text {
  color: #212f3a;
  font-size: 24px;
  position: relative;
  .title_btn {
    width: 70px;
    height: 26px;
    border-radius: 13px;
    line-height: 26px;
    text-align: center;
    position: absolute;
    background-color: #0096ff;
    color: #eff9ff;
    font-size: 16px;
    right: -80px;
    bottom: -10px;
    cursor: pointer;
  }
}
.tit-info {
  color: #626f7f;
  margin-top: 10px;
}
.tit-unit {
  color: #626f7f;
  margin: 24px 0 16px;
  span {
    font-size: 36px;
  }
}
// 表格
table {
  tr {
    th,
    td {
      border: 1px solid #c0c8cf;
      font-size: 14px;
      height: 60px;
    }
    th {
      height: 40px;
      font-weight: 400;
      background-color: #d3dfe7;
    }
  }
  .detail-list {
    display: flex;
    > span {
      width: 60%;
    }
  }
  .show-list {
    display: flex;
    align-items: center;
    cursor: pointer;
    color: #0096ff;
    &::before {
      content: "";
      display: inline-block;
      width: 16px;
      height: 16px;
      background-image: url("../../assets/imgs/demand/look_gray.png");
      background-repeat: no-repeat;
      margin-right: 4px;
    }
    &:hover::before {
      background-image: url("../../assets/imgs/demand/look.png");
    }
  }
  .choose-win {
    color: #0096ff;
    cursor: pointer;
  }
  .choose-win-disabled {
    color: #626f7f;
    cursor: not-allowed;
  }
}
// 分页
.paging {
  padding: 28px;
  text-align: center;
  height: 50px;
  /deep/ .number {
    width: 30px;
    height: 30px;
    line-height: 30px;
    background: url(../../assets/imgs/index/pageIndBG.png) no-repeat center;
    color: #12222e;
    font-size: 14px;
    &.active {
      color: #fff;
      background: url(../../assets/imgs/index/pageindBG-ac.png) no-repeat center;
    }
    &:hover {
      color: #fff;
    }
  }
  /deep/ .el-icon {
    color: #12222e;
    background: #eff9ff;
  }
  /deep/ .btn-next,
  /deep/ .btn-prev {
    padding: 0 10px;
    width: 70px;
    height: 30px;
    background: url(../../assets/imgs/index/pageBG.png) no-repeat center /
      contain;
  }
}
</style>
