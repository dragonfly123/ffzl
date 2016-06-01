/**
 * Created by longfei on 16-5-19.
 */
define(["require"],function (require) {
    require(["app","angular"],function (app,angular) {
       /* app./!*config(
            ['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
                function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
                    // lazy controller, directive and service
                    app.controller = $controllerProvider.register;
                    app.directive  = $compileProvider.directive;
                    app.filter     = $filterProvider.register;
                    app.factory    = $provide.factory;
                    app.service    = $provide.service;
                    app.constant   = $provide.constant;
                    app.value      = $provide.value;
                }
            ])*!/config(['$translateProvider', function($translateProvider){
            // Register a loader for the static files
            // So, the module will search missing translation tables under the specified urls.
            // Those urls are [prefix][langKey][suffix].
            $translateProvider.useStaticFilesLoader({
                prefix: CONTEXTPATH+'src/app/l10n/',
                suffix: '.json'
            });
            // Tell the module what language to use by default
            $translateProvider.preferredLanguage('en');
            // Tell the module to store the language in the local storage
            $translateProvider.useLocalStorage();
        }]);*/
        app.constant('JQ_CONFIG',{
            slimScroll:     ['../../scripts/lib/slimScroll/jquery.slimscroll.js'],
            contextPath:CONTEXTPATH
        });

        app.run(["$rootScope","$http","JQ_CONFIG",function ($rootScope,$http,config) {
            $http.get(config.contextPath+"ffzl/common/execute?servicename=ffzl_base_menu",{
                responseType:"json",
                cache:true
            }).then(function (data) {
                if(data.status == 200) {
                    $rootScope.menu = data.data.data;
                }
            },function (error) {
                
            });




        }]);

    });

});
