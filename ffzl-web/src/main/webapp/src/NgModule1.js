/**
 * Created by longfei on 16-5-14.
 */
console.log(angular);
var counter = 0;
for(var p in  angular){
    counter++;
    if(angular.isFunction(angular[p])){
        console.log("function->"+p+"-->");
    } else{
        console.log("property->"+"p"+angular[p])
    }
}
var helloModdule = angular.module("HelloAngular",[]);
helloModdule.controller("helloNgCtrl",["$scope",function ($scope) {
    $scope.greeting = {
        text:'Hello'
    }
}]);
