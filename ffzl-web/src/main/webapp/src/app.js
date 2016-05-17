/**
 * Created by admin on 16/5/12.
 */
var myModule = angular.module("Angello",[]);
myModule.factory('AngelloHelper',function(){
    var buildIndex = function(source,property){
        var tempArray = [];
        for(var i = 0,len = source.length;i <len;++i){
            tempArray[source[i][property]] = source[i];
        }
        return tempArray;
    };
    return {
        buildIndex:buildIndex
    };
});
myModule.service('AngelloModel',function(){
    var service = this,
        stories = [{
            title:"First story",
            description:"our first story",
            crieteria:"Criteria pending.",
            status:"To Do",
            type:"Feature",
            reporter:"Lukas Ruebbelke",
            assignee:"Brian Ford"
        },{
            title:"Second story",
            description:"our second story",
            crieteria:"Criteria pending.",
            status:"Back Log",
            type:"Feature",
            reporter:"Lukas Ruebbelke",
            assignee:"Brian Ford"
        }, {
                title:"Another story",
                description:"Just one more",
                crieteria:"Criteria pending.",
                status:"Back Log",
                type:"Feature",
                reporter:"Lukas Ruebbelke",
                assignee:"Brian Ford"
        }];
    service.getStories = function(){
        return stories;
    };
});
myModule.controller('MainCtrl',function(AngelloModel){
    var main = this;
    main.stories = AngelloModel.getStories();
    main.createStory = function(){
        main.stories.push({
            title:"Second story",
            description:"our second story",
            crieteria:"Criteria pending.",
            status:"Back Log",
            type:"Feature",
            reporter:"Lukas Ruebbelke",
            assignee:"Brian Ford"
        });
    };
});
myModule.directive('story',function(){
    alert("aadsaddsaddsdjkltestfdsfdsad");
    return {
        scope:true,
        replace:true,
        template:"<div><h4>{{story.title}}</h4><p>{{story.description}}</p></div>"
    };
});
