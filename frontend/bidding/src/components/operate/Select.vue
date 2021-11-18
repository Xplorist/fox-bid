<template>
  <div
    class="m-select"
    :class="focusFlag ? 'focus' : ''"
    @click.stop="handleSelectClick()"
  >
    {{ label }}:{{
      JSON.stringify(choosedItem) === "{}"
        ? subList[0].label
        : choosedItem.label
    }}
    <div class="option-box">
      <m-option
        v-for="item in subList"
        :key="item.value"
        :value="item.value"
        :label="item.label"
        @choosedVal="getChoosedVal"
      />
    </div>
  </div>
</template>

<script>
import MOption from "./Option";
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      // 是否選中
      focusFlag: false,
      // 選中的項
      choosedItem: {},
      routerPath: ""
    };
  },
  components: {
    MOption
  },
  props: ["subList", "label"],
  methods: {
    ...mapActions(["get_mSelectList"]),
    // 選中的 option 值
    getChoosedVal(val) {
      for (let i in this.subList) {
        if (this.subList[i].value == val) {
          this.choosedItem = this.subList[i];
        }
      }
    },
    // select 框點擊事件
    handleSelectClick() {
      let data = [];
      if (this.mSelectList.length == 0) {
        data = [
          {
            name: this.currentSelectName,
            focus: true
          }
        ];
      } else {
        let hasItem = false;
        data = JSON.parse(JSON.stringify(this.mSelectList));
        for (let i in data) {
          if (data[i].name == this.currentSelectName) {
            data[i].focus = !data[i].focus;
            hasItem = true;
          } else {
            data[i].focus = false;
          }
        }
        // 如果沒有值，直接添加
        if (!hasItem) {
          const obj = {
            name: this.currentSelectName,
            focus: true
          };
          data.push(obj);
        }
      }
      this.updateData(data);
    },
    // 更新 vuex 中的數據
    updateData(data) {
      this.get_mSelectList(data).then(() => {
        console.log(this.mSelectList);
      });
    }
  },
  computed: {
    ...mapState({
      mSelectList: state => state.mSelectList
    }),
    currentSelectName() {
      return this.routerPath + "-" + this.label;
    }
  },
  watch: {
    "$store.state.mSelectList": {
      handler(newVal) {
        let arr = JSON.parse(JSON.stringify(newVal));
        for (let item of arr) {
          if (item.name == this.currentSelectName) {
            this.focusFlag = item.focus;
          }
        }
      },
      deep: true
    }
  },
  created() {
    // const routerPath = this.$router.history.current.path;
    this.routerPath = this.$router.history.current.path;
  }
};
</script>

<style lang="less" scoped>
.m-select {
  position: relative;
  background-color: #d3dfe7;
  width: 160px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  font-size: 14px;
  user-select: none;
  cursor: pointer;
  &::after {
    content: "";
    position: absolute;
    top: 40%;
    right: 10%;
    border-width: 6px;
    border-style: solid;
    border-color: #2e6e9e transparent transparent transparent;
    transition-duration: 0.5s;
    transform-origin: center 3px;
  }
  &.focus {
    background-color: #91adc0;
    box-shadow: 0px 0px 8px 0px rgba(164, 175, 183, 0.75);
    color: #ffffff;
    .option-box {
      height: auto;
    }
    &::after {
      transform: rotate(180deg);
    }
  }
}
.option-box {
  position: absolute;
  z-index: 999;
  color: #12222e;
  top: 40px;
  width: 100%;
  background-color: #91adc0;
  overflow: hidden;
  box-shadow: 0px 0px 8px 0px rgba(164, 175, 183, 0.75);
  height: 0;
}
</style>
