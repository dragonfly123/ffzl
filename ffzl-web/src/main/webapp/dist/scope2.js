/*! ffzl 2016-05-16 */
var myModule=angular.module("myscope",[]);myModule.controller("EventController",["$scope",function(a){a.count=0,a.$on("MyEvent",function(){a.count++})}]);