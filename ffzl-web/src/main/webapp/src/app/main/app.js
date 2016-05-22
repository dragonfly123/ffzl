/**
 * Created by longfei on 16-5-16.
 */
define(["angular","ngStorage","angular-animate",
    "angular-ui-router","angular-translate",
    "angular-translate-loader-static-files",
    "angular-translate-storage-local","angular-cookies",
    "angualr-ui-load","angular-ui-jq","ffzl-ui","ui-nav",
    "ui-bootstrap-tpls","ng-fullscreen","ffzl-common"],function (angular) {
    'use strict';
    return angular.module('app', [
        'ui.router',
        "ngCookies",
        "ngStorage",
        "pascalprecht.translate",
        "ui.load",
        "ui.jq",
        "ffzl.ui",
        "ui.bootstrap",
        "ngFullscreen"
    ]);
});
