<template>
  <div class="information">
    <Top></Top>
    <Logo></Logo>
    <section class="info-cont" v-if="merchantInfo">
      <!-- 主體內容 -->
      <div class="main type-area">
        <div class="warpper">
          <div class="principal">
            <div class="base-info">
              <!-- 基本信息 -->
              <el-image
                class="info_logo"
                :src="porImgUrl"
                fit="cover"
              ></el-image>
              <div class="info-name">
                <span class="icon"></span>
                <span>{{ merchantInfo.dept_name }}</span>
              </div>
              <div class="info-one">
                <span
                  >廠區：{{ merchantInfo.fctry_zone }} 》次集团：{{
                    merchantInfo.secn_cmpy
                  }}
                  》事業群：{{ merchantInfo.entrps_group }} 》處：{{
                    merchantInfo.pd_office
                  }}
                  》單位：{{ merchantInfo.dept_name }}</span
                >
              </div>
              <div class="info-two">
                <span
                  class="range"
                  v-if="merchantInfo.send_recv_type == 'recv'"
                >
                  加工範圍：
                  <span
                    v-for="item in merchantInfo.recv_range_list"
                    :key="item.pkid"
                    >{{ item.name }}</span
                  >
                </span>
                <span v-else>&nbsp;</span>
                <span class="tel">联系电话：{{ merchantInfo.tel }}</span>
              </div>
              <!-- 客戶評價 -->
              <div class="info-evaluation">
                <div class="info-eva-title">客戶評價</div>
                <div class="info-eva-list">
                  <!-- 單獨評語 -->
                  <div
                    class="single"
                    v-for="(item, index) in comments"
                    :key="index"
                  >
                    <div>
                      <div class="eva-text">{{ item.text }}</div>
                      <div class="eva-date">{{ item.date }}</div>
                    </div>
                    <div class="eva-belong">{{ item.belong }}</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="introduction">
              <div class="intro-grade">
                <table class="intro-gra-tab">
                  <tr>
                    <th>入駐時間:</th>
                    <th>...</th>
                  </tr>
                  <tr>
                    <td>客戶評分:</td>
                    <td>...</td>
                  </tr>
                  <tr>
                    <td>出貨時效:</td>
                    <td>...</td>
                  </tr>
                  <tr>
                    <td>出貨質量:</td>
                    <td>...</td>
                  </tr>
                  <tr>
                    <td>服務態度:</td>
                    <td>...</td>
                  </tr>
                </table>
              </div>
              <div class="intro-text">
                <div
                  v-if="merchantInfo.send_recv_type == 'recv'"
                  class="intro-text-title"
                >
                  客戶簡介
                </div>
                <div v-else class="intro-text-title">商戶簡介</div>

                <pre>{{ merchantInfo.summary }}</pre>
              </div>
            </div>
          </div>
          <div class="more">
            <p>查看更多</p>
            <span class="moreIcon"></span>
          </div>
        </div>
      </div>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
import Top from "@/components/Top";
import Logo from "@/components/Logo";
import Footer from "@/components/Footer";

import { query_user_info_by_pkid } from "@/api/user";
import { getPorImg } from "@/assets/js/getInfo";
// import { query_send_get_eval_list, query_recv_get_eval_list} from "@/api/order";

