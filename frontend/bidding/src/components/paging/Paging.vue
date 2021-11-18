<template>
  <!-- 分页 -->
  <div class="paging">
    <el-pagination
      layout="prev, pager, next"
      :total="total"
      :current-page="currentPage"
      :page-size="pageSize"
      prev-text="上一頁"
      next-text="下一頁"
      @current-change="sendPage"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  props: {
    total:{
      type: Number
    },
    currentPage: {
      type: Number,
      default: 1
    },
    pageSize:{
      type: Number,
    }
  },
  methods: {
    sendPage(page){
      this.$emit('getCurrentPage', page);
    }
  }
};
</script>

<style lang="less" scoped>
// 分页
.paging {
  padding: 30px;
  box-sizing: border-box;
  text-align: center;
  /deep/ .number {
    width: 30px;
    height: 30px;
    line-height: 30px;
    background: url(../../assets/imgs/index/pageIndBG.png) no-repeat center;
    color: #12222e;
    font-size: 14px;
    &.active {
      color: #fff;
      background: url(../../assets/imgs/index/pageindBG-ac.png) no-repeat center;
    }
    &:hover {
      color: #fff;
    }
  }
  /deep/ .el-icon {
    color: #12222e;
    background-color: rgba(0,0,0,0);
  }
  /deep/ .btn-next,
  /deep/ .btn-prev {
    user-select: none;
    padding: 0 10px;
    width: 70px;
    height: 30px;
    background: url(../../assets/imgs/index/pageBG.png) no-repeat center /
      contain;
  }
}
</style>