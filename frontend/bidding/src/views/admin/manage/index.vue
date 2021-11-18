<template>
  <div class="manage">
    <div class="man-info">
      <m-info></m-info>
    </div>
    <div class="man-list">
      <!-- title -->
      <div class="z-flex z-align-item-center title">
        <span>賬號管理</span>
      </div>
      <!-- 筛选 -->
      <div class="flit z-flex z-justify-content-between">
        <div
          v-for="(flitItem, flitIndex) in flitList"
          :key="flitItem.id"
          class="selet-admin-manage z-wid100"
          :class="flitItem.active ? 'active' : ''"
          @click="handleFlitTypeClick(flitIndex)"
        >
          {{ flitItem.scope }}:{{ flitItem.checkedItem.txt }}
          <div class="flit-item-wrapper">
            <p
              v-for="(subItem, subIndex) in flitItem.sub"
              :key="subItem.id"
              @click="handleFlitItemClick(subIndex)"
              :class="subItem.id == flitItem.checkedItem.id ? 'active' : ''"
            >
              {{ subItem.txt }}
            </p>
          </div>
        </div>
      </div>
      <!-- 表格 -->
      <div class="table">
        <m-table
          @choosedData="getChoosedData"
          @delSelection="getDelSelection"
          :tableList="tableList"
        />
      </div>
      <!-- 彈窗 -->
      <div>
        <details-alert
          :choosedData="choosedData"
          @choosedDataEmpty="setChoosedData"
        />
      </div>
      <!-- 刪除按鈕 -->
      <div class="del-btn" @click="handleDelBtnClick">
        <svg-btn
          class="svgBtn"
          :inColor="delBtn.inColor"
          :outColor="delBtn.outColor"
          :fillColor="delBtn.fillColor"
        />
        <span>刪除</span>
      </div>
      <!-- 分页 -->
      <div class="paging" style="backgroundColor:#D3DFE7">
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
import MInfo from "./Modules/Info";
import MTable from "./Modules/Table";
import DetailsAlert from "./Modules/DetailsAlert";
import SvgBtn from "@/components/operate/SvgBtn";
// import MSelect from "@/components/operate/Select";
import Paging from "@/components/paging/Paging.vue";
import { queryUserList, deleteUser } from "@/api/user";
import { listQueryConditions } from "@/api/formInfo";

