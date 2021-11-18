<template>
  <div class="sideContent z-border-box">
    <div class="z-text-center">關於我們</div>
    <pre>
      我们致力于发展成为一个专业的产品开发技术信息服务平台，为各单位提供更公平、快捷、开放的加工资源交易方案。通过平台订单信息公开，产品生产单位可以快速地找到优势加工资源降低产品开发门槛，短缩量产准备时间；加工服务单位可结合自身当前的经营状况，积极承接或转发订单，从而实现营收平衡，发展持续！
      希望各单位通过平台使用，生产力和竞争力可以得到提升，进而为公司永续经营提供更多活力！                            
    </pre>
    <hr />
    <table>
      <tr>
        <td>地址</td>
        <td>：</td>
        <td>富士康科技集团（成都）D23三楼西侧</td>
      </tr>
      <tr>
        <td>電話</td>
        <td>：</td>
        <td>5073-66645</td>
      </tr>
      <tr>
        <td>E-mall</td>
        <td>：</td>
        <td>hzlh-mac-gongweilong@mail.foxconn.com</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.sideContent {
  flex: 1;
  background-color: #e6ecf1;
  padding: 45px;
  color: #333333;
  > div {
    font-size: 24px;
  }
  > pre {
    white-space: pre-wrap;
    word-wrap: break-word;
    margin-top: 45px;
    font-size: 14px;
    color: #333333;
    line-height: 30px;
  }
  > hr{
    border-color: #D3DFE7;
  }
  > table{
    font-size: 14px;
    margin-top: 30px;
    tr{
      height: 30px;
      td{
        &:nth-child(1){
          text-align-last: justify;
        }
      }
    }
  }
}
</style>
