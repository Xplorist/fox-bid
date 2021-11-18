<template>
  <section id="footer">
        <div class="foo-warpper type-area">
          <div class="technical">
            <div class="m-logo"></div>
            <div class="text">技術服務: 智能雲網絡開發成都分部</div>
          </div>
          <div class="customer">
            客戶服務: 5073-66645
          </div>
          <div class="blogroll">
            <div>友情鏈接: </div>
            <div>
              <router-link to="">
                MAC(I)智能雲網平台
              </router-link>
            </div>
            <div class="line"></div>
            <div>
              <router-link to="">
                MAC(I)人事點名系統
              </router-link>
            </div>
            <div class="line"></div>
            <div>
              <router-link to="">
                MAC(I)金流系統                
              </router-link>
            </div>
            <div class="line"></div>
            <div>
              <router-link to="">
                智能刀具管理               
              </router-link>
            </div>
        </div>
      </div>
    </section>
</template>

<script>
export default {

}
</script>

<style lang="less" scoped>
// 底部
#footer {
  height: 160px;
  background-color: #12222E;
  color: #2D3B49;
  font-size: 14px;
  .foo-warpper {
    height: 100%;
    padding: 0 20px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .technical {
    display: flex;
    align-items: center;
    .m-logo {
      width: 35px;
      height: 21px;
      background-image: url(../assets/imgs/index/m-logo.png);
      margin-right: 10px;
    }
  }
  .customer {}
  .blogroll {
    display: flex;
    align-items: center;
    div:nth-child(1) {
      margin-right: 8px;
    }
    a {
      text-decoration: none;
      out-line: none;
      color: #2D3B49;
      &:hover {
        color: #D3DFE7;
      }
    }
    .line {
      width: 1px;
      height: 15px;
      margin: 0 8px;
      background-color: #2D3B49;
    }
  }
}
</style>