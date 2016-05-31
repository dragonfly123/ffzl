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

        ui.directive("condition",["$compile","layoutCondition",function ($compile,layoutCondition) {
            return{
                restrict:"EA",
                scope:{
                    input:"="
                },
                replace:"false",
                link:function (scope,element,attr) {
                    angular.element(element).append(layoutCondition.getTemplate(scope.input));
                    angular.element(element).append(layoutCondition.getTemplate(scope.input));
                    angular.element(element).append(layoutCondition.getTemplate(scope.input));
                    angular.element(element).append(layoutCondition.getTemplate(scope.input));
                    scope.datepickerOptions = {
                        format: 'yyyy-mm-dd',
                        language: 'zh',
                        startDate: "2012-10-01",
                        endDate: "2012-10-31",
                        autoclose: true,
                        weekStart: 0
                    }
                    $compile(element.contents())(scope);
                }
            }
        }]);
        
    });
});
