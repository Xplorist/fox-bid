<template>
  <div>
    <el-row :gutter="20" type="flex" justify="space-between">
      <el-col :span="2"
        ><div class="grid-content">
          <el-tooltip class="item" content="狀態" placement="top">
            <el-select v-model="bill_status" placeholder="请选择狀態">
              <el-option
                v-for="item in bill_status_list"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option> </el-select
          ></el-tooltip></div
      ></el-col>
      <el-col :span="3"
        ><div class="grid-content">
          <el-tooltip class="item" content="接單方參與數" placement="top">
            <el-select v-model="recv_num_type" placeholder="请选择接單方個數">
              <el-option
                v-for="item in recv_num_type_list"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-tooltip></div
      ></el-col>
      <el-col :span="5"
        ><div class="grid-content">
          <el-input placeholder="请输入單號" v-model="bill_no" clearable>
          </el-input></div
      ></el-col>
      <el-col :span="5"
        ><div class="grid-content">
          <el-input
            placeholder="请输入發單用戶賬號"
            v-model="send_user_name"
            clearable
          >
          </el-input></div
      ></el-col>
      <el-col :span="5"
        ><div class="grid-content">
          <el-input
            placeholder="请输入接單用戶賬號"
            v-model="recv_user_name"
            clearable
          >
          </el-input></div
      ></el-col>
      <el-col :span="2"
        ><div class="grid-content z-flex z-justify-content-center">
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="handleQueryBtnClick"
            >查詢</el-button
          >
        </div></el-col
      >
    </el-row>
  </div>
</template>

<script>
import { listBillStatus } from "@/api/formInfo";

export default {
  data() {
    return {
      bill_status_list: [],
      bill_status: "", //狀態
      recv_num_type_list: [
        { id: "all", name: "全部" },
        { id: "single", name: "單個" },
        { id: "multiple", name: "多個" }
      ],
      recv_num_type: "all", // 接單方個數
      bill_no: "", //	單號（all:全部）
      send_user_name: "", // 發單用戶名
      recv_user_name: "" // 接單用戶名
    };
  },
  created() {
    this.getStatusList();
  },
  methods: {
    // 查詢按鈕點擊事件
    handleQueryBtnClick() {
      const data = [
        { name: "bill_status", val: this.bill_status },
        { name: "bill_no", val: this.bill_no.trim() },
        { name: "send_user_name", val: this.send_user_name.trim() },
        { name: "recv_user_name", val: this.recv_user_name.trim() },
        { name: "recv_num_type", val: this.recv_num_type.trim() }
      ];
      this.$emit("filtData", data);
    },
    // 獲取狀態選項
    getStatusList() {
      const data = {};
      listBillStatus(data).then(res => {
        if (res.code === "1") {
          this.bill_status_list = res.t;
          for (let item of this.bill_status_list) {
            if (item.id == "all") {
              this.bill_status = item.id;
            }
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.el-select {
  width: 100%;
}
</style>
