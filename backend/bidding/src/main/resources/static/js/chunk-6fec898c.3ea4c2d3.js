(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6fec898c"],{"319d":function(t,e,r){"use strict";var n=r("8eb3"),s=r.n(n);s.a},"3ae8":function(t,e,r){"use strict";var n=r("ca08"),s=r.n(n);s.a},"6a94":function(t,e,r){"use strict";r.r(e);var n=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"delivery"},[r("Top"),r("Logo"),r("section",{attrs:{id:"content"}},[r("div",{staticClass:"main type-area"},[r("SilderBar",{attrs:{"choose-item":t.silderBarItem}}),r("div",{staticClass:"mainPart"},[r("div",{staticClass:"main_headline"},[r("div",{staticClass:"main_head_back"},[r("div",{staticClass:"head_back_backBtn",on:{click:function(e){return t.$router.back(-1)}}},[r("span"),t._v("\n              返回\n            ")]),r("div",{staticClass:"head_back_status"},[t._v("交貨")])]),r("div",{staticClass:"main_head_title"},[r("div",{staticClass:"head_tit_text"},[t._v("【模具】需求單號"+t._s(t.orderInfo.bill_no))]),r("div",{staticClass:"head_tit_time"},[t._v("交貨時間: "+t._s(t.orderInfo?t.orderInfo.deliver_date.split("T")[0]:""))])])]),r("div",{staticClass:"main_baseInfo"},[r("div",{staticClass:"main_base_title"},[t._v("基本信息")]),r("div",{staticClass:"main_base_content"},[r("table",[r("tr",[r("td",[t._v("產品處")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.send_user.pd_office:""))])]),r("tr",[r("td",[t._v("需求單位")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.send_user.dept_name:""))])]),r("tr",[r("td",[t._v("交易法人名稱")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.send_user.legal_person:""))])]),r("tr",[r("td",[t._v("交貨地點")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.deliver_address))])]),r("tr",[r("td",[t._v("費用代碼")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.send_user.cost_code:""))])])]),r("table",[r("tr",[r("td",[t._v("接受總價")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.total_price))])]),r("tr",[r("td",[t._v("幣別")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.money_type))])]),r("tr",[r("td",[t._v("競標範圍")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo.bid_range))])]),r("tr",[r("td",[t._v("競價開始時間")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.bid_start_date.split("T")[0]:""))])]),r("tr",[r("td",[t._v("競價結束時間")]),r("td",[t._v(":")]),r("td",[t._v(t._s(t.orderInfo?t.orderInfo.bid_end_date.split("T")[0]:""))])])])])]),r("div",{staticClass:"main_confirm"},[t._m(0),r("div",{staticClass:"main_con_conBtn",on:{click:t.confirm}},[r("svg",{attrs:{width:"265px",height:"65px",version:"1.1",xmlns:"http://www.w3.org/2000/svg"}},[r("polygon",{staticStyle:{fill:"none",stroke:"e51313","stroke-width":"1"},attrs:{points:"0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"}}),r("rect",{staticStyle:{fill:"e51313"},attrs:{x:"2",y:"2",width:"261",height:"61"}}),r("polyline",{staticStyle:{fill:"none",stroke:"#ea4242","stroke-width":"2"},attrs:{points:"10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"}})]),r("span",[t._v("確認交貨")])])])])],1)]),r("Footer")],1)},s=[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"main_con_upload"},[r("span"),r("p",[t._v("上傳憑證")])])}],a=r("e9c9"),o=r("2241"),i=r("fd2d"),d=r("f9bd"),c=r("f8b7"),_={data:function(){return{silderBarItem:"order",orderInfo:""}},methods:{getOrderInfo:function(t){var e=this;Object(c["a"])(t).then((function(t){"1"===t.code?(e.orderInfo=t.t,console.log(e.orderInfo)):e.$message.error("出錯啦，稍後再試試吧！")}))},confirm:function(){this.updataStatus()},updataStatus:function(){var t=this,e={pkid:this.orderInfo.pkid,bill_status:3};Object(c["v"])(e).then((function(e){"1"===e.code&&t.$router.push("/personal")}))}},created:function(){this.getOrderInfo(this.$router.history.current.query.pkid)},components:{Top:a["a"],Logo:o["a"],Footer:i["a"],SilderBar:d["a"]}},l=_,u=(r("319d"),r("2877")),p=Object(u["a"])(l,n,s,!1,null,"43298fc9",null);e["default"]=p.exports},"8eb3":function(t,e,r){},ca08:function(t,e,r){},f9bd:function(t,e,r){"use strict";var n=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.userInfo?r("div",{staticClass:"sideBar"},[r("div",{staticClass:"side_portrait"},[t.porImg?r("img",{staticStyle:{width:"120px",height:"120px","text-align":"center","line-height":"120px",borderRadius:"50%"},attrs:{src:t.porImg,alt:"頭像加載失敗",onerror:"this.style.display='none'"}}):r("div",{staticClass:"porImg"})]),r("div",{staticClass:"side_department"},[r("span",{staticClass:"depIcon"}),r("span",[t._v(t._s(t.userInfo.dept_name))])]),r("div",{staticClass:"side_navigate"},t._l(t.list,(function(e,n){return r("router-link",{key:n,class:{active:t.chooseItem==e.ename},attrs:{to:e.path}},[r("div",[t._v("\n        "+t._s(e.name)+"\n        "),r("span",{directives:[{name:"show",rawName:"v-show",value:e.sub,expression:"item.sub"}]},[t._v("("+t._s(e.sub)+")")])])])})),1)]):t._e()},s=[],a=(r("8e6e"),r("ac6a"),r("456d"),r("bd86")),o=r("2f62");function i(t,e){var r=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),r.push.apply(r,n)}return r}function d(t){for(var e=1;e<arguments.length;e++){var r=null!=arguments[e]?arguments[e]:{};e%2?i(r,!0).forEach((function(e){Object(a["a"])(t,e,r[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(r)):i(r).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(r,e))}))}return t}var c={data:function(){return{porImg:"",list:[{ename:"order",name:"訂單管理",path:"/personal",sub:""},{ename:"information",name:"賬戶信息",path:"/personal/information",sub:""},{ename:"evaluation",name:"評價管理",path:"/personal/evaluation",sub:""},{ename:"news",name:"消息",path:"",sub:"2"},{ename:"",name:"收藏",path:"",sub:""},{ename:"setting",name:"設置",path:"/personal/setting",sub:""}]}},props:["chooseItem"],mounted:function(){this.porImg=this.porImgUrl},methods:{},watch:{porImgUrl:function(){this.porImg=this.porImgUrl}},computed:d({},Object(o["c"])({userInfo:function(t){return t.userInfo},porImgUrl:function(t){return t.porImgUrl}}))},_=c,l=(r("3ae8"),r("2877")),u=Object(l["a"])(_,n,s,!1,null,"2ab418f5",null);e["a"]=u.exports}}]);
//# sourceMappingURL=chunk-6fec898c.3ea4c2d3.js.map