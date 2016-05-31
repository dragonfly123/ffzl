/**
 * Created by longfei on 16-5-29.
 */
define(["require"],function (require) {
    require(["ffzl-ui","angular"],function (ui,angular) {
        //
        ui.service("layoutCondition",[function(){
            var getNames = function (input) {
                var namesArray = [];
                if(input.cond  && (input.cond == "b" || input.cond == "bt")){
                    namesArray.push({name:input.name+"b",desc:input.desc+"-起"});
                    namesArray.push({name:input.name+"e",desc:input.desc+"-止"});
                } else  {
                    namesArray.push({name:input.name,desc:input.desc});
                }
                return namesArray;
            };
            
            var buildTemplate = function (input) {
                if(input.type == "D"){
                    return buildDateTemplate(getNames(input),input);
                } else {
                    return buildOtherTemplate(getNames(input),input);
                }
            } ;
            
            var buildDateTemplate = function (names,input){
                var htmls = [];
                angular.forEach(names,function (value,i) {
                    var $input  = angular.element("<input>");
                    $input.attr("placeholder",value.desc);
                    $input.attr("ng-model",value.name);
                    $input.attr("ng-datepicker","");
                    $input.attr("ng-optionsd","datepickerOptions");
                    var $div = angular.element("<div>");
                    $div.addClass("col-sm-2 col-xs-4 text-center m-r");
                    $div.attr("ui-calendar","");
                    $div.attr("ng-model",value.name);
                    $div.append($input);
                    $div.height(50);
                    htmls.push($div);
                });
                return htmls;
            };
            
            var buildOtherTemplate = function (names,input){
                var htmls = [];
                angular.forEach(names,function (value,i) {
                    var $input  = angular.element("<input>");
                    $input.attr("placeholder",value.desc);
                    $input.attr("ng-model",value.name);
                    var $div = angular.element("<div>");
                    $div.addClass("col-sm-2 col-xs-4 text-center m-r");
                    $div.append($input);
                    $div.height(50);
                    htmls.push($div);
                });
                return htmls;
            };
            
            return {
                getTemplate:buildTemplate
            }
        }]);
    });
})
