/**
 * Created by longfei on 16-5-14.
 */
var myCssModule = angular.module("MyCSSModule",[]);
myCssModule.controller("HeaderController",['$scope',function ($scope) {
    $scope.isError = false;
    $scope.isWarning  = false;
    $scope.showError =  function () {
        $scope.messageText = "This is an error!";
        $scope.isError = true;
        $scope.isWarning = false;
    }; $scope.showError =  function () {
        $scope.messageText = "This is an error!";
        $scope.isError = true;
        $scope.isWarning = false;
    };

    $scope.showWarning =  function () {
        $scope.messageText = "This is an isWarning!";
        $scope.isError = false;
        $scope.isWarning = true;
    };

}]);
