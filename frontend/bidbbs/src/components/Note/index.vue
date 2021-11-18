<template>
  <div class="note" v-if="data">
    <router-link class="title" :to="`/p/${data.id}`">
      {{ data.title }}
    </router-link>
    <p class="abstract">
      {{ briefData }}
    </p>
    <div class="meta">
      <span>
        {{ data.creatorName }}
      </span>
      <span>
        {{ data.createTime }}
      </span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Object
    }
  },
  computed: {
    briefData: function() {
      let data = this.data.content;
      let returnData;
      if (data) {
        let reg = /<[^<>]+>/g;
        returnData = this.data.content.replace(reg, "").slice(0, 74) + "...";
      } else {
        returnData = "...";
      }

      return returnData;
    }
  },
  created() {}
};
</script>

<style lang="less" scoped>
@import "../../style/variable";
.note {
  padding: 15px 2px 20px 0;
  text-align: left;
  border-bottom: 1px solid #f0f0f0;
}
.title {
  margin: 0 0 4px 0;
  font-weight: 700;
  line-height: 1.5;
  font-size: @articleTitleSize;
  color: @articleTitleColor;
  transition-duration: 0.2s;
}
.abstract {
  margin: 0 0 8px 0;
  font-size: @articleContentSize;
  line-height: 24px;
  color: @articleContentColor;
}
.meta {
  font-size: @articleContentSize;
  color: @articleContentColor;
  > span {
    margin: 0 8px 0 0;
  }
}
</style>
