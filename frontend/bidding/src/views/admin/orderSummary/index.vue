<template>
  <div class="order-summary">
    <div class="info-box">
      <m-info />
    </div>
    <div class="table-box">
      <div class="filt-box">
        <m-filt @filtData="getFiltData" />
      </div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <!-- 訂單信息展示 -->
            <p style="color:#0092ff;">訂單信息</p>
            <el-form
              label-position="left"
              label-width="120px"
              inline
              class="demo-table-expand"
            >
              <el-form-item
                v-for="item in orderContract"
                :key="item.val"
                :label="item.label"
              >
                <span>{{ props.row[item.val] }}</span>
              </el-form-item>
            </el-form>
            <!-- 發單賬號展示 -->
            <p style="margin-top:2rem;color:#0092ff;">發單賬號信息</p>
            <el-form
              label-position="left"
              label-width="120px"
              inline
              class="demo-table-expand"
            >
              <el-form-item
                v-for="item in sendAccountContract"
                :key="item.val"
                :label="item.label"
              >
                <span>{{ props.row.send_user[item.val] }}</span>
              </el-form-item>
            </el-form>
            <!-- 中標賬號展示 -->
            <p style="margin-top:2rem;color:#0092ff;">中標賬號信息</p>
            <el-form
              v-if="props.row.recv_user"
              label-position="left"
              label-width="120px"
              inline
              class="demo-table-expand"
            >
              <el-form-item
                v-for="item in winAccountContract"
                :key="item.val"
                :label="item.label"
              >
                <span>{{ props.row.recv_user[item.val] }}</span>
              </el-form-item>
            </el-form>
            <div v-else>暫無</div>
            <!-- 所有的競標賬號及報價展示 -->
            <p style="margin-top:2rem;color:#0092ff;">競標單位信息</p>
            <div v-if="props.row.give_recv_user_list">
              <div
                v-for="(item, index) in props.row.give_recv_user_list"
                :key="index + item"
              >
                <el-form
                  label-position="left"
                  label-width="120px"
                  inline
                  class="demo-table-expand"
                >
                  <el-form-item
                    v-for="recvItem in recvAccountContract"
                    :key="recvItem.val"
                    :label="recvItem.label"
                  >
                    <span>{{ item[recvItem.val] }}</span>
                  </el-form-item>
                  <el-form-item label="報價">
                    <span>{{ item.give_price.total_price }}</span>
                  </el-form-item>
                  <el-form-item label="是否中標">
                    <span>{{
                      item.give_price.f_win_bid === "Y" ? "是" : "否"
                    }}</span>
                  </el-form-item>
                </el-form>
                <hr />
              </div>
            </div>
            <div v-else>暫無</div>
          </template>
          <!-- 表頭 -->
        </el-table-column>
        <el-table-column label="單號" prop="bill_no"> </el-table-column>
        <el-table-column label="狀態" prop="bill_status_zh"> </el-table-column>
        <el-table-column label="發單用戶" prop="send_user.busis_mngr">
        </el-table-column>
        <el-table-column label="中標用戶" prop="recv_user.busis_mngr">
        </el-table-column>
        <el-table-column label="接受總價" prop="total_price"> </el-table-column>
        <el-table-column label="中標總價" prop="give_price.total_price">
        </el-table-column>
        <el-table-column label="時間">
          <template slot-scope="scope">
            {{ scope.row.publish_date }}
          </template>
        </el-table-column>
      </el-table>
      <div class="paging">
        <m-paging
          :total="total"
          :current-page="currentPage"
          :page-size="pageSize"
          @getCurrentPage="getListDate"
        />
      </div>
    </div>
  </div>
</template>

<script>
import MPaging from "@/components/paging/Paging";
import MInfo from "./Modules/Info";
import MFilt from "./Modules/Filt";
import { query_bill_list_not_login } from "../../../api/order";

let orderContract = [
  { val: "pd_type", label: "類型" },
  { val: "create_date", label: "創建時間" },
  { val: "bid_range", label: "開標範圍" },
  { val: "publish_date", label: "發佈時間" },
  { val: "deliver_address", label: "交貨地點" },
  { val: "deliver_date", label: "交貨時間" },
  { val: "tel", label: "聯繫電話" },
  { val: "bid_start_date", label: "競標開始時間" },
  { val: "money_type", label: "貨幣種類" },
  { val: "bid_end_date", label: "競標結束時間" },
  { val: "deliver_way", label: "交貨方式" },
  { val: "make_requ", label: "製作要求" }
];

let sendAccountContract = [
  { val: "username", label: "發單用戶" },
  { val: "tel", label: "聯繫電話" },
  { val: "fctry_zone", label: "廠區" },
  { val: "phone", label: "聯繫手機" },
  { val: "secn_cmpy", label: "次集團" },
  { val: "email", label: "Email" },
  { val: "entrps_group", label: "事業群" },
  { val: "create_date", label: "創建時間" },
  { val: "pd_office", label: "產品處" },
  { val: "latest_login_time", label: "上次登錄時間" },
  { val: "dept_name", label: "單位名稱" },
  { val: "dept_code", label: "單位代碼" },
  { val: "legal_person", label: "法人" },
  { val: "cost_code", label: "費用代碼" },
  { val: "busis_mngr", label: "業務窗口" },
  { val: "summary", label: "簡介" }
];

