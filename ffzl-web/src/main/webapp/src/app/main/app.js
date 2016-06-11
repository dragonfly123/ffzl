/**
 * Created by longfei on 16-5-16.
 */
define(["angular","ngStorage","./angular/animate",
    "./angular/ui/router","smart-table",
    /*"./angular/translate/loader/static/files",
    "./angular/translate/storage/local",*/"./angular/cookies",
    "angualr/ui/load","angular/ui/jq","ffzl-ui",
    "./ui-bootstrap/tpls","ng-fullscreen"],function (angular) {
    'use strict';
    return angular.module('app', [
        'ui.router',
        "ngCookies",
        "ngStorage",
/*
        "pascalprecht.translate",
*/
        "ui.load",
        "ui.jq",
        "ffzl.ui",
        "ui.bootstrap",
        "ngFullscreen",
        "smart-table"
    ]);
});
