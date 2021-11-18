new Vue({
  el: '#app',
  data: function() {
    return {
    	// 環形進度條
    	progress: {
	    	strokeWidth: 12,
	    	width: 100,
	    	color: '#0096FF'
    	},
      // 選中的訂單狀態
      orderStatus: 'all', // all progress production win payment evaluation finished
      // 狀態值
      statusNum: {
        all : '59',
        progress : '2',
        production : '0',
        win : '5',
        payment : '1',
        evaluation : '0',
        finished : '53',
      },
      // 數據
      orderList: [
        {
          scope: '檢',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['￥', '50000'],
          unitNum: '16',
          current: {
            status: true,
            // time:'投标剩余15天17小时5分39秒'
            startDate: '1568023104000',
            endDate: '1569044741000',
            remainDate: '',
            text: '',
          },
          status: {
            en: 'progress',
            ch: '進行中',
            btnText: '詳情'
          },
          winBidding: false,
          href: '',
        },
        {
          scope: '治',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['$', '50000'],
          unitNum: '26',
          current: {
            status: true,
            startDate: '1558023104000',
            endDate: '1578023104000',
            remainDate: '',
            text: ''
          },
          status: {
            en: 'win',
            ch: '中標',
            btnText: '簽核協議'
          },
          winBidding: true,
          href: '',
        },
        {
          scope: '檢',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['￥', '50000'],
          unitNum: '31',
          current: {
            status: true,
            startDate: '1508823104000',
            endDate: '1570023104000',
            remainDate: '',
            text: ''
          },
          status: {
            en: 'production',
            ch: '製作中',
            btnText: '交貨'
          },
          winBidding: true,
          href: '',
        },
        {
          scope: '檢',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['￥', '50000'],
          unitNum: '31',
          current: {
            status: true,
            startDate: '1408823104000',
            endDate: '1508023104000',
            remainDate: '',
            text: ''
          },
          status: {
            en: 'payment',
            ch: '待收款',
            btnText: '確認收款',
          },
          winBidding: true,
          href: '',
        },
        {
          scope: '檢',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['￥', '50000'],
          unitNum: '25',
          current: {
            status: true,
            startDate: '1408823104000',
            endDate: '1508023104000',
            remainDate: '',
            text: ''
          },
          status: {
            en: 'evaluation',
            ch: '待評價',
            btnText: '評價',
          },
          winBidding: true,
          href: '',
        },
        {
          scope: '檢',
          formNum: '需求單號A30000028',
          state: ['成都D23', '產品處'],
          price: ['￥', '50000'],
          unitNum: '31',
          current: {
            status: false,
            startDate: '1408823104000',
            endDate: '1508023104000',
            remainDate: '',
            text: '競標失敗',
          },
          status: {
            en: 'finished',
            ch: '已結束',
            btnText: '詳情',
          },
          winBidding: false,
          href: '',
        },
      ]
    }
  },
  created(){
    // 更改href
    for(let i in this.orderList){
      switch(this.orderList[i].status.en){
        case 'progress' :
            this.orderList[i].href = './details.html'
            break 
        case 'win' :
            this.orderList[i].href = '../contract/index.html'
            break 
        case 'production' :
            this.orderList[i].href = '#'
            break
        case 'payment' :
            this.orderList[i].href = '#'
            break
        case 'evaluation' :
            this.orderList[i].href = './postEvaluation.html'
            break
        case 'finished' :
            this.orderList[i].href = './details.html'
            break
      }
    }
    
    // 修改時間
    for(let i in this.orderList){
      var startDate = this.orderList[i].current.startDate
      var endDate = this.orderList[i].current.endDate
      // var nowDate = Date.parse(new Date())
      var nowDate = 1569044721000
      var remainDate = (endDate - nowDate) > 0 ? endDate - nowDate : ''
      if (remainDate == '') return
      remainDate = remainDate / 1000
      this.orderList[i].current.remainDate = remainDate
      var _this = this
      setInterval(function(){
        _this.orderList[i].current.remainDate > 0 ? _this.orderList[i].current.remainDate-- : _this.orderList[i].current.remainDate = 0
      },1000)
    }
  },
  methods: {

  },
  filters: {
    formatDate(val){
      // console.log(val)
      if (val === 0){
        return '時間已到'
      }else if(val == ''){
        return
      }else{
        var dd,hh,mm,ss = null
        dd = Math.floor(val / 60 / 60 / 24) 
        hh = Math.floor((val / 60 / 60) % 24) 
        mm = Math.floor((val / 60) % 60) 
        ss = Math.floor(val % 60 )
        dd = dd < 10 ? '0'+ dd : dd
        hh = hh < 10 ? '0'+ hh : hh
        mm = mm < 10 ? '0'+ mm : mm
        ss = ss < 10 ? '0'+ ss : ss
        return '時間剩餘' + dd + '天' + hh + '小時' + mm + '分鐘' + ss + '秒'
      }
    }
  }
})
