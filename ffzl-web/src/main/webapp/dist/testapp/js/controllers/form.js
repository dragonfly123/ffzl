/*! ffzl 2016-05-16 */
"use strict";app.controller("FormDemoCtrl",["$scope",function(a){a.notBlackListed=function(a){var b=["bad@domain.com","verybad@domain.com"];return-1===b.indexOf(a)},a.val=15;var b=function(b){a.$apply(function(){a.val=b})};angular.element("#slider").on("slideStop",function(a){b(a.value)}),a.select2Number=[{text:"First",value:"One"},{text:"Second",value:"Two"},{text:"Third",value:"Three"}],a.list_of_string=["tag1","tag2"],a.select2Options={multiple:!0,simple_tags:!0,tags:["tag1","tag2","tag3","tag4"]},angular.element("#LinkInput").bind("click",function(a){a.stopPropagation()})}]);