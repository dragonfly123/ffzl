/**
 * Created by longfei on 16-5-12.
 */
var myModule = angular.module("HelloAngular",[]);
myModule.controller("helloAngular",['$scope',function HelloAngular($scope) {
    $scope.greeting =  {text:"Hello"}
}])
