(function(e){function t(t){for(var r,a,s=t[0],c=t[1],u=t[2],l=0,d=[];l<s.length;l++)a=s[l],Object.prototype.hasOwnProperty.call(i,a)&&i[a]&&d.push(i[a][0]),i[a]=0;for(r in c)Object.prototype.hasOwnProperty.call(c,r)&&(e[r]=c[r]);f&&f(t);while(d.length)d.shift()();return o.push.apply(o,u||[]),n()}function n(){for(var e,t=0;t<o.length;t++){for(var n=o[t],r=!0,a=1;a<n.length;a++){var s=n[a];0!==i[s]&&(r=!1)}r&&(o.splice(t--,1),e=c(c.s=n[0]))}return e}var r={},a={app:0},i={app:0},o=[];function s(e){return c.p+"js/"+({about:"about"}[e]||e)+"."+{about:"22c721a7","chunk-004d533a":"f209e385","chunk-02c5fd90":"733ba0d1","chunk-0f9c8c96":"2fcd2a4e","chunk-1e6d5ed7":"e5f67ed3","chunk-2d0bff72":"567edaf6","chunk-377ba2c6":"f987d5ed","chunk-428f5b40":"05c4b84d","chunk-496b1c90":"8b9edae0","chunk-4df34fff":"eb35bac9","chunk-580a1ab4":"efbdb1cc","chunk-59833db6":"451a4d5c","chunk-5fb9e8e2":"7af9a09b","chunk-66ac5d7b":"90cae73a","chunk-7066fb88":"1f83e497","chunk-7083ebec":"6a70efd4","chunk-8964a700":"0e8f7e66","chunk-92dacd6a":"25b35da8","chunk-98b4a8a6":"af12d5c8","chunk-9e4aa0e0":"3fb3b179","chunk-b6ae1f2e":"8b6a0628","chunk-b9b5c7d8":"e92882c0","chunk-bdb82036":"7176575e","chunk-be2ff994":"c810e45a","chunk-d047ff38":"c5ea0490","chunk-1888e4ea":"7882f961"}[e]+".js"}function c(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,c),n.l=!0,n.exports}c.e=function(e){var t=[],n={"chunk-004d533a":1,"chunk-02c5fd90":1,"chunk-0f9c8c96":1,"chunk-1e6d5ed7":1,"chunk-377ba2c6":1,"chunk-428f5b40":1,"chunk-496b1c90":1,"chunk-4df34fff":1,"chunk-580a1ab4":1,"chunk-59833db6":1,"chunk-5fb9e8e2":1,"chunk-66ac5d7b":1,"chunk-7066fb88":1,"chunk-7083ebec":1,"chunk-8964a700":1,"chunk-92dacd6a":1,"chunk-98b4a8a6":1,"chunk-9e4aa0e0":1,"chunk-b6ae1f2e":1,"chunk-b9b5c7d8":1,"chunk-bdb82036":1,"chunk-be2ff994":1,"chunk-d047ff38":1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise((function(t,n){for(var r="css/"+({about:"about"}[e]||e)+"."+{about:"31d6cfe0","chunk-004d533a":"105f0613","chunk-02c5fd90":"c986eadf","chunk-0f9c8c96":"2b99dadc","chunk-1e6d5ed7":"9f0e3b7e","chunk-2d0bff72":"31d6cfe0","chunk-377ba2c6":"6c5d2506","chunk-428f5b40":"89be4d6a","chunk-496b1c90":"16420fa3","chunk-4df34fff":"4fcbcb90","chunk-580a1ab4":"b8b43d28","chunk-59833db6":"5d7d7e7d","chunk-5fb9e8e2":"b9801b4c","chunk-66ac5d7b":"a3669a11","chunk-7066fb88":"e1accc02","chunk-7083ebec":"8780cf35","chunk-8964a700":"dd745ddb","chunk-92dacd6a":"b0a7535d","chunk-98b4a8a6":"5a47d705","chunk-9e4aa0e0":"d2945643","chunk-b6ae1f2e":"3ba330f0","chunk-b9b5c7d8":"37ab143d","chunk-bdb82036":"58e0088f","chunk-be2ff994":"dedc3d4b","chunk-d047ff38":"8c88a201","chunk-1888e4ea":"31d6cfe0"}[e]+".css",i=c.p+r,o=document.getElementsByTagName("link"),s=0;s<o.length;s++){var u=o[s],l=u.getAttribute("data-href")||u.getAttribute("href");if("stylesheet"===u.rel&&(l===r||l===i))return t()}var d=document.getElementsByTagName("style");for(s=0;s<d.length;s++){u=d[s],l=u.getAttribute("data-href");if(l===r||l===i)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||i,o=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");o.code="CSS_CHUNK_LOAD_FAILED",o.request=r,delete a[e],f.parentNode.removeChild(f),n(o)},f.href=i;var p=document.getElementsByTagName("head")[0];p.appendChild(f)})).then((function(){a[e]=0})));var r=i[e];if(0!==r)if(r)t.push(r[2]);else{var o=new Promise((function(t,n){r=i[e]=[t,n]}));t.push(r[2]=o);var u,l=document.createElement("script");l.charset="utf-8",l.timeout=120,c.nc&&l.setAttribute("nonce",c.nc),l.src=s(e);var d=new Error;u=function(t){l.onerror=l.onload=null,clearTimeout(f);var n=i[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+a+")",d.name="ChunkLoadError",d.type=r,d.request=a,n[1](d)}i[e]=void 0}};var f=setTimeout((function(){u({type:"timeout",target:l})}),12e4);l.onerror=l.onload=u,document.head.appendChild(l)}return Promise.all(t)},c.m=e,c.c=r,c.d=function(e,t,n){c.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},c.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},c.t=function(e,t){if(1&t&&(e=c(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(c.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)c.d(n,r,function(t){return e[t]}.bind(null,r));return n},c.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return c.d(t,"a",t),t},c.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},c.p="/bid/",c.oe=function(e){throw console.error(e),e};var u=window["webpackJsonp"]=window["webpackJsonp"]||[],l=u.push.bind(u);u.push=t,u=u.slice();for(var d=0;d<u.length;d++)t(u[d]);var f=l;o.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("64a9"),a=n.n(r);a.a},1943:function(e,t,n){},"222c":function(e,t,n){"use strict";var r=n("3ab9"),a=n.n(r);a.a},2241:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",{attrs:{id:"logo"}},[n("div",{staticClass:"type-area logo-content"},[n("router-link",{attrs:{to:"/"}}),e._m(0)],1)])},a=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"search-box"},[n("input",{staticClass:"search-text",attrs:{type:"text",placeholder:"請輸入信息"}}),n("input",{staticClass:"search-btn",attrs:{type:"submit",value:"搜索"}})])}],i={name:"logo"},o=i,s=(n("222c"),n("2877")),c=Object(s["a"])(o,r,a,!1,null,"79c95e42",null);t["a"]=c.exports},"2aa5":function(e,t,n){"use strict";var r=n("3f49"),a=n.n(r);a.a},"365c":function(e,t,n){"use strict";n("6762"),n("2fdb");var r=n("bc3a"),a=n.n(r),i=n("2b0e"),o=n("5c96"),s=n("4360"),c=a.a.create({timeout:3e4,baseURL:"/bid"});i["default"].prototype.$http=a.a;var u=null;c.interceptors.request.use((function(e){return u=o["Loading"].service({text:"資源拉取中..."}),e}),(function(e){return Promise.reject(e)})),c.interceptors.response.use((function(e){u&&u.close();var t=e.status;return t>=200&&t<300||304===t?Promise.resolve(e.data):Promise.reject(e)}),(function(e){if(u&&u.close(),e.response)switch(e.response.status){case 404:o["Message"].error("網絡請求不存在");break;case 500:o["Message"].error("服務器崩潰啦");break;default:o["Message"].error(e.response.data.message)}else e.message.includes("timeout")?o["Message"].error("請求超時！請檢查網絡是否正常"):o["Message"].error("請求失敗！請檢查網絡是否連接");return Promise.reject(e)})),t["a"]={post:function(e,t,n){return c({method:"post",url:e,data:t,headers:{"Content-Type":n||"application/json"}})},get:function(e,t){return c({method:"get",url:e,params:t})},getWithToken:function(e,t){var n=s["a"].state.token;return c({method:"get",url:e,params:t,responseType:"blob",headers:{token:n}})},postWithToken:function(e,t,n){var r=s["a"].state.token;return c({method:"post",url:e,data:t,headers:{"Content-Type":n||"application/json",token:r}})}}},"3ab9":function(e,t,n){},"3bf2":function(e,t,n){},"3f49":function(e,t,n){},4360:function(e,t,n){"use strict";var r=n("2b0e"),a=n("2f62"),i=n("fcc2"),o={SET_TOKEN:function(e,t){sessionStorage.setItem("token",JSON.stringify(t)),e.token=t},SET_USERNAME:function(e,t){e.username=t},SET_USERINFO:function(e,t){sessionStorage.setItem("userInfo",JSON.stringify(t)),e.userInfo=t},SET_PORIMGURL:function(e,t){sessionStorage.setItem("porImgUrl",JSON.stringify(t)),e.porImgUrl=t},LOG_OUT:function(e){sessionStorage.clear(),e.username=null,e.token=null,e.userInfo=null,e.porImgUrl=null}},s=o,c={get_token:function(e,t){var n=e.commit;n("SET_TOKEN",t)},get_username:function(e,t){var n=e.commit;n("SET_USERNAME",t)},get_userInfo:function(e,t){var n=e.commit;n("SET_USERINFO",t)},get_porImgUrl:function(e,t){var n=e.commit;n("SET_PORIMGURL",t)}},u=c;r["default"].use(a["a"]);t["a"]=new a["a"].Store({state:i["a"],mutations:s,actions:u})},"56d7":function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var r=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},i=[],o={},s=o,c=(n("034f"),n("2877")),u=Object(c["a"])(s,a,i,!1,null,null,null),l=u.exports,d=(n("6762"),n("2fdb"),n("7f7f"),n("28a5"),n("8c4f")),f=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"home"},[n("Top"),n("Logo"),n("section",{attrs:{id:"content"}},[n("div",{staticClass:"con-tag type-area"}),n("div",{staticClass:"con-req type-area"},[e._m(0),n("div",{staticClass:"panel-filter"},[n("div",{staticClass:"fil-purpose"},[n("div",[e._v("類型:")]),n("select",{directives:[{name:"model",rawName:"v-model",value:e.filterCondition.purpose,expression:"filterCondition.purpose"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.$set(e.filterCondition,"purpose",t.target.multiple?n:n[0])}}},[n("option",{attrs:{value:"all"}},[e._v("全部")]),e._l(e.typeList,(function(e){return n("option",{key:e.pkid,attrs:{label:e.name},domProps:{value:e.name}})}))],2)]),n("div",{staticClass:"fil-scope"},[n("div",[e._v("開標範圍:")]),n("select",{directives:[{name:"model",rawName:"v-model",value:e.filterCondition.scope,expression:"filterCondition.scope"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.$set(e.filterCondition,"scope",t.target.multiple?n:n[0])}}},e._l(e.rangeList,(function(e){return n("option",{key:e.pkid,attrs:{label:e.name},domProps:{value:e.name}})})),0)]),n("div",{staticClass:"fil-state"},[n("div",[e._v("狀態:")]),n("select",{directives:[{name:"model",rawName:"v-model",value:e.filterCondition.state,expression:"filterCondition.state"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.$set(e.filterCondition,"state",t.target.multiple?n:n[0])}}},[n("option",{attrs:{value:"all"}},[e._v("全部")]),n("option",{attrs:{value:"ing"}},[e._v("競標中")]),n("option",{attrs:{value:"end"}},[e._v("已結束")])])]),n("div",{staticClass:"fil-site"},[n("div",[e._v("交貨地點:")]),n("select",{directives:[{name:"model",rawName:"v-model",value:e.filterCondition.site,expression:"filterCondition.site"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.$set(e.filterCondition,"site",t.target.multiple?n:n[0])}}},[n("option",{attrs:{value:"all"}},[e._v("全部")]),e._l(e.addressList,(function(e){return n("option",{key:e.pkid,attrs:{label:e.name},domProps:{value:e.name}})}))],2)]),n("div",{staticClass:"fil-time"},[n("div",[e._v("交貨時間:")]),n("select",{directives:[{name:"model",rawName:"v-model",value:e.filterCondition.time,expression:"filterCondition.time"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.$set(e.filterCondition,"time",t.target.multiple?n:n[0])}}},[n("option",{attrs:{value:"all"}},[e._v("默認")]),n("option",{attrs:{value:"0_5"}},[e._v("0-5天")]),n("option",{attrs:{value:"5_10"}},[e._v("5-10天")]),n("option",{attrs:{value:"10more"}},[e._v("10天以上")])])])]),n("div",{staticClass:"panel-list"},e._l(e.listData,(function(t,r){return n("div",{key:r,staticClass:"list-single"},[n("div",{staticClass:"single-left"},[n("div",{staticClass:"sin_l_pur"},[n("div",[e._v(e._s(t.purpose))])]),n("div",{staticClass:"sin_l_info"},[n("div",{staticClass:"info-top"},[n("div",{staticClass:"formNum"},[e._v("需求單號"+e._s(t.formNum))]),n("div",{staticClass:"state"},[n("div",{staticClass:"bidding"},[e._v(e._s(t.bill_status_text))]),n("div",{},[e._v(e._s(t.deliver_address))]),n("div",{},[e._v(e._s(t.bid_range))])])]),n("div",{staticClass:"info-bottom"},[n("div",{staticClass:"price"},[n("div",{staticClass:"kind"},[e._v(e._s("RMB"==t.money_type?"￥":"$"))]),n("div",{staticClass:"num"},[e._v(e._s(t.price))])]),n("div",{staticClass:"demand"},[n("div",[e._v("需求量:")]),n("div",{staticClass:"num"},[e._v(e._s(t.demand))])]),n("div",{staticClass:"unitNum"},[e._v(e._s(t.unitNum?t.unitNum:0)+"個單位已參與")])])])]),n("div",{staticClass:"single-right"},[n("div",{staticClass:"sin_r_left"},[n("div",{staticClass:"sin_r_l_dep"},[n("div",{staticClass:"icon"}),n("div",{staticClass:"text"},[e._v(e._s(t.dept_name))])]),n("div",{staticClass:"sin_r_l_time"},[n("div",[e._v("交貨時間:")]),n("div",{staticClass:"num"},[e._v(e._s(t.deliver_date.split(" ")[0]))])])]),n("div",{staticClass:"sin_r_right"},[n("div",[n("router-link",{attrs:{to:"/particulars?pkid="+t.pkid}},[e._v("查看/參與")])],1)])])])})),0),n("div",{staticClass:"paging",staticStyle:{backgroundColor:"#D3DFE7"}},[n("Paging",{attrs:{total:e.total,"current-page":e.currentPage,"page-size":e.pageSize},on:{getCurrentPage:e.getListDate}})],1)])]),n("Footer"),n("section",{attrs:{id:"fixBox"}},[e.porImgUrl?n("img",{staticStyle:{width:"100px",height:"100px","object-fit":"cover"},attrs:{src:e.porImgUrl,alt:""}}):e._e(),e.porImgUrl?n("div",{staticClass:"liFunc"},[e._m(1),e._m(2),e._m(3),e._m(4)]):e._e(),n("div",{directives:[{name:"show",rawName:"v-show",value:e.backFlag,expression:"backFlag"}],staticClass:"backTop",on:{click:function(t){return e.backTop()}}},[n("div",{staticClass:"backTop-icon"}),n("div",{staticClass:"backTop-text"},[e._v("返回頂端")])])])],1)},p=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"panel-title"},[n("div",{staticClass:"left-line"},[n("div",{staticClass:"theLine"}),n("div",{staticClass:"theBlock"})]),n("div",{staticClass:"center-text"},[e._v("模治檢具訂單")]),n("div",{staticClass:"right-line"},[n("div",{staticClass:"theBlock"}),n("div",{staticClass:"theLine"})])])},function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"singleFunc collect"},[n("div",{staticClass:"funcIcon"}),n("div",{staticClass:"funcText"},[e._v("收藏")])])},function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"singleFunc history"},[n("div",{staticClass:"funcIcon"}),n("div",{staticClass:"funcText"},[e._v("足跡")])])},function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"singleFunc news"},[n("div",{staticClass:"funcIcon"}),n("div",{staticClass:"funcText"},[e._v("消息")]),n("div",{staticClass:"funcNum"})])},function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"singleFunc order"},[n("div",{staticClass:"funcIcon"}),n("div",{staticClass:"funcText"},[e._v("我的訂單")])])}],v=(n("8e6e"),n("ac6a"),n("456d"),n("bd86")),h=(n("c5f6"),n("e9c9")),m=n("2241"),b=n("fd2d"),_=n("def5"),g=n("2f62"),k=n("f8b7"),y=n("c0e7");function C(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function O(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?C(n,!0).forEach((function(t){Object(v["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):C(n).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var T={name:"home",data:function(){return{listData:[],total:null,currentPage:1,pageSize:8,filterCondition:{purpose:"all",scope:"集團",state:"ing",site:"all",time:"all"},typeList:[],rangeList:[],addressList:[],backFlag:!1}},components:{Top:h["a"],Logo:m["a"],Footer:b["a"],Paging:_["a"]},methods:{handleScroll:function(){window.scrollY>400?this.backFlag=!0:this.backFlag=!1},backTop:function(){if(document.documentElement.scrollTop>0)if(document.documentElement.scrollTop>30)var e=setInterval((function(){document.documentElement.scrollTop-=10,document.documentElement.scrollTop<30&&(document.documentElement.scrollTop=0,clearInterval(e))}),5);else document.documentElement.scrollTop=0},query_classify:function(){var e=this;Object(y["i"])().then((function(t){"1"===t.code?e.typeList=t.t:e.$message.error(t.msg)})),Object(y["e"])().then((function(t){"1"===t.code?e.rangeList=t.t:e.$message.error(t.msg)})),Object(y["f"])().then((function(t){"1"===t.code?e.addressList=t.t:e.$message.error(t.msg)}))},getListDate:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.currentPage=Number(t),sessionStorage.setItem("homeCurrentPage",this.currentPage);var n={pd_type:this.filterCondition.purpose,bid_range:this.filterCondition.scope,bill_status:this.filterCondition.state,deliver_address:this.filterCondition.site,deliver_date:this.filterCondition.time,pageIndex:this.currentPage,pageSize:this.pageSize};this.userInfo&&"recv"===this.userInfo.send_recv_type?Object(k["c"])(n).then((function(t){if("1"===t.code){e.total=t.t.row_total;var n=t.t.bill_list;e.changeListData(n)}else e.$message.error(t.msg)})):Object(k["b"])(n).then((function(t){if("1"===t.code){e.total=t.t.row_total;var n=t.t.bill_list;e.changeListData(n)}else e.$message.error(t.msg)}))},changeListData:function(e){for(var t in this.listData=[],e){var n="";switch(e[t].bill_status){case"0":n="待發佈";break;case"1":n="待報價";break;case"2":n="待發貨";break;case"3":n="待收貨";break;case"4":n="待付款";break;case"5":n="待收款";break;case"6":n="已完成";break;default:break}var r={pkid:e[t].pkid,purpose:e[t].pd_type,formNum:e[t].bill_no,bill_status:e[t].bill_status,bill_status_text:n,deliver_address:e[t].deliver_address,bid_range:e[t].bid_range,price:e[t].total_price,money_type:e[t].money_type,demand:e[t].amount,unitNum:e[t].recv_user_num,dept_name:e[t].send_user?e[t].send_user.dept_name:"",deliver_date:e[t].deliver_date.split("T")[0]};this.listData.push(r)}}},created:function(){var e=Number(sessionStorage.getItem("homeCurrentPage"));e=e||1,this.getListDate(e),this.query_classify()},mounted:function(){window.addEventListener("scroll",this.handleScroll,!0)},destroyed:function(){window.removeEventListener("scroll",this.handleScroll)},computed:O({},Object(g["c"])({porImgUrl:function(e){return e.porImgUrl},userInfo:function(e){return e.userInfo}})),watch:{filterCondition:{handler:function(){this.getListDate()},deep:!0}}},I=T,S=(n("f18f"),Object(c["a"])(I,f,p,!1,null,"65b507df",null)),E=S.exports,w=n("4360");r["default"].use(d["a"]);var P=function(){return new d["a"]({mode:"history",base:"/bid/",routes:[{path:"/",name:"home",component:E},{path:"/login",name:"login",component:function(){return n.e("chunk-b6ae1f2e").then(n.bind(null,"3150"))}},{path:"/personal",component:function(){return n.e("about").then(n.bind(null,"94cf"))},meta:{title:"permission",roles:["recv"]},children:[{path:"",name:"per-main",component:function(){return n.e("chunk-1e6d5ed7").then(n.bind(null,"252c"))}},{path:"delivery",name:"per-delivery",component:function(){return n.e("chunk-0f9c8c96").then(n.bind(null,"6a94"))}},{path:"evaluation",name:"per-evaluation",component:function(){return n.e("chunk-9e4aa0e0").then(n.bind(null,"6ba7"))}},{path:"information",name:"per-information",component:function(){return n.e("chunk-4df34fff").then(n.bind(null,"9165"))}},{path:"postEvaluation",name:"per-postEvaluation",component:function(){return n.e("chunk-7083ebec").then(n.bind(null,"b41e"))}},{path:"gather",name:"per-gather",component:function(){return n.e("chunk-98b4a8a6").then(n.bind(null,"5d8a"))}},{path:"setting",name:"per-setting",component:function(){return n.e("chunk-66ac5d7b").then(n.bind(null,"6682"))}}]},{path:"/particulars",name:"particulars",component:function(){return n.e("chunk-be2ff994").then(n.bind(null,"8013"))}},{path:"/biddingDoc",name:"biddingDoc",component:function(){return n.e("chunk-428f5b40").then(n.bind(null,"5019"))}},{path:"/merchant",component:function(){return n.e("chunk-2d0bff72").then(n.bind(null,"4088"))},children:[{path:"",name:"entryFir",component:function(){return n.e("chunk-b9b5c7d8").then(n.bind(null,"12a2"))}},{path:"entrySec",name:"entrySec",component:function(){return n.e("chunk-8964a700").then(n.bind(null,"b4a9"))}},{path:"information",name:"information",component:function(){return n.e("chunk-496b1c90").then(n.bind(null,"6ec3"))}}]},{path:"/contract",name:"contract",component:function(){return n.e("chunk-580a1ab4").then(n.bind(null,"59d3"))}},{path:"/404",name:"404",component:function(){return n.e("chunk-bdb82036").then(n.bind(null,"efd7"))}},{path:"/demand",redirect:"/demand/order",name:"demand",meta:{title:"permission",roles:["send"]},component:function(){return n.e("chunk-004d533a").then(n.bind(null,"1dcc"))},children:[{path:"order",name:"de-order",component:function(){return n.e("chunk-59833db6").then(n.bind(null,"39e7"))}},{path:"information",name:"de-information",component:function(){return n.e("chunk-5fb9e8e2").then(n.bind(null,"aef7"))}},{path:"evaluation",name:"de-evaluation",component:function(){return n.e("chunk-02c5fd90").then(n.bind(null,"54df"))}},{path:"postEva",name:"de-postEva",component:function(){return n.e("chunk-7066fb88").then(n.bind(null,"bcbd"))}},{path:"publish",name:"de-publish",component:function(){return n.e("chunk-d047ff38").then(n.bind(null,"6b8c"))}},{path:"chooseDetails",name:"de-chooseDetails",component:function(){return n.e("chunk-92dacd6a").then(n.bind(null,"2c4b"))}},{path:"setting",name:"de-setting",component:function(){return n.e("chunk-377ba2c6").then(n.bind(null,"1480"))}}]}]})},j=P();j.beforeEach((function(e,t,n){var r=sessionStorage.getItem("token"),a=["/personal","/demand"],i="/"+e.path.split("/")[1];if(null===e.name)console.log("這是錯誤的頁面"),n({path:"/404"});else if(a.indexOf(i)>=0)if(r&&null!=r){var o=w["a"].state.userInfo.send_recv_type,s=e.matched[0].meta.roles.includes(o);s?n():(console.log("這是你不該來的頁面"),n({path:"/404"}))}else n({path:"/login"});else n()})),j.afterEach((function(){}));var x=j,N=n("5c96"),q=n.n(N),L=(n("0fae"),n("8237")),$=n.n(L),D=n("c0e9"),U=n.n(D),W=n("e511"),F=n.n(W),M={install:function(e){e.prototype.$getPdf=function(e,t){window.pageYOffset=0,document.documentElement.scrollTop=0,document.body.scrollTop=0;for(var n=[595.28,841.89],r=document.querySelector("#".concat(e)),a=document.querySelector("#tbSec"),i=document.querySelector("#sign"),o=document.querySelector("#annotation"),s=r.clientWidth,c=s/n[0]*n[1],u=a.childNodes,l=a.offsetTop-r.offsetTop,d=1,f=0,p=u.length;f<p;f++){var v=u[f],h=v.clientHeight;v.offsetTop+l+h>c*d&&(d++,this.addElement(v,h,"tbSec"))}var m=i.offsetTop-r.offsetTop,b=i.clientHeight;m+b>c*d&&(d++,this.addElement(i,b,"sign"));var _=o.offsetTop-r.offsetTop,g=o.clientHeight;_+g>c*d&&(d++,this.addElement(o,g,"anno"));var k=this;U()(r,{useCORS:!0,scale:2}).then((function(e){var r=e.width,a=e.height,i=r/n[0]*n[1],o=a,s=0,c=0,u=n[0]-2*c,l=u/r*a,d=e.toDataURL("image/jpeg",1),f=new F.a("","pt","a4");if(o<i)f.addImage(d,"JPEG",c,s,u,l);else while(o>0)f.addImage(d,"JPEG",c,s,u,l),o-=i,s-=n[1],o>0&&f.addPage();f.save(t+".pdf"),k.delElement()}))}}};r["default"].config.productionTip=!1,r["default"].prototype.$md5=$.a,r["default"].use(q.a),r["default"].use(M),new r["default"]({router:x,store:w["a"],render:function(e){return e(l)}}).$mount("#app")},"5fdc":function(e,t,n){"use strict";var r=n("1943"),a=n.n(r);a.a},"64a9":function(e,t,n){},8947:function(e,t,n){"use strict";var r=n("3bf2"),a=n.n(r);a.a},a033:function(e,t,n){},c0e7:function(e,t,n){"use strict";n.d(t,"a",(function(){return a})),n.d(t,"j",(function(){return i})),n.d(t,"h",(function(){return o})),n.d(t,"d",(function(){return s})),n.d(t,"b",(function(){return c})),n.d(t,"c",(function(){return u})),n.d(t,"f",(function(){return l})),n.d(t,"e",(function(){return d})),n.d(t,"i",(function(){return f})),n.d(t,"g",(function(){return p}));var r=n("365c");function a(e){var t="/api/user/ck_user_is_exist";return r["a"].post(t,e)}function i(e){var t="/api/user/register";return r["a"].post(t,e)}function o(){var e="/api/baseInfo/query_factory_list",t={};return r["a"].post(e,t)}function s(){var e="/api/baseInfo/query_SECN_CMPY_list",t={};return r["a"].post(e,t)}function c(e){var t="/api/baseInfo/query_ENTRPS_GROUP_list";return e={secn_cmpy_pkid:e},r["a"].post(t,e)}function u(e){var t="/api/baseInfo/query_PD_OFFICE_list";return e={entrps_group_pkid:e},r["a"].post(t,e)}function l(){var e="/api/baseInfo/query_deliver_address_list",t={};return r["a"].post(e,t)}function d(){var e="/api/baseInfo/query_bid_range_list",t={};return r["a"].post(e,t)}function f(){var e="/api/baseInfo/query_pd_type_list",t={};return r["a"].post(e,t)}function p(e,t){var n="/api/baseInfo/query_dept_name_by_dept_no",a={dept_no:e,fctry_zone:t};return r["a"].post(n,a)}},def5:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"paging"},[n("el-pagination",{attrs:{layout:"prev, pager, next",total:e.total,"current-page":e.currentPage,"page-size":e.pageSize,"prev-text":"上一頁","next-text":"下一頁"},on:{"current-change":e.sendPage}})],1)},a=[],i=(n("c5f6"),{props:{total:{type:Number},currentPage:{type:Number,default:1},pageSize:{type:Number}},methods:{sendPage:function(e){this.$emit("getCurrentPage",e)}}}),o=i,s=(n("5fdc"),n("2877")),c=Object(s["a"])(o,r,a,!1,null,"3cb675c8",null);t["a"]=c.exports},e9c9:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",{attrs:{id:"top"}},[n("div",{staticClass:"type-area top-nav"},[n("div",{staticClass:"backHome"},[n("router-link",{directives:[{name:"show",rawName:"v-show",value:e.backHome||!1,expression:"backHome || false "}],attrs:{to:""}},[e._v("\n        返回首頁\n      ")])],1),n("ul",[n("li",[e.userInfo?n("router-link",{attrs:{to:""}},[e._v("歡迎您："+e._s(e.userInfo.username))]):n("router-link",{attrs:{to:"/login"}},[e._v("請登錄")])],1),n("li",[n("router-link",{attrs:{to:"/"}},[e._v("主頁")])],1),n("li",[n("router-link",{attrs:{to:e.userInfo?"send"==e.userInfo.send_recv_type?"/demand":"/personal":"/login"}},[e._v("個人中心")])],1),e.userInfo?n("li",{on:{click:e.quit}},[n("router-link",{attrs:{to:""}},[e._v("退出登錄")])],1):e._e(),n("li",[n("router-link",{attrs:{to:"/merchant"}},[e._v("註冊")])],1)])])])},a=[],i=(n("8e6e"),n("ac6a"),n("456d"),n("bd86")),o=n("2f62");function s(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?s(n,!0).forEach((function(t){Object(i["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):s(n).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var u={name:"top",data:function(){return{}},methods:{quit:function(){this.$router.push("/login"),this.$store.commit("LOG_OUT")}},props:{backHome:Boolean},computed:c({},Object(o["c"])({username:function(e){return e.username},userInfo:function(e){return e.userInfo},token:function(e){return e.token}}))},l=u,d=(n("8947"),n("2877")),f=Object(d["a"])(l,r,a,!1,null,"0d689044",null);t["a"]=f.exports},f18f:function(e,t,n){"use strict";var r=n("a033"),a=n.n(r);a.a},f8b7:function(e,t,n){"use strict";n.d(t,"p",(function(){return a})),n.d(t,"u",(function(){return i})),n.d(t,"e",(function(){return o})),n.d(t,"n",(function(){return s})),n.d(t,"a",(function(){return c})),n.d(t,"d",(function(){return u})),n.d(t,"o",(function(){return l})),n.d(t,"q",(function(){return d})),n.d(t,"b",(function(){return f})),n.d(t,"c",(function(){return p})),n.d(t,"v",(function(){return v})),n.d(t,"s",(function(){return h})),n.d(t,"r",(function(){return m})),n.d(t,"f",(function(){return b})),n.d(t,"l",(function(){return _})),n.d(t,"m",(function(){return g})),n.d(t,"k",(function(){return k})),n.d(t,"i",(function(){return y})),n.d(t,"j",(function(){return C})),n.d(t,"h",(function(){return O})),n.d(t,"g",(function(){return T})),n.d(t,"t",(function(){return I}));var r=n("365c");function a(e){var t="/api/bill/saveBill";return r["a"].postWithToken(t,e)}function i(e){var t="/api/bill/submitBill";return r["a"].postWithToken(t,e)}function o(e,t,n){var a={bill_status:e,pageIndex:t,pageSize:n},i="/api/bill/query_bill_list_send_user";return r["a"].postWithToken(i,a)}function s(){var e={},t="/api/bill/query_status_bill_num_send_user";return r["a"].postWithToken(t,e)}function c(e){var t={pkid:e},n="/api/bill/query_bill_by_pkid";return r["a"].post(n,t)}function u(e,t,n){var a={bill_status:e,pageIndex:t,pageSize:n},i="/api/bill/query_bill_list_recv_user";return r["a"].postWithToken(i,a)}function l(){var e={},t="/api/bill/query_status_num_recv_user";return r["a"].postWithToken(t,e)}function d(e){var t="/api/bill/save_give_price";return r["a"].postWithToken(t,e)}function f(e){var t="/api/bill/query_bill_list_not_login";return r["a"].post(t,e)}function p(e){var t="/api/bill/query_bill_list_recv_main";return r["a"].postWithToken(t,e)}function v(e){var t="/api/bill/update_bill_status";return r["a"].postWithToken(t,e)}function h(e){var t="/api/bill/save_send_eval";return r["a"].postWithToken(t,e)}function m(e){var t="/api/bill/save_recv_eval";return r["a"].postWithToken(t,e)}function b(e){var t={bill_pkid:e},n="/api/bill/query_give_price_by_bill_pkid";return r["a"].postWithToken(n,t)}function _(e,t){var n={pageIndex:e,pageSize:t},a="/api/bill/query_send_get_eval_list";return r["a"].postWithToken(a,n)}function g(e,t){var n={pageIndex:e,pageSize:t},a="/api/bill/query_send_make_eval_list";return r["a"].postWithToken(a,n)}function k(){var e={},t="/api/bill/query_send_get_eval_avg";return r["a"].postWithToken(t,e)}function y(e,t){var n={pageIndex:e,pageSize:t},a="/api/bill/query_recv_get_eval_list";return r["a"].postWithToken(a,n)}function C(e,t){var n={pageIndex:e,pageSize:t},a="/api/bill/query_recv_make_eval_list";return r["a"].postWithToken(a,n)}function O(){var e={},t="/api/bill/query_recv_get_eval_avg";return r["a"].postWithToken(t,e)}function T(e){var t="/api/bill/query_give_price_list";return r["a"].postWithToken(t,e)}function I(e){var t={pkid:e},n="/api/bill/select_win_bid";return r["a"].postWithToken(n,t)}},fcc2:function(e,t,n){"use strict";var r={username:"",token:JSON.parse(sessionStorage.getItem("token")),userInfo:JSON.parse(sessionStorage.getItem("userInfo")),porImgUrl:JSON.parse(sessionStorage.getItem("porImgUrl"))};t["a"]=r},fd2d:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",{attrs:{id:"footer"}},[n("div",{staticClass:"foo-warpper type-area"},[e._m(0),n("div",{staticClass:"customer"},[e._v("\n          客戶服務: 5073-66645\n        ")]),n("div",{staticClass:"blogroll"},[n("div",[e._v("友情鏈接: ")]),n("div",[n("router-link",{attrs:{to:""}},[e._v("\n              MAC(I)智能雲網平台\n            ")])],1),n("div",{staticClass:"line"}),n("div",[n("router-link",{attrs:{to:""}},[e._v("\n              MAC(I)人事點名系統\n            ")])],1),n("div",{staticClass:"line"}),n("div",[n("router-link",{attrs:{to:""}},[e._v("\n              MAC(I)金流系統                \n            ")])],1),n("div",{staticClass:"line"}),n("div",[n("router-link",{attrs:{to:""}},[e._v("\n              智能刀具管理               \n            ")])],1)])])])},a=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"technical"},[n("div",{staticClass:"m-logo"}),n("div",{staticClass:"text"},[e._v("技術服務: 智能雲網絡開發成都分部")])])}],i={},o=i,s=(n("2aa5"),n("2877")),c=Object(s["a"])(o,r,a,!1,null,"10680a84",null);t["a"]=c.exports}});
//# sourceMappingURL=app.d7ef39d4.js.map