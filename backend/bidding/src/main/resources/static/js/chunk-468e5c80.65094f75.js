(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-468e5c80"],{"48fb":function(t,e,n){"use strict";n.d(e,"e",(function(){return s})),n.d(e,"d",(function(){return a})),n.d(e,"b",(function(){return r})),n.d(e,"a",(function(){return o})),n.d(e,"c",(function(){return d})),n.d(e,"g",(function(){return c})),n.d(e,"f",(function(){return l}));var i=n("365c");function s(t){var e="/bid-statistics/realtimeDataBoard";return i["a"].get(e,t)}function a(){var t="/bid-statistics/publishType";return i["a"].get(t)}function r(){var t="/bid-statistics/customerMarketing";return i["a"].get(t)}function o(){var t="/bid-statistics/billAnalysis";return i["a"].get(t)}function d(){var t="/bid-statistics/factoryAnalysis";return i["a"].get(t)}function c(t){var e="/bid-statistics/sendUserRank";return i["a"].get(e,t)}function l(t){var e="/bid-statistics/receiveUserRank";return i["a"].get(e,t)}},5383:function(t,e,n){"use strict";var i=n("7aa1"),s=n.n(i);s.a},"5a35":function(t,e,n){"use strict";var i=n("d372"),s=n.n(i);s.a},"5f08":function(t,e,n){},"5f08b":function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"ranking-list"},[n("div",{staticClass:"content-box"},[n("div",[n("h3",{staticClass:"module-title"},[t._v("\n        "+t._s("send"===t.type?"發單方":"接單方")+"排行榜\n      ")]),n("div",{staticClass:"radio-box"},t._l(t.radioTimeData,(function(e,i){return n("m-radio",{key:i+e,attrs:{rId:e.id,rName:e.name,rValue:e.value,rChecked:e.checked,rLabel:e.label,rColor:"#96A8B6"},on:{radioValChange:t.radioValChange}})})),1)]),n("div",{staticClass:"table-box"},[n("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[n("el-table-column",{attrs:{type:"index",width:"60"}}),t._l("send"===t.type?t.sendTableTitle:t.recvTableTitle,(function(t,e){return n("el-table-column",{key:e+t.prop,attrs:{prop:t.prop,label:t.label,"min-width":t.minWidth,"show-overflow-tooltip":t.showOverflowTooltip,sortable:t.sortable}})})),n("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("button",{on:{click:function(n){return t.handleDetail(e.$index,e.row)}}},[t._v("\n              詳情\n            ")])]}}])})],2)],1)]),n("div",[n("details-alert",{attrs:{choosedData:t.choosedData},on:{choosedDataEmpty:t.setChoosedData}})],1)])},s=[],a=n("48fb"),r=n("85e6"),o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return t.info?n("div",{staticClass:"details-alert"},[n("el-dialog",{attrs:{visible:t.dialogFormVisible,"before-close":t.handleClose},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[n("div",{staticClass:"title"},[t._v("\n      新用戶 "),n("span",[t._v(" "+t._s(t.info.dept_name)+" ")]),t._v(" 詳細信息\n      "),n("p",[t._v("注册时间："+t._s(t.info.create_date))])]),"send"==t.info.send_recv_type&&Boolean(t.info.send_user_statistics)?n("div",{staticClass:"z-flex z-justify-content-around account-data"},[n("div",[n("span",[t._v("發單量")]),n("div",[t._v(t._s(t.info.send_user_statistics.send_amount||0))])]),n("div",[n("span",[t._v("發單金額")]),n("div",[t._v(t._s(t.info.send_user_statistics.send_money||0))])]),n("div",[n("span",[t._v("選標金額")]),n("div",[t._v(t._s(t.info.send_user_statistics.pick_money||0))])]),n("div",[n("span",[t._v("棄標率")]),n("div",[t._v("\n          "+t._s(100*t.info.send_user_statistics.abandon_rate+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("選標率")]),n("div",[t._v(t._s(100*t.info.send_user_statistics.pick_rate+"%"||!1))])]),n("div",[n("span",[t._v("流標率")]),n("div",[t._v(t._s(100*t.info.send_user_statistics.flow_rate+"%"||!1))])]),n("div",[n("span",[t._v("客戶評分")]),n("div",[t._v(t._s(t.info.send_user_statistics.user_score||0))])]),n("div",[n("span",[t._v("信譽分")]),n("div",[t._v(t._s(t.info.send_user_statistics.reputation||0))])])]):"recv"==t.info.send_recv_type&&Boolean(t.info.recv_user_statistics)?n("div",{staticClass:"z-flex z-justify-content-around account-data"},[n("div",[n("span",[t._v("接單量")]),n("div",[t._v(t._s(t.info.recv_user_statistics.recv_amount||0))])]),n("div",[n("span",[t._v("報價金額")]),n("div",[t._v(t._s(t.info.recv_user_statistics.offer_money||0))])]),n("div",[n("span",[t._v("中標金額")]),n("div",[t._v(t._s(t.info.recv_user_statistics.win_bid_money||0))])]),n("div",[n("span",[t._v("競標成功率")]),n("div",[t._v("\n          "+t._s(100*t.info.recv_user_statistics.win_bid_rate+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("客戶評分")]),n("div",[t._v(t._s(t.info.recv_user_statistics.user_score||0))])])]):t._e(),n("div",{staticClass:"details z-flex z-justify-content-between"},[n("table",[n("tr",[n("th",{attrs:{colspan:"3"}},[t._v("\n            公司信息\n          ")])]),n("tr",[n("td",[t._v("賬號屬性")]),n("td",[t._v(":")]),n("td",[t._v("\n            集團內部賬號"),n("span",{staticClass:"z-cBlue"},[t._v("【"+t._s("send"==t.info.send_recv_type?"發佈方":"接單方")+"】\n            ")])])]),n("tr",[n("td",[t._v("賬號名稱")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.username))])]),n("tr",[n("td",[t._v("所屬廠區")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.fctry_zone))])]),n("tr",[n("td",[t._v("次集團")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.secn_cmpy))])]),n("tr",[n("td",[t._v("事業群")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.entrps_group))])]),n("tr",[n("td",[t._v("產品處")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.pd_office))])]),n("tr",[n("td",[t._v("交易法人")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.legal_person))])]),n("tr",[n("td",[t._v("費用代碼")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.cost_code))])])]),n("table",[n("tr",[n("th",{attrs:{colspan:"3"}},[t._v("\n            業務信息\n          ")])]),n("tr",[n("td",[t._v("業務窗口")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.busis_mngr))])]),n("tr",[n("td",[t._v("固定電話")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.tel))])]),n("tr",[n("td",[t._v("移動電話")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.phone))])]),n("tr",[n("td",[t._v("Email")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.email))])]),n("tr",{directives:[{name:"show",rawName:"v-show",value:"recv"==t.info.send_recv_type,expression:"info.send_recv_type == 'recv'"}]},[n("td",[t._v("加工範圍")]),n("td",[t._v(":")]),n("td",t._l(t.info.recv_range_list,(function(e){return n("span",{key:e.pkid},[t._v(t._s(e.name)+"、")])})),0)]),n("tr",[n("td",[t._v("簡介")]),n("td",[t._v(":")]),n("td",[n("p",[t._v("\n              "+t._s(t.info.summary)+"\n            ")])])])])])])],1):t._e()},d=[],c=n("c24f"),l={data:function(){return{dialogFormVisible:!1,info:{}}},props:{choosedData:{type:Object,required:!0}},watch:{choosedData:{deep:!0,handler:function(t){var e=this;console.log(t),"{}"!=JSON.stringify(t)&&Object(c["h"])(t.pkid).then((function(t){console.log(t),"1"===t.code?(e.info=t.t,e.dialogFormVisible=!0):e.$message.error(t.msg)}))}}},methods:{handleClose:function(t){this.$emit("choosedDataEmpty"),t()}}},u=l,_=(n("5a35"),n("2877")),v=Object(_["a"])(u,o,d,!1,null,"525c4fdd",null),f=v.exports,p={data:function(){return{radioTimeData:[{id:"this-month",name:"timeRadio",value:"thisMonth",checked:!0,label:"本月"},{id:"this-year",name:"timeRadio",value:"thisYear",checked:!1,label:"本年"}],tableData:[{userId:"9837A594291FF14EE050F40A5BE9FEB3",username:"F3236205",deptName:"富泰捷",receiveNum:3,winNum:0,winPrice:0,winRate:0,score:0,outRate:0,outQuality:0,service:0}],type:"發單方",sendTableTitle:[{prop:"deptName",label:"單位",minWidth:"160",showOverflowTooltip:!0},{prop:"sendNum",label:"發佈數量",sortable:!0},{prop:"sendPrice",label:"發佈金額",sortable:!0},{prop:"receiveRate",label:"(被)接標率",sortable:!0},{prop:"score",label:"客戶評分",sortable:!0},{prop:"payment",label:"付款時效",sortable:!0},{prop:"service",label:"服務態度",sortable:!0}],recvTableTitle:[{prop:"deptName",label:"單位",minWidth:"160",showOverflowTooltip:!0},{prop:"receiveNum",label:"接單數量",sortable:!0},{prop:"winNum",label:"中標數",sortable:!0},{prop:"winPrice",label:"中標金額",sortable:!0},{prop:"winRate",label:"中標率",sortable:!0},{prop:"score",label:"綜合評分",sortable:!0},{prop:"outRate",label:"出貨時效",sortable:!0},{prop:"outQuality",label:"出貨質量",sortable:!0},{prop:"service",label:"服務態度",sortable:!0}],choosedData:{}}},created:function(){this.type=this.$router.history.current.query.type,this.getRecvUserRankData()},methods:{setChoosedData:function(){this.choosedData={}},handleDetail:function(t,e){this.choosedData={pkid:e.userId}},radioValChange:function(t,e){this.getRecvUserRankData(e)},getRecvUserRankData:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"thisMonth",n={timeLimit:e};"send"===this.type?Object(a["g"])(n).then((function(e){t.handleRes(e)})):Object(a["f"])(n).then((function(e){t.handleRes(e)}))},handleRes:function(t){"1"===t.code?this.tableData=t.t:this.$message.error(t.msg)}},components:{MRadio:r["a"],DetailsAlert:f}},b=p,h=(n("b8f4"),Object(_["a"])(b,i,s,!1,null,"66790cdb",null));e["default"]=h.exports},"7aa1":function(t,e,n){},"85e6":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"radio-box"},[n("input",{attrs:{type:"radio",id:t.rId,name:t.rName},domProps:{value:t.rValue,checked:t.rChecked},on:{click:function(e){return t.handleRadioChange(t.rName,t.rValue)}}}),n("label",{style:{color:t.rColor},attrs:{for:t.rId}},[t._v(t._s(t.rLabel))])])},s=[],a={data:function(){return{}},watch:{},methods:{handleRadioChange:function(t,e){this.$emit("radioValChange",t,e)}},props:{rId:{type:String},rName:{type:String,required:!0},rValue:{type:String,required:!0},rChecked:{type:Boolean,default:!1},rLabel:{type:String},rColor:{type:String,default:"#5b6974"}}},r=a,o=(n("5383"),n("2877")),d=Object(o["a"])(r,i,s,!1,null,"4db1733e",null);e["a"]=d.exports},b8f4:function(t,e,n){"use strict";var i=n("5f08"),s=n.n(i);s.a},c24f:function(t,e,n){"use strict";n.d(e,"d",(function(){return s})),n.d(e,"e",(function(){return a})),n.d(e,"h",(function(){return r})),n.d(e,"j",(function(){return o})),n.d(e,"c",(function(){return d})),n.d(e,"a",(function(){return c})),n.d(e,"i",(function(){return l})),n.d(e,"g",(function(){return u})),n.d(e,"b",(function(){return _})),n.d(e,"f",(function(){return v}));var i=n("365c");function s(t){var e="/bidding/user/login";return i["a"].post(e,t)}function a(t){t={username:t};var e="/bidding/user/login_user_info";return i["a"].postWithToken(e,t)}function r(t){t={pkid:t};var e="/bidding/user/query_user_info_by_pkid";return i["a"].post(e,t)}function o(t){var e="/bidding/user/update_user_info";return i["a"].postWithToken(e,t)}function d(t){var e="/bidding/user/generate_code";return i["a"].post(e,t)}function c(t){var e="/bidding/user/check_code";return i["a"].post(e,t)}function l(t){var e="/bidding/user/update_password";return i["a"].post(e,t)}function u(t){var e="/bidding/user/queryUserList";return i["a"].postWithToken(e,t)}function _(t){var e="/bidding/user/deleteUser";return i["a"].postWithToken(e,t)}function v(t){var e="/bidding/user/queryAllUsersStatistics";return i["a"].postWithToken(e,t)}},d372:function(t,e,n){}}]);
//# sourceMappingURL=chunk-468e5c80.65094f75.js.map