(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-476df618"],{"090d":function(t,e,n){},"0bb7":function(t,e,n){"use strict";var i=n("814e"),s=n.n(i);s.a},1598:function(t,e,n){},"1f6a":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"svg-btn"},[n("svg",{attrs:{width:"265px",height:"65px",version:"1.1",xmlns:"http://www.w3.org/2000/svg"}},[n("polygon",{staticStyle:{fill:"none",stroke:"#0096FF","stroke-width":"1"},style:{stroke:t.outColor},attrs:{points:"0,5 5,0 260,0 265,5 265,60 260,65 5,65 0,60 0,5"}}),n("rect",{style:{fill:t.fillColor},attrs:{x:"2",y:"2",width:"261",height:"61"}}),n("polyline",{staticStyle:{fill:"none",stroke:"#3FB0FF","stroke-width":"2"},style:{stroke:t.inColor},attrs:{points:"10,13 10,6 6,6 6,10 13,10 13,6 252,6 252,10 259,10 259,6 255,6 255,13 259,13 259,52 255,52 255,59 259,59 259,55 252,55 252,59 13,59 13,55 6,55 6,59 10,59 10,52 6,52 6,14 11,14"}})])])},s=[],a={props:["inColor","outColor","fillColor"]},r=a,o=(n("e9eb"),n("2877")),c=Object(o["a"])(r,i,s,!1,null,"36ea827c",null);e["a"]=c.exports},"386b":function(t,e,n){var i=n("5ca1"),s=n("79e5"),a=n("be13"),r=/"/g,o=function(t,e,n,i){var s=String(a(t)),o="<"+e;return""!==n&&(o+=" "+n+'="'+String(i).replace(r,"&quot;")+'"'),o+">"+s+"</"+e+">"};t.exports=function(t,e){var n={};n[t]=e(o),i(i.P+i.F*s((function(){var e=""[t]('"');return e!==e.toLowerCase()||e.split('"').length>3})),"String",n)}},"3f9d":function(t,e,n){"use strict";var i=n("090d"),s=n.n(i);s.a},6689:function(t,e,n){},"673e":function(t,e,n){"use strict";n("386b")("sub",(function(t){return function(){return t(this,"sub","","")}}))},6984:function(t,e,n){},"7e1f":function(t,e,n){"use strict";var i=n("1598"),s=n.n(i);s.a},"814e":function(t,e,n){},9727:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"manage"},[n("div",{staticClass:"man-info"},[n("m-info")],1),n("div",{staticClass:"man-list"},[t._m(0),n("div",{staticClass:"flit z-flex z-justify-content-between"},t._l(t.flitList,(function(e,i){return n("div",{key:e.id,staticClass:"selet-admin-manage z-wid100",class:e.active?"active":"",on:{click:function(e){return t.handleFlitTypeClick(i)}}},[t._v("\n        "+t._s(e.scope)+":"+t._s(e.checkedItem.txt)+"\n        "),n("div",{staticClass:"flit-item-wrapper"},t._l(e.sub,(function(i,s){return n("p",{key:i.id,class:i.id==e.checkedItem.id?"active":"",on:{click:function(e){return t.handleFlitItemClick(s)}}},[t._v("\n            "+t._s(i.txt)+"\n          ")])})),0)])})),0),n("div",{staticClass:"table"},[n("m-table",{attrs:{tableList:t.tableList},on:{choosedData:t.getChoosedData,delSelection:t.getDelSelection}})],1),n("div",[n("details-alert",{attrs:{choosedData:t.choosedData},on:{choosedDataEmpty:t.setChoosedData}})],1),n("div",{staticClass:"del-btn",on:{click:t.handleDelBtnClick}},[n("svg-btn",{staticClass:"svgBtn",attrs:{inColor:t.delBtn.inColor,outColor:t.delBtn.outColor,fillColor:t.delBtn.fillColor}}),n("span",[t._v("刪除")])],1),n("div",{staticClass:"paging",staticStyle:{backgroundColor:"#D3DFE7"}},[n("Paging",{attrs:{total:t.total,"current-page":t.currentPage,"page-size":t.pageSize},on:{getCurrentPage:t.getListDate}})],1)])])},s=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"z-flex z-align-item-center title"},[n("span",[t._v("賬號管理")])])}],a=(n("673e"),n("c5f6"),n("ac4d"),n("8a81"),n("ac6a"),function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"z-flex info"},t._l(t.statistics,(function(e){return n("div",{key:e.id},[n("div",{style:{color:"wait_num"==e.ename&&e.num>0?"#ff0000":""}},[t._v("\n      "+t._s(e.num)+"\n    ")]),n("span",[t._v(t._s(e.name))])])})),0)}),r=[],o=n("c24f"),c={data:function(){return{statistics:{}}},created:function(){this.getStatistics()},methods:{getStatistics:function(){var t=this,e={};Object(o["f"])(e).then((function(e){if("1"===e.code){var n=e.t;t.statistics=[{ename:"total_num",name:"註冊賬號總數",num:n.total_num,id:"1"},{ename:"send_num",name:"發單方賬號總數",num:n.send_num,id:"2"},{ename:"recv_num",name:"接單方賬號總數",num:n.recv_num,id:"3"},{ename:"active_num",name:"活躍賬號總數",num:n.active_num,id:"4"},{ename:"inactive_num",name:"不活躍賬號總數",num:n.inactive_num,id:"5"},{ename:"wait_num",name:"待審核賬號總數",num:n.wait_num,id:"6"}]}else t.$message.error(e.msg)}))}}},l=c,d=(n("7e1f"),n("2877")),u=Object(d["a"])(l,a,r,!1,null,"2d5575ea",null),_=u.exports,v=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"table"},[n("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:t.tableList,"tooltip-effect":"dark","row-class-name":t.tableRowClassName},on:{"selection-change":t.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection","min-width":"55"}}),n("el-table-column",{attrs:{label:"用戶名","min-width":"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.username))]}}])}),n("el-table-column",{attrs:{prop:"fctry_zone",label:"所屬廠區","min-width":"80"}}),n("el-table-column",{attrs:{prop:"dept_name",label:"單位名稱","min-width":"260"}}),n("el-table-column",{attrs:{prop:"tel",label:"固定電話","min-width":"100"}}),n("el-table-column",{attrs:{prop:"email",label:"Email","min-width":"340"}}),n("el-table-column",{attrs:{prop:"create_date",label:"申請時間","min-width":"180"}}),n("el-table-column",{attrs:{label:"活躍",type:"index","min-width":"48"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{style:{color:"Y"===e.row.f_active?"":"red"}},[t._v("\n          "+t._s("Y"===e.row.f_active?"是":"否")+"\n        ")])]}}])}),n("el-table-column",{attrs:{label:"操作","min-width":"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("div",{on:{click:function(n){return t.showAlert(e.row)}}},[t._v("\n          詳細信息\n        ")])]}}])})],1)],1)},f=[],m={data:function(){return{tableData:[],multipleSelection:[],styleClass:""}},props:{tableList:{type:Array,required:!0}},methods:{showAlert:function(t){this.$emit("choosedData",t)},tableRowClassName:function(t){var e=t.row;this.multipleSelection.map((function(t){return t.pkid===e.pkid?"del-row":""}))},handleSelectionChange:function(t){this.multipleSelection=t,this.$emit("delSelection",this.multipleSelection)}}},h=m,p=(n("bc45"),Object(d["a"])(h,v,f,!1,null,"63c725dc",null)),b=p.exports,g=function(){var t=this,e=t.$createElement,n=t._self._c||e;return t.info?n("div",{staticClass:"details-alert"},[n("el-dialog",{attrs:{visible:t.dialogFormVisible,"before-close":t.handleClose},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[n("div",{staticClass:"title"},[t._v("\n      新用戶 "),n("span",[t._v(" "+t._s(t.info.dept_name)+" ")]),t._v(" 詳細信息\n      "),n("p",[t._v("注册时间："+t._s(t.info.create_date))])]),"send"==t.info.send_recv_type&&Boolean(t.info.send_user_statistics)?n("div",{staticClass:"z-flex z-justify-content-around account-data"},[n("div",[n("span",[t._v("發單量")]),n("div",[t._v(t._s(t.info.send_user_statistics.send_amount||0))])]),n("div",[n("span",[t._v("發單金額")]),n("div",[t._v(t._s(t.info.send_user_statistics.send_money||0))])]),n("div",[n("span",[t._v("選標金額")]),n("div",[t._v(t._s(t.info.send_user_statistics.pick_money||0))])]),n("div",[n("span",[t._v("棄標率")]),n("div",[t._v("\n          "+t._s((100*t.info.send_user_statistics.abandon_rate).toFixed(0)+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("選標率")]),n("div",[t._v("\n          "+t._s((100*t.info.send_user_statistics.pick_rate).toFixed(0)+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("流標率")]),n("div",[t._v("\n          "+t._s((100*t.info.send_user_statistics.flow_rate).toFixed(0)+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("客戶評分")]),n("div",[t._v(t._s(t.info.send_user_statistics.user_score||0))])]),n("div",[n("span",[t._v("信譽分")]),n("div",[t._v(t._s(t.info.send_user_statistics.reputation||0))])])]):"recv"==t.info.send_recv_type&&Boolean(t.info.recv_user_statistics)?n("div",{staticClass:"z-flex z-justify-content-around account-data"},[n("div",[n("span",[t._v("接單量")]),n("div",[t._v(t._s(t.info.recv_user_statistics.recv_amount||0))])]),n("div",[n("span",[t._v("報價金額")]),n("div",[t._v(t._s(t.info.recv_user_statistics.offer_money||0))])]),n("div",[n("span",[t._v("中標金額")]),n("div",[t._v(t._s(t.info.recv_user_statistics.win_bid_money||0))])]),n("div",[n("span",[t._v("競標成功率")]),n("div",[t._v("\n          "+t._s((100*t.info.recv_user_statistics.win_bid_rate).toFixed(0)+"%"||!1)+"\n        ")])]),n("div",[n("span",[t._v("客戶評分")]),n("div",[t._v(t._s(t.info.recv_user_statistics.user_score||0))])])]):t._e(),n("div",{staticClass:"details z-flex z-justify-content-between"},[n("table",[n("tr",[n("th",{attrs:{colspan:"3"}},[t._v("\n            公司信息\n          ")])]),n("tr",[n("td",[t._v("賬號屬性")]),n("td",[t._v(":")]),n("td",[t._v("\n            集團內部賬號"),n("span",{staticClass:"z-cBlue"},[t._v("【"+t._s("send"==t.info.send_recv_type?"發佈方":"接單方")+"】\n            ")])])]),n("tr",[n("td",[t._v("賬號名稱")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.username))])]),n("tr",[n("td",[t._v("所屬廠區")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.fctry_zone))])]),n("tr",[n("td",[t._v("次集團")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.secn_cmpy))])]),n("tr",[n("td",[t._v("事業群")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.entrps_group))])]),n("tr",[n("td",[t._v("產品處")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.pd_office))])]),n("tr",[n("td",[t._v("交易法人")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.legal_person))])]),n("tr",[n("td",[t._v("費用代碼")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.cost_code))])])]),n("table",[n("tr",[n("th",{attrs:{colspan:"3"}},[t._v("\n            業務信息\n          ")])]),n("tr",[n("td",[t._v("業務窗口")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.busis_mngr))])]),n("tr",[n("td",[t._v("固定電話")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.tel))])]),n("tr",[n("td",[t._v("移動電話")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.phone))])]),n("tr",[n("td",[t._v("Email")]),n("td",[t._v(":")]),n("td",[t._v(t._s(t.info.email))])]),n("tr",{directives:[{name:"show",rawName:"v-show",value:"recv"==t.info.send_recv_type,expression:"info.send_recv_type == 'recv'"}]},[n("td",[t._v("加工範圍")]),n("td",[t._v(":")]),n("td",t._l(t.info.recv_range_list,(function(e){return n("span",{key:e.pkid},[t._v(t._s(e.name)+"、")])})),0)]),n("tr",[n("td",[t._v("簡介")]),n("td",[t._v(":")]),n("td",[n("p",[t._v("\n              "+t._s(t.info.summary)+"\n            ")])])])])])])],1):t._e()},y=[],k={data:function(){return{dialogFormVisible:!1,info:{}}},props:{choosedData:{type:Object,required:!0}},watch:{choosedData:function(t){var e=this;"{}"!=JSON.stringify(t)&&Object(o["h"])(t.pkid).then((function(t){"1"===t.code?(e.info=t.t,e.dialogFormVisible=!0):e.$message.error(t.msg)}))}},methods:{handleClose:function(t){this.$emit("choosedDataEmpty"),t()}}},w=k,C=(n("0bb7"),Object(d["a"])(w,g,y,!1,null,"24bdaecc",null)),S=C.exports,x=n("1f6a"),L=n("def5"),D=n("c0e7"),F={data:function(){return{choosedData:{},delBtn:{inColor:"#5B6573",outColor:"#485463",fillColor:"#485463"},flitList:[],total:null,currentPage:1,pageSize:8,tableList:[],delSelection:[]}},created:function(){this.getFlitList()},methods:{deleteAccuont:function(t){var e=this,n={user_pkid:t.pkid};Object(o["b"])(n).then((function(n){"1"===n.code?(e.getListDate(),e.$message.success("刪除成功: "+t.username)):(e.$message.error("刪除失敗: "+t.username),e.$message.error(n.msg))}))},handleDelBtnClick:function(){var t=this;if(0==this.delSelection.length)return this.$alert("當前沒有選中賬號","提示",{confirmButtonText:"確定",callback:function(){}});this.$confirm("此操作將永遠刪除選中賬號，是否繼續？","警告",{type:"error"}).then((function(){var e=!0,n=!1,i=void 0;try{for(var s,a=t.delSelection[Symbol.iterator]();!(e=(s=a.next()).done);e=!0){var r=s.value;t.deleteAccuont(r)}}catch(o){n=!0,i=o}finally{try{e||null==a.return||a.return()}finally{if(n)throw i}}})).catch((function(){t.$message({type:"info",message:"已取消"})}))},getDelSelection:function(t){this.delSelection=t},getFlitList:function(){var t=this,e={};Object(D["d"])(e).then((function(e){if("1"===e.code){t.flitList=e.t;var n=Number(sessionStorage.getItem("adminManCurrentPage"));n=n||1,t.getListDate(n)}else t.$message.error(e.msg)}))},getListDate:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.currentPage=Number(e),sessionStorage.setItem("adminManCurrentPage",this.currentPage);var n="",i="",s="",a="",r=!0,c=!1,l=void 0;try{for(var d,u=this.flitList[Symbol.iterator]();!(r=(d=u.next()).done);r=!0){var _=d.value;switch(_.val){case"factory":i=_.checkedItem.txt;break;case"business":s=_.checkedItem.txt;break;case"product":a=_.checkedItem.txt;break;case"type":n=_.checkedItem.txt;break}}}catch(f){c=!0,l=f}finally{try{r||null==u.return||u.return()}finally{if(c)throw l}}var v={send_recv_type:n,fctry_zone:i,secn_cmpy:"全部",entrps_group:s,pd_office:a,pageIndex:this.currentPage,pageSize:this.pageSize};Object(o["g"])(v).then((function(e){"1"===e.code?(t.tableList=e.t.list,t.total=e.t.row_total):t.$message.error(e.msg)}))},handleFlitItemClick:function(t){for(var e in this.flitList)if(1==this.flitList[e].active)return this.flitList[e].checkedItem=this.flitList[e].sub[t],void this.getListDate()},handleFlitTypeClick:function(t){if(1!=this.flitList[t].active){for(var e in this.flitList)this.flitList[e].active=!1;this.flitList[t].active=!0}else this.flitList[t].active=!1},setChoosedData:function(){this.choosedData={}},getChoosedData:function(t){this.choosedData=t},toggleFlit:function(t){var e=!0,n=!1,i=void 0;try{for(var s,a=this.flitList[Symbol.iterator]();!(e=(s=a.next()).done);e=!0){var r=s.value;r.active=!1}}catch(o){n=!0,i=o}finally{try{e||null==a.return||a.return()}finally{if(n)throw i}}switch(t){case"all":this.flitList[0].active=!0;break;case"recv":this.flitList[1].active=!0;break;case"send":this.flitList[2].active=!0;break}}},watch:{},components:{MInfo:_,MTable:b,DetailsAlert:S,SvgBtn:x["a"],Paging:L["a"]}},z=F,$=(n("3f9d"),Object(d["a"])(z,i,s,!1,null,"2a61daa5",null));e["default"]=$.exports},bc45:function(t,e,n){"use strict";var i=n("6689"),s=n.n(i);s.a},c24f:function(t,e,n){"use strict";n.d(e,"d",(function(){return s})),n.d(e,"e",(function(){return a})),n.d(e,"h",(function(){return r})),n.d(e,"j",(function(){return o})),n.d(e,"c",(function(){return c})),n.d(e,"a",(function(){return l})),n.d(e,"i",(function(){return d})),n.d(e,"g",(function(){return u})),n.d(e,"b",(function(){return _})),n.d(e,"f",(function(){return v}));var i=n("365c");function s(t){var e="/bidding/user/login";return i["a"].post(e,t)}function a(t){t={username:t};var e="/bidding/user/login_user_info";return i["a"].postWithToken(e,t)}function r(t){t={pkid:t};var e="/bidding/user/query_user_info_by_pkid";return i["a"].post(e,t)}function o(t){var e="/bidding/user/update_user_info";return i["a"].postWithToken(e,t)}function c(t){var e="/bidding/user/generate_code";return i["a"].post(e,t)}function l(t){var e="/bidding/user/check_code";return i["a"].post(e,t)}function d(t){var e="/bidding/user/update_password";return i["a"].post(e,t)}function u(t){var e="/bidding/user/queryUserList";return i["a"].postWithToken(e,t)}function _(t){var e="/bidding/user/deleteUser";return i["a"].postWithToken(e,t)}function v(t){var e="/bidding/user/queryAllUsersStatistics";return i["a"].postWithToken(e,t)}},e9eb:function(t,e,n){"use strict";var i=n("6984"),s=n.n(i);s.a}}]);
//# sourceMappingURL=chunk-476df618.87afedec.js.map