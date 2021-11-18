import echarts from "echarts";

// ============= dashboardOption =================
const xData = [];
const yData1 = [];
const yData2 = [];

let currentYData1 = 0;
let currentYData2 = 0;

let evalDay1 = [];
let evalDay2 = [];

for (let i = 1; i <= 31; i++) {
  xData.push(i);
}

var now = new Date(2019, 12, 0);
var maxNum = now.getDate();
for (let i = 0; i < maxNum; i++) {
  const num1 = Math.floor(Math.random() * (2000 - 100) + 100);
  const num2 = Math.floor(Math.random() * (2050 - 100) + 100);

  evalDay1.push(num1);
  evalDay2.push(num2);

  currentYData1 += num1;
  currentYData2 += num2;

  yData1.push(currentYData1);
  if (i < 15) {
    yData2.push(currentYData2);
  }
}

var dashboardOption = {
  color: ["#0092FF", "#193143"],
  grid: {
    left: "0%",
    right: "0%",
    bottom: "5%",
    top: "35%",
    containLabel: true
  },
  tooltip: {
    trigger: "axis",
    formatter: function(param) {
      let str = "";
      if (param.length === 1) {
        str = `
        ${param[0].name}<br />
        上月：${param[0].value}元`;
      } else {
        str = `
        ${param[0].name}<br />
        本月：${param[1].value}元<br />
        上月：${param[0].value}元<br />
        今日：${evalDay1[param[1].dataIndex]}`;
      }
      return str;
    },
    axisPointer: {
      animation: false
    }
  },
  legend: {
    right: 0,
    top: 22,
    icon: "rect",
    textStyle: {
      color: "#96A8B6",
      fontSize: 16
    },
    data: [
      { name: "上月", textStyle: {} },
      { name: "本月", textStyle: {} }
    ]
  },
  xAxis: {
    type: "category",
    data: xData,
    splitLine: {
      show: false
    },
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77",
      fontSize: 14
    },
    axisTick: {
      alignWithLabel: true
    }
  },
  yAxis: {
    type: "value",
    boundaryGap: [0, "2%"],
    splitNumber: 4,
    splitLine: {
      show: false
    },
    axisTick: { show: false },
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77",
      fontSize: 14
    }
  },
  series: [
    {
      name: "本月",
      type: "line",
      showSymbol: false,
      hoverAnimation: false,
      // data: yData2,
      smooth: true,
      z: 10
    },
    {
      name: "上月",
      type: "line",
      showSymbol: false,
      hoverAnimation: false,
      // data: yData1,
      smooth: true,
      smoothMonotone: "x"
    }
  ]
};

