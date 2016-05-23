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
