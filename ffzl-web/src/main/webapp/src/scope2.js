/**
 * Created by longfei on 16-5-13.
 */
var myModule = angular.module("myscope",[]);
myModule.controller("EventController",["$scope",function($scope){
   $scope.count = 0;
    $scope.$on("MyEvent",function(){
        $scope.count++;
    });
}]);
