!function(n,t){"use strict";var c=!0,o=navigator.userAgent,i=o.indexOf("AFocus")>=0,a=function(n){c&&console.log(n)},e=function(n,t){this.token=null,this.succCallback=n,this.failCallback=t},l={},s=0,u=function(){return s++,s>=65535&&(s=0),s},r=function(){for(;s in l;)u();return s},f=function(n){var t=r();n.token=t,l[t]=n,a("{msg:'[registerContext]',token:"+t+",context:"+JSON.stringify(n)+"}")},d=function(){};d.prototype={},d.call=function(n,t,c,o,l){if(!i)return void a("not support afocus");var s=new e(o,l);f(s);var u={plugin:n,method:t,params:c,info:JSON.stringify(s)};a(JSON.stringify(u)),window.prompt(JSON.stringify(u),"hybrid://protocol/af")},d.callback=function(n,t,c){return i?void(n in l&&(a("{msg:'[callback] find context succssed.', token:"+n+"}"),"successed"==t?(a("{msg:'[callback] execute succssed callback.', token:"+n+"}"),l[n].succCallback(c)):"failed"==t&&(a("{msg:'[callback] execute failed callback.', token:"+n+"}"),l[n].failCallback(c)))):void a("not support afocus")},t.AFocus=d}(window,window.lib||(window.lib={}));