/*! ffzl 2016-05-16 */
angular.module("app").directive("setNgAnimate",["$animate",function(a){return{link:function(b,c,d){b.$watch(function(){return b.$eval(d.setNgAnimate,b)},function(b,d){a.enabled(!!b,c)})}}}]);