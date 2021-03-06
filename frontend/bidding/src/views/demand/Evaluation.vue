<template>
  <div class="evaluation">
    <div class="mainPart">
      <!-- title -->
      <div class="main-title">評價管理</div>
      <!-- 分類 -->
      <div class="main-classify">
        <div
          :class="{active:commentsType == 'received'}"
          @click="commentsType = 'received'"
        >收到的評價({{ receivedNum || 0 }})</div>
        <div
          :class="{active:commentsType == 'published'}"
          @click="commentsType = 'published'"
        >做出的評價({{ publishedNum || 0 }})</div>
      </div>
      <!-- 評分 -->
      <div class="main-rate" v-show="commentsType !== 'published'">
        <div class="single" v-for="(item, index) in rate.list" :key="index">
          <span>{{item.name}}:</span>
          <el-rate
            disabled
            show-score
            class="stars"
            text-color="#0096FF"
            score-template="{value}"
            disabled-void-color="#D3DFE7"
            :colors="rate.color"
            v-model="item.value"
          ></el-rate>
        </div>
      </div>
      <!-- 評價 -->
      <div class="main-evaluation-box">
        <!-- 單獨評語 -->
        <div class="single" v-for="(item, index) in comments" :key="index">
          <div>
            <div class="comments">{{item.text}}</div>
            <div class="date">{{item.date}}</div>
          </div>
          <div class="belong">{{item.belong}}</div>
        </div>
      </div>
      <!-- 分頁 -->
      <div class="main-paging">
        <Paging
          :total="total"
          :current-page="currentPage"
          :page-size="pageSize"
          @getCurrentPage="getListDate"
        ></Paging>
      </div>
    </div>
  </div>
</template>

<script>
import Paging from "@/components/paging/Paging";
import {
  query_send_get_eval_list,
  query_send_make_eval_list,
  query_send_get_eval_avg
} from "@/api/order";

export default {
  data: function() {
    return {
      // 評分信息
      rate: {
        // 評分顏色
        color: ["#0096FF", "#0096FF", "#0096FF"],
        // 評分類型、值、數量
        list: [{ name: "付款時效", value: 0 }, { name: "服務態度", value: 0 }]
      },
      // 总页数
      total: null,
      // 当前页
      currentPage: null,
      // 每页数目
      pageSize: 6,
      // 展现的评价
      comments: [],
      // 收到的評價
      allReceivedComments: [],
      // 做出的评价
      allPublishedComments: [],
      // 选中的侧边栏条目
      silderBarItem: "evaluation",
      // 評價類型
      commentsType: "",
      // 評價個數
      receivedNum: null,
      publishedNum: null
    };
  },
  methods: {
    // 处理分页
    handlePage(val) {
      this.currentPage = val;
      var start = (this.currentPage - 1) * this.pageSize;
      var end = start + this.pageSize;
      if (this.commentsType == "published") {
        this.comments = this.allPublishedComments.slice(start, end);
      } else {
        this.comments = this.allReceivedComments.slice(start, end);
      }
    },

    // 获取总数目
    getPage(type) {
      this.commentsType = type || "received";
      switch (this.commentsType) {
        case "published":
          this.total = this.allPublishedComments.length;
          break;
        default:
          this.total = this.allReceivedComments.length;
          break;
      }
      this.handlePage(1);
    },

    // 向後端發起請求獲取數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("demandGetEvaCurrentPage", this.currentPage);
      if (this.commentsType == "received") {
        this._query_send_get_eval_list();
      } else if (this.commentsType == "published") {
        this._query_send_make_eval_list();
      } else {
        this._query_send_get_eval_list();
        this.commentsType = "received";
        query_send_make_eval_list(this.currentPage, this.pageSize).then(res => {
          if (res.code === "1") {
            this.publishedNum = res.t.row_total;
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },

    // received
    _query_send_get_eval_list() {
      query_send_get_eval_list(this.currentPage, this.pageSize).then(res => {
        if (res.code === "1") {
          // 清空原有數據
          this.comments = [];
          this.total = res.t.row_total;
          this.receivedNum = res.t.row_total;
          const listData = res.t.send_get_eval_list;
          this.changeListData(listData);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // published
    _query_send_make_eval_list() {
      query_send_make_eval_list(this.currentPage, this.pageSize).then(res => {
        if (res.code === "1") {
          // 清空原有數據
          this.comments = [];
          this.total = res.t.row_total;
          this.publishedNum = res.t.row_total;
          const listData = res.t.send_make_eval_list;
          this.changeListData(listData);
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 修改數據格式
    changeListData(listData) {
      for (let item of listData) {
        const obj = {
          text: item.summary_text,
          date: item.create_date,
          belong: item.recv_user ? item.recv_user.dept_name : "此賬號已註銷"
        };
        this.comments.push(obj);
      }
    },

    // 獲取評分
    getRate() {
      query_send_get_eval_avg().then(res => {
        if (res.code === "1") {
          if (!res.t) return;
          this.rate.list[0].value = Number(res.t.pay_rate_avg);
          this.rate.list[1].value = Number(res.t.svc_atitu_avg);
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  },
  created() {
    // 獲取分頁數據
    let page = Number(sessionStorage.getItem("demandGetEvaCurrentPage"));
    page = page ? page : 1;
    this.getListDate(page);
    // 獲取評分
    this.getRate();
  },
  components: {
    Paging
  },
  watch: {
    commentsType: function() {
      this.getListDate();
    }
  }
};
</script>

<style lang="less" scoped>
.evaluation {
  background-color: #eff9ff;
  box-sizing: border-box;
  color: #12222e;
  padding: 30px 100px;
}
// 主體標題
.main-title {
  color: #12222e;
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #afc2cf;
}
// 分類
.main-classify {
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
  div {
    width: 200px;
    height: 36px;
    line-height: 36px;
    text-align: center;
    background-color: #c8d4de;
    color: #212f3a;
    transition-duration: 0.2s;
    &.active,
    &:hover {
      background-color: #2e6e9e;
      color: #d3dfe7;
    }
  }
}
// 評分
.main-rate {
  margin-top: 16px;
  user-select: none;
  > .single {
    display: flex;
    align-items: center;
  }
  .stars {
    margin: 5px;
    width: 160px;
  }
}
// 評價
.main-evaluation-box {
  margin-top: 20px;
  > .single {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top: 1px solid #afc2cf;
    padding: 20px 0;
    position: relative;
  }
  .comments {
    color: #212f3a;
  }
  .date {
    color: #626f7f;
    font-size: 14px;
    margin-top: 15px;
  }
  .belong {
    color: #626f7f;
    font-size: 14px;
    position: absolute;
    right: 0;
    bottom: 20px;
  }
}
</style>