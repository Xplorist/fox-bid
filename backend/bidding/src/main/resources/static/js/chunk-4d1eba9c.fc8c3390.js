(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4d1eba9c"],{3150:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"main"},[r("section",{attrs:{id:"content"}},[r("div",{staticClass:"type-area"},[r("div",{staticClass:"header"},[e._m(0),r("div",{staticClass:"hea_right"},[r("router-link",{attrs:{to:"/"}},[e._v("返回首頁")])],1)]),r("div",{staticClass:"prin"},[r("div",{staticClass:"pri_bgBox"},[r("div",{staticClass:"pri_loginBox"},[r("div",{staticClass:"login_logo"}),"login"==e.nowStatus?r("div",{staticClass:"login_content"},[r("div",{staticClass:"title"},[e._v("登錄")]),r("el-form",{ref:"formLogin",attrs:{"status-icon":"",model:e.formLogin,rules:e.rulesLogin,"label-width":"50px","label-position":"left"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e._login()}}},[r("el-form-item",{staticClass:"name",attrs:{label:"用戶",prop:"name"}},[r("el-input",{attrs:{placeholder:"請輸入您的用戶名"},model:{value:e.formLogin.name,callback:function(t){e.$set(e.formLogin,"name",t)},expression:"formLogin.name"}})],1),r("el-form-item",{staticClass:"psd",attrs:{label:"密碼",prop:"psd"}},[r("el-input",{attrs:{type:"password","show-password":"",placeholder:"請輸入您的密碼"},model:{value:e.formLogin.psd,callback:function(t){e.$set(e.formLogin,"psd",t)},expression:"formLogin.psd"}})],1)],1),r("div",{staticClass:"opreation"},[r("div",{staticClass:"autoLogin"}),r("div",{staticClass:"forget_register"},[r("router-link",{attrs:{to:""},nativeOn:{click:function(t){return e.changeStatue("forgot")}}},[e._v("忘記密碼")]),r("router-link",{attrs:{to:"/merchant"}},[e._v("立即註冊")])],1)]),r("div",{staticClass:"nextBtn",on:{click:function(t){return e._login()}}},[e._v("立即登錄")])],1):"forgot"==e.nowStatus?r("div",{staticClass:"forgot_content"},[r("div",{staticClass:"title"},[e._v("找回密碼")]),r("el-form",{ref:"formForgot",attrs:{"status-icon":"",model:e.formForgot,rules:e.rulesForgot},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e._confirm()}}},[r("el-form-item",{staticClass:"account",attrs:{prop:"account"}},[r("el-input",{attrs:{placeholder:"請輸入您的賬號"},model:{value:e.formForgot.account,callback:function(t){e.$set(e.formForgot,"account",t)},expression:"formForgot.account"}}),r("el-button",{attrs:{disabled:e.getCodeFlag},on:{click:function(t){return e.getCode()}}},[e._v(e._s(e.getCodeFlag?"已發送("+e.countdown+")":"發送驗證碼"))]),r("span",{directives:[{name:"show",rawName:"v-show",value:e.getCodeFlag,expression:"getCodeFlag"}],staticClass:"tip"},[e._v("已發往"+e._s(e.formForgot.email))])],1),r("el-form-item",{staticClass:"verifCode",attrs:{prop:"verifCode"}},[r("el-input",{attrs:{placeholder:"請輸入驗證碼"},model:{value:e.formForgot.verifCode,callback:function(t){e.$set(e.formForgot,"verifCode",t)},expression:"formForgot.verifCode"}}),r("span",{directives:[{name:"show",rawName:"v-show",value:e.verifCodeFlag,expression:"verifCodeFlag"}],staticClass:"tip"},[e._v("驗證碼錯誤！")])],1)],1),r("div",{staticClass:"opreation"},[r("div"),r("div",{staticClass:"forget_register"},[r("router-link",{attrs:{to:""},nativeOn:{click:function(t){return e.changeStatue("login")}}},[e._v("返回")])],1)]),r("div",{staticClass:"nextBtn",on:{click:function(t){return e._confirm()}}},[e._v("確認")])],1):r("div",{staticClass:"alter_content"},[r("div",{staticClass:"title"},[e._v("修改密碼")]),r("el-form",{ref:"formAlter",attrs:{"status-icon":"",model:e.formAlter,rules:e.rulesAlter},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e._submit()}}},[r("el-form-item",{staticClass:"psd",attrs:{prop:"psd"}},[r("el-input",{attrs:{type:"password","show-password":"",placeholder:"請輸入新密碼"},model:{value:e.formAlter.psd,callback:function(t){e.$set(e.formAlter,"psd",t)},expression:"formAlter.psd"}})],1),r("el-form-item",{staticClass:"checkPsd",attrs:{prop:"checkPsd"}},[r("el-input",{attrs:{type:"password","show-password":"",placeholder:"請再次輸入密碼"},model:{value:e.formAlter.checkPsd,callback:function(t){e.$set(e.formAlter,"checkPsd",t)},expression:"formAlter.checkPsd"}})],1)],1),r("div",{staticClass:"opreation"},[r("div"),r("div",{staticClass:"forget_register"},[r("router-link",{attrs:{to:""},nativeOn:{click:function(t){return e.changeStatue("forgot")}}},[e._v("返回")])],1)]),r("div",{staticClass:"nextBtn",on:{click:function(t){return e._submit()}}},[e._v("確認")])],1)])])])])]),r("Footer")],1)},n=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"hea_left"},[r("div",{staticClass:"text1"},[r("span",[e._v("模治檢具")]),r("span",[e._v("招投標平台")])]),r("div",{staticClass:"text2"},[e._v("通行證")])])}],s=(r("8e6e"),r("ac6a"),r("456d"),r("7f7f"),r("bd86")),i=r("fd2d"),a=r("2f62"),c=r("5c96"),u=r("c24f"),l=r("3ebc");function f(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,o)}return r}function d(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?f(r,!0).forEach((function(t){Object(s["a"])(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):f(r).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}var g={data:function(){return{formLogin:{name:"",psd:""},rulesLogin:{name:[{required:!0,message:"請輸入賬號名",trigger:"blur"},{max:16,message:"長度不超過16個字符",trigger:"blur"}],psd:[{required:!0,message:"請輸入密碼"}]},formForgot:{account:"",verifCode:""},rulesForgot:{account:[{required:!0,message:"請先輸入賬號",trigger:"blur"}],email:"",verifCode:[{required:!0,message:"還未輸入驗證碼",trigger:"blur"}]},formAlter:{psd:"",checkPsd:""},rulesAlter:{psd:[{required:!0,message:"不能為空"},{min:4,max:16,message:"密碼長度為4-16"}],checkPsd:[{required:!0,message:"不能為空"}]},autoLogin:!1,nowStatus:"login",getCodeFlag:!1,countdown:60,verifCodeFlag:!1,account:"",verifCode:""}},methods:d({},Object(a["b"])({get_username:"get_username",get_token:"get_token",get_userInfo:"get_userInfo",get_porImgUrl:"get_porImgUrl"}),{_submit:function(){var e=this;this.$refs["formAlter"].validate((function(t){if(t){if(e.formAlter.psd!==e.formAlter.checkPsd)return e.$message.error("密碼不一致，請檢查！");var r={username:e.account,password:e.$md5(e.formAlter.psd),code:e.verifCode};Object(u["i"])(r).then((function(t){"1"===t.code?(e.$message.success("修改成功，請登錄！"),e.changeStatue("login")):e.$message.error(t.msg)}))}}))},getCode:function(){var e=this;this.$refs["formForgot"].validateField("account",(function(t){if(t)return!1;var r={username:e.formForgot.account};Object(u["c"])(r).then((function(t){if("1"===t.code){e.account=e.formForgot.account,e.formForgot.email=t.t,e.getCodeFlag=!0;var r=e,o=setInterval((function(){r.countdown<=0?(r.getCodeFlag=!1,r.countdown=60,clearInterval(o)):r.countdown--}),1e3)}else e.$message.error(t.msg)}))}))},changeStatue:function(e){this.$refs["formLogin"]?(this.$refs["formLogin"].clearValidate(),this.formLogin.name&&(this.formForgot.account=this.formLogin.name)):this.$refs["formForgot"]?this.$refs["formForgot"].resetFields():this.$refs["formAlter"].resetFields(),this.nowStatus=e},_confirm:function(){var e=this;if(!this.getCodeFlag)return this.$message.warning("請先獲取驗證碼！");this.$refs["formForgot"].validateField("verifCode",(function(t){if(!t){var r={username:e.account,code:e.formForgot.verifCode};Object(u["a"])(r).then((function(t){"1"===t.code?(e.verifCode=r.code,e.$message.info("驗證成功，請設置您的新密碼！"),e.changeStatue("alter")):e.$message.error(t.msg)}))}}))},_login:function(){var e=this;this.$refs["formLogin"].validate((function(t){if(!t)return!1;var r={username:e.formLogin.name,password:e.$md5(e.formLogin.psd)};Object(u["d"])(r).then((function(t){"1"===t.code?(e.get_username(e.formLogin.name),e.get_token(t.t),e.getInfo(),c["Message"].success(t.msg)):c["Message"].error(t.msg)}))}))},getInfo:function(){var e=this,t=this.username;Object(u["e"])(t).then((function(t){"1"===t.code?(e.get_userInfo(t.t),console.log(t.t.send_recv_type),"send"===t.t.send_recv_type?e.$router.push("/demand"):"system"===t.t.send_recv_type?e.$router.push("/admin"):e.$router.push("/"),e.get_porImgUrl(Object(l["b"])())):c["Message"].error(t.msg)})).catch((function(e){console.log(e)}))}}),computed:d({},Object(a["c"])({username:function(e){return e.username},userInfo:function(e){return e.userInfo}})),components:{Footer:i["a"]}},m=g,p=(r("449c"),r("2877")),v=Object(p["a"])(m,o,n,!1,null,"606df5b4",null);t["default"]=v.exports},"3ebc":function(e,t,r){"use strict";r.d(t,"b",(function(){return n})),r.d(t,"a",(function(){return s}));var o=r("fcc2");function n(e,t,r){var n=encodeURIComponent(e||o["a"].userInfo.user_pic_file.file_save_path),s=encodeURIComponent(t||o["a"].userInfo.user_pic_file.file_save_name),i=encodeURIComponent(r||o["a"].userInfo.user_pic_file.file_origin_name),a="/bid/api/bidding/pic_show/load",c=a+"?file_save_path="+n+"&file_save_name="+s+"&file_origin_name="+i;return c}function s(e,t,r){var o=encodeURIComponent(e),n=encodeURIComponent(t),s=encodeURIComponent(r),i="/bidding/pic_show/download",a=i+"?file_save_path="+o+"&file_save_name="+n+"&file_origin_name="+s;return a}},"449c":function(e,t,r){"use strict";var o=r("87dc"),n=r.n(o);n.a},"87dc":function(e,t,r){},c24f:function(e,t,r){"use strict";r.d(t,"d",(function(){return n})),r.d(t,"e",(function(){return s})),r.d(t,"h",(function(){return i})),r.d(t,"j",(function(){return a})),r.d(t,"c",(function(){return c})),r.d(t,"a",(function(){return u})),r.d(t,"i",(function(){return l})),r.d(t,"g",(function(){return f})),r.d(t,"b",(function(){return d})),r.d(t,"f",(function(){return g}));var o=r("365c");function n(e){var t="/bidding/user/login";return o["a"].post(t,e)}function s(e){e={username:e};var t="/bidding/user/login_user_info";return o["a"].postWithToken(t,e)}function i(e){e={pkid:e};var t="/bidding/user/query_user_info_by_pkid";return o["a"].post(t,e)}function a(e){var t="/bidding/user/update_user_info";return o["a"].postWithToken(t,e)}function c(e){var t="/bidding/user/generate_code";return o["a"].post(t,e)}function u(e){var t="/bidding/user/check_code";return o["a"].post(t,e)}function l(e){var t="/bidding/user/update_password";return o["a"].post(t,e)}function f(e){var t="/bidding/user/queryUserList";return o["a"].postWithToken(t,e)}function d(e){var t="/bidding/user/deleteUser";return o["a"].postWithToken(t,e)}function g(e){var t="/bidding/user/queryAllUsersStatistics";return o["a"].postWithToken(t,e)}}}]);
//# sourceMappingURL=chunk-4d1eba9c.fc8c3390.js.map