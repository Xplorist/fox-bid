<template>
  <div class="m-option" @click="handleClick(value)">
    {{ label }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      text: "option"
    };
  },
  props: ["label", "value"],
  methods: {
    handleClick(val) {
      this.$emit("choosedVal", val);
    }
  }
};
</script>

<style lang="less" scoped>
.m-option {
  height: 40px;
  border-top: 1px solid #a4bccd;
  transition-duration: .2s;
  &:hover {
    background-color: #a4bccd;
    color: #fff;
  }
}
</style>
