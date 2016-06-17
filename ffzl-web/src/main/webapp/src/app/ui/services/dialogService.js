/**
 * Created by admin on 16/6/15.
 */
define(["require"],function(){
    require(["ffzl-ui","angular","art-dialog"],function(ui,angular,dialog){
       ui.service("dialogService",["$timeout","$q",function($timeout,$q){


           var info = dialog({
               width:300,
               heigt:200,
               title:"消息",
               quickClose:true,
               cancel:function(){
                   info.close();
                   return false;
               },
               cancelDisplay: false
           });
           var warning = dialog({
               width:300,
               heigt:200,
               title:"警告",
               fixed:true,
               quickClose:false,
               cancel:function(){
                   warning.close();
                   return false;
               },
               cancelDisplay: false
           });

           var confrimDeferred = $q.defer();
           var confirm = dialog({
               title:"确认",
               fixed:true,
               quickClose:false,
               cancelValue:"取消",
               cancel:function(){
                   confrimDeferred.reject("cancel");
                   delete confrimDeferred;
                   confrimDeferred = $q.defer();
                   this.close();
                   return false;
               },
               okValue:"确认",
               ok:function(){
                   confrimDeferred.resolve("ok");
                   delete confrimDeferred;
                   confrimDeferred = $q.defer();
                   this.close();
                   return false;
               }
           });


           return {
               info:function(content){
                   info.content(content);
                   info.show();
                  $timeout(function(){
                       info.close();
                   },2000);
               },
               //手动关闭
               warning:function(content){
                   warning.content(content);
                   warning.show();
               },confirm:function(content){
                   confirm.content(content);
                   confirm.showModal();
                   return confrimDeferred.promise;
               }
           }
       }]);
    });
});