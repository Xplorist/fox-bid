(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-27b41bc4"],{"10e5":function(t,e,r){},4297:function(t,e,r){"use strict";var s=r("4351"),n=r.n(s);n.a},4351:function(t,e,r){},6950:function(t,e,r){"use strict";var s=r("10e5"),n=r.n(s);n.a},"6a94":function(t,e,r){"use strict";r.r(e);var s=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"delivery"},[r("Top"),r("Logo"),r("section",{attrs:{id:"content"}},[t.orderInfo?r("div",{staticClass:"main type-area"},[r("SilderBar",{attrs:{"choose-item":t.silderBarItem}}),r("div",{staticClass:"mainPart"},[r("div",{staticClass:"main_headline"},[r("div",{staticClass:"main_head_back"},[r("div",{staticClass:"head_back_backBtn",on:{click:function(e){return t.$router.back(-1)}}},[r("span"),t._v("\n              返回\n            ")]),r("div",{staticClass:"head_back_status"},[t._v("交貨")])]),r("div",{staticClass:"main_head_title"},[r("div",{staticClass:"head_tit_text"},[t._v("【模具】需求單號"+t._s(t.orderInfo.bill_no))]),r("div",{staticClass:"head_tit_time"},[t._v("交貨時間: "+t._s(t.orderInfo.deliver_date.split(" ")[0]))])])]),r("div",{staticClass:"main_baseInfo"},[r("div",{staticClass:"main_base_title"},[t._v("基本信息")]),r("div",{staticClass:"main_base_content"},[r("table",[r("tr",[r("td",[t._v("產品處")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.send_user.pd_office))])]),r("tr",[r("td",[t._v("需求單位")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.send_user.dept_name))])]),r("tr",[r("td",[t._v("交易法人名稱")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.send_user.legal_person))])]),r("tr",[r("td",[t._v("交貨地點")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.deliver_address))])]),r("tr",[r("td",[t._v("費用代碼")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.send_user.cost_code))])])]),r("table",[r("tr",[r("td",[t._v("接受總價")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.total_price))])]),r("tr",[r("td",[t._v("幣別")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.money_type))])]),r("tr",[r("td",[t._v("競標範圍")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.bid_range))])]),r("tr",[r("td",[t._v("競價開始時間")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.bid_start_date))])]),r("tr",[r("td",[t._v("競價結束時間")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.bid_end_date))])])])])]),r("div",{staticClass:"main_confirm"},[t._m(0),r("div",{staticClass:"main_con_conBtn",on:{click:t.confirm}},[r("svg",{attrs:{width:"265px",height:"65px",version:"1.1",xmlns:"http://www.w3.org/2000/svg"}},[r("polygon",{staticStyle:{fill:"none",stroke:"e51313","stroke-width":"1"},attrs:{points:"0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"}}),r("rect",{staticStyle:{fill:"e51313"},attrs:{x:"2",y:"2",width:"261",height:"61"}}),r("polyline",{staticStyle:{fill:"none",stroke:"#ea4242","stroke-width":"2"},attrs:{points:"10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"}})]),r("span",[t._v("確認交貨")])])])])],1):t._e()]),r("Footer")],1)},n=[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"main_con_upload"},[r("span"),r("p",[t._v("上傳憑證")])])}],a=r("e9c9"),i=r("2241"),o=r("fd2d"),d=r("f9bd"),c=r("f8b7"),_={data:function(){return{silderBarItem:"order",orderInfo:""}},methods:{getOrderInfo:function(t){var e=this;Object(c["c"])(t).then((function(t){"1"===t.code?e.orderInfo=t.t:e.$message.error(t.msg)}))},confirm:function(){this.updataStatus()},updataStatus:function(){var t=this,e={pkid:this.orderInfo.pkid,bill_status:3};Object(c["x"])(e).then((function(e){"1"===e.code?t.$router.push("/personal"):t.$message.error(e.msg)}))}},created:function(){this.getOrderInfo(this.$router.history.current.query.pkid)},components:{Top:a["a"],Logo:i["a"],Footer:o["a"],SilderBar:d["a"]}},l=_,u=(r("4297"),r("2877")),v=Object(u["a"])(l,s,n,!1,null,"19e1bf6f",null);e["default"]=v.exports},f9bd:function(t,e,r){"use strict";var s=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.userInfo?r("div",{staticClass:"sideBar"},[r("div",{staticClass:"side-portrait"},[t.porImg?r("img",{staticStyle:{width:"120px",height:"120px","text-align":"center","line-height":"120px",borderRadius:"50%"},attrs:{src:t.porImg,alt:"頭像加載失敗",onerror:"this.style.display='none'"}}):r("div",{staticClass:"porImg"})]),r("div",{staticClass:"side-department"},[r("span",{staticClass:"dep-icon"}),r("span",[t._v(t._s(t.userInfo.dept_name))])]),r("div",{staticClass:"side-navigate"},t._l(t.list,(function(e,s){return r("router-link",{key:s,class:{active:t.chooseItem==e.ename},attrs:{to:e.path}},[r("div",[t._v("\n        "+t._s(e.name)+"\n        "),r("span",{directives:[{name:"show",rawName:"v-show",value:e.sub,expression:"item.sub"}]},[t._v("("+t._s(e.sub)+")")])])])})),1)]):t._e()},n=[],a=(r("8e6e"),r("ac6a"),r("456d"),r("bd86")),i=r("2f62");function o(t,e){var r=Object.keys(t);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(t);e&&(s=s.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),r.push.apply(r,s)}return r}function d(t){for(var e=1;e<arguments.length;e++){var r=null!=arguments[e]?arguments[e]:{};e%2?o(r,!0).forEach((function(e){Object(a["a"])(t,e,r[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(r)):o(r).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(r,e))}))}return t}var c={data:function(){return{porImg:"",list:[{ename:"order",name:"訂單管理",path:"/personal",sub:""},{ename:"information",name:"賬戶信息",path:"/personal/information",sub:""},{ename:"evaluation",name:"評價管理",path:"/personal/evaluation",sub:""},{ename:"setting",name:"設置",path:"/personal/setting",sub:""}]}},props:["chooseItem"],mounted:function(){this.porImg=this.porImgUrl},methods:{},watch:{porImgUrl:function(){this.porImg=this.porImgUrl}},computed:d({},Object(i["c"])({userInfo:function(t){return t.userInfo},porImgUrl:function(t){return t.porImgUrl}}))},_=c,l=(r("6950"),r("2877")),u=Object(l["a"])(_,s,n,!1,null,"34d5c580",null);e["a"]=u.exports}}]);
//# sourceMappingURL=chunk-27b41bc4.88130126.js.map