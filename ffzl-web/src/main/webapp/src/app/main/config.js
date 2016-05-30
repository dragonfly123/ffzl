/**
 * Created by longfei on 16-5-16.
 */
/**
 * Created by longfei on 16-5-12.
 */
(function(win){
    require.config({
        baseUrl: CONTEXTPATH,
        paths: {
            // the left side is the module ID,
            // the right side is the path to
            // the jQuery file, relative to baseUrl.
            // Also, the path should NOT include
            // the '.js' file extension. This example
            // is using jQuery 1.9.0 located at
            // js/lib/jquery-1.9.0.js, relative to
            // the HTML page.
            "angular":'scripts/lib/angular/angular',
            "jquery":"scripts/lib/jquery/dist/jquery",
            "angularAMD":"scripts/lib/angularAMD/angularAMD.js",
            "angular-animate":'scripts/lib/angular-animate/angular-animate',
            'ui-bootstrap':"scripts/lib/angular-bootstrap/ui-bootstrap",
            "ui-bootstrap-tpls":"scripts/lib/angular-bootstrap/ui-bootstrap-tpls",
            "angular-ui-router":"scripts/lib/angular-ui-router/release/angular-ui-router",
            "angular-translate":"scripts/lib/angular-translate/angular-translate",
            "angular-translate-loader-static-files":"scripts/lib/angular-translate-loader-static-files/angular-translate-loader-static-files",
            "angular-cookies":"scripts/lib/angular-cookies/angular-cookies",
            "angular-storage-cookies":"scripts/lib/angular-translate-storage-cookie/angular-translate-storage-cookie",
            "angular-translate-storage-local":"scripts/lib/angular-translate-storage-local/angular-translate-storage-local",
            "angular-ui-jq":"scripts/lib/angular-ui-jq/ui-jq",
            "angualr-ui-load":"scripts/lib/angular-ui-load/ui-load",
            "ngStorage":"scripts/lib/ngstorage/ngStorage",
            "ng-fullscreen":"scripts/lib/ng-fullscreen/ng-fullscreen",
            "app":"src/app/main/app",
            "ffzl-ui":"src/app/ui/ffzl-ui",
            "ffzl-common-directive":"src/app/ui/directives/common",
            "ffzl-common-service":"src/app/ui/services/common",
            "ui-nav":"src/app/ui/directives/ui-nav",
            "ui-calendar":"scripts/lib/angular-ui-calendar/src/calendar",
            "fullcalendar":"scripts/lib/fullcalendar/dist/fullcalendar",
            "domReady":"scripts/lib/domReady/domReady",
            "moment":"scripts/lib/moment/min/moment.min"

        } ,
        shim:{
            "angular":{
                deps:["jquery"],
                exports:"angular"
            },
            'angular-animate': {
                deps: ['angular'],   //依赖什么模块
                exports: 'ngAnimateModule'
            },
            "ng-fullscreen":{
                deps:["angular"],
                exports:"fullscreen"
            },
            "ui-bootstrap":{
                deps:['angular'],
                exports:"ui_bootstrap"
            },
            "ui-bootstrap-tpls":{
                deps:['angular'],
                exports:"ui_bootstrap_tpls"
            },
            'angular-ui-router':{
                deps:["angular"],
                exports:"ui_router"
            },
            'angular-translate':{
                deps:["angular"],
                exports:"translate"
            },
            "angular-translate-loader-static-files":{
                deps:["angular-translate"],
                exports:"translate_static_files"
            },
            "angular-cookies":{
                deps:["angular"],
                exports:"angular_cookies"
            },
            "angular-storage-cookies":{
                deps:["angular-cookies","angular-translate"],
                exports:"storage_cookies"
            },
            "angular-translate-storage-local":{
                deps:["angular-translate","angular-storage-cookies"],
                exports:"storage_local"
            },
            "ngStorage":{
                deps:["angular"],
                exports:"ngStorage"
            },
            "angular-ui-jq":{
                deps:["angular","jquery"],
                exports:"ui_jq"
            },
            "angualr-ui-load":{
                deps:["angular"],
                exports:"ui_load"
            },
        },
        deps:["src/app/main/boot"]
    });

})(window);
