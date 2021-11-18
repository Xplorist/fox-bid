<template>
  <div class="post-message">
    <div class="post_title">消息與發佈</div>
    <div class="announceBox">
      <m-announce />
    </div>
  </div>
</template>

<script>
import MAnnounce from './Modules/Announce';

export default {
  data() {
    return {
    };
  },
  components:{
    MAnnounce
  }
};
</script>

<style lang="less" scoped>
.post-message {
  margin: 20px;
  background-color: #eff9ff;
  box-sizing: border-box;
  color: #12222e;
  padding: 20px;
  min-height: 100%;
  min-width: 925px;
}
.post_title {
  color: #12222e;
  font-size: 18px;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #afc2cf;
}

</style>
