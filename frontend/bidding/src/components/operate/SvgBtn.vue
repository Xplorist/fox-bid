<template>
  <div class="svg-btn">
    <svg
      width="265px"
      height="65px"
      version="1.1"
      xmlns="http://www.w3.org/2000/svg"
    >
      <!-- 外紋理 -->
      <polygon
        points="0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"
        style="fill:none;stroke:#0096FF;stroke-width:1"
        :style="{ stroke: outColor }"
      />
      <!-- 主體色 -->
      <rect x="2" y="2" width="261" height="61" :style="{ fill: fillColor }" />
      <!-- 內紋理 -->
      <polyline
        points="10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"
        style="fill:none;stroke:#3FB0FF;stroke-width:2;"
        :style="{ stroke: inColor }"
      />
    </svg>
  </div>
</template>

<script>
export default {
  props: ["inColor", "outColor", "fillColor"]
};
</script>

<style lang="less" scoped>
.svg-btn {
  cursor: pointer;
}
</style>
