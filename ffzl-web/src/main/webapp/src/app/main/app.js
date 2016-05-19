/**
 * Created by longfei on 16-5-16.
 */
define(["angular","ngStorage","angular-animate",
    "angular-ui-router","angular-translate",
    "angular-translate-loader-static-files",
    "angular-translate-storage-local","angular-cookies"],function (angular) {
    'use strict';
    return angular.module('app', [
        'ui.router',
        "ngCookies",
        "ngStorage",
        "pascalprecht.translate"
    ]);
});
