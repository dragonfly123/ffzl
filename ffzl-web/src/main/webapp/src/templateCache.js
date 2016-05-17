/**
 * Created by longfei on 16-5-14.
 */
var myModule = angular.module("MyModule",[]);
myModule.run(function ($templateCache) {
   $templateCache.put("hello.html","<div>Hi everyone</div>") 
});

myModule.directive("hello",function ($templateCache) {
    return {
        restrict:"AECM",
        template:$templateCache.get("hello.html"),
        replace:true
    };
});
