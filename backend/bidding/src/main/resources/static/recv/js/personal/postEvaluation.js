new Vue({
  el: '#app',
  data: function() {
    return {
      rateData: {
        texts: ['(非常差)','(差)','(一般)','(好)','(非常好)'],
        color: ['#0096FF', '#0096FF', '#0096FF']
      },
      rate:{
        comprehensive: null,
        payment: null,
        attitude: null
      },
      anonymityFlag: false,
      comments: ''
    }
  },
  methods: {

  }
})