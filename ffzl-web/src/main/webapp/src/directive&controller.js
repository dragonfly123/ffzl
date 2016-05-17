/**
 * Created by longfei on 16-5-14.
 */
var  myModule = angular.module("MyModule",[]);
myModule.controller("MyCtrl",["$scope",function ($scope) {
    $scope.loadData = function () {
        console.log("加载 数据中");
    }
}]);

myModule.controller("MyCtrl2",["$scope",function ($scope) {
    $scope.loadData2 = function () {
        console.log("加载 数据中2222");
    }
}]);

myModule.directive("loader",function () {
    return {
        restrict:"AE",
        link:function (scope,element,attrs) {
            element.bind("mouseenter",function () {
               // scope.loadData();
                //scope.$apply("loadData()");
                scope.$apply(attrs.howtoload);
            });
        }
    };
});
