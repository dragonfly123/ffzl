/**
 * Created by longfei on 16-5-14.
 */
var myModule = angular.module("MyModule",[]);
myModule.directive("hello",function () {
   return {
       restrict:"AECM",
       templateUrl:"hello.html",
       replace:true
   };
});
