<template>
  <div v-if="data" class="bb-home-wrapper">
    <div class="container">
      <el-row :gutter="20">
        <el-col :span="16">
          <div class="left-wrapper">
            <div class="text">
              <div class="information flex-start">
                <el-avatar
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                >
                  article
                </el-avatar>
                <strong class="author-name">
                  {{ data.creatorId.slice(0, 8) }}
                </strong>
              </div>
              <div class="title">
                <h2>
                  {{ data.title }}
                </h2>
                <div class="tag">
                  <span>发布于：</span>
                  <span>{{ data.createTime }}</span>
                </div>
              </div>
              <!-- 內容 -->
              <article class="article">
                <div v-html="data.content"></div>
              </article>
            </div>
            <div class="comments">
              <div class="commit-comment">
                <CommentBox @handleConfirm="handleConfirm"></CommentBox>
              </div>
              <div v-if="data.replyList" class="history-comments mt-20">
                <div class="title flex-start">
                  <h3>全部評論</h3>
                  <span>
                    {{ data.replyList.length }}
                  </span>
                </div>
                <div class="comments-area mt-20">
                  <CommentsList :replyData="data" />
                </div>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="right-wrapper">
            廣告/推薦/排行
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import CommentBox from "@/components/comment/Textarea";
import CommentsList from "@/components/comment/List";
import { getPostById, sendReply } from "@/api/forumPosts";

export default {
  data() {
    return {
      data: null
    };
  },
  components: {
    CommentBox,
    CommentsList
  },
  created() {
    this.$route.params.articleID ? this.getData() : this.$route.push("/");
  },
  methods: {
    getData() {
      const reqData = {
        postId: this.$route.params.articleID
      };
      getPostById(reqData).then(res => {
        if (res.code === "1") {
          this.data = res.t;
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    // 評論發佈按鈕點擊事件
    handleConfirm(val) {
      const reqData = {
        replyType: "toPost",
        bbsPostId: this.data.id,
        parentId: null,
        content: val
      };
      sendReply(reqData).then(res => {
        if (res.code === "1") {
          this.$message.success("回復成功！");
          this.$router.go(0);
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../style/variable";

.bb-home-wrapper {
  .left-wrapper > div,
  .right-wrapper {
    padding: 20px 15px;
    text-align: left;
    background-color: #fff;
  }
  .left-wrapper .text {
    .information .el-avatar {
      margin-right: 0.5rem;
    }
    .information .author-name {
      color: @mainColor;
      font-weight: 600;
    }
    .title {
      > h2 {
        color: @articleDetailTitleColor;
        font-size: @articleDetailTitleSize;
        line-height: 1.5;
        margin: 10px 0 4px 0;
      }
      .tag > span {
        color: #6c757d;
        margin-right: 0.5rem;
      }
    }
    .article {
      margin: 20px 0;
      line-height: 1.75;
      > p {
        margin-bottom: 1.25rem;
      }
      /deep/ a {
        color: rgba(@mainColor, 0.7);
        &:hover {
          color: @mainColor;
        }
      }
    }
  }
  .left-wrapper .comments {
    margin-top: 20px;
    .history-comments .title {
      border-left: 4px solid @mainColor;
      padding-left: 8px;
      > h3 {
        font-size: 18px;
      }
      > span {
        margin-left: 8px;
        font-size: 14px;
      }
    }
  }
}
</style>
