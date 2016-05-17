/**
 * Created by longfei on 16-5-14.
 */
var userInfoModule = angular.module("UserInfoModule",[]);
userInfoModule.controller("UserInfoCtrl",['$scope',function ($scope) {
    $scope.userInfo={
        email:"452241339@qq.com",
        password:232323232,
        autoLogin:true
    };
    $scope.getFormData=function () {
        console.log($scope.userInfo);
    };
    $scope.setFormData=function () {
        $scope.userInfo={
            email:"longfei@u51.com",
            password:232323232,
            autoLogin:false
        };
    }
}]);
