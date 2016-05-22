/**
 * Created by longfei on 16-5-21.
 */
define(["require"],function (require) {
    require([""],function (app) {
       app.directive("ffzlMenu",['$compile', function ($compile) {
           return {
               restrict:"AC",
               replace:false,
               scope:{
                   "tree":"="
               },
               template:"",
               link:function(scope, el, attr){
                   
               }
           }
       }]);
    });
})
