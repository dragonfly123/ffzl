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

                    var $div = angular.element("<div>");
                    $div.addClass("col-sm-3 col-xs-6 text-center");
                    var $label = angular.element("<label>");
                    $label.addClass("col-sm-4 font-bold");
                    $label.text(value.desc);
                    $label.appendTo($div);

                    var $datepicker = $("<datepicker>");
                    $datepicker.attr("button-prev","<i class='fa fa-arrow-left'></i>");
                    $datepicker.attr("button-next","<i class='fa fa-arrow-right'></i>");
                    $datepicker.attr("date-format","yyyy-MM-dd");
                    $datepicker.attr("date-max-limit",new Date());
                    $datepicker.addClass("col-sm-8");
                    $datepicker.appendTo($div);
                    if(i == 0){
                        var date = new Date();
                        date.setMonth(date.getMonth()-1);
                        $datepicker.attr("date-set",date);
                    } else {
                        $datepicker.attr("date-set",new Date());
                    }
                    var $input  = angular.element("<input>");
                    $input.attr("ng-model",value.name);
                    $input.attr("placeholder",value.desc);
                    $input.addClass("col-sm-12");
                    $input.appendTo($datepicker);
                    $div.height(40);
                    htmls.push($div);
                });
                return htmls;
            };
            
            var buildOtherTemplate = function (names,input){
                var htmls = [];
                angular.forEach(names,function (value,i) {

                    var $div = angular.element("<div>");
                    $div.addClass("col-sm-3 col-xs-6 text-center");
                    var $label = angular.element("<label>");
                    $label.addClass("col-sm-4 font-bold");
                    $label.text(value.desc);
                    $label.appendTo($div);

                    var $div2 = angular.element("<div>");
                    $div2.addClass("col-sm-8");
                    $div2.appendTo($div);
                    var $input  = angular.element("<input>");
                    $input.attr("placeholder",value.desc);
                    $input.attr("ng-model",value.name);
                    $input.addClass("col-sm-12");
                    $input.appendTo($div2);
                    $div.height(40);
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
