<template>
  <div class="ranking-list">
    <div class="content-box">
      <div>
        <el-button
          type="primary"
          class="back-btn"
          icon="el-icon-arrow-left"
          @click="$router.back()"
        ></el-button>
        <h3 class="module-title">
          {{ type === "send" ? "發單方" : "接單方" }}排行榜
        </h3>
        <div class="radio-box">
          <m-radio
            v-for="(item, index) in radioTimeData"
            :key="index + item"
            :rId="item.id"
            :rName="item.name"
            :rValue="item.value"
            :rChecked="item.checked"
            :rLabel="item.label"
            @radioValChange="radioValChange"
            rColor="#96A8B6"
          />
        </div>
      </div>

      <div class="table-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="60"> </el-table-column>
          <el-table-column
            v-for="(item, index) in type === 'send'
              ? sendTableTitle
              : recvTableTitle"
            :key="index + item.prop"
            :prop="item.prop"
            :label="item.label"
            :min-width="item.minWidth"
            :show-overflow-tooltip="item.showOverflowTooltip"
            :sortable="item.sortable"
          ></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <button @click="handleDetail(scope.$index, scope.row)">
                詳情
              </button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div>
      <details-alert
        :choosedData="choosedData"
        @choosedDataEmpty="setChoosedData"
      ></details-alert>
    </div>
  </div>
</template>

<script>
import { sendUserRank, receiveUserRank } from "@/api/statistics.js";
import MRadio from "../Modules/MRadio";
import DetailsAlert from "../Modules/DetailsAlert";

export default {
  data() {
    return {
      // radio 數據
      radioTimeData: [
        {
          id: "this-month",
          name: "timeRadio",
          value: "thisMonth",
          checked: true,
          label: "本月"
        },
        {
          id: "this-year",
          name: "timeRadio",
          value: "thisYear",
          checked: false,
          label: "本年"
        }
      ],
      // 表格數據
      tableData: [
        {
          userId: "9837A594291FF14EE050F40A5BE9FEB3",
          username: "F3236205",
          deptName: "富泰捷",
          receiveNum: 3,
          winNum: 0,
          winPrice: 0,
          winRate: 0,
          score: 0,
          outRate: 0,
          outQuality: 0,
          service: 0
        }
      ],
      // 類別
      type: "發單方",
      // 發單方列表
      sendTableTitle: [
        {
          prop: "deptName",
          label: "單位",
          minWidth: "160",
          showOverflowTooltip: true
        },
        {
          prop: "sendNum",
          label: "發佈數量",
          sortable: true
        },
        {
          prop: "sendPrice",
          label: "發佈金額",
          sortable: true
        },
        {
          prop: "receiveRate",
          label: "(被)接標率",
          sortable: true
        },
        {
          prop: "score",
          label: "客戶評分",
          sortable: true
        },
        {
          prop: "payment",
          label: "付款時效",
          sortable: true
        },
        {
          prop: "service",
          label: "服務態度",
          sortable: true
        }
      ],
      // 接單方列表
      recvTableTitle: [
        {
          prop: "deptName",
          label: "單位",
          minWidth: "160",
          showOverflowTooltip: true
        },
        {
          prop: "receiveNum",
          label: "接單數量",
          sortable: true
        },
        {
          prop: "winNum",
          label: "中標數",
          sortable: true
        },
        {
          prop: "winPrice",
          label: "中標金額",
          sortable: true
        },
        {
          prop: "winRate",
          label: "中標率",
          sortable: true
        },
        {
          prop: "score",
          label: "綜合評分",
          sortable: true
        },
        {
          prop: "outRate",
          label: "出貨時效",
          sortable: true
        },
        {
          prop: "outQuality",
          label: "出貨質量",
          sortable: true
        },
        {
          prop: "service",
          label: "服務態度",
          sortable: true
        }
      ],
      // 選中的列表數據
      choosedData: {}
    };
  },
  created() {
    this.type = this.$router.history.current.query.type;
    this.getRecvUserRankData();
  },
  methods: {
    // 設置選中的表格數據
    setChoosedData() {
      this.choosedData = {};
    },

    // 點擊詳細
    handleDetail(index, row) {
      this.choosedData = { pkid: row.userId };
    },

    // radio 選中變化
    radioValChange(name, val) {
      this.getRecvUserRankData(val);
    },

    // 獲取數據
    getRecvUserRankData(timeLimit = "thisMonth") {
      const params = { timeLimit };
      this.type === "send"
        ? sendUserRank(params).then(res => {
            this.handleRes(res);
          })
        : receiveUserRank(params).then(res => {
            this.handleRes(res);
          });
    },
    handleRes(res) {
      if (res.code === "1") {
        this.tableData = res.t;
      } else {
        this.$message.error(res.msg);
      }
    }
  },
  components: { MRadio, DetailsAlert }
};
</script>

<style lang="less" scoped>
.ranking-list {
  min-height: 100%;
  box-sizing: border-box;
  padding: 20px;
  background-color: #0d1a23;
  .content-box {
    box-sizing: border-box;
    padding: 20px;
    background-color: #12222e;
  }
  .back-btn {
    background-color: rgba(0, 0, 0, 0);
    border-color: rgba(0, 0, 0, 0);
  }
}
.module-title {
  font-size: 18px;
  font-weight: 400;
  margin: 0;
  color: #2e6e9e;
  display: inline-block;
}
.radio-box {
  display: flex;
  float: right;
}
.table-box {
  margin-top: 20px;
  /deep/.el-table {
    background-color: transparent;
    th {
      background-color: #12222e;
      border-bottom: 4px solid #12222e;
      color: #5e6c77;
      font-weight: 400;
    }
    th:nth-child(1) {
      color: transparent;
    }
    .sort-caret.ascending {
      border-bottom-color: #5e6c77;
    }
    .ascending .sort-caret.ascending {
      border-bottom-color: #0096ff;
    }
    .sort-caret.descending {
      border-top-color: #5e6c77;
    }
    .descending .sort-caret.descending {
      border-top-color: #0096ff;
    }

    td {
      background-color: #172632;
      color: #96a8b6;
      border-bottom: 4px solid #12222e;
    }
    td:nth-child(1) {
      font-size: 30px;
      font-style: italic;
    }
    td:nth-child(2) .cell {
      white-space: nowrap;
    }
    td:last-child button {
      border: none;
      background-color: transparent;
      color: #008fff;
      cursor: pointer;
      &:focus {
        outline: none;
      }
    }

    tr:nth-child(1),
    tr:nth-child(2),
    tr:nth-child(3) {
      td {
        background-color: #112a3d;
      }
      td:nth-child(1) {
        color: #0096ff;
      }
    }

    tr:hover {
      td {
        background-color: #1c2d3a;
      }
    }

    &::before {
      background-color: #12222e;
    }
  }
}
</style>
