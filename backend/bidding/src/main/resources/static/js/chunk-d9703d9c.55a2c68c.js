(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d9703d9c"],{1480:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"setting"}},[n("div",{staticClass:"main_title"},[t._v("設置")]),n("button",{on:{click:function(e){return t.quit()}}},[t._v("退出登錄")])])},c=[],o=(n("8e6e"),n("ac6a"),n("456d"),n("bd86")),i=n("2f62");function u(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function s(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?u(n,!0).forEach((function(e){Object(o["a"])(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):u(n).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}var a={data:function(){return{}},methods:{quit:function(){var t=this;this.$jsonp("http://10.244.231.103:14000/bidsso/logout",{callbackName:"jsonpFunc"}).then((function(e){"1"===e.code&&(t.$router.push("/login"),t.$store.commit("LOG_OUT"))}))}},computed:s({},Object(i["c"])({userInfo:function(t){return t.userInfo}})),created:function(){}},f=a,p=(n("7015"),n("2877")),l=Object(p["a"])(f,r,c,!1,null,"61459068",null);e["default"]=l.exports},7015:function(t,e,n){"use strict";var r=n("7558"),c=n.n(r);c.a},7558:function(t,e,n){}}]);
//# sourceMappingURL=chunk-d9703d9c.55a2c68c.js.map