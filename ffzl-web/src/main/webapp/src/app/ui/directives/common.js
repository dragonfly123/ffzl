/**
 * Created by longfei on 16-5-21.
 */
define(["require"],function (require) {
    require(["ffzl-ui","angular"],function (ui,angular) {
        ui.directive("ffzlFull",["$document",'Fullscreen',function ($document,Fullscreen) {
            return {
                restrict: 'AC',
                template:'<i class="icon-size-fullscreen text"></i><i class="icon-size-actual  text-active"></i>',
                link: function(scope, el, attr) {
                    el.on('click', function (ev) {
                        if(Fullscreen.isSupported()){
                            if(!el.hasClass("active")){
                                Fullscreen.toggleAll();
                                el.addClass("active");
                            } else {
                                Fullscreen.cancel();
                                el.removeClass("active");
                            }
                        }
                    });
                }
            };
        }]);
       
        ui.directive("menuItem",["$document","$location","$compile","$rootScope",function ($document,$location,$compile,$rootScope) {
            return {
                restrict: 'E',
                scope:{
                    item:"="
                },
                replace:true,
                template:'<a ui-sref="app.dashboard({menuId:item.id})" class="dropdown-toggle"><i class="glyphicon {{item.icon}} icon text-primary-dker"></i><span font-bold hidden-folded>{{item.text}}</span></a>',
            };
        }]);
        ui.directive('uiToggleClass', ['$timeout', '$document', function ($timeout, $document) {
            return {
                restrict: 'AC',
                link: function (scope, el, attr) {
                    el.on('click', function (e) {
                        e.preventDefault();
                        var classes = attr.uiToggleClass.split(','),
                            targets = (attr.target && attr.target.split(',')) || Array(el),
                            key = 0;
                        angular.forEach(classes, function (_class) {
                            var target = targets[(targets.length && key)];
                            ( _class.indexOf('*') !== -1 ) && magic(_class, target);
                            angular.element(target).toggleClass(_class);
                            key++;
                        });
                        angular.element(el).toggleClass('active');

                        function magic(_class, target) {
                            var patt = new RegExp('\\s' +
                                _class.replace(/\*/g, '[A-Za-z0-9-_]+').split(' ').join('\\s|\\s') +
                                '\\s', 'g');
                            var cn = ' ' + $(target)[0].className + ' ';
                            while (patt.test(cn)) {
                                cn = cn.replace(patt, ' ');
                            }
                            angular.element(target)[0].className=angular.element.trim(cn);
                        }
                    });
                }

            }
        }]);
        
        ui.directive('uiScroll', ['$location', '$anchorScroll', function($location, $anchorScroll) {
            return {
                restrict: 'AC',
                link: function(scope, el, attr) {
                    el.on('click', function(e) {
                        $location.hash(attr.uiScroll);
                        $anchorScroll();
                    });
                }
            };
        }]);

        ui.directive("condition",["$scope","layoutCondition",,function ($scope,layoutCondition) {
            return{
                restrict:"EA",
                scope:{
                    input:"="
                },
                replace:false,
                transclude:true,
                template:"<ng-transclude></ng-transclude>",
                link:function (scope,element,attr) {
                }
            }
        }]);
        ui.directive("conditionInput",["$compile",function($compile){
            return {
                restrict:"EA",
                scope:{
                    options:"="
                },
                link:function(scope,element,attr){
                    var $input = angular.element("<input>");
                    $input.attr("placeholder",scope.options.desc);
                    $input.attr("ng-model",scope.options.name);
                    $input.addClass("form-control");
                    $input.addClass("col-sm-12");
                    element.replaceWith($input);
                    $compile($input)(scope);
                }
            }
        }]);

        ui.directive("ffzlBox",[function(){
            return {
                restrict:"EA",
                scope:{
                    topButtons:"=",
                    bottomButtons:"=",
                    title:"@"
                },
                transclude:true,
                replace:true,
                template:"<div class='box box-info'>" +
                "<ffzl-box-header title='title' buttons='topButtons'></ffzl-box-header>" +
                "<ffzl-box-body><ng-transclude/></ffzl-box-body>" +
                "<ffzl-box-footer buttons='bottomButtons'></ffzl-box-footer>" +
                "</div>",
                controller:function($scope,$element){
                    this.collapse = function (childScope,childElement,childAddr) {
                        var box_contents = $element.find("> .box-body, > .box-footer, > form  >.box-body, > form > .box-footer");
                        if($element.hasClass("collapsed-box")){
                            if(childElement.children(":first").hasClass("fa-plus")){
                                childElement.children(":first").addClass("fa-minus");
                                childElement.children(":first").removeClass("fa-plus");
                            }
                            box_contents.slideDown(500,function () {
                                $element.removeClass("collapsed-box");
                            });
                        }  else {
                            if(!childElement.children(":first").hasClass("fa-plus")){
                                childElement.children(":first").removeClass("fa-minus");
                                childElement.children(":first").addClass("fa-plus");
                            }

                            box_contents.slideUp(500,function () {
                                $element.addClass("collapsed-box");
                            })
                        }

                    }
                }
            }
        }]);

        ui.directive("ffzlBoxHeader",[function () {
            return {
                restrict:"EA",
                require:"?^ffzlBox",
                scope:{
                    buttons:"=",
                    title:"="
                },
                replace:true,
                template:"<div class='box-header with-border'>" +
                "<h3 class='box-title'>{{title}}</h3>" +
                "<div class='box-tools pull-right'>" +
                "<ffzl-box-header-button ng-repeat='button in buttons' button='button'/>"+
                "</div></div>"

            }
        }]);
        ui.directive("ffzlBoxHeaderButton",[function(){
            return {
                restrict:"EA",
                require:"?^ffzlBox",
                replace:"true",
                scope:{
                    button:"="
                },
                replace:true,
                template:"<button class='btn btn-box-tool' title='{{button.text}}' data-widget='{{button.function}}'><i class='{{button.i}}'></i></button>",
                controller:function ($scope,$element) {

                },
                link:function (scope,element,attr,ffzlboxheader) {
                    var func = attr.widget;
                    var self ={

                    };

                    element.unbind("click").bind("click",function(e){
                        if(self[func] && typeof self[func] === "function" ){
                          self[func]();
                        }
                        else if(ffzlboxheader[func] && typeof ffzlboxheader[func]) {
                            ffzlboxheader[func](scope,element,attr);
                        }
                    });
                }
            }
        }]);

        ui.directive("ffzlBoxBody",[function(){
            return {
                restrict:"E",
                scope:{
                },
                transclude:true,
                replace:true,
                template:'<div class="box-body animated fade-in-down"><ng-transclude/></div>'
            }
        }]);
        ui.directive("ffzlBoxFooter",[function(){
            return {
                restrict:"EA",
                require:"?^ffzlBox",
                scope:{
                    buttons:"=",
                },
                replace:true,
                template:'<div class="box-footer">' +
                '<div ng-repeat="button in buttons" class="m-r-sm pull-right">' +
                '<ffzl-box-footer-button button="button"/>' +
                '</div>' +
                '</div>'
            }

        }]);
        ui.directive("ffzlBoxFooterButton",[function(){
            return {
                restrict:"E",
                require:"?^ffzlBoxFooter",
                scope:{
                    button:"=",
                },
                replace:true,
                template:'<a class="{{button.class}}"><i class="{{button.i}}"></i><span class="hidden-xs">{{button.text}}</span></a>',

            }
        }]);

    });
});
