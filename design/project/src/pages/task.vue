<template>
    <div>
        <div class="content_top">
            <div style="color:rgba(18, 34, 46, 1);font-size:18px;padding-top:17px;">我的任务</div>
            <div style="margin-top:20px;">
              <span v-for="item in list_task" :key="item.name" style="margin-right:60px">
                <span style>{{item.name}}</span>
                <span>【{{item.number}}】</span>
              </span>
            </div>
          </div>
          <div class="content_bottom">
            <div style="height:57px;color:rgba(18, 34, 46, 1);font-size:18px;line-height:57px">我的项目</div>
            <div style="width:100%">
              <el-radio-group v-model="radio1" style="width:100%">
                <el-radio-button label="全部（59）"></el-radio-button>
                <el-radio-button label="進行中（2）"></el-radio-button>
                <el-radio-button label="待选标（1）"></el-radio-button>
                <el-radio-button label="待收货"></el-radio-button>
                <el-radio-button label="待评价（3）"></el-radio-button>
                <el-radio-button label="已完成（53）"></el-radio-button>
                <el-radio-button label="待发布（1）"></el-radio-button>
              </el-radio-group>
            </div>
            <div class="content_table" v-for="item in list_table" :key="item.id"> 
              <div style="display:flex;flex-direction: row" v-if="item.schedule != '已完成'">
                <div style="width:320px">
                    <span style="color:rgba(0, 146, 255, 1);font-size:18px">${{item.money}}</span>
                    <!-- <span style="color:rgba(15, 30, 41, 1);font-size:16px;margin-left:20px">【<span>{{item.type}}</span>】需求單號{{item.number}}</span> -->
                    <a href="recv/page/particulars.html"><span style="color:rgba(98, 111, 127, 1);font-size:16px;margin-left:20px">【<span>{{item.type}}</span>】需求單號{{item.number}}</span></a> 
                    <div style="color:rgba(98, 111, 127, 1);font-size:14px;margin-top:15px">发布时间：{{item.time}}</div>
                </div>
                <div>
                  <div style="color:rgba(0, 146, 255, 1);font-size:14px">{{item.Company}}个单位已參與</div>
                  <div style="color:rgba(98, 111, 127, 1);font-size:14px;margin-top:15px">
                    <span>瀏覽:{{item.browse}}</span>
                    <span style="margin-left:30px">收藏:{{item.Collection}}</span>
                  </div>
                </div>
              </div>
              <div style="display:flex;flex-direction: row" v-if="item.schedule == '已完成'">
                <div style="width:320px">
                    <span style="color:rgba(98, 111, 127, 1);font-size:18px">${{item.money}}</span>
                    <a href="recv/page/particulars.html"><span style="color:rgba(98, 111, 127, 1);font-size:16px;margin-left:20px">【<span>{{item.type}}</span>】需求單號{{item.number}}</span></a> 
                    <div style="color:rgba(98, 111, 127, 1);font-size:14px;margin-top:15px">发布时间：{{item.time}}</div>
                </div>
                <div>
                  <div style="color:rgba(98, 111, 127, 1);font-size:14px">{{item.Company}}个单位已參與</div>
                  <div style="color:rgba(98, 111, 127, 1);font-size:14px;margin-top:15px">
                    <span>瀏覽:{{item.browse}}</span>
                    <span style="margin-left:30px">收藏:{{item.Collection}}</span>
                  </div>
                </div>
              </div>
              <div style="display:flex;flex-direction: row;align-items: center;">
                <div style="margin-right:150px;color:rgba(0, 150, 255, 1);font-size:16px"  v-if="item.schedule !='待评价' && item.schedule !='已完成' && item.schedule != '待發佈'">
                  投标剩余:{{item.Surplus}}
                </div>
                <div style="margin-right:150px;color:rgba(0, 150, 255, 1);font-size:16px"  v-if="item.schedule == '待發佈'">
                   {{item.Surplus}}
                </div>
                 <div style="margin-right:150px;color:red;font-size:16px;border:1px solid red;width:200px;height:30px;line-height:30px;text-align:center"  v-if="item.schedule =='待评价' || item.schedule=='已完成' ">
                  中标单位：{{item.department}}
                </div>
                <div style="display: flex;flex-direction:column;align-items: center;">
                  <span style="color:rgba(0, 150, 255, 1);font-size:16px" v-if="item.schedule == '待發佈'">{{item.schedule}}</span>
                  <span style="color:rgba(15, 30, 41, 1);font-size:16px" v-if="item.schedule == '進行中'">{{item.schedule}}</span>
                  <span style="color:rgba(229, 19, 19, 1);font-size:16px" v-if="item.schedule == '待选标'">{{item.schedule}}</span>
                  <span style="color:rgba(229, 19, 19, 1);font-size:16px" v-if="item.schedule == '待收货'">{{item.schedule}}</span>
                  <span style="color:rgba(0, 150, 255, 1);font-size:16px" v-if="item.schedule == '待评价'">{{item.schedule}}</span>
                  <span style="color:rgba(98, 111, 127, 1);font-size:16px" v-if="item.schedule == '已完成'">{{item.schedule}}</span>
                   <span style="color:rgba(229, 19, 19, 1);font-size:16px" v-if="item.schedule == '待确定'">{{item.schedule}}</span>
                  <div class="operation_tab" @click="show_Choice(item)">
                    <span style="color:white;font-size:14px" v-if="item.schedule != '已完成'">操作</span>
                    <span style="color:white;font-size:14px" v-if="item.schedule === '已完成'">查看</span>
                    <img src="../assets/imgs/xl_xiala.png" style="margin-left:12px" >

                    <div class="click_Pulldown" >  <!-- 點擊操作彈出下拉框 -->
                      <div  v-for="(itema,index) in list_Pulldowna" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()"  @click="xm_jump(itema)"
                            v-show="item.fb_show && item.schedule ==='待發佈'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldownb" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()"
                          v-show="item.fb_show && item.schedule ==='進行中'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldownc" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()" @click="xm_jump(itema)"
                          v-show="item.fb_show && item.schedule ==='待选标'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldownd" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()" @click="xm_jump(itema)"
                          v-show="item.fb_show && item.schedule ==='待收货'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldowne" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()" @click="xm_jump(itema)"
                          v-show="item.fb_show && item.schedule ==='待评价'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldownf" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()" @click="xm_jump(itema)"
                          v-show="item.fb_show && item.schedule ==='已完成'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                      <div v-for="(itema,index) in list_Pulldowng" :key="itema.id" @mouseover="addcolor(index)" @mouseout="outcolor()" @click="xm_jump(itema)"
                          v-show="item.fb_show && item.schedule ==='待确定'" v-bind:class="{change_bagea:index === addcolors}">{{itema.name}}</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="fy_paging">
              <el-pagination background layout="prev, pager, next" :total="1000"
              prev-text="上一頁"  next-text="下一頁"> <!-- 分頁 -->
              </el-pagination>
            </div>
            
          </div>
    </div>
