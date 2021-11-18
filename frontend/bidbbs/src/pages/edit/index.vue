<template>
  <div class="bb-quill-editor-wrapper">
    <div class="container">
      <el-input v-model.trim="title" placeholder="請輸入標題"></el-input>
      <quill-editor
        ref="text"
        v-model.trim="content"
        class="myQuillEditor mt-20"
        :options="editorOption"
      />
      <el-button type="primary" class="mt-20" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
import { sendPost } from "@/api/forumPosts";
import { quillEditor } from "vue-quill-editor";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

export default {
  data() {
    return {
      title: "",
      content: "",
      editorOption: {
        modules: {},
        placeholder: "请输入正文"
      }
    };
  },
  components: {
    quillEditor
  },
  methods: {
    submit() {
      if (this.title && this.content) {
        const reqData = {
          title: this.title,
          content: this.content
        };
        sendPost(reqData).then(res => {
          if (res.code === "1") {
            this.$message.success("發佈成功，即將跳轉回主頁！");
            setTimeout(() => {
              this.$router.push("/");
            }, 2000);
          } else {
            this.$message.error(res.msg);
          }
        });
      } else {
        this.$message.warning("請檢查標題和正文");
      }
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../style/variable";

.bb-quill-editor-wrapper {
  background-color: #fff;
  .container {
    height: 100%;
  }
}

/deep/.quill-editor {
  .ql-editor {
    min-height: 200px !important;
  }
}

.el-button--primary {
  background-color: @mainColor;
  border-color: @mainBorderColor;
  &:hover {
    opacity: 0.8;
  }
}
</style>
