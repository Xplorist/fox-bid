<template>
  <div class="details-alert" v-if="info">
    <el-dialog :visible.sync="dialogFormVisible" :before-close="handleClose">
      <div class="title">
        新用戶 <span> {{ info.dept_name }} </span> 詳細信息
        <p>注册时间：{{ info.create_date }}</p>
      </div>
      <div
        v-if="
          info.send_recv_type == 'send' && Boolean(info.send_user_statistics)
        "
        class="z-flex z-justify-content-around account-data"
      >
        <div>
          <span>發單量</span>
          <div>{{ info.send_user_statistics.send_amount || 0 }}</div>
        </div>
        <div>
          <span>發單金額</span>
          <div>{{ info.send_user_statistics.send_money || 0 }}</div>
        </div>
        <div>
          <span>選標金額</span>
          <div>{{ info.send_user_statistics.pick_money || 0 }}</div>
        </div>
        <div>
          <span>棄標率</span>
          <div>
            {{
              (info.send_user_statistics.abandon_rate * 100).toFixed(0) + "%" ||
                0
            }}
          </div>
        </div>
        <div>
          <span>選標率</span>
          <div>
            {{
              (info.send_user_statistics.pick_rate * 100).toFixed(0) + "%" || 0
            }}
          </div>
        </div>
        <div>
          <span>流標率</span>
          <div>
            {{
              (info.send_user_statistics.flow_rate * 100).toFixed(0) + "%" || 0
            }}
          </div>
        </div>
        <div>
          <span>客戶評分</span>
          <div>{{ info.send_user_statistics.user_score || 0 }}</div>
        </div>
        <div>
          <span>信譽分</span>
          <div>{{ info.send_user_statistics.reputation || 0 }}</div>
        </div>
      </div>
      <div
        v-else-if="
          info.send_recv_type == 'recv' && Boolean(info.recv_user_statistics)
        "
        class="z-flex z-justify-content-around account-data"
      >
        <div>
          <span>接單量</span>
          <div>{{ info.recv_user_statistics.recv_amount || 0 }}</div>
        </div>
        <div>
          <span>報價金額</span>
          <div>{{ info.recv_user_statistics.offer_money || 0 }}</div>
        </div>
        <div>
          <span>中標金額</span>
          <div>{{ info.recv_user_statistics.win_bid_money || 0 }}</div>
        </div>
        <div>
          <span>競標成功率</span>
          <div>
            {{
              (info.recv_user_statistics.win_bid_rate * 100).toFixed(0) + "%" ||
                0
            }}
          </div>
        </div>
        <div>
          <span>客戶評分</span>
          <div>{{ info.recv_user_statistics.user_score || 0 }}</div>
        </div>
      </div>
      <div class="details z-flex z-justify-content-between">
        <table>
          <tr>
            <th colspan="3">
              公司信息
            </th>
          </tr>
          <tr>
            <td>賬號屬性</td>
            <td>:</td>
            <td>
              集團內部賬號<span class="z-cBlue"
                >【{{ info.send_recv_type == "send" ? "發佈方" : "接單方" }}】
              </span>
            </td>
          </tr>
          <tr>
            <td>賬號名稱</td>
            <td>:</td>
            <td>{{ info.username }}</td>
          </tr>
          <tr>
            <td>所屬廠區</td>
            <td>:</td>
            <td>{{ info.fctry_zone }}</td>
          </tr>
          <tr>
            <td>次集團</td>
            <td>:</td>
            <td>{{ info.secn_cmpy }}</td>
          </tr>
          <tr>
            <td>事業群</td>
            <td>:</td>
            <td>{{ info.entrps_group }}</td>
          </tr>
          <tr>
            <td>產品處</td>
            <td>:</td>
            <td>{{ info.pd_office }}</td>
          </tr>
          <tr>
            <td>交易法人</td>
            <td>:</td>
            <td>{{ info.legal_person }}</td>
          </tr>
          <tr>
            <td>費用代碼</td>
            <td>:</td>
            <td>{{ info.cost_code }}</td>
          </tr>
        </table>

        <table>
          <tr>
            <th colspan="3">
              業務信息
            </th>
          </tr>
          <tr>
            <td>業務窗口</td>
            <td>:</td>
            <td>{{ info.busis_mngr }}</td>
          </tr>
          <tr>
            <td>固定電話</td>
            <td>:</td>
            <td>{{ info.tel }}</td>
          </tr>
          <tr>
            <td>移動電話</td>
            <td>:</td>
            <td>{{ info.phone }}</td>
          </tr>
          <tr>
            <td>Email</td>
            <td>:</td>
            <td>{{ info.email }}</td>
          </tr>
          <tr v-show="info.send_recv_type == 'recv'">
            <td>加工範圍</td>
            <td>:</td>
            <td>
              <span v-for="item in info.recv_range_list" :key="item.pkid"
                >{{ item.name }}、</span
              >
            </td>
          </tr>
          <tr>
            <td>簡介</td>
            <td>:</td>
            <td>
              <p>
                {{ info.summary }}
              </p>
            </td>
          </tr>
        </table>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { query_user_info_by_pkid } from "@/api/user";

export default {
  data() {
    return {
      dialogFormVisible: false,
      info: {}
    };
  },
  props: {
    choosedData: {
      type: Object,
      required: true
    }
  },
  watch: {
    // 獲取到值就彈窗
    choosedData: function(val) {
      if (JSON.stringify(val) != "{}") {
        query_user_info_by_pkid(val.pkid).then(res => {
          if (res.code === "1") {
            this.info = res.t;
            this.dialogFormVisible = true;
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    }
  },
  methods: {
    // 關閉清除選中數據
    handleClose(done) {
      this.$emit("choosedDataEmpty");
      done();
    }
  }
};
</script>

<style lang="less" scoped>
.title {
  font-size: 18px;
  > span {
    color: #0092ff;
  }
  > p {
    color: #485463;
    font-size: 14px;
  }
}
.account-data {
  background-color: #e0e6ea;
  padding: 2rem 0;
  flex-wrap: wrap;
  > div {
    text-align: center;
    padding-top: 1.5em;
    box-sizing: border-box;
    > span {
      color: #485463;
      font-size: 18px;
    }
    > div {
      color: #0092ff;
      font-size: 48px;
      margin-top: 0.375rem;
    }
  }
}

.details {
  margin-top: 20px;
  table {
    width: 48%;
    tr {
      color: #212f3a;
      th {
        border-bottom: 1px solid #4c5a65;
        text-align: left;
        font-weight: 400;
        font-size: 18px;
        padding-bottom: 10px;
      }
      td {
        vertical-align: top;
        padding: 0.5rem 0;
        p {
          margin: 0;
          margin-top: -3px;
          line-height: 1.375rem;
        }
        &:nth-child(1) {
          width: 60px;
          text-align-last: justify;
        }
      }
    }
  }
}
/deep/.el-dialog {
  width: 60%;
  .el-dialog__header {
    padding: 0;
  }
  .el-dialog__body {
    color: #12222e;
  }
}
</style>
