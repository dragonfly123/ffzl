/*! ffzl 2016-05-16 */
app.controller("CustomTabController",["$scope",function(a){a.tabs=[!0,!1,!1],a.tab=function(b){angular.forEach(a.tabs,function(b,c){a.tabs[c]=!1}),a.tabs[b]=!0}}]);