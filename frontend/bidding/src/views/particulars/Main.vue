<template>
  <div class="main" v-if="orderInfo">
    <Top></Top>
    <Logo></Logo>
    <section id="content">
      <div class="wrapper type-area">
        <!-- 右上角tag -->
        <div class="con-tag">
          <ul>
            <!-- <li>
              <div
                @click="collect"
                :style="{backgroundImage: 'url('+collectBg+')'}"
                class="tag_icon tag_collect"
              ></div>
              <div>收藏</div>
            </li>-->
            <li>
              <!-- <div class="tag_icon tag_browse"></div>
              <div>瀏覽:</div>
              <div class="tag_browseNum">{{Math.floor(Math.random()*99)+1}}次</div>-->
            </li>
          </ul>
        </div>
        <!-- 標題 title -->
        <div class="con-title">
          <div class="tit-text">【治具】需求單號{{ orderInfo.bill_no }}</div>
          <div class="tit-info">
            <div class="info-price info-single">
              標價: {{ orderInfo.money_type === "RMB" ? "￥" : "$"
              }}{{ orderInfo.total_price }}
            </div>
            <div class="info-num info-single">
              需求套數: {{ orderInfo.amount }}
            </div>
            <div class="info-time info-single">
              投標截止日期: {{ orderInfo.bid_end_date }}
            </div>
          </div>
        </div>
        <!-- 主體內容 -->
        <div class="con-main">
          <!-- 進度 -->
          <!-- <div class="panel_progress">
            <div class="pan_pro_name pro_finished">
              <div class="text">發佈需求</div>
            </div>
            <div class="pan_pro_bar bar_finished"></div>
            <div class="pan_pro_name pro_finished">
              <div class="text">競價出標</div>
            </div>
            <div class="pan_pro_bar bar_inprogress"></div>
            <div class="pan_pro_name pro_unfinished">
              <div class="text">選標對比</div>
            </div>
            <div class="pan_pro_bar bar_unfinished"></div>
            <div class="pan_pro_name pro_unfinished">
              <div class="text">出貨驗收</div>
            </div>
            <div class="pan_pro_bar bar_unfinished"></div>
            <div class="pan_pro_name pro_unfinished">
              <div class="text">項目評價</div>
            </div>
          </div>-->
          <!-- 列表1 -->
          <div class="panel_info">
            <div class="pan_info_Top">
              <!-- 基本信息 -->
              <div class="baseInfo">
                <div class="ba_title">
                  <span>基本信息</span>
                </div>
                <table class="ba_content">
                  <tr>
                    <td>需求單位</td>
                    <td>:</td>
                    <td class="blueTd">
                      <span></span>
                      <router-link
                        :to="
                          '/merchant/information?pkid=' +
                            orderInfo.send_user.pkid
                        "
                        style="color:#0096ff"
                        >{{ orderInfo.send_user.dept_name }}</router-link
                      >
                    </td>
                  </tr>
                  <tr>
                    <td>交易法人名稱</td>
                    <td>:</td>
                    <td>{{ orderInfo.send_user.legal_person }}</td>
                  </tr>
                  <tr>
                    <td>交貨地點</td>
                    <td>:</td>
                    <td>{{ orderInfo.deliver_address }}</td>
                  </tr>
                  <tr>
                    <td>競價開始時間</td>
                    <td>:</td>
                    <td>{{ orderInfo.bid_start_date }}</td>
                  </tr>
                  <tr>
                    <td>競價結束時間</td>
                    <td>:</td>
                    <td>{{ orderInfo.bid_end_date }}</td>
                  </tr>
                  <tr>
                    <td>實際交貨時間</td>
                    <td>:</td>
                    <td>{{ orderInfo.deliver_date.split(" ")[0] }}</td>
                  </tr>
                  <tr>
                    <td>類型</td>
                    <td>:</td>
                    <td>{{ orderInfo.pd_type }}</td>
                  </tr>
                  <tr>
                    <td>費用代碼</td>
                    <td>:</td>
                    <td>{{ orderInfo.send_user.cost_code }}</td>
                  </tr>
                  <tr>
                    <td>接受總價</td>
                    <td>:</td>
                    <td>{{ orderInfo.total_price }}</td>
                  </tr>
                  <tr>
                    <td>幣別</td>
                    <td>:</td>
                    <td>{{ orderInfo.money_type }}</td>
                  </tr>
                  <tr>
                    <td>競標範圍</td>
                    <td>:</td>
                    <td>{{ orderInfo.bid_range }}</td>
                  </tr>
                  <tr>
                    <td>交貨方式</td>
                    <td>:</td>
                    <td>{{ orderInfo.deliver_way }}</td>
                  </tr>
                </table>
              </div>
              <!-- 圖檔附件 -->
              <div class="accessory">
                <div class="ac_title">
                  <span>圖檔附件</span>
                </div>
                <div class="ac_content">
                  <el-tooltip
                    class="item"
                    effect="dark"
                    placement="top-start"
                    v-for="item in orderInfo.file_list"
                    :key="item.pkid"
                    :content="'文件全稱：' + item.file_origin_name"
                  >
                    <div
                      class="ac_con_item"
                      :style="{
                        cursor:
                          send_recv_type == 'recv' ||
                          send_recv_type == 'send' ||
                          send_recv_type == 'system'
                            ? 'pointer'
                            : 'not-allowed'
                      }"
                      @click="
                        downloadOne(
                          item.file_save_path,
                          item.file_save_name,
                          item.file_origin_name
                        )
                      "
                    >
                      <div class="item-wrapper">
                        <div class="itemIcon"></div>
                        <div class="itemName">{{ item.file_origin_name }}</div>
                      </div>
                    </div>
                  </el-tooltip>
                </div>
                <div
                  class="ac_allDownload"
                  @click="downloadAll"
                  :style="{
                    cursor:
                      send_recv_type == 'recv' || send_recv_type == 'send'
                        ? 'pointer'
                        : 'not-allowed'
                  }"
                >
                  全部下載
                </div>
              </div>
            </div>
            <!-- 製作要求 -->
            <div class="pan_info_req">
              <div class="req_title">
                <span>製作要求</span>
              </div>
              <pre class="req_content">{{ orderInfo.make_requ }}</pre>
            </div>
            <!-- 立即參與按鈕 -->
            <div
              class="pan_info_join"
              @click="joinFlag ? checkList('uploaded') : alertBoxShow()"
              v-if="send_recv_type !== 'send' && send_recv_type !== 'system'"
              :style="{
                cursor:
                  send_recv_type == 'recv' && !isOverTime
                    ? 'pointer'
                    : 'not-allowed'
              }"
            >
              <svg-btn
                inColor="#3FB0FF"
                outColor="#0096FF"
                fillColor="#0096FF"
                :style="{
                  cursor:
                    send_recv_type == 'recv' && !isOverTime
                      ? 'pointer'
                      : 'not-allowed'
                }"
              />
              <span>{{
                joinFlag ? "查看標書" : isOverTime ? "已過期" : "參與報價"
              }}</span>
            </div>
            <div></div>
          </div>
          <!-- 列表2 -->
          <div class="panel_info_sec">
            <!-- 競標單位 -->
            <!-- TODO -->
            <div
              class="biddingUnits"
              v-if="isSender || send_recv_type === 'system'"
            >
              <div class="bid_title">
                <span>競標單位</span>
              </div>
              <div
                v-if="
                  orderInfo.give_recv_user_list &&
                    orderInfo.give_recv_user_list.length > 0
                "
                class="bid_content"
              >
                <div
                  class="bid_con_item"
                  v-for="item in orderInfo.give_recv_user_list"
                  :key="item.pkid"
                >
                  <div
                    :class="{ active: item.f_win_bid === 'Y' }"
                    class="item-wrapper"
                  >
                    <span class="icon"></span>
                    <router-link
                      :to="{
                        path: '/merchant/information',
                        query: { pkid: item.pkid }
                      }"
                      >{{ item.dept_name }}</router-link
                    >
                  </div>
                </div>
              </div>
              <div v-else class="bid_content">
                <p style="color:#626f7f;">暫無投標</p>
              </div>
            </div>
            <!-- 項目評價 -->
            <div class="evaluate">
              <div class="eva_title">
                <span>項目評價</span>
              </div>
              <div class="eva_content">
                <p v-if="orderInfo.send_eval">
                  需求方：{{ orderInfo.send_eval.summary_text }}
                </p>
                <p v-if="orderInfo.recv_eval">
                  接單方：{{ orderInfo.recv_eval.summary_text }}
                </p>
                <p
                  v-if="!orderInfo.send_eval && !orderInfo.recv_eval"
                  style="color:#626f7f;"
                >
                  暫無評價
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <Footer></Footer>

    <!-- 競價彈窗 -->
    <section class="offer-alertBox" v-show="alertBoxFlag">
      <div ref="alertBox" class="content">
        <div class="con_top">
          <div class="boxName">接單競價</div>
          <div class="closeBox" @click="alertBoxShow"></div>
        </div>
        <div class="con_form">
          <!-- 標題 title -->
          <div class="con_form_title">
            <div class="tit_text">【治具】需求單號{{ orderInfo.bill_no }}</div>
            <div class="tit_info">
              <div class="tit_info_single">
                標價: {{ orderInfo.total_price }}{{ orderInfo.money_type }}
              </div>
              <div class="tit_info_single">
                需求套數: {{ orderInfo.amount }}
              </div>
              <div class="tit_info_single">
                交貨時間: {{ orderInfo.deliver_date.split(" ")[0] }}
              </div>
            </div>
          </div>
          <!-- 表格1 -->
          <div class="con_form_listOne">
            <div class="listOne_info">
              投標法人: {{ orderInfo.send_user.legal_person }}
            </div>
            <table cellspacing="0" cellpadding="0" class="listOne_table">
              <tr>
                <th>幣別</th>
                <th>總報價（{{ orderInfo.money_type }}）</th>
                <th>交期</th>
                <th>備註</th>
              </tr>
              <tr>
                <td>{{ orderInfo.money_type }}</td>
                <td>{{ totalPrices * orderInfo.amount }}</td>
                <td>
                  <el-date-picker
                    v-model="deliver_date"
                    type="date"
                    :placeholder="orderInfo.deliver_date.split(' ')[0]"
                  ></el-date-picker>
                </td>
                <td>
                  <el-input
                    v-model="bidReason"
                    type="textarea"
                    maxlength="50"
                  ></el-input>
                </td>
              </tr>
            </table>
          </div>
          <!-- 表格2 -->
          <div class="con_form_listSec">
            <div class="listSec_info">
              <div>報價清單（單套）:</div>
            </div>
            <table class="listSec_table" cellspacing="0" cellpadding="0">
              <tr>
                <th style="width: 80px;">序號</th>
                <th style="width: 290px;">零件名</th>
                <th style="width: 120px;">零件數量</th>
                <th style="width: 280px;">
                  報價（{{ orderInfo.money_type }}/個）
                </th>
                <th style="width: 120px;">
                  小計（{{ orderInfo.money_type }}）
                </th>
                <th style="width: 80px;">操作</th>
              </tr>
              <tr v-for="(item, index) in quotatList" :key="item.id">
                <td>{{ index + 1 }}</td>
                <td>{{ item.name.split(".")[0] }}</td>
                <td>
                  <el-input
                    v-model="item.num"
                    type="number"
                    class="el-input-text-center"
                    style="width: 100px;"
                    oninput="this.value = this.value.replace(/[^1-9]/g, '');"
                  ></el-input>
                </td>
                <td>
                  <el-input
                    v-model="item.price"
                    type="number"
                    class="el-input-text-center"
                    style="width: 160px;"
                    oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                  ></el-input>
                </td>
                <td>{{ item.num * item.price }}</td>
                <td
                  style="color:#0096ff; cursor:pointer"
                  @click="delQuota(index)"
                >
                  刪除
                </td>
              </tr>
              <!-- 自定義的清單列表 -->
              <tr>
                <td
                  colspan="6"
                  v-show="otherList.length"
                  style="height: 10px; background:#c8d4de"
                >
                  自定義
                </td>
              </tr>
              <tr v-for="(item, index) in otherList" :key="item.id">
                <td>{{ index + 1 }}</td>
                <td>
                  <el-input
                    v-model="item.selfDefineName"
                    :placeholder="item.name"
                    type="text"
                    class="el-input-text-center"
                    style="width: 180px;"
                    maxlength="25"
                    :disabled="joinFlag"
                  ></el-input>
                </td>
                <td>
                  <el-input
                    v-model="item.num"
                    type="number"
                    class="el-input-text-center"
                    style="width: 100px;"
                    oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                  ></el-input>
                </td>
                <td>
                  <el-input
                    v-model="item.price"
                    type="number"
                    class="el-input-text-center"
                    style="width: 160px;"
                    placeholder="0"
                    oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                  ></el-input>
                </td>
                <td>{{ item.num * item.price }}</td>
                <td
                  style="color:#0096ff; cursor:pointer"
                  @click="delOther(index)"
                >
                  刪除
                </td>
              </tr>

              <tr>
                <td>總計</td>
                <td colspan="5">{{ totalPrices }}</td>
              </tr>
            </table>

            <div class="listSec_opera">
              <div class="listSec_add" @click="dialogVisible = !dialogVisible">
                <div class="addIcon"></div>
                <div>添加報價選項</div>
              </div>
              <div class="listSec_addS" @click="addNewLine">
                <div class="addIcon"></div>
                <div>添加自定義選項</div>
              </div>
              <excel-opera @excelData="getExcelData"></excel-opera>
            </div>
          </div>
          <!-- footer -->
          <div class="con_form_footer">
            <!-- 開始競價按鈕 -->
            <div class="foo_startBtn" @click="checkBidding()">
              <svg-btn
                inColor="#3FB0FF"
                outColor="#0096FF"
                fillColor="#0096FF"
              />
              <span>確認提交</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 添加弹窗 -->
    <el-dialog title="零件名稱" :visible.sync="dialogVisible">
      <el-checkbox
        :indeterminate="isIndeterminate"
        v-model="checkAll"
        @change="handleCheckAllChange"
        >全选</el-checkbox
      >
      <div style="margin: 15px 0;"></div>
      <el-checkbox-group
        v-model="checkedComponents"
        @change="handleCheckedComponentsChange"
      >
        <el-checkbox
          v-for="component in components"
          :label="component"
          :key="component"
          >{{ component }}</el-checkbox
        >
      </el-checkbox-group>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addToQuotatList(), (dialogVisible = false)"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <!-- 添加弹窗 -->
    <el-dialog title="提交" :visible.sync="dialogSecVisible">
      <p>
        您的單套報價清單為
        <span style="font-size:28px;">{{ totalPrices }}</span
        >，總報價為
        <span style="font-size:28px;">{{ totalPrices * orderInfo.amount }}</span
        >;
      </p>
      <p>
        標書已生成,
        <router-link @click.native="checkList" to style="color:#0096ff"
          >點擊查看</router-link
        >
      </p>
      <!-- 同意協議 -->
      <div class="foo_agreement">
        <label for="agree">
          <input
            id="agree"
            type="checkBox"
            @click="agree = !agree"
            :checked="agree"
          />
          我已仔细查看標書及相關協議內容，并对我提交的信息负责。
        </label>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogSecVisible = false">返 回</el-button>
        <el-button type="primary" @click="isAgree()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Top from "@/components/Top";
