(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1b182c2f"],{"10e5":function(e,t,r){},1302:function(e,t,r){e.exports=r.p+"img/portrait.017effe5.png"},"2d89":function(e,t,r){"use strict";var o=r("fa13"),n=r.n(o);n.a},"3ebc":function(e,t,r){"use strict";r.d(t,"b",(function(){return n})),r.d(t,"a",(function(){return i}));var o=r("fcc2");function n(e,t,r){var n=encodeURIComponent(e||o["a"].userInfo.user_pic_file.file_save_path),i=encodeURIComponent(t||o["a"].userInfo.user_pic_file.file_save_name),s=encodeURIComponent(r||o["a"].userInfo.user_pic_file.file_origin_name),a="/bid/api/bidding/pic_show/load",f=a+"?file_save_path="+n+"&file_save_name="+i+"&file_origin_name="+s;return f}function i(e,t,r){var o=encodeURIComponent(e),n=encodeURIComponent(t),i=encodeURIComponent(r),s="/bidding/pic_show/download",a=s+"?file_save_path="+o+"&file_save_name="+n+"&file_origin_name="+i;return a}},"3f5e":function(e,t,r){"use strict";r.d(t,"d",(function(){return n})),r.d(t,"b",(function(){return i})),r.d(t,"c",(function(){return s})),r.d(t,"a",(function(){return a}));var o=r("365c");function n(e){var t="/bidding/ftp_file/upload",r="multipart/form-data";return o["a"].post(t,e,r)}function i(e){var t="/bidding/ftp_file/delete";return o["a"].post(t,e)}function s(e){var t="/bidding/bill/delete_part_doc_file_by_pkid";return o["a"].post(t,e)}function a(e){return o["a"].getWithToken(e)}},6950:function(e,t,r){"use strict";var o=r("10e5"),n=r.n(o);n.a},9165:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"information"},[r("Top"),r("Logo"),r("section",{attrs:{id:"content"}},[r("div",{staticClass:"main type-area"},[r("SilderBar",{attrs:{"choose-item":e.silderBarItem}}),r("div",{staticClass:"mainPart"},[r("div",{staticClass:"main_title"},[e._v("個人資料")]),r("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[r("el-form-item",{staticClass:"properties",attrs:{label:"賬號屬性:"}},[r("span",[e._v(e._s(e.form.properties))])]),r("el-form-item",{staticClass:"portrait",attrs:{label:"LOGO頭像:"}},[r("el-image",{staticStyle:{width:"60px",height:"60px"},attrs:{src:e.form.portraitUrl,fit:"cover"}}),r("input",{ref:"fileInp",staticClass:"dis_none",attrs:{type:"file"},on:{change:e.getFile}}),r("span",{on:{click:e.upImg}},[e._v("更換")])],1),r("el-form-item",{staticClass:"department",attrs:{label:"所屬地區:"}},[r("el-select",{nativeOn:{click:function(t){return e.query_department("factory")}},model:{value:e.form.fctry_zone,callback:function(t){e.$set(e.form,"fctry_zone",t)},expression:"form.fctry_zone"}},e._l(e.form.fctry_zone_list,(function(e){return r("el-option",{key:e.pkid,attrs:{label:e.name,value:e.name}})})),1)],1),r("el-form-item",{staticClass:"department",attrs:{label:"所屬單位:"}},[r("el-autocomplete",{staticClass:"inline-input",staticStyle:{"margin-right":"10px"},attrs:{"fetch-suggestions":e.querySearchGroup},on:{select:function(t){return e.query_department("PD_OFFICE")}},nativeOn:{change:function(t){return e.query_department("PD_OFFICE")}},model:{value:e.form.entrps_group,callback:function(t){e.$set(e.form,"entrps_group","string"===typeof t?t.trim():t)},expression:"form.entrps_group"}}),r("el-autocomplete",{staticClass:"inline-input",attrs:{"fetch-suggestions":e.querySearchOffice},model:{value:e.form.pd_office,callback:function(t){e.$set(e.form,"pd_office","string"===typeof t?t.trim():t)},expression:"form.pd_office"}})],1),r("el-form-item",{attrs:{label:"賬號名稱:",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),r("el-form-item",{attrs:{label:"單位代碼:",prop:"departCode"}},[r("el-input",{model:{value:e.form.departCode,callback:function(t){e.$set(e.form,"departCode","string"===typeof t?t.trim():t)},expression:"form.departCode"}})],1),r("el-form-item",{attrs:{label:"單位名稱:",prop:"departName"}},[r("el-input",{model:{value:e.form.departName,callback:function(t){e.$set(e.form,"departName",t)},expression:"form.departName"}})],1),r("el-form-item",{attrs:{label:"交易法人:"}},[r("span",[e._v(e._s(e.form.corporate))])]),r("el-form-item",{attrs:{label:"加工範圍:"}},[r("el-select",{attrs:{multiple:""},nativeOn:{click:function(t){return e.getRange(t)}},model:{value:e.form.processRange,callback:function(t){e.$set(e.form,"processRange",t)},expression:"form.processRange"}},e._l(e.rangeOptions,(function(e){return r("el-option",{key:e.pkid,attrs:{label:e.name,value:e.name}})})),1)],1),r("el-form-item",{staticClass:"costCode",attrs:{label:"費用代碼:",prop:"costCode"}},[r("span",[e._v(e._s(e.form.costCode))]),r("el-button",{attrs:{type:"text"},on:{click:function(t){return e.open("costCode")}}},[e._v(" 變更")])],1),r("el-form-item",{attrs:{label:"業務經理:",prop:"manager"}},[r("el-input",{model:{value:e.form.manager,callback:function(t){e.$set(e.form,"manager","string"===typeof t?t.trim():t)},expression:"form.manager"}})],1),r("el-form-item",{staticClass:"tel",attrs:{label:"固定電話:",prop:"telNum"}},[r("el-input",{model:{value:e.form.telNum,callback:function(t){e.$set(e.form,"telNum","string"===typeof t?t.trim():t)},expression:"form.telNum"}})],1),r("el-form-item",{staticClass:"tel",attrs:{label:"移動電話:",prop:"mobileNum"}},[r("el-input",{model:{value:e.form.mobileNum,callback:function(t){e.$set(e.form,"mobileNum","string"===typeof t?t.trim():t)},expression:"form.mobileNum"}})],1),r("el-form-item",{staticClass:"email",attrs:{label:"公司郵箱:",prop:"email"}},[r("el-input",{model:{value:e.form.email,callback:function(t){e.$set(e.form,"email",t)},expression:"form.email"}})],1),r("el-form-item",{staticClass:"intro",attrs:{label:"簡介:",prop:"intro"}},[r("el-input",{attrs:{type:"textarea",autosize:""},model:{value:e.form.intro,callback:function(t){e.$set(e.form,"intro",t)},expression:"form.intro"}})],1),r("el-form-item",{attrs:{label:" "}},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.save()}}},[e._v("保存")])],1)],1)],1)],1)]),r("Footer")],1)},n=[],i=(r("8e6e"),r("456d"),r("bd86")),s=(r("ac4d"),r("8a81"),r("ac6a"),r("7f7f"),r("e9c9")),a=r("2241"),f=r("fd2d"),c=r("f9bd"),m=r("3f5e"),l=r("c24f"),u=r("c0e7"),p=r("3ebc"),d=r("2f62");function g(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,o)}return r}function h(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?g(r,!0).forEach((function(t){Object(i["a"])(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):g(r).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}var _={data:function(){var e=this,t=function(t,r,o){""===r?o(new Error("请輸入名稱")):r.length<3||r.length>10?o(new Error("长度在 3 到 10 个字符")):e.userInfo.username==e.form.name?o():e.ck_user(e.form.name,o)};return{silderBarItem:"information",form:{properties:"集團內部賬號",portraitUrl:r("1302"),name:"",fctry_zone:"",secn_cmpy:"",entrps_group:"",pd_office:"",fctry_zone_list:[],secn_cmpy_list:[],entrps_group_list:[],pd_office_list:[],departName:"",departCode:"",corporate:"",costCode:"",processRange:[],bankName:"",bankAccount:"",manager:"",telNum:"",mobileNum:"",email:"",intro:"",in_out_type:""},rules:{name:[{required:!0,validator:t,trigger:"blur"}],departCode:[{required:!0,message:"单位代碼不能为空",trigger:"blur"}],departName:[{required:!0,message:"未查詢到单位名称，請檢查",trigger:"blur"}],costCode:[{required:!0,message:"费用代码不能为空",trigger:"blur"}],manager:[{required:!0,message:"业务经理不能为空",trigger:"blur"}],telNum:[{required:!0,message:"固定電話不能为空",trigger:"blur"}],mobileNum:[{required:!0,message:"移動電話不能为空",trigger:"blur"}],email:[{required:!0,message:"email不能为空",trigger:"blur"},{type:"email",message:"请输入正确的邮箱地址",trigger:["blur","change"]}],intro:[{max:100,message:"最長100個字符",trigger:"blur"}]},rangeOptions:[],dialogFormVisible:!1,alertForm:{bankName:"",bankAccount:""},user_pic_file:null}},mounted:function(){this.initData(),this.query_department("ENTRPS_GROUP")},methods:{queryDeptName:function(){var e=this;if(!this.form.fctry_zone)return this.$message.error("請先選擇廠區！");this.form.departCode=this.form.departCode.toUpperCase(),Object(u["j"])(this.form.departCode,this.form.fctry_zone).then((function(t){"1"===t.code?e.form.departName=t.t.dept_name:(e.form.departName="",e.$message.error("查詢失敗，請檢查單位代碼是否輸入正確！"))}))},getRange:function(){var e=this;Object(u["l"])().then((function(t){"1"===t.code?e.rangeOptions=t.t:e.$message.error(t.msg)}))},query_department:function(e){var t=this,r="";switch(e){case"factory":Object(u["k"])().then((function(e){"1"===e.code?t.form.fctry_zone_list=e.t:t.$message.error(e.msg)}));break;case"SECN_CMPY":Object(u["g"])().then((function(e){"1"===e.code?(t.form.secn_cmpy_list=e.t,t.form.entrps_group="",t.form.pd_office=""):t.$message.error(e.msg)}));break;case"ENTRPS_GROUP":r="Foxconn",Object(u["e"])(r).then((function(e){"1"===e.code?(t.form.entrps_group_list=e.t,t.query_department("PD_OFFICE")):t.$message.error(e.msg)}));break;case"PD_OFFICE":var o=!0,n=!1,i=void 0;try{for(var s,a=this.form.entrps_group_list[Symbol.iterator]();!(o=(s=a.next()).done);o=!0){var f=s.value;this.form.entrps_group==f.name&&(r=f.pkid)}}catch(c){n=!0,i=c}finally{try{o||null==a.return||a.return()}finally{if(n)throw i}}Object(u["f"])(r).then((function(e){"1"===e.code?t.form.pd_office_list=e.t:t.$message.error(e.msg)}));break}},querySearchGroup:function(e,t){var r=[];if(this.form.entrps_group_list.length>0){var o=!0,n=!1,i=void 0;try{for(var s,a=this.form.entrps_group_list[Symbol.iterator]();!(o=(s=a.next()).done);o=!0){var f=s.value,c={value:f.name,id:f.pkid};r.push(c)}}catch(m){n=!0,i=m}finally{try{o||null==a.return||a.return()}finally{if(n)throw i}}}t(r)},querySearchOffice:function(e,t){var r=[];if(this.form.pd_office_list.length>0){var o=!0,n=!1,i=void 0;try{for(var s,a=this.form.pd_office_list[Symbol.iterator]();!(o=(s=a.next()).done);o=!0){var f=s.value,c={value:f.name,id:f.pkid};r.push(c)}}catch(m){n=!0,i=m}finally{try{o||null==a.return||a.return()}finally{if(n)throw i}}}t(r)},ck_user:function(e,t){var r={username:e};Object(u["a"])(r).then((function(e){"0"==e.code?t(new Error("賬戶已存在!")):t()}))},save:function(){var e=this;this.$refs["form"].validate((function(t){return!!t&&(e.form.entrps_group&&e.form.pd_office?0==e.form.processRange.length?e.$message.error("請選擇加工範圍"):void e.updateInfo():e.$message.error("所屬單位未填寫完整"))}))},updateInfo:function(){var e=this,t=[],r=!0,o=!1,n=void 0;try{for(var i,s=this.form.processRange[Symbol.iterator]();!(r=(i=s.next()).done);r=!0){var a=i.value;t.push({name:a})}}catch(c){o=!0,n=c}finally{try{r||null==s.return||s.return()}finally{if(o)throw n}}var f={pkid:this.userInfo.pkid,username:this.form.name,fctry_zone:this.form.fctry_zone,secn_cmpy:this.form.secn_cmpy,entrps_group:this.form.entrps_group,pd_office:this.form.pd_office,dept_name:this.form.departName,dept_code:this.form.departCode,legal_person:this.form.corporate,cost_code:this.form.costCode,recv_range_list:t,busis_mngr:this.form.manager,tel:this.form.telNum,phone:this.form.mobileNum,email:this.form.email,summary:this.form.intro,user_pic_file:this.user_pic_file,send_recv_type:this.userInfo.send_recv_type};Object(l["j"])(f).then((function(t){"1"===t.code?(e.$message.success("已保存"),Object(l["h"])(e.userInfo.pkid).then((function(t){"1"===t.code?(e.$store.dispatch("get_userInfo",t.t),e.$store.dispatch("get_porImgUrl",Object(p["b"])())):e.$message.error(t.msg)}))):e.$message.error("出錯啦，稍後再試試吧")}))},upImg:function(){this.$refs.fileInp.dispatchEvent(new MouseEvent("click"))},getFile:function(){var e=this,t=this.$refs.fileInp.files[0],r=window.URL||window.webkitURL;if(t){if("image/jpeg"!==t.type&&"image/png"!==t.type&&"image/gif"!==t.type)return void alert("請選擇圖片文件");var o=r.createObjectURL(t);this.form.portraitUrl=o;var n=new FormData;n.append("file",t),n.append("file_type","user_pic"),Object(m["d"])(n).then((function(t){"1"===t.code?e.user_pic_file=t.t:e.$message.error(t.msg)}))}},initData:function(){var e=[],t=!0,r=!1,o=void 0;try{for(var n,i=this.userInfo.recv_range_list[Symbol.iterator]();!(t=(n=i.next()).done);t=!0){var s=n.value;e.push(s.name)}}catch(a){r=!0,o=a}finally{try{t||null==i.return||i.return()}finally{if(r)throw o}}this.form.portraitUrl=this.porImgUrl,this.form.name=this.userInfo.username,this.form.fctry_zone=this.userInfo.fctry_zone,this.form.secn_cmpy="富士康次集團",this.form.entrps_group=this.userInfo.entrps_group,this.form.pd_office=this.userInfo.pd_office,this.form.departName=this.userInfo.dept_name,this.form.departCode=this.userInfo.dept_code,this.form.corporate=this.userInfo.legal_person,this.form.costCode=this.userInfo.cost_code,this.form.processRange=e,this.form.bankName=this.userInfo.bank_name,this.form.bankAccount=this.userInfo.bank_acunt,this.form.manager=this.userInfo.busis_mngr,this.form.telNum=this.userInfo.tel,this.form.mobileNum=this.userInfo.phone,this.form.email=this.userInfo.email,this.form.intro=this.userInfo.summary,this.form.in_out_type=this.userInfo.in_out_type},open:function(){var e=this,t="費用代碼";this.$prompt("请输入"+t,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputErrorMessage:t+"格式不正确"}).then((function(r){var o=r.value;if(!o.trim())return e.$message.warning("费用代码不能为空");e.$message({type:"success",message:"已修改"+t+"為: "+o}),e.form.costCode=o})).catch((function(){e.$message({type:"info",message:"取消输入"})}))},transmit:function(e){if("cancel"==e)this.$message({type:"info",message:"取消输入"});else{if("confirm"!=e)return;this.form.bankName=this.alertForm.bankName,this.form.bankAccount=this.alertForm.bankAccount,this.$message({type:"success",message:"修改成功"})}}},components:{Top:s["a"],Logo:a["a"],Footer:f["a"],SilderBar:c["a"]},watch:{},computed:h({},Object(d["c"])({userInfo:function(e){return e.userInfo},porImgUrl:function(e){return e.porImgUrl}}))},b=_,v=(r("2d89"),r("2877")),y=Object(v["a"])(b,o,n,!1,null,"494d0a6e",null);t["default"]=y.exports},f9bd:function(e,t,r){"use strict";var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return e.userInfo?r("div",{staticClass:"sideBar"},[r("div",{staticClass:"side-portrait"},[e.porImg?r("img",{staticStyle:{width:"120px",height:"120px","text-align":"center","line-height":"120px",borderRadius:"50%"},attrs:{src:e.porImg,alt:"頭像加載失敗",onerror:"this.style.display='none'"}}):r("div",{staticClass:"porImg"})]),r("div",{staticClass:"side-department"},[r("span",{staticClass:"dep-icon"}),r("span",[e._v(e._s(e.userInfo.dept_name))])]),r("div",{staticClass:"side-navigate"},e._l(e.list,(function(t,o){return r("router-link",{key:o,class:{active:e.chooseItem==t.ename},attrs:{to:t.path}},[r("div",[e._v("\n        "+e._s(t.name)+"\n        "),r("span",{directives:[{name:"show",rawName:"v-show",value:t.sub,expression:"item.sub"}]},[e._v("("+e._s(t.sub)+")")])])])})),1)]):e._e()},n=[],i=(r("8e6e"),r("ac6a"),r("456d"),r("bd86")),s=r("2f62");function a(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,o)}return r}function f(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?a(r,!0).forEach((function(t){Object(i["a"])(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):a(r).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}var c={data:function(){return{porImg:"",list:[{ename:"order",name:"訂單管理",path:"/personal",sub:""},{ename:"information",name:"賬戶信息",path:"/personal/information",sub:""},{ename:"evaluation",name:"評價管理",path:"/personal/evaluation",sub:""},{ename:"setting",name:"設置",path:"/personal/setting",sub:""}]}},props:["chooseItem"],mounted:function(){this.porImg=this.porImgUrl},methods:{},watch:{porImgUrl:function(){this.porImg=this.porImgUrl}},computed:f({},Object(s["c"])({userInfo:function(e){return e.userInfo},porImgUrl:function(e){return e.porImgUrl}}))},m=c,l=(r("6950"),r("2877")),u=Object(l["a"])(m,o,n,!1,null,"34d5c580",null);t["a"]=u.exports},fa13:function(e,t,r){}}]);
//# sourceMappingURL=chunk-1b182c2f.bfbe44f2.js.map