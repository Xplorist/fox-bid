<template>
  <div>
    <div
      v-for="(item, index) in comments"
      :key="item.id + index"
      class="commentWrapper"
    >
      <div class="comment">
        <div class="commentator">
          <span>{{ item.creatorId.slice(0, 6) }}</span>
          <span>{{ item.createTime }}</span>
        </div>
        <p>{{ item.content }}</p>
        <el-button
          class="mt-10"
          @click="handleReply(item)"
          icon="el-icon-chat-round"
          size="mini"
          round
        >
          回复
        </el-button>
      </div>
      <div class="child-comment">
        <div v-if="item.replyList">
          <div
            v-for="(item2, index2) in item.replyList"
            :key="item2.id + index2"
            class="commentWrapper"
          >
            <div class="comment">
              <div class="commentator">
                <span>{{ item2.creatorId.slice(0, 6) }}</span>
                <span>{{ item2.createTime }}</span>
              </div>
              <p>
                <a v-if="item2.parentId" href="" class="comment-for">
                  @{{ item2.parentId.slice(0, 6) }}
                </a>
                {{ item2.content }}
              </p>
              <el-button
                class="mt-10"
                @click="handleReply(item, item2)"
                icon="el-icon-chat-round"
                size="mini"
                round
              >
                回复
              </el-button>
            </div>
          </div>
        </div>
        <div
          v-show="isReply && commentId === item.id"
          class="comment-box mt-20"
        >
          <CommentBox
            :showCancel="true"
            :showBaseText="showBaseText"
            :baseText="commentFor"
            @handleCancel="handleCancel"
            @handleConfirm="handleConfirm"
          ></CommentBox>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { sendReply } from "@/api/forumPosts";
import CommentBox from "../Textarea";

export default {
  data() {
    return {
      comments: [],
      // 是否回复
      isReply: false,
      // 應該打開評論框的評論id
      commentId: "",
      // 被評論的評論id
      replyId: "",
      // 被評論人的名字
      commentFor: "",
      // 是否显示被评论人名字
      showBaseText: false
    };
  },
  props: {
    replyData: {
      type: Object
    }
  },
  components: {
    CommentBox
  },
  created() {
    let comments = this.replyData.replyList;
    for (let i in comments) {
      if (comments[i].replyList && comments[i].replyList.length > 0) {
        comments[i].replyList = this.handleComments(comments[i].replyList);
      }
    }
    this.comments = comments;
  },
  methods: {
    // 處理數據
    handleComments(allData) {
      let arr = [];
      for (let i in allData) {
        if (allData[i].replyList && allData[i].replyList.length > 0) {
          arr.push(allData[i]);
          arr = [...arr, ...this.handleComments(allData[i].replyList)];
        } else {
          arr.push(allData[i]);
        }
      }
      return arr;
    },

    // 回復
    handleReply(data1, data2) {
      if (data2) {
        this.replyId = data2.id;
        this.commentFor = `@ ${data2.creatorId.slice(0, 6)}`;
      } else {
        this.replyId = data1.id;
        this.commentFor = `@ ${data1.creatorId.slice(0, 6)}`;
      }
      this.isReply = true;
      this.showBaseText = true;
      this.commentId = data1.id;
    },

    // 取消 輸入
    handleCancel() {
      this.isReply = false;
    },

    // 確認 輸入
    handleConfirm(val) {
      const reqData = {
        replyType: "toReply",
        bbsPostId: this.replyData.id,
        parentId: this.replyId,
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
// @import "../../../style/variable.less";

.comment {
  padding: 14px 0;
  border-bottom: 1px solid #eee;
  > .commentator span:nth-child(1) {
    font-size: 15px;
  }
  > .commentator span:nth-child(2) {
    margin-left: 1rem;
    font-size: 12px;
    color: #969696;
  }
  > p {
    margin-top: 10px;
    font-size: 16px;
    line-height: 1.5;
    word-break: break-word;
    // color: #676767;
    > .comment-for {
      color: #8cde59;
    }
  }
}

.child-comment {
  box-sizing: border-box;
  padding-left: 4rem;
}
</style>
