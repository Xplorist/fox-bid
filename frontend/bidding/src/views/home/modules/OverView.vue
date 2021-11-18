<template>
  <div>
    <ul class="list">
      <li v-for="item in BillStatistics" :key="item.id">
        {{ item.name + "：" + item.num }}
      </li>
      <li v-for="item in usersStatistics" :key="item.id">
        {{ item.name + "：" + item.num }}
      </li>
    </ul>
  </div>
</template>

<script>
import { queryAllUsersStatistics } from "@/api/user";
import { queryBillStatistics } from "@/api/order";

export default {
  data() {
    return {
      usersStatistics: [],
      BillStatistics: []
    };
  },
  created() {
    this.getStatistics();
  },
  methods: {
    // 獲取統計信息
    getStatistics() {
      const data = {};
      queryAllUsersStatistics(data).then(res => {
        if (res.code === "1") {
          let mData = res.t;
          this.usersStatistics = [
            // {
            //   ename: "total_num",
            //   name: "註冊賬號總數",
            //   num: mData.total_num,
            //   id: "1"
            // },
            // {
            //   ename: "send_num",
            //   name: "發單方賬號總數",
            //   num: mData.send_num,
            //   id: "2"
            // },
            {
              ename: "recv_num",
              name: "競標單位數",
              num: mData.recv_num,
              id: "3"
            }
          ];
        } else {
          this.$message.error(res.msg);
        }
      });

      queryBillStatistics(data).then(res => {
        if (res.code === "1") {
          let mData = res.t;
          this.BillStatistics = [
            // {
            //   ename: "total_send_num",
            //   name: "累計發單的總筆數",
            //   num: mData.total_send_num,
            //   id: "3"
            // },
            // {
            //   ename: "total_send_money",
            //   name: "累計發單的總金額",
            //   num: mData.total_send_money,
            //   id: "2"
            // },
            {
              ename: "total_win_money",
              name: "成交總額",
              num: `￥${mData.total_win_money}`,
              id: "1"
            },
            {
              ename: "total_win_num",
              name: "成交數",
              num: mData.total_win_num + " 單",
              id: "4"
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
.list > li {
  float: left;
  margin-right: 28px;
  padding-bottom: 10px;
}
</style>
