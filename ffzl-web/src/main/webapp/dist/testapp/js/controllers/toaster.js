/*! ffzl 2016-05-16 */
app.controller("ToasterDemoCtrl",["$scope","toaster",function(a,b){a.toaster={type:"success",title:"Title",text:"Message"},a.pop=function(){b.pop(a.toaster.type,a.toaster.title,a.toaster.text)}}]);