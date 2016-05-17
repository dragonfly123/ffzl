/*! ffzl 2016-05-16 */
var myModule=angular.module("MyModule",[]);myModule.directive("hello",function(){return{restrict:"E",template:"<div>Hi everyone!</div>",replace:!0}});