// ============= manage common =================
const manageColor = ["#0092FF", "#0BB60B", "#14DCC2", "#545DC2", "#842DC2"];
const manageTooltip = {
  trigger: "item",
  formatter: "{a} <br/>{b}: {c} ({d}%)"
};
const manageSeries = {
  itemStyle: {
    normal: {
      borderColor: "#12222e",
      borderWidth: 2
    }
  },
  label: {
    normal: {
      show: false,
      position: "center"
    },
    emphasis: {
      show: false
    }
  },
  labelLine: {
    normal: {
      show: false
    }
  }
};
const manageData = [
  { value: 335, name: "治具" },
  { value: 310, name: "檢具" },
  { value: 234, name: "沖模" },
  { value: 234, name: "塑模" },
  { value: 234, name: "其它" }
];
let manageAmount = 0;
manageData.forEach(item => {
  manageAmount += item.value;
});
// ============= manageOption1 =================
var manageOption1 = {
  color: manageColor,
  title: {
    text: "option1",
    subtext: "option1",
    textAlign: "center",
    x: "33%",
    y: "35%",
    textStyle: {
      color: "#5E6C77",
      fontSize: 16,
      fontWeight: 400
    },
    subtextStyle: {
      color: "#96A8B6",
      fontSize: 30
    }
  },
  tooltip: manageTooltip,
  legend: {
    x: "60%",
    y: "center",
    icon: "rect",
    formatter: function(name) {
      let rate;
      let num;
      for (let item of manageData) {
        if (item.name === name) {
          num = item.value;
          rate = Math.round((num / manageAmount) * 100);
        }
      }
      return name + "\n" + num + "(" + rate + "%)";
    },
    textStyle: {
      color: "#5E6C77"
    }
  },
  series: [
    {
      name: "發佈類型",
      type: "pie",
      radius: ["45%", "60%"],
      center: ["35%", "50%"],
      avoidLabelOverlap: false,
      hoverOffset: 2,
      itemStyle: manageSeries.itemStyle,
      label: manageSeries.label,
      labelLine: manageSeries.labelLine
      // data: manageData
    }
  ]
};
// ============= manageOption2 =================
var manageOption2 = {
  color: manageColor,
  title: {
    text: "option",
    subtext: "option",
    textAlign: "center",
    x: "33%",
    y: "35%",
    textStyle: {
      color: "#5E6C77",
      fontSize: 16,
      fontWeight: 400
    },
    subtextStyle: {
      color: "#96A8B6",
      fontSize: 30
    }
  },
  tooltip: manageTooltip,
  legend: {
    x: "60%",
    y: "center",
    icon: "rect",
    formatter: function(name) {
      let rate;
      let num;
      for (let item of manageData) {
        if (item.name === name) {
          num = item.value;
          rate = Math.round((num / manageAmount) * 100);
        }
      }
      return name + "\n" + num + "(" + rate + "%)";
    },
    textStyle: {
      color: "#5E6C77"
    }
  },
  series: [
    {
      name: "註冊總量",
      type: "pie",
      radius: ["45%", "60%"],
      center: ["35%", "50%"],
      avoidLabelOverlap: false,
      hoverOffset: 2,
      itemStyle: manageSeries.itemStyle,
      label: manageSeries.label,
      labelLine: manageSeries.labelLine
      // data: manageData
    }
  ]
};
// ============= manageOption3 =================
var manageOption3 = {
  color: manageColor,
  title: {
    text: "option3",
    subtext: "option3",
    textAlign: "center",
    x: "33%",
    y: "35%",
    textStyle: {
      color: "#5E6C77",
      fontSize: 16,
      fontWeight: 400
    },
    subtextStyle: {
      color: "#96A8B6",
      fontSize: 30
    }
  },
  tooltip: manageTooltip,
  legend: {
    x: "60%",
    y: "center",
    icon: "rect",
    formatter: function(name) {
      let rate;
      let num;
      for (let item of manageData) {
        if (item.name === name) {
          num = item.value;
          rate = Math.round((num / manageAmount) * 100);
        }
      }
      return name + "\n" + num + "(" + rate + "%)";
    },
    textStyle: {
      color: "#5E6C77"
    }
  },
  series: [
    {
      name: "總量",
      type: "pie",
      radius: ["45%", "60%"],
      center: ["35%", "50%"],
      avoidLabelOverlap: false,
      hoverOffset: 2,
      itemStyle: manageSeries.itemStyle,
      label: manageSeries.label,
      labelLine: manageSeries.labelLine
      // data: manageData
    }
  ]
};
// ============= 廠部分析 =================
// const factoryData = {
//   name: [
//     "廠商數(家)",
//     "發單量(單)",
//     "接單數(單)",
//     "中標數(單)",
//     "接單金額(M)",
//     "發單金額(M)",
//     "中標率(%)",
//     "(被)接標率(%)"
//   ],
//   legendData: ["成都", "深圳"],
//   value1: [100, 200, 300, 180, 250, 320, 80, 80],
//   value2: [100, 200, 300, 180, 250, 320, 80, 80]
// };

var factoryOption = {
  color: ["#0BB60B", "#0096FF"],
  grid: {
    left: "0%",
    right: "0%",
    bottom: "5%",
    top: "15%",
    containLabel: true
  },
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
      shadowStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: "rgba(18,34,46,.5)" },
          { offset: 1, color: "rgba(53,88,114,.5)" }
        ])
      }
    }
  },
  legend: {
    right: 0,
    top: 0,
    // data: factoryData.legendData,
    data: "",
    icon: "rect",
    textStyle: {
      color: "#96A8B6"
    }
  },
  calculable: true,
  xAxis: [
    {
      type: "category",
      // data: factoryData.name,
      axisTick: { show: false },
      splitLine: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: "#273844"
        }
      },
      axisLabel: {
        color: "#5E6C77",
        fontSize: 12
      }
    }
  ],
  yAxis: [
    {
      type: "value",
      axisTick: { show: false },
      splitLine: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: "#273844"
        }
      },
      axisLabel: {
        color: "#5E6C77",
        fontSize: 12
      }
    },
    {
      type: "value",
      max: 100,
      min: 0,
      axisTick: { show: false },
      splitLine: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: "#273844"
        }
      },
      axisLabel: {
        color: "#5E6C77",
        fontSize: 12,
        formatter: "{value}%"
      }
    }
  ],
  series: [
    {
      // data: factoryData.value1,
      name: "成都",
      type: "bar",
      barGap: "100%",
      barWidth: 10,
      label: {
        normal: {
          show: true,
          position: ["-6", "-20"],
          distance: 1,
          formatter: ["{c}"],
          color: "#5E6C77"
        }
      }
    },
    {
      // data: factoryData.value2,
      name: "深圳",
      type: "bar",
      barGap: "100%",
      barWidth: 10,
      label: {
        normal: {
          show: true,
          position: ["-2", "-20"],
          distance: 1,
          formatter: ["{c}"],
          color: "#5E6C77"
        }
      }
    }
  ]
};

