/**
 * Created by longfei on 16-5-14.
 */

var myModule = angular.module("MyModule",[]);

myModule.controller("MyCtrl",["$scope",function ($scope) {
    $scope.ctrlFlavor = "百威";
    $scope.sayHello  = function (name) {
        alert("Hello " +  name);
    }
}]);
myModule.directive("drink",function () {
   /* return {
        restrict:"AE",
        template:"<div>{{flavor}}</div>",
        scope:{
            flavor:"@"
        }*/
        /*link:function (scope,element,attrs) {
            scope.flavor = attrs.flavor;
        }*/
   /* }*/
    return {
        restroct:"AE",
        scope:{
            flavor:"="
        },
        template:"<input type='text' ng-model='flavor'>"
    }
});
myModule.directive("greeting",function () {
   return {
       restrict:"AE",
       scope:{
           greet:"&"
       },
       template:"<br/><input  type='text' ng-model='userName'/><br/><button" +
       "" +
       "  class='btn btn-default' ng-click='greet({name:userName})'>greet</button>"
   }
});
