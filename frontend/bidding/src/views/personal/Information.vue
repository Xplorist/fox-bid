<template>
  <div class="information">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <!-- 主體內容 -->
      <div class="main type-area">
        <SilderBar :choose-item="silderBarItem"></SilderBar>
        <div class="mainPart">
          <!-- title -->
          <div class="main_title">個人資料</div>
          <!-- form表單 -->
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <!-- 賬號屬性 -->
            <el-form-item label="賬號屬性:" class="properties">
              <span>{{form.properties}}</span>
            </el-form-item>
            <!-- 頭像 -->
            <el-form-item label="LOGO頭像:" class="portrait">
              <el-image style="width: 60px; height: 60px" :src="form.portraitUrl" fit="cover"></el-image>
              <input ref="fileInp" type="file" class="dis_none" @change="getFile" />
              <span @click="upImg">更換</span>
            </el-form-item>
            <!-- 所屬地區 -->
            <el-form-item label="所屬地區:" class="department">
              <el-select v-model="form.fctry_zone" @click.native="query_department('factory')">
                <el-option
                  v-for="item in form.fctry_zone_list"
                  :key="item.pkid"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <!-- 所屬單位 -->
            <el-form-item label="所屬單位:" class="department">
              <!-- <el-select v-model="form.secn_cmpy" @click.native="query_department('SECN_CMPY')">
                <el-option
                  v-for="(item) in form.secn_cmpy_list"
                  :key="item.pkid"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
              <el-select
                v-model="form.entrps_group"
                @click.native="query_department('ENTRPS_GROUP')"
              >
                <el-option
                  v-for="(item) in form.entrps_group_list"
                  :key="item.pkid"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
              <el-select v-model="form.pd_office" @click.native="query_department('PD_OFFICE')">
                <el-option
                  v-for="(item) in form.pd_office_list"
                  :key="item.pkid"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>-->
              <!-- <el-input v-model="form.secn_cmpy" style="display:inline;" disabled></el-input> -->
              <el-autocomplete
                class="inline-input"
                v-model.trim="form.entrps_group"
                :fetch-suggestions="querySearchGroup"
                @select="query_department('PD_OFFICE')"
                @change.native="query_department('PD_OFFICE')"
                style="margin-right:10px;"
              ></el-autocomplete>
              <el-autocomplete
                class="inline-input"
                v-model.trim="form.pd_office"
                :fetch-suggestions="querySearchOffice"
              ></el-autocomplete>
            </el-form-item>
            <!-- 賬號名稱 -->
            <el-form-item label="賬號名稱:" prop="name">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <!-- 單位代碼 -->
            <el-form-item label="單位代碼:" prop="departCode">
              <el-input
                v-model.trim="form.departCode"
              ></el-input>
                <!-- @focus="form.departName = ''" -->
                <!-- @blur="queryDeptName" -->
            </el-form-item>
            <!-- 單位名稱 -->
            <el-form-item label="單位名稱:" prop="departName">
              <el-input v-model="form.departName"></el-input>
            </el-form-item>
            <!-- 交易法人 -->
            <el-form-item label="交易法人:">
              <span>{{form.corporate}}</span>
            </el-form-item>
            <!-- 加工範圍 -->
            <el-form-item label="加工範圍:">
              <el-select multiple v-model="form.processRange" @click.native="getRange">
                <el-option
                  v-for="item in rangeOptions"
                  :key="item.pkid"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <!-- 費用代碼 -->
            <el-form-item label="費用代碼:" class="costCode" prop="costCode">
              <span>{{form.costCode}}</span>
              <el-button type="text" @click="open('costCode')">&nbsp;變更</el-button>
            </el-form-item>
            <!-- 業務經理 -->
            <el-form-item label="業務經理:" prop="manager">
              <el-input v-model.trim="form.manager"></el-input>
            </el-form-item>
            <!-- 固定電話 -->
            <el-form-item label="固定電話:" class="tel" prop="telNum">
              <el-input v-model.trim="form.telNum"></el-input>
              <!-- <el-checkbox label="在主頁中顯示" @change="form.tel.check = !form.tel.check" :checked="form.tel.check" name="type"></el-checkbox> -->
            </el-form-item>
            <!-- 移動電話 -->
            <el-form-item label="移動電話:" class="tel" prop="mobileNum">
              <el-input v-model.trim="form.mobileNum"></el-input>
            </el-form-item>
            <!-- Email -->
            <el-form-item label="公司郵箱:" class="email" prop="email">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <!-- 簡介 -->
            <el-form-item label="簡介:" class="intro" prop="intro">
              <el-input type="textarea" v-model="form.intro" autosize></el-input>
            </el-form-item>
            <!-- 按鈕 -->
            <el-form-item label=" ">
              <el-button type="primary" @click="save()">保存</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
