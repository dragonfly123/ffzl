/**
 * Created by longfei on 16-5-21.
 */
define(["angular","datepicker","./ffzl-ui/service/common",
    "./ffzl-ui/directive/common",
    "./ffzl-ui/directive/ui-nav","ffzl-http","./ffzl-http/service/common"],function (angular) {
    var ui = angular.module("ffzl.ui",["720kb.datepicker","ffzl.http"]);
    return ui;
});


