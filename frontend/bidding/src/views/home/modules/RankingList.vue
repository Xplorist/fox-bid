<template>
  <div class="list-warpper">
    <header class="title">實時榜單</header>
    <div class="radio-warpper">
      <MRadio
        v-for="(item, index) in radioTimeData"
        :key="index + item"
        :rId="item.id"
        :rName="item.name"
        :rValue="item.value"
        :rChecked="item.checked"
        :rLabel="item.label"
        :rColor="item.color"
        @radioValChange="radioValChange"
        style="margin-left:15px"
      />
    </div>
    <!-- echart -->
    <div class="content">
      <div
        class="echart-wrapper"
        v-for="(echartItem, echartIndex) in echartList"
        :key="echartItem + echartIndex"
      >
        <span>{{ echartItem.title }}</span>
        <div class="echart-box">
          <e-charts :option="echartItem.option" />
          <ul class="rank-num">
            <li
              v-for="(colorItem, colorIndex) in rankNumColor"
              :key="colorItem + colorIndex"
              :style="{ backgroundColor: colorItem.bg }"
            >
              <span :style="{ color: colorItem.text }">
                {{ colorIndex + 1 }}
              </span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ECharts from "@/components/echarts/Echarts";
import MRadio from "../../../components/operate/Radio";
import { option } from "./echart-data.js";

import { sendUserRank, receiveUserRank } from "../../../api/statistics.js";

export default {
  data() {
    return {
      option: option,
      sendData: [],
      recvData: [],
      radioTimeData: [
        {
          id: "this-month",
          name: "timeRadio",
          value: "thisMonth",
          checked: false,
          label: "近一月",
          color: "#ffffff"
        },
        {
          id: "this-year",
          name: "timeRadio",
          value: "thisYear",
          checked: true,
          label: "近一年",
          color: "#ffffff"
        }
      ],
      rankNumColor: [
        { text: "#CFE6F6", bg: "#FF9000" },
        { text: "#CFE6F6", bg: "#36CC2A" },
        { text: "#CFE6F6", bg: "#0092FF" },
        { text: "#12222E", bg: "#CFE6F6" },
        { text: "#12222E", bg: "#CFE6F6" }
      ],
      echartList: [
        { type: "sendNum", title: "发单数TOP-5", option: {} },
        { type: "sendPrice", title: "發单金额TOP-5", option: {} },
        { type: "winNum", title: "中标数TOP-5", option: {} },
        { type: "winPrice", title: "中标金额TOP-5", option: {} }
      ]
    };
  },
  created() {
    this.getRankData();
  },
  methods: {
    // radio 選中變化
    radioValChange(name, val) {
      if (name === "timeRadio") {
        this.getRankData(val);
      } else {
        return;
      }
    },

    // 獲取排行榜數據
    getRankData(timeLimit = "thisYear") {
      const params = { timeLimit };
      sendUserRank(params).then(res => {
        if (res.code === "1") {
          this.sendData = res.t;
          this.getOption("send");
        } else {
          this.$message.error(res.msg);
        }
      });
      receiveUserRank(params).then(res => {
        if (res.code === "1") {
          this.recvData = res.t;
          this.getOption("recv");
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 獲取 echart 數據
    getOption(type) {
      let sendPriceOption, sendNumOption, winPriceOption, winNumOption;
      if (type === "send") {
        sendPriceOption = this.handleRankOption("send", "sendPrice");
        sendNumOption = this.handleRankOption("send", "sendNum");
      } else {
        (winPriceOption = this.handleRankOption("recv", "winPrice")),
          (winNumOption = this.handleRankOption("recv", "winNum"));
      }

      for (let item of this.echartList) {
        switch (item.type) {
          case "sendPrice":
            item.option = sendPriceOption;
            break;
          case "sendNum":
            item.option = sendNumOption;
            break;
          case "winPrice":
            item.option = winPriceOption;
            break;
          case "winNum":
            item.option = winNumOption;
            break;
          default:
            break;
        }
      }
    },

    // 處理 echarts 數據
    handleRankOption(role, type) {
      let data = role === "send" ? this.sendData : this.recvData;

      data.sort((a, b) => {
        return b[type] - a[type];
      });

      let deptName = [],
        seriesData1 = [],
        seriesData0 = [],
        currentOption = JSON.parse(JSON.stringify(option));

      for (let index in data) {
        if (index < 5) {
          deptName.unshift(data[index].deptName);
          seriesData1.unshift(data[index][type]);
        }
      }
      
      // 數據不夠補零
      if (data.length < 5) {
        for (let i = 0; i < 5 - data.length; i++) {
          deptName.unshift(" ");
          seriesData1.unshift(0);
        }
      }

      for (let i in seriesData1) {
        let obj = {
          name: deptName[i],
          value: seriesData1[i]
        };
        seriesData0.push(obj);
      }
      currentOption.series[0].data = seriesData0;
      currentOption.series[1].data = seriesData1;

      return currentOption;
    }
  },
  components: {
    MRadio,
    ECharts
  }
};
</script>

<style lang="less" scoped>
.list-warpper {
  padding: 10px;
  background-image: radial-gradient(
    rgba(135, 174, 202, 0.4) 10%,
    rgba(121, 159, 189, 0.4) 25%,
    rgba(21, 39, 53, 0.4) 70%
  );
  .title {
    text-align: center;
    font-size: 18px;
    color: #ffffff;
  }
  .radio-warpper {
    display: flex;
    justify-content: flex-end;
  }
  .content {
    display: flex;
    justify-content: space-between;
  }
}

.echart-wrapper {
  flex: 1;
  span {
    color: #0092ff;
    font-size: 14px;
  }
  .echart-box {
    position: relative;
    // height: 240px;
  }
}

.rank-num {
  position: absolute;
  bottom: 0;
  left: 1%;
  padding: 0;
  height: 95%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  > li {
    transform: rotate(45deg);
    width: 16px;
    height: 16px;
    text-align: center;
    user-select: none;
  }
  span {
    display: inline-block;
    transform: rotate(-45deg);
  }
}
</style>
