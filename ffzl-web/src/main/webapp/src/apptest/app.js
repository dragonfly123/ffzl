/**
 * Created by longfei on 16-5-14.
 */
var bookStoreApp = angular.module("bookStoreApp",['ngRoute','ngAnimate',
'bookStoreCtrls','bookStoreFilters','bookStoreServices','bookStoreDirectives'
]);

bookStoreApp.config(function ($routeProvider) {
    $routeProvider.when('/hello',{
        templateUrl:"tpls/hello.html",
        controller:"HelloCtrl"
    }).when("/list",{
        templateUrl:"tpls/booklist.html",
        controller:"BookListCtrl"
    }).otherwise({redirectTo:"/hello"})
});

