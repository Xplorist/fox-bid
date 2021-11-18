<template>
  <div style="height:100%">
    <el-container style="height:100%">
      <el-aside width="280px" style="background: rgba(18, 34, 46, 1);">
        <div class="ht_logobox">
          <img src="../assets/imgs/ht_logo.png" />
        </div>
        <div style="margin-top:40px;height:132px">
          <img src="../assets/imgs/head_pp.png" />
        </div>
        <div
          style="display: flex;align-items: center;flex-direction: row;height:65px;justify-content: center"
        >
          <img src="../assets/imgs/tx_log.png" />
          <span style="margin-left:10px;color:rgba(156, 175, 189, 1);font-size:18px">Mac(I)制造一处</span>
        </div>
        <div class="left_box" v-for="(item,index) in list"  :key="item.name"  @click="change_pattern(index,item)" style="cursor: pointer;"
          v-bind:class="{left_pattern:index === change_patt}" 
        >{{item.name}}</div>
      </el-aside>
      <el-container>
        <el-header class="nav_box">
          <div class="search_box">
            <input type="text" placeholder="请输入信息" />
            <div>搜索</div>
          </div>
          <div style="height:48px;position:relative;cursor: pointer;">
            <img src="../assets/imgs/Release.png"/>
            <span style="color:white;font-size:18px;position:absolute;top:-5px;left:37px">发布任务</span>
          </div>
        </el-header>
        <el-main > <!--  中间内容 -->
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style>
@import url("../assets/css/home.css");
</style>
<script>
export default {
  data() {
    return {
      list: [ 
        { name: "訂單管理",path:'/'},
        { name: "个人资料",path:'/profile' },
        { name: "评价管理" ,path:'/reviewManagement'},
        { name: "消息" },
        { name: "设置" }
      ],
      change_patt: 0, //左菜单点击颜色变化
    };
  },
  methods: {
    change_pattern: function(index,item) {
      this.change_patt = index;
      this.$router.push(item.path);
    }
  },

};
</script>