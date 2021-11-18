<template>
  <div class="sideBar">
    <div class="itemWrapper">
      <ul>
        <li v-for="item in sideItem" :key="item.id">{{item.name}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
data(){
  return{
    sideItem: [
      {name: '關於我們', val: 'about', id: 1},
      // {name: '協議', val: 'about', id: 2}
    ]
  }
}
}
</script>

<style lang="less" scoped>
.sideBar{
  width: 200px;
  background-color: #D3DFE7;
}
.itemWrapper{
  margin-top: 80px;
  ul{
    padding: 0;
    li{
      padding-left: 2rem;
      line-height: 40px;
      height: 40px;
      background-color: #C2D3E0;
      color: #333333;
      font-size: 14px;
    }
  }
}
</style>