export default {
  data: function() {
    return {
      comments: [],
      // 賬號信息
      merchantInfo: "",
      // 賬號頭像
      porImgUrl: ""
    };
  },
  methods: {
    // 獲取信息
    getMerchantInfo() {
      const data = this.$router.history.current.query.pkid;
      query_user_info_by_pkid(data).then(res => {
        if (res.code === "1") {
          this.merchantInfo = res.t;
          const param = this.merchantInfo.user_pic_file;
          this.porImgUrl = getPorImg(
            param.file_save_path,
            param.file_save_name,
            param.file_origin_name
          );

          this.comments = [];
          for (let item of this.merchantInfo.get_eval_list) {
            const obj = {
              text: item.summary_text,
              date: item.create_date,
              belong: item.user.dept_name
            };
            this.comments.push(obj);
          }
          // this.getEvaluation(data, this.merchantInfo.send_recv_type)
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
  },
  created() {
    this.getMerchantInfo();
  }
};
</script>

<style lang="less" scoped>
.information {
  // height: 100%;
  // background-color: #12222e;
}
// 主體內容
.info-cont {
  background: #12222e url(../../assets/imgs/particulars/contentBG.png) no-repeat
    top center / contain;
  padding-top: 24px;
  // 邊界樣式
  > .main {
    background: rgba(13, 28, 42, 0.2);
    box-sizing: border-box;
    padding: 20px;
  }
  .warpper {
    border: 3px solid #566170;
  }
  // 主要區域
  .principal {
    display: flex;
    justify-content: space-around;
  }
  // 信息區域
  .base-info {
    width: 668px;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #e0f2ff;
    font-size: 14px;
  }
  // logo圖
  .info_logo {
    width: 200px;
    height: 200px;
    margin-top: 20px;
  }
  // 公司名稱
  .info-name {
    display: flex;
    align-items: center;
    margin-top: 12px;
    font-size: 18px;
    > .icon {
      display: inline-block;
      width: 14px;
      height: 23px;
      margin-right: 10px;
      background: url(../../assets/imgs/merchant/nameIcon.png) no-repeat;
    }
  }
  // 基本信息第一行
  .info-one {
    margin-top: 15px;
  }
  // 基本信息第二行
  .info-two {
    margin-top: 24px;
    width: 100%;
    display: flex;
    justify-content: space-between;
  }
  // 客戶評價
  .info-evaluation {
    width: 100%;
    margin-top: 40px;
  }
  .info-eva-title {
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #0d1c29;
    color: #e0f2ff;
    font-size: 18px;
    padding-left: 10px;
  }
  .info-eva-list {
    > .single {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid rgba(255, 255, 255, 0.05);
      padding: 32px 0 16px 0;
      position: relative;
    }
  }
  // 評價
  .eva-text {
    color: #96a2b0;
  }
  // 日期
  .eva-date {
    color: #404c5a;
    font-size: 14px;
    margin-top: 15px;
  }
  // 評價人
  .eva-belong {
    color: #404c5a;
    font-size: 14px;
    position: absolute;
    right: 0;
    bottom: 20px;
  }
  // 簡介區域
  .introduction {
    width: 186px;
  }
  // 簡介-評分
  .intro-grade {
    width: 186px;
    height: 236px;
    background: url(../../assets/imgs/merchant/flag.png) no-repeat;
  }
  // 表格
  .intro-gra-tab {
    width: 90%;
    th {
      font-weight: 400;
      font-size: 18px;
      height: 70px;
    }
    td,
    th {
      text-align: center;
      color: #fff;
      font-size: 14px;
      &:nth-child(2) {
        font-size: 16px;
      }
    }
  }
  // 簡介-文字
  .intro-text {
    margin-top: 30px;
    pre {
      font-family: "myFont", "Avenir", Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      height: 100%;
      color: #fff;
      // 疊加背景
      mix-blend-mode: overlay;
      font-size: 14px;
      line-height: 28px;
      // 自動換行
      word-wrap: break-word;
      white-space: pre-wrap;
    }
  }
  .intro-text-title {
    font-size: 18px;
    color: #fff;
    // 疊加背景
    mix-blend-mode: overlay;
  }
  // more
  .more {
    margin: 60px auto 30px;
    width: 80px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    &:hover {
      > span {
        transform: translateY(8px);
      }
    }
    > p {
      color: #065997;
      font-size: 14px;
      text-align: center;
    }
    > span {
      display: block;
      width: 28px;
      height: 12px;
      background: url(../../assets/imgs/merchant/more.png) no-repeat;
      transition-duration: 0.3s;
    }
  }
}
</style>
