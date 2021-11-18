<template>
  <div class="entrySec">
    <Top></Top>
    <Logo></Logo>
    <section class="ent-sec-cont">
      <!-- 主體內容 -->
      <div class="main type-area">
        <!-- 信息填寫框 -->
        <div class="form-box">
          <div class="form-title">
            <span>填寫資料</span>
            <div class="tit-describe">
              <span>我是{{ category == "recv" ? "接單方" : "需求方" }}</span>
              <span v-if="category == 'recv'">承接模治檢具加工、設計。</span>
              <span v-else>發佈模治檢具加工、設計需求。</span>
            </div>
          </div>
          <div class="form-info">
            <!-- 左表單 -->
            <div class="form-left">
              <!-- form表單 -->
              <el-form
                ref="formOne"
                status-icon
                :model="form"
                :rules="rules"
                label-width="80px"
              >
                <!-- LOGO -->
                <el-form-item label="LOGO:" class="logo">
                  <el-image
                    style="width: 40px; height: 40px"
                    :src="form.logoUrl"
                    fit="cover"
                  ></el-image>
                  <span @click="upImg">上傳</span>
                  <input
                    ref="fileInp"
                    type="file"
                    class="dis_none"
                    @change="getFile"
                  />
                  <span>200px*200px以上，不超過1M</span>
                </el-form-item>
                <!-- 賬號名稱 -->
                <el-form-item label="賬號名稱:" class="name" prop="name">
                  <el-input v-model="form.name" auto-complete="off"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item label="密码:" class="psd" prop="psd">
                  <el-input v-model="form.psd" show-password></el-input>
                </el-form-item>
                <!-- 确认密码 -->
                <el-form-item label="确认密码:" class="conpsd" prop="conpsd">
                  <el-input v-model="form.conpsd" show-password></el-input>
                </el-form-item>
                <!-- 所屬廠區 -->
                <el-form-item label="所屬廠區:" class="factory" prop="factory">
                  <el-select
                    v-model="form.factory"
                    @click.native="_query_factory_list()"
                    placeholder
                  >
                    <el-option
                      v-for="(item, index) in factory_list"
                      :key="item.list_order"
                      :label="item.name || index"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <!-- 事業群 -->
                <el-form-item
                  label="事業群:"
                  class="bussiness"
                  prop="bussiness"
                >
                  <!-- 帶輸入建議的輸入框 -->
                  <el-autocomplete
                    class="inline-input"
                    v-model.trim="form.bussiness"
                    :fetch-suggestions="querySearchGroup"
                    @select="_query_PD_OFFICE_list()"
                    @change.native="_query_PD_OFFICE_list()"
                  ></el-autocomplete>
                </el-form-item>
                <!-- 處 -->
                <el-form-item label="產品處:" class="bureau" prop="bureau">
                  <!-- 帶輸入建議的輸入框 -->
                  <el-autocomplete
                    class="inline-input"
                    v-model.trim="form.bureau"
                    :fetch-suggestions="querySearchOffice"
                  ></el-autocomplete>
                </el-form-item>
                <!-- 單位代碼 -->
                <el-form-item
                  label="單位代碼:"
                  class="departCode"
                  prop="departCode"
                >
                  <el-input v-model.trim="form.departCode"></el-input>
                </el-form-item>
                <!-- 單位名稱 -->
                <el-form-item
                  label="單位名稱:"
                  class="departName"
                  prop="departName"
                >
                  <el-input v-model="form.departName"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <!-- 右表單 -->
            <div class="form-right">
              <el-form
                ref="formTwo"
                status-icon
                :model="form"
                :rules="rules"
                label-width="80px"
              >
                <!-- 交易法人 -->
                <el-form-item
                  label="交易法人:"
                  class="corporate"
                  prop="corporate"
                >
                  <el-autocomplete
                    v-model.trim="form.corporate"
                    :fetch-suggestions="querySearchLegalList"
                    @select="handleSelectLegal"
                  ></el-autocomplete>
                </el-form-item>
                <!-- 費用代碼 -->
                <el-form-item
                  label="費用代碼:"
                  class="costCode"
                  prop="costCode"
                >
                  <el-input v-model.trim="form.costCode"></el-input>
                </el-form-item>
                <!-- 加工範圍 -->
                <el-form-item
                  v-if="category != 'send'"
                  label="加工範圍:"
                  class="processRange"
                  prop="processRange"
                >
                  <el-select
                    multiple
                    v-model="form.processRange"
                    @click.native="getRange"
                    placeholder="請選擇加工範圍"
                  >
                    <el-option
                      v-for="item in rangeOptions"
                      :key="item.pkid"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <!-- 業務窗口 -->
                <el-form-item label="業務窗口:" class="manager" prop="manager">
                  <el-input v-model.trim="form.manager"></el-input>
                </el-form-item>
                <!-- 固定電話 -->
                <el-form-item label="固定電話:" class="tel" prop="tel">
                  <el-input
                    v-model.trim="form.tel"
                    placeholder="廠區內電話"
                  ></el-input>
                </el-form-item>
                <!-- 移動電話 -->
                <el-form-item label="移動電話:" class="tel" prop="mobile">
                  <el-input v-model.trim="form.mobile"></el-input>
                </el-form-item>
                <!-- Email -->
                <el-form-item label="公司郵箱:" class="email" prop="email">
                  <el-input
                    v-model="form.email"
                    placeholder="請輸入內部Email"
                  ></el-input>
                </el-form-item>
                <!-- 簡介 -->
                <el-form-item label="簡介:" class="intro" prop="intro">
                  <el-input type="textarea" v-model="form.intro"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <!-- 按鈕 -->
          <div class="form-chooseBtn" @click="submit('formOne', 'formTwo')">
            <router-link to>
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
                <rect
                  x="2"
                  y="2"
                  width="261"
                  height="61"
                  style="fill:#0096FF;"
                />
                <polyline
                  points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
                  style="fill:none;stroke:#3FB0FF;stroke-width:2;"
                />
              </svg>
              <span>提交審核</span>
            </router-link>
          </div>
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

