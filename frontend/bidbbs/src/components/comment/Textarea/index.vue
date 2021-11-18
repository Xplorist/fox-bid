<template>
  <div>
    <el-input
      type="textarea"
      :rows="3"
      placeholder="請輸入評論..."
      v-model="textarea"
      resize="none"
    >
    </el-input>
    <div class="opera-btn flex-end">
      <el-button type="primary" size="mini" round @click="handleConfirm()"
        >確定</el-button
      >
      <el-button v-show="showCancel" size="mini" round @click="handleCancel()"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textarea: ""
    };
  },
  props: {
    showCancel: {
      type: Boolean
    },
    showBaseText: {
      type: Boolean,
      default: false
    },
    baseText: {
      type: String,
      default: ""
    }
  },
  watch: {
    showBaseText: function(val) {
      if (val) {
        this.textarea = this.baseText + " ";
      } else {
        this.textarea = "";
      }
    },
    baseText: function(val) {
      if (val) {
        this.textarea = this.baseText + " ";
      }
    }
  },
  methods: {
    handleCancel() {
      this.$emit("handleCancel", true);
    },
    handleConfirm() {
      let text = this.baseText
        ? this.textarea.slice(this.baseText.length + 1)
        : this.textarea;
      this.$emit("handleConfirm", text);
    }
  }
};
</script>

<style lang="less" scoped>
@import "../../../style/variable";

.opera-btn {
  margin: 1rem 0;
}
</style>
