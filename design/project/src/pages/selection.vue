<template>
  <div style="background:rgba(239, 249, 255, 1);padding:0 20px">
    <div class="cont_tit">
      <div style="display:flex;flex-direction: row;align-items:center">
        <div style="cursor: pointer;" @click="return_sele">
          <img src="../assets/imgs/return.png"  />
          <span style="margin-left:6px;color:rgba(98, 111, 127, 1);font-size:14px">返回</span>
        </div>
        <span style="margin-left:30px;color:rgba(0, 150, 255, 1);font-size:18px">选标</span>
      </div>
      <div style="display:flex;flex-direction: row; align-items: center">
        <div style="display:flex;flex-direction: column;">
          <span style="color:rgba(33, 47, 58, 1);font-size:24px">【治具】需求單號A3000028 需求量:5</span>
          <span
            style="color:rgba(98, 111, 127, 1);font-size:14px;margin-top:7px"
          >发布时间：2019-08-27 10:52:25</span>
        </div>
        <div
          style="width:70px;height:26px;background:rgba(0,150,255,1);border:1px solid rgba(0,150,255,1);border-radius:13px;font-size:16px;margin-left:23px;color:white;line-height:26px;text-align:center"
        >详情</div>
      </div>
      <div class="choice">
        <span>排序：竞争价位</span>
        <img src="../assets/imgs/xl_px.png" style="position:absolute;right:20px;top:13px" />
      </div>
    </div>
    <div  style="width:100%;display:flex;justify-content: center;align-items: center;margin-top:37px"  >
      <span style="font-size:14px;color:rgba(0, 150, 255, 1)">当前项目投标单位数</span>
      <span style="font-size:36px;color:rgba(0, 150, 255, 1);margin-left:10px">{{this.number}}</span>
    </div>
    <div>
      <table>
        <thead  class="table_tit">
          <tr v-for="(item,index) in list_a" :key="index">
            <th >{{item.ordinal}}</th>
            <th >{{item.corporation}}</th>
            <th >{{item.quote}}</th>
            <th >{{item.currency}}</th>
            <th >{{item.leadtime}}</th>
            <th >{{item.superiority}}</th>
            <th >{{item.operation}}</th>
          </tr>
        </thead>
        <tbody  class="table_body">
          <tr v-for="(item,index) in list" :key="index">
            <td>{{item.ordinal}}</td>
            <td>{{item.corporation}}</td>
            <td>{{item.quote}} <img src="../assets/imgs/repertoire.png" style="margin-left:50px;margin-right:5px"/><span style="color:rgba(0, 150, 255, 1)">清單</span></td>
            <td>{{item.currency}}</td>
            <td>{{item.leadtime}}</td>
            <td>{{item.superiority}}</td>
            <td style="color:rgba(0, 150, 255, 1);cursor:pointer;">{{item.operation}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="fy_paging" >
      <el-pagination background layout="prev, pager, next" :total="1000"
      prev-text="上一頁"  next-text="下一頁"> <!-- 分頁 -->
      </el-pagination>
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
      list_a:[
        {ordinal:'序號',corporation:'法人', quote:'報價',currency:'幣別', leadtime:'交期',superiority:'優勢說明',operation:'操作'}
      ],
      list:[
         {ordinal:'1',corporation:'鸿富锦成都', quote:'50000',currency:'USB', leadtime:'2019-09-30',superiority:'本公司近期新增先进设备，大量引进优秀人才，只为获得客户更好的认可。',operation:'选为中标'},
         {ordinal:'1',corporation:'鸿富锦成都', quote:'50000',currency:'USB', leadtime:'2019-09-30',superiority:'本公司近期新增先进设备，大量引进优秀人才，只为获得客户更好的认可。',operation:'选为中标'},
         {ordinal:'1',corporation:'鸿富锦成都', quote:'50000',currency:'USB', leadtime:'2019-09-30',superiority:'本公司近期新增先进设备，大量引进优秀人才，只为获得客户更好的认可。',operation:'选为中标'},
         {ordinal:'1',corporation:'鸿富锦成都', quote:'50000',currency:'USB', leadtime:'2019-09-30',superiority:'本公司近期新增先进设备，大量引进优秀人才，只为获得客户更好的认可。',operation:'选为中标'},
         {ordinal:'1',corporation:'鸿富锦成都', quote:'50000',currency:'USB', leadtime:'2019-09-30',superiority:'本公司近期新增先进设备，大量引进优秀人才，只为获得客户更好的认可。',operation:'选为中标'}
      ],
      number:57
    };
  },
  methods:{
    return_sele:function(){
      this.$router.push('/');
    }
  }
};
</script>