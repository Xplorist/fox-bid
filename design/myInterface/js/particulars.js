new Vue({
  el: '#app',
  data: function() {
    return { 
    	visible: false,
    	alertBoxFlag: false,

    }
  },
  methods: {
  	alertBoxShow(){
  		var flag = this.alertBoxFlag ? false : true
  		console.log(flag)
  		return this.alertBoxFlag = flag
  	}
  }
})
