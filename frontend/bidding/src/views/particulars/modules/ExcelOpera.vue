<template>
  <div class="excel-opera">
    <div class="excel-import excel-btn" @click="excelImport">
      <i class="el-icon-upload2"></i>
      <div>導入自定義選項Excel</div>
      <input
        type="file"
        ref="inputFile"
        class="display-none"
        @change="uploadFileInfo"
        accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      />
    </div>
    <div class="excel-export excel-btn" @click="excelExport">
      <i class="el-icon-download"></i>
      <div>下載Excel模板</div>
    </div>
  </div>
</template>

<script>
let XLSX = require("xlsx");
export default {
  data() {
    return {};
  },
  methods: {
    // 上傳文件信息
    uploadFileInfo() {
      let inputFile = this.$refs.inputFile.files[0];
      if (!inputFile) return;
      if (
        inputFile.type === "application/vnd.ms-excel" ||
        inputFile.type ===
          "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      ) {
        this.handleExcel(inputFile);
        this.$refs.inputFile.value = "";
      } else {
        this.$message.error("格式有誤，請上傳excel文件！");
      }
    },
    // 處理上傳的文件
    handleExcel(file) {
      let f = file;
      var reader = new FileReader();
      let $t = this;
      reader.onload = function(e) {
        var data = e.target.result;
        if ($t.rABS) {
          $t.wb = XLSX.read(btoa(this.fixdata(data)), {
            // 手动转化
            type: "base64"
          });
        } else {
          $t.wb = XLSX.read(data, {
            type: "binary"
          });
        }
        let json = XLSX.utils.sheet_to_json($t.wb.Sheets[$t.wb.SheetNames[0]]);
        $t.analyzeData(json); // analyzeData: 解析导入数据
      };
      if (this.rABS) {
        reader.readAsArrayBuffer(f);
      } else {
        reader.readAsBinaryString(f);
      }
    },
    // 解析导入数据
    analyzeData: function(data) {
      let array = [];
      for (let item of data) {
        let obj = {};
        obj.id = array.length + 1;
        obj.name = "其它";
        if (!item.hasOwnProperty("零件名")) {
          obj.selfDefineName = "";
        }
        for (let key in item) {
          switch (key) {
            case "報價（RMB/個）":
              obj.price = item[key];
              break;
            case "序號":
              // obj.id = item[key];
              break;
            case "零件名":
              obj.selfDefineName = item[key];
              break;
            case "零件數量":
              obj.num = item[key];
              break;
            default:
              return this.$message.error("文檔格式有誤，請按照模板寫入數據！");
          }
        }
        array.push(obj);
      }
      this.$emit("excelData", array);
    },
    // 導入按鈕點擊事件
    excelImport() {
      this.$refs.inputFile.dispatchEvent(new MouseEvent("click"));
    },
    // 導出按鈕點擊事件
    excelExport() {
      this.$confirm(
        "請勿修改模板首行表頭信息，並將兩條演示數據改為自己的數據！",
        "提示",
        {
          confirmButtonText: "确定",
          type: "info"
        }
      )
        .then(() => {
          const list = [
            { orderNum: "1", selfDefineName: "其它1", num: "1", price: "100" },
            { orderNum: "2", selfDefineName: "其它2", num: "3", price: "50" }
          ];
          const tHeader = ["序號", "零件名", "零件數量", "報價（RMB/個）"];
          const filterVal = ["orderNum", "selfDefineName", "num", "price"];

          require.ensure([], () => {
            const { export_json_to_excel } = require("@/excel/Export2Excel.js");
            const data = this.formatJson(filterVal, list);
            export_json_to_excel(tHeader, data, "模治檢具-自定義報價清單模板");
          });
        })
        .catch(() => {
          return;
        });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
    }
  }
};
</script>

<style lang="less" scoped>
.excel-opera {
  display: flex;
  .display-none {
    display: none;
  }
  .excel-btn {
    padding: 10px;
    margin-left: 20px;
    background-color: #9ab0c3;
    color: #fff;
    font-size: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    user-select: none;
    i {
      margin-right: 10px;
    }
  }
}
</style>
