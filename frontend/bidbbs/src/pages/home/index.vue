<template>
  <div class="bb-home-wrapper">
    <div class="container">
      <el-row :gutter="40">
        <el-col :span="16">
          <div>
            <noteOfArticle
              v-for="(item, index) in articalList"
              :key="item.id + index"
              :data="item"
            ></noteOfArticle>
          </div>
        </el-col>
        <el-col :span="8">
          <div>
            <div class="block">
              <el-carousel height="150px">
                <el-carousel-item v-for="item in 3" :key="item">
                  <h3 class="small">{{ item }}</h3>
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import noteOfArticle from "@/components/Note";
import { listPostsByPage } from "@/api/forumPosts";

export default {
  data() {
    return {
      // 列表
      articalList: null,
      value: new Date()
    };
  },
  components: {
    noteOfArticle
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      const reqData = {
        pageIndex: 1,
        pageSize: 10
      };
      listPostsByPage(reqData).then(res => {
        if (res.code === "1") {
          let data = res.t;
          this.articalList = data.list;
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.bb-home-wrapper {
  background-color: #fff;
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #e1f3d8;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #f0f9eb;
  }
}
</style>
