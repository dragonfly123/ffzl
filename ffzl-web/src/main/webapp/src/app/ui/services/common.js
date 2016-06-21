/**
 * Created by longfei on 16-5-29.
 */
define(["require"],function (require) {
    require(["ffzl-ui","angular"],function (ui,angular) {
        //
        ui.service("layoutCondition",[function(){

            var getDateOptions = function (objs) {
                angular.forEach(objs,function(obj,i){
                    obj.maxDate = new Date();
                    if(i == 0){
                        var date = new Date();
                        date.setMonth(date.getMonth()-1);
                        obj.init = date;
                    } else {
                        obj.init = new Date();
                    }
                });
                return objs;
            };

            var getOtherOptions = function(objs){

                return objs;
            };

            var getMutiOptions = function(func,input){
                var objs = [];
                objs.push({
                    name:input.name+"b",
                    desc:input.desc+"-起",
                    type:input.type
                });
                objs.push({
                    name:input.name+"e",
                    desc:input.desc+"-止",
                    type:input.type
                });

                return func(objs);

            }

            var getSingleOptions = function(func,input){
                var obj = {
                    name:input.name,
                    desc:input.desc,
                    type:input.type
                };

                return func([obj]);
            }
            var getCommonOptions = function(input){

                if(input.cond == "b" || input.cond == "bt"){
                    return getMutiOptions;
                } else {
                    return getSingleOptions;
                }
            }
            
            var getOptions = function(input){
                if(input.type == "D"){
                    return getCommonOptions(input)(getDateOptions,input);
                } else {
                    return getCommonOptions(input)(getOtherOptions,input);
                }
            };


            return {
                getOptions:getOptions
            }
        }]);
        
        ui.service("commonfunction",[function () {
            
        }]);
    });
})
