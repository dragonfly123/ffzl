/**
 * Created by longfei on 16-5-16.
 */
define(['require','angular','app','app-controller','app-router'],function (require,angular) {
        'use strict';
        require(['angular','app','app-controller','app-router','domReady!'],function(){
            angular.bootstrap(document,['app']);
        });
});
