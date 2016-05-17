/**
 * Created by longfei on 16-5-14.
 */
var myModule = angular.module("MyModule",[]);
myModule.controller("MyCtrl",["$scope",function ($scope) {
    $scope.getName = "longfei test";
}]);
angular.element(document).ready(function () {
    angular.bootstrap(document,['MyModule']);
});
