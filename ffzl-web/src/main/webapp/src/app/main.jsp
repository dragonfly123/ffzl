<%--
  Created by IntelliJ IDEA.
  User: longfei
  Date: 16-5-16
  Time: 上午11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Be Angular | Bootstrap Admin Web App with AngularJS</title>
    <meta name="description" content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <style type="text/css">
        @import "<%=request.getContextPath()%>/scripts/lib/bootstrap/dist/css/bootstrap.css";
/*
 @import "<%=request.getContextPath()%>/src/app/css/animate.css";
*/
        @import "<%=request.getContextPath()%>/scripts/lib/font-awesome/css/font-awesome.css";
        @import "<%=request.getContextPath()%>/src/app/css/simple-line-icons.css";
        @import "<%=request.getContextPath()%>/src/app/css/font.css";
        @import "<%=request.getContextPath()%>/src/app/css/app.css";
        @import "<%=request.getContextPath()%>/src/app/css/appext.css";
        @import "<%=request.getContextPath()%>/scripts/lib/angularjs-datepicker/dist/angular-datepicker.min.css";
        @import "<%=request.getContextPath()%>/scripts/lib/artDialog/css/ui-dialog.css";
/*       @import "<%=request.getContextPath()%>>/scripts/lib/ag-grid/dist/styles/ag-grid.css";
        @import "<%=request.getContextPath()%>>/scripts/lib/ag-grid/dist/styles/theme-blue.css";*/

        .select-page {
            width: 40px;
            text-align: center;
        }
        .pagination li a input {
            padding: 0;
            margin: -5px 0;

        }
        .st-selected {
            background-color: #46b8da;
            color: white;
        }

        .bootstrap-dialog .modal-header.bootstrap-dialog-draggable {
            cursor: move;
        }
    </style>
    <script>
        var CONTEXTPATH = "<%=request.getContextPath()%>/";
    </script>
    <script data-main="<%=request.getContextPath()%>/src/app/main/config.js" src="<%=request.getContextPath()%>/scripts/lib/requirejs/require.js"></script>
</head>
<body ng-controller="AppCtrl">
<div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed,
'app-aside-fixed':app.settings.asideFixed,
'app-aside-folded':app.settings.asideFolded,
'app-aside-dock':app.settings.asideDock,
'container':app.settings.container}" ui-view></div>


<%--<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Angular -->
<script src="vendor/angular/angular.js"></script>

<script src="vendor/angular/angular-animate/angular-animate.js"></script>
<script src="vendor/angular/angular-cookies/angular-cookies.js"></script>
<script src="vendor/angular/angular-resource/angular-resource.js"></script>
<script src="vendor/angular/angular-sanitize/angular-sanitize.js"></script>
<script src="vendor/angular/angular-touch/angular-touch.js"></script>--%>
<!-- Vendor -->
<%--<script src="vendor/angular/angular-ui-router/angular-ui-router.js"></script>
<script src="vendor/angular/ngstorage/ngStorage.js"></script>--%>

<!-- bootstrap -->
<%--<script src="vendor/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>--%>
<!-- lazyload -->
<%--<script src="vendor/angular/oclazyload/ocLazyLoad.js"></script>--%>
<!-- translate -->
<%--<script src="vendor/angular/angular-translate/angular-translate.js"></script>
<script src="vendor/angular/angular-translate/loader-static-files.js"></script>
<script src="vendor/angular/angular-translate/storage-cookie.js"></script>
<script src="vendor/angular/angular-translate/storage-local.js"></script>--%>

<!-- App -->
<%--<script src="js/app.js"></script>
<script src="js/config.js"></script>
<script src="js/config.lazyload.js"></script>
<script src="js/config.router.js"></script>
<script src="js/main.js"></script>
<script src="js/services/ui-load.js"></script>
<script src="js/filters/fromNow.js"></script>
<script src="js/directives/setnganimate.js"></script>
<script src="js/directives/ui-butterbar.js"></script>
<script src="js/directives/ui-focus.js"></script>
<script src="js/directives/ui-fullscreen.js"></script>
<script src="js/directives/ui-jq.js"></script>
<script src="js/directives/ui-module.js"></script>
<script src="js/directives/ui-nav.js"></script>
<script src="js/directives/ui-scroll.js"></script>
<script src="js/directives/ui-shift.js"></script>
<script src="js/directives/ui-toggleclass.js"></script>
<script src="js/directives/ui-validate.js"></script>
<script src="js/controllers/bootstrap.js"></script>--%>
<!-- Lazy loading -->
</body>
</html>

