/*! ffzl 2016-05-16 */
var expanderModule=angular.module("expanderModule",[]);expanderModule.directive("accordion",function(){return{restrict:"EA",replace:!0,transclude:!0,template:"<div ng-transclude></div>",controller:function(){var a=[];this.gotOpend=function(b){angular.forEach(a,function(a){b!=a&&(a.showMe=!1)})},this.addExpander=function(b){a.push(b)}}}}),expanderModule.directive("expander",function(){return{restrict:"EA",replace:!0,transclude:!0,require:"^?accordion",scope:{title:"=expanderTitle"},template:"<div><div class='title' ng-click='toggle()'>{{title}}</div><div class='body' ng-show='showMe' ng-transclude></div></div>",link:function(a,b,c,d){a.showMe=!1,d.addExpander(a),a.toggle=function(){a.showMe=!a.showMe,d.gotOpend(a)}}}}),expanderModule.controller("SomeController",function(a){a.expanders=[{title:"Click  me to expand",text:"mslfsalf;'lse'df;l"},{title:"Click  me to this",text:"mslfsalf;'dsaddd;l"},{title:"test",text:"mslfsdsadsdalf;'dsaddd;l"}]});