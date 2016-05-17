/**
 * Created by longfei on 16-5-14.
 */
var myModule = angular.module("MyModule",[]);
myModule.directive("hello",function () {
    return {
        restrict:"AE",
        template:"<div>Hi  everyOne <div ng-transclude></div></div>",
       // replace:true
        transclude:true
    };
});
