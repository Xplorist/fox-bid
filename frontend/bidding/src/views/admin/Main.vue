<template>
  <div class="main">
    <div class="main-title">新用戶</div>
    <!-- 表格 -->
    <table
      class="table-list"
      style="width:100%; text-align:center; border-collapse:collapse;"
    >
      <tr>
        <th style="min-width: 80px;">
          <el-checkbox
            v-model="checkedAll"
            @change="changeCheckedAll"
            style="margin-right: 10px;"
          ></el-checkbox
          >全選
        </th>
        <th style="min-width: 80px;">用戶名</th>
        <th style="min-width: 80px;">所屬廠區</th>
        <th style="min-width: 80px;">單位名稱</th>
        <th style="min-width: 80px;">固定電話</th>
        <th>Email</th>
        <th style="min-width: 80px;">申請時間</th>
        <th style="min-width: 68px;">操作</th>
      </tr>
      <tr v-for="item in tableData" :key="item.pkid">
        <td>
          <el-checkbox v-model="item.checked"></el-checkbox>
        </td>
        <td>{{ item.username }}</td>
        <td>{{ item.fctry_zone }}</td>
        <td>{{ item.dept_name }}</td>
        <td>{{ item.tel }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.create_date }}</td>
        <td @click="(alertFlag = true), showInfo(item)">详细信息</td>
      </tr>
    </table>

    <!-- 按鈕 -->
    <div v-show="tableData.length > 0" class="opera-btn">
      <div
        class="pass"
        @click="opera('passSelect')"
        :style="{ cursor: checked ? 'pointer' : 'not-allowed' }"
      >
        <svg
          width="265px"
          height="65px"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
            style="fill:none;stroke:#0096FF;stroke-width:1"
          />
          <rect x="2" y="2" width="261" height="61" style="fill:#0096FF;" />
          <polyline
            points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
            style="fill:none;stroke:#3FB0FF;stroke-width:2;"
          />
        </svg>
        <span class="btnText">批量通過</span>
      </div>
      <div
        class="reject"
        @click="opera('rejectSelect')"
        :style="{ cursor: checked ? 'pointer' : 'not-allowed' }"
      >
        <svg
          width="265px"
          height="65px"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
            style="fill:none;stroke:#485463;stroke-width:1"
          />
          <rect x="2" y="2" width="261" height="61" style="fill:#485463;" />
          <polyline
            points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
            style="fill:none;stroke:#56616f;stroke-width:2;"
          />
        </svg>
        <span class="btnText">批量駁回</span>
      </div>
    </div>
    <div v-show="tableData.length > 0" class="adm-paging">
      <Paging
        :total="total"
        :current-page="currentPage"
        :page-size="pageSize"
        @getCurrentPage="getListDate"
        style="padding: 10px;"
      ></Paging>
    </div>
    <div
      v-show="tableData.length <= 0"
      style="text-align:center;margin-top:20px;"
    >
      暫無需要處理的申請
    </div>
    <!-- 彈框 -->
    <div class="cover" v-show="alertFlag" @click="alertFlag = false"></div>
    <div class="alertBox" v-if="detailData && alertFlag">
      <div class="alert-title" style="position:relative">
        <div>
          新用戶
          <span>{{ detailData.dept_name }}</span>
          詳細信息
        </div>
        <img
          src="../../assets/imgs/admin/closeAlertBox.png"
          alt
          style="position:absolute; top: 0; right:0; cursor:pointer"
          @click="alertFlag = false"
        />
      </div>
      <div style="margin-top:10px;">注册时间：{{ detailData.create_date }}</div>
      <div class="table-detail">
        <table>
          <tr>
            <th colspan="3">公司信息</th>
          </tr>
          <tr>
            <td style="width:68px">賬號屬性</td>
            <td style="width:10px;">:</td>
            <td>
              集团内部账号
              <span style="color:#0092FF"
                >【{{
                  detailData.send_recv_type == "recv" ? "接單方" : "發單方"
                }}】</span
              >
            </td>
          </tr>
          <tr>
            <td>賬號名稱</td>
            <td>:</td>
            <td>{{ detailData.username }}</td>
          </tr>
          <tr>
            <td>所屬廠區</td>
            <td>:</td>
            <td>{{ detailData.fctry_zone }}</td>
          </tr>
          <tr>
            <td>次集團</td>
            <td>:</td>
            <td>{{ detailData.secn_cmpy }}</td>
          </tr>
          <tr>
            <td>事業群</td>
            <td>:</td>
            <td>{{ detailData.entrps_group }}</td>
          </tr>
          <tr>
            <td>產品處</td>
            <td>:</td>
            <td>{{ detailData.pd_office }}</td>
          </tr>
          <tr>
            <td>交易法人</td>
            <td>:</td>
            <td>{{ detailData.legal_person }}</td>
          </tr>
          <tr>
            <td>費用代碼</td>
            <td>:</td>
            <td>{{ detailData.cost_code }}</td>
          </tr>
        </table>

        <table>
          <tr>
            <th colspan="3">業務信息</th>
          </tr>
          <tr>
            <td style="width:68px">业务窗口</td>
            <td style="width:10px">:</td>
            <td>{{ detailData.busis_mngr }}</td>
          </tr>
          <tr>
            <td>固定電話</td>
            <td>:</td>
            <td>{{ detailData.tel }}</td>
          </tr>
          <tr>
            <td>移動電話</td>
            <td>:</td>
            <td>{{ detailData.phone }}</td>
          </tr>
          <tr>
            <td>Email</td>
            <td>:</td>
            <td>{{ detailData.email }}</td>
          </tr>
          <tr v-if="detailData.send_recv_type == 'recv'">
            <td>加工範圍</td>
            <td>:</td>
            <td>
              <span v-for="item in detailData.recv_range_list" :key="item.pkid"
                >{{ item.name }}&nbsp;&nbsp;</span
              >
            </td>
          </tr>
          <tr>
            <td>簡介</td>
            <td>:</td>
            <td>
              <pre
                style="word-wrap: break-word;white-space: pre-wrap; font-family: inherit;margin:0;"
                >{{ detailData.summary }}</pre
              >
            </td>
          </tr>
        </table>
      </div>
      <textarea
        v-model="detailData.rejectSug"
        autofocus
        placeholder="【選填】請填寫駁回意見，如：信息有誤；公司不具有該業務等。"
        style="resize:vertical"
      ></textarea>
      <!-- 按鈕 -->
      <div class="opera-btn-detail">
        <div class="reject" @click="opera('reject')">
          <svg
            width="265px"
            height="65px"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
          >
            <polygon
              points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
              style="fill:none;stroke:#485463;stroke-width:1"
            />
            <rect x="2" y="2" width="261" height="61" style="fill:#485463;" />
            <polyline
              points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
              style="fill:none;stroke:#56616f;stroke-width:2;"
            />
          </svg>
          <span class="btnText">駁回</span>
        </div>
        <div class="pass" @click="opera('pass')">
          <svg
            width="265px"
            height="65px"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
          >
            <polygon
              points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
              style="fill:none;stroke:#0096FF;stroke-width:1"
            />
            <rect x="2" y="2" width="261" height="61" style="fill:#0096FF;" />
            <polyline
              points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
              style="fill:none;stroke:#3FB0FF;stroke-width:2;"
            />
          </svg>
          <span class="btnText">通過</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Paging from "@/components/paging/Paging";
