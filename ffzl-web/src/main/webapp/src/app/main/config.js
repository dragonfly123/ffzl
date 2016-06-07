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
            
            "jquery":"scripts/lib/jquery/dist/jquery",
            "angular":'scripts/lib/angular/angular',
            "ui-bootstrap/tpls":"scripts/lib/angular-bootstrap/ui-bootstrap-tpls",
            "angular/animate":'scripts/lib/angular-animate/angular-animate',
            "angular/cookies":"scripts/lib/angular-cookies/angular-cookies",
            "angular/ui/router":"scripts/lib/angular-ui-router/release/angular-ui-router",
            "angular/ui/jq":"scripts/lib/angular-ui-jq/ui-jq",
            "angualr/ui/load":"scripts/lib/angular-ui-load/ui-load",
            "ngStorage":"scripts/lib/ngstorage/ngStorage",
            "ng-fullscreen":"scripts/lib/ng-fullscreen/ng-fullscreen",
            "app":"src/app/main/app",
            "ffzl-ui":"src/app/ui/ffzl-ui",
            "ffzl-ui/directive/common":"src/app/ui/directives/common",
            "ffzl-ui/service/common":"src/app/ui/services/common",
            "ffzl-ui/directive/ui-nav":"src/app/ui/directives/ui-nav",
            "domReady":"scripts/lib/domReady/domReady",
            "datepicker":"scripts/lib/angularjs-datepicker/src/js/angular-datepicker",
            "ffzl-http":"src/http/ffzl-http",
            "ffzl-http/service/common":"src/http/ffzl-http-service"
        } ,
        shim:{
            "angular":{
                deps:["jquery"],
                exports:"angular"
            },
            "ui-bootstrap/tpls":{
                deps:['angular'],
                exports:"ui_bootstrap_tpls"
            },
            
            'angular/animate': {
                deps: ['angular'],   //依赖什么模块
                exports: 'ngAnimateModule'
            },
            'angular/ui/router':{
                deps:["angular"],
                exports:"ui_router"
            },
           /* 'angular/translate':{
                deps:["angular"],
                exports:"translate"
            },
            "angular/translate/loader/static/files":{
                deps:["angular/translate"],
                exports:"translate_static_files"
            },
            "angular/storage/cookies":{
                deps:["angular/cookies","angular/translate"],
                exports:"storage_cookies"
            },
            "angular/translate/storage/local":{
                deps:["angular/translate","angular/storage/cookies"],
                exports:"storage_local"
            },*/
            "angular/cookies":{
                deps:["angular"],
                exports:"angular_cookies"
            },
            "angular/ui/jq":{
                deps:["angular","jquery"],
                exports:"ui_jq"
            },
            "angualr/ui/load":{
                deps:["angular"],
                exports:"ui_load"
            },

            "ng-fullscreen":{
                deps:["angular"],
                exports:"fullscreen"
            },
            "ngStorage":{
                deps:["angular"],
                exports:"ngStorage"
            },
            "datepicker":{
                deps:["angular"]
            }

        },
        deps:["src/app/main/boot"]
    });

})(window);
