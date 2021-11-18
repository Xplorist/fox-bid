<template>
  <div class="administration">
    <el-row type="flex" justify="space-between">
      <el-col :span="16">
        <div class="left-module-box">
          <div class="dashboard module-box">
            <h3 class="module-title">實時看板</h3>
            <div class="drop-list">
              <select name="" id="" v-model="regionScope">
                <option value="全部">全部</option>
                <option value="成都">成都</option>
                <option value="深圳">深圳</option>
              </select>
            </div>
            <div class="details" @click="$router.push('orderSummary')">
              詳細數據 ＞
            </div>
            <div class="echart-title">
              <img src="../../../assets/imgs/admin/rmb-icon.png" alt="" />
              <div class="describe">
                <p>接單總金額（元）</p>
                <p>{{ dashboardAmount }}</p>
              </div>
            </div>
            <e-charts :option="dashboardOption" />
          </div>
          <div class="manage module-box">
            <h3 class="module-title">經營分析</h3>
            <div style="display: flex">
              <div class="manage-echart-box">
                <span>發佈類型</span>
                <e-charts :option="manageOption1" />
              </div>
              <div class="manage-echart-box">
                <span>客戶營銷</span>
                <e-charts :option="manageOption2" />
              </div>
              <div class="manage-echart-box">
                <span>訂單分析</span>
                <e-charts :option="manageOption3" />
              </div>
            </div>
          </div>
          <div class="factory module-box">
            <h3 class="module-title">廠部分析</h3>
            <e-charts :option="factoryOption" />
            <!-- <e-charts :option="sendTopOption" /> -->
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="right-module-box">
          <div class="list module-box">
            <h3 class="module-title" style="margin: 0 20px">實時榜單</h3>
            <div class="list-radio-box">
              <MRadio
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

            <div style="height:100%;display:flex;flex-direction: column">
              <div style="flex:1;display:flex;flex-direction: column">
                <div class="list-item-title">
                  發單-TOP10
                  <span
                    @click="
                      $router.push('administration/rankingList?type=send')
                    "
                    >更多 ></span
                  >
                </div>
                <div style="display: flex">
                  <MRadio
                    v-for="(item, index) in radioSendData"
                    :key="index + item"
                    :rId="item.id"
                    :rName="item.name"
                    :rValue="item.value"
                    :rChecked="item.checked"
                    :rLabel="item.label"
                    @radioValChange="radioValChange"
                    style="margin-left:15px"
                  />
                </div>
                <div style="flex:1">
                  <e-charts :option="sendTopOption" />
                </div>
              </div>
              <div style="flex:1;display:flex;flex-direction: column">
                <div class="list-item-title">
                  接單-TOP10
                  <span
                    @click="
                      $router.push('administration/rankingList?type=recv')
                    "
                    >更多 ></span
                  >
                </div>
                <div style="display: flex">
                  <MRadio
                    v-for="(item, index) in radioRecvData"
                    :key="index + item"
                    :rId="item.id"
                    :rName="item.name"
                    :rValue="item.value"
                    :rChecked="item.checked"
                    :rLabel="item.label"
                    @radioValChange="radioValChange"
                    style="margin-left:15px"
                  />
                </div>
                <div style="flex:1">
                  <e-charts :option="recvTopOption" />
                </div>
              </div>
            </div>
          </div></div
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import ECharts from "@/components/echarts/Echarts";
import {
  realtimeDataBoard,
  publishType,
  customerMarketing,
  billAnalysis,
  factoryAnalysis,
  sendUserRank,
  receiveUserRank
} from "../../../api/statistics.js";

import MRadio from "./Modules/MRadio";
import {
  dashboardOption,
  manageOption1,
  manageOption2,
  manageOption3,
  factoryOption,
  sendTopOption,
  recvTopOption
} from "./echart-data.js";