import Logo from "@/components/Logo";
import Footer from "@/components/Footer";
import SvgBtn from "@/components/operate/SvgBtn";
import ExcelOpera from "./modules/ExcelOpera";

import {
  query_bill_by_pkid,
  save_give_price,
  query_give_price_by_bill_pkid
} from "@/api/order";
import { downLoad } from "@/api/file";

import { getAccess } from "@/assets/js/getInfo";
import { mapState } from "vuex";

let componentOptions = [];
export default {
  data: function() {
    return {
      // 添加彈窗的顯示/隱藏
      dialogVisible: false,
      dialogSecVisible: false,
      // 是否全選
      checkAll: false,
      // 已選項
      checkedComponents: [],
      // 可選項
      components: [],
      // 全選狀態
      isIndeterminate: false,
      // 可選項詳細信息
      componentAllOptions: [],
      // 查詢到的信息
      orderInfo: "",
      // 進度條
      progress: [],
      // 收藏
      collectFlag: false,
      // 收藏圖標背景圖
      collectBg: require("../../assets/imgs/particulars/collect.png"),
      // 彈窗
      alertBoxFlag: false,
      // 圖檔附件數據
      accessoryData: [],
      // 交期
      deliver_date: "",
      // 報價理由
      bidReason: "",
      // 報價清單
      quotatList: [],
      // 其它零件
      otherList: [],
      // 是否參與
      joinFlag: false,
      // 是否過期
      isOverTime: false,
      // 是否同意
      agree: false,
      // 是否查看標書
      isCheckList: false,
      //
      scrollTopDistance: 0,
      // 是否是發單方本人
      isSender: false
    };
  },
  methods: {
    // 獲取到上傳的excel文檔內容
    getExcelData(val) {
      if (this.otherList.length > 0) {
        for (let item of val) {
          item.id += this.otherList.length;
        }
      }
      this.otherList = [...this.otherList, ...val];

      // this.otherList
    },
    // 是否同意
    isAgree() {
      // this.agree;

      if (!this.isCheckList) return this.$message.error("請先查看標書!");
      if (!this.agree)
        return this.$message.error("請確認標書內容無誤,並勾選同意內容！");
      this.upBidding();
      this.dialogSecVisible = false;
    },
    // 查看報價清單
    checkList(val) {
      if (val === "uploaded") {
        sessionStorage.setItem("biddinfdDoc", JSON.stringify(this.orderInfo));
        let routeData = this.$router.resolve({
          path: "/biddingDoc",
          query: { loaded: true, pkid: this.pkid }
        });
        window.open(routeData.href, "_blank");
      } else {
        this.isCheckList = true;
        let slav_list = [];
        // 將報價清單的數據加入slav_list
        for (let item of this.quotatList) {
          let obj = {
            part_name: item.selfDefineName || item.name,
            part_doc_file_pkid: item.id,
            part_amunt: item.num,
            part_unit_price: item.price,
            part_price_sum: Number(item.num) * Number(item.price)
          };
          slav_list.push(obj);
        }

        // 將 其它 的數據加入slav_list
        for (let item of this.otherList) {
          let obj = {
            part_name: item.selfDefineName || item.name,
            part_doc_file_pkid: item.id,
            part_amunt: item.num,
            part_unit_price: item.price,
            part_price_sum: Number(item.num) * Number(item.price)
          };
          slav_list.push(obj);
        }

        // 存入 data
        const data = {
          orderInfo: this.orderInfo,
          total_price: this.totalPrices,
          deliver_date:
            this.deliver_date || new Date(this.orderInfo.deliver_date),
          descp: this.bidReason,
          // f_win_bid: "",
          slav_list: slav_list
        };
        sessionStorage.setItem("biddinfdDoc", JSON.stringify(data));
        const url = "/biddingDoc?loaded=false";
        let routeData = this.$router.resolve({ path: url });
        window.open(routeData.href, "_blank");
      }
    },
    // 添加自定義清單
    addNewLine() {
      let cliH = this.$refs.alertBox.clientHeight;
      let scrH = this.$refs.alertBox.scrollHeight;
      this.$refs.alertBox.scrollTop = scrH - cliH;

      const id = this.otherList.length
        ? this.otherList[this.otherList.length - 1].id + 1
        : 1;
      this.otherList.push({
        selfDefineName: "",
        num: 1,
        price: 0,
        id: id,
        name: "其它"
      });
    },

    // 添加零件 全選
    handleCheckAllChange(val) {
      // this.checkedComponents = val ? componentOptions : [];
      if (val) {
        this.checkedComponents = [];
        for (let item of componentOptions) {
          this.checkedComponents.push(item);
        }
      } else {
        this.checkedComponents = [];
      }
      this.isIndeterminate = false;
    },
    handleCheckedComponentsChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.components.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.components.length;
    },
    // 刪除發單方給定零件
    delQuota(index) {
      this.checkedComponents.splice(index, 1);
      this.addToQuotatList();
      this.handleCheckedComponentsChange(this.checkedComponents);
    },

    // 刪除自定義零件
    delOther(index) {
      this.otherList.splice(index, 1);
    },

    // 收藏事件
    collect() {
      this.collectFlag = !this.collectFlag;
      this.collectBg = this.collectFlag
        ? require("../../assets/imgs/particulars/collected.png")
        : require("../../assets/imgs/particulars/collect.png");
    },

    // 彈窗
    alertBoxShow() {
      if (this.isOverTime) {
        return this.$alert("競標時間已結束！");
      }

      if (this.send_recv_type == "recv") {
        var flag = this.alertBoxFlag ? false : true;
        return (this.alertBoxFlag = flag);
      } else {
        this.$alert("請先登錄！");
      }
    },

    // 獲取信息
    getInfo() {
      query_bill_by_pkid(this.pkid).then(res => {
        if (res.code === "1") {
          this.orderInfo = res.t;
          this.isOverdue();
          this.isJoin();
          this.isSender =
            this.orderInfo.send_user_pkid === this.$store.state.userInfo.pkid;
          // 如果參加過 不需要再渲染表格
          if (this.joinFlag) return;

          this.componentAllOptions = [];
          for (let item of this.orderInfo.file_list) {
            const obj = {
              id: item.pkid,
              name: item.file_origin_name,
              selfDefineName: "",
              num: item.part_amunt,
              price: 0
            };
            // 將文件列表信息存入 componentAllOptions
            this.componentAllOptions.push(obj);
          }
          this.detachment();
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 是否過期
    isOverdue() {
      let endDate = new Date(this.orderInfo.bid_end_date),
        currentDate = new Date();
      this.isOverTime = currentDate > endDate ? true : false;
    },

    // 抽離出可選的項
    detachment() {
      componentOptions = [];
      for (let item of this.componentAllOptions) {
        componentOptions.push(item.name);
      }
      this.components = componentOptions;
    },

    // 將文件列表信息存入 quotatList
    addToQuotatList() {
      this.quotatList = [];
      for (let check of this.checkedComponents) {
        for (let component of this.componentAllOptions)
          if (check == component.name) {
            this.quotatList.push(component);
          }
      }
    },

    // 下載文件
    downloadOne(path, name, originName) {
      if (
        this.send_recv_type == "recv" ||
        this.send_recv_type == "send" ||
        this.send_recv_type == "system"
      ) {
        downLoad(getAccess(path, name, originName)).then(res => {
          let blob = new Blob([res], { type: res.type });
          let objectUrl = URL.createObjectURL(blob); //生成一个url

          let link = document.createElement("a");
          link.href = objectUrl;
          link.setAttribute("download", originName);
          link.click();
        });
      } else {
        this.$alert("請先登錄");
      }
    },

    downloadAll() {
      for (let item of this.orderInfo.file_list) {
        this.downloadOne(
          item.file_save_path,
          item.file_save_name,
          item.file_origin_name
        );
      }
    },

    // 檢查競價信息
    checkBidding() {
      // 判斷是否參加過
      if (this.joinFlag) return this.$message.warning("您已參與！");
      // 判斷
      if (this.deliver_date) {
        let earliestTime = new Date(this.orderInfo.bid_end_date);
        let latestTime = new Date(
          new Date(this.orderInfo.deliver_date).getTime() + 2592000000
        );
        if (new Date(this.deliver_date) < earliestTime)
          return this.$message.error("交期不能早於競標結束時間，請修改！");
        if (new Date(this.deliver_date) > latestTime)
          return this.$message.error("交期不能延遲超過一個月，請修改！");
      }
      for (let item of this.quotatList) {
        if (!item.num) {
          return this.$message.error("零件報價信息不完整");
        } else if (item.num < 1) {
          return this.$message.error("零件個數不正確");
        }
        if (!item.price) {
          return this.$message.error("零件報價信息不完整");
        } else if (item.price < 1) {
          return this.$message.error("零件報價不正確");
        }
      }
      for (let item of this.otherList) {
        if (!item.num) {
          return this.$message.error("零件報價信息不完整");
        } else if (item.num < 1) {
          return this.$message.error("零件個數不正確");
        }
        if (!item.price) {
          return this.$message.error("零件報價信息不完整");
        } else if (item.price < 1) {
          return this.$message.error("零件報價不正確");
        }
      }
      if (this.totalPrices < 1) return this.$message.error("請報價");
      this.dialogSecVisible = !this.dialogSecVisible;
      this.isCheckList = false;
    },

    // 上傳競價信息
    upBidding() {
      let slav_list = [];
      // 將報價清單的數據加入slav_list
      for (let item of this.quotatList) {
        let obj = {
          part_name: item.selfDefineName || item.name,
          part_doc_file_pkid: item.id,
          part_amunt: item.num,
          part_unit_price: item.price,
          part_price_sum: Number(item.num) * Number(item.price)
        };
        slav_list.push(obj);
      }

      // 將 其它 的數據加入slav_list
      for (let item of this.otherList) {
        let obj = {
          part_name: item.selfDefineName || item.name,
          part_doc_file_pkid: item.id,
          part_amunt: item.num,
          part_unit_price: item.price,
          part_price_sum: Number(item.num) * Number(item.price)
        };
        slav_list.push(obj);
      }

      // 存入 data
      const data = {
        bill_pkid: this.orderInfo.pkid,
        total_price: this.totalPrices,
        deliver_date:
          this.deliver_date || new Date(this.orderInfo.deliver_date),
        descp: this.bidReason,
        f_win_bid: "",
        slav_list: slav_list
      };
      save_give_price(data).then(res => {
        if (res.code === "1") {
          this.$message.success(
            "上傳價格完成，請在個人中心查看詳情，祝您競價成功！"
          );
          // 關閉窗口
          this.$router.push("/");
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 判斷是否參加過
    isJoin() {
      for (let item of this.orderInfo.give_recv_user_list) {
        if (item.pkid == this.my_pkid) {
          var id = 0;
          // 查詢報價信息
          query_give_price_by_bill_pkid(this.orderInfo.pkid).then(res => {
            // { id: "1", name: "螺帽1", num: "2", price: "" }
            for (let item of res.t.slav_list) {
              if (item.part_doc_file) {
                const obj = {
                  id: item.pkid,
                  name: item.part_doc_file.file_origin_name,
                  selfDefineName: item.part_name,
                  num: item.part_amunt,
                  price: item.part_unit_price
                };
                this.quotatList.push(obj);
              } else {
                id++;
                const obj = {
                  id: id,
                  selfDefineName: item.part_name,
                  num: item.part_amunt,
                  price: item.part_unit_price
                };
                this.otherList.push(obj);
              }
            }
            this.deliver_date = res.t.deliver_date;
            this.bidReason = res.t.descp;
          });
          // 退出循環
          return (this.joinFlag = true);
        }
      }
    }
  },
  components: {
    Top,
    Logo,
    Footer,
    SvgBtn,
    ExcelOpera
  },
  computed: {
    ...mapState({
      send_recv_type: state =>
        state.userInfo ? state.userInfo.send_recv_type : "",
      my_pkid: state => (state.userInfo ? state.userInfo.pkid : "")
    }),
    // 計算總價值
    totalPrices() {
      var val = 0;
      for (let i in this.quotatList) {
        val += this.quotatList[i].num * this.quotatList[i].price;
      }
      for (let i in this.otherList) {
        val += this.otherList[i].num * this.otherList[i].price;
      }
      return val;
    }
  },
  created() {
    this.pkid = this.$router.history.current.query.pkid;
    this.getInfo();
  },
  watch: {
    alertBoxFlag: val => {
      if (val) {
        document.body.style.height = "100vh";
        document.body.style["overflow-y"] = "hidden";
      } else {
        document.body.style.height = "unset";
        document.body.style["overflow-y"] = "auto";
      }
    }
  }
};
</script>

<style lang="less" scoped>
// 避免弹窗页遮罩层高度不适应
.main {
  position: relative;
}

// 主体内容
#content {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  background-size: 120%;
  position: relative;
  // 小標籤
  .con-tag {
    display: flex;
    justify-content: flex-end;
    color: #627887;
    font-size: 14px;
    padding-top: 10px;
    li {
      float: left;
      margin-left: 28px;
      display: flex;
      align-items: center;
    }
  }
  .tag_icon {
    margin-right: 8px;
  }
  .tag_collect {
    cursor: pointer;
    width: 18px;
    height: 18px;
    background-repeat: no-repeat;
  }
  .tag_browse {
    width: 18px;
    height: 12px;
    background: url(../../assets/imgs/particulars/browse.png) no-repeat;
  }
  // 標題內容
  .con-title {
    text-align: center;
    margin-top: 24px;
  }
  // 標題名
  .tit-text {
    font-size: 30px;
    color: #212f3a;
  }
  // 其餘相關信息
  .tit-info {
    display: flex;
    justify-content: center;
    margin-top: 24px;

    .info-single {
      margin: 0 20px;
      font-size: 14px;
      color: #212f3a;
    }
  }
  // 主體內容
  .con-main {
    width: 100%;
    background: rgba(13, 28, 42, 0.2);
    margin-top: 24px;
    box-sizing: border-box;
    padding: 20px;
  }
  // 主體-進度
  .panel_progress {
    margin: 28px 60px 20px 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .pan_pro_name {
    color: #12222e;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    &.pro_finished {
      color: #d7efff;
    }
    .text {
      font-size: 18px;
    }
    // .time {
    //   margin-top: 12px;
    //   font-size: 12px;
    //   color: #aac0cf;
    // }
  }
  .pan_pro_bar {
    width: 127px;
    height: 34px;
    background-repeat: no-repeat;
    background-position: center;
    &.bar_finished {
      background-image: url(../../assets/imgs/particulars/bar-finished.png);
    }
    &.bar_inprogress {
      background-image: url(../../assets/imgs/particulars/bar-inprogress.png);
      position: relative;
      &:after {
        content: "進行中";
        display: block;
        position: absolute;
        top: -32px;
        left: 32px;
        width: 60px;
        height: 29px;
        background: url(../../assets/imgs/particulars/processTag.png) no-repeat;
        text-align: center;
        color: #fff;
        line-height: 24px;
      }
    }
    &.bar_unfinished {
      background-image: url(../../assets/imgs/particulars/bar-unfinished.png);
    }
  }

  // info公共樣式
  .ba_title,
  .ac_title,
  .req_title,
  .bid_title,
  .eva_title {
    user-select: none;
    position: relative;
    width: 100px;
    height: 35px;
    background: url(../../assets/imgs/particulars/infoTitleBG.png) no-repeat;
    span {
      color: #fff;
      font-size: 16px;
      position: absolute;
      left: 14%;
      top: 32%;
    }
  }
  // info公共樣式結束

  // 主體-信息
  .panel_info {
    box-sizing: border-box;
    padding: 32px 50px;
    background: #d3dfe7;
  }
  .pan_info_Top {
    display: flex;
    justify-content: space-between;
  }
  // 【基本信息】
  .baseInfo {
    table {
      margin-top: 18px;
      td {
        height: 24px;
        &:nth-child(1) {
          min-width: 100px;
          font-size: 16px;
          color: #626f7f;
          // 文本居中
          // text-align:center;
          text-align: justify;
          // text-justify:distribute-all-lines;
          text-align-last: justify;
        }
        &:nth-child(2) {
          width: 20px;
          color: #626f7f;
        }
        &:nth-child(3) {
          &.blueTd {
            color: #0096ff;
            span {
              display: inline-block;
              width: 16px;
              height: 15px;
              background: url(../../assets/imgs/particulars/plane.png) no-repeat;
            }
          }
        }
      }
    }
  }
  // 【圖檔附件】
  .accessory {
    position: relative;
  }
  .ac_content {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    width: 680px;
    margin-top: 22px;
  }
  // 單獨的元素包裹框
  .ac_con_item {
    width: 25%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;
    height: 34px;
    box-sizing: border-box;
    transition-duration: 0.1s;
    &:hover {
      border: 1px solid #0092ff;
      background-color: #aedfff;
      .item-wrapper::after {
        opacity: 1;
      }
      .itemNum {
        opacity: 0;
      }
    }
    .item-wrapper {
      position: relative;
      display: flex;
      justify-content: space-around;
      align-items: center;
      width: 150px;
      margin: 0 auto;
      &::after {
        display: block;
        content: "";
        width: 16px;
        height: 16px;
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        background: url(../../assets/imgs/particulars/check.png) no-repeat;
        opacity: 0;
        transition-duration: 0.2s;
        cursor: pointer;
      }
    }
    .itemIcon {
      width: 21px;
      height: 21px;
      background: url(../../assets/imgs/particulars/File.png) no-repeat;
    }
    .itemName {
      width: 60%;
      overflow: hidden;
      white-space: nowrap;
      margin-right: 10px;
      text-overflow: ellipsis;
    }
    // .itemNum {
    //   width: 22px;
    //   text-align: center;
    //   font-size: 12px;
    //   color: #626f7f;
    //   overflow: hidden;
    // }
  }

  // 全部下載按鈕
  .ac_allDownload {
    width: 120px;
    height: 44px;
    float: right;
    margin-top: 24px;
    margin-right: 20px;
    background-color: #2e6e9e;
    color: #fff;
    font-size: 18px;
    text-align: center;
    line-height: 44px;
    cursor: pointer;
    user-select: none;
  }

  // 【製作要求】
  .pan_info_req {
    margin-top: 30px;
    .req_content {
      margin-top: 22px;
    }
    p {
      color: #212f3a;
      font-size: 16px;
      height: 28px;
    }
    pre {
      font-family: "myFont", "Avenir", Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      height: 100%;
      line-height: 1.5rem;
      // 自動換行
      word-wrap: break-word;
      white-space: pre-wrap;
    }
  }
  // 參與按鈕
  .pan_info_join {
    margin: 28px auto 0;
    width: 265px;
    height: 65px;
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    user-select: none;
    position: relative;
    > span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
  // 主體-信息二
  .panel_info_sec {
    margin-top: 10px;
    padding: 32px 50px;
    background: #d3dfe7;
  }
  // 【競標單位】
  .bid_content {
    display: flex;
    flex-wrap: wrap;
    margin-top: 22px;
  }
  .bid_con_item {
    width: 25%;
    height: 46px;
    box-sizing: border-box;
    color: #626f7f;
    font-size: 16px;
    > .item-wrapper {
      display: flex;
      align-items: center;
      &.active {
        > span {
          opacity: 1;
        }
        > a {
          color: #ff0004;
        }
      }
      > span {
        display: block;
        width: 16px;
        height: 21px;
        background: url(../../assets/imgs/particulars/winIcon.png) no-repeat;
        opacity: 0;
        margin-right: 6px;
      }
      > a {
        color: #626f7f;
      }
    }
  }
  // 【項目評價】
  .eva_content {
    padding-top: 22px;
    > p {
      font-size: 16px;
      color: #626f7f;
    }
  }
}

// 彈窗
.offer-alertBox {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  .content {
    position: fixed;
    width: 80%;
    top: 60px;
    left: 50%;
    transform: translateX(-50%);
    background-color: #d3dfe7;
    padding: 20px;
    box-sizing: border-box;
    // 窗口滾動
    height: 80vh;
    overflow: auto;
    /* 滚动槽 */
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
  }
  // 頂部信息
  .con_top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .boxName {
      font-size: 18px;
      color: #12222e;
    }
    .closeBox {
      width: 24px;
      height: 24px;
      background: url(../../assets/imgs/particulars/closeAlertBox.png) no-repeat;
      cursor: pointer;
      user-select: none;
    }
  }
  // 表單信息
  .con_form {
    width: 85%;
    margin: 0 auto;
  }
  .con_form_title {
    text-align: center;
    .tit_text {
      font-size: 30px;
      color: #0092ff;
    }
    .tit_info {
      display: flex;
      justify-content: center;
      margin-top: 18px;
    }
    .tit_info_single {
      margin: 0 20px;
      font-size: 14px;
      color: #0092ff;
    }
  }
  // 表格一
  .con_form_listOne {
    margin-top: 40px;
  }
  .listOne_info {
    font-size: 14px;
    color: #212f3a;
  }
  .listOne_table {
    margin-top: 15px;
    width: 100%;
    border-collapse: collapse;
    // border: 1px solid #C0C8CF;
    th {
      background-color: #c8d4de;
    }
    td,
    th {
      font-size: 16px;
      color: #212f3a;
      border: 1px solid #c0c8cf;
      text-align: center;
      height: 50px;
      font-weight: 400;
    }
  }
  // 表格二
  .con_form_listSec {
    margin-top: 40px;
  }
  .listSec_info {
    font-size: 16px;
    color: #212f3a;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #0096ff;
  }
  .listSec_table {
    margin-top: 15px;
    width: 100%;
    border-collapse: collapse;
    // border: 1px solid #C0C8CF;
    th {
      background-color: #c8d4de;
    }
    td,
    th {
      font-size: 16px;
      color: #212f3a;
      border: 1px solid #c0c8cf;
      text-align: center;
      height: 40px;
      font-weight: 400;
    }
  }
  .listSec_opera {
    display: flex;
    align-items: center;
    padding-top: 20px;
  }
  .listSec_add,
  .listSec_addS {
    padding: 10px;
    background-color: #9ab0c3;
    color: #fff;
    font-size: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    user-select: none;
    > .addIcon {
      width: 15px;
      height: 15px;
      background: url(../../assets/imgs/particulars/add.png) no-repeat;
      margin-right: 10px;
    }
  }
  .listSec_addS {
    width: 160px;
    margin-left: 20px;
  }
  // el-input
  /deep/ .el-input-text-center {
    .el-input__inner {
      text-align: center;
    }
  }
  /deep/ .el-input {
    height: 40px;
    margin: 8px 0;
  }
  // 底部
  .con_form_footer {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  // 開始競價按鈕
  .foo_startBtn {
    margin: 20px 0;
    width: 265px;
    height: 65px;
    line-height: 64px;
    color: #fff;
    font-size: 20px;
    position: relative;
    cursor: pointer;
    user-select: none;
    > span {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
}

// 弹窗2
// 同意協議
.foo_agreement {
  margin-top: 40px;
  font-size: 14px;
  color: #212f3a;
  label {
    display: flex;
    align-items: center;
  }
  input {
    width: 16px;
    height: 16px;
    margin-right: 5px;
  }
}
</style>
