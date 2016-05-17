/*! ffzl 2016-05-16 */
"use strict";angular.module("app").filter("fromNow",function(){return function(a){return moment(a).fromNow()}});