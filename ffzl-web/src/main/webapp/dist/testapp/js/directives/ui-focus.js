/*! ffzl 2016-05-16 */
angular.module("app").directive("uiFocus",function(a,b){return{link:function(c,d,e){var f=b(e.uiFocus);c.$watch(f,function(b){b===!0&&a(function(){d[0].focus()})}),d.bind("blur",function(){c.$apply(f.assign(c,!1))})}}});