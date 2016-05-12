/**
 * Created by longfei on 16-5-12.
 */
define(['require','angular','app'],function (require,angular) {
   'use strict';
    require(['domReady!'],function (document) {
        angular.bootstrap(document,['webapp'])
    })
});
