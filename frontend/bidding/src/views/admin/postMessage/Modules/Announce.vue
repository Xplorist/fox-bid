<template>
  <div class="announce">
    <div>公告編輯：</div>
    <div class="content">
      <p>公告内容每条不超过60个字符。</p>
      <div
        class="single-wrapper z-flex z-align-items-center"
        v-for="(item, index) in announceList"
        :key="item + index"
      >
        {{ (index + 1) | addZero }}.
        <div class="inputBox">
          <input
            type="text"
            v-model="item.list_content"
            maxlength="60"
            placeholder="請輸入"
          />
          <span class="input-length">{{ item.list_content.length }}/60</span>
        </div>
        <div class="opera z-flex z-justify-content-between">
          <img
            v-if="index != announceList.length - 1"
            src="../../../../assets/imgs/admin/arrow_down.png"
            alt=""
            @click="handleOpera('down', index)"
          />
          <span v-else></span>
          <img
            v-if="index != 0"
            src="../../../../assets/imgs/admin/arrow_top.png"
            alt=""
            @click="handleOpera('top', index)"
          />
          <span v-else></span>
          <img
            src="../../../../assets/imgs/admin/delete_gray.png"
            alt=""
            @click="handleOpera('delte', index)"
          />
        </div>
      </div>
    </div>
    <div
      class="addNewBtn z-btn z-btn-gray z-flex z-align-items-center z-justify-content-center"
      @click="handleAddNewBtnClick"
    >
      <img src="../../../../assets/imgs/admin/add_blue.png" alt="" />
      添加
    </div>
    <div class="svg-btn-box z-flex">
      <div>
        <svg-btn
          class="svgBtn"
          inColor="#3FB0FF"
          fillColor="#0096FF"
          outColor="#0096FF"
          @click.native="handlePublishClick()"
        />
        <span @click="handlePublishClick()">發佈</span>
      </div>
    </div>
  </div>
</template>

<script>
import SvgBtn from "@/components/operate/SvgBtn";
import { saveBulletin, listBulletin } from "@/api/admin";

export default {
  data() {
    return {
      announceList: []
    };
  },
  created() {
    this.getAnnounce();
  },
  filters: {
    addZero: function(val) {
      if (Number(val) < 10) {
        return "0" + val;
      }
      return val
    }
  },
  methods: {
    // 獲取公告
    getAnnounce() {
      const data = {};
      listBulletin(data).then(res => {
        if (res.code === "1") {
          this.announceList = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 處理發佈公告事件
    handlePublishClick() {
      this.$confirm("確定發佈此公告嗎？", "提示", {})
        .then(() => {
          let obj = {};
          let data = [];
          let order = 1;
          for (let i in this.announceList) {
            if (this.announceList[i].list_content.trim() == "") {
              continue;
            }
            obj = {
              list_order: order++,
              list_content: this.announceList[i].list_content
            };
            data.push(obj);
          }
          saveBulletin(data).then(res => {
            if (res.code === "1") {
              this.$message.success("已發佈！");
              this.getAnnounce();
            } else {
              this.$message.error(res.msg);
            }
          });
        })
        .catch(() => {
          this.$message.info("已取消！");
        });
    },
    // 添加
    handleAddNewBtnClick() {
      this.announceList.push({
        list_order: Number(this.announceList.length) + 1,
        list_content: ""
      });
    },
    // 處理操作點擊事件
    handleOpera(event, index) {
      let item = this.announceList[index];
      switch (event) {
        case "top":
          this.announceList.splice(index, 1);
          this.announceList.splice(index - 1, 0, item);
          break;
        case "down":
          this.announceList.splice(index, 1);
          this.announceList.splice(index + 1, 0, item);
          break;
        case "delte":
          this.announceList.splice(index, 1);
          break;
      }
    }
  },
  components: {
    SvgBtn
  }
};
</script>

<style lang="less" scoped>
.announce {
  > div:nth-child(1) {
    font-size: 18px;
    color: #0092ff;
  }
  .content {
    > p {
      margin-left: 1rem;
      color: #a5b8c5;
    }
  }
  .opera {
    margin-left: 0.8rem;
    width: 68px;
    user-select: none;
    img {
      cursor: pointer;
    }
    span {
      width: 18px;
      height: 18px;
    }
  }
  .single-wrapper {
    padding: 0.5rem 0;
    box-sizing: border-box;
  }
  .inputBox {
    position: relative;
    width: 70%;
  }
  input {
    margin-left: 0.25rem;
    box-sizing: border-box;
    padding: 0.125rem 0.375rem;
    border: 1px solid #d3dfe7;
    width: 100%;
    background-color: #eff9ff;
    transition-duration: 0.3s;
    &:focus {
      outline: none;
      border: 1px solid #0092ff;
    }
  }
  .input-length {
    position: absolute;
    right: 0;
    font-size: 12px;
    color: #a5b8c5;
  }
  .addNewBtn {
    margin-top: 1rem;
    margin-left: 1rem;
    width: 100px;
    height: 30px;
    img {
      margin-right: 0.5rem;
    }
  }
  .svg-btn-box {
    margin-left: -12px;
    margin-top: 20px;
    > div {
      position: relative;
      user-select: none;
    }
    .svgBtn {
      transform: scaleX(0.8);
    }
    span {
      position: absolute;
      font-size: 18px;
      color: #fff;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      cursor: pointer;
    }
  }
}
</style>
