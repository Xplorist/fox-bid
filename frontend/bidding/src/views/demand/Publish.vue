<template>
  <div class="publish">
    <!-- 總標題 -->
    <div class="title">發佈需求</div>
    <!-- 內容 -->
    <div class="content">
      <!-- 【基本信息】 -->
      <div class="base-info">
        <div class="title">基本信息:</div>
        <!-- 表單 -->
        <el-form ref="form1" :model="form" :rules="rules" label-width="130px">
          <el-form-item label="類型:" prop="purpose">
            <el-select
              type="select"
              v-model="form.purpose"
              @click.native="getRange"
            >
              <el-option
                v-for="item in rangeOptions"
                :key="item.pkid"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="競價開始時間:" prop="startTime">
            <el-date-picker
              v-model="form.startTime"
              type="datetime"
              placeholder="选择開始时间"
              @click.native="getStartTime"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="競價持續時間:" prop="durTime">
            <el-select
              type="select"
              v-model="form.durTime"
              @change="getEndTime"
            >
              <el-option
                v-for="item in durTimeList"
                :key="item.id"
                :value="item.val"
                :label="item.val + '小時'"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="競價結束時間:" prop="endTime">
            <el-date-picker
              v-model="form.endTime"
              type="datetime"
              disabled
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="交貨日期:" prop="deliveryTime">
            <el-date-picker
              v-model="form.deliveryTime"
              type="date"
              placeholder="选择日期"
              @click.native="getDeliTime"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="交貨地點:" prop="place">
            <!-- 帶輸入建議的輸入框 -->
            <el-autocomplete
              class="inline-input"
              v-model.trim="form.place"
              :fetch-suggestions="querySearch"
            ></el-autocomplete>
          </el-form-item>
          <el-form-item label="聯繫電話:">
            <span style="margin-right:10px;">{{ userInfo.tel }}</span>
            <el-checkbox
              label="顯示聯繫電話"
              v-model="form.telCheck"
              name="type"
            ></el-checkbox>
          </el-form-item>
          <el-form-item label="需求套數:" prop="quantity">
            <el-input
              v-model="form.quantity"
              type="number"
              oninput="this.value = this.value.replace(/[^1-9]/g, '');"
            ></el-input>
          </el-form-item>
          <el-form-item label="接受總價:" prop="price">
            <el-input
              v-model="form.price"
              type="number"
              oninput="this.value = this.value.replace(/[^0-9]/g, '');"
              style="width:120px;"
            ></el-input>
            <el-radio
              label="RMB"
              v-model="form.currencyType"
              name="type"
              style="marginLeft:10px;marginRight:10px;"
            ></el-radio>
            <el-radio
              label="USD"
              v-model="form.currencyType"
              name="type"
              style="marginLeft:10px;marginRight:10px;"
            ></el-radio>
          </el-form-item>

          <el-form-item label="接單範圍:" prop="scope">
            <el-select
              type="select"
              v-model="form.scope"
              @click.native="query_info('range')"
            >
              <el-option
                v-for="item in rangeList"
                :key="item.pkid"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="交貨模式:" prop="deliveryMode">
            <el-select type="select" v-model="form.deliveryMode">
              <el-option value="貨交工廠" selected></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <!-- 【製作要求】 -->
        <div class="requirement">
          <div class="title">製作要求:</div>
          <el-form ref="form2" :model="form" :rules="rules">
            <el-form-item prop="requirement">
              <el-input
                type="textarea"
                v-model="form.requirement"
                :autosize="{ minRows: 8 }"
                placeholder="請填寫製作要求"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
        <!-- 【圖檔附件】 -->
        <div class="accessory">
          <!-- TODO -->
          <div class="title-info">
            <div class="info-left">
              <span class="title-text">圖檔附件：</span>
              <button class="add-btn" @click="trigger()">
                <img src="../../assets/imgs/demand/add_white.png" alt />
                <span>批量添加</span>
              </button>
              <input
                type="file"
                ref="uploadFile"
                @change="getFile"
                multiple
                style="display: none"
              />
            </div>
            <div class="info-right">
              <!-- <div @click="accessFlag = true">
                <img v-show="accessFlag" src="../../assets/imgs/demand/checked.png" alt />
                <img v-show="!accessFlag" src="../../assets/imgs/demand/unchecked.png" alt />
                <span>公開</span>
              </div>
              <div @click="accessFlag = false">
                <img v-show="!accessFlag" src="../../assets/imgs/demand/checked.png" alt />
                <img v-show="accessFlag" src="../../assets/imgs/demand/unchecked.png" alt />
                <span>隱藏</span>
              </div>（隱藏后只對中標單位公開圖檔）-->
            </div>
          </div>
          <span style="color: #F56C6C;fontSize: 14px;"
            >* 請選擇體積小於100M的文檔</span
          >
          <div class="access-content">
            <!-- single -->
            <el-tooltip
              class="item"
              effect="dark"
              placement="top-start"
              v-for="(item, index) in accessList"
              :key="item.id"
              :content="'文件全稱：' + item.file_origin_name"
            >
              <div class="file-box">
                <img src="../../assets/imgs/particulars/File.png" alt />
                <span class="file-name">{{ item.file_origin_name }}</span>
                <img
                  class="close"
                  src="../../assets/imgs/demand/close.png"
                  @click="deleteFile(index)"
                />
              </div>
            </el-tooltip>
          </div>
        </div>
      </div>
    </div>
    <!-- 按鈕 -->
    <div class="btn">
      <div class="save-btn" @click="check('form1', 'form2', 'save')">
        <svg-btn inColor="56616f" outColor="#485463" fillColor="#485463" />
        <span>保存</span>
      </div>
      <div class="publish-btn" @click="check('form1', 'form2', 'publish')">
        <svg-btn inColor="26a6ff" outColor="#0096ff" fillColor="#0096ff" />
        <span>立即發佈</span>
      </div>
    </div>
  </div>
