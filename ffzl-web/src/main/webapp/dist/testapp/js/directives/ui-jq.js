/*! ffzl 2016-05-16 */
"use strict";angular.module("ui.jq",["ui.load"]).value("uiJqConfig",{}).directive("uiJq",["uiJqConfig","JQ_CONFIG","uiLoad","$timeout",function(a,b,c,d){return{restrict:"A",compile:function(e,f){if(!angular.isFunction(e[f.uiJq])&&!b[f.uiJq])throw new Error('ui-jq: The "'+f.uiJq+'" function does not exist');var g=a&&a[f.uiJq];return function(a,e,f){function h(){var b=[];return f.uiOptions?(b=a.$eval("["+f.uiOptions+"]"),angular.isObject(g)&&angular.isObject(b[0])&&(b[0]=angular.extend({},g,b[0]))):g&&(b=[g]),b}function i(){d(function(){e[f.uiJq].apply(e,h())},0,!1)}function j(){f.uiRefresh&&a.$watch(f.uiRefresh,function(){i()})}f.ngModel&&e.is("select,input,textarea")&&e.bind("change",function(){e.trigger("input")}),b[f.uiJq]?c.load(b[f.uiJq]).then(function(){i(),j()})["catch"](function(){}):(i(),j())}}}}]);