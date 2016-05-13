/**
 * Created by longfei on 16-5-12.
 */
var myModule = angular.module("MyModule",[]);
myModule.directive("hello",function () {
    return {
        restrict:"E",
        template:"<div>Hi everyone!</div>",
        replace:true
    }
})
