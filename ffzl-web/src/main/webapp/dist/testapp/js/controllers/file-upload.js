/*! ffzl 2016-05-16 */
app.controller("FileUploadCtrl",["$scope","FileUploader",function(a,b){var c=a.uploader=new b({url:"js/controllers/upload.php"});c.filters.push({name:"customFilter",fn:function(a,b){return this.queue.length<10}}),c.onWhenAddingFileFailed=function(a,b,c){},c.onAfterAddingFile=function(a){},c.onAfterAddingAll=function(a){},c.onBeforeUploadItem=function(a){},c.onProgressItem=function(a,b){},c.onProgressAll=function(a){},c.onSuccessItem=function(a,b,c,d){},c.onErrorItem=function(a,b,c,d){},c.onCancelItem=function(a,b,c,d){},c.onCompleteItem=function(a,b,c,d){},c.onCompleteAll=function(){}}]);