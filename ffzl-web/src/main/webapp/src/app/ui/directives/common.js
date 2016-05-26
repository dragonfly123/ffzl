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
                template:'<a href class="dropdown-toggle"><i class="glyphicon {{item.icon}} icon text-primary-dker"></i><span font-bold hidden-folded>{{item.text}}</span></a>',
                link: function(scope, el, attr) {
                    el.on('click', function (ev) {

                        var itemArray = [];
                        angular.forEach(scope.item.children,function (value) {
                            var subArray = [];
                            if(value.children){
                                angular.forEach(value.children,function (value2) {
                                    subArray.push({
                                        router:"",
                                        pullright:"",
                                        text:value2.text
                                    });
                                });
                            }
                            itemArray.push({
                                icon: "glyphicon-stats",
                                "translate": "",
                                "text":value.text,
                                router:"",
                                subitems:subArray
                            });
                        });

                        $rootScope.tree = [
                            {
                                "name": "Navigation",
                                "translate": "aside.nav.HEADER",
                                items:itemArray
                            }
                        ];
                        $compile(angular.element("nav"));
             /*           $scope.tree = [
                            {
                                "name": "Navigation",
                                "translate": "aside.nav.HEADER",
                                "items": [
                                    {
                                        icon: "glyphicon-stats",
                                        "translate": "aside.nav.DASHBOARD",
                                        "text": "Dashboard",
                                        "router": "",
                                        "subitems": [
                                            {
                                                router: "app.dashboard-v1",
                                                pullright: "",
                                                text: "Dashboard v1",
                                            }, {
                                                router: "app.dashboard-v2",
                                                pullright: "N",
                                                text: "Dashboard v2",
                                            },
                                        ]
                                    }, {
                                        icon: "glyphicon-calendar",
                                        "translate": "aside.nav.CALENDAR",
                                        "text": "Calendar",
                                        "router": "app.calendar"
                                    }
                                ]
                            }];*/
                    });
                }
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
        }])
        
    });
});
