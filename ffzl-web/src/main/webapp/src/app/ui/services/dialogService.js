/**
 * Created by admin on 16/6/15.
 */
define(["require"],function(){
    require(["ffzl-ui","angular","art-dialog"],function(ui,angular,dialog){
       ui.service("dialogService",["$timeout",function($timeout){

           var waningDialog = dialog({
               width:300,
               heigt:200,
               icon:"fa fa-info",
               quickClose:true,
               cancel:function(){
                   waningDialog.close();
                   return false;
               },
               cancelDisplay: false
           });



           return {
               info:function(content){
                   var info = dialog({
                       content:content
                   });

                   info.show();
                  $timeout(function(){
                       info.close();
                   },3000);
               },
               warning:function(content){
                   waningDialog.content(content);
                   waningDialog.title("警告");
                   waningDialog.show();
                  $timeout(function(){
                       waningDialog.close();
                   },3000);
               },
           }
       }]);
    });
});