(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-49880622"],{"07dd":function(t,s,a){},"39e7":function(t,s,a){"use strict";a.r(s);var e=function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{attrs:{id:"main"}},[t._m(0),e("div",{staticClass:"project"},[e("div",{staticClass:"title"},[t._v("我的項目")]),e("div",{staticClass:"details"},[e("el-radio-group",{model:{value:t.classify,callback:function(s){t.classify=s},expression:"classify"}},t._l(t.classifyList,(function(s){return e("el-radio",{key:s.id,attrs:{label:s.ename},on:{click:function(a){t.classify=s.ename}}},[t._v("\n          "+t._s(s.name)+"\n          "),e("span",{directives:[{name:"show",rawName:"v-show",value:s.num,expression:"item.num"}]},[t._v("("+t._s(s.num)+")")])])})),1),e("div",t._l(t.orderList,(function(s,i){return e("div",{key:s.pkid,staticClass:"orderList"},[e("div",{staticClass:"list_left"},[e("div",{staticClass:"left_info1"},[e("div",[e("span",{staticClass:"price"},[t._v(t._s(s.money_type)+t._s(s.total_price))]),e("router-link",{attrs:{to:"/particulars?pkid="+s.pkid}},[e("span",{staticClass:"orderNumber"},[t._v("【"+t._s(s.pd_type)+"】需求單號："+t._s(s.bill_no))])])],1),s.publish_date?e("div",{staticClass:"startTime"},[t._v("\n                發佈時間："+t._s(s.publish_date)+"\n              ")]):t._e()]),e("div",{staticClass:"left_info2"},[e("div",{staticClass:"unitNumber"},[t._v("\n                "+t._s(s.unitNumber||0)+"個單位已參與\n              ")])])]),e("div",{staticClass:"list_right"},[e("div",[s.winUnit?e("div",{staticClass:"winUnit"},[t._v("\n                中標單位："+t._s(s.winUnit||"")+"\n              ")]):e("div",{staticClass:"restTime"},[t._v("\n                竞标结束时间："+t._s(s.restTime)+"\n              ")])]),e("div",{staticClass:"right_info"},[e("div",{staticClass:"status",class:s.statusClass},[t._v("\n                "+t._s(s.status)+"\n              ")]),e("div",{staticClass:"operation",on:{click:function(a){s.dropDownFlag=!s.dropDownFlag,t.getDropItem(s.statusClass,i)}}},[t._v("\n                操作\n                "),e("img",{attrs:{src:a("c2b2"),alt:""}}),e("div",{directives:[{name:"show",rawName:"v-show",value:s.dropDownFlag&&s.operationList.length,expression:"item.dropDownFlag && item.operationList.length"}],staticClass:"dropDown"},t._l(s.operationList,(function(a){return e("span",{key:a.id,on:{click:function(e){return t.handleOperation(a.to,s.pkid)}}},[t._v(t._s(a.text))])})),0)])])])])})),0),e("div",{staticClass:"paging"},[e("Paging",{attrs:{total:t.total,"current-page":t.currentPage,"page-size":t.pageSize},on:{getCurrentPage:t.getListDate}})],1)],1)])])},i=[function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"assignment",staticStyle:{display:"none"}},[a("div",{staticClass:"title"},[t._v("我的任務")]),a("div",{staticClass:"details"},[a("div",{staticClass:"single"},[a("span",{staticClass:"singleTit"},[t._v("待签核")]),a("span",{staticClass:"singleCon"},[t._v("【0】")])]),a("div",{staticClass:"single"},[a("span",{staticClass:"singleTit"},[t._v("待选标")]),a("span",{staticClass:"singleCon",staticStyle:{color:"#E51313"}},[t._v("【1】")])]),a("div",{staticClass:"single"},[a("span",{staticClass:"singleTit"},[t._v("待收货")]),a("span",{staticClass:"singleCon",staticStyle:{color:"#E51313"}},[t._v("【2】")])]),a("div",{staticClass:"single"},[a("span",{staticClass:"singleTit"},[t._v("新动态")]),a("span",{staticClass:"singleCon",staticStyle:{color:"#E51313"}},[t._v("【17】")])])])])}],n=(a("28a5"),a("c5f6"),a("def5")),l=a("f8b7"),c={data:function(){return{classify:"all",classifyList:[{id:"0",ename:"all",name:"全部",num:""},{id:"1",ename:"0",name:"待發佈",num:""},{id:"2",ename:"1",name:"待報價",num:""},{id:"1_5",ename:"1.5",name:"待選標",num:""},{id:"3",ename:"2",name:"待發貨",num:""},{id:"4",ename:"3",name:"待收貨",num:""},{id:"5",ename:"4",name:"待付款",num:""},{id:"6",ename:"5",name:"待收款",num:""},{id:"7",ename:"6",name:"已完成",num:""}],dropDownFlag:!1,orderList:[],operationList:[{id:"-2",status:"abandon",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"-1",status:"drain",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"1",status:"publish",child:[{id:"1",text:"修改",to:"change"},{id:"2",text:"取消",to:"cancel"}]},{id:"2",status:"quote",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"3",status:"deliver",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"4",status:"takeDeliver",child:[{id:"1",text:"確認收貨",to:"take"},{id:"2",text:"投標明細",to:"show"},{id:"3",text:"取消",to:"cancel"}]},{id:"5",status:"payment",child:[{id:"1",text:"付款",to:"pay"},{id:"2",text:"投標明細",to:"show"},{id:"3",text:"取消",to:"cancel"}]},{id:"6",status:"gather",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"7",status:"finished",child:[{id:"1",text:"投標明細",to:"show"},{id:"2",text:"取消",to:"cancel"}]},{id:"8",status:"remainEvaluate",child:[{id:"1",text:"評價",to:"postEva"},{id:"2",text:"查看",to:"show"},{id:"3",text:"取消",to:"cancel"}]}],currentPage:1,pageSize:10,total:1,initPage:1}},methods:{handleOperation:function(t,s){var a={pkid:s,bill_status:""};switch(t){case"change":this.$router.push("/demand/publish?pkid="+s);break;case"postEva":this.$router.push("/demand/postEva?pkid="+s);break;case"take":a.bill_status=4,this.updataStatus(a);break;case"pay":a.bill_status=5,this.updataStatus(a);break;case"show":this.$router.push("/demand/chooseDetails?pkid="+s);break;case"cancel":break}},updataStatus:function(t){var s=this;Object(l["w"])(t).then((function(t){"1"===t.code?s.getListDate(s.currentPage):s.$message.error(t.msg)}))},getDropItem:function(t,s){for(var a in console.log(t),console.log(s),this.operationList)this.operationList[a].status==t&&(this.orderList[s].operationList=this.operationList[a].child)},getListDate:function(){var t=this,s=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.currentPage=Number(s),sessionStorage.setItem("demandMainCurrentPage",this.currentPage),Object(l["f"])(this.classify,this.currentPage,this.pageSize).then((function(s){if("1"===s.code){t.orderList=[],t.total=s.t.row_total;var a=s.t.bill_list;t.changeListData(a)}else t.$message.error(s.msg)}))},changeListData:function(t){for(var s in t){var a="",e="";switch(t[s].bill_status){case"-2":a="棄標",e="abandon";break;case"-1":a="流標",e="drain";break;case"0":a="待發佈",e="publish";break;case"1":a="待報價",e="quote";break;case"2":a="待發貨",e="deliver";break;case"3":a="待收貨",e="takeDeliver";break;case"4":a="待付款",e="payment";break;case"5":a="待收款",e="gather";break;case"6":if("N"===t[s].f_send_eval){a="待評價",e="remainEvaluate";break}a="已完成",e="finished";break;default:break}var i={pkid:t[s].pkid,total_price:t[s].total_price,pd_type:t[s].pd_type,bill_no:t[s].bill_no,publish_date:t[s].publish_date?t[s].publish_date.split("T")[0]:"",unitNumber:t[s].recv_user_num,browseNumber:"295",collectNumber:"67",winUnit:t[s].recv_user?t[s].recv_user.dept_name:"",restTime:t[s].bid_end_date?t[s].bid_end_date.split("T")[0]:"",status:a,statusClass:e,money_type:"RMB"==t[s].money_type?"￥":"$",dropDownFlag:!1};this.orderList.push(i)}},getListClassifyNum:function(){var t=this;Object(l["o"])().then((function(s){if("1"===s.code){var a=s.t;for(var e in t.classifyList)switch(t.classifyList[e].ename){case"all":t.classifyList[e].num=a.num_status_all;break;case"0":t.classifyList[e].num=a.num_status_0;break;case"1":t.classifyList[e].num=a.num_status_1;break;case"1.5":t.classifyList[e].num=a.num_status_1_5;break;case"2":t.classifyList[e].num=a.num_status_2;break;case"3":t.classifyList[e].num=a.num_status_3;break;case"4":t.classifyList[e].num=a.num_status_4;break;case"5":t.classifyList[e].num=a.num_status_5;break;case"6":t.classifyList[e].num=a.num_status_6;break;case"no_send_eval":t.classifyList[e].num=a.num_no_send_eval;break;default:break}}else t.$message.error(s.msg)}))}},created:function(){this.getListClassifyNum();var t=Number(sessionStorage.getItem("demandMainCurrentPage")),s=sessionStorage.getItem("demandMainCurrentClassify");this.currentPage=t||1,s&&"all"!==s?(this.initPage=this.currentPage,this.classify=s):this.getListDate(this.currentPage)},components:{Paging:n["a"]},computed:{},watch:{classify:function(){sessionStorage.setItem("demandMainCurrentClassify",this.classify),1==this.initPage?this.getListDate():(this.getListDate(this.initPage),this.initPage=1)}}},r=c,o=(a("4987"),a("2877")),d=Object(o["a"])(r,e,i,!1,null,"6295ea07",null);s["default"]=d.exports},4987:function(t,s,a){"use strict";var e=a("07dd"),i=a.n(e);i.a},c2b2:function(t,s){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAGCAYAAAD68A/GAAAAlElEQVQYV2O8fO/5VAZGhiwGfOA/wzTG////M195+HwHw39GF6xqGRn3vLl/3ZMRJHn+/nsBFoYfJxgYGNSRFf/////WX0ZOc0NFwQ9ghSBw4e4TNWYmZpBiQajQ+7//WCwMlEVvgfhwhSDOxYcvnJn+/t8BYv///89TT1l6D8wgFIUgwcv3n2cyMjIw6ihITkN2BgDvxDF1vcI+PgAAAABJRU5ErkJggg=="}}]);
//# sourceMappingURL=chunk-49880622.8dc1250c.js.map