/*! ffzl 2016-05-16 */
var myModule=angular.module("Angello",[]);myModule.factory("AngelloHelper",function(){var a=function(a,b){for(var c=[],d=0,e=a.length;e>d;++d)c[a[d][b]]=a[d];return c};return{buildIndex:a}}),myModule.service("AngelloModel",function(){var a=this,b=[{title:"First story",description:"our first story",crieteria:"Criteria pending.",status:"To Do",type:"Feature",reporter:"Lukas Ruebbelke",assignee:"Brian Ford"},{title:"Second story",description:"our second story",crieteria:"Criteria pending.",status:"Back Log",type:"Feature",reporter:"Lukas Ruebbelke",assignee:"Brian Ford"},{title:"Another story",description:"Just one more",crieteria:"Criteria pending.",status:"Back Log",type:"Feature",reporter:"Lukas Ruebbelke",assignee:"Brian Ford"}];a.getStories=function(){return b}}),myModule.controller("MainCtrl",function(a){var b=this;b.stories=a.getStories(),b.createStory=function(){b.stories.push({title:"Second story",description:"our second story",crieteria:"Criteria pending.",status:"Back Log",type:"Feature",reporter:"Lukas Ruebbelke",assignee:"Brian Ford"})}}),myModule.directive("story",function(){return alert("aadsaddsaddsdjkltestfdsfdsad"),{scope:!0,replace:!0,template:"<div><h4>{{story.title}}</h4><p>{{story.description}}</p></div>"}});