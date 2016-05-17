/**
 * Created by longfei on 16-5-14.
 */
var expanderModule  = angular.module('expanderModule',[]);
/*expanderModule.directive('expander',function () {
    return  {
        restrict:"EA",
        replace:true,
        transclude:true,
        scope:{
            title:"=expanderTitle"
        },
        template:"<div>"
        +"<div class='title' ng-click='toggle()'>{{title}}</div>"
        +"<div class='body' ng-show='showMe' ng-transclude></div>"
        +"</div>",
        link:function (scope,element,attrs) {
            scope.showMe=false;
            scope.toggle=function () {
                scope.showMe = !scope.showMe;
            }
        }
    }
    
});
expanderModule.controller("SomeController",function ($scope) {
    $scope.title = "点击展开";
    $scope.text = "这里是内部的内容";
})*/
expanderModule.directive("accordion",function () {
    return {
        restrict:"EA",
        replace:true,
        transclude:true,
        template:"<div ng-transclude></div>",
        controller:function () {
            var  expanders = [];
            this.gotOpend = function (selectExpander) {
                angular.forEach(expanders,function (expander) {
                    if(selectExpander != expander){
                        expander.showMe = false;
                    }
                });
            };
            this.addExpander =  function (expander) {
                expanders.push(expander);
            };
        }
    }
});
expanderModule.directive('expander',function () {
    return{
        restrict:"EA",
        replace:true,
        transclude:true,
        require:"^?accordion",
        scope:{
            title:"=expanderTitle"
        },
        template:"<div>"
        +"<div class='title' ng-click='toggle()'>{{title}}</div>"
        +"<div class='body' ng-show='showMe' ng-transclude></div>"
        +"</div>",
        link:function (scope,element,attrs,accordionController) {
            scope.showMe=false;
            accordionController.addExpander(scope);
            scope.toggle=function () {
                scope.showMe = !scope.showMe;
                accordionController.gotOpend(scope);
            }
        }
    };
});
expanderModule.controller("SomeController",function ($scope) {
    $scope.expanders =  [
        {
            title:"Click  me to expand",
            text:"mslfsalf;'lse'df;l"
        },{
            title:"Click  me to this",
            text:"mslfsalf;'dsaddd;l"
        }, {
            title: "test",
            text: "mslfsdsadsdalf;'dsaddd;l"
        }
    ]
})