export default {
  data() {
    return {
      // 點擊詳情的表格數據
      choosedData: {},
      // 刪除按鈕的顏色
      delBtn: {
        inColor: "#5B6573",
        outColor: "#485463",
        fillColor: "#485463"
      },
      // 篩選欄
      flitList: [],
      // 分頁數據
      total: null,
      currentPage: 1,
      pageSize: 8,
      // 表格數據
      tableList: [],
      // 選取的待刪除項
      delSelection: [],
      
    };
  },
  created() {
    this.getFlitList();
  },
  methods: {
    // 刪除賬號
    deleteAccuont(item) {
      const data = {
        user_pkid: item.pkid
      };
      deleteUser(data).then(res => {
        if (res.code === "1") {
          this.getListDate()
          this.$message.success("刪除成功: " + item.username);
        } else {
          this.$message.error("刪除失敗: " + item.username);
          this.$message.error(res.msg);
        }
      });
    },
    // 處理刪除按鈕點擊事件
    handleDelBtnClick() {
      if (this.delSelection.length == 0) {
        return this.$alert("當前沒有選中賬號", "提示", {
          confirmButtonText: "確定",
          callback: () => {}
        });
      }

      this.$confirm("此操作將永遠刪除選中賬號，是否繼續？", "警告", {
        type: "error"
      })
        .then(() => {
          for (let item of this.delSelection) {
            this.deleteAccuont(item);
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    },
    // 獲取選取的待刪除數據
    getDelSelection(val) {
      this.delSelection = val;
    },
    // 獲取下拉列表數據
    getFlitList() {
      const data = {};
      listQueryConditions(data).then(res => {
        if (res.code === "1") {
          this.flitList = res.t;
          let page = Number(sessionStorage.getItem("adminManCurrentPage"));
          page = page ? page : 1;
          this.getListDate(page);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 獲取列表數據
    getListDate(page = 1) {
      this.currentPage = Number(page);
      sessionStorage.setItem("adminManCurrentPage", this.currentPage);
      let send_recv_type = "",
        fctry_zone = "",
        entrps_group = "",
        pd_office = "";

      for (let item of this.flitList) {
        switch (item.val) {
          case "factory":
            fctry_zone = item.checkedItem.txt;
            break;
          case "business":
            entrps_group = item.checkedItem.txt;
            break;
          case "product":
            pd_office = item.checkedItem.txt;
            break;
          case "type":
            send_recv_type = item.checkedItem.txt;
            break;
        }
      }

      const data = {
        send_recv_type: send_recv_type,
        fctry_zone: fctry_zone,
        secn_cmpy: "全部",
        entrps_group: entrps_group,
        pd_office: pd_office,
        pageIndex: this.currentPage,
        pageSize: this.pageSize
      };
      queryUserList(data).then(res => {
        if (res.code === "1") {
          this.tableList = res.t.list;
          this.total = res.t.row_total;
        } else {
          console.log(123)
          this.$message.error(res.msg);
        }
      });
    },
    // 篩選item點擊事件
    handleFlitItemClick(index) {
      for (let i in this.flitList) {
        if (this.flitList[i].active == true) {
          this.flitList[i].checkedItem = this.flitList[i].sub[index];
          this.getListDate();
          return;
        }
      }
    },
    // 篩選type點擊事件
    handleFlitTypeClick(index) {
      if (this.flitList[index].active == true) {
        this.flitList[index].active = false;
        return;
      }
      for (let i in this.flitList) {
        this.flitList[i].active = false;
      }
      this.flitList[index].active = true;
    },
    // 設置選中的表格數據
    setChoosedData() {
      this.choosedData = {};
    },
    // 獲取選中的表格數據
    getChoosedData(val) {
      this.choosedData = val;
    },
    // 選擇分類
    toggleFlit(val) {
      for (let item of this.flitList) {
        item.active = false;
      }
      switch (val) {
        case "all":
          this.flitList[0].active = true;
          break;
        case "recv":
          this.flitList[1].active = true;
          break;
        case "send":
          this.flitList[2].active = true;
          break;
      }
    }
  },
  watch: {},
  components: {
    MInfo,
    MTable,
    DetailsAlert,
    SvgBtn,
    // MSelect,
    Paging
  }
};
</script>

<style lang="less" scoped>
.manage {
  margin: 20px;
  display: flex;
  flex-direction: column;
  .man-info,
  .man-list {
    background-color: #eff9ff;
    box-sizing: border-box;
    color: #12222e;
    padding: 20px;
  }
  .man-list {
    margin-top: 10px;
    flex: 1
  }
  .title {
    > span {
      margin-right: 1rem;
    }
    > button {
      margin: 0 1rem;
    }
  }
  .del-btn {
    display: inline-block;
    position: relative;
    margin-top: 20px;
    user-select: none;
    cursor: pointer;
    > .svgBtn {
      transform: scaleX(0.8);
    }
    > span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-size: 20px;
      color: #fff;
    }
  }
  .paging {
    background-color: #eff9ff;
  }
}
.flit {
  margin-top: 10px;
}
.selet-admin-manage {
  background-color: #d3dfe7;
  height: 40px;
  line-height: 40px;
  text-align: center;
  position: relative;
  font-size: 14px;
  color: #12222e;
  user-select: none;
  margin-top: 10px;
  cursor: pointer;
  &.active {
    background-color: #91adc0;
    box-shadow: 0px 0px 8px 0px rgba(164, 175, 183, 0.75);
    color: #ffffff;
    &::after {
      transform: rotate(180deg);
    }
    .flit-item-wrapper {
      height: auto;
    }
  }
  &::after {
    content: "";
    position: absolute;
    top: 40%;
    right: 10%;
    border-width: 6px;
    border-style: solid;
    border-color: #2e6e9e transparent transparent transparent;
    transition-duration: 0.5s;
    transform-origin: center 3px;
  }
  &:focus {
    box-shadow: 0px 0px 8px 0px rgba(164, 175, 183, 0.75);
  }
}
.flit-item-wrapper {
  position: absolute;
  color: #12222e;
  top: 40px;
  width: 100%;
  z-index: 999;
  background-color: #91adc0;
  overflow-y: scroll;
  height: 0;
  transition: height 0.5s;
  max-height: 240px;
  padding-left: 6px;
  box-sizing: border-box;
  box-shadow: 0px 0px 8px 0px rgba(164, 175, 183, 0.75);
  p {
    margin: 0;
    height: 40px;
    border-top: 1px solid #a4bccd;
    &:hover {
      background-color: #a4bccd;
    }
    &.active {
      background-color: #a4bccd;
      color: #fff;
    }
    &:last-child {
      margin-bottom: 4px;
    }
  }
  /* 设置滚动条的样式 */
  &::-webkit-scrollbar {
    width: 6px;
  }
  /* 滚动槽 */
  &::-webkit-scrollbar-track {
    border-radius: 2px;
    // border-left: 8px solid #f4f8fb;
    box-sizing: border-box;
  }
  /* 滚动条滑块 */
  &::-webkit-scrollbar-thumb {
    border-radius: 2px;
    background: rgba(0, 0, 0, 0.1);
    // border-left: 8px solid #f4f8fb;
  }
  /* 窗口不活跃时 */
  &::-webkit-scrollbar-thumb:window-inactive {
    background: rgba(0, 0, 0, 0.4);
  }
}
</style>
