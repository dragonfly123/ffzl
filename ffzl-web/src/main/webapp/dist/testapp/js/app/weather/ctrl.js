/*! ffzl 2016-05-16 */
function JSON_CALLBACK(){}app.controller("WeatherCtrl",["$scope","yahooApi","geoApi",function(a,b,c){a.userSearchText="",a.search={},a.forcast={},a.place={},a.data={},c.then(function(b){a.userSearchText=b.data.city+", "+b.data.country_code,a.getLocations()}),a.getLocations=function(){var c='select * from geo.places where text="'+a.userSearchText+'"';b.query({q:c,format:"json"},{},function(b){a.search=b,1!==b.query.count||b.query.results.channel||a.getWeather(b.query.results.place.woeid,b.query.results.place.name,b.query.results.place.country.content)})},a.getWeather=function(c,d,e){a.place.city=d,a.place.country=e;var f="select item from weather.forecast where woeid="+c;b.query({q:f,format:"json"},{},function(b){b.query.results.channel.item.forecast.forEach(function(b,c){b.icon=a.getCustomIcon(b.code)}),b.query.results.channel.item.condition.icon=a.getCustomIcon(b.query.results.channel.item.condition.code),a.data=b})},a.getCustomIcon=function(a){switch(a){case"0":case"1":case"2":case"24":case"25":return"wind";case"5":case"6":case"7":case"18":return"sleet";case"3":case"4":case"8":case"9":case"10":case"11":case"12":case"35":case"37":case"38":case"39":case"40":case"45":case"47":return"rain";case"13":case"14":case"15":case"16":case"17":case"41":case"42":case"43":case"46":return"snow";case"19":case"20":case"21":case"22":case"23":return"fog";case"26":case"27":case"28":case"44":return"cloudy";case"29":return"partly-cloudy-night";case"30":return"partly-cloudy-day";case"31":case"33":return"clear-night";case"32":case"34":case"36":return"clear-day";default:return""}}}]),app.factory("yahooApi",["$resource",function(a){return a("http://query.yahooapis.com/v1/public/yql",{},{query:{method:"GET",isArray:!1}})}]),app.factory("geoApi",["$http",function(a){return a.jsonp("http://muslimsalat.com/daily.json?callback=JSON_CALLBACK")}]);