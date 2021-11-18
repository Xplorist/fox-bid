// 导出页面为PDF格式
import html2Canvas from 'html2canvas'
import JsPDF from 'jspdf'

export default {
  install: function (Vue) {
    Vue.prototype.$getPdf = function (id, title) {
      window.pageYOffset = 0;
      document.documentElement.scrollTop = 0
      document.body.scrollTop = 0

      const SIZE = [595.28, 841.89] // a4宽高
      let node = document.querySelector(`#${id}`)
      let tab = document.querySelector('#tbSec')
      let sign = document.querySelector('#sign')
      let anno = document.querySelector('#annotation')

      // ========== 避免表格內容分割，分割位置添加新元素 ============
      let nodeW = node.clientWidth;
      let pageH = nodeW / SIZE[0] * SIZE[1];
      let modules = tab.childNodes;
      let remainH = tab.offsetTop - node.offsetTop
      let currentPage =  1;
      for (let i = 0, len = modules.length; i < len; i++) {
        let item = modules[i];
        let itemH = item.clientHeight
        if ( (item.offsetTop + remainH + itemH) > pageH * currentPage ){
          currentPage ++
          this.addElement(item, itemH, 'tbSec')
        }
      }
      // ========== 避免簽字位置被分割 ============
      let remainHofSign = sign.offsetTop - node.offsetTop
      let itemHofSign = sign.clientHeight
      if( (remainHofSign + itemHofSign) > pageH * currentPage ){
        currentPage ++
        this.addElement(sign, itemHofSign, 'sign')
      }
      // ========== 避免注釋內容分割 ============
      let remainHofAnno = anno.offsetTop - node.offsetTop
      let itemHofAnno = anno.clientHeight
      if( (remainHofAnno + itemHofAnno) > pageH * currentPage ){
        currentPage ++
        this.addElement(anno, itemHofAnno, 'anno')
      }

      let _this =this
      html2Canvas(node, {
        // allowTaint: true,
        useCORS: true, // allowTaint与useCORS看情况二选一,设置 useCORS 为 true，即可开启图片跨域
        scale: 2 // 设置 scale 为 2 及以上，即可支持高分屏
      }).then(function (canvas) {
        let contentWidth = canvas.width
        let contentHeight = canvas.height
        // 一页pdf显示html页面生成的canvas高度
        let pageHeight = contentWidth / SIZE[0] * SIZE[1]
        // 未生成pdf的html页面高度
        let leftHeight = contentHeight
        // pdf页面竖向偏移
        let position = 0
        // 横向页边距
        let sidesway = 0
        // html页面生成的canvas在pdf中图片的宽高
        let imgWidth = SIZE[0] - sidesway * 2
        let imgHeight = imgWidth / contentWidth * contentHeight
        let pageData = canvas.toDataURL('image/jpeg', 1.0)
        let PDF = new JsPDF('', 'pt', 'a4')
        if (leftHeight < pageHeight) {
          PDF.addImage(pageData, 'JPEG', sidesway, position, imgWidth, imgHeight)
        } else {
          while (leftHeight > 0) {
            PDF.addImage(pageData, 'JPEG', sidesway, position, imgWidth, imgHeight)
            leftHeight -= pageHeight
            position -= SIZE[1]
            if (leftHeight > 0) {
              PDF.addPage()
            }
          }
        }
        PDF.save(title + '.pdf')
        _this.delElement()
      })
    }
  },
};