import { query_user_au_list_pagi, user_au } from "../../api/admin";

export default {
  data() {
    return {
      tableData: [],
      // 彈窗
      alertFlag: false,
      // 全選
      checkedAll: false,
      // 單選
      checked: false,
      // 詳細信息
      detailData: {},
      total: null,
      currentPage: 1,
      pageSize: 9
    };
  },
  methods: {
    // 詳細信息
    showInfo(item) {
      this.detailData = item;
    },
    // 獲取數據
    getListDate(page = 1) {
      this.currentPage = page;
      const data = {
        pageIndex: this.currentPage,
        pageSize: this.pageSize
      };
      query_user_au_list_pagi(data).then(res => {
        if (res.code === "1") {
          this.tableData = [];
          this.total = res.t.row_total;
          for (let item of res.t.list) {
            item.checked = false;
            item.rejectSug = "";
            this.tableData.push(item);
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 全選
    changeCheckedAll() {
      if (this.checkedAll) {
        for (let item of this.tableData) {
          item.checked = true;
        }
      } else {
        for (let item of this.tableData) {
          item.checked = false;
        }
      }
    },
    // 審核
    executeOpera(id, type) {
      var rej_msg;
      for (let item of this.tableData) {
        if (item.pkid == id) {
          rej_msg = item.rejectSug;
        }
      }

      const data = {
        pkid: id,
        au_type: type, // 1通過， 2駁回
        rej_msg
      };
      user_au(data).then(res => {
        if (res.code === "1") {
          this.$message.success("操作成功！");
          this.alertFlag = false;
          this.getListDate();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 按鈕點擊事件
    opera(type) {
      switch (type) {
        case "reject":
          this.$confirm("是否【拒絕】此用戶的註冊申請？", "提示", {})
            .then(() => {
              this.executeOpera(this.detailData.pkid, 2);
            })
            .catch(() => {
              this.$message.info("已取消");
            });
          break;
        case "pass":
          this.$confirm("是否【同意】此用戶的註冊申請？", "提示", {})
            .then(() => {
              this.executeOpera(this.detailData.pkid, 1);
            })
            .catch(() => {
              this.$message.info("已取消");
            });
          break;
        case "rejectSelect":
          if (!this.checked) return;
          this.$confirm("是否【拒絕】所選用戶的註冊申請？", "提示", {})
            .then(() => {
              let data = JSON.parse(JSON.stringify(this.tableData));
              for (let item of data) {
                if (item.checked == true) {
                  this.executeOpera(item.pkid, 2);
                }
              }
            })
            .catch(() => {
              this.$message.info("已取消");
            });
          break;
        case "passSelect":
          if (!this.checked) return;
          this.$confirm("是否【同意】所選用戶的註冊申請？", "提示", {})
            .then(() => {
              let data = JSON.parse(JSON.stringify(this.tableData));
              for (let item of data) {
                if (item.checked == true) {
                  this.executeOpera(item.pkid, 1);
                }
              }
            })
            .catch(() => {
              this.$message.info("已取消");
            });
          break;
        default:
          break;
      }
    },
    // TODO 封裝點擊事件
    handleOperaChoose(isAgreen, pkidData, msg) {
      const flag = isAgreen ? 1 : 2;
      this.$confirm(msg, "提示", {})
        .then(() => {
          this.executeOpera(pkidData, flag);
        })
        .catch(() => {
          this.$message.info("已取消");
        });
    }
  },
  watch: {
    tableData: {
      handler: function() {
        let isCheckedAll = true;
        let isChecked = false;
        for (let item of this.tableData) {
          if (item.checked != true) {
            isCheckedAll = false;
          } else {
            isChecked = true;
          }
        }
        this.checkedAll = isCheckedAll;
        this.checked = isChecked;
      },
      deep: true
    }
  },
  created() {
    this.getListDate();
  },
  components: {
    Paging
  }
};
</script>

<style lang="less" scoped>
.main {
  margin: 20px;
  background-color: #eff9ff;
  box-sizing: border-box;
  color: #12222e;
  padding: 20px;
  min-height: 100%;
  min-width: 925px;
}
// 主體標題
.main-title {
  color: #12222e;
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #afc2cf;
}
// 表格
.table-list {
  font-size: 16px;
  color: #12222e;
  tr {
    transition-duration: 0.3s;
    &:hover {
      background-color: #e1e9ef;
    }
    &:nth-child(1) {
      height: 40px;
      background-color: #d3dfe7;
      border-bottom: 0;
    }
    height: 60px;
    border-bottom: 1px solid #d3dfe7;
    box-sizing: border-box;
    td:last-child {
      color: #0092ff;
      cursor: pointer;
    }
  }
  th {
    font-weight: 400;
    padding: 0 10px;
  }
}
// 按鈕
.opera-btn {
  margin-top: 20px;
  user-select: none;
  > div {
    width: 265px;
    height: 65px;
    line-height: 65px;
    color: #fff;
    font-size: 32px;
    position: relative;
    transform: scale(0.5625);
    // transform-origin: left;
    display: inline-block;
    cursor: pointer;
    .btnText {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
    &:last-child {
      transform-origin: left;
    }
  }
}
// 分頁
.adm-paging {
}
// 遮擋
.cover {
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}
.alertBox {
  z-index: 2;
  width: 900px;
  height: 650px;
  overflow-y: scroll;
  background-color: #fff;
  margin-left: 140px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  padding: 30px 40px;
  font-size: 14px;
  color: #212f3a;
  &::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }
  &::-webkit-scrollbar-track {
    border-radius: 3px;
    background: rgba(0, 0, 0, 0.06);
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.08);
  }
  /* 滚动条滑块 */
  &::-webkit-scrollbar-thumb {
    border-radius: 3px;
    background: rgba(0, 0, 0, 0.12);
    -webkit-box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.2);
  }
  .alert-title {
    font-size: 18px;
    color: #12222e;
    span {
      color: #0092ff;
    }
  }
  textarea {
    width: 100%;
    height: 80px;
    background-color: #e0e6ea;
    border: 0;
    margin-top: 10px;
    box-sizing: border-box;
    border: 1px solid rgba(0, 0, 0, 0);
    padding: 10px;
    &:focus {
      outline: none;
      border-color: #0092ff;
    }
  }
}
.table-detail {
  display: flex;
  justify-content: space-between;
  margin-top: 35px;
  table {
    width: 390px;
    overflow: hidden;
    border-collapse: collapse;
    tr {
      height: 34px;
      &:first-child {
        border-bottom: 1px solid #4c5a65;
      }
    }
    th {
      font-size: 18px;
      font-weight: 400;
      text-align: left;
    }
    td {
      line-height: 34px;
      vertical-align: top;
      &:first-child {
        text-align-last: justify;
      }
    }
  }
}
// 詳情的操作按鈕
.opera-btn-detail {
  text-align: center;
  margin-top: 10px;
  > div {
    width: 265px;
    height: 65px;
    line-height: 65px;
    color: #fff;
    font-size: 28px;
    position: relative;
    transform: scale(0.7);
    display: inline-block;
    cursor: pointer;
    .btnText {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
    &:first-child {
      transform-origin: right;
      margin-right: 10px;
    }
    &:last-child {
      transform-origin: left;
      margin-left: 10px;
    }
  }
}

/deep/.el-checkbox {
  &.is-checked {
    .el-checkbox__inner {
      border: 1px solid #0092ff;
    }
  }
  .el-checkbox__inner {
    background-color: rgba(0, 0, 0, 0);
    border: 1px dashed #b5b5b5;
    &:after {
      border-color: #0092ff;
    }
  }
  .el-checkbox__label {
    font-size: 16px;
    color: #212f3a;
  }
}
</style>
