/**
 * Created by longfei on 16-5-16.
 */
define(["angular","ngStorage","angular-animate",
    "angular-ui-router","angular-translate",
    "angular-translate-loader-static-files",
    "angular-translate-storage-local","angular-cookies"],function (angular) {
    'use strict';
    var app = angular.module('app', [
        'ui.router',
        "ngCookies",
        "ngStorage",
        "pascalprecht.translate"
    ]);
    app.config(
        [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
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
        ]).config(['$translateProvider', function($translateProvider){
            // Register a loader for the static files
            // So, the module will search missing translation tables under the specified urls.
            // Those urls are [prefix][langKey][suffix].
            $translateProvider.useStaticFilesLoader({
                prefix: 'l10n/',
                suffix: '.json'
            });
            // Tell the module what language to use by default
            $translateProvider.preferredLanguage('en');
            // Tell the module to store the language in the local storage
            $translateProvider.useLocalStorage();
        }]);
    return app;
});
