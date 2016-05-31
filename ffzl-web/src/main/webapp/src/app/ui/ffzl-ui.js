/**
 * Created by longfei on 16-5-21.
 */
define(["angular","datepicker"],function (angular) {
    var ui = angular.module("ffzl.ui",["720kb.datepicker"]);
    ui.run(["$templateCache", function($templateCache) {
        $templateCache.put("template/ffzl_ui_menu_groups","<group ng-repeat='group in tree' group='group'></group>");
        
        $templateCache.put("template/ffzl_ui_menu_items","<li class='hidden-folded padder m-t m-b-sm text-muted text-xs'> " +
            "<span translate='{{group.translate}}'>{{group.name}}</span></li><item ng-repeat='item in group.items' item='item'></item>");
        
        $templateCache.put("template/ffzl_ui_menu_item",
            "<li ng-if='item.subitems && item.subitems.length > 0'>" +
            "<a href class='auto'><span class='pull-right text-muted'><i class='fa fa-fw fa-angle-right text'></i>" +
            "<i class='fa fa-fw fa-angle-down text-active'></i></span><i class='glyphicon {{item.icon}} icon text-primary-dker'></i>" +
            "<span class='font-bold' translate='{{item.translate}}'>{{item.text}}</span></a><ul class='nav nav-sub dk'>" +
            "<li class='nav-sub-header'><a href><span translate='{{item.translate}}'>{{item.text}}</span></a></li>" +
            "<li ng-repeat='subitem in item.subitems' ui-sref-active='active'>" +
            "<a ui-sref='{{subitem.router}}'><b class='label bg-info pull-right' ng-if='subitem.pullright'>{{subitem.pullright}}</b>" +
            "<span>{{subitem.text}}</span></a></li></ul></li>" +
            "<li ng-if='!item.subitems || item.subitems.length == 0' ui-sref-active='active'><a ui-sref='{{item.router}}'>" +
            "<i class='glyphicon icon text-info-dker {{item.icon}}'></i><span class='font-bold' translate='{{item.translate}}'>" +
            "{{item.text}}</span> </a></li>");
    }]);
    return ui;
});


