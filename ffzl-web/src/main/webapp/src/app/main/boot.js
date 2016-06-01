/**
 * Created by longfei on 16-5-16.
 */
define(['require',"app","./appconfig",
    './routers/router','./controllers/appcontroller'],function (require) {
        'use strict';
        require(["angular",'domReady!'],function(angular){
            angular.bootstrap(document,['app']);
        });
});
