/*! ffzl 2016-05-16 */
var loadGoogleMaps=function(a){var b,c=a.now();return function(d,e,f){if(b)return b;var g=a.Deferred(),h=function(){g.resolve(window.google&&google.maps?google.maps:!1)},i="loadGoogleMaps_"+c++,j=a.extend({sensor:!1},e?{key:e}:{},f?{language:f}:{});return window.google&&google.maps?h():window.google&&google.load?google.load("maps",d||3,{other_params:a.param(j),callback:h}):(j=a.extend(j,{v:d||3,callback:i}),window[i]=function(){h(),setTimeout(function(){try{delete window[i]}catch(a){}},20)},a.ajax({dataType:"script",data:j,url:"http://maps.google.com/maps/api/js"})),b=g.promise()}}(jQuery);