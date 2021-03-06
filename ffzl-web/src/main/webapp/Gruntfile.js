/**
 * Created by longfei on 16-5-12.
 */
module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        concat:{
            options:{
                separator:";"
            },
            dist:{
                src:['src/**/*.js'],
                dest:'dist/<%= pkg.name %>.js'
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            buildall:{
                options:{
                    mangle:true,
                    compress:{
                        drop_console:true
                    },
                    report:"min"
                },
                files:[{
                    expand:true,
                    cwd:"src",
                    src:"**/*.js",
                    dest:"dist"
                }]
            },
           /* build: {
                src: 'src/<%= pkg.name %>.js',
                dest: 'build/<%= pkg.name %>.min.js'
            }*/
        },
        jshint: {
            options: {
                bitwise:true,
                freeze:true,
                curly: true,
                eqeqeq: true,
                eqnull: true,
                browser: true,
                globals: {
                    jQuery: true,
                    angular:true,
                    alert:true
                },
                funcscope:false,
                latedef:"nofunc",
                noempty:true,
                nonbsp:true,
                undef:true,
                unused:false
            },
            compile:{
                options: {
                    curly: true,
                    undef: true,
                },
                files: {
                    src: ['src/**/*.js']
                },
            }
        },
        watch:{
            scripts:{
                files:["src/**/*.js"],
                tasks:["compileall","minall"],
                options:{
                    spawn:true,
                    interrupt:true
                }
            }
        }
    });

    // 加载包含 "uglify" 任务的插件。
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-qunit');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');

    // 默认被执行的任务列表。
    grunt.registerTask('minall', ['uglify:buildall']);
    grunt.registerTask('compileall', ['jshint:compile']);
    grunt.registerTask('default', ['concat','buildall']);

};
