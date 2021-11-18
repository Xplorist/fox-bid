<template>
  <div class="table">
    <el-table
      ref="multipleTable"
      :data="tableList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="selection" min-width="55"> </el-table-column>
      <el-table-column label="用戶名" min-width="120">
        <template slot-scope="scope">{{ scope.row.username }}</template>
      </el-table-column>
      <el-table-column prop="fctry_zone" label="所屬廠區" min-width="80">
      </el-table-column>
      <el-table-column
        prop="dept_name"
        label="單位名稱"
        min-width="260"
      ></el-table-column>
      <el-table-column
        prop="tel"
        label="固定電話"
        min-width="100"
      ></el-table-column>
      <el-table-column
        prop="email"
        label="Email"
        min-width="340"
      ></el-table-column>
      <el-table-column
        prop="create_date"
        label="申請時間"
        min-width="180"
      ></el-table-column>
      <el-table-column label="活躍" type="index" min-width="48">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.f_active === 'Y' ? '' : 'red' }">
            {{ scope.row.f_active === "Y" ? "是" : "否" }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="80">
        <template slot-scope="scope">
          <div @click="showAlert(scope.row)">
            詳細信息
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 表格數據
      tableData: [],
      // 待刪除數據的 index 列表
      multipleSelection: [],
      styleClass: ""
    };
  },
  props: {
    tableList: {
      type: Array,
      required: true
    }
  },

  methods: {
    // 展開彈框
    showAlert(val) {
      this.$emit("choosedData", val);
    },

    //
    tableRowClassName({ row }) {
      this.multipleSelection.map(item => {
        if (item.pkid === row.pkid) {
          return "del-row";
        }
        return "";
      });
    },

    // checkbox
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
      this.$emit("delSelection", this.multipleSelection);
    }
  }
};
</script>

<style lang="less" scoped>
.table {
  margin-top: 10px;
}
.el-table .del-row {
  color: red;
  font-size: 26px;
}
// 修改 table 样式
/deep/.el-table {
  th {
    background-color: #d3dfe7;
    // text-align: center;
    padding: 0;
    height: 40px;
    > .cell {
      color: #12222e;
      font-weight: 400;
    }
    .el-checkbox__input {
      &.is-checked {
        .el-checkbox__inner {
          border-color: #0092ff;
        }
      }
      .el-checkbox__inner {
        background-color: #d3dfe7;
        border: 1px solid #b5b5b5;
        &::after {
          border-color: #0092ff;
        }
      }
      &.is-indeterminate {
        .el-checkbox__inner {
          border-color: #0092ff;
          &::before {
            background-color: #0092ff;
          }
        }
      }
    }
  }
  tr {
    background-color: #eff9ff;
    &:hover {
      background-color: #e1e9ef;
      // background-color: red;
      > td {
        background-color: #e1e9ef;
      }
    }
    td {
      color: #12222e;
      // text-align: center;
      // 详细信息
      &:last-child {
        > .cell {
          color: #0092ff;
          cursor: pointer;
          user-select: none;
        }
      }
      .el-checkbox__input {
        &.is-checked {
          .el-checkbox__inner {
            border-color: #0092ff;
          }
        }
        .el-checkbox__inner {
          background-color: #eff9ff;
          border: 1px solid #b5b5b5;
          &::after {
            border-color: #0092ff;
          }
        }
      }
    }
  }
}
</style>
