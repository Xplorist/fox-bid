new Vue({
  el: '#app',
  data: function() {
    return {
      form: {
        // 賬號屬性
        properties: '集團內部賬號',
        // 頭像地址
        portraitUrl: '../../imgs/personal/portrait.png',
        // 賬號名稱
        name: 'SHZBG',
        // 所屬單位
        department: {
          partOne: '成都',
          partTwo: '次集團',
          partThree: '事業群',
          partFour: '處',
        },
        // 所屬單位可選項【不傳】
        departmentList: {
          partOne: ['成都', '深圳', '鄭州'],
          partTwo: ['A', 'B', 'C', 'D', 'D'],
          partThree: ['SHZBG', 'PCEBG', 'CCPBG', 'CNSBG', 'NWING', 'CMMSG'],
          partFour: ['處'],
        },
        // 單位名稱
        departName: '智網平台開發成都分部',
        // 交易法人
        corporate: '鴻富錦成都',
        // 費用代碼
        costCode: 'Q358DS5A5278A',
        // 加工範圍
        processRange: '模具、治具、檢具加工設計。',
        // 銀行賬號
        bankAccount: [
          '中国工商银行',
          '6222************6789',
        ],
        // 業務經理
        manager: '譚輝',
        // 聯繫電話
        tel: {
        	num: '5073-668123',
        	check: false
        },
        // Email
        email: 'hzcd-mis-sys1@mail.foxconn.com',
        // 簡介
        intro: '富士康科技集团是专业从事计算机、通讯、消费性电子等3c产品研发制造，广泛涉足数位内容、汽车零组件、通路、云运算服务及新能源、新材料开发应用的高新科技企业。凭借前瞻决策、扎根科技和专业制造，自1974年在台湾肇基，1988年投资中国大陆以来，富士康迅速发展壮大，拥有百余万员工及全球顶尖客户群，是全球最大的电子产业科技制造服务商。2015年进出口总额占中国大陆进出口总额的3.7%；2015年位居《财富》全球500强第31位。',
      }
    }
  },
  methods: {
    save() {

      console.log(this.form.intro);
      console.log('submit!');
    }
  }
})