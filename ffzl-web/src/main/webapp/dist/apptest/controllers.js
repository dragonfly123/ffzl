/*! ffzl 2016-05-16 */
var bookStoreCtrls=angular.module("bookStoreCtrls",[]);bookStoreCtrls.controller("HelloCtrl",["$scope",function(a){a.greeting={text:"hello"}}]),bookStoreCtrls.controller("BookListCtrl",["$scope",function(a){a.books=[{title:"<Ext江湖>",author:"aa"},{title:"<Ext江湖2>",author:"bb"},{title:"<Ext江湖3>",author:"cc"}]}]);