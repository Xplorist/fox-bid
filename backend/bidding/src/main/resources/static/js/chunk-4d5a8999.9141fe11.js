(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4d5a8999"],{1169:function(t,e,n){var r=n("2d95");t.exports=Array.isArray||function(t){return"Array"==r(t)}},"37c8":function(t,e,n){e.f=n("2b4c")},"3a72":function(t,e,n){var r=n("7726"),i=n("8378"),a=n("2d00"),o=n("37c8"),s=n("86cc").f;t.exports=function(t){var e=i.Symbol||(i.Symbol=a?{}:r.Symbol||{});"_"==t.charAt(0)||t in e||s(e,t,{value:o.f(t)})}},"3ebc":function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"a",(function(){return a}));var r=n("fcc2");function i(){var t=encodeURIComponent(r["a"].userInfo.user_pic_file.file_save_path),e=encodeURIComponent(r["a"].userInfo.user_pic_file.file_save_name),n=encodeURIComponent(r["a"].userInfo.user_pic_file.file_origin_name),i="/api/pic_show/load",a=i+"?file_save_path="+t+"&file_save_name="+e+"&file_origin_name="+n;return a}function a(t,e,n){var r=encodeURIComponent(t),i=encodeURIComponent(e),a=encodeURIComponent(n),o="/api/pic_show/load",s=o+"?file_save_path="+r+"&file_save_name="+i+"&file_origin_name="+a;return s}},"67ab":function(t,e,n){var r=n("ca5a")("meta"),i=n("d3f4"),a=n("69a8"),o=n("86cc").f,s=0,c=Object.isExtensible||function(){return!0},f=!n("79e5")((function(){return c(Object.preventExtensions({}))})),u=function(t){o(t,r,{value:{i:"O"+ ++s,w:{}}})},l=function(t,e){if(!i(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;if(!a(t,r)){if(!c(t))return"F";if(!e)return"E";u(t)}return t[r].i},_=function(t,e){if(!a(t,r)){if(!c(t))return!0;if(!e)return!1;u(t)}return t[r].w},v=function(t){return f&&p.NEED&&c(t)&&!a(t,r)&&u(t),t},p=t.exports={KEY:r,NEED:!1,fastKey:l,getWeak:_,onFreeze:v}},"6ec3":function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"information"},[n("Top"),n("Logo"),t.merchantInfo?n("section",{attrs:{id:"content"}},[n("div",{staticClass:"main type-area"},[n("div",{staticClass:"warpper"},[n("div",{staticClass:"principal"},[n("div",{staticClass:"information"},[n("el-image",{staticClass:"info_logo",attrs:{src:t.porImgUrl,fit:"cover"}}),n("div",{staticClass:"info_name"},[n("span",{staticClass:"icon"}),n("span",[t._v(t._s(t.merchantInfo.dept_name))])]),n("div",{staticClass:"info_one"},[n("span",[t._v("廠區："+t._s(t.merchantInfo.fctry_zone)+" 》次集团："+t._s(t.merchantInfo.secn_cmpy)+" 》事業群："+t._s(t.merchantInfo.entrps_group)+" 》處："+t._s(t.merchantInfo.pd_office)+" 》單位："+t._s(t.merchantInfo.dept_name))])]),n("div",{staticClass:"info_two"},["recv"==t.merchantInfo.send_recv_type?n("span",{staticClass:"range"},[t._v("\n                加工範圍：\n                "),t._l(t.merchantInfo.recv_range_list,(function(e){return n("span",{key:e.pkid},[t._v(t._s(e.name))])}))],2):n("span",[t._v(" ")]),n("span",{staticClass:"tel"},[t._v("联系电话："+t._s(t.merchantInfo.tel))])]),n("div",{staticClass:"info_evaluation"},[n("div",{staticClass:"info_eva_title"},[t._v("客戶評價")]),n("div",{staticClass:"info_eva_list"},t._l(t.comments,(function(e,r){return n("div",{key:r,staticClass:"single"},[n("div",[n("div",{staticClass:"eva_text"},[t._v(t._s(e.text))]),n("div",{staticClass:"eva_date"},[t._v(t._s(e.date))])]),n("div",{staticClass:"eva_belong"},[t._v(t._s(e.belong))])])})),0)])],1),n("div",{staticClass:"introduction"},[t._m(0),n("div",{staticClass:"intro_text"},["recv"==t.merchantInfo.send_recv_type?n("div",{staticClass:"intro_text_title"},[t._v("客戶簡介")]):n("div",{staticClass:"intro_text_title"},[t._v("商戶簡介")]),n("pre",[t._v(t._s(t.merchantInfo.summary))])])])]),t._m(1)])])]):t._e(),n("Footer")],1)},i=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"intro_grade"},[n("table",{staticClass:"intro_gra_tab"},[n("tr",[n("th",[t._v("入駐時間:")]),n("th",[t._v("2年")])]),n("tr",[n("td",[t._v("客戶評分:")]),n("td",[t._v("4.9")])]),n("tr",[n("td",[t._v("出貨時效:")]),n("td",[t._v("3.2")])]),n("tr",[n("td",[t._v("出貨質量:")]),n("td",[t._v("3.2")])]),n("tr",[n("td",[t._v("服務態度:")]),n("td",[t._v("4.5")])])])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"more"},[n("p",[t._v("查看更多")]),n("span",{staticClass:"moreIcon"})])}],a=(n("ac4d"),n("8a81"),n("ac6a"),n("e9c9")),o=n("2241"),s=n("fd2d"),c=n("c24f"),f=n("3ebc"),u={data:function(){return{comments:[{text:"与该公司合作的非常愉快，交货时间没有延期，制作的东西非常棒，希望下次有机会再次合作。",date:"2019-08-29 17:22:37",belong:"FOXCONN 成都制一处"},{text:"很不错，多次合作，一如既往的好。",date:"2019-08-29 17:22:37",belong:"FOXCONN 成都制一处"},{text:"不好说，习惯性好评。",date:"2019-08-29 17:22:37",belong:"FOXCONN 成都制一处"},{text:"虽然出现了一点小状况，但是商家后面还是很耐心的给解决了，给商家服务态度点个赞。",date:"2019-08-29 17:22:37",belong:"FOXCONN 成都制一处"},{text:"与该公司合作的非常愉快，交货时间没有延期，制作的东西非常棒，希望下次有机会再次合作。",date:"2019-08-29 17:22:37",belong:"FOXCONN 成都制一处"}],merchantInfo:"",porImgUrl:""}},methods:{getMerchantInfo:function(){var t=this,e=this.$router.history.current.query.pkid;Object(c["c"])(e).then((function(e){if("1"===e.code){t.merchantInfo=e.t;var n=t.merchantInfo.user_pic_file;t.porImgUrl=Object(f["a"])(n.file_save_path,n.file_save_name,n.file_origin_name),t.comments=[];var r=!0,i=!1,a=void 0;try{for(var o,s=t.merchantInfo.get_eval_list[Symbol.iterator]();!(r=(o=s.next()).done);r=!0){var c=o.value,u={text:c.summary_text,date:c.create_date,belong:c.user.dept_name};t.comments.push(u)}}catch(l){i=!0,a=l}finally{try{r||null==s.return||s.return()}finally{if(i)throw a}}}else t.$message.error("出錯啦，稍後再試試吧")}))}},components:{Top:a["a"],Logo:o["a"],Footer:s["a"]},created:function(){this.getMerchantInfo()}},l=u,_=(n("bfff"),n("2877")),v=Object(_["a"])(l,r,i,!1,null,"7dd50b46",null);e["default"]=v.exports},"7bbc":function(t,e,n){var r=n("6821"),i=n("9093").f,a={}.toString,o="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[],s=function(t){try{return i(t)}catch(e){return o.slice()}};t.exports.f=function(t){return o&&"[object Window]"==a.call(t)?s(t):i(r(t))}},"8a81":function(t,e,n){"use strict";var r=n("7726"),i=n("69a8"),a=n("9e1e"),o=n("5ca1"),s=n("2aba"),c=n("67ab").KEY,f=n("79e5"),u=n("5537"),l=n("7f20"),_=n("ca5a"),v=n("2b4c"),p=n("37c8"),d=n("3a72"),m=n("d4c0"),h=n("1169"),b=n("cb7c"),y=n("d3f4"),g=n("4bf8"),C=n("6821"),I=n("6a99"),O=n("4630"),w=n("2aeb"),S=n("7bbc"),x=n("11e9"),N=n("2621"),E=n("86cc"),k=n("0d58"),F=x.f,j=E.f,P=S.f,U=r.Symbol,T=r.JSON,J=T&&T.stringify,R="prototype",W=v("_hidden"),X=v("toPrimitive"),$={}.propertyIsEnumerable,A=u("symbol-registry"),M=u("symbols"),D=u("op-symbols"),K=Object[R],q="function"==typeof U&&!!N.f,z=r.QObject,L=!z||!z[R]||!z[R].findChild,Y=a&&f((function(){return 7!=w(j({},"a",{get:function(){return j(this,"a",{value:7}).a}})).a}))?function(t,e,n){var r=F(K,e);r&&delete K[e],j(t,e,n),r&&t!==K&&j(K,e,r)}:j,G=function(t){var e=M[t]=w(U[R]);return e._k=t,e},Q=q&&"symbol"==typeof U.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof U},B=function(t,e,n){return t===K&&B(D,e,n),b(t),e=I(e,!0),b(n),i(M,e)?(n.enumerable?(i(t,W)&&t[W][e]&&(t[W][e]=!1),n=w(n,{enumerable:O(0,!1)})):(i(t,W)||j(t,W,O(1,{})),t[W][e]=!0),Y(t,e,n)):j(t,e,n)},H=function(t,e){b(t);var n,r=m(e=C(e)),i=0,a=r.length;while(a>i)B(t,n=r[i++],e[n]);return t},V=function(t,e){return void 0===e?w(t):H(w(t),e)},Z=function(t){var e=$.call(this,t=I(t,!0));return!(this===K&&i(M,t)&&!i(D,t))&&(!(e||!i(this,t)||!i(M,t)||i(this,W)&&this[W][t])||e)},tt=function(t,e){if(t=C(t),e=I(e,!0),t!==K||!i(M,e)||i(D,e)){var n=F(t,e);return!n||!i(M,e)||i(t,W)&&t[W][e]||(n.enumerable=!0),n}},et=function(t){var e,n=P(C(t)),r=[],a=0;while(n.length>a)i(M,e=n[a++])||e==W||e==c||r.push(e);return r},nt=function(t){var e,n=t===K,r=P(n?D:C(t)),a=[],o=0;while(r.length>o)!i(M,e=r[o++])||n&&!i(K,e)||a.push(M[e]);return a};q||(U=function(){if(this instanceof U)throw TypeError("Symbol is not a constructor!");var t=_(arguments.length>0?arguments[0]:void 0),e=function(n){this===K&&e.call(D,n),i(this,W)&&i(this[W],t)&&(this[W][t]=!1),Y(this,t,O(1,n))};return a&&L&&Y(K,t,{configurable:!0,set:e}),G(t)},s(U[R],"toString",(function(){return this._k})),x.f=tt,E.f=B,n("9093").f=S.f=et,n("52a7").f=Z,N.f=nt,a&&!n("2d00")&&s(K,"propertyIsEnumerable",Z,!0),p.f=function(t){return G(v(t))}),o(o.G+o.W+o.F*!q,{Symbol:U});for(var rt="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),it=0;rt.length>it;)v(rt[it++]);for(var at=k(v.store),ot=0;at.length>ot;)d(at[ot++]);o(o.S+o.F*!q,"Symbol",{for:function(t){return i(A,t+="")?A[t]:A[t]=U(t)},keyFor:function(t){if(!Q(t))throw TypeError(t+" is not a symbol!");for(var e in A)if(A[e]===t)return e},useSetter:function(){L=!0},useSimple:function(){L=!1}}),o(o.S+o.F*!q,"Object",{create:V,defineProperty:B,defineProperties:H,getOwnPropertyDescriptor:tt,getOwnPropertyNames:et,getOwnPropertySymbols:nt});var st=f((function(){N.f(1)}));o(o.S+o.F*st,"Object",{getOwnPropertySymbols:function(t){return N.f(g(t))}}),T&&o(o.S+o.F*(!q||f((function(){var t=U();return"[null]"!=J([t])||"{}"!=J({a:t})||"{}"!=J(Object(t))}))),"JSON",{stringify:function(t){var e,n,r=[t],i=1;while(arguments.length>i)r.push(arguments[i++]);if(n=e=r[1],(y(e)||void 0!==t)&&!Q(t))return h(e)||(e=function(t,e){if("function"==typeof n&&(e=n.call(this,t,e)),!Q(e))return e}),r[1]=e,J.apply(T,r)}}),U[R][X]||n("32e9")(U[R],X,U[R].valueOf),l(U,"Symbol"),l(Math,"Math",!0),l(r.JSON,"JSON",!0)},ac4d:function(t,e,n){n("3a72")("asyncIterator")},aeb3:function(t,e,n){},bfff:function(t,e,n){"use strict";var r=n("aeb3"),i=n.n(r);i.a},c24f:function(t,e,n){"use strict";n.d(e,"a",(function(){return i})),n.d(e,"b",(function(){return a})),n.d(e,"c",(function(){return o})),n.d(e,"d",(function(){return s}));var r=n("365c");function i(t){var e="/api/user/login";return r["a"].post(e,t)}function a(t){t={username:t};var e="/api/user/login_user_info";return r["a"].postWithToken(e,t)}function o(t){t={pkid:t};var e="/api/user/query_user_info_by_pkid";return r["a"].post(e,t)}function s(t){var e="/api/user/update_user_info";return r["a"].postWithToken(e,t)}},d4c0:function(t,e,n){var r=n("0d58"),i=n("2621"),a=n("52a7");t.exports=function(t){var e=r(t),n=i.f;if(n){var o,s=n(t),c=a.f,f=0;while(s.length>f)c.call(t,o=s[f++])&&e.push(o)}return e}}}]);
//# sourceMappingURL=chunk-4d5a8999.9141fe11.js.map