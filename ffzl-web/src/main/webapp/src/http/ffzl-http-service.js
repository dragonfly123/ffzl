/**
 * Created by longfei on 16-6-6.
 */
define(["require"],function (require) {
   require(["ffzl-http"],function (fflzHttp) {
       fflzHttp.service("httpService",["$http","$q",function ($http,$q) {
           return {
               asyn:function(promise){
                   var defferred = $q.defer();
                   promise.success(function (data) {
                       defferred.resolve(data);
                   }).error(function (data) {
                       defferred.reject(data);
                   });
                   return defferred.promise;
               }
           }
       }]);
   }) ;
});
