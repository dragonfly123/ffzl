/**
 * Created by longfei on 16-5-16.
 */
define(["require"],function (require) {
    "use strict";
    /* Controllers */
    require(["app","angular","ffzl-ui"],function (app,angular) {
        app.controller('AppCtrl', ['$scope', '$localStorage', '$window',"$http","httpService",
            function($scope,   $localStorage,   $window ,$http,httpService) {
                // add 'ie' classes to html
                var isIE = !!navigator.userAgent.match(/MSIE/i);
                isIE && angular.element($window.document.body).addClass('ie');
                isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

                // config
                $scope.app = {
                    name: 'Angulr',
                    version: '1.3.3',
                    // for chart colors
                    color: {
                        primary: '#7266ba',
                        info:    '#23b7e5',
                        success: '#27c24c',
                        warning: '#fad733',
                        danger:  '#f05050',
                        light:   '#e8eff0',
                        dark:    '#3a3f51',
                        black:   '#1c2b36'
                    },
                    settings: {
                        themeID: 1,
                        navbarHeaderColor: 'bg-black',
                        navbarCollapseColor: 'bg-white-only',
                        asideColor: 'bg-black',
                        headerFixed: true,
                        asideFixed: false,
                        asideFolded: false,
                        asideDock: false,
                        container: false
                    }
                };
                

                // save settings to local storage
                if ( angular.isDefined($localStorage.settings) ) {
                    $scope.app.settings = $localStorage.settings;
                } else {
                    $localStorage.settings = $scope.app.settings;
                }
                $scope.$watch('app.settings', function(){
                    if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
                        // aside dock and fixed must set the header fixed.
                        $scope.app.settings.headerFixed = true;
                    }
                    // save to local storage
                    $localStorage.settings = $scope.app.settings;
                }, true);
                function isSmartDevice( $window )
                {
                    // Adapted from http://www.detectmobilebrowsers.com
                    var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
                    // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
                    return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
                }
            }]);
        app.controller("ConditionController",["$scope","layoutCondition",function($scope,layoutCondition){
            $scope.getOptions=layoutCondition.getOptions;
        }]);

        app.controller("mc",["$scope","$http","httpService",function ($scope,$http,httpService) {
            var ctrl = this;
            $scope.displayed = [];

            $scope.callServer = function callServer(tableState){
                $scope.isLoading = true;
                var pagination = tableState.pagination;

                var start = pagination.start || 0;
                var number = pagination.number ||10;

                httpService.asyn($http.get(CONTEXTPATH+"ffzl/common/execute?servicename=ffzl_base_menulist",{
                    responseType:"json",
                    cache:true
                })).then(function (recordset) {
                    $scope.displayed = recordset.data;
                    tableState.pagination.numberOfPages = 10
                    $scope.isLoading  = false;
                })
            };
        }])
    })
})
