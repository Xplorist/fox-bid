new Vue({
  el: '#app',
  data: function() {
    return {
      category:{
        // 選中項
        checked: true, // recipients demanders
        // 圖片地址
        recipients: {
          backgroundImage: "url('../../imgs/merchant/recipients.png')",
        },
        recipientsActive: {
          backgroundImage: "url('../../imgs/merchant/recipientsActive.png')",
        },
        demanders: {
          backgroundImage: "url('../../imgs/merchant/demanders.png')",
        },
        demandersActive: {
          backgroundImage: "url('../../imgs/merchant/demandersActive.png')",
        }
      }
    }
  },
  methods: {
    changCategory(val){
      this.category.checked = val
      console.log(this.category.checked)
      return
    }
  }
})