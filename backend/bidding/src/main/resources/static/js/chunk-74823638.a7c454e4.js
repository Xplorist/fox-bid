(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-74823638"],{3150:function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"main"},[n("section",{attrs:{id:"content"}},[n("div",{staticClass:"type-area"},[n("div",{staticClass:"header"},[e._m(0),n("div",{staticClass:"hea_right"},[n("router-link",{attrs:{to:"/"}},[e._v("返回首頁")])],1)]),n("div",{staticClass:"prin"},[n("div",{staticClass:"pri_bgBox"},[n("div",{staticClass:"pri_loginBox"},[n("div",{staticClass:"login_logo"}),n("div",{staticClass:"login_content"},[n("div",{staticClass:"title"},[e._v("登錄")]),n("el-form",{ref:"formLogin",attrs:{"status-icon":"",model:e.form,rules:e.rules,"label-width":"50px","label-position":"left"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e._login()}}},[n("el-form-item",{staticClass:"name",attrs:{label:"用戶",prop:"name"}},[n("el-input",{attrs:{placeholder:"請輸入您的用戶名"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),n("el-form-item",{staticClass:"psd",attrs:{label:"密碼",prop:"psd"}},[n("el-input",{attrs:{type:"password",placeholder:"請輸入您的密碼"},model:{value:e.form.psd,callback:function(t){e.$set(e.form,"psd",t)},expression:"form.psd"}})],1)],1),n("div",{staticClass:"opreation"},[n("div",{staticClass:"autoLogin"},[n("el-checkbox",{model:{value:e.autoLogin,callback:function(t){e.autoLogin=t},expression:"autoLogin"}},[e._v("下次自動登錄")])],1),n("div",{staticClass:"forget_register"},[n("router-link",{attrs:{to:""}},[e._v("忘記密碼")]),n("router-link",{attrs:{to:"/merchant"}},[e._v("立即註冊")])],1)]),n("div",{staticClass:"loginBtn",on:{click:function(t){return e._login()}}},[e._v("立即登錄")])],1)])])])])]),n("Footer")],1)},o=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"hea_left"},[n("div",{staticClass:"text1"},[n("span",[e._v("模治檢具")]),n("span",[e._v("招投標平台")])]),n("div",{staticClass:"text2"},[e._v("通行證")])])}],s=(n("8e6e"),n("ac6a"),n("456d"),n("7f7f"),n("bd86")),a=n("fd2d"),i=n("2f62"),c=n("5c96"),u=n("c24f"),l=n("3ebc");function f(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function p(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?f(n,!0).forEach((function(t){Object(s["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):f(n).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var d={data:function(){return{form:{name:"",psd:""},rules:{name:[{required:!0,message:"請輸入賬號名"},{max:16,message:"長度不超過16個字符",trigger:"blur"}],psd:[{required:!0,message:"請輸入密碼"}]},autoLogin:!1}},methods:p({},Object(i["b"])({get_username:"get_username",get_token:"get_token",get_userInfo:"get_userInfo",get_porImgUrl:"get_porImgUrl"}),{_login:function(){var e=this;this.$refs["formLogin"].validate((function(t){if(!t)return!1;var n={username:e.form.name,password:e.form.psd};Object(u["a"])(n).then((function(t){"1"===t.code?(e.get_username(e.form.name),e.get_token(t.t),e.getInfo(),c["Message"].success(t.msg)):c["Message"].error(t.msg)}))}))},getInfo:function(){var e=this,t=this.username;Object(u["b"])(t).then((function(t){console.log("登錄信息"),console.log(t),"1"===t.code?(e.get_userInfo(t.t),console.log(t.t),"send"===t.t.send_recv_type?e.$router.push("/demand"):e.$router.push("/personal"),e.get_porImgUrl(Object(l["b"])())):c["Message"].error("出錯啦，稍後再試試吧！")})).catch((function(e){console.log(e)}))}}),computed:p({},Object(i["c"])({username:function(e){return e.username},userInfo:function(e){return e.userInfo}})),components:{Footer:a["a"]}},_=d,m=(n("8684"),n("2877")),g=Object(m["a"])(_,r,o,!1,null,"55778532",null);t["default"]=g.exports},"3ebc":function(e,t,n){"use strict";n.d(t,"b",(function(){return o})),n.d(t,"a",(function(){return s}));var r=n("fcc2");function o(){var e=encodeURIComponent(r["a"].userInfo.user_pic_file.file_save_path),t=encodeURIComponent(r["a"].userInfo.user_pic_file.file_save_name),n=encodeURIComponent(r["a"].userInfo.user_pic_file.file_origin_name),o="/api/pic_show/load",s=o+"?file_save_path="+e+"&file_save_name="+t+"&file_origin_name="+n;return s}function s(e,t,n){var r=encodeURIComponent(e),o=encodeURIComponent(t),s=encodeURIComponent(n),a="/api/pic_show/load",i=a+"?file_save_path="+r+"&file_save_name="+o+"&file_origin_name="+s;return i}},8684:function(e,t,n){"use strict";var r=n("a773"),o=n.n(r);o.a},a773:function(e,t,n){},c24f:function(e,t,n){"use strict";n.d(t,"a",(function(){return o})),n.d(t,"b",(function(){return s})),n.d(t,"c",(function(){return a})),n.d(t,"d",(function(){return i}));var r=n("365c");function o(e){var t="/api/user/login";return r["a"].post(t,e)}function s(e){e={username:e};var t="/api/user/login_user_info";return r["a"].postWithToken(t,e)}function a(e){e={pkid:e};var t="/api/user/query_user_info_by_pkid";return r["a"].post(t,e)}function i(e){var t="/api/user/update_user_info";return r["a"].postWithToken(t,e)}}}]);
//# sourceMappingURL=chunk-74823638.a7c454e4.js.map