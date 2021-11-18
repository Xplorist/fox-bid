<template>
  <div
    id="echartsId"
    :ref="chartRef"
    style="width:100%; height:100%; min-height:250px"
  ></div>
</template>

<script>
export default {
  data() {
    return {
      chart: ""
    };
  },
  props: ["option"],
  methods: {
    drawChart() {
      this.chart = this.$echarts.init(this.$refs[this.chartRef]);
      this.chart.setOption(this.option);

      window.addEventListener("resize", () => {
        setTimeout(this.chart.resize(), 100);
      });
    }
  },
  mounted() {
    this.drawChart();
  },
  computed: {
    chartRef() {
      let time = new Date();
      return "chart" + this.$md5(time.toLocaleDateString());
    }
  },
  watch: {
    option: {
      handler(newVal, oldVal) {

        if (this.chart) {
          if (newVal) {
            this.chart.setOption(newVal);
          } else {
            this.chart.setOption(oldVal);
          }
        } else {
          this.drawChart();
        }
      },
      deep: true //对象内部属性的监听
    }
  }
};
</script>

<style></style>
