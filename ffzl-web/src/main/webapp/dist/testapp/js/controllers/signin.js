/*! ffzl 2016-05-16 */
"use strict";app.controller("SigninFormController",["$scope","$http","$state",function(a,b,c){a.user={},a.authError=null,a.login=function(){a.authError=null,b.post("api/login",{email:a.user.email,password:a.user.password}).then(function(b){b.data.user?c.go("app.dashboard-v1"):a.authError="Email or Password not right"},function(b){a.authError="Server Error"})}}]);