// ============= 排行榜 =================
// var chartData = [
//   28000,
//   25000,
//   20000,
//   18000,
//   17000,
//   16000,
//   15000,
//   8000,
//   8000,
//   6000
// ];
// var chartName = [
//   "夾治具加工部",
//   "Mac(I)製造二處金加廠模修",
//   "McEBG Mac（I）製造二處設備部",
//   "Mac(I)製造二處金加廠模修",
//   "品質保證處品工一部",
//   "MAC(I)觀瀾製造處沖壓金加生產部",
//   "產品開發工程處",
//   "MAC(I)精密組件智能製造推展部",
//   "MAC(I)精密組件智能製造推展部",
//   "成都工程技術中心"
// ];

var sendTopOption = {
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
      shadowStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
          { offset: 0, color: "rgba(30,65,91,.6)" },
          { offset: 1, color: "rgba(18,34,46,.3)" }
        ])
      }
    }
  },
  grid: {
    left: "2%",
    right: "10%",
    bottom: "2%",
    top: "5%",
    containLabel: true
  },
  xAxis: {
    splitLine: {
      show: false
    },
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77",
      fontSize: 12
    }
  },

  yAxis: {
    type: "category",
    // inverse: true,
    // data: chartName,
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77"
    }
  },

  series: [
    // 值
    {
      show: true,
      type: "bar",
      barGap: "-100%",
      barWidth: "20%",
      z: 2,
      itemStyle: {
        normal: {
          color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
            { offset: 0, color: "rgba(30,65,91,1)" },
            { offset: 1, color: "rgba(0,150,255,1)" }
          ])
        }
      },
      label: {
        normal: {
          show: true,
          textStyle: {
            color: "#96A8B6",
            fontSize: 14
          },
          position: "right"
          // formatter: function(data) {
          //   return chartData[data.dataIndex];
          // }
        }
      }
      // data: chartData
    }
  ]
};

var recvTopOption = {
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
      shadowStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
          { offset: 0, color: "rgba(30,65,91,.6)" },
          { offset: 1, color: "rgba(18,34,46,.3)" }
        ])
      }
    }
  },
  grid: {
    left: "2%",
    right: "10%",
    bottom: "2%",
    top: "5%",
    containLabel: true
  },
  xAxis: {
    splitLine: {
      show: false
    },
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77",
      fontSize: 12
    }
  },

  yAxis: {
    type: "category",
    // inverse: true,
    // data: chartName,
    axisLine: {
      lineStyle: {
        color: "#273844"
      }
    },
    axisLabel: {
      color: "#5E6C77"
      // show: false
    }
  },

  series: [
    // 值
    {
      show: true,
      type: "bar",
      barGap: "-100%",
      barWidth: "20%",
      z: 2,
      itemStyle: {
        normal: {
          color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
            { offset: 0, color: "rgba(30,65,91,1)" },
            { offset: 1, color: "rgba(0,150,255,1)" }
          ])
        }
      },
      label: {
        normal: {
          show: true,
          textStyle: {
            color: "#96A8B6",
            fontSize: 14
          },
          position: "right"
          // formatter: function(data) {
          //   return chartData[data.dataIndex];
          // }
        }
      }
      // data: chartData
    }
  ]
};

export {
  dashboardOption,
  manageOption1,
  manageOption2,
  manageOption3,
  factoryOption,
  sendTopOption,
  recvTopOption
};