</template>

<script>
import SvgBtn from "../../components/operate/SvgBtn";

import { mapState } from "vuex";
import { file_upload, file_delete, file_delete_Bypkid } from "@/api/file";
import { saveBill, submitBill, query_bill_by_pkid } from "@/api/order";
import {
  query_deliver_address_list,
  query_bid_range_list,
  query_pd_type_list
} from "@/api/formInfo";

export default {
  data() {
    // 開始時間
    var validateST = (rule, value, callback) => {
      let nowTime = new Date().getTime();
      let validTime = new Date(nowTime - 1800000);
      if (value === "") {
        callback(new Error("請選擇開始時間！"));
      } else if (value < validTime) {
        callback(new Error("不能小於當前時間！"));
      } else {
        callback();
      }
    };

    // 交貨時間
    var validateDT = (rule, value, callback) => {
      if (this.form.endTime === "") {
        callback(new Error("請先確定競標時間！"));
      } else if (value === "") {
        callback(new Error("請選擇交貨時間！"));
      } else if (value < new Date(this.form.endTime)) {
        callback(new Error("不能小於競標結束時間！"));
      } else {
        callback();
      }
    };

    // 價格
    var validatePrice = (rule, value, callback) => {
      if (this.form.price < 0) {
        callback(new Error("不能小於零！"));
      } else {
        callback();
      }
    };

    // 數量
    var validateQuantity = (rule, value, callback) => {
      if (this.form.quantity < 1) {
        callback(new Error("不能小於一！"));
      } else {
        callback();
      }
    };

    return {
      form: {
        // 用途
        purpose: "",
        // 交貨時間
        deliveryTime: "",
        // 交貨地點
        place: "",
        // 是否顯示聯繫電話
        telCheck: true,
        // 接受總價
        price: "0",
        // 貨幣種類
        currencyType: "RMB",
        // 需求套數
        quantity: "1",
        // 目標範圍
        scope: "",
        // 開始時間
        startTime: "",
        // 持續時間
        durTime: 24,
        // 結束時間
        endTime: "",
        // 交貨模式
        deliveryMode: "貨交工廠",
        // 製作要求
        requirement: ""
      },
      // 限定
      rules: {
        purpose: [{ required: true, message: "不能為空", trigger: "blur" }],
        place: [{ required: true, message: "不能為空", trigger: "blur" }],
        quantity: [
          { required: true, message: "不能为空", trigger: "blur" },
          { validator: validateQuantity, trigger: "blur" }
        ],
        price: [
          { required: true, message: "不能为空", trigger: "blur" },
          { validator: validatePrice, trigger: "blur" }
        ],
        currencyType: [],
        scope: [{ required: true, message: "不能為空", trigger: "blur" }],
        startTime: [{ required: true, validator: validateST, trigger: "blur" }],
        deliveryTime: [
          { required: true, validator: validateDT, trigger: "blur" }
        ],
        deliveryMode: [
          { required: true, message: "不能為空", trigger: "blur" }
        ],
        requirement: [{ required: true, message: "不能為空", trigger: "blur" }]
      },
      // 持續時間列表
      durTimeList: [
        { val: 24, id: 24 },
        { val: 23, id: 23 },
        { val: 22, id: 22 },
        { val: 21, id: 21 },
        { val: 20, id: 20 },
        { val: 19, id: 19 },
        { val: 18, id: 18 },
        { val: 17, id: 17 },
        { val: 16, id: 16 },
        { val: 15, id: 15 },
        { val: 14, id: 14 },
        { val: 13, id: 13 },
        { val: 12, id: 12 },
        { val: 11, id: 11 },
        { val: 10, id: 10 },
        { val: 9, id: 9 },
        { val: 8, id: 8 },
        { val: 7, id: 7 },
        { val: 6, id: 6 },
        { val: 5, id: 5 },
        { val: 4, id: 4 },
        { val: 3, id: 3 },
        { val: 2, id: 2 },
        { val: 1, id: 1 }
      ],
      // 文件數量
      fileNum: "1",
      // 圖檔附件 公開 | 隱藏
      accessFlag: true,
      // 圖檔附件 數據
      accessList: [],
      pkid: "",
      bill_no: "",
      bill_status: "",
      send_user_pkid: "",
      recv_user_pkid: "",
      give_price_mstr_pkid: "",
      part_doc_file_rel_id: "",
      create_date: "",
      // 交货地点列表
      addressList: [],
      // 目标范围列表
      rangeList: [],
      // 類型
      rangeOptions: []
    };
  },
  methods: {
    // 獲取默認開始時間
    getStartTime() {
      this.form.startTime = new Date();
      this.getEndTime();
    },

    // 獲取默認結束時間
    getEndTime() {
      const dHour = this.form.durTime;
      if (!this.form.startTime) return;
      const startTime = new Date(this.form.startTime).getTime();
      this.form.endTime = new Date(startTime + dHour * 60 * 60 * 1000);
    },

    // 獲取默認交貨時間
    getDeliTime() {
      if (!this.form.startTime) return;
      let startTime = this.form.startTime;
      let reTime =
        ((startTime.getHours() * 60 + startTime.getMinutes()) * 60 +
          startTime.getSeconds()) *
        1000;
      let dayTime = Date.parse(startTime) - reTime;
      this.form.deliveryTime = new Date(dayTime + 172800000);
    },

    // 獲取類型範圍
    getRange() {
      query_pd_type_list().then(res => {
        if (res.code === "1") {
          this.rangeOptions = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 觸發事件
    trigger() {
      this.$refs.uploadFile.dispatchEvent(new MouseEvent("click"));
    },

    // 獲取文檔信息
    getFile() {
      var inputFiles = this.$refs.uploadFile.files;
      for (let item of inputFiles) {
        if (item.size > 100 * 1024 * 1024) {
          alert("文件：" + item.name + "，體積過大，請檢查！");
          continue;
        }
        if (this.accessList.length == 0) {
          this.uploadFile(item);
        } else {
          this.isRepetName(item);
        }
        // const fileSuffix = item.name.split(".");
        // const isDwg = fileSuffix[fileSuffix.length - 1].toUpperCase() === "DWG";
        // const isPdf = item.type === "application/pdf";
        // const isXls = item.type === "application/vnd.ms-excel";
        // const isXlsx =
        //   item.type ===
        //   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        // if (isPdf || isDwg || isXls || isXlsx) {
        //   if (this.accessList.length == 0) {
        //     this.uploadFile(item);
        //   } else {
        //     this.isRepetName(item);
        //   }
        // } else {
        //   this.$message.error("請選擇pdf/dwg/xls文檔");
        // }
      }

      // 清除 input 的 value 值，避免选择重复文件而不触发change事件
      this.$refs.uploadFile.value = null;
    },

    // 是否重名
    isRepetName(item) {
      for (let single of this.accessList) {
        if (single.file_origin_name == item.name) {
          alert(
            "發現重复文件：" + item.name + "，若需更換文件請先手動刪除原文件"
          );
          return;
        }
      }
      this.uploadFile(item);
    },

    // 上傳文件
    uploadFile(file) {
      var data = new FormData();
      data.append("file", file);
      data.append("file_type", "part_doc");
      file_upload(data).then(res => {
        if (res.code === "1") {
          // this.$message.success("上傳成功");
          this.accessList.push({
            id: JSON.stringify(new Date()),
            part_amunt: "1",
            file_save_path: res.t.file_save_path,
            file_save_name: res.t.file_save_name,
            file_origin_name: res.t.file_origin_name
          });
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 刪除文件
    deleteFile(index) {
      // 判斷是否在數據庫存有數據
      if (this.accessList[index].pkid) {
        const data = {
          pkid: this.accessList[index].pkid
        };
        file_delete_Bypkid(data).then(res => {
          if (res.code === "1") {
            this.deleteFileFromFTP(index);
          } else {
            this.$message.error(res.msg);
          }
        });
      } else {
        this.deleteFileFromFTP(index);
      }
    },

    // 刪除ftp數據
    deleteFileFromFTP(index) {
      // 刪除FTP數據
      var data = {
        file_save_path: this.accessList[index].file_save_path,
        file_save_name: this.accessList[index].file_save_name,
        file_origin_name: this.accessList[index].file_origin_name
      };
      file_delete(data).then(res => {
        if (res.code === "1") {
          this.accessList.splice(index, 1);
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 檢查
    check(nameOne, nameTwo, type) {
      var file_list = [];
      if (this.accessList.length == 0) {
        this.$message.error("請先上傳圖檔附件！");
        return;
      }
      this.$refs[nameOne].validate(valid => {
        if (valid) {
          this.$refs[nameTwo].validate(valid => {
            if (valid) {
              // 存入data
              for (let i in this.accessList) {
                var obj = {
                  id: JSON.stringify(new Date()),
                  pkid: this.accessList[i].pkid,
                  file_origin_name: this.accessList[i].file_origin_name,
                  file_save_name: this.accessList[i].file_save_name,
                  file_save_path: this.accessList[i].file_save_path,
                  part_amunt: this.accessList[i].part_amunt
                };
                file_list.push(obj);
              }
              var data = {
                pkid: this.pkid,
                bill_no: this.bill_no,
                bill_status: this.bill_status,
                send_user_pkid: this.send_user_pkid,
                recv_user_pkid: this.recv_user_pkid,
                give_price_mstr_pkid: this.give_price_mstr_pkid,
                // 用途
                pd_type: this.form.purpose,
                // 交貨時間
                deliver_date: new Date(this.form.deliveryTime),
                // 交貨地點
                deliver_address: this.form.place,
                // 聯繫電話
                tel: this.userInfo.tel,
                // 是否顯示聯繫電話
                f_show_tel: this.form.telCheck == true ? "y" : "n",
                // 接受總價
                total_price: this.form.price,
                // 幣別
                money_type: this.form.currencyType,
                // 需求套數
                amount: this.form.quantity,
                // 開標範圍
                bid_range: this.form.scope,
                // 競價開始時間
                bid_start_date: new Date(this.form.startTime),
                // 競價結束時間
                bid_end_date: new Date(this.form.endTime),
                // 交貨模式
                deliver_way: this.form.deliveryMode,
                // 製作要求
                make_requ: this.form.requirement,
                // 公開隱藏標誌
                f_open_hide: this.accessFlag == true ? "o" : "h",
                // 零件圖檔文件標關聯id
                part_doc_file_rel_id: this.part_doc_file_rel_id,
                // 創建時間
                create_date: this.create_date,
                // 文件List
                file_list: file_list
              };
              if (type == "save") {
                this.save(data);
              } else if (type == "publish") {
                let nowTime = new Date().getTime();
                let validTime = new Date(nowTime - 1800000);
                new Date(this.form.startTime) > validTime
                  ? this.pulish(data)
                  : this.$message.error("競標開始時間早於當前時間，請修改！");
              }
            } else {
              return false;
            }
          });
        } else {
          return false;
        }
      });
    },

    // 保存
    save(data) {
      saveBill(data, this.token).then(res => {
        if (res.code === "1") {
          this.$message.success("保存成功!");
          this.$router.push("/demand/order");
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 發佈
    pulish(data) {
      this.$confirm("發佈後無法更改內容，是否已確認信息無誤？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          submitBill(data).then(res => {
            if (res.code === "1") {
              this.$message.success("發佈成功!");
              this.$router.push("/demand/order");
            } else {
              this.$message.error(res.msg);
            }
          });
        })
        .catch(() => {
          this.$message.info("取消");
        });
    },

    // 查詢保存的訂單信息
    _query_bill_by_pkid(param) {
      query_bill_by_pkid(param).then(res => {
        if (res.code === "1") {
          var data = res.t;
          this.pkid = data.pkid;
          this.bill_no = data.bill_no;
          this.bill_status = data.bill_status;
          this.send_user_pkid = data.send_user_pkid;
          this.recv_user_pkid = data.recv_user_pkid;
          this.give_price_mstr_pkid = data.give_price_mstr_pkid;
          this.form.purpose = data.pd_type;
          this.form.deliveryTime = data.deliver_date;
          this.form.place = data.deliver_address;
          this.form.telCheck = data.f_show_tel == "y" ? true : false;
          this.form.price = data.total_price;
          this.form.currencyType = data.money_type;
          this.form.quantity = data.amount;
          this.form.scope = data.bid_range;
          this.form.startTime = data.bid_start_date;
          this.form.endTime = data.bid_end_date;
          this.form.deliveryMode = data.deliver_way;
          this.form.requirement = data.make_requ;
          this.accessFlag = data.f_open_hide == "o" ? true : false;
          this.part_doc_file_rel_id = data.part_doc_file_rel_id;
          this.create_date = data.create_date;
          this.accessList = data.file_list;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 查询 交货地点 | 開標範圍
    query_info(val) {
      switch (val) {
        case "address":
          query_deliver_address_list().then(res => {
            if (res.code === "1") {
              this.addressList = res.t;
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
        case "range":
          query_bid_range_list().then(res => {
            if (res.code === "1") {
              this.rangeList = res.t;
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
      }
    },

    // 輸入框 查詢交貨地點
    querySearch(queryString, cb) {
      var addressList = [];
      for (let item of this.addressList) {
        const obj = {
          value: item.name,
          id: item.pkid
        };
        addressList.push(obj);
      }

      // 调用 callback 返回建议列表的数据
      cb(addressList);
    },
    createFilter(queryString) {
      return address => {
        return (
          address.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    }
  },
  created() {
    // 判斷路由是否有 pkid
    const param = this.$router.history.current.query.pkid;
    if (param) {
      this._query_bill_by_pkid(param);
    }
  },
  mounted() {
    this.form.place = this.userInfo.fctry_zone;
    this.query_info("address");
    this.query_info("range");
  },
  computed: {
    ...mapState({
      userInfo: state => state.userInfo,
      token: state => state.token
    })
  },
  components: {
    SvgBtn
  }
};
</script>

<style lang="less" scoped>
.publish {
  background-color: #eff9ff;
  box-sizing: border-box;
  padding: 30px;
  > .title {
    height: 40px;
    border-bottom: 1px solid #d3dfe7;
    color: #0f1e29;
    font-size: 18px;
    margin-bottom: 20px;
  }
}
.content {
  display: flex;
  justify-content: space-between;
  > div:first-child {
    width: 35%;
  }
  > div:last-child {
    width: 60%;
  }
}
.base-info,
.requirement {
  > .title {
    font-size: 18px;
    color: #0092ff;
    margin-bottom: 20px;
  }
}
// 【圖檔附件】
.accessory {
  > .title-info {
    margin-top: 30px;
    margin-bottom: 10px;
    padding-bottom: 10px;
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid #d3dfe7;
  }
  .info-left {
    display: flex;
    align-items: center;
  }
  .title-text {
    color: #0092ff;
    font-size: 18px;
  }
  .add-btn {
    margin-left: 6px;
    width: 120px;
    height: 36px;
    border-radius: 2px;
    background-color: #0096ff;
    display: flex;
    align-items: center;
    justify-content: center;
    border: none;
    cursor: pointer;
    user-select: none;
    /deep/.el-upload {
      display: flex;
      align-items: center;
    }
    img {
      display: inline-block;
    }
    span {
      color: #fff;
    }
  }
  .info-right {
    display: flex;
    align-items: center;
    color: #a5b8c5;
    > div {
      color: #0f1e29;
      display: flex;
      align-items: center;
      margin-left: 12px;
      cursor: pointer;
    }
    img {
      margin-right: 4px;
    }
  }
  > .access-content {
    display: flex;
    flex-wrap: wrap;
    margin-top: 20px;
    padding-bottom: 60px;
    border-bottom: 1px solid #d3dfe7;
  }
  .file-box {
    position: relative;
    width: 33.3%;
    display: flex;
    box-sizing: border-box;
    padding: 2px 6px;
    justify-content: space-around;
    align-items: center;
    margin-bottom: 20px;
    border: 1px solid;
    border-color: rgba(0, 0, 0, 0);
    cursor: pointer;
    transition-duration: 0.3s;
    &:hover {
      border-color: #0092ff;
      .close {
        opacity: 1;
      }
    }
    input {
      margin-right: 10px;
      width: 20px;
      background-color: #eff9ff;
      border: 1px solid #d3dfe7;
      text-align: center;
    }
    .file-name {
      width: 66%;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    .close {
      display: block;
      background-repeat: no-repeat;
      position: absolute;
      top: -9px;
      right: -9px;
      opacity: 0;
      transition-duration: 0.3s;
    }
  }
}

@media (min-width: 1400px) {
  .accessory {
    .access-content {
      .file-box {
        width: 25%;
      }
    }
  }
}
@media (min-width: 1600px) {
  .accessory {
    .access-content {
      .file-box {
        width: 20%;
      }
    }
  }
}
// 按鈕
.btn {
  margin-top: 30px;
  display: flex;
  justify-content: center;
  user-select: none;
  .save-btn,
  .publish-btn {
    color: #fff;
    position: relative;
    transform: scale(0.8);
    font-size: 24px;
    cursor: pointer;
    > span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
}
// 修改el-form表單樣式
.el-form-item {
  margin: 16px 0;
}
/deep/ .el-form-item__label {
  text-align: left;
  font-size: 16px;
  color: #212f3a;
  text-align: right;
  user-select: none;
  // text-justify: distribute-all-lines; /*ie6-8*/
  // text-align-last: justify; /* ie9*/
  // -moz-text-align-last: justify; /*ff*/
  // -webkit-text-align-last: justify; /*chrome 20+*/
}
/* content全局效果修改 */
/deep/ .el-form-item__content {
  font-size: 16px;
  color: #0f1e29;
  .el-select,
  .el-autocomplete {
    width: 100%;
  }
  .el-input {
    width: 100%;
    input {
      background-color: #eff9ff;
      border: 1px solid #d3dfe7;
      border-radius: 0px;
      height: 36px;
      &:focus {
        border-color: #0092ff;
      }
    }
  }
  .el-textarea {
    textarea {
      background-color: #eff9ff;
      border: 1px solid #d3dfe7;
      border-radius: 0px;
      &:focus {
        border-color: #0092ff;
      }
    }
  }
  .el-checkbox {
    margin-left: 20px;
    .el-checkbox__inner {
      background-color: #eff9ff;
      border: 1px dashed #d3dfe7;
      &:after {
        border-color: #0092ff;
      }
    }
    .el-checkbox__label {
      font-size: 16px;
      color: #212f3a;
    }
  }
  .el-radio {
    .el-radio__inner {
      background-color: #eff9ff;
      &::after {
        background-color: #0092ff;
      }
    }
  }
}
</style>
