(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9a53b9c6"],{1169:function(t,e,i){var n=i("2d95");t.exports=Array.isArray||function(t){return"Array"==n(t)}},"2ab3":function(t,e,i){},3674:function(t,e,i){"use strict";var n=i("2ab3"),r=i.n(n);r.a},"37c8":function(t,e,i){e.f=i("2b4c")},"3a72":function(t,e,i){var n=i("7726"),r=i("8378"),o=i("2d00"),a=i("37c8"),s=i("86cc").f;t.exports=function(t){var e=r.Symbol||(r.Symbol=o?{}:n.Symbol||{});"_"==t.charAt(0)||t in e||s(e,t,{value:a.f(t)})}},"3ebc":function(t,e,i){"use strict";i.d(e,"b",(function(){return r})),i.d(e,"a",(function(){return o}));var n=i("fcc2");function r(t,e,i){var r=encodeURIComponent(t||n["a"].userInfo.user_pic_file.file_save_path),o=encodeURIComponent(e||n["a"].userInfo.user_pic_file.file_save_name),a=encodeURIComponent(i||n["a"].userInfo.user_pic_file.file_origin_name),s="/bidding/api/pic_show/load",l=s+"?file_save_path="+r+"&file_save_name="+o+"&file_origin_name="+a;return l}function o(t,e,i){var n=encodeURIComponent(t),r=encodeURIComponent(e),o=encodeURIComponent(i),a="/bidding/api/pic_show/download",s=a+"?file_save_path="+n+"&file_save_name="+r+"&file_origin_name="+o;return s}},"3f5e":function(t,e,i){"use strict";i.d(e,"d",(function(){return r})),i.d(e,"b",(function(){return o})),i.d(e,"c",(function(){return a})),i.d(e,"a",(function(){return s}));var n=i("365c");function r(t){var e="/api/ftp_file/upload",i="multipart/form-data";return n["a"].post(e,t,i)}function o(t){var e="/api/ftp_file/delete";return n["a"].post(e,t)}function a(t){var e="/api/bill/delete_part_doc_file_by_pkid";return n["a"].post(e,t)}function s(t){return n["a"].getWithToken(t)}},"67ab":function(t,e,i){var n=i("ca5a")("meta"),r=i("d3f4"),o=i("69a8"),a=i("86cc").f,s=0,l=Object.isExtensible||function(){return!0},c=!i("79e5")((function(){return l(Object.preventExtensions({}))})),d=function(t){a(t,n,{value:{i:"O"+ ++s,w:{}}})},f=function(t,e){if(!r(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;if(!o(t,n)){if(!l(t))return"F";if(!e)return"E";d(t)}return t[n].i},u=function(t,e){if(!o(t,n)){if(!l(t))return!0;if(!e)return!1;d(t)}return t[n].w},_=function(t){return c&&v.NEED&&l(t)&&!o(t,n)&&d(t),t},v=t.exports={KEY:n,NEED:!1,fastKey:f,getWeak:u,onFreeze:_}},"7bbc":function(t,e,i){var n=i("6821"),r=i("9093").f,o={}.toString,a="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[],s=function(t){try{return r(t)}catch(e){return a.slice()}};t.exports.f=function(t){return a&&"[object Window]"==o.call(t)?s(t):r(n(t))}},8013:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return t.orderInfo?i("div",{staticClass:"main"},[i("Top"),i("Logo"),i("section",{attrs:{id:"content"}},[i("div",{staticClass:"wrapper type-area"},[t._m(0),i("div",{staticClass:"con-title"},[i("div",{staticClass:"tit-text"},[t._v("【治具】需求單號"+t._s(t.orderInfo.bill_no))]),i("div",{staticClass:"tit-info"},[i("div",{staticClass:"info-price info-single"},[t._v("標價: "+t._s("RMB"===t.orderInfo.money_type?"￥":"$")+t._s(t.orderInfo.total_price))]),i("div",{staticClass:"info-num info-single"},[t._v("需求數量: "+t._s(t.orderInfo.amount))]),i("div",{staticClass:"info-time info-single"},[t._v("投標截止日期: "+t._s(t.orderInfo.bid_end_date))])])]),i("div",{staticClass:"con-main"},[i("div",{staticClass:"panel_info"},[i("div",{staticClass:"pan_info_Top"},[i("div",{staticClass:"baseInfo"},[t._m(1),i("table",{staticClass:"ba_content"},[i("tr",[i("td",[t._v("需求單位")]),i("td",[t._v(":")]),i("td",{staticClass:"blueTd"},[i("span"),i("router-link",{staticStyle:{color:"#0096ff"},attrs:{to:"/merchant/information?pkid="+t.orderInfo.send_user.pkid}},[t._v(t._s(t.orderInfo.send_user.dept_name))])],1)]),i("tr",[i("td",[t._v("交易法人名稱")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.send_user.legal_person))])]),i("tr",[i("td",[t._v("交貨地點")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.deliver_address))])]),i("tr",[i("td",[t._v("競價開始時間")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.bid_start_date))])]),i("tr",[i("td",[t._v("競價結束時間")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.bid_end_date))])]),i("tr",[i("td",[t._v("實際交貨時間")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.deliver_date.split(" ")[0]))])]),i("tr",[i("td",[t._v("類型")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.pd_type))])]),i("tr",[i("td",[t._v("費用代碼")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.send_user.cost_code))])]),i("tr",[i("td",[t._v("接受總價")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.total_price))])]),i("tr",[i("td",[t._v("幣別")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.money_type))])]),i("tr",[i("td",[t._v("競標範圍")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.bid_range))])]),i("tr",[i("td",[t._v("交貨方式")]),i("td",[t._v(":")]),i("td",[t._v(t._s(t.orderInfo.deliver_way))])])])]),i("div",{staticClass:"accessory"},[t._m(2),i("div",{staticClass:"ac_content"},t._l(t.orderInfo.file_list,(function(e){return i("div",{key:e.pkid,staticClass:"ac_con_item",style:{cursor:"recv"==t.send_recv_type||"send"==t.send_recv_type?"pointer":"not-allowed"},on:{click:function(i){return t.downloadOne(e.file_save_path,e.file_save_name,e.file_origin_name)}}},[i("div",{staticClass:"item-wrapper"},[i("div",{staticClass:"itemIcon"}),i("div",{staticClass:"itemName"},[t._v(t._s(e.file_origin_name))])])])})),0),i("div",{staticClass:"ac_allDownload",style:{cursor:"recv"==t.send_recv_type||"send"==t.send_recv_type?"pointer":"not-allowed"},on:{click:t.downloadAll}},[t._v("全部下載")])])]),i("div",{staticClass:"pan_info_req"},[t._m(3),i("pre",{staticClass:"req_content"},[t._v(t._s(t.orderInfo.make_requ))])]),"send"!==t.send_recv_type?i("div",{staticClass:"pan_info_join",style:{cursor:"recv"==t.send_recv_type?"pointer":"not-allowed"},on:{click:function(e){t.joinFlag?t.checkList("uploaded"):t.alertBoxShow()}}},[i("svg",{attrs:{width:"265px",height:"65px",version:"1.1",xmlns:"http://www.w3.org/2000/svg"}},[i("polygon",{staticStyle:{fill:"none",stroke:"#0096FF","stroke-width":"1"},attrs:{points:"0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"}}),i("rect",{staticStyle:{fill:"#0096FF"},style:{fill:"recv"==t.send_recv_type?"#0096FF":"0096FF"},attrs:{x:"2",y:"2",width:"261",height:"61"}}),i("polyline",{staticStyle:{fill:"none",stroke:"#3FB0FF","stroke-width":"2"},attrs:{points:"10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"}})]),i("span",[t._v(t._s(t.joinFlag?"查看標書":"參與報價"))])]):t._e()]),i("div",{staticClass:"panel_info_sec"},[i("div",{staticClass:"biddingUnits"},[t._m(4),t.orderInfo.give_recv_user_list&&t.orderInfo.give_recv_user_list.length>0?i("div",{staticClass:"bid_content"},t._l(t.orderInfo.give_recv_user_list,(function(e){return i("div",{key:e.pkid,staticClass:"bid_con_item"},[i("div",{staticClass:"item-wrapper",class:{active:"Y"===e.f_win_bid}},[i("span",{staticClass:"icon"}),i("router-link",{attrs:{to:{path:"/merchant/information",query:{pkid:e.pkid}}}},[t._v(t._s(e.dept_name))])],1)])})),0):i("div",{staticClass:"bid_content"},[i("p",{staticStyle:{color:"#626f7f"}},[t._v("暫無投標")])])]),i("div",{staticClass:"evaluate"},[t._m(5),i("div",{staticClass:"eva_content"},[t.orderInfo.send_eval?i("p",[t._v("需求方："+t._s(t.orderInfo.send_eval.summary_text))]):t._e(),t.orderInfo.recv_eval?i("p",[t._v("接單方："+t._s(t.orderInfo.recv_eval.summary_text))]):t._e()])])])])])]),i("Footer"),i("section",{directives:[{name:"show",rawName:"v-show",value:t.alertBoxFlag,expression:"alertBoxFlag"}],attrs:{id:"alertBox"}},[i("div",{ref:"alertBox",staticClass:"content"},[i("div",{staticClass:"con_top"},[i("div",{staticClass:"boxName"},[t._v("接單競價")]),i("div",{staticClass:"closeBox",on:{click:t.alertBoxShow}})]),i("div",{staticClass:"con_form"},[i("div",{staticClass:"con_form_title"},[i("div",{staticClass:"tit_text"},[t._v("【治具】需求單號"+t._s(t.orderInfo.bill_no))]),i("div",{staticClass:"tit_info"},[i("div",{staticClass:"tit_info_single"},[t._v("標價: "+t._s(t.orderInfo.total_price)+t._s(t.orderInfo.money_type))]),i("div",{staticClass:"tit_info_single"},[t._v("需求數量: "+t._s(t.orderInfo.amount))]),i("div",{staticClass:"tit_info_single"},[t._v("交貨時間: "+t._s(t.orderInfo.deliver_date.split(" ")[0]))])])]),i("div",{staticClass:"con_form_listOne"},[i("div",{staticClass:"listOne_info"},[t._v("投標法人: "+t._s(t.orderInfo.send_user.legal_person))]),i("table",{staticClass:"listOne_table",attrs:{cellspacing:"0",cellpadding:"0"}},[i("tr",[i("th",[t._v("幣別")]),i("th",[t._v("總報價（"+t._s(t.orderInfo.money_type)+"）")]),i("th",[t._v("交期")]),i("th",[t._v("備註")])]),t.joinFlag?i("tr",[i("td",[t._v(t._s(t.orderInfo.money_type))]),i("td",[t._v(t._s(t.totalPrices*t.orderInfo.amount))]),i("td",[t._v(t._s(t.deliver_date.split(" ")[0]))]),i("td",[t._v(t._s(t.bidReason))])]):i("tr",[i("td",[t._v(t._s(t.orderInfo.money_type))]),i("td",[t._v(t._s(t.totalPrices*t.orderInfo.amount))]),i("td",[i("el-date-picker",{attrs:{type:"date",placeholder:t.orderInfo.deliver_date.split(" ")[0]},model:{value:t.deliver_date,callback:function(e){t.deliver_date=e},expression:"deliver_date"}})],1),i("td",[i("el-input",{attrs:{type:"textarea",maxlength:"50"},model:{value:t.bidReason,callback:function(e){t.bidReason=e},expression:"bidReason"}})],1)])])]),i("div",{staticClass:"con_form_listSec"},[t._m(6),i("table",{staticClass:"listSec_table",attrs:{cellspacing:"0",cellpadding:"0"}},[i("tr",[i("th",{staticStyle:{width:"80px"}},[t._v("序號")]),i("th",{staticStyle:{width:"290px"}},[t._v("零件名")]),i("th",{staticStyle:{width:"120px"}},[t._v("零件數量")]),i("th",{staticStyle:{width:"280px"}},[t._v("報價（"+t._s(t.orderInfo.money_type)+"/個）")]),i("th",{staticStyle:{width:"120px"}},[t._v("小計（"+t._s(t.orderInfo.money_type)+"）")]),i("th",{staticStyle:{width:"80px"}},[t._v("操作")])]),t._l(t.quotatList,(function(e,n){return i("tr",{key:e.id},[i("td",[t._v(t._s(n+1))]),i("td",[t._v(t._s(e.name.split(".")[0]))]),t.joinFlag?i("td",[t._v(t._s(e.num))]):i("td",[i("el-input",{staticClass:"el-input-text-center",staticStyle:{width:"100px"},attrs:{type:"number"},model:{value:e.num,callback:function(i){t.$set(e,"num",i)},expression:"item.num"}})],1),t.joinFlag?i("td",[t._v(t._s(e.price))]):i("td",[i("el-input",{staticClass:"el-input-text-center",staticStyle:{width:"160px"},attrs:{type:"number"},model:{value:e.price,callback:function(i){t.$set(e,"price",i)},expression:"item.price"}})],1),i("td",[t._v(t._s(e.num*e.price))]),t.joinFlag?i("td"):i("td",{staticStyle:{color:"#0096ff",cursor:"pointer"},on:{click:function(e){return t.delQuota(n)}}},[t._v("刪除")])])})),i("tr",[i("td",{directives:[{name:"show",rawName:"v-show",value:t.otherList.length,expression:"otherList.length"}],staticStyle:{height:"10px",background:"#c8d4de"},attrs:{colspan:"6"}},[t._v("自定義")])]),t._l(t.otherList,(function(e,n){return i("tr",{key:e.id},[i("td",[t._v(t._s(n+1))]),t.joinFlag?i("td",[t._v(t._s(e.selfDefineName))]):i("td",[i("el-input",{staticClass:"el-input-text-center",staticStyle:{width:"180px"},attrs:{placeholder:e.name,type:"text",maxlength:"25",disabled:t.joinFlag},model:{value:e.selfDefineName,callback:function(i){t.$set(e,"selfDefineName",i)},expression:"item.selfDefineName"}})],1),t.joinFlag?i("td",[t._v(t._s(e.num))]):i("td",[i("el-input",{staticClass:"el-input-text-center",staticStyle:{width:"100px"},attrs:{type:"number",placeholder:"1",disabled:t.joinFlag},model:{value:e.num,callback:function(i){t.$set(e,"num",i)},expression:"item.num"}})],1),t.joinFlag?i("td",[t._v(t._s(e.price))]):i("td",[i("el-input",{staticClass:"el-input-text-center",staticStyle:{width:"160px"},attrs:{type:"number",placeholder:"0",disabled:t.joinFlag},model:{value:e.price,callback:function(i){t.$set(e,"price",i)},expression:"item.price"}})],1),i("td",[t._v(t._s(e.num*e.price))]),t.joinFlag?i("td"):i("td",{staticStyle:{color:"#0096ff",cursor:"pointer"},on:{click:function(e){return t.delOther(n)}}},[t._v("刪除")])])})),i("tr",[i("td",[t._v("總計")]),i("td",{attrs:{colspan:"5"}},[t._v(t._s(t.totalPrices))])])],2),i("div",{staticClass:"listSec_opera"},[t.joinFlag?t._e():i("div",{staticClass:"listSec_add",on:{click:function(e){t.dialogVisible=!t.dialogVisible}}},[i("div",{staticClass:"addIcon"}),i("div",[t._v("添加報價選項")])]),t.joinFlag?t._e():i("div",{staticClass:"listSec_addS",on:{click:t.addNewLine}},[i("div",{staticClass:"addIcon"}),i("div",[t._v("添加自定義選項")])])])]),t.joinFlag?t._e():i("div",{staticClass:"con_form_footer"},[i("div",{staticClass:"foo_startBtn",on:{click:function(e){return t.checkBidding()}}},[i("svg",{attrs:{width:"265px",height:"65px",version:"1.1",xmlns:"http://www.w3.org/2000/svg"}},[i("polygon",{staticStyle:{fill:"none",stroke:"#0096FF","stroke-width":"1"},attrs:{points:"0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"}}),i("rect",{staticStyle:{fill:"#0096FF"},attrs:{x:"2",y:"2",width:"261",height:"61"}}),i("polyline",{staticStyle:{fill:"none",stroke:"#3FB0FF","stroke-width":"2"},attrs:{points:"10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"}})]),i("span",[t._v("確認提交")])])]),t.joinFlag?i("span",{staticStyle:{display:"inline-block","margin-top":"20px",color:"#0096ff",cursor:"pointer"},on:{click:function(e){return t.checkList("uploaded")}}},[t._v("查看標書")]):t._e()])])]),i("el-dialog",{attrs:{title:"零件名稱",visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e}}},[i("el-checkbox",{attrs:{indeterminate:t.isIndeterminate},on:{change:t.handleCheckAllChange},model:{value:t.checkAll,callback:function(e){t.checkAll=e},expression:"checkAll"}},[t._v("全选")]),i("div",{staticStyle:{margin:"15px 0"}}),i("el-checkbox-group",{on:{change:t.handleCheckedComponentsChange},model:{value:t.checkedComponents,callback:function(e){t.checkedComponents=e},expression:"checkedComponents"}},t._l(t.components,(function(e){return i("el-checkbox",{key:e,attrs:{label:e}},[t._v(t._s(e))])})),1),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),i("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addToQuotatList(),t.dialogVisible=!1}}},[t._v("确 定")])],1)],1),i("el-dialog",{attrs:{title:"提交",visible:t.dialogSecVisible},on:{"update:visible":function(e){t.dialogSecVisible=e}}},[i("p",[t._v("\n      您的單套報價清單為\n      "),i("span",{staticStyle:{"font-size":"28px"}},[t._v(t._s(t.totalPrices))]),t._v("，總報價為\n      "),i("span",{staticStyle:{"font-size":"28px"}},[t._v(t._s(t.totalPrices*t.orderInfo.amount))]),t._v(";\n    ")]),i("p",[t._v("\n      標書已生成,\n      "),i("router-link",{staticStyle:{color:"#0096ff"},attrs:{to:""},nativeOn:{click:function(e){return t.checkList(e)}}},[t._v("點擊查看")])],1),i("div",{staticClass:"foo_agreement"},[i("label",{attrs:{for:"agree"}},[i("input",{attrs:{id:"agree",type:"checkBox"},domProps:{checked:t.agree},on:{click:function(e){t.agree=!t.agree}}}),t._v("\n        我已仔细查看標書及相關協議內容，并对我提交的信息负责。\n      ")])]),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.dialogSecVisible=!1}}},[t._v("返 回")]),i("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.isAgree()}}},[t._v("确 定")])],1)])],1):t._e()},r=[function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"con-tag"},[i("ul",[i("li")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"ba_title"},[i("span",[t._v("基本信息")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"ac_title"},[i("span",[t._v("圖檔附件")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"req_title"},[i("span",[t._v("製作要求")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"bid_title"},[i("span",[t._v("競標單位")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"eva_title"},[i("span",[t._v("項目評價")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"listSec_info"},[i("div",[t._v("報價清單（單套）:")])])}],o=(i("8e6e"),i("456d"),i("bd86")),a=(i("c5f6"),i("7f7f"),i("ac4d"),i("8a81"),i("ac6a"),i("e9c9")),s=i("2241"),l=i("fd2d"),c=i("f8b7"),d=i("3f5e"),f=i("3ebc"),u=i("2f62");function _(t,e){var i=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),i.push.apply(i,n)}return i}function v(t){for(var e=1;e<arguments.length;e++){var i=null!=arguments[e]?arguments[e]:{};e%2?_(i,!0).forEach((function(e){Object(o["a"])(t,e,i[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(i)):_(i).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(i,e))}))}return t}var p=[],h={data:function(){return{dialogVisible:!1,dialogSecVisible:!1,checkAll:!1,checkedComponents:[],components:[],isIndeterminate:!1,componentAllOptions:[],orderInfo:"",progress:[],collectFlag:!1,collectBg:i("b4a0"),alertBoxFlag:!1,accessoryData:[],deliver_date:"",bidReason:"",quotatList:[],otherList:[],joinFlag:!1,agree:!1,isCheckList:!1,scrollTopDistance:0}},methods:{consoleLog:function(){},isAgree:function(){return this.isCheckList?this.agree?(this.upBidding(),void(this.dialogSecVisible=!1)):this.$message.error("請確認標書內容無誤,並勾選同意內容！"):this.$message.error("請先查看標書!")},checkList:function(t){if("uploaded"===t){sessionStorage.setItem("biddinfdDoc",JSON.stringify(this.orderInfo));var e=this.$router.resolve({path:"/biddingDoc",query:{loaded:!0,pkid:this.pkid}});window.open(e.href,"_blank")}else{this.isCheckList=!0;var i=[],n=!0,r=!1,o=void 0;try{for(var a,s=this.quotatList[Symbol.iterator]();!(n=(a=s.next()).done);n=!0){var l=a.value,c={part_name:l.selfDefineName||l.name,part_doc_file_pkid:l.id,part_amunt:l.num,part_unit_price:l.price,part_price_sum:Number(l.num)*Number(l.price)};i.push(c)}}catch(g){r=!0,o=g}finally{try{n||null==s.return||s.return()}finally{if(r)throw o}}var d=!0,f=!1,u=void 0;try{for(var _,v=this.otherList[Symbol.iterator]();!(d=(_=v.next()).done);d=!0){var p=_.value,h={part_name:p.selfDefineName||p.name,part_doc_file_pkid:p.id,part_amunt:p.num,part_unit_price:p.price,part_price_sum:Number(p.num)*Number(p.price)};i.push(h)}}catch(g){f=!0,u=g}finally{try{d||null==v.return||v.return()}finally{if(f)throw u}}var m={orderInfo:this.orderInfo,total_price:this.totalPrices,deliver_date:this.deliver_date||new Date(this.orderInfo.deliver_date),descp:this.bidReason,slav_list:i};sessionStorage.setItem("biddinfdDoc",JSON.stringify(m));var y=this.$router.resolve({path:"/biddingDoc",query:{loaded:!1,pkid:this.pkid}});window.open(y.href,"_blank")}},addNewLine:function(){var t=this.$refs.alertBox.clientHeight,e=this.$refs.alertBox.scrollHeight;this.$refs.alertBox.scrollTop=e-t;var i=this.otherList.length?this.otherList[this.otherList.length-1].id+1:1;this.otherList.push({selfDefineName:"",num:1,price:0,id:i,name:"其它"+i})},handleCheckAllChange:function(t){if(t){this.checkedComponents=[];var e=!0,i=!1,n=void 0;try{for(var r,o=p[Symbol.iterator]();!(e=(r=o.next()).done);e=!0){var a=r.value;this.checkedComponents.push(a)}}catch(s){i=!0,n=s}finally{try{e||null==o.return||o.return()}finally{if(i)throw n}}}else this.checkedComponents=[];this.isIndeterminate=!1},handleCheckedComponentsChange:function(t){var e=t.length;this.checkAll=e===this.components.length,this.isIndeterminate=e>0&&e<this.components.length},delQuota:function(t){this.checkedComponents.splice(t,1),this.addToQuotatList(),this.handleCheckedComponentsChange(this.checkedComponents)},delOther:function(t){this.otherList.splice(t,1)},collect:function(){this.collectFlag=!this.collectFlag,this.collectBg=this.collectFlag?i("a495"):i("b4a0")},alertBoxShow:function(){if("recv"==this.send_recv_type){var t=!this.alertBoxFlag;return this.alertBoxFlag=t}this.$alert("請先登錄！")},getInfo:function(){var t=this;Object(c["a"])(this.pkid).then((function(e){if("1"===e.code){if(t.orderInfo=e.t,t.isJoin(),t.joinFlag)return;t.componentAllOptions=[];var i=!0,n=!1,r=void 0;try{for(var o,a=t.orderInfo.file_list[Symbol.iterator]();!(i=(o=a.next()).done);i=!0){var s=o.value,l={id:s.pkid,name:s.file_origin_name,selfDefineName:"",num:s.part_amunt,price:0};t.componentAllOptions.push(l)}}catch(c){n=!0,r=c}finally{try{i||null==a.return||a.return()}finally{if(n)throw r}}t.detachment()}}))},detachment:function(){p=[];var t=!0,e=!1,i=void 0;try{for(var n,r=this.componentAllOptions[Symbol.iterator]();!(t=(n=r.next()).done);t=!0){var o=n.value;p.push(o.name)}}catch(a){e=!0,i=a}finally{try{t||null==r.return||r.return()}finally{if(e)throw i}}this.components=p},addToQuotatList:function(){this.quotatList=[];var t=!0,e=!1,i=void 0;try{for(var n,r=this.checkedComponents[Symbol.iterator]();!(t=(n=r.next()).done);t=!0){var o=n.value,a=!0,s=!1,l=void 0;try{for(var c,d=this.componentAllOptions[Symbol.iterator]();!(a=(c=d.next()).done);a=!0){var f=c.value;o==f.name&&this.quotatList.push(f)}}catch(u){s=!0,l=u}finally{try{a||null==d.return||d.return()}finally{if(s)throw l}}}}catch(u){e=!0,i=u}finally{try{t||null==r.return||r.return()}finally{if(e)throw i}}},downloadOne:function(t,e,i){"recv"==this.send_recv_type||"send"==this.send_recv_type?Object(d["a"])(Object(f["a"])(t,e,i)).then((function(t){var e=new Blob([t],{type:t.type}),n=URL.createObjectURL(e),r=document.createElement("a");r.href=n,r.setAttribute("download",i),r.click()})):this.$alert("請先登錄")},downloadAll:function(){var t=!0,e=!1,i=void 0;try{for(var n,r=this.orderInfo.file_list[Symbol.iterator]();!(t=(n=r.next()).done);t=!0){var o=n.value;this.downloadOne(o.file_save_path,o.file_save_name,o.file_origin_name)}}catch(a){e=!0,i=a}finally{try{t||null==r.return||r.return()}finally{if(e)throw i}}},checkBidding:function(){if(this.joinFlag)return this.$message.warning("您已參與！");if(this.deliver_date){var t=new Date(this.orderInfo.bid_end_date),e=new Date(new Date(this.orderInfo.deliver_date).getTime()+2592e6);if(new Date(this.deliver_date)<t)return this.$message.error("交期不能早於競標結束時間，請修改！");if(new Date(this.deliver_date)>e)return this.$message.error("交期不能延遲超過一個月，請修改！")}var i=!0,n=!1,r=void 0;try{for(var o,a=this.quotatList[Symbol.iterator]();!(i=(o=a.next()).done);i=!0){var s=o.value;if(!s.num)return this.$message.error("零件報價信息不完整");if(s.num<1)return this.$message.error("零件個數不正確");if(!s.price)return this.$message.error("零件報價信息不完整");if(s.price<1)return this.$message.error("零件報價不正確")}}catch(v){n=!0,r=v}finally{try{i||null==a.return||a.return()}finally{if(n)throw r}}var l=!0,c=!1,d=void 0;try{for(var f,u=this.otherList[Symbol.iterator]();!(l=(f=u.next()).done);l=!0){var _=f.value;if(!_.num)return this.$message.error("零件報價信息不完整");if(_.num<1)return this.$message.error("零件個數不正確");if(!_.price)return this.$message.error("零件報價信息不完整");if(_.price<1)return this.$message.error("零件報價不正確")}}catch(v){c=!0,d=v}finally{try{l||null==u.return||u.return()}finally{if(c)throw d}}if(this.totalPrices<1)return this.$message.error("請報價");this.dialogSecVisible=!this.dialogSecVisible,this.isCheckList=!1},upBidding:function(){var t=this,e=[],i=!0,n=!1,r=void 0;try{for(var o,a=this.quotatList[Symbol.iterator]();!(i=(o=a.next()).done);i=!0){var s=o.value,l={part_name:s.selfDefineName||s.name,part_doc_file_pkid:s.id,part_amunt:s.num,part_unit_price:s.price,part_price_sum:Number(s.num)*Number(s.price)};e.push(l)}}catch(y){n=!0,r=y}finally{try{i||null==a.return||a.return()}finally{if(n)throw r}}var d=!0,f=!1,u=void 0;try{for(var _,v=this.otherList[Symbol.iterator]();!(d=(_=v.next()).done);d=!0){var p=_.value,h={part_name:p.selfDefineName||p.name,part_doc_file_pkid:p.id,part_amunt:p.num,part_unit_price:p.price,part_price_sum:Number(p.num)*Number(p.price)};e.push(h)}}catch(y){f=!0,u=y}finally{try{d||null==v.return||v.return()}finally{if(f)throw u}}var m={bill_pkid:this.orderInfo.pkid,total_price:this.totalPrices,deliver_date:this.deliver_date||new Date(this.orderInfo.deliver_date),descp:this.bidReason,f_win_bid:"",slav_list:e};Object(c["q"])(m).then((function(e){"1"===e.code?(t.$message.success("上傳價格完成，請在個人中心查看詳情，祝您競價成功！"),t.$router.push("/")):t.$message.error("出錯啦，請稍後重試")}))},isJoin:function(){var t=this,e=!0,i=!1,n=void 0;try{for(var r,o=this.orderInfo.give_recv_user_list[Symbol.iterator]();!(e=(r=o.next()).done);e=!0){var a=r.value;if(a.pkid==this.my_pkid){var s=0;return Object(c["f"])(this.orderInfo.pkid).then((function(e){var i=!0,n=!1,r=void 0;try{for(var o,a=e.t.slav_list[Symbol.iterator]();!(i=(o=a.next()).done);i=!0){var l=o.value;if(l.part_doc_file){var c={id:l.pkid,name:l.part_doc_file.file_origin_name,selfDefineName:l.part_name,num:l.part_amunt,price:l.part_unit_price};t.quotatList.push(c)}else{s++;var d={id:s,selfDefineName:l.part_name,num:l.part_amunt,price:l.part_unit_price};t.otherList.push(d)}}}catch(f){n=!0,r=f}finally{try{i||null==a.return||a.return()}finally{if(n)throw r}}t.deliver_date=e.t.deliver_date,t.bidReason=e.t.descp})),this.joinFlag=!0}}}catch(l){i=!0,n=l}finally{try{e||null==o.return||o.return()}finally{if(i)throw n}}}},components:{Top:a["a"],Logo:s["a"],Footer:l["a"]},computed:v({},Object(u["c"])({send_recv_type:function(t){return t.userInfo?t.userInfo.send_recv_type:""},my_pkid:function(t){return t.userInfo?t.userInfo.pkid:""}}),{totalPrices:function(){var t=0;for(var e in this.quotatList)t+=this.quotatList[e].num*this.quotatList[e].price;for(var i in this.otherList)t+=this.otherList[i].num*this.otherList[i].price;return t}}),created:function(){this.pkid=this.$router.history.current.query.pkid,this.getInfo()}},m=h,y=(i("3674"),i("2877")),g=Object(y["a"])(m,n,r,!1,null,"995b8d3a",null);e["default"]=g.exports},"8a81":function(t,e,i){"use strict";var n=i("7726"),r=i("69a8"),o=i("9e1e"),a=i("5ca1"),s=i("2aba"),l=i("67ab").KEY,c=i("79e5"),d=i("5537"),f=i("7f20"),u=i("ca5a"),_=i("2b4c"),v=i("37c8"),p=i("3a72"),h=i("d4c0"),m=i("1169"),y=i("cb7c"),g=i("d3f4"),b=i("4bf8"),k=i("6821"),w=i("6a99"),C=i("4630"),S=i("2aeb"),I=i("7bbc"),x=i("11e9"),A=i("2621"),O=i("86cc"),F=i("0d58"),j=x.f,N=O.f,L=I.f,B=n.Symbol,E=n.JSON,D=E&&E.stringify,R="prototype",P=_("_hidden"),Q=_("toPrimitive"),q={}.propertyIsEnumerable,T=d("symbol-registry"),$=d("symbols"),U=d("op-symbols"),V=Object[R],J="function"==typeof B&&!!A.f,Y=n.QObject,Z=!Y||!Y[R]||!Y[R].findChild,G=o&&c((function(){return 7!=S(N({},"a",{get:function(){return N(this,"a",{value:7}).a}})).a}))?function(t,e,i){var n=j(V,e);n&&delete V[e],N(t,e,i),n&&t!==V&&N(V,e,n)}:N,H=function(t){var e=$[t]=S(B[R]);return e._k=t,e},W=J&&"symbol"==typeof B.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof B},K=function(t,e,i){return t===V&&K(U,e,i),y(t),e=w(e,!0),y(i),r($,e)?(i.enumerable?(r(t,P)&&t[P][e]&&(t[P][e]=!1),i=S(i,{enumerable:C(0,!1)})):(r(t,P)||N(t,P,C(1,{})),t[P][e]=!0),G(t,e,i)):N(t,e,i)},z=function(t,e){y(t);var i,n=h(e=k(e)),r=0,o=n.length;while(o>r)K(t,i=n[r++],e[i]);return t},M=function(t,e){return void 0===e?S(t):z(S(t),e)},X=function(t){var e=q.call(this,t=w(t,!0));return!(this===V&&r($,t)&&!r(U,t))&&(!(e||!r(this,t)||!r($,t)||r(this,P)&&this[P][t])||e)},tt=function(t,e){if(t=k(t),e=w(e,!0),t!==V||!r($,e)||r(U,e)){var i=j(t,e);return!i||!r($,e)||r(t,P)&&t[P][e]||(i.enumerable=!0),i}},et=function(t){var e,i=L(k(t)),n=[],o=0;while(i.length>o)r($,e=i[o++])||e==P||e==l||n.push(e);return n},it=function(t){var e,i=t===V,n=L(i?U:k(t)),o=[],a=0;while(n.length>a)!r($,e=n[a++])||i&&!r(V,e)||o.push($[e]);return o};J||(B=function(){if(this instanceof B)throw TypeError("Symbol is not a constructor!");var t=u(arguments.length>0?arguments[0]:void 0),e=function(i){this===V&&e.call(U,i),r(this,P)&&r(this[P],t)&&(this[P][t]=!1),G(this,t,C(1,i))};return o&&Z&&G(V,t,{configurable:!0,set:e}),H(t)},s(B[R],"toString",(function(){return this._k})),x.f=tt,O.f=K,i("9093").f=I.f=et,i("52a7").f=X,A.f=it,o&&!i("2d00")&&s(V,"propertyIsEnumerable",X,!0),v.f=function(t){return H(_(t))}),a(a.G+a.W+a.F*!J,{Symbol:B});for(var nt="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),rt=0;nt.length>rt;)_(nt[rt++]);for(var ot=F(_.store),at=0;ot.length>at;)p(ot[at++]);a(a.S+a.F*!J,"Symbol",{for:function(t){return r(T,t+="")?T[t]:T[t]=B(t)},keyFor:function(t){if(!W(t))throw TypeError(t+" is not a symbol!");for(var e in T)if(T[e]===t)return e},useSetter:function(){Z=!0},useSimple:function(){Z=!1}}),a(a.S+a.F*!J,"Object",{create:M,defineProperty:K,defineProperties:z,getOwnPropertyDescriptor:tt,getOwnPropertyNames:et,getOwnPropertySymbols:it});var st=c((function(){A.f(1)}));a(a.S+a.F*st,"Object",{getOwnPropertySymbols:function(t){return A.f(b(t))}}),E&&a(a.S+a.F*(!J||c((function(){var t=B();return"[null]"!=D([t])||"{}"!=D({a:t})||"{}"!=D(Object(t))}))),"JSON",{stringify:function(t){var e,i,n=[t],r=1;while(arguments.length>r)n.push(arguments[r++]);if(i=e=n[1],(g(e)||void 0!==t)&&!W(t))return m(e)||(e=function(t,e){if("function"==typeof i&&(e=i.call(this,t,e)),!W(e))return e}),n[1]=e,D.apply(E,n)}}),B[R][Q]||i("32e9")(B[R],Q,B[R].valueOf),f(B,"Symbol"),f(Math,"Math",!0),f(n.JSON,"JSON",!0)},a495:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAASCAYAAABWzo5XAAABZklEQVQ4T6XUPY+MURQA4OeM7JKIrETIKlaiQSHZSkUixP4GhUolplghK1EobDQ+QkLsxm6j0vgLsqGhl21oTClGYxISZGaO3BnDjgzDvqd677nnfe5H7r1hY9zPrbp2mtJyNr4M9Q0aj3Kblik1H83H10E6eh/LeRD3hBPSJLp4LT3F4x/FZ4Q5HEJN+CY9wwX1eBOWclp4hT0jZzAumd6bNBuW8xYuj6sf03+7QOs4XAkK62Vpn4TtlaD0uUAdoVYJoluW1sTuitCHAq3hZCUorRXoEu5UgrgYHuQuNQ3s2CTWwv7Byb6K65uErqjHzT60khM6XuDIf2IvNR13Ldp9qMRSzogeNvOPWMMWR52Ld6X+F1Raq3lAu3dR9/0VSw3hlHq8Hb79G/9ayb06nuDYH7Dnwmnno5y/nzE8o0G6v2eLWMBEL53awg1Ni2VPfh9kNDSoepizuu723qe2BfNRnpuR8R1wwmXfZuF27gAAAABJRU5ErkJggg=="},ac4d:function(t,e,i){i("3a72")("asyncIterator")},b4a0:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAASCAYAAABWzo5XAAACvklEQVQ4T5WTS0hUYRTH/+feO47vwggtHyFBtQgEwUWmZFJEuYlgkNBkZhazKEQSbWbU4Io6c30F4SIKfJRpkkGB0AMTzR67pHBTC5UgK0ysfOWM986Jb9QYQ0PP6nC+//c7j+98hBA7XVxsToxJ2Kl/Wf7V0aEuhZ6t+VarGq7sNe2YnPv280lLi28tTsKxX6k5CJKvg+g4gDAAAQY+gNHPhC6hIUYBCCcJOARAAuAH8yDYKGlruPqRrOUNCZJsvAMQD0BkmAEQB8AczMYYZgIRkL2afZ2GGVMckNPI7vI2ACgH06BvcelsV4s6m6OqSsqikq6Qkh8g7uNAQJZIypNY6hmL/D0ypKp6QbEaa44MfwRi0UUj2V2eUYAOGwZn3W6seL3RXDaL2ZxaJhGLO6Nkc3rniRC1rCvRnU3lC9sBXShrjDIp+jwzFkRrhhje3NiI0tvbK/wtm8VikWP2p+vicQRoCsBuXTaS7tRVTW6ZAqCosjZRMeTPAL6L1gaIkMvElnZvxYPtgGwubz4BPWAeIKvTUyoRNTPwuF1z520HZHdqT0F8CsSXqcjt2aUwTTAjWgLltNa7hrcCs7m8uQQ8BzC7BKQGN9vm1KqIuIaZx3xEGd2a+8f/YCvJ8RagfQS4WjV3fRDkcNw06XEzYh8yGHgxPeM703dLXdwIZrmoRkfHmp8RkAnGm/EI3zGxoEFQsKrS2mQKkwUsmcEvl5b95+41q9OhsPMldfEREdJDAEfANOGXjaN3PZVfg38xVFjk9hyQmfoJSAH4EzhQ2FZf9UporG5PtsTUDSAJzBMgOtGmucfX/f5QWGFF3Z6wgHQfQJZYNDDfAJEBxiUQZAaGdN2X39mkiv37a+sqWos6HA7TclxqNQFlAEyrcZ2JtQmzv1rM5N/5bQhaE9mdWhqIr61URmVt9a73m73mH/1VFgn+1YL7AAAAAElFTkSuQmCC"},d4c0:function(t,e,i){var n=i("0d58"),r=i("2621"),o=i("52a7");t.exports=function(t){var e=n(t),i=r.f;if(i){var a,s=i(t),l=o.f,c=0;while(s.length>c)l.call(t,a=s[c++])&&e.push(a)}return e}}}]);
//# sourceMappingURL=chunk-9a53b9c6.32a2a1f2.js.map