export default {
  data() {
    return {
      // 實時看板數據
      dashboardAmount: "",
      regionScope: "全部",
      dashboardOption,
      // 經營分析數據
      manageOption1,
      manageOption2,
      manageOption3,
      // 廠部分析數據
      factoryOption,
      // 排行榜數據
      sendTopOption,
      recvTopOption,
      // 單選框數據
      radioTimeData: [
        {
          id: "this-month",
          name: "timeRadio",
          value: "thisMonth",
          checked: true,
          label: "近一月"
        },
        {
          id: "this-year",
          name: "timeRadio",
          value: "thisYear",
          checked: false,
          label: "近一年"
        }
      ],
      radioSendData: [
        {
          id: "send-num",
          name: "sendRadio",
          value: "sendNum",
          checked: true,
          label: "發單數"
        },
        {
          id: "send-money",
          name: "sendRadio",
          value: "sendPrice",
          checked: false,
          label: "發單金額"
        }
      ],
      radioRecvData: [
        {
          id: "recv-rate",
          name: "recvRadio",
          value: "winRate",
          checked: true,
          label: "中標率"
        },
        {
          id: "recv-num",
          name: "recvRadio",
          value: "winNum",
          checked: false,
          label: "中標數"
        },
        {
          id: "recv-money",
          name: "recvRadio",
          value: "winPrice",
          checked: false,
          label: "中標金額"
        }
      ],
      radioSendChoosed: "sendNum", // val: 'sendNum' 'sendPrice'
      radioRecvChoosed: "winRate" // val: 'winRate' 'winPrice' 'winNum'
    };
  },

  methods: {
    // radio 選中變化
    radioValChange(name, val) {
      switch (name) {
        case "timeRadio":
          this.getSendUserRankData(val);
          this.getRecvUserRankData(val);
          break;
        case "sendRadio":
          this.radioSendChoosed = val;
          this.handleSendUserRankOption();
          break;
        case "recvRadio":
          this.radioRecvChoosed = val;
          this.handleReceiveUserRankOption();
          break;
        default:
          break;
      }
    },

    // 【看版】數據
    getDashboardData() {
      const params = { factory: this.regionScope };
      realtimeDataBoard(params).then(res => {
        if (res.code === "1") {
          this.dashboardAmount =
            res.t.curMonth[res.t.curMonth.length - 1].totalSum;
          this.handleDashboardOption(res.t);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleDashboardOption(val) {
      // 上月 總值 每日值
      let yData1 = [];
      let evalDay1 = [];
      // 本月 總值 每日值
      let yData2 = [];
      let evalDay2 = [];

      for (let item of val.preMonth) {
        yData1.push(item.thisMonthSum);
        evalDay1.push(item.curDaySum);
      }
      for (let item of val.curMonth) {
        yData2.push(item.thisMonthSum);
        evalDay2.push(item.curDaySum);
      }

      this.dashboardOption.tooltip.formatter = function(param) {
        let str = "";
        if (param.length === 1) {
          if (param[0].seriesName === "本月") {
            str = `本月${param[0].name}日累計：${param[0].value}元`;
          } else {
            str = `上月${param[0].name}日累計：${param[0].value}元`;
          }
        } else {
          str = `
            本月${param[0].name}日累計：${param[1].value}元<br />
            上月${param[0].name}日累計：${param[0].value}元<br />
            今日：${evalDay1[param[1].dataIndex]}元`;
        }
        return str;
      };

      this.dashboardOption.series[0].data = yData2;
      this.dashboardOption.series[1].data = yData1;
    },

    // 【經營分析】數據
    getManageData() {
      publishType().then(res => {
        if (res.code === "1") {
          this.handleManageOption(this.manageOption1, res.t, "發佈總量");
        } else {
          this.$message.error(res.msg);
        }
      });

      customerMarketing().then(res => {
        if (res.code === "1") {
          this.handleManageOption(this.manageOption2, res.t, "客戶營銷");
        } else {
          this.$message.error(res.msg);
        }
      });

      billAnalysis().then(res => {
        if (res.code === "1") {
          this.handleManageOption(this.manageOption3, res.t, "訂單分析");
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleManageOption(option, data, title) {
      let manageData1 = data;
      let manageAmount1 = 0;

      manageData1.forEach(item => {
        manageAmount1 += item.value;
      });

      // 第三個圖表無需總量
      if (option === this.manageOption3) {
        for (let item of manageData1) {
          if (item.name === "中標數") {
            option.title.subtext = item.value;
          }
        }
      } else {
        option.title.subtext = manageAmount1;
      }

      option.title.text = title;
      option.series[0].data = manageData1;
      option.legend.formatter = function(name) {
        let rate;
        let num;
        for (let item of manageData1) {
          if (item.name === name) {
            num = item.value;
            rate = Math.round((num / manageAmount1) * 100);
          }
        }
        return name + "\n" + num + "(" + rate + "%)";
      };
    },

    // 【廠部】數據
    getFactoryData() {
      factoryAnalysis().then(res => {
        if (res.code === "1") {
          this.handleFactoryDOption(res.t);
        } else {
          this.$$message.error(res.msg);
        }
      });
    },
    handleFactoryDOption(data) {
      let legend = [],
        val = [],
        name = [];
      data.map(item => {
        legend.push(item.name);
        val.push(item.value);
      });
      for (let i = 0; i < val.length; i++) {
        for (let item of val[i]) {
          switch (item.name) {
            case "廠商數":
              item.showName = "廠商數(家)";
              item.showValue = item.value;
              break;
            case "發單量":
              item.showName = "發單量(單)";
              item.showValue = item.value;
              break;
            case "接單數":
              item.showName = "接單數(單)";
              item.showValue = item.value;
              break;
            case "中標數":
              item.showName = "中標數(單)";
              item.showValue = item.value;
              break;
            case "接單金額":
              item.showName = "接單金額(萬)";
              item.showValue = (item.value / 10000).toFixed(2);
              break;
            case "發單金額":
              item.showName = "發單金額(萬)";
              item.showValue = (item.value / 10000).toFixed(2);
              break;
            case "中標率":
              item.showName = "中標率(%)";
              item.showValue = item.value;
              break;
            case "(被)接標率":
              item.showName = "(被)接標率(%)";
              item.showValue = item.value;
              break;
          }
        }
      }

      let showValue1 = [];
      let showValue2 = [];
      val[0].map(item => {
        name.push(item.showName);
        showValue1.push(item.showValue);
      });
      val[1].map(item => {
        showValue2.push(item.showValue);
      });

      this.factoryOption.legend.data = legend;
      this.factoryOption.xAxis[0].data = name;
      this.factoryOption.series[0].data = showValue1;
      this.factoryOption.series[1].data = showValue2;
    },

    // 【發單排行】數據
    getSendUserRankData(timeLimit = "thisMonth") {
      const params = { timeLimit };
      sendUserRank(params).then(res => {
        console.log(1)
        if (res.code === "1") {
          this.$store.dispatch("get_administrationSendData", res.t);
          this.sendData = res.t;
          this.handleSendUserRankOption();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleSendUserRankOption() {
      let val = this.radioSendChoosed;
      let sendData = this.sendData;
      sendData.sort((a, b) => {
        return a[val] - b[val];
      });

      let yData = [],
        seriesData = [];
      for (let item of sendData) {
        yData.push(item.deptName);
        seriesData.push(item[val]);
      }

      this.sendTopOption.yAxis.data = yData;
      this.sendTopOption.series[0].data = seriesData;
      console.log(this.sendTopOption)
    },

    // 【接單排行】數據
    getRecvUserRankData(timeLimit = "thisMonth") {
      const params = { timeLimit };
      receiveUserRank(params).then(res => {
        if (res.code === "1") {
          this.$store.dispatch("get_administrationRecvData", res.t);
          this.recvData = res.t;
          this.handleReceiveUserRankOption();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleReceiveUserRankOption() {
      let val = this.radioRecvChoosed;
      let recvData = this.recvData;
      recvData.sort((a, b) => {
        return a[val] - b[val];
      });

      let yData = [],
        seriesData = [];
      for (let item of recvData) {
        yData.push(item.deptName);
        seriesData.push(item[val]);
      }
      this.recvTopOption.yAxis.data = yData;
      this.recvTopOption.series[0].data = seriesData;
    }
  },
  created() {
    this.getDashboardData();
    this.getManageData();
    this.getFactoryData();
    this.getSendUserRankData();
    this.getRecvUserRankData();
  },
  watch: {
    regionScope: {
      handler() {
        this.getDashboardData();
      }
    }
  },
  components: {
    ECharts,
    MRadio
  }
};
</script>

<style lang="less" scoped>
.administration {
  min-height: 100%;
  box-sizing: border-box;
  padding: 20px;
  background-color: #0d1a23;
  .left-module-box {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
  }
  .right-module-box {
    display: flex;
    flex-direction: column;
    height: 100%;
  }
}

.module-box {
  box-sizing: border-box;
  padding: 20px;
  margin: 5px;
  height: 100%;
  background-color: #12222e;
  .module-title {
    font-size: 18px;
    font-weight: 400;
    margin: 0;
    color: #2e6e9e;
  }
}

.dashboard {
  position: relative;
  .echart-title {
    position: absolute;
    top: 50px;
    left: 5%;
    display: flex;
  }
  .drop-list {
    position: absolute;
    top: 20px;
    right: 140px;
    select {
      background-color: #1c303f;
      border-color: #1c303f;
      color: #96a8b6;
      cursor: pointer;
      &:focus {
        outline: none;
      }
    }
  }
  .details {
    position: absolute;
    top: 20px;
    right: 20px;
    color: #5e6c77;
    cursor: pointer;
  }
  img {
    width: 47px;
    height: 47px;
    margin-right: 5px;
  }
  .describe {
    p {
      margin: 0;
    }
    p:nth-child(1) {
      color: #5e6c77;
    }
    p:nth-child(2) {
      font-size: 30px;
      color: #96a8b6;
    }
  }
}

.manage {
  .manage-echart-box {
    flex: 1;
    position: relative;
    span {
      position: absolute;
      top: 2%;
      left: 50%;
      transform: translateX(-50%);
      color: #96a8b6;
    }
  }
}

.list {
  padding-left: 0;
  padding-right: 0;
  position: relative;
  .list-radio-box {
    position: absolute;
    right: 20px;
    top: 20px;
    display: flex;
  }
  .list-item-title {
    padding: 10px 20px;
    margin: 10px 0;
    color: #96a8b6;
    background-color: #1c303f;
    span {
      float: right;
      cursor: pointer;
      color: #5b6974;
    }
  }
}

.el-row--flex {
  min-height: calc(100vh - 120px);
}
</style>
