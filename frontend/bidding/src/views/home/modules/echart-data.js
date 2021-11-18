let option = {
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
      shadowStyle: {
        color: "rgba(215, 239, 255, .3)"
      }
    },
    formatter: "{c}"
  },
  grid: {
    left: "10%",
    bottom: "0",
    top: "5%",
    right: "18%"
    // containLabel: true
  },
  xAxis: {
    show: false
  },
  yAxis: {
    show: false,
    type: "category",
    data: [0, 1, 2, 3, 4]
  },

  series: [
    {
      show: true,
      type: "bar",
      // barGap: "-100%",
      barWidth: "20%",
      itemStyle: {
        normal: {
          color: "transparent"
        }
      },
      label: {
        normal: {
          show: true,
          textStyle: {
            color: "#CFE6F6",
            fontSize: 14
          },
          position: [0, "-6"],
          formatter: "{b}"
        }
      }
    },
    {
      show: true,
      type: "bar",
      // barGap: "-100%",
      barWidth: "20%",
      itemStyle: {
        normal: {
          color: "#0096FF"
        }
      },
      label: {
        normal: {
          show: true,
          textStyle: {
            color: "#FFFFFF",
            fontSize: 14
          },
          position: "right"
        }
      }
    }
  ]
};

export { option };