</template>
<style>
@import url("../assets/css/home.css");
</style>
<script>
export default {
  data() {
    return {
      list_task: [
        { name: "待签核", number: "0" },
        { name: "待选标", number: "0" },
        { name: "待收货", number: "0" },
        { name: "新动态", number: "0" }
      ],
      list_table:[],
      list_Pulldowna:[ //待发布
        {name:'修改',id:1},
        {name:'發佈', path:'/requirement',id:2},
        {name:'取消',id:3}
      ],
      list_Pulldownb:[ //进行中
        {name:'查看投標',id:4},
        {name:'取消',id:5}
      ],
      list_Pulldownc:[//待选标
        {name:'选标',path:'/selection',id:6},
        {name:'查看',id:7},
      ],
      list_Pulldownd:[ //待收货
        {name:'查看投標',id:8},
        {name:'取消',id:9}
      ],
      list_Pulldowne:[ //待评价
        {name:'查看',id:10},
        {name:'评价',id:11,path:'/evaluate'}
      ], 
      list_Pulldownf:[ //已完成
        {name:'查看投標',id:12},
        {name:'取消',id:13}
      ],
      list_Pulldowng:[ //待确定
        {name:'查看投標',id:14},
        {name:'取消',id:15}
      ],
      change_patt: "0", //左菜单点击颜色变化
      radio1: '全部（59）',
      addcolors:''
    };
  },
  methods: {
    change_pattern: function(index) {
      this.change_patt = index;
    },
    addcolor:function(index){
      this.addcolors= index
    },
    show_Choice:function(item){
      item.fb_show = !item.fb_show
    },
    xm_jump:function(itema){
      this.$router.push(itema.path);
    },
  },
  mounted(){
    let data = [
      {id:'1',money:'5000',type:'治具',time:'2019-08-12 10:52:10',number:'A3000028',Company:'3',browse:'222',Collection:'333',schedule:'待發佈',Surplus:'',department:'Mac(I)制一处'},
      {id:'2',money:'5000',type:'治具',time:'2019-08-12 10:52:10',number:'A3000028',Company:'3',browse:'222',Collection:'333',schedule:'進行中',Surplus:'15天17小时5分39秒',department:'Mac(I)制一处'},
      {id:'3',money:'5000',type:'治具',time:'2019-08-12 10:52:10',number:'A3000028',Company:'3',browse:'222',Collection:'333',schedule:'待选标',Surplus:'15天17小时5分39秒',department:'Mac(I)制一处'},
      {id:'5',money:'5000',type:'治具',time:'2019-08-12 10:52:10',number:'A3000028',Company:'3',browse:'222',Collection:'333',schedule:'待评价',Surplus:'15天17小时5分39秒',department:'Mac(I)制一处'},
      {id:'4',money:'5000',type:'治具',time:'2019-08-12 10:52:10',number:'A3000028',Company:'3',browse:'222',Collection:'333',schedule:'已完成',Surplus:'15天17小时5分39秒',department:'Mac(I)制一处'}
    ];
    data.forEach(item => item.fb_show = false);
    //  data.forEach(function(item) {
    //   item.fb_show = false;
    // });
    this.list_table = data;
  }
};
</script>