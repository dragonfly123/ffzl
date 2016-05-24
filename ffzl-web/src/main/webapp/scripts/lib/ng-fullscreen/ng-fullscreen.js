(function(window) {
   var createModule = function(angular) {
      var module = angular.module('ngFullscreen', []);

      module.factory('Fullscreen', ['$document', function ($document) {
         var document = $document[0];

         var serviceInstance = {
            all: function() {
               serviceInstance.enable( document.documentElement );
            },
            enable: function(element) {
               if (element.requestFullscreen) {
                  element.requestFullscreen();
               } else if (element.webkitRequestFullscreen) {
                  element.webkitRequestFullscreen();
               } else if (element.mozRequestFullScreen) {
                  element.mozRequestFullScreen();
               } else if(element.webkitRequestFullScreen) {
                  element.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
               } else if(element.webkitRequestFullscreen) {
                   element.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
               } else if (element.msRequestFullscreen) {
                  element.msRequestFullscreen();
               }
            },
            cancel: function() {
               if (document.exitFullscreen) {
                   document.exitFullscreen();
               } else if (document.webkitExitFullscreen) {
                   document.webkitExitFullscreen();
               } else if (document.mozCancelFullScreen) {
                   document.mozCancelFullScreen();
               } else if(document.cancelFullScreen) {
                  document.cancelFullScreen();
               } else if(document.mozCancelFullScreen) {
                  document.mozCancelFullScreen();
               } else if(document.webkitCancelFullScreen) {
                  document.webkitCancelFullScreen();
               } else if(document.webkitCancelFullscreen) {
                   document.webkitCancelFullscreen();
               } else if (document.msExitFullscreen) {
                   document.msExitFullscreen();
               }
            },
            isEnabled: function(){
               var fullscreenElement = document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || document.msFullscreenElement;
               return fullscreenElement;
            },
            toggleAll: function(){
                serviceInstance.isEnabled() ? serviceInstance.cancel() : serviceInstance.all();
            },
            isSupported: function(){
                var docElm = document.documentElement;
                return docElm.requestFullScreen || docElm.mozRequestFullScreen || docElm.webkitRequestFullScreen || docElm.webkitRequestFullscreen || docElm.msRequestFullscreen;
            }
         };
         
         return serviceInstance;
      }]);

      module.directive('fullscreen', ['Fullscreen', '$document', function(Fullscreen, $document) {
         return {
            link : function ($scope, $element, $attrs) {
               // Watch for changes on scope if model is provided
               if ($attrs.fullscreen) {
                  $scope.$watch($attrs.fullscreen, function(value) {
                     var isEnabled = Fullscreen.isEnabled();
                     if (value && !isEnabled) {
                        Fullscreen.enable($element[0]);
                        $element.addClass('isInFullScreen');
                     } else if (!value && isEnabled) {
                        Fullscreen.cancel();
                        $element.removeClass('isInFullScreen');
                     }
                  });
                  $document.on('fullscreenchange webkitfullscreenchange mozfullscreenchange', function(){
                     if(!Fullscreen.isEnabled()){
                        $scope.$evalAsync(function(){
                           $scope[$attrs.fullscreen] = false
                           $element.removeClass('isInFullScreen');
                        })
                     }
                  })
               } else {
                  $element.on('click', function (ev) {
                     Fullscreen.enable(  $element[0] );
                  });

                  if ($attrs.onlyWatchedProperty !== undefined) {
                     return;
                  }
               }
            }
         };
      }]);
      return module;
   };

   if (typeof define === "function" && define.amd) {
      define(['angular'], function (angular) { return createModule(angular); } );
   } else {
      createModule(window.angular);
   }
})(window);
