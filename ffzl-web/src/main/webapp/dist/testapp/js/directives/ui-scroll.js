/*! ffzl 2016-05-16 */
angular.module("app").directive("uiScroll",["$location","$anchorScroll",function(a,b){return{restrict:"AC",link:function(c,d,e){d.on("click",function(c){a.hash(e.uiScroll),b()})}}}]);