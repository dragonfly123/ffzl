/*! ffzl 2016-05-16 */
var userInfoModule=angular.module("UserInfoModule",[]);userInfoModule.controller("UserInfoCtrl",["$scope",function(a){a.userInfo={email:"452241339@qq.com",password:232323232,autoLogin:!0},a.getFormData=function(){},a.setFormData=function(){a.userInfo={email:"longfei@u51.com",password:232323232,autoLogin:!1}}}]);