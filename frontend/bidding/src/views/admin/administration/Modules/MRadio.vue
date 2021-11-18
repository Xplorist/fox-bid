<template>
  <div class="radio-box">
    <input
      type="radio"
      :id="rId"
      :name="rName"
      :value="rValue"
      :checked="rChecked"
      @click="handleRadioChange(rName, rValue)"
    />
    <label :for="rId" :style="{ color: rColor }">{{ rLabel }}</label>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  watch: {},
  methods: {
    handleRadioChange(name, val) {
      this.$emit("radioValChange", name, val);
    }
  },
  props: {
    rId: {
      type: String
    },
    rName: {
      type: String,
      required: true
    },
    rValue: {
      type: String,
      required: true
    },
    rChecked: {
      type: Boolean,
      default: false
    },
    rLabel: {
      type: String
    },
    rColor: {
      type: String,
      default: "#5b6974"
    }
  }
};
</script>

<style lang="less" scoped>
.radio-box {
  height: 20px;
  line-height: 20px;
  input {
    margin: 0 10px 0 10px;
    vertical-align: middle;
    appearance: none;
    position: relative;
    cursor: pointer;
    &::before {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      display: inline-block;
      content: "";
      width: 12px;
      height: 12px;
      box-sizing: border-box;
      border: 1px solid #4f5d68;
      border-radius: 50%;
    }
    &::after {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      display: inline-block;
      content: "";
      width: 6px;
      height: 6px;
      border-radius: 50%;
    }
    &:focus {
      outline: none;
    }
    &:checked::before {
      background-color: #0096ff;
      border-color: #0096ff;
    }
    &:checked::after {
      background-color: #12222e;
    }
  }
  label {
    font-size: 14px;
    cursor: pointer;
  }
}
</style>