let winAccountContract = [
  { val: "username", label: "中標用戶" },
  { val: "tel", label: "聯繫電話" },
  { val: "fctry_zone", label: "廠區" },
  { val: "phone", label: "聯繫手機" },
  { val: "secn_cmpy", label: "次集團" },
  { val: "email", label: "Email" },
  { val: "entrps_group", label: "事業群" },
  { val: "create_date", label: "創建時間" },
  { val: "pd_office", label: "產品處" },
  { val: "latest_login_time", label: "上次登錄時間" },
  { val: "dept_name", label: "單位名稱" },
  { val: "dept_code", label: "單位代碼" },
  { val: "legal_person", label: "法人" },
  { val: "cost_code", label: "費用代碼" },
  { val: "busis_mngr", label: "業務窗口" },
  { val: "recv_range_list_str", label: "加工範圍" },
  { val: "summary", label: "簡介" }
];

let recvAccountContract = [
  { val: "username", label: "競標用戶" },
  { val: "tel", label: "聯繫電話" },
  { val: "fctry_zone", label: "廠區" },
  { val: "phone", label: "聯繫手機" },
  { val: "secn_cmpy", label: "次集團" },
  { val: "email", label: "Email" },
  { val: "entrps_group", label: "事業群" },
  { val: "dept_name", label: "單位名稱" },
  { val: "pd_office", label: "產品處" },
  { val: "busis_mngr", label: "業務窗口" }
];

let statusContract = [
  { val: "-2", status_zh: "已棄標" },
  { val: "-1", status_zh: "已流標" },
  { val: "0", status_zh: "待發佈" },
  { val: "1", status_zh: "待報價" },
  { val: "2", status_zh: "待發貨" },
  { val: "3", status_zh: "待收貨" },
  { val: "4", status_zh: "待付款" },
  { val: "5", status_zh: "待收款" },
  { val: "6", status_zh: "已完成" }
];

export default {
  data() {
    return {
      // 分頁數據
      total: 60,
      currentPage: 1,
      pageSize: 11,
      //
      tableData: [],
      // 查詢條件
      filtData: {
        bill_status: "",
        bill_no: "",
        send_user_name: "",
        recv_user_name: "",
        recv_num_type: ""
      },
      orderContract,
      sendAccountContract,
      winAccountContract,
      recvAccountContract,
      statusContract
    };
  },
  created() {
    let page = Number(sessionStorage.getItem("adminOrderSummary"));
    page = page ? page : 1;
    this.getListDate(page);
  },
  components: {
    MPaging,
    MInfo,
    MFilt
  },
  filters: {
    // bidStatus: val => {}
  },
  methods: {
    // 獲取filt數據
    getFiltData(value) {
      for (let item of value) {
        this.filtData[item.name] = item.val;
      }
      this.loadingData();
    },
    // 處理Data
    handleData(data) {
      console.log(data);
      for (let dataItem of data) {
        for (let contractItem of this.statusContract) {
          if (dataItem.bill_status === contractItem.val) {
            dataItem.bill_status_zh = contractItem.status_zh;
          }
        }

        if (dataItem.recv_user) {
          let rangeListRes = dataItem.recv_user.recv_range_list;
          let rangeList = "";
          for (let range of rangeListRes) {
            rangeList = rangeList + range.name + "、";
          }
          dataItem.recv_user.recv_range_list_str = rangeList;
        }
      }
      this.tableData = data;
    },
    // 加載Data
    loadingData() {
      const data = {
        bid_range: "集團",
        deliver_address: "all",
        deliver_date: "all",
        pd_type: "all",
        pageIndex: this.currentPage,
        pageSize: this.pageSize,
        bill_status: this.filtData.bill_status || "all",
        bill_no: this.filtData.bill_no,
        send_user_name: this.filtData.send_user_name,
        recv_user_name: this.filtData.recv_user_name,
        recv_num_type: this.filtData.recv_num_type
      };
      query_bill_list_not_login(data).then(res => {
        if (res.code === "1") {
          this.total = res.t.row_total;
          this.handleData(res.t.bill_list);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 獲取數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("adminOrderSummary", this.currentPage);
      this.loadingData();
    }
  }
};
</script>

<style lang="less">
.order-summary {
  margin: 20px;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  .info-box,
  .table-box {
    background-color: #eff9ff;
    box-sizing: border-box;
    padding: 20px;
  }
  .table-box {
    margin-top: 10px;
    flex: 1;
  }
  .paging {
  }
  // table
  .el-table th,
  .el-table tr {
    background-color: #eff9ff;
  }
  .el-table__expanded-cell {
    background-color: rgba(255, 255, 255, 0.4);
  }
  .el-table__expanded-cell:hover {
    background-color: #fff !important;
  }
  .el-table .el-table__row:hover td {
    background-color: #e1e9ef;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
}
</style>
