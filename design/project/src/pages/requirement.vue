<template>
  <div style="background:rgba(239, 249, 255, 1);padding:10px 20px">
    <div class="cont_fbxq">
      <span>發佈需求</span>
      <div>
        <img src="../assets/imgs/repertoire.png" />
        <span style="margin-left:5px">預覽</span>
      </div>
    </div>
    <div style="display:flex;flex-direction: row;justify-content: space-between;">
      <div style="display:flex;flex-direction: column;color:rgba(15, 30, 41, 1);font-size:16px">
        <span style="font-size:18px;color:rgba(0, 146, 255, 1);margin-top:30px">基本信息</span>
        <div style="display:flex;flex-direction: row;">
          <div style="width:110px;display:flex;flex-direction: column;">
            <span v-for="item in list" :key="item.name" style="margin-top:30px">{{item.name}}</span>
          </div>
          <div style="display:flex;flex-direction: column;margin-top:30px">
            <span>Mac(I)製造一處</span>
            <span style="margin-top:30px">鴻富錦精密（成都）有限公司</span>
            <div class="purpose" style="margin-top:18px">
              <span>治具</span>
              <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
            </div>
            <input type="text" placeholder="2019-09-30" class="purpose" style="height:34px">
            <input type="text"  class="purpose" style="height:34px">
            <div class="purpose" style="border:none; display: flex;flex-direction: row;">
              <span>5073-123456</span>
              <div @click="conceal" style="margin-left:70px">
                <img src="../assets/imgs/visible.png" v-if="is_show == true" />
                <img src="../assets/imgs/invisible.png" v-if="is_show == false" />
                <span style="margin-left:10px">显示聯繫電話</span>
              </div>
            </div>
            <input type="text"  class="purpose" style="height:34px">
            <div class="purpose" style="border:none; display: flex;flex-direction: row;">
              <!-- <div @click="change_money">
                <img src="../assets/imgs/visible.png" />
                <img src="../assets/imgs/invisible.png"/>
                <span style="margin-left:10px">RMB</span>
              </div>
              <div style="margin-left:70px"  @click="change_money">
                <img src="../assets/imgs/visible.png" />
                <img src="../assets/imgs/invisible.png">
                <span style="margin-left:10px">USB</span>
              </div> -->
              <label style="display:flex;align-items:center">
                <input type="radio" name="sex" checked="checked"><span style="margin-top:4px">RMB</span>
              </label>
              <label style="display:flex;align-items:center;margin-left:70px">
                 <input type="radio" name="sex"><span style="margin-top:4px">USB</span>
              </label>
            </div>
            <input type="text"  class="purpose" style="height:34px">
            <div class="purpose">
              <span>SHZBG</span>
              <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
            </div>
            <input type="text"  class="purpose" style="height:34px" placeholder="2019-09-01 02:30:00">
            <input type="text"  class="purpose" style="height:34px" placeholder="2019-09-01 02:30:00">
            <div class="purpose">
              <span>貨交工廠（DDU）</span>
              <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
            </div>
          </div>
        </div>
      </div>
      <div style="min-width:80px"></div>
      <div style="display:flex;flex-direction: column;">
        <!-- 右 -->
        <span style="font-size:18px;color:rgba(0, 146, 255, 1);margin-top:30px">製作要求</span>
        <el-input style="margin-top:25px"
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
        <div style="display:flex;flex-direction: row;justify-content: space-between;margin-top:40px">
          <div style="display:flex;flex-direction: row;align-items: center;">
            <span style="color:rgba(0, 146, 255, 1);font-size:18px">图档附件：</span>
            <div class="add_botton">
              <img src="../assets/imgs/addimg.png" />
              <span style="font-size:16px;color:white;margin-left:10px">批量添加</span>
            </div>
          </div>
          <div style="display:flex;flex-direction: row;align-items: center;">
            <!-- <div style="display:flex;flex-direction: row;align-items: center;">
              <img src="../assets/imgs/open_botton.png" />
              <span style="margin-left:5px;color:rgba(15, 30, 41, 1);font-size:16px">公開</span>
            </div>
            <div style="display:flex;flex-direction: row;align-items: center;margin-left:30px">
              <img src="../assets/imgs/clouse_botton.png" />
              <span style="margin-left:5px;color:rgba(15, 30, 41, 1);font-size:16px">隱藏</span>
            </div> -->
             <label style="display:flex;align-items:center">
                <input type="radio" name="sexa" checked="checked"><span style="margin-left:5px;color:rgba(15, 30, 41, 1);font-size:16p">公开</span>
              </label>
              <label style="display:flex;align-items:center;margin-left:70px">
                 <input type="radio" name="sexa"><span style="margin-left:5px;color:rgba(15, 30, 41, 1);font-size:16px">隐藏</span>
              </label>
            <span style="font-size:16px;color:rgba(165, 184, 197, 1)">（隱藏后只對中標單位公開圖檔）</span>
          </div>
        </div>
        <div class="add_png">
          <div class="png_box" v-for="(item,index) in list_png" :key="index" @click="tj_sc_png(index)" >
            <img v-bind:src="item.src" style="margin-left: 10px" />
            <span style="font-size:16px;color:rgba(18, 34, 46, 1);margin-left:5px" >{{item.name}}.{{item.suffix}}</span>
            <!-- <img v-bind:src="item.src_a" style="margin-left: 50px" v-show="add_png" />
            <img v-bind:src="item.src_b" style=" position: absolute;right:-7px;top:-7px" v-show="add_png"/> -->
          </div>
        </div>
        <div style="display:flex; flex-direction: row;align-items: center;margin-top:20px">
          <img src="../assets/imgs/paperclip.png" />
          <span style="margin-left:5px;color:rgba(15, 30, 41, 1);font-size:16px">上传零件清單</span>
          <img src="../assets/imgs/tj_blue.png" style="margin-left:10px"/>
          <span style="margin-left:30px;color:rgba(0, 150, 255, 1);font-size:16px">下载模板</span>
        </div>
      </div>
    </div>
    <div style="width:100%;display:flex;flex-direction: row;justify-content: center;margin-top:50px"> 
      <div style="position: relative;">
        <img src="../assets/imgs/baocun.png">
        <span style="position: absolute;top:20px;left:80px;font-size;20px;color:white">保存</span>
      </div>
      <div style="position: relative;margin-left:20px">
        <img src="../assets/imgs/fabu.png">
        <span style="position: absolute;top:20px;left:70px;font-size;20px;color:white">立即發佈</span>
      </div>
    </div>
  </div>
</template>
<style>
@import url(../assets/css/subpage.css);
</style>
<script>
export default {
  data() {
    return {
      list: [
        { name: "需求单位:" },
        { name: "交易法人名称:" },
        { name: "用途:" },
        { name: "交货时间:" },
        { name: "交货地点:" },
        { name: "联系电话:" },
        { name: "接受總價:" },
        { name: "币别:" },
        { name: "需求数量:" },
        { name: "開標範圍:" },
        { name: "競價開始時間:" },
        { name: "競價結束時間:" },
        { name: "交貨模式:" }
      ],
      list_png: [
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        },
        {
          src: require("../assets/imgs/pdf.png"),
          src_a: require("../assets/imgs/pdf_ck.png"),
          src_b: require("../assets/imgs/clouse_pdf.png"),
          name: "k1",
          suffix: "pdf"
        }
      ],
      is_show: true,
      textarea: ''
    };
  },
  methods: {
    conceal: function() {
      this.is_show = !this.is_show;
    },
  }
};
</script>