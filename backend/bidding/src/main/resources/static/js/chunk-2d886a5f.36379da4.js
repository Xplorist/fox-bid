(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d886a5f"],{"0039":function(t,e,s){},2261:function(t,e,s){},"252c":function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"main"},[s("Top"),s("Logo"),s("section",{attrs:{id:"content"}},[s("div",{staticClass:"main type-area"},[s("SilderBar",{attrs:{"choose-item":t.silderBarItem}}),s("div",{staticClass:"mainPart"},[s("div",{staticClass:"manage"},[t._m(0),s("el-radio-group",{model:{value:t.classify,callback:function(e){t.classify=e},expression:"classify"}},t._l(t.classifyList,(function(e){return s("el-radio",{key:e.id,attrs:{label:e.ename},on:{click:function(s){t.classify=e.ename}}},[t._v("\n              "+t._s(e.name)+"\n              "),s("span",{directives:[{name:"show",rawName:"v-show",value:e.num,expression:"item.num"}]},[t._v("("+t._s(e.num)+")")])])})),1),s("div",{staticClass:"list"},t._l(t.orderList,(function(e,a){return s("div",{key:a,staticClass:"list_single",class:{winBidding:"s"===e.winBidding}},[s("div",{staticClass:"list_sin_left"},[s("div",{staticClass:"sin_left_scope"},[t._v(t._s(e.scope))]),s("div",{staticClass:"sin_left_info"},[s("div",{staticClass:"left_info_item"},[s("div",{staticClass:"formNum",on:{click:function(s){return t.$router.push("/particulars?pkid="+e.pkid)}}},[t._v(t._s(e.formNum))]),s("div",{staticClass:"state"},t._l(e.state,(function(e,a){return s("div",{key:a},[t._v(t._s(e))])})),0)]),s("div",{staticClass:"left_info_item"},[s("div",{staticClass:"price"},[s("div",{staticClass:"kind"},[t._v(t._s(e.price[0]))]),s("div",{staticClass:"num"},[t._v(t._s(e.price[1]))])]),s("div",{staticClass:"unitNum"},[t._v(t._s(e.unitNum)+"個單位已參與")])])])]),s("div",{staticClass:"list_sin_right"},[s("div",{staticClass:"sin_right_left"},[s("div",{staticClass:"current"},["f"===e.winBidding?s("div",{staticClass:"failed"},[t._v("競標失敗")]):s("div",{staticClass:"remainTime"},[t._v(t._s("finished"==e.status.class?"":"交貨日期："+e.deliver_date.split(" ")[0]))])])]),s("div",{staticClass:"sin_right_right",class:e.status.class},[s("div",{staticClass:"status"},[t._v(t._s(e.status.text))]),s("div",{staticClass:"btn"},[s("router-link",{attrs:{to:e.href+"?pkid="+e.pkid}},[t._v(t._s(e.status.btnText))])],1)])])])})),0),s("div",{staticClass:"paging"},[s("Paging",{attrs:{total:t.total,"current-page":t.currentPage,"page-size":t.pageSize},on:{getCurrentPage:t.getListDate}})],1)],1)])],1)]),s("Footer")],1)},i=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"title"},[s("div",{staticClass:"tit_icon"}),s("div",[t._v("接單管理")])])}],n=(s("8e6e"),s("ac6a"),s("456d"),s("bd86")),r=(s("c5f6"),s("e9c9")),c=s("2241"),o=s("fd2d"),u=s("def5"),l=s("f9bd"),m=s("f8b7"),f=s("2f62");function d(t,e){var s=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),s.push.apply(s,a)}return s}function _(t){for(var e=1;e<arguments.length;e++){var s=null!=arguments[e]?arguments[e]:{};e%2?d(s,!0).forEach((function(e){Object(n["a"])(t,e,s[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(s)):d(s).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(s,e))}))}return t}var p={data:function(){return{progress:{strokeWidth:12,width:100,color:"#0096FF"},classify:"all",classifyList:[{interfaceName:"num_status_all",id:"0",ename:"all",name:"全部",num:"0"},{interfaceName:"num_status_1",id:"1",ename:"1",name:"競價中",num:"0"},{interfaceName:"num_status_2",id:"2",ename:"2",name:"待發貨",num:"0"},{interfaceName:"num_status_3",id:"3",ename:"3",name:"待收貨",num:"0"},{interfaceName:"num_status_4",id:"4",ename:"4",name:"待付款",num:"0"},{interfaceName:"num_status_5",id:"5",ename:"5",name:"待收款",num:"0"},{interfaceName:"num_status_6",id:"6",ename:"6",name:"已完成",num:"0"},{interfaceName:"num_status_n2",id:"-2",ename:"-2",name:"已棄標",num:"0"}],orderList:[],total:null,pageSize:6,currentPage:null,silderBarItem:"order",initPage:1}},methods:{setHref:function(){for(var t in this.orderList)switch(this.orderList[t].status.class){case"abandon":case"quote":case"payment":case"takeDel":case"finished":case"failed":this.orderList[t].href="particulars";break;case"deliver":this.orderList[t].href="personal/delivery";break;case"gather":this.orderList[t].href="personal/gather";break;case"evaluation":this.orderList[t].href="personal/postEvaluation";break}},getListClassifyNum:function(){var t=this;Object(m["q"])().then((function(e){if("1"===e.code)for(var s in t.classifyList)switch(t.classifyList[s].interfaceName){case"num_status_n2":t.classifyList[s].num=e.t.num_status_n2;break;case"num_status_all":t.classifyList[s].num=e.t.num_status_all;break;case"num_status_1":t.classifyList[s].num=e.t.num_status_1;break;case"num_status_2":t.classifyList[s].num=e.t.num_status_2;break;case"num_status_3":t.classifyList[s].num=e.t.num_status_3;break;case"num_status_4":t.classifyList[s].num=e.t.num_status_4;break;case"num_status_5":t.classifyList[s].num=e.t.num_status_5;break;case"num_status_6":t.classifyList[s].num=e.t.num_status_6;break;case"num_no_recv_eval":t.classifyList[s].num=e.t.num_no_recv_eval;break;default:break}else t.$message.error(e.msg)}))},getListDate:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.currentPage=Number(e),sessionStorage.setItem("personalMainCurrentPage",this.currentPage),Object(m["f"])(this.classify,this.currentPage,this.pageSize).then((function(e){if("1"===e.code){t.orderList=[],t.total=e.t.row_total;var s=e.t.bill_list;t.changeListData(s)}else t.$message.error(e.msg)}))},changeListData:function(t){for(var e in t){var s={class:"",text:"",btnText:""},a="f";if(a=t[e].recv_user_pkid?t[e].recv_user_pkid==this.userInfo.pkid?"s":"f":"w","f"===a)s.class="failed",s.text="",s.btnText="詳情";else switch(t[e].bill_status){case"-2":s.class="abandon",s.text="已棄標",s.btnText="詳情";break;case"1":s.class="quote",s.text="競價中",s.btnText="詳情";break;case"2":s.class="deliver",s.text="待發貨",s.btnText="發貨";break;case"3":s.class="takeDel",s.text="待收貨",s.btnText="詳情";break;case"4":s.class="payment",s.text="待付款",s.btnText="詳情";break;case"5":s.class="gather",s.text="待收款",s.btnText="收款";break;case"6":if("N"===t[e].f_recv_eval){s.class="evaluation",s.text="待評價",s.btnText="評價";break}s.class="finished",s.text="已完成",s.btnText="詳情";break;default:break}var i={pkid:t[e].pkid,scope:t[e].pd_type,formNum:"需求單號"+t[e].bill_no,state:[t[e].deliver_address,t[e].bid_range],price:["RMB"==t[e].money_type?"￥":"$",t[e].total_price],unitNum:t[e].recv_user_num,deliver_date:t[e].deliver_date,status:s,winBidding:a,href:""};this.orderList.push(i)}this.setHref()}},created:function(){this.getListClassifyNum();var t=Number(sessionStorage.getItem("personalMainCurrentPage")),e=sessionStorage.getItem("personalMainCurrentClassify");this.currentPage=t||1,e&&"all"!==e?(this.initPage=this.currentPage,this.classify=e):this.getListDate(this.currentPage)},computed:_({},Object(f["c"])({userInfo:function(t){return t.userInfo}})),filters:{},components:{Top:r["a"],Logo:c["a"],Footer:o["a"],SilderBar:l["a"],Paging:u["a"]},watch:{classify:function(){sessionStorage.setItem("personalMainCurrentClassify",this.classify),1==this.initPage?this.getListDate():(this.getListDate(this.initPage),this.initPage=1)}}},v=p,g=(s("bdb3"),s("2877")),b=Object(g["a"])(v,a,i,!1,null,"49eaf676",null);e["default"]=b.exports},"3a70":function(t,e,s){"use strict";var a=s("2261"),i=s.n(a);i.a},bdb3:function(t,e,s){"use strict";var a=s("0039"),i=s.n(a);i.a},f9bd:function(t,e,s){"use strict";var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return t.userInfo?s("div",{staticClass:"sideBar"},[s("div",{staticClass:"side_portrait"},[t.porImg?s("img",{staticStyle:{width:"120px",height:"120px","text-align":"center","line-height":"120px",borderRadius:"50%"},attrs:{src:t.porImg,alt:"頭像加載失敗",onerror:"this.style.display='none'"}}):s("div",{staticClass:"porImg"})]),s("div",{staticClass:"side_department"},[s("span",{staticClass:"depIcon"}),s("span",[t._v(t._s(t.userInfo.dept_name))])]),s("div",{staticClass:"side_navigate"},t._l(t.list,(function(e,a){return s("router-link",{key:a,class:{active:t.chooseItem==e.ename},attrs:{to:e.path}},[s("div",[t._v("\n        "+t._s(e.name)+"\n        "),s("span",{directives:[{name:"show",rawName:"v-show",value:e.sub,expression:"item.sub"}]},[t._v("("+t._s(e.sub)+")")])])])})),1)]):t._e()},i=[],n=(s("8e6e"),s("ac6a"),s("456d"),s("bd86")),r=s("2f62");function c(t,e){var s=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),s.push.apply(s,a)}return s}function o(t){for(var e=1;e<arguments.length;e++){var s=null!=arguments[e]?arguments[e]:{};e%2?c(s,!0).forEach((function(e){Object(n["a"])(t,e,s[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(s)):c(s).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(s,e))}))}return t}var u={data:function(){return{porImg:"",list:[{ename:"order",name:"訂單管理",path:"/personal",sub:""},{ename:"information",name:"賬戶信息",path:"/personal/information",sub:""},{ename:"evaluation",name:"評價管理",path:"/personal/evaluation",sub:""},{ename:"setting",name:"設置",path:"/personal/setting",sub:""}]}},props:["chooseItem"],mounted:function(){this.porImg=this.porImgUrl},methods:{},watch:{porImgUrl:function(){this.porImg=this.porImgUrl}},computed:o({},Object(r["c"])({userInfo:function(t){return t.userInfo},porImgUrl:function(t){return t.porImgUrl}}))},l=u,m=(s("3a70"),s("2877")),f=Object(m["a"])(l,a,i,!1,null,"047b60a2",null);e["a"]=f.exports}}]);
//# sourceMappingURL=chunk-2d886a5f.36379da4.js.map