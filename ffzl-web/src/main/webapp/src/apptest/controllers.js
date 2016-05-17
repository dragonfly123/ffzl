/**
 * Created by longfei on 16-5-14.
 */
var bookStoreCtrls = angular.module("bookStoreCtrls",[]);
bookStoreCtrls.controller("HelloCtrl",["$scope",function ($scope) {
    $scope.greeting = {
        text:"hello"
    };
}]);

bookStoreCtrls.controller("BookListCtrl",['$scope',function ($scope) {
    $scope.books  = [
        {title:"<Ext江湖>",author:"aa"},
        {title:"<Ext江湖2>",author:"bb"},
        {title:"<Ext江湖3>",author:"cc"}
    ]
}])
