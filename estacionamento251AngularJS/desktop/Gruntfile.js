module.exports = function (grunt) {
    //Collect all of our node modules
    var modules = []
    var package = require('./package.json')
    if (!!package.dependencies) {
        modules = Object.keys(package.dependencies)
            .filter(function (m) {
                return m != 'nodewebkit'
            })
            .map(function (m) {
                return './node_modules/' + m + '/**/*'
            });
    }
    //Grunt Config
    grunt.initConfig({
        shell: {
            start: {
                command: 'npm start'
            }
        },
        nwjs: {
            options: {
                cacheDir: './build/cache',
                macIcns: './app-icon.icns',
                winIco: './app-icon.ico',
                version: '0.12.3',
                buildDir: './builds', // Where the build version your app is saved
            },
            src: ['./package.json', './app/**/*'].concat(modules) // Your NW.js app
        }
    });

    grunt.loadNpmTasks('grunt-nw-builder');
    grunt.loadNpmTasks('grunt-shell');

    grunt.registerTask('run', ['shell']);
    grunt.registerTask('default', ['shell']);
    grunt.registerTask('build', 'Custom build task.', function (platform) {
        var platforms = [];
        // If no platform where specified, determine current platform
        if (arguments.length === 0) {
            if (process.platform === 'darwin') platforms.push('osx')
            else if (process.platform === 'win32') platforms.push('win')
            else if (process.arch === 'ia32') platforms.push('linux32')
            else if (process.arch === 'x64') platforms.push('linux64')

        } else {
            if (platform === 'win') platforms.push('win')
            if (platform === 'mac') platforms.push('osx')
            if (platform === 'linux32') platforms.push('linux32')
            if (platform === 'linux34') platforms.push('linux64')

            // Build for All platforms
            if (platform === 'all') platforms = ['win', 'osx', 'linux32', 'linux64']

        }


        if (platforms) {
            grunt.config('nwjs.options.platforms', platforms);
        }

        grunt.task.run(['nwjs']);

    });
};