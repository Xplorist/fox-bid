<template>
  <div class="z-flex info">
    <div v-for="item in statistics" :key="item.id" style="color: #0092ff">
      <div>
        {{ item.num }}
      </div>
      <span>{{ item.name }}</span>
    </div>
  </div>
</template>

<script>
import { queryBillStatistics } from "@/api/order";

export default {
  data() {
    return {
      // 統計信息
      statistics: {}
    };
  },
  created() {
    this.getStatistics();
  },
  methods: {
    // 獲取統計信息
    getStatistics() {
      const data = {};
      queryBillStatistics(data).then(res => {
        if (res.code === "1") {
          let mData = res.t;
          this.statistics = [
            {
              ename: "total_send_num",
              name: "累計發單的總筆數",
              num: mData.total_send_num,
              id: "3"
            },
            {
              ename: "total_send_money",
              name: "累計發單的總金額",
              num: mData.total_send_money,
              id: "2"
            },

            {
              ename: "total_win_num",
              name: "累計中標的總筆數",
              num: mData.total_win_num,
              id: "4"
            },
            {
              ename: "total_win_money",
              name: "累計中標的總金額",
              num: mData.total_win_money,
              id: "1"
            }
          ];
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.info {
  > div {
    margin-left: 60px;
    text-align: center;
    > div {
      font-size: 30px;
    }
    > span {
      font-size: 14px;
    }
  }
}
</style>