// 引入請求
import {
  ck_user_is_exist,
  query_factory_list,
  query_ENTRPS_GROUP_list,
  query_PD_OFFICE_list,
  query_pd_type_list,
  query_dept_name_by_dept_no,
  register,
  fuzzy_search_legal_person
} from "@/api/formInfo";
// query_SECN_CMPY_list,
import { file_upload } from "@/api/file";

export default {
  data: function() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("請再次輸入密碼"));
      } else if (value !== this.form.psd) {
        callback(new Error("兩次輸入密碼不一致!"));
      } else {
        callback();
      }
    };
    var validatorName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请輸入名稱"));
      } else if (value.length < 4 || value.length > 16) {
        callback(new Error("長度在 4 到 16 個字符"));
      } else {
        // 如果 value 不為空，發起請求判斷重複
        this.ck_user(this.form.name, callback);
      }
    };
    return {
      // 表單數據
      form: {
        // logo url
        logoUrl: require("../../assets/imgs/merchant/LOGO.png"),
        // 賬號名稱
        name: "",
        // 密码
        psd: "",
        // 确认密码
        conpsd: "",
        // 廠區
        factory: "",
        // 次集團
        group: "富士康次集團",
        // 事業群
        bussiness: "",
        currentBussiness: "",
        // 產品處
        bureau: "",
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
        // 業務窗口
        manager: "",
        // 固定電話
        tel: "",
        // 移動電話
        mobile: "",
        // email
        email: "",
        // 簡介
        intro: ""
      },
      // 表單校驗
      rules: {
        // 賬號名稱
        name: [
          {
            required: true,
            validator: validatorName,
            trigger: "blur"
          }
        ],
        // 密码
        psd: [
          { required: true, message: "请输入密碼", trigger: "blur" },
          { min: 4, max: 16, message: "密碼長度為4-16" }
        ],
        // 确认密码
        conpsd: [{ required: true, validator: validatePass, trigger: "blur" }],
        // 廠區
        factory: [{ required: true, message: "请選擇廠區", trigger: "change" }],
        // 次集團
        group: [{ required: true, message: "请輸入次集團", trigger: "change" }],
        // 事業群
        bussiness: [
          { required: true, message: "请選擇事業群", trigger: "change" }
        ],
        // 產品處
        bureau: [
          { required: true, message: "请選擇事業群", trigger: "change" }
        ],
        // 單位代碼
        departCode: [
          { required: true, message: "單位代碼不能為空", trigger: "blur" }
        ],
        // 單位名
        departName: [
          {
            required: true,
            message: "单位名称不能為空",
            trigger: "blur"
          }
        ],
        // 交易法人
        corporate: [
          { required: true, message: "交易法人不能為空", trigger: "blur" }
        ],
        // 費用代碼
        costCode: [
          { required: true, message: "費用代碼不能為空", trigger: "blur" }
        ],
        // 加工範圍
        processRange: [
          { required: true, message: "加工範圍不能為空", trigger: "blur" }
        ],
        // 業務窗口
        manager: [
          { required: true, message: "業務窗口不能為空", trigger: "blur" }
        ],
        // 固定電話
        tel: [
          { required: true, message: "號碼不能为空", trigger: "blur" }
          // { type: "number", message: "號碼必须为数字值" }
        ],
        // 移動電話
        mobile: [{ required: true, message: "號碼不能为空", trigger: "blur" }],
        // email
        email: [
          { required: true, message: "邮箱地址不能為空", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        // 簡介
        intro: [{ max: 100, message: "最長100個字符", trigger: "blur" }]
      },
      // 加工範圍
      rangeOptions: [],
      // url中獲取 接單方 | 需求方
      category: "",
      // 查詢回來的廠區列表
      factory_list: [],
      // 查詢回來的次集團列表
      SECN_CMPY_list: [],
      // 查詢回來的事業群列表
      ENTRPS_GROUP_list: [],
      // 查詢回來的產品處列表
      PD_OFFICE_list: [],
      // 存儲頭像信息
      user_pic_file: null
    };
  },
  created() {
    let categoryVal = sessionStorage.getItem("merchangtCategory");
    if (categoryVal) {
      this.category = categoryVal;
    } else {
      this.$router.push("/merchant");
    }

    this._query_ENTRPS_GROUP_list();
  },
  methods: {
    // 拉取法人列表
    querySearchLegalList(queryString, cb) {
      if (queryString == "") return cb([]);
      const data = { legal_person: queryString };
      fuzzy_search_legal_person(data).then(res => {
        if (res.code === "1") {
          if (res.t.length <= 0) return cb([{ value: "暫無數據" }]);
          let resData = res.t;
          let list = [];
          for (let item of resData) {
            const obj = {
              value: item.legal_person,
              pkid: item.pkid
            };
            list.push(obj);
          }
          cb(list);
        } else {
          cb([{ value: "無數據" }]);
        }
      });
    },
    handleSelectLegal() {},
    // 查詢單位名稱
    queryDeptName() {
      if (!this.form.factory) return this.$message.error("請先選擇廠區！");
      this.form.departCode = this.form.departCode.toUpperCase();
      query_dept_name_by_dept_no(this.form.departCode, this.form.factory).then(
        res => {
          if (res.code === "1") {
            this.form.departName = res.t.dept_name;
          } else {
            this.form.departName = "";
            this.$message.error("查詢失敗，請檢查單位代碼輸入正確！");
          }
        }
      );
    },

    // 獲取加工範圍
    getRange() {
      query_pd_type_list().then(res => {
        if (res.code === "1") {
          this.rangeOptions = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 觸發 ref='fileInp' 的點擊事件
    upImg() {
      this.$refs.fileInp.dispatchEvent(new MouseEvent("click"));
    },

    getFile() {
      // 獲取到 input 選中的文件信息
      const inputFile = this.$refs.fileInp.files[0];

      // window 对象的 URL 对象通過 createObjectURL() 将blob或者file读取成一个url。
      const windowURL = window.URL || window.webkitURL;
      if (inputFile) {
        if (
          inputFile.type !== "image/jpeg" &&
          inputFile.type !== "image/png" &&
          inputFile.type !== "image/gif"
        ) {
          alert("請上傳圖片文件");
          return;
        }
        const dataURL = windowURL.createObjectURL(inputFile);
        this.form.logoUrl = dataURL;

        // 上傳至服務器
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

    // 查询厂区列表
    // 廠區
    // factory: "",
    // 次集團
    // group: "",
    // 事業群
    // bussiness: "",
    // 產品處
    // bureau: "",
    _query_factory_list() {
      query_factory_list().then(res => {
        if (res.code === "1") {
          this.factory_list = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 查詢次集團信息
    _query_SECN_CMPY_list() {
      // query_SECN_CMPY_list().then(res => {
      //   if (res.code === "1") {
      //     this.SECN_CMPY_list = res.t;
      //     this.form.bussiness = "";
      //     this.form.bureau = "";
      //   } else {
      //     this.$message.error(res.msg);
      //   }
      // });
    },

    // 查詢事業群列表信息
    _query_ENTRPS_GROUP_list() {
      var data = "";
      // for (let item of this.SECN_CMPY_list) {
      //   if (item.name == this.form.group) {
      //     data = item.pkid;
      //   }
      // }
      data = "Foxconn";
      query_ENTRPS_GROUP_list(data).then(res => {
        if (res.code === "1") {
          this.ENTRPS_GROUP_list = res.t;
          this._query_PD_OFFICE_list();
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    querySearchGroup(queryString, cb) {
      var groupList = [];
      if (this.ENTRPS_GROUP_list.length > 0) {
        for (let item of this.ENTRPS_GROUP_list) {
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
    // 查詢產品處列表信息
    _query_PD_OFFICE_list() {
      var data = "";
      for (let item of this.ENTRPS_GROUP_list) {
        if (item.name == this.form.bussiness) {
          data = item.pkid;
        }
      }
      if (!data) {
        return (this.PD_OFFICE_list = []);
      }
      query_PD_OFFICE_list(data).then(res => {
        if (res.code === "1") {
          this.PD_OFFICE_list = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    querySearchOffice(queryString, cb) {
      var officeList = [];
      if (this.PD_OFFICE_list.length > 0) {
        for (let item of this.PD_OFFICE_list) {
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

    // 驗證
    submit(nameOne, nameTwo) {
      if (!this.category) return this.$message.error("請選擇賬號類型！");
      this.$refs[nameOne].validate(valid => {
        if (valid) {
          this.$refs[nameTwo].validate(valid => {
            if (valid) {
              this.upload();
            } else {
              return false;
            }
          });
        } else {
          return false;
        }
      });
    },

    // 提交
    upload() {
      let recv_range_list = [];
      for (let item of this.form.processRange) {
        recv_range_list.push({ name: item });
      }

      let obj = {
        username: this.form.name,
        password: this.$md5(this.form.psd),
        in_out_type: "in",
        // 接單方 | 需求方
        send_recv_type: this.category,
        fctry_zone: this.form.factory,
        secn_cmpy: this.form.group,
        entrps_group: this.form.bussiness,
        pd_office: this.form.bureau,
        dept_name: this.form.departName,
        dept_code: this.form.departCode,
        legal_person: this.form.corporate,
        cost_code: this.form.costCode,
        // recv_mnufc_range: this.form.processRange,
        bank_name: "",
        bank_acunt: "",
        busis_mngr: this.form.manager,
        tel: this.form.tel,
        phone: this.form.mobile,
        email: this.form.email,
        summary: this.form.intro,
        user_pic_file: this.user_pic_file,
        recv_range_list: recv_range_list
      };

      register(obj).then(res => {
        if (res.code === "1") {
          this.$message.success("信息提交成功，請等待管理員審核！");
          this.$router.push("/login");
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  },
  components: {
    Top,
    Logo,
    Footer
  }
};
</script>

<style lang="less" scoped>
.ent-sec-cont {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  padding-top: 24px;
  // 邊界效果
  > .main {
    background: rgba(13, 28, 42, 0.2);
    box-sizing: border-box;
    padding: 20px;
    position: relative;
  }
  // 信息填寫框
  .form-box {
    background-color: #d3dfe7;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  // 信息框頂部
  .form-title {
    margin-top: 60px;
    text-align: center;
    > span {
      color: #0096ff;
      font-size: 18px;
    }
  }
  // 描述
  .tit-describe {
    margin-top: 26px;
    background: #c6d4de;
    text-align: center;
    width: 960px;
    height: 60px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    span:nth-child(1) {
      color: #12222e;
      font-size: 20px;
    }
    span:nth-child(2) {
      color: #50616f;
      font-size: 14px;
    }
  }
  // 信息框主體
  .form-info {
    width: 960px;
    display: flex;
    justify-content: space-between;
    margin-top: 40px;
    .form-left,
    .form-right {
      width: 50%;
    }
  }
  .departName {
    /deep/ .el-input.is-disabled .el-input__inner {
      color: #606266;
    }
  }
  // 修改 element 表格樣式
  // 單獨列
  /deep/ .el-form-item {
    display: flex;
    align-items: center;
    // logo
  }
  /deep/ .el-form-item.logo {
    .el-form-item__content {
      width: 326px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    span:nth-child(2) {
      color: #2e6e9e;
      font-size: 16px;
      border: 1px solid #afc2cf;
      display: inline-block;
      width: 58px;
      height: 24px;
      text-align: center;
      line-height: 24px;
      border-radius: 13px;
    }
    span:nth-child(3) {
      color: #7d919f;
      font-size: 14px;
    }
  }
  // label全局效果修改
  /deep/ .el-form-item__label {
    width: 100px !important;
    text-align: right;
    font-size: 16px;
    color: #212f3a;
    padding: 0;
  }
  // content全局效果修改
  /deep/ .el-form-item__content {
    font-size: 16px;
    color: #212f3a;
    margin-left: 10px !important;
  }
  // 信息格式正確的顏色
  /deep/ .el-icon-circle-check:before {
    color: #0096ff;
  }
  /deep/ .el-input {
    input {
      background-color: #d3dfe7;
      border: 1px solid #afc2cf;
      border-radius: 0px;
      width: 326px;
      &:focus {
        border-color: #0092ff;
      }
    }
  }
  /deep/ .el-textarea {
    textarea {
      background-color: #d3dfe7;
      border: 1px solid #afc2cf;
      border-radius: 0px;
      width: 326px;
      height: 164px;
      &:focus {
        border-color: #0092ff;
      }
    }
  }
  // 信息框提交按鈕
  .form-chooseBtn {
    margin-top: 40px;
    margin-bottom: 60px;
    position: relative;
    cursor: pointer;
    > a {
      span {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 20px;
        color: #fff;
      }
    }
  }
}
</style>
