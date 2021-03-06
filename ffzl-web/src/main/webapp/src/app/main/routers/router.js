/**
 * Created by longfei on 16-5-16.
 */
define(["app","angular"],function (app,angular) {
    'use strict';

    var relatevePath  = CONTEXTPATH+"src/app/main/tpl/"

    /**
     * Config for the router
     */
    app.run(
            [          '$rootScope', '$state', '$stateParams',"$http",
                function ($rootScope,$state,$stateParams,$http) {
                    $rootScope.$state = $state;
                    $rootScope.$stateParams = $stateParams;
                    $rootScope.$http = $http;

                }
            ]
        ).config(
            [          '$stateProvider', '$urlRouterProvider',
                function ($stateProvider,$urlRouterProvider) {

                    $urlRouterProvider
                        .otherwise("/app/dashboard/0/");
                    $stateProvider
                        .state('app', {
                            abstract: true,
                            url: '/app',
                            templateUrl: relatevePath+'app.html'
                        })
                        .state('app.dashboard', {
                            url: '/dashboard/{menuId}/{serviceName}',
                            views: {
                                "app_header":{
                                    templateUrl: relatevePath+'header.html',
                                    controller:function ($stateParams,$scope,$rootScope) {
                                        var menu = {};
                                        if($stateParams.menuId == 0) {
                                            menu = $rootScope.menu[0];
                                        } else{
                                            angular.forEach($rootScope.menu,function(item){
                                                if($stateParams.menuId == item.id){
                                                    menu = item;
                                                }
                                            });
                                        }
                                        while(menu.children && menu.children.length > 0){
                                            menu = menu.children[0];
                                        }
                                        if(menu.id) {
                                            $stateParams.menuId = menu.id;
                                            $stateParams.serviceName = menu.servicename;
                                        }
                                    }
                                },
                                "app_menu":{
                                    templateUrl:relatevePath+"menu.html",
                                    controller:function ($stateParams,$scope,$rootScope) {
                                        function isContain(item) {
                                            if(item.id == $stateParams.menuId){
                                                return true;
                                            } else if(item.children&&item.children.length>0){
                                                for(var iitem in item.children){
                                                    if(isContain(item.children[iitem])){
                                                        return true;
                                                    }
                                                }
                                            }
                                        }

                                        $rootScope.tree = [];
                                        var item = {};
                                        if($rootScope.menu === undefined){
                                            return;
                                        }
                                        for(var group in $rootScope.menu){
                                            if(isContain($rootScope.menu[group])){
                                                item = $rootScope.menu[group];
                                                break;
                                            }
                                        }

                                         var itemArray = [];
                                         angular.forEach(item.children,function (value,i) {
                                             var subArray = [];
                                             var active = false;
                                             if(value.children){
                                                 angular.forEach(value.children,function (value2,j) {
                                                     subArray.push({
                                                         router:"app.dashboard({menuId:"+value2.id+",serviceName:'"+(value2.servicename||'')+"'})",
                                                         pullright:"",
                                                         text:value2.text
                                                     });
                                                     if(value2.id == $stateParams.menuId){
                                                         active = true;
                                                     }
                                                 });
                                             }
                                             itemArray.push({
                                                 active:active,
                                                 icon: "glyphicon-stats",
                                                 "translate": "",
                                                 "text":value.text,
                                                 router:"app.dashboard({menuId:"+value.id+",serviceName:'"+(value.servicename||'')+"'})",
                                                 subitems:subArray
                                             });
                                         });
                                         $rootScope.tree.push({
                                             "name": item.text,
                                             "translate": "",
                                             items:itemArray
                                         });
                                    }
                                },
                                "app_content":{
                                    templateUrl:relatevePath+"dashboard_v1.html",
                                    controller:function ($stateParams,$scope,$http) {
                                        $http.get(CONTEXTPATH+"ffzl/common/layout?servicename="+$stateParams.serviceName,{
                                            responseType:"json",
                                            cache:true
                                        }).then(function (response) {
                                            if(response.status == 200) {
                                                $scope.layout = response.data;
                                            }
                                        },function (error) {

                                        });
                                    }
                                },
                                "app_aside":{templateUrl:relatevePath+"asideright.html"},
                                "app_footer":{templateUrl:relatevePath+"footer.html"},
                                "app_settings":{templateUrl:relatevePath+"settings.html"}
                            }
                        })
                        .state('app.dashboard-v2', {
                            url: '/dashboard-v2',
                            templateUrl: 'tpl/app_dashboard_v2.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load(['js/controllers/chart.js']);
                                    }]
                            }
                        })
                        .state('app.ui', {
                            url: '/ui',
                            template: '<div ui-view class="fade-in-up"></div>'
                        })
                        .state('app.ui.buttons', {
                            url: '/buttons',
                            templateUrl: 'tpl/ui_buttons.html'
                        })
                        .state('app.ui.icons', {
                            url: '/icons',
                            templateUrl: 'tpl/ui_icons.html'
                        })
                        .state('app.ui.grid', {
                            url: '/grid',
                            templateUrl: 'tpl/ui_grid.html'
                        })
                        .state('app.ui.widgets', {
                            url: '/widgets',
                            templateUrl: 'tpl/ui_widgets.html'
                        })
                        .state('app.ui.bootstrap', {
                            url: '/bootstrap',
                            templateUrl: 'tpl/ui_bootstrap.html'
                        })
                        .state('app.ui.sortable', {
                            url: '/sortable',
                            templateUrl: 'tpl/ui_sortable.html'
                        })
                        .state('app.ui.portlet', {
                            url: '/portlet',
                            templateUrl: 'tpl/ui_portlet.html'
                        })
                        .state('app.ui.timeline', {
                            url: '/timeline',
                            templateUrl: 'tpl/ui_timeline.html'
                        })
                        .state('app.ui.tree', {
                            url: '/tree',
                            templateUrl: 'tpl/ui_tree.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load('angularBootstrapNavTree').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/tree.js');
                                            }
                                        );
                                    }
                                ]
                            }
                        })
                        .state('app.ui.toaster', {
                            url: '/toaster',
                            templateUrl: 'tpl/ui_toaster.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad){
                                        return $ocLazyLoad.load('toaster').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/toaster.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.ui.jvectormap', {
                            url: '/jvectormap',
                            templateUrl: 'tpl/ui_jvectormap.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad){
                                        return $ocLazyLoad.load('js/controllers/vectormap.js');
                                    }]
                            }
                        })
                        .state('app.ui.googlemap', {
                            url: '/googlemap',
                            templateUrl: 'tpl/ui_googlemap.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( [
                                            'js/app/map/load-google-maps.js',
                                            'js/app/map/ui-map.js',
                                            'js/app/map/map.js'] ).then(
                                            function(){
                                                return loadGoogleMaps();
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.chart', {
                            url: '/chart',
                            templateUrl: 'tpl/ui_chart.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad){
                                        return uiLoad.load('js/controllers/chart.js');
                                    }]
                            }
                        })
                        // table
                        .state('app.table', {
                            url: '/table',
                            template: '<div ui-view></div>'
                        })
                        .state('app.table.static', {
                            url: '/static',
                            templateUrl: 'tpl/table_static.html'
                        })
                        .state('app.table.datatable', {
                            url: '/datatable',
                            templateUrl: 'tpl/table_datatable.html'
                        })
                        .state('app.table.footable', {
                            url: '/footable',
                            templateUrl: 'tpl/table_footable.html'
                        })
                        .state('app.table.grid', {
                            url: '/grid',
                            templateUrl: 'tpl/table_grid.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load('ngGrid').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/grid.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        // form
                        .state('app.form', {
                            url: '/form',
                            template: '<div ui-view class="fade-in"></div>',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad){
                                        return uiLoad.load('js/controllers/form.js');
                                    }]
                            }
                        })
                        .state('app.form.elements', {
                            url: '/elements',
                            templateUrl: 'tpl/form_elements.html'
                        })
                        .state('app.form.validation', {
                            url: '/validation',
                            templateUrl: 'tpl/form_validation.html'
                        })
                        .state('app.form.wizard', {
                            url: '/wizard',
                            templateUrl: 'tpl/form_wizard.html'
                        })
                        .state('app.form.fileupload', {
                            url: '/fileupload',
                            templateUrl: 'tpl/form_fileupload.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad){
                                        return $ocLazyLoad.load('angularFileUpload').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/file-upload.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.form.imagecrop', {
                            url: '/imagecrop',
                            templateUrl: 'tpl/form_imagecrop.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad){
                                        return $ocLazyLoad.load('ngImgCrop').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/imgcrop.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.form.select', {
                            url: '/select',
                            templateUrl: 'tpl/form_select.html',
                            controller: 'SelectCtrl',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load('ui.select').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/select.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.form.slider', {
                            url: '/slider',
                            templateUrl: 'tpl/form_slider.html',
                            controller: 'SliderCtrl',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load('vr.directives.slider').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/slider.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('app.form.editor', {
                            url: '/editor',
                            templateUrl: 'tpl/form_editor.html',
                            controller: 'EditorCtrl',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load('textAngular').then(
                                            function(){
                                                return $ocLazyLoad.load('js/controllers/editor.js');
                                            }
                                        );
                                    }]
                            }
                        })
                        // pages
                        .state('app.page', {
                            url: '/page',
                            template: '<div ui-view class="fade-in-down"></div>'
                        })
                        .state('app.page.profile', {
                            url: '/profile',
                            templateUrl: 'tpl/page_profile.html'
                        })
                        .state('app.page.post', {
                            url: '/post',
                            templateUrl: 'tpl/page_post.html'
                        })
                        .state('app.page.search', {
                            url: '/search',
                            templateUrl: 'tpl/page_search.html'
                        })
                        .state('app.page.invoice', {
                            url: '/invoice',
                            templateUrl: 'tpl/page_invoice.html'
                        })
                        .state('app.page.price', {
                            url: '/price',
                            templateUrl: 'tpl/page_price.html'
                        })
                        .state('app.docs', {
                            url: '/docs',
                            templateUrl: 'tpl/docs.html'
                        })
                        // others
                        .state('lockme', {
                            url: '/lockme',
                            templateUrl: 'tpl/page_lockme.html'
                        })
                        .state('access', {
                            url: '/access',
                            template: '<div ui-view class="fade-in-right-big smooth"></div>'
                        })
                        .state('access.signin', {
                            url: '/signin',
                            templateUrl: 'tpl/page_signin.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/controllers/signin.js'] );
                                    }]
                            }
                        })
                        .state('access.signup', {
                            url: '/signup',
                            templateUrl: 'tpl/page_signup.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/controllers/signup.js'] );
                                    }]
                            }
                        })
                        .state('access.forgotpwd', {
                            url: '/forgotpwd',
                            templateUrl: 'tpl/page_forgotpwd.html'
                        })
                        .state('access.404', {
                            url: '/404',
                            templateUrl: 'tpl/page_404.html'
                        })

                        // fullCalendar
                        .state('app.calendar', {
                            url: '/calendar',
                            templateUrl: 'tpl/app_calendar.html',
                            // use resolve to load other dependences
                            resolve: {
                                deps: ['$ocLazyLoad', 'uiLoad',
                                    function( $ocLazyLoad, uiLoad ){
                                        return uiLoad.load(
                                            ['vendor/jquery/fullcalendar/fullcalendar.css',
                                                'vendor/jquery/fullcalendar/theme.css',
                                                'vendor/jquery/jquery-ui-1.10.3.custom.min.js',
                                                'vendor/libs/moment.min.js',
                                                'vendor/jquery/fullcalendar/fullcalendar.min.js',
                                                'js/app/calendar/calendar.js']
                                        ).then(
                                            function(){
                                                return $ocLazyLoad.load('ui.calendar');
                                            }
                                        )
                                    }]
                            }
                        })

                        // mail
                        .state('app.mail', {
                            abstract: true,
                            url: '/mail',
                            templateUrl: 'tpl/mail.html',
                            // use resolve to load other dependences
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/app/mail/mail.js',
                                            'js/app/mail/mail-service.js',
                                            'vendor/libs/moment.min.js'] );
                                    }]
                            }
                        })
                        .state('app.mail.list', {
                            url: '/inbox/{fold}',
                            templateUrl: 'tpl/mail.list.html'
                        })
                        .state('app.mail.detail', {
                            url: '/{mailId:[0-9]{1,4}}',
                            templateUrl: 'tpl/mail.detail.html'
                        })
                        .state('app.mail.compose', {
                            url: '/compose',
                            templateUrl: 'tpl/mail.new.html'
                        })

                        .state('layout', {
                            abstract: true,
                            url: '/layout',
                            templateUrl: 'tpl/layout.html'
                        })
                        .state('layout.fullwidth', {
                            url: '/fullwidth',
                            views: {
                                '': {
                                    templateUrl: 'tpl/layout_fullwidth.html'
                                },
                                'footer': {
                                    templateUrl: 'tpl/layout_footer_fullwidth.html'
                                }
                            },
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/controllers/vectormap.js'] );
                                    }]
                            }
                        })
                        .state('layout.mobile', {
                            url: '/mobile',
                            views: {
                                '': {
                                    templateUrl: 'tpl/layout_mobile.html'
                                },
                                'footer': {
                                    templateUrl: 'tpl/layout_footer_mobile.html'
                                }
                            }
                        })
                        .state('layout.app', {
                            url: '/app',
                            views: {
                                '': {
                                    templateUrl: 'tpl/layout_app.html'
                                },
                                'footer': {
                                    templateUrl: 'tpl/layout_footer_fullwidth.html'
                                }
                            },
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/controllers/tab.js'] );
                                    }]
                            }
                        })
                        .state('apps', {
                            abstract: true,
                            url: '/apps',
                            templateUrl: 'tpl/layout.html'
                        })
                        .state('apps.note', {
                            url: '/note',
                            templateUrl: 'tpl/apps_note.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/app/note/note.js',
                                            'vendor/libs/moment.min.js'] );
                                    }]
                            }
                        })
                        .state('apps.contact', {
                            url: '/contact',
                            templateUrl: 'tpl/apps_contact.html',
                            resolve: {
                                deps: ['uiLoad',
                                    function( uiLoad ){
                                        return uiLoad.load( ['js/app/contact/contact.js'] );
                                    }]
                            }
                        })
                        .state('app.weather', {
                            url: '/weather',
                            templateUrl: 'tpl/apps_weather.html',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load(
                                            {
                                                name: 'angular-skycons',
                                                files: ['js/app/weather/skycons.js',
                                                    'vendor/libs/moment.min.js',
                                                    'js/app/weather/angular-skycons.js',
                                                    'js/app/weather/ctrl.js' ]
                                            }
                                        );
                                    }]
                            }
                        })
                        .state('music', {
                            url: '/music',
                            templateUrl: 'tpl/music.html',
                            controller: 'MusicCtrl',
                            resolve: {
                                deps: ['$ocLazyLoad',
                                    function( $ocLazyLoad ){
                                        return $ocLazyLoad.load([
                                            'com.2fdevs.videogular',
                                            'com.2fdevs.videogular.plugins.controls',
                                            'com.2fdevs.videogular.plugins.overlayplay',
                                            'com.2fdevs.videogular.plugins.poster',
                                            'com.2fdevs.videogular.plugins.buffering',
                                            'js/app/music/ctrl.js',
                                            'js/app/music/theme.css'
                                        ]);
                                    }]
                            }
                        })
                        .state('music.home', {
                            url: '/home',
                            templateUrl: 'tpl/music.home.html'
                        })
                        .state('music.genres', {
                            url: '/genres',
                            templateUrl: 'tpl/music.genres.html'
                        })
                        .state('music.detail', {
                            url: '/detail',
                            templateUrl: 'tpl/music.detail.html'
                        })
                        .state('music.mtv', {
                            url: '/mtv',
                            templateUrl: 'tpl/music.mtv.html'
                        })
                        .state('music.mtvdetail', {
                            url: '/mtvdetail',
                            templateUrl: 'tpl/music.mtv.detail.html'
                        })
                        .state('music.playlist', {
                            url: '/playlist/{fold}',
                            templateUrl: 'tpl/music.playlist.html'
                        })
                }
            ]
        );
    return app;
});
