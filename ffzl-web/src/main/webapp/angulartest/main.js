/**
 * Created by longfei on 16-5-12.
 */
(function(win){
    require.config({
        baseUrl: 'js/lib',
        paths: {
            // the left side is the module ID,
            // the right side is the path to
            // the jQuery file, relative to baseUrl.
            // Also, the path should NOT include
            // the '.js' file extension. This example
            // is using jQuery 1.9.0 located at
            // js/lib/jquery-1.9.0.js, relative to
            // the HTML page.
            jquery: 'jquery/dist/jquery',
            angular:'angular/angular',
            bootstrap:"myboot",
            app:"app"
        } ,
        shim:{
            "angular":{
                exports:"angular"
            },
        },
        deps:"bootstrap"
    });

})(window);