import Top from "../../components/Top";
import Logo from "../../components/Logo";
import Footer from "../../components/Footer";
import SilderBar from "../../components/personal/SilderBar";

import { file_upload } from "@/api/file";
import { update_user_info, query_user_info_by_pkid } from "@/api/user";

import {
  ck_user_is_exist,
  query_factory_list,
  query_SECN_CMPY_list,
  query_ENTRPS_GROUP_list,
  query_PD_OFFICE_list,
  query_dept_name_by_dept_no,
  query_pd_type_list
} from "@/api/formInfo";

import { getPorImg } from "@/assets/js/getInfo";
import { mapState } from "vuex";

export default {
  data: function() {
    var validatorName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请輸入名稱"));
      } else if (value.length < 3 || value.length > 10) {
        callback(new Error("长度在 3 到 10 个字符"));
      } else {
        // 如果 value 不為空且作出更改，發起請求判斷重複
        this.userInfo.username == this.form.name
          ? callback()
          : this.ck_user(this.form.name, callback);
      }
    };
    return {
      // 侧边栏选中条目
      silderBarItem: "information",
      // 表单数据
      form: {
        // 賬號屬性
        properties: "集團內部賬號",
        // 頭像地址
        portraitUrl: require("../../assets/imgs/personal/portrait.png"),
        // 賬號名稱
        name: "",
        // 所屬地區
        fctry_zone: "",
        secn_cmpy: "",
        entrps_group: "",
        pd_office: "",

        // 所屬單位可選項【不傳】
        fctry_zone_list: [],
        secn_cmpy_list: [],
        entrps_group_list: [],
        pd_office_list: [],

        // 單位名稱
        departName: "",
        // 單位代碼
        departCode: "",
        // 交易法人
        corporate: "",
        // 費用代碼
        costCode: "",
        // 加工範圍
        processRange: [],
        // 銀行賬號
        bankName: "",
        bankAccount: "",
        // 業務經理
        manager: "",
        // 固定電話
        telNum: "",
        // 移動電話
        mobileNum: "",
        // Email
        email: "",
        // 簡介
        intro: "",
        // 內部 | 外部
        in_out_type: ""
      },
      // 限定
      rules: {
        // 賬號名稱
        name: [
          {
            required: true,
            validator: validatorName,
            trigger: "blur"
          }
        ],
        // 单位代碼
        departCode: [
          { required: true, message: "单位代碼不能为空", trigger: "blur" }
        ],
        // 单位名称
        departName: [
          {
            required: true,
            message: "未查詢到单位名称，請檢查",
            trigger: "blur"
          }
        ],
        // 费用代码
        costCode: [
          { required: true, message: "费用代码不能为空", trigger: "blur" }
        ],
        // 业务经理
        manager: [
          { required: true, message: "业务经理不能为空", trigger: "blur" }
        ],
        // 固定電話
        telNum: [
          { required: true, message: "固定電話不能为空", trigger: "blur" }
        ],
        // 移動電話
        mobileNum: [
          { required: true, message: "移動電話不能为空", trigger: "blur" }
        ],
        // email
        email: [
          { required: true, message: "email不能为空", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        // 简介
        intro: [{ max: 100, message: "最長100個字符", trigger: "blur" }]
      },
      // 加工範圍
      rangeOptions: [],
      // 彈框數據
      dialogFormVisible: false,
      alertForm: {
        bankName: "",
        bankAccount: ""
      },
      // 頭像信息
      user_pic_file: null
    };
  },
  mounted() {
    this.initData();
    this.query_department("ENTRPS_GROUP");
  },
  methods: {
    // 查詢單位名稱
    queryDeptName() {
      if (!this.form.fctry_zone) return this.$message.error("請先選擇廠區！");
      this.form.departCode = this.form.departCode.toUpperCase();
      query_dept_name_by_dept_no(
        this.form.departCode,
        this.form.fctry_zone
      ).then(res => {
        if (res.code === "1") {
          this.form.departName = res.t.dept_name;
        } else {
          this.form.departName = "";
          this.$message.error("查詢失敗，請檢查單位代碼是否輸入正確！");
        }
      });
    },

    // 查詢加工範圍
    getRange() {
      query_pd_type_list().then(res => {
        if (res.code === "1") {
          this.rangeOptions = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 查詢 廠區 單位
    query_department(type) {
      var data = "";
      switch (type) {
        case "factory":
          query_factory_list().then(res => {
            if (res.code === "1") {
              this.form.fctry_zone_list = res.t;
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
        case "SECN_CMPY":
          query_SECN_CMPY_list().then(res => {
            if (res.code === "1") {
              this.form.secn_cmpy_list = res.t;
              // 清空 事業群 產品處
              this.form.entrps_group = "";
              this.form.pd_office = "";
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
        case "ENTRPS_GROUP":
          // for (let item of this.form.secn_cmpy_list) {
          //   if (this.form.secn_cmpy == item.name) {
          //     data = item.pkid;
          //   }
          // }
          data = "Foxconn";
          query_ENTRPS_GROUP_list(data).then(res => {
            if (res.code === "1") {
              this.form.entrps_group_list = res.t;
              // 清空 產品處
              // this.form.pd_office = "";
              this.query_department("PD_OFFICE");
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
        case "PD_OFFICE":
          for (let item of this.form.entrps_group_list) {
            if (this.form.entrps_group == item.name) {
              data = item.pkid;
            }
          }
          query_PD_OFFICE_list(data).then(res => {
            if (res.code === "1") {
              this.form.pd_office_list = res.t;
            } else {
              this.$message.error(res.msg);
            }
          });
          break;
      }
    },

    // 查詢事業群
    querySearchGroup(queryString, cb) {
      var groupList = [];
      if (this.form.entrps_group_list.length > 0) {
        for (let item of this.form.entrps_group_list) {
          const obj = {
            value: item.name,
            id: item.pkid
          };
          groupList.push(obj);
        }
      }

      // 调用 callback 返回建议列表的数据
      cb(groupList);
    },

    // 查詢產品處
    querySearchOffice(queryString, cb) {
      var officeList = [];
      if (this.form.pd_office_list.length > 0) {
        for (let item of this.form.pd_office_list) {
          const obj = {
            value: item.name,
            id: item.pkid
          };
          officeList.push(obj);
        }
      }

      // 调用 callback 返回建议列表的数据
      cb(officeList);
    },

    // 查詢名稱是否重複
    ck_user(name, callback) {
      var data = { username: name };
      // 調用api接口
      ck_user_is_exist(data).then(res => {
        if (res.code == "0") {
          callback(new Error("賬戶已存在!"));
        } else {
          callback();
        }
      });
    },

    save() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.form.entrps_group || !this.form.pd_office)
            return this.$message.error("所屬單位未填寫完整");
          if (this.form.processRange.length == 0)
            return this.$message.error("請選擇加工範圍");

          this.updateInfo();
        } else {
          return false;
        }
      });
    },

    // 更新用戶信息
    updateInfo() {
      var recv_range_list = [];
      for (let item of this.form.processRange) {
        recv_range_list.push({ name: item });
      }
      const data = {
        pkid: this.userInfo.pkid,
        // 賬號名
        username: this.form.name,
        // 產區
        fctry_zone: this.form.fctry_zone,
        // 次集團
        secn_cmpy: this.form.secn_cmpy,
        // 事業群
        entrps_group: this.form.entrps_group,
        // 產品處
        pd_office: this.form.pd_office,
        // 單位名稱
        dept_name: this.form.departName,
        // 單位代碼
        dept_code: this.form.departCode,
        // 交易法人
        legal_person: this.form.corporate,
        // 費用代碼
        cost_code: this.form.costCode,
        // 加工範圍
        recv_range_list: recv_range_list,
        // 業務經理
        busis_mngr: this.form.manager,
        // 固定電話
        tel: this.form.telNum,
        // 移動電話
        phone: this.form.mobileNum,
        // email
        email: this.form.email,
        // 簡介
        summary: this.form.intro,
        // 頭像
        user_pic_file: this.user_pic_file,
        // 接單方 | 需求方 類型
        send_recv_type: this.userInfo.send_recv_type
      };
      // 調用接口上傳信息
      update_user_info(data).then(res => {
        if (res.code === "1") {
          this.$message.success("已保存");
          // 重新查詢信息存儲在 vuex
          query_user_info_by_pkid(this.userInfo.pkid).then(res => {
            if (res.code === "1") {
              this.$store.dispatch("get_userInfo", res.t);
              this.$store.dispatch("get_porImgUrl", getPorImg());
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          this.$message.error("出錯啦，稍後再試試吧");
        }
      });
    },

    // 點擊更換觸發input[type='file']的點擊事件
    upImg() {
      this.$refs.fileInp.dispatchEvent(new MouseEvent("click"));
    },

    // 獲取到 input 選中的文件信息
    getFile() {
      const inputFile = this.$refs.fileInp.files[0];
      // window 对象的 URL 对象通過 createObjectURL() 将blob或者file读取成一个url。
      const windowURl = window.URL || window.webkitURL;
      if (inputFile) {
        if (
          inputFile.type !== "image/jpeg" &&
          inputFile.type !== "image/png" &&
          inputFile.type !== "image/gif"
        ) {
          alert("請選擇圖片文件");
          return;
        }
        const url = windowURl.createObjectURL(inputFile);
        this.form.portraitUrl = url;

        // 上傳文件
        var data = new FormData();
        data.append("file", inputFile);
        data.append("file_type", "user_pic");

        file_upload(data).then(res => {
          if (res.code === "1") {
            this.user_pic_file = res.t;
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },

    // 從 vuex 從獲取數據
    initData() {
      var processRange = [];
      for (let item of this.userInfo.recv_range_list) {
        processRange.push(item.name);
      }

      // 頭像
      (this.form.portraitUrl = this.porImgUrl),
        // 賬號名稱
        (this.form.name = this.userInfo.username),
        // 廠區
        (this.form.fctry_zone = this.userInfo.fctry_zone),
        // 次集團
        // (this.form.secn_cmpy = this.userInfo.secn_cmpy),
        (this.form.secn_cmpy = "富士康次集團"),
        // 事業群
        (this.form.entrps_group = this.userInfo.entrps_group),
        // 產品處
        (this.form.pd_office = this.userInfo.pd_office),
        // 單位名稱
        (this.form.departName = this.userInfo.dept_name),
        // 單位代碼
        (this.form.departCode = this.userInfo.dept_code),
        // 法人
        (this.form.corporate = this.userInfo.legal_person),
        // 費用代碼
        (this.form.costCode = this.userInfo.cost_code),
        // 加工範圍
        (this.form.processRange = processRange),
        // 銀行名稱
        (this.form.bankName = this.userInfo.bank_name),
        // 銀行賬號
        (this.form.bankAccount = this.userInfo.bank_acunt),
        // 業務經理
        (this.form.manager = this.userInfo.busis_mngr),
        // 固定電話
        (this.form.telNum = this.userInfo.tel),
        // 移動電話
        (this.form.mobileNum = this.userInfo.phone),
        // 郵箱
        (this.form.email = this.userInfo.email),
        // 簡介
        (this.form.intro = this.userInfo.summary),
        // 內部 | 外部
        (this.form.in_out_type = this.userInfo.in_out_type);
    },

    // 彈窗消息1
    open() {
      var text = "費用代碼";
      this.$prompt("请输入" + text, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: text + "格式不正确"
      })
        .then(({ value }) => {
          if (!value.trim()) return this.$message.warning("费用代码不能为空");
          this.$message({
            type: "success",
            message: "已修改" + text + "為: " + value
          });
          this.form.costCode = value;
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },
    // 彈窗消息2
    transmit(val) {
      if (val == "cancel") {
        this.$message({
          type: "info",
          message: "取消输入"
        });
      } else if (val == "confirm") {
        this.form.bankName = this.alertForm.bankName;
        this.form.bankAccount = this.alertForm.bankAccount;
        this.$message({
          type: "success",
          message: "修改成功"
        });
      } else {
        return;
      }
    }
  },
  components: {
    Top,
    Logo,
    Footer,
    SilderBar
  },
  watch: {},
  computed: {
    ...mapState({
      userInfo: state => state.userInfo,
      porImgUrl: state => state.porImgUrl
    })
  }
};
</script>

<style lang="less" scoped>
// 主體內容
#content {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  padding-top: 24px;
  > .main {
    background: rgba(13, 28, 42, 0.2);
    box-sizing: border-box;
    padding: 20px;
    display: flex;
    justify-content: space-between;
  }
}
// 主體部分
.mainPart {
  width: 910px;
  background-color: #d3dfe7;
  box-sizing: border-box;
  padding: 30px;
}
.main_title {
  color: #12222e;
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #afc2cf;
}
/deep/ .el-form-item {
  display: flex;
  align-items: center;

  // 賬號屬性欄修改
  &.properties {
    span {
      color: #0092ff;
    }
  }
  // 頭像欄修改
  &.portrait {
    user-select: none;
    .el-form-item__content {
      display: flex;
      align-items: center;
    }
    .el-image {
      border-radius: 50%;
      margin-right: 20px;
    }
    span {
      display: inline-block;
      margin: 0 auto;
      color: #0092ff;
      cursor: pointer;
    }
  }
  // 所屬單位修改
  &.department {
    .el-select {
      margin-right: 10px;
    }
    .el-input {
      input {
        width: 160px;
      }
    }
  }
  // 費用代碼修改
  // 加工範圍修改
  // 銀行賬號修改
  &.costCode,
  &.processRange,
  &.bankAccount {
    .operation {
      color: #0092ff;
      margin-left: 10px;
      cursor: pointer;
    }
  }
  // 聯繫電話
  &.tel {
    .el-form-item__content {
      display: flex;
      align-items: center;
    }
    .el-checkbox {
      margin-left: 20px;
    }
    .el-checkbox__inner {
      background-color: #d3dfe7;
      border: 1px dashed #afc2cf;
      &:after {
        border-color: #0092ff;
      }
    }
    .el-checkbox__label {
      font-size: 16px;
      color: #212f3a;
    }
  }
  // email
  &.email {
    .el-input {
      input {
        width: 320px;
      }
    }
  }
  // 簡介
  &.intro {
    .el-textarea {
      textarea {
        width: 700px;
        background-color: #d3dfe7;
        border-color: #afc2cf;
        border-radius: 0px;
        &:focus {
          border-color: #0092ff;
        }
      }
    }
  }
}
// label全局效果修改
/deep/ .el-form-item__label {
  width: 100px !important;
  text-align: left;
  font-size: 16px;
  color: #212f3a;
  user-select: none;
}
// content全局效果修改
/deep/ .el-form-item__content {
  font-size: 16px;
  color: #212f3a;
  margin-left: 10px !important;
  .el-input {
    input {
      background-color: #d3dfe7;
      border: 1px solid #afc2cf;
      border-radius: 0px;
      width: 320px;
      &:focus {
        border-color: #0092ff;
      }
    }
  }
}

// 彈出對話框樣式
/deep/ .el-dialog {
  width: 460px;
  position: absolute;
  top: 23%;
  left: 50%;
  transform: translateX(-50%);
  .el-dialog__body {
    .el-input__inner {
      background-color: #fff;
    }
  }
}

// 消去紅*
/deep/ .el-form-item__label {
  &:before {
    content: "" !important;
    margin: 0 !important;
  }
}
</style>