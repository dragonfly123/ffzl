/*! ffzl 2016-05-16 */
var myModule=angular.module("MyModule",[]);myModule.directive("hello",function(){return{restrict:"AECM",templateUrl:"hello.html",replace:!0}});