<template>
  <div class="z-flex info">
    <div v-for="item in statistics" :key="item.id">
      <div :style="{color: item.ename == 'wait_num' && item.num > 0 ? '#ff0000': ''}">
        {{ item.num }}
      </div>
      <span>{{ item.name }}</span>
    </div>
  </div>
</template>

<script>
import { queryAllUsersStatistics } from "@/api/user";

export default {
  data(){
    return{
      // 統計信息
      statistics: {}
    }
  },
  created(){
    this.getStatistics()
  },
  methods:{
    // 獲取統計信息
    getStatistics(){
      const data = {}
      queryAllUsersStatistics(data).then(res=>{
        if(res.code === '1'){
          let mData = res.t
          this.statistics = [
            {ename: 'total_num',name:'註冊賬號總數', num: mData.total_num, id:'1'},
            {ename: 'send_num',name:'發單方賬號總數', num: mData.send_num, id:'2'},
            {ename: 'recv_num',name:'接單方賬號總數', num: mData.recv_num, id:'3'},
            {ename: 'active_num',name:'活躍賬號總數', num: mData.active_num, id:'4'},
            {ename: 'inactive_num',name:'不活躍賬號總數', num: mData.inactive_num, id:'5'},
            {ename: 'wait_num',name:'待審核賬號總數', num: mData.wait_num, id:'6'},
          ]
        }else{
          this.$message.error(res.msg)
        }
      })
    },
  }
};
</script>

<style lang="less" scoped>
.info {
  > div {
    margin-left: 60px;
    text-align: center;
    > div {
      font-size: 30px;
    }
    > span {
      font-size: 14px;
    }
  }
